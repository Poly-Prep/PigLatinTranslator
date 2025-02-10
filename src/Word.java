import java.util.ArrayList;
//TO-DO: COMPLETE TOENGLISH FUNCTION
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
    Dictionary dictionary;
    public Word(String newWord, Dictionary dictionary) {
        this.dictionary = dictionary;
        System.out.println(newWord);
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
            return;
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

    //CAN handle " --- " (entirely symbol 'words')
    public String toPigLatin() {
        //okay so like if it doesn't contain numbers (i.e. 40%) then just return the orginial
        if (!containsLetters) {
            return new String(originalWord);
        }
        //make that baby a character array
        char[] charArray = editedWord.toCharArray();
        //deal with the weird case that the length is 0, which no idea how that would ever happen but screw it!
        if (charArray.length == 0) return originalWord;
        //deal with single letter words, only possible cases are vowel or failure so only have to deal with those
        if(charArray.length == 1) {
            if(vowels.contains(charArray[0])) {
                String piglatinword = new String(charArray);
                if(isCapitalized) piglatinword = piglatinword.toUpperCase();
                piglatinword = punctuationF + piglatinword + "way" + punctuationE;
                return piglatinword;
            } else {
                String piglatinword = new String(charArray);
                if(isCapitalized) piglatinword = piglatinword.toUpperCase();
                piglatinword = punctuationF + piglatinword + punctuationE;
                return piglatinword;
            }
        }
        //is the first LETTER a vowel? check by referencing against list of vowels
        if (vowels.contains(charArray[0])) {
            //rule3
            //create new string out of our character array
            String pigLatinWord = new String(charArray);
            if (isCapitalized) {
                //if it's capitalized then capitalize the first letter
                pigLatinWord = pigLatinWord.substring(0, 1).toUpperCase() + pigLatinWord.substring(1);
            }
            //add punctuation and pig latin modification
            pigLatinWord = punctuationF + pigLatinWord + "way" + punctuationE;
            return pigLatinWord;
        } else if (vowels.contains(charArray[1]) && Character.isLetter(charArray[0])) {
            //rule 1
            //create new string
            String pigLatinWord = "";
            //assemble word starting at second letter
            for (int i = 1; i < charArray.length; i++) {
                pigLatinWord = pigLatinWord + charArray[i];
            }
            if (isCapitalized) {
                //if it's capitalized then capitalize the first letter
                pigLatinWord = pigLatinWord.substring(0, 1).toUpperCase() + pigLatinWord.substring(1);
            }
            //assemble word with ending and punctuation.
            pigLatinWord = punctuationF + pigLatinWord + charArray[0] + "ay" + punctuationE;
            return pigLatinWord;
            //redundant but worth checking for debugging and error handling reasons
        } else if (Character.isLetter(charArray[0]) && Character.isLetter(charArray[1])) {
            //rule 2
            //new string
            String pigLatinWord = "";
            //build word starting at 3rd letter
            for (int i = 2; i < charArray.length; i++) {
                pigLatinWord = pigLatinWord + charArray[i];
            }
            if (isCapitalized) {
                //if it's capitalized then capitalize the first letter
                pigLatinWord = pigLatinWord.substring(0, 1).toUpperCase() + pigLatinWord.substring(1);
            }
            //blah blah pig latin rules blah blah just put the crap back together the right way
            pigLatinWord = punctuationF + pigLatinWord + charArray[0] + charArray[1] + punctuationE;
            return pigLatinWord;
        } else if (!vowels.contains(charArray[0]) && Character.isLetter(charArray[0]) && charArray.length == 1) {
            //'Y' and each individual letter is technically a word, and isn't handled by the piglatin rules, so this is the exception for it
            return originalWord;
        } else {
            //failure handling
            System.out.println("Failure to translate: " + editedWord + " | " + originalWord);
            return originalWord;
        }
    }
    //In Testing 2/10/25
    public String[] toEnglish() {
        //okay so like if it doesn't contain letters (i.e. 40%) then just return the orginial
        if (!containsLetters) {
            return new String[]{originalWord};
        }
        //make that baby a character array
        char[] charArray = editedWord.toCharArray();
        //deal with the weird case that the length is 0, which no idea how that would ever happen but screw it! Also deals with the weird fact that 'X' and all the letters are technically words and might show up
        if (charArray.length == 0 || charArray.length == 1) return new String[]{originalWord};
        //create an arraylist to hold any possible words
        ArrayList<String> possibleWords = new ArrayList<String>();
        //checking if word ends in "way" and if that "way" is at the end of the word. Shortest possible occurance of this would be if we started with the word "a", which wouldn't conflict with the methodology ghere
        if (editedWord.contains("way") && vowels.contains(charArray[0]) && editedWord.lastIndexOf("way") == editedWord.length() - 1 - 2) {
            possibleWords.add(editedWord.substring(0,editedWord.lastIndexOf("way")));
        }
        //okay so my thought is, both of the other rules add ay to the end, so we're safe to just remove "ay" if we see it, throw an error if we dont
        if (editedWord.contains("ay") && editedWord.lastIndexOf("ay") == editedWord.length() - 1 - 1) {
            String choppedWord = editedWord.substring(0, editedWord.lastIndexOf("ay"));
            //rule 1. if it starts with a vowel, do this crap
            if (vowels.contains(charArray[0])) {
                //using a string builder for fun, move letter at the end to the front then add to list of possible words
                StringBuilder possible1 = new StringBuilder();
                possible1.append(choppedWord.charAt(choppedWord.length() - 1));
                possible1.append(choppedWord.substring(0, choppedWord.length() - 1));
                possibleWords.add(possible1.toString());
            }
            //rule 2
            StringBuilder possible2 = new StringBuilder();
            //this rule can get messed up if we got a word like "Ty" so we gotta have an exception
            if(choppedWord.length() == 2) possibleWords.add(choppedWord);
            else{
                //chop off back 2 and add to front then add to list of possible words
                possible2.append(choppedWord.substring(choppedWord.length() - 2));
                possible2.append(choppedWord.substring(0, choppedWord.length() - 2));
                possibleWords.add(possible2.toString());
            }
        } else {
            System.out.println("Failure to translate: " + editedWord + " | " + originalWord);
            System.out.println("Possible words: " + possibleWords);
            System.out.println("ERROR IN RULES 1 AND 2, FAILURE TO FIND 'AY'");
        }
        //create list for holding valid words, run through words and if they valid put em in the list and add their punctuations. if no words are valid.... somethings wrong, return the original word
        ArrayList<String> validWords = new ArrayList<>();
        for (String word : possibleWords) {
            if (dictionary.findWord(word)) {
                validWords.add(punctuationF + word + punctuationE);
            }
        }
        if (validWords.size() == 0) {
            System.out.println("Failure to translate: " + editedWord + " | " + originalWord);
            System.out.println("Possible words: " + possibleWords);
            System.out.println("No Valid Words Found");
            return new String[]{originalWord};
        }
        return (String[]) validWords.toArray();

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
