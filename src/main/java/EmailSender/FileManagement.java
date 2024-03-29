package EmailSender;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;
import java.io.*;

/**
 * FileManagement class is responsible for reading files and validating email addresses.
 */
public class FileManagement {

    /**
     * Reads a file and returns its content as a list of strings.
     * @param path The path to the file. The file must be in JSON format with UTF8 charset.
     * @return A list of strings representing the content of the file.
     */
    public List<List<String>> readFileJSON(String path){
        List<List<String>> messagesList = new ArrayList<>();
        try {
            String content = Files.readString(Paths.get(path));
            JSONObject jsonObject = new JSONObject(content);
            JSONArray messages = jsonObject.getJSONArray("messages");
            for (int i = 0; i < messages.length(); i++) {
                JSONObject message = messages.getJSONObject(i);
                String subject = message.getString("Subject");
                String body = message.getString("Body");
                List<String> messageList = new ArrayList<>();
                messageList.add(subject);
                messageList.add(body);
                messagesList.add(messageList);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading JSON file", e);
        }
        return messagesList;
    }

    /**
     * Reads a file and returns its content as a list of strings.
     * If the file contains email addresses, it validates them before adding to the list.
     * @param path The path to the file.
     * @param isEmailAdress A flag indicating whether the file contains email addresses.
     * @return A list of strings representing the content of the file.
     */
    public List<String> readFileTXT(String path, boolean isEmailAdress) {
        List<String> lines = new ArrayList<>();
        try (var reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (emailValidator(line) || !isEmailAdress){
                    lines.add(line);
                }else {
                    System.out.println("Invalid email: " + line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file", e);
        }
        return lines;
    }

    /**
     * Validates an email address.
     * @param emailAddress The email address to validate.
     * @return A boolean indicating whether the email address is valid.
     */
    public Boolean emailValidator(String emailAddress) {
        // Vérifie que l'adresse e-mail contient un @.
        if (!emailAddress.contains("@")) {
            return false;
        }

        // Vérifie que l'adresse e-mail contient au moins un caractère avant et après le @.
        if (emailAddress.indexOf("@") == 0 || emailAddress.lastIndexOf("@") == emailAddress.length() - 1) {
            return false;
        }

        // Vérifie que le domaine est valide.
        String[] parts = emailAddress.split("@");
        String domain = parts[1];

        return domain.matches("^[a-zA-Z0-9-]+\\.[a-zA-Z0-9-]+$");
    }

}