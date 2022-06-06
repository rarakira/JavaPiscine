import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Program {
    private static final String CURRENT_FOLDER = "--current-folder=";

    public static void main(String[] args) {
        Path homeDir = Paths.get("/");
        if (args.length != 0 && args[0].startsWith(CURRENT_FOLDER)){
            homeDir = Paths.get(args[0].substring(CURRENT_FOLDER.length()));
        }

        CommandLine cmdL = new CommandLine(homeDir.normalize());
        Scanner scanner = new Scanner(System.in);
        String userInput;
        System.out.print("-> ");
        while ((userInput = scanner.nextLine()) != null) {
            try {
                String[] command = userInput.split(" ");
                switch (command[0]) {
                    case "ls":
                        cmdL.ls();
                        break;
                    case "cd":
                        cmdL.cd(Paths.get(command[1]));
                        break;
                    case "mv":
                        cmdL.mv(Paths.get(command[1]), Paths.get(command[2]));
                        break;
                    case "pwd":
                        cmdL.pwd();
                        break;
                    case "exit":
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Unknown command. For exit type \"exit\"");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.print("-> ");
        }
    }
}
