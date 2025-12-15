package com.atguigu.aiagent.tool;

import com.atguigu.aiagent.tools.FileOperationTool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FileOperationTest {

    @Test
    public void testReadFile(){
        String fileName = "读写测试.txt";
        FileOperationTool fileOperationTool = new FileOperationTool();
        String result = fileOperationTool.readFile(fileName);
        Assertions.assertNotNull(result);
    }

    @Test
    public void testWriteFile(){
        String fileName = "读写测试.txt";
        FileOperationTool fileOperationTool = new FileOperationTool();
        String content = "写入测试";
        String result = fileOperationTool.writeFile(fileName, content);
        Assertions.assertNotNull(result);
    }
}
