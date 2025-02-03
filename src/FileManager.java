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
            File file = new File(fileName);
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
            BufferedWriter myWriter = new BufferedWriter(new FileWriter(fileName+".txt"));
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
    //one time use function to create a more efficient dictionary search function
    public static void createDictionary(){
        //TO-DO: Finish this thing up, last thing was working on was the thing to create the hashmap that houses the new dictionary, after that'll be formatting the writing of it
        StringBuilder fileContent = new StringBuilder();
        try {

            File file = new File("words_alpha.txt");
            if (file.exists()) {
                System.out.println("File name: " + file.getName());
                System.out.println("Absolute path: " + file.getAbsolutePath());
                System.out.println("Writeable: " + file.canWrite());
                System.out.println("Readable " + file.canRead());
                System.out.println("File size in bytes " + file.length());
            } else {
                System.out.println("The file does not exist.");
            }
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                fileContent.append(data).append(";");
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error in reading initial dictionary file");
            e.printStackTrace();
            return;
        }
        String fileContentCopy = fileContent.toString();
        //https://stackoverflow.com/questions/33163253/for-loop-iteration-through-alphabet-java
        //create an array of strings for each letter of the alphabet and put em in a hashmap
        HashMap<String, ArrayList<String>> dictionary = new HashMap<String, ArrayList<String>>();
        for(char letter = 'a' ; letter <= 'z' ; letter++){
            String key = "" + letter;
            dictionary.put(key,new ArrayList<String>());
        }
        //sort out all of the words by their first letter
        while(!fileContentCopy.equals("")){
            String word = fileContentCopy.substring(0, fileContentCopy.indexOf(";"));
            dictionary.get("" + word.charAt(0)).add(word);
            if(fileContentCopy.contains(";")&&fileContentCopy.indexOf(";") + 1 != fileContentCopy.length()) {
                fileContentCopy = fileContentCopy.substring(fileContentCopy.indexOf(";") + 1);
            } else {
                break;
            }
        }
        StringBuilder finalFileContent = new StringBuilder();
        for(String letter : dictionary.keySet()){
            finalFileContent.append(letter).append("{");
            ArrayList<String> words = dictionary.get(letter);
            for(String word : words){
                finalFileContent.append(word).append(",");
            }
            finalFileContent.deleteCharAt(finalFileContent.length()-1);
            finalFileContent.append("}");
        }
        writeFile("&*&dictionary&*&", finalFileContent.toString());
    }

}
