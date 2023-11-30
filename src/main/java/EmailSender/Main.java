package EmailSender;

public class Main {
    public static void main(String[] args) {

        if(args == null){
            System.out.println(CommandLine.getHelp());
            System.exit(0);
        }else if(args[0].equals("--json")) {
            System.out.println(CommandLine.getJSONStructure());
            System.exit(0);
        }else if (args[0].equals("--txt")){
            System.out.println(CommandLine.getTXTStructure());
            System.exit(0);
        }else if(args[0].equals("--man")){
            System.out.println(CommandLine.getManPage());
            System.exit(0);
        }else if(args.length != 2 || args[0].equals("--help")){
            System.out.println(CommandLine.getHelp());
            System.exit(0);
        }
        // Créer un client SMTP.
        SMTPClient client = new SMTPClient();
        // Lire les fichiers de configuration.
        client.readEmailAdressFiles(args[0]);
        client.readMessagesFiles(args[1]);

        // Envoyer les messages.
        client.sendEmails();
    }

}
