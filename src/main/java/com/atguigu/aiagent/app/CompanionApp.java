package com.atguigu.aiagent.app;

import com.atguigu.aiagent.advisor.MyLoggerAdvisor;
import com.atguigu.aiagent.chatmemory.FileBasedChatMemory;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.TestOnly;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;

@Component
@Slf4j
public class CompanionApp {
    private final ChatClient chatClient ;
    private static final String systemPrompt="扮演资深心理咨询专家，开场向用户表明身份，告知用户可倾诉心理困扰。";

    //初始化AI客户端
    public CompanionApp(ChatModel dashscopeChatModel){
        //初始化基于文件的对话记忆
        //System.getProperty可以获取系统属性，user.dir是用户当前工作目录
        String fileDir = System.getProperty("user.dir")+"/tmp/chat-memory";

        ChatMemory chatMemory = new FileBasedChatMemory(fileDir);


        //初始化基于内存的对话记忆
        //ChatMemory chatMemory = new InMemoryChatMemory();
        chatClient=ChatClient.builder(dashscopeChatModel)
                .defaultSystem(systemPrompt)
                .defaultAdvisors(
                        new MessageChatMemoryAdvisor(chatMemory),
                        //自定义日志拦截器，可按需开启
                        new MyLoggerAdvisor()
                )
                .build();
    }

    public String doChat(String message,String chatId){ //不同对话id隔离开
        ChatResponse chatResponse = chatClient
                .prompt()
                .user(message)
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10)
                )
                .call()
                .chatResponse();

        String content = chatResponse.getResult().getOutput().getText();
        //log.info("content: {}",content);
        return content;
    }


    record ChatReport (String title, List<String> suggestions){

    }

    //结构化输出
    public ChatReport doChatWithReport(String message,String chatId){ //不同对话id隔离开
        ChatReport chatReport = chatClient
                .prompt()
                .system(systemPrompt+"每次对话后都生成分析报告，标题为{用户名}的心理分析报告，内容为给用户的建议")
                .user(message)
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10)
                )
                .call()
                .entity(ChatReport.class);

        log.info("chatReport: {}",chatReport);
        return chatReport;
    }


}
