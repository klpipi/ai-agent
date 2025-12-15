package com.atguigu.aiagent.tools;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.atguigu.aiagent.constant.FileConstant;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

public class FileOperationTool {
    private final String fileDir = FileConstant.FILE_SAVE_DIR + "/file";

    @Tool(description = "Read content from file")
    public String readFile(@ToolParam(description = "Name of the file") String fileName){
        String filePath = fileDir + "/" + fileName;
        try {
            return FileUtil.readUtf8String(filePath);
        } catch (Exception e) {
            return "Error reading file: " + e.getMessage();
        }
    }



    @Tool(description = "Write content to file")
    public String writeFile(@ToolParam(description = "The name of file") String fileName,@ToolParam(description = "Content to write to the file") String content){
         String filePath = fileDir + "/" +fileName;
        try {
            FileUtil.mkdir(fileDir);
            FileUtil.writeUtf8String(content, filePath);
            return "File write successful";
        } catch (Exception e) {
            return "Error writing to file: " + e.getMessage();
        }
    }

}
