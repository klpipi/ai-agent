package com.atguigu.aiagent.rag;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.rag.DashScopeDocumentRetriever;
import com.alibaba.cloud.ai.dashscope.rag.DashScopeDocumentRetrieverOptions;
import org.springframework.ai.chat.client.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.rag.retrieval.search.DocumentRetriever;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//自定义基于阿里云知识库的 RAG 增强顾问
@Configuration
public class RagCloudAdvisorConfig {

    @Value("${spring.ai.dashscope.api-key}")
    String dashScopeApiKey;

    @Bean
    public Advisor ragCloudAdvisor(){
        DashScopeApi dashScopeApi = new DashScopeApi(dashScopeApiKey);
        final String name = "test";
        DocumentRetriever documentRetriever = new DashScopeDocumentRetriever(dashScopeApi,
                DashScopeDocumentRetrieverOptions
                        .builder()
                        .withIndexName(name)
                        .build());
        return RetrievalAugmentationAdvisor.builder()
                .documentRetriever(documentRetriever)
                .build();
    }


}
