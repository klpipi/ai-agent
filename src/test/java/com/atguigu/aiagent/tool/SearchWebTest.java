package com.atguigu.aiagent.tool;


import com.atguigu.aiagent.tools.WebSearchTool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SearchWebTest {
    @Value("${search-api.api-key}")
    private String apiKey;

    @Test
    public void testSearchWeb(){
        WebSearchTool tool = new WebSearchTool(apiKey);
        String result = tool.searchWeb("抑郁症治疗方法");
        Assertions.assertNotNull(result);
    }
}
