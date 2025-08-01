package com.shrey.learning.ai.tool;

public class SchemaTool {

    public static String getSchema() {
        return """
            Table employee
            - id INT PK
            - name VARCHAR
            - department_id INT
            Table departments
            - id INT PK
            - name VARCHAR
            employee.department_id -> departments.id
            """;
    }
}