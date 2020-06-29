/**
 * This program is a word guessing game called hangperson.
 * A person will try and guess a word before the max
 * number of guesses are used. Every time the user chooses an
 * incorrect letter another body part is displayed in the gallows.
 *
 */

import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HangPersonGames {

    public static void main(String[] args) {
        Scanner wordsFile = null;                  // words data file

        // open the file containing the words
        try {
            wordsFile = new Scanner(new FileInputStream("data7.txt"));
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found or not opened.");
            System.exit(0);
        }

        HangPerson hangGame = new HangPerson(wordsFile);
        Scanner keyboard = new Scanner(System.in);

        // display an introduction on the game to the player
        hangGame.displayGameIntro();

        // continually play new games if the user desires
        String playAgain;
        do {
            hangGame.play();
            System.out.print("Do you want to play again? ");
            playAgain = keyboard.next();
            System.out.println();
        } while (playAgain.toUpperCase().startsWith("Y"));

        System.out.println();
        System.out.println("Thanks for playing!");
        System.out.println();

        // TESTING
        hangGame.printList();
    }
}
