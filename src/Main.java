//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        String unedited = "Roy DeCarava, born on December 9, 1919, in Harlem, New York, stands as a luminary in the world of photography, renowned for reshaping the visual narrative of African American life. Growing up in the vibrant cultural crucible of the Harlem Renaissance, DeCarava was immersed in the artistic and intellectual energy of his community from an early age. Though he began as a painter, his artistic vision led him to photography, a medium through which he could explore the nuances of African American life with unparalleled emotional depth and authenticity.\n";
        System.out.println(unedited);
        System.out.println(SentenceManager.toPigParagraph(SentenceManager.toWordList(unedited)));
    }
}