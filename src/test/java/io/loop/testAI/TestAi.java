package io.loop.testAI;

import io.loop.utilities.ChatGPTClient;
import org.apache.poi.ss.formula.functions.T;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestAi {

    public static void main(String[] args) {
        String prompt = "Generate me scenarios for www.google.com in Gherkin language, make sure to add negative ones as well"; // you can read from smth else as well
        String aiResponse = ChatGPTClient.getResponseFromPrompt(prompt);

        System.out.println("\n====================AI Generated Suggestions ====================AI Generated Suggestions\n" );
        System.out.println(aiResponse);
        System.out.println("\n====================AI Generated Suggestions ====================AI Generated Suggestions\n" );

        // folder path
        String folderPath = "src\\test\\resources\\ai_suggestions\\";

        //  dynamic naming
        LocalDateTime now = LocalDateTime.now();
        String timeStamp = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        String fileName = "ai_suggestions_" + timeStamp + ".txt";

        try{
            //Create folder if it does not exist
            Files.createDirectories(Paths.get(folderPath));

            // write ai output to the file
            PrintWriter out = new PrintWriter(folderPath + fileName);
            out.println(aiResponse);
            out.close();

            System.out.println("Output saved to: " + folderPath + fileName);

        } catch (IOException e) {
            System.out.println("Fail to save output to file");
            e.printStackTrace();
        }
    }
}
