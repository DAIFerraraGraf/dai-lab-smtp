package EmailSender;

public class Main {
    public static void main(String[] args) {

        if (args.length == 0 || args[0].equals("--help")){
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
        }
        // Create an SMTPClient.
        SMTPClient client = new SMTPClient();
        // Read configuration files.
        client.readEmailAdressFiles(args[0]);
        client.readMessagesFiles(args[1]);

        // Send emails.
        client.sendEmails();
    }

}
