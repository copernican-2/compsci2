package stringmanipulations;
import java.util.Arrays;
import java.util.Scanner;
import static java.lang.System.*;

/**
 * Computer Science II
 * Week 4 Strings Manipulations
 * @author Emmanuel Cruz
 * @Since March 5 2021
 *
 */

public class StringManipulations {
    /**
     * This program takes a sentence and prompts the user
     * to enter a letter and will search each word for that letter
     * Then it will display how manny times that letter appeared in
     * the sentence and it will display each word and how many
     * times the letter appeared in that word.
     */
    public static void main(String[] args) {

        /**
         * Here we're asking for the user to input a sentence
         */
        Scanner input = new Scanner(in);
        out.print("Please Enter a Sentence: ");
        String sentenceEnteredByUser = input.nextLine();
        out.println("NOTE: Only the first character will be accepted if more than one is entered.");
        /**
         * Next the user is asked to enter a letter
         * With a disclaimer that if they enter more than
         * one character the first one will be valid
         * since the input at charAt(0) will only be accepted
         */
        out.print("Please Enter a Letter to Search: ");
        char letter = input.next().charAt(0);

        /**
         * Then were split the words by spaces and places them into an array
         * I also created an array the length of the sentence
         */
        String[] individualWords = sentenceEnteredByUser.split(" ");
        String[] wordsForOutput = new String[individualWords.length];

        /**
         * Here the number words in the sentence is printed
         */
        out.printf("%s%d%n", "Total Number of Words: ", individualWords.length);

        /**
         * I created an enhanced for loop to place the words
         * in the array into a string, then i type case the words all to lower case
         * and search each word to see if the letter the user typed is in the word
         * and keep count if the letter is in each word
         */
        int d = 0;
        int occurrencesOfWord = 0;
        for (String wordsMatch : individualWords) {
            int counter = 0;
            for (int i = 0; i < wordsMatch.length(); i++) {
                if (Character.toLowerCase(wordsMatch.charAt(i)) == letter) {
                    counter++;
                }
            }
            wordsForOutput[d] = wordsMatch + "\t" + counter + "\n";
            d++;
            occurrencesOfWord += counter;
        }
        /**
         * This string builder removes the commas, brackets from the words in the array
         * appends the numbers of the occurrences the letter appears in each word
         * and outputs the results. 
         */
        StringBuilder output = new StringBuilder();
        String s = ("Number of Occurrences of the letter " + letter + " in each word: " + occurrencesOfWord + "\n");
        output.append(s).append("\n").append(Arrays.asList(wordsForOutput).toString().replace("[", "").replace("]", "").replace(", ", "").trim()).append("\n");
        System.out.print(output);
    }
}
