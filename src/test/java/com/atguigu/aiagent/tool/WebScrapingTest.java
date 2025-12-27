package com.atguigu.aiagent.tool;

import com.atguigu.aiagent.tools.WebScrapingTool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WebScrapingTest {
    @Test
    public void testWebScraping(){
        WebScrapingTool webScrapingTool = new WebScrapingTool();
        String result = webScrapingTool.scrapWebPage("https://www.searchapi.io/");
        Assertions.assertNotNull(result);
    }
}
