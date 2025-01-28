import java.util.ArrayList;

//reminder to self, don't feed in spaces
//for revision at end: possible bloat in unused instance variables
public class Word {
    //so that we can work with words easier, automatic handling of capitalization and storing of punctuation.
    //punctuation can be "," "!" ";" ":" "." "/" """ "'" etc.
    //punctuation on front (@ # etc.)
    private String punctuationF;
    //punctuation on end " " "," etc.
    private String punctuationE;
    private String originalWord;
    private String editedWord;
    private boolean isCapitalized;
    private boolean containsLetters;
    private ArrayList<Character> vowels = new ArrayList<Character>();

    public Word(String newWord) {
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('y');
        originalWord = new String(newWord);

        //convert word into a character array.
        //https://www.digitalocean.com/community/tutorials/string-char-array-java
        char[] charArray = newWord.toCharArray();
        containsLetters = false;
        for (int i = 0; i < charArray.length; i++) {
            //https://docs.oracle.com/javase/8/docs/api/java/lang/Character.html
            if (Character.isLetter(charArray[i])) {
                containsLetters = true;
                break;
            }
        }
        if (!containsLetters) {
            punctuationF = "";
            punctuationE = "";
            isCapitalized = false;
            editedWord = newWord;
        }

        //if the first character isn't alphabetic call it front punctuation
        if (!Character.isLetter(charArray[0])) {
            punctuationF = "";
            //loop through all of end until we hit a letter to accommodate "---" etc.
            for (int i = 0; i < charArray.length; i++) {
                if (Character.isLetter(charArray[i])) {
                    break;
                }
                punctuationF = punctuationF + charArray[i];
            }
        } else {
            punctuationF = "";
        }
        //if the first character isn't alphabetic call it end punctuation
        if (!Character.isLetter(charArray[charArray.length - 1])) {
            punctuationE = "";
            //loop through all of end until we hit a letter to accommodate "..." etc.
            for (int i = charArray.length - 1; i > 0; i--) {
                if (Character.isLetter(charArray[i])) {
                    break;
                }
                punctuationE = charArray[i] + punctuationE;
            }

        } else {
            punctuationE = "";
        }
        editedWord = "";
        //build an edited version of the word without punctuation and lowercased
        for (int i = 0; i < charArray.length; i++) {
            //dont include punctuation or anything other than a letter that's at the front or back of a word
            if (punctuationF != "" && i < punctuationF.length()) {
                continue;
            }
            if (punctuationE != "" && i > charArray.length - 1 - punctuationE.length()) {
                continue;
            }
            editedWord = editedWord + charArray[i];

        }
        //fill out is capitalized field before turning all to lowercase
        if (Character.isUpperCase(editedWord.charAt(0))) {
            isCapitalized = true;
        } else {
            isCapitalized = false;
        }
        //all to lowercase
        editedWord = editedWord.toLowerCase();
    }


    public String toPigLatin() {
        //!!!!!currently can't handlee 1 letter words!!!!!! 1/28/25

        if (!containsLetters) {
            return new String(originalWord);
        }

        char[] charArray = editedWord.toCharArray();

        if (vowels.contains(charArray[0])) {
            System.out.println(3);
            //rule3
            String pigLatinWord = new String(charArray);
            if (isCapitalized) {
                pigLatinWord = pigLatinWord.substring(0, 1).toUpperCase() + pigLatinWord.substring(1);
            }
            pigLatinWord = punctuationF + pigLatinWord + "way" + punctuationE;
            return pigLatinWord;
        } else if (vowels.contains(charArray[1]) && Character.isLetter(charArray[0])) {
            System.out.println(1);
            //rule 1
            String pigLatinWord = "";
            //assemble word starting at second letter
            for (int i = 1; i < charArray.length; i++) {
                pigLatinWord = pigLatinWord + charArray[i];
            }
            //assemble word with ending and punctuation.
            pigLatinWord = punctuationF + pigLatinWord + charArray[0] + "ay" + punctuationE;
            return pigLatinWord;
            //redundant but worth checking for debugging and error handling reasons
        } else if (Character.isLetter(charArray[0]) && Character.isLetter(charArray[1])) {
            //rule 2
            System.out.println(2);
            String pigLatinWord = "";
            for (int i = 2; i < charArray.length; i++) {
                pigLatinWord = pigLatinWord + charArray[i];
            }
            pigLatinWord = punctuationF + pigLatinWord + charArray[0] + charArray[1] + punctuationE;
            return pigLatinWord;
        } else {
            //failure handling
            //currently may fail if a word is like x-xxxxx
            System.out.println("Failure to translate: " + editedWord + " | " + originalWord);
            return originalWord;
        }
    }


    public String getPunctuationF() {
        return punctuationF;
    }

    public void setPunctuationF(String punctuationF) {
        this.punctuationF = punctuationF;
    }

    public String getPunctuationE() {
        return punctuationE;
    }

    public void setPunctuationE(String punctuationE) {
        this.punctuationE = punctuationE;
    }

    public String getOriginalWord() {
        return originalWord;
    }

    public void setOriginalWord(String originalWord) {
        this.originalWord = originalWord;
    }

    public String getEditedWord() {
        return editedWord;
    }

    public void setEditedWord(String editedWord) {
        this.editedWord = editedWord;
    }

    public boolean isCapitalized() {
        return isCapitalized;
    }

    public void setCapitalized(boolean capitalized) {
        isCapitalized = capitalized;
    }
}
