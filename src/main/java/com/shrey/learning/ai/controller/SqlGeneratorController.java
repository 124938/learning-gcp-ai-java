package com.shrey.learning.ai.controller;

import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.Tool;
import com.shrey.learning.ai.tool.SchemaTool;
import org.springframework.web.bind.annotation.*;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

import java.util.List;

@RestController
@RequestMapping("/sql")
public class SqlGeneratorController {

    @PostMapping("/gen")
    public String generateSql(@RequestBody String body) throws NoSuchMethodException {
        // The client gets the API key from the environment variable `GEMINI_API_KEY`.
        Client client = new Client();

        // Create tools
        List<Tool> tools = List.of(
                Tool.builder().functions(
                        List.of(SchemaTool.class.getMethod("getSchema"))
                ).build()
        );

        // Create content configuration
        GenerateContentConfig config = GenerateContentConfig.builder()
                        .tools(tools)
                        .build();

        // Generate content by interacting with Gemini model
        GenerateContentResponse response1 = client.models.generateContent(
                "gemini-2.5-flash",
                body,
                config
        );
        String result1 = response1.text();
        System.out.println(result1);

        return result1;
    }
}
