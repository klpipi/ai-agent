package com.atguigu.aiagent.rag;


import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DocumentLoaderTest {
    @Resource
    private DocumentLoader documentLoader ;
    @Test
    public void loadMarkdownsTest(){
        documentLoader.loadMarkdowns();
    }
}
