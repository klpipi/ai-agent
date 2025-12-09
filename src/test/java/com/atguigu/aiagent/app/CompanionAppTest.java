package com.atguigu.aiagent.app;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class CompanionAppTest {
    @Resource
    private CompanionApp companionApp;

    @Test
    void testChat(){
        String chatId = UUID.randomUUID().toString();
        //第一轮
        String message = "你好，我是小奇";
        String answer = companionApp.doChat(message,chatId);
        //第二轮
        message = "我现在大三，最近很焦虑";
        answer = companionApp.doChat(message,chatId);
        Assertions.assertNotNull(answer); //单元测试，盘非空
        //第三轮
        /*message = "我现在大几？";
        answer = companionApp.doChat(message,chatId);
        Assertions.assertNotNull(answer);*/
    }

    @Test
    void doChatWithReport(){
        String chatId = UUID.randomUUID().toString();
        String message = "你好，我是小奇,我即将大学毕业，面临就业压力，怎么办";
        CompanionApp.ChatReport chatReport = companionApp.doChatWithReport(message, chatId);
        Assertions.assertNotNull(chatReport);
    }

    @Test
    void doChatWithRag(){
        String chatId = UUID.randomUUID().toString();
        String message = "我总是拖延，容易焦虑怎么办";
        String answer = companionApp.chatWithRag(message, chatId);
        Assertions.assertNotNull(answer);
    }

}