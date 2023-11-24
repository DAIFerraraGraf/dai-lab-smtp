package Jokes;

public class Main {
    public static void main(String[] args) {
        if(args[0].equals("--json")){
            System.out.println(getJSONStructure());
            System.exit(0);
        }else if(args[0].equals("--help") || args.length != 2){
            System.out.println(getHelp());
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
    public static String getHelp() {
        String ANSI_Green = "\u001B[32m";
        String ANSI_RESET = "\u001B[0m";
        return ANSI_Green + "Usage: java -jar <SpammerEmail.jar> <email_addresses_file.txt> <messages_file.json>\n" +
                          "Use --json to display the JSON structure"+ ANSI_RESET;
    }
    public static String getJSONStructure() {
        String ANSI_Green = "\u001B[32m";
        String ANSI_Magenta = "\u001B[35m";
        String ANSI_RESET = "\u001B[0m";
        return "{\n" +
                ANSI_Magenta  +"  \"messages\" "+  ANSI_RESET +": [\n" +
                "    {\n" +
                ANSI_Magenta  +"      \"Subject\" " + ANSI_RESET +":" + ANSI_Green +" \"<subject>\""+ ANSI_RESET +",\n" +
                ANSI_Magenta  +"      \"Body\"" + ANSI_RESET + ":" + ANSI_Green +" \"<body>\"\n" + ANSI_RESET +
                "    },\n" +
                "    {\n" +
                ANSI_Magenta  +"      \"Subject\" " + ANSI_RESET +":" + ANSI_Green +" \"<subject>\""+ ANSI_RESET +",\n" +
                ANSI_Magenta  +"      \"Body\"" + ANSI_RESET + ":" + ANSI_Green +" \"<body>\"\n" + ANSI_RESET +
                "    }\n" +
                "    ...\n" +
                "  ]\n" +
                "}";
    }

}
