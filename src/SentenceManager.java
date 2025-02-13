import java.util.ArrayList;

//basic *ss class to keep everything dealing with parsing out and rebuilding sentences in one place
public class SentenceManager {
    public static ArrayList<Word> toWordList(String paragraph, Dictionary dictionary) {
        //https://docs.oracle.com/javase/8/docs/api/java/lang/String.html
        //uses split to parse out all the words and then the word class takes care of
        //the rest after each word is passed in
        ArrayList<Word> wordList = new ArrayList<Word>();
        String[] words = paragraph.split(" ");
        for (String word : words) {
            wordList.add(new Word(word, dictionary));
        }
        return wordList;
    }

    //new line variant
    public static ArrayList<String> toWordListNL(String paragraph) {
        //https://docs.oracle.com/javase/8/docs/api/java/lang/String.html
        //uses split to parse out all the words
        ArrayList<String> wordList = new ArrayList<String>();
        String[] words = paragraph.split("\n");
        for (String word : words) {
            wordList.add(word);
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

    public static String toEnglishParagraph(ArrayList<Word> wordList, Dictionary dictionary) {
        String sentence = "";
        for (Word word : wordList) {
            String[] validWords = word.toEnglish();
            if (validWords.length > 0) {
                if (validWords.length == 1) {
                    sentence = sentence + " " + validWords[0];
                } else {
                    ArrayList<String> commonWords = new ArrayList<>();
                    for (String validWord : validWords) {
                        if (dictionary.isCommon(validWord)) {
                            commonWords.add(validWord);
                        }
                    }
                    if (commonWords.size() == 0) {
                        sentence = sentence + " [";
                        boolean first = true;
                        for (String validWord : validWords) {
                            if (first) {
                                sentence = sentence + validWord;
                                first = false;
                                continue;
                            }
                            sentence = sentence + ", " + validWord;
                        }
                        sentence = sentence + "]";
                    } else if (commonWords.size() == 1) {
                        sentence = sentence + " " + commonWords.get(0);
                    } else if (commonWords.size() > 1) {
                        sentence = sentence + " [";
                        boolean first = true;
                        for (String commonWord : commonWords) {
                            if (first) {
                                sentence = sentence + commonWord;
                                first = false;
                                continue;
                            }
                            sentence = sentence + ", " + commonWord;
                        }
                        sentence = sentence + "]";
                    }
                }
            }
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
