package Jokes;

public class Main {
    public static void main(String[] args) {
        // Créer un client SMTP.
        SMTPClient client = new SMTPClient();
        // Lire les fichiers de configuration.
        client.readEmailAdressFiles(args[0]);
        client.readMessagesFiles(args[1]);

        // Envoyer les messages.
        client.sendEmails();
    }

}
