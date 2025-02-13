import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Dictionary {
    private HashMap<String, ArrayList<String>> dictionary;
    private ArrayList<String> commonWordList;
    public Dictionary() {
        //vital to functioning of program, must exit if failed
        try {
            File commonWords = new File("1000-most-common-words.txt");
            if (!commonWords.exists()) {
                System.out.println("No  common words file found");
                System.exit(0);
            }
            String commonWordsContent = FileManager.readFile("1000-most-common-words.txt");

            commonWordList = SentenceManager.toWordListNL(commonWordsContent);
            File McommonWords = new File("5000-more-common.txt");
            if (!McommonWords.exists()) {
                System.out.println("No more common words file found");
                System.exit(0);
            }
            String McommonWordsContent = FileManager.readFile("5000-more-common.txt");

            commonWordList.addAll(SentenceManager.toWordListNL(McommonWordsContent));
            //copious checking to make sure we know exactly what's happening if there's an error and to create a dictionary if none found
            File dicFile = new File("&*&dictionary&*&.txt");
            if (!dicFile.exists()) {
                createDictionary();
            }
            String rawDictionary = FileManager.readFile("&*&dictionary&*&.txt");
            if(rawDictionary == null || rawDictionary.isEmpty() || !rawDictionary.contains("a{") || !rawDictionary.contains("b{") || !rawDictionary.contains("c{")) {
                System.out.println("Dictionary read failure: !#@!#@");
                System.exit(0);
            }
            //Create dictionary object, can't use primitive in it so using strings instead of chars for hte key
            dictionary = new HashMap<String, ArrayList<String>>();
            //screw aliasing
            String editedDictionary = new String(rawDictionary);
            //going through each letter in the dictionary and parsing out the list of words as I've stored them
            for(char letter = 'a' ; letter <= 'z' ; letter++){
                //UP stands for unparsed, so unparsed words. Taking out the relevant section of words
                String UPWords = editedDictionary.substring(editedDictionary.indexOf('{')+1,editedDictionary.indexOf('}'));
                ArrayList<String> words = new ArrayList<String>();
                //go through until no words left
                while(!UPWords.isEmpty()){
                    if (UPWords.contains(",")) {
                        words.add(UPWords.substring(0, UPWords.indexOf(',')));
                        UPWords = UPWords.substring(UPWords.indexOf(',') + 1);
                    } else {
                        words.add(UPWords);
                        UPWords = "";
                    }
                }
                //adding the parsed word list
                dictionary.put(""+letter, words);
                //removed parsed portion
                if (letter != 'z') editedDictionary = editedDictionary.substring(editedDictionary.indexOf('}')+1);
            }

        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Dictionary object creation failure:&*($!@&");
            System.exit(0);
        }
    }
    public boolean isCommon(String word){
        return commonWordList.contains(word);
    }
    public boolean findWord(String word) {
        String letter = word.charAt(0) + "";
        ArrayList<String> words = dictionary.get(letter);
        if(words.contains(word)) {
            return true;
        } else { return false; }
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
        while(!fileContentCopy.isEmpty()){
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
        FileManager.writeFile("&*&dictionary&*&", finalFileContent.toString());
    }
}
