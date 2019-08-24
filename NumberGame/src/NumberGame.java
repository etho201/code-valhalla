import java.util.Scanner;

public class NumberGame {

    public static void main(String[] args){
        int randomNumber = (int) (Math.random() * 100) + 1;
        boolean hasWon = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("I'm thinking of a number between 1 and 100.");
        System.out.println("I'll give you 10 chances to guess the number to win.");
        System.out.println("So let's give it a shot, pick a number between 1 and 100.");

        for (int i=9; i>=0; i--){
            int guess = scanner.nextInt();
            if (randomNumber > guess){
                System.out.println("That's not right. The number is greater than " + guess);
            }
            else if (randomNumber < guess){
                System.out.println("That's not right. The number is less than " + guess);
            }
            else{
                hasWon = true;
                break;
            }
            System.out.println("You have " + i + " guesses left. Try again!");
        }
        if (hasWon){
            System.out.println("That's correct! You win!!!");
        }
        else{
            System.out.println("Sorry! Game over!");
            System.out.println("The number was " + randomNumber);
        }
    }
}
