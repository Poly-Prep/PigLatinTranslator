import java.util.ArrayList;
//basic *ss class to keep everything dealing with parsing out and rebuilding sentences in one place
public class SentenceManager {
    public static ArrayList<Word> toWordList(String paragraph) {
        //https://docs.oracle.com/javase/8/docs/api/java/lang/String.html
        //uses split to parse out all the words and then the word class takes care of
        //the rest after each word is passed in
        ArrayList<Word> wordList = new ArrayList<Word>();
        String[] words = paragraph.split(" ");
        for (String word : words) {
            wordList.add(new Word(word));
        }
        return wordList;
    }
    //i aint even gonna comment htese they so basic, just grab the damn string and combine it (for piglatin convert before combining)
    public static String toPigParagraph(ArrayList<Word> wordList) {
        String sentence = "";
        for (Word word : wordList) {
            sentence = sentence + " " + word.toPigLatin();
        }
        return sentence;
    }
    public static String toParagraph(ArrayList<String> wordList) {
        String sentence = "";
        for (String word : wordList) {
            sentence = sentence + " " + word;
        }
        return sentence;
    }
}
