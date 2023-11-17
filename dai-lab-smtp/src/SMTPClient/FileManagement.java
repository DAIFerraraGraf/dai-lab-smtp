package SMTPClient;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.io.FileReader;
import java.nio.charset.*;

/**
 * FileManagement class
 */
public class FileManagement {
    /**
     * Path to the file
     */

    /**
     * Constructor
     * @param path Path to the file
     */

    /**
     * Read file and return a list of lines
     * @return list of emails
     */
    public List<String> readFile( String path, boolean isEmailAdress) {

        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
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

        if (!domain.matches("^[a-zA-Z0-9-]+\\.[a-zA-Z0-9-]+$")) {
            return false;
        }

        return true;
    }
}
