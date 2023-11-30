package EmailSender;

public class CommandLine {
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
        return "\n{\n" +
                ANSI_Magenta  +"  \"messages\" "+  ANSI_RESET +": [\n" +
                "    {\n" +
                ANSI_Magenta  +"      \"Subject\" " + ANSI_RESET +":" + ANSI_Green +" \"<subject>\""+ ANSI_RESET +",\n" +
                ANSI_Magenta  +"      \"Body\"" + ANSI_RESET + ":" + ANSI_Green +" \"<body>\"\n" + ANSI_RESET +
                "    },\n" +
                "    {\n" +
                ANSI_Magenta  +"      \"Subject\" " + ANSI_RESET +":" + ANSI_Green +" \"<subject>\""+ ANSI_RESET +",\n" +
                ANSI_Magenta  +"      \"Body\"" + ANSI_RESET + ":" + ANSI_Green +" \"<body>\"\n" + ANSI_RESET +
                "    },\n" +
                "    ...\n" +
                "    {\n" +
                ANSI_Magenta  +"      \"Subject\" " + ANSI_RESET +":" + ANSI_Green +" \"<subject>\""+ ANSI_RESET +",\n" +
                ANSI_Magenta  +"      \"Body\"" + ANSI_RESET + ":" + ANSI_Green +" \"<body>\"\n" + ANSI_RESET +
                "    }\n" +
                "  ]\n" +
                "}\n";
    }
}
