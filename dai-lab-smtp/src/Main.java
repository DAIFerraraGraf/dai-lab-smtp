import SMTPClient.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Créer un client SMTP.
        SMTPClient client = new SMTPClient();

        // Lire les fichiers de configuration.
        client.readEmailAdressFiles("dai-lab-smtp/src/victims.txt");
        client.selectMessages("dai-lab-smtp/src/jokes.txt");

        // Envoyer les messages.
        client.sendEmails();

    }
}