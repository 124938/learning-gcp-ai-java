package com.shrey.learning.ai.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Tool;
import com.shrey.learning.ai.tool.SchemaTool;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SqlGeneratorService {

    private static final String MODEL = "gemini-2.5-flash";

    private final Client client;
    private final GenerateContentConfig contentConfig;

    public SqlGeneratorService() throws NoSuchMethodException {
        // The client gets the API key from the environment variable `GEMINI_API_KEY`.
        this.client = new Client();

        // Create tools
        List<Tool> tools = List.of(
                Tool.builder().functions(
                        List.of(
                                SchemaTool.class.getMethod("getSchema")
                        )
                ).build()
        );

        // Create content configuration
        this.contentConfig = GenerateContentConfig.builder()
                .tools(tools)
                .build();

    }

    public String generate(final String prompt) {
        // Generate content by interacting with Gemini model
        GenerateContentResponse response = client.models.generateContent(
                MODEL,
                prompt,
                contentConfig
        );

        return response.text();
    }
}
