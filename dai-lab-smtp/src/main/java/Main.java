import ch.heig.JokesEmail.SMTPClient;
public class Main {
    public static void main(String[] args) {
        // Créer un client SMTP.
        SMTPClient client = new SMTPClient();
        // Lire les fichiers de configuration.
        client.readEmailAdressFiles(args[1]);
        client.selectMessages(args[2]);

        // Envoyer les messages.
        client.sendEmails();
    }

}
