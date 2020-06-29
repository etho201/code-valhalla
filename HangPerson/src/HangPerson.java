import java.lang.reflect.Array;
import java.util.*;

public class HangPerson {
    Scanner file;
    ArrayList<String> wordList = new ArrayList<>();
    char[] randomWord;
    boolean[] lettersGuessed = new boolean[26]; //ascii value of `a` is 97, ascii value of `z` is 122
    int wrongGuesses = 0;
    boolean wrongGuess = true;
    boolean hasWon = false;

    HangPerson(Scanner file) {
        // Build list
        this.file = file;
        while (file.hasNextLine()) {
            this.wordList.add(file.nextLine());
        }
    }

    public void printList() {
        for (int i = 0; i<wordList.size(); i++) {
            System.out.println(wordList.get(i));
        }
    }

    public void resetGame() {
        lettersGuessed = new boolean[26];
        wrongGuesses = 0;
        hasWon = false;
    }

    public void displayGameIntro() {
        System.out.println(
                "Welcome to the hangperson game ...\n" +
                "To play, guess a letter to try to guess the word.\n" +
                "Every time you choose an incorrect letter another\n" +
                "body part appears on the gallows. If you guess the\n" +
                "word before you're hung, you win :-)\n" +
                "If you get hung you lose :-(\n\n" +
                "Time to guess ...\n"
        );
    }

    public void play() {
        //System.out.println("Let's get started!");
        resetGame();
        setRandomWord();
        while (getWrongGuesses() <= 7) {
            displayGallows(getWrongGuesses());
            printLettersGuessed();
            printWrongGuesses();
            //System.out.println("Number of wrong guesses => " + getWrongGuesses());
            mapLetter();
            System.out.println();
            if (hasWon) {
                break;
            }
            if (getWrongGuesses() == 7) {
                System.out.println("Too bad, you lose!");
                System.out.println("The word was => " + getRandomWord()); // need to fix tis
                break;
            }
            chooseLetter();
        }
    }

    public void chooseLetter () {
        System.out.print("\nChoose a letter => ");
        Scanner scanner = new Scanner(System.in);
        char letter = scanner.next().toLowerCase().charAt(0);
        System.out.println();
        checkValidGuess(letter);
    }

    public void checkValidGuess(char guess) {
        if (guess < 'a' || guess > 'z') {
            System.out.println("\nInvalid, don't you know your ABCs?\n");
        } else if (lettersGuessed[guess - 'a']) {
            System.out.println("\nYou already tried this letter\n");
        } else {
            lettersGuessed[guess - 'a'] = true;
            checkWrongGuess(guess);
        }
    }

    public void checkWrongGuess(char guess) {
        for (int i = 0; i<randomWord.length; i++) {
            if (randomWord[i] == guess) {
                wrongGuess = false;
            }
        }
        if (wrongGuess) {
            wrongGuesses+=1;
        }
        wrongGuess = true;
    }

    public void mapLetter() {
        System.out.print("The word so far => ");
        hasWon = true;
        for (int i = 0; i<randomWord.length; i++) {
            if (lettersGuessed[randomWord[i]-'a']) {
                System.out.print(randomWord[i]);
            }
            else {
                System.out.print("-");
                hasWon = false;
            }
        }
        if (hasWon) {
            System.out.println("\n\nCongratulations, you win!!!");
        }
    }

    public void displayGallows(int wrongGuesses) {
        switch (wrongGuesses) {
            case 0:
                System.out.println(
                        "|-----|-\n" +
                        "|\n" +
                        "|\n" +
                        "|\n" +
                        "|\n" +
                        "|__________"
                );
                break;
            case 1:
                System.out.println(
                        "|-----|-\n" +
                        "|     O\n" +
                        "|\n" +
                        "|\n" +
                        "|\n" +
                        "|__________"
                );
                break;
            case 2:
                System.out.println(
                        "|-----|-\n" +
                        "|     O\n" +
                        "|     |\n" +
                        "|\n" +
                        "|\n" +
                        "|__________"
                );
                break;
            case 3:
                System.out.println(
                        "|-----|-\n" +
                        "|     O\n" +
                        "|     |\n" +
                        "|     |\n" +
                        "|\n" +
                        "|__________"
                );
                break;
            case 4:
                System.out.println(
                        "|-----|-\n" +
                        "|     O\n" +
                        "|    \\|\n" +
                        "|     |\n" +
                        "|\n" +
                        "|__________"
                );
                break;
            case 5:
                System.out.println(
                        "|-----|-\n" +
                        "|     O\n" +
                        "|    \\|/\n" +
                        "|     |\n" +
                        "|\n" +
                        "|__________"
                );
                break;
            case 6:
                System.out.println(
                        "|-----|-\n" +
                        "|     O\n" +
                        "|    \\|/\n" +
                        "|     |\n" +
                        "|    /\n" +
                        "|\n" +
                        "|__________"
                );
                break;
            case 7:
                System.out.println(
                        "|-----|-\n" +
                        "|     O\n" +
                        "|    \\|/\n" +
                        "|     |\n" +
                        "|    / \\\n" +
                        "|\n" +
                        "|__________"
                );
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + wrongGuesses);
        }
        System.out.println();
        //System.out.println(getRandomWord());
    }

    public void setRandomWord() {
        int randomWord;
        Random random = new Random();

        randomWord = random.nextInt(wordList.size());
        this.randomWord = wordList.get(randomWord).toLowerCase().toCharArray();
    }

    public String getRandomWord () {
        return Arrays.toString(this.randomWord);
    }

    public void printLettersGuessed () {
        System.out.print("Letters guessed already => ");
        for (int i = 0; i<lettersGuessed.length; i++) {
            if (lettersGuessed[i]) {
                System.out.print((char)(i+'a') + " ");
            }
        }
        System.out.println();
    }

    public int getWrongGuesses () {
        return wrongGuesses;
    }

    public void printWrongGuesses () {
        System.out.println("Number of wrong guesses => " + wrongGuesses);
    }
}