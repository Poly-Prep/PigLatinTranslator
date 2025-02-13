import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


//https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
//Just another basic POS to read in the desired file and consolidate crud
public class FileManager {
//TO DO: reading in files; writing files; error handling
    public static String readFile(String fileName) {
        //https://www.w3schools.com/java/java_files_read.asp
        //https://www.geeksforgeeks.org/stringbuilder-class-in-java-with-examples/
        try {
            StringBuilder fileContent = new StringBuilder();
            File file;
            if (fileName.contains(".txt")) {
                file = new File(fileName);
            }else {
                file = new File(fileName+".txt");
            }

            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                fileContent.append(data).append("\n");
            }
            myReader.close();
            return fileContent.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error in reading file");
            e.printStackTrace();
            return "";
        }
    }
    public static boolean writeFile(String fileName, String content) {
        //https://www.baeldung.com/java-write-to-file
        //checking if somebody is fcking with us and is trying to overwrite the dictionary
        if (fileName.equals("words_alpha") || fileName.equals("&*&dictionary&*&") && !content.contains("a{") && !content.contains("b{") && !content.contains("c{")) {
            return false;
        }
        try{
            BufferedWriter myWriter;
            if (fileName.contains(".txt")){
                myWriter = new BufferedWriter(new FileWriter(fileName));
            }else {
                myWriter = new BufferedWriter(new FileWriter(fileName+".txt"));
            }
            myWriter.write(content);
            myWriter.close();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Error in writing file");
            e.printStackTrace();
            return false;
        }
    }



}
