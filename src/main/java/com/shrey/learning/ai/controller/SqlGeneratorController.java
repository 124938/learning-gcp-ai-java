package com.shrey.learning.ai.controller;

import com.shrey.learning.ai.service.SqlGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sql")
@Slf4j
public class SqlGeneratorController {

    private final SqlGeneratorService sqlGeneratorService;

    public SqlGeneratorController(final SqlGeneratorService sqlGeneratorService) {
        this.sqlGeneratorService = sqlGeneratorService;
    }

    @PostMapping("/gen")
    public String generateSql(@RequestBody final String body) throws NoSuchMethodException {
        String result = sqlGeneratorService.generate(body);
        log.info(result);

        return result;
    }
}
