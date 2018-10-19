package turnmanager.michil.ru;

import org.apache.commons.cli.*;

public class Main {

    public static void main(String[] args) {

        Options options = new Options();

        Option secretKeyOption = new Option("s", "secretKey", true, "Secret key for access to REST API");
        secretKeyOption.setRequired(true);
        options.addOption(secretKeyOption);

        Option pathOption = new Option("p", "path", true, "reTURN users.txt file location");
        pathOption.setRequired(false);
        options.addOption(pathOption);

        Option restApiPortOption = new Option("port", "port", true, "REST API HTTP Server post");
        restApiPortOption.setRequired(false);
        options.addOption(restApiPortOption);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);

            String secretKey = cmd.getOptionValue("secretKey");
            String dbPath = cmd.getOptionValue("path");
            String serverPortString = cmd.getOptionValue("port");

            if (dbPath != null){
                Constants.DEFAULT_CONFIG_ADDRESS = dbPath;
            }

            if (serverPortString != null) {
                Constants.DEFAULT_REST_API_PORT = Integer.parseInt(serverPortString);
            }

            Constants.SECRET_KEY = secretKey;


            new TurnManager();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            formatter.printHelp("Turn Server Manager", options);

            System.exit(1);
        }




    }
}
