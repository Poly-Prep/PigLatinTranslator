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
    public Word(String newWord) {
        originalWord = new String(newWord);

        //convert word into a character array.
        //https://www.digitalocean.com/community/tutorials/string-char-array-java
        char[] charArray = newWord.toCharArray();
        containsLetters = false;
        for (int i = 0; i < charArray.length; i++) {
            if (Character.isLetter(charArray[i])) { containsLetters = true; break; }
        }
        if (!containsLetters) {
            punctuationF = null;
            punctuationE = null;
            isCapitalized = false;
            editedWord = newWord;
        }

        //if the first character isn't alphabetic call it front punctuation
        if (!Character.isLetter(charArray[0])) {
            punctuationF = charArray[0] + "";
        } else {
            punctuationF = null;
        }
        //if the first character isn't alphabetic call it end punctuation
        if (!Character.isLetter(charArray[charArray.length - 1])) {
            punctuationE = charArray[charArray.length - 1] + "";
        } else {
            punctuationE = null;
        }
        editedWord = "";
        //build an edited version of the word without punctuation and lowercased
        for (int i = 0; i < charArray.length; i++) {
            //dont include punctuation or anything other than a letter that's at the front or back of a word
            if (punctuationF == null && i == 0) { continue;}
            if (punctuationE == null && i == charArray.length-1) { continue;}
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
        if(!containsLetters) { return new String(originalWord);}
        char[] charArray = editedWord.toCharArray();
        
    }


    public String getPunctuationF() {
        return punctuationF;
    }

    public void setPunctuationF(String punctuationF) {
        this.punctuationF = punctuationF;
    }

    public String getPuntuationE() {
        return puntuationE;
    }

    public void setPuntuationE(String puntuationE) {
        this.puntuationE = puntuationE;
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
