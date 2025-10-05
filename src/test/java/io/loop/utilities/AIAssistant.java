package io.loop.utilities;

public class AIAssistant {

    private static final String SAVE_DIR = "src/test/resources/ai_suggestions/";

    /**
     * explain the error
     */

    public static String explainError(String testName, String stackTrace){
        String prompt = """
            You are a senior SDET. Explain the root cause and top 3 fixes concisely.
            Test: %s
            Stacktrace:
            %s
            Return as:
            - Root cause (1-2 lines)
            - Likely culprit (lib/file/line if obvious)
            - Fixes (3 bullets, specific)
            """.formatted(testName, stackTrace);
        String response = ChatGPTClient.getResponseFromPrompt(prompt);
        return response;
    }
}