package Command;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CmdApplication {
    public static void cmdList() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        // Initialize command list
        ArrayList<String> commands = new ArrayList<>();
        commands.add("1 : View all suits");
        commands.add("2 : Buw a suits");
        commands.add("3 : Return a suits");
        commands.add("4 : Add a new user");
        commands.add("5 : Export a file with the available suits");
        commands.add("0 : Exit the program");

        // Display the list of commands
        System.out.println("Command list:");
        for (String command : commands) {
            System.out.println(command);
        }

        // Ask the user to enter a command
        System.out.println("Enter command number: ");
        int choice = scanner.nextInt();

        // COMMAND SWITCH

        switch (choice) {
            case 1:
                System.out.println("Displaying all courses in the system...");
                Cmd1.command1();
                break;
            case 2:
                System.out.println("Booking an existing course...");
                Cmd2.command2();
                break;
            case 3:
                System.out.println("Canceling a course reservation...");
                Cmd3.command3();
                break;
            case 4:
                System.out.println("Adding a new user...");
                Cmd4.command4();
                break;
            case 5:
                System.out.println("Exporting a file with available courses...");
                Cmd5.command5();
                break;
            case 0:
                System.out.println("Closing the program. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid command. Please enter a valid command number.");
        }
    }
}
