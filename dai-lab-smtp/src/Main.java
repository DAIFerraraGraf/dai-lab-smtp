import SMTPClient.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileManagement file = new FileManagement("src/victims.txt");
        List<String> emails = file.readFile();
        for (String email : emails) {
            System.out.println(email);
        }

    }
}