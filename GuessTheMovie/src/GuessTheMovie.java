import java.io.File;
import java.util.Scanner;

public class GuessTheMovie {
    public static void main(String[] args) throws Exception {
        File file = new File("movies.txt");
        Scanner scanner = new Scanner(file);
        Scanner scanner1 = new Scanner(file);
        Scanner input = new Scanner(System.in);


        // Get array length
        int movieCount = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            movieCount++;
        }
        System.out.println(movieCount);
        int movie = (int) (Math.random() * movieCount);
        // End get array length



        // Build list of movies
        String[] movies = new String[movieCount];
        movieCount = 0;
        while (scanner1.hasNextLine()) {
            movies[movieCount++] = scanner1.nextLine();
        }
        System.out.println(movies[10]);
        // End build list of movies



        // Okay, let's ask the user to start guessing.
        System.out.print("You are guessing: ");

        //char[] guess = new char[10];
        String guess = "r";
        // Step through movie name as array


        // Printing from array of characters.
        for (int i = 0; i<movies[10].length(); i++) {
            System.out.print(movies[10].charAt(i));
        }

        System.out.println();

        for (int i = 0; i<movies[10].length(); i++) {
            System.out.print("_ ");
        }
    }
}