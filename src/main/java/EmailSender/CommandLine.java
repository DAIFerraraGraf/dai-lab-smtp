package EmailSender;

/**
 * The CommandLine class provides methods for displaying help and structure information to the user.
 */
public class CommandLine {

    /**
     * Returns a string containing usage instructions for the program.
     * @return A string containing usage instructions for the program.
     */
    public static String getHelp() {
        String ANSI_Green = "\u001B[32m";
        String ANSI_RESET = "\u001B[0m";
        return ANSI_Green + "Usage: java -jar <EmailSender-1.0-jar-with-dependencies.jar> <email_addresses_file.txt> <messages_file.json>\n" +
                "Use --json to display the JSON structure\n"+
                "Use --txt to display the txt structure" + ANSI_RESET;
    }

    /**
     * Returns a string containing the JSON structure for the messages file.
     * @return A string containing the JSON structure for the messages file.
     */
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
                "    {\n" +
                ANSI_Magenta  +"      \"Subject\" " + ANSI_RESET +":" + ANSI_Green +" \"<subject>\""+ ANSI_RESET +",\n" +
                ANSI_Magenta  +"      \"Body\"" + ANSI_RESET + ":" + ANSI_Green +" \"<body>\"\n" + ANSI_RESET +
                "    }\n" +
                "  ]\n" +
                "}\n";
    }

    /**
     * Returns a string containing the TXT structure for the email addresses file.
     * @return A string containing the TXT structure for the email addresses file.
     */
    public static String getTXTStructure() {

        return "The TXT file containing the email addresses should be formatted such that each line contains a single email address.\n\n" +
                "remy.thomas@example.com\n" +
                "maxime.robin@example.com\n" +
                "romain.gauthier@example.com\n" +
                "nicolas.thomas@example.com\n" +
                "adrien.robin@example.com\n";

    }

    /**
     * Returns a string containing a brief manual page for the program.
     * @return A string containing a brief manual page for the program.
     */
    public static String getManPage() {
        return "Description\n" +
                "The program is a Java application that reads email addresses and messages from files, forms email groups, and sends a random message to each group.\n\n\n" +
                "1. Prepare two input files. The first file should contain email addresses, one per line. The second file should contain messages in JSON format. Each message should have a subject and a body.  \n\n" +
                "2. Open a terminal or command prompt.  \n\n" +
                "3. Navigate to the directory where the JAR file is located.  \n\n" +
                "4. Run the program using the following command: \n\n" +
                "5. java -jar EmailSender-1.0-jar-with-dependencies.jar email_addresses_file.txt messages_file.json\n\n" +
                "6. Replace SpammerEmail.jar, email_addresses_file.txt, and messages_file.json with the names of your JAR file and your input files, respectively.  \n\n" +
                "7. If you want to display the JSON structure of the messages, add the --json option at the end of the command\n\n" +
                "8. If you want to display the TXT structure of the email adresse, add the --txt option at the end of the command\n\n";
    }
}