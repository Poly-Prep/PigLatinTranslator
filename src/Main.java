import java.io.File;
import java.util.Scanner;

//TODO: APOSTROPHES
//test to piglatin function
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//TO-DO: GUI
public class Main {
    public static void main(String[] args) throws Exception {
        Dictionary dictionary = new Dictionary();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose an input method:");
        System.out.println("1 - Enter an english text to be translated to pig-latin");
        System.out.println("2 - Enter a pig-latin text to be translated to english");
        System.out.println("3 - Enter the file name for an english text to be translated to pig-latin");
        System.out.println("4 - Enter the file name for a pig-latin text to be translated to english");

        int choice;
        String input = "";

        // Get the choice from the user
        System.out.print("Enter your choice (1, 2, 3, or 4): ");
        while (true) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                if (choice >= 1 && choice <= 4) {
                    break;
                }
            } else {
                scanner.next(); // Consume invalid input
            }
            System.out.print("Invalid choice. Please enter 1, 2, 3, or 4: ");
        }

        // Get input based on the choice
        switch (choice) {
            case 1:
                System.out.print("Enter text to be translated to pig-latin: ");
                input = scanner.nextLine();
                break;
            case 2:
                System.out.print("Enter text to be translated to english: ");
                input = scanner.nextLine();
                break;
            case 3:
                File file = new File("");
                while (!file.exists()) {
                    file = new File(input);
                    System.out.print("Enter a valid file name: ");
                    input = scanner.nextLine();
                }
                break;
            case 4:
                File file2 = new File("");
                while (!file2.exists()) {
                    file2 = new File(input);
                    System.out.print("Enter a valid file name: ");
                    input = scanner.nextLine();
                }
                break;
        }


        // Output results
        System.out.println("\nYou chose method: " + choice);
        


        switch (choice) {
            case 1:
                String englishText = new String(input);
                String pigText = SentenceManager.toPigParagraph(SentenceManager.toWordList(input, dictionary));
                System.out.println("Your english text was: " + englishText);
                System.out.println("Your text translated to pig-latin was: " + pigText);

                String input2;

                while (true) { // Keep asking until valid input is received
                    System.out.println("Would you like to save your result? (Y/N)");
                    input2 = scanner.nextLine().trim().toLowerCase();

                    if (input2.equals("yes") || input2.equals("y") || input2.equals("true") || input2.equals("t")) {
                        String filename = getValidFilename(scanner);
                        System.out.println("Valid filename entered: " + filename);
                        FileManager.writeFile(filename + ".txt", pigText);
                        break;
                    } else if (input2.equals("no") || input2.equals("n") || input2.equals("false") || input2.equals("f")) {
                        System.out.println("Goodbye!");
                        break; // Exit the loop on valid input
                    } else {
                        System.out.println("Invalid input. Please enter yes/no or true/false.");
                    }
                }


                break;
            case 2:
                String pigText2 = new String(input);
                String englishText2 = SentenceManager.toEnglishParagraph(SentenceManager.toWordList(input, dictionary), dictionary);
                System.out.println("Your pig-latin text was: " + pigText2);
                System.out.println("Your text translated to english was: " + englishText2);
                String input3;

                while (true) { // Keep asking until valid input is received
                    System.out.println("Would you like to save your result? (Y/N)");
                    input3 = scanner.nextLine().trim().toLowerCase();

                    if (input3.equals("yes") || input3.equals("y") || input3.equals("true") || input3.equals("t")) {
                        String filename = getValidFilename(scanner);
                        System.out.println("Valid filename entered: " + filename);
                        FileManager.writeFile(filename + ".txt", englishText2);
                        break;
                    } else if (input3.equals("no") || input3.equals("n") || input3.equals("false") || input3.equals("f")) {
                        System.out.println("Goodbye!");
                        break; // Exit the loop on valid input
                    } else {
                        System.out.println("Invalid input. Please enter yes/no or true/false.");
                    }
                }

                break;
            case 3:
                File fileBoogy = new File("");
                String filename = getValidFilename(scanner);
                fileBoogy = new File(filename + ".txt");
                while(!fileBoogy.exists()) {
                    filename = getValidFilename(scanner);
                    fileBoogy = new File(filename + ".txt");
                }
                input = FileManager.readFile(filename+".txt");
                englishText = new String(input);
                pigText = SentenceManager.toPigParagraph(SentenceManager.toWordList(input, dictionary));
                System.out.println("Your english text was: " + englishText);
                System.out.println("Your text translated to pig-latin was: " + pigText);


                while (true) { // Keep asking until valid input is received
                    System.out.println("Would you like to save your result? (Y/N)");
                    input = scanner.nextLine().trim().toLowerCase();

                    if (input.equals("yes") || input.equals("y") || input.equals("true") || input.equals("t")) {
                        filename = getValidFilename(scanner);
                        System.out.println("Valid filename entered: " + filename);
                        FileManager.writeFile(filename + ".txt", pigText);
                        break;
                    } else if (input.equals("no") || input.equals("n") || input.equals("false") || input.equals("f")) {
                        System.out.println("Goodbye!");
                        break; // Exit the loop on valid input
                    } else {
                        System.out.println("Invalid input. Please enter yes/no or true/false.");
                    }
                }

                break;
            case 4:
                fileBoogy = new File("");
                filename = getValidFilename(scanner);
                fileBoogy = new File(filename + ".txt");
                while(!fileBoogy.exists()) {
                    filename = getValidFilename(scanner);
                    fileBoogy = new File(filename + ".txt");
                }
                input = FileManager.readFile(filename+".txt");
                pigText = new String(input);
                englishText = SentenceManager.toEnglishParagraph(SentenceManager.toWordList(input, dictionary), dictionary);
                System.out.println("Your english text was: " + englishText);
                System.out.println("Your text translated to pig-latin was: " + pigText);


                while (true) { // Keep asking until valid input is received
                    System.out.println("Would you like to save your result? (Y/N)");
                    input = scanner.nextLine().trim().toLowerCase();

                    if (input.equals("yes") || input.equals("y") || input.equals("true") || input.equals("t")) {
                        filename = getValidFilename(scanner);
                        System.out.println("Valid filename entered: " + filename);
                        FileManager.writeFile(filename + ".txt", englishText);
                        break;
                    } else if (input.equals("no") || input.equals("n") || input.equals("false") || input.equals("f")) {
                        System.out.println("Goodbye!");
                        break; // Exit the loop on valid input
                    } else {
                        System.out.println("Invalid input. Please enter yes/no or true/false.");
                    }
                }
                break;

        }

        scanner.close(); // Close the scanner to prevent resource leaks

    }

    // Function to validate and get a valid filename
    private static String getValidFilename(Scanner scanner) {
        String filename;
        String filenamePattern = "^[a-zA-Z0-9_-]+$"; // Allowed characters: letters, numbers, underscore, hyphen

        while (true) {
            System.out.print("Enter a valid filename (no spaces or special characters; the '.txt' extension will be added automatically): ");
            filename = scanner.nextLine().trim();

            if (filename.isEmpty()) {
                System.out.println("Filename cannot be empty. Try again.");
            } else if (!filename.matches(filenamePattern)) {
                System.out.println("Invalid filename. Use only letters, numbers, underscores, and hyphens.");
            } else {
                return filename; // Valid filename, return it
            }
        }
    }
}