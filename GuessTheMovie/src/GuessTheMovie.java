import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class GuessTheMovie {
    public static void main(String[] args) throws Exception {
        File file = new File("movies.txt");
        Scanner scanner = new Scanner(file);
        //Scanner input = new Scanner(System.in);

        // Build list of movies
        ArrayList<String> movieList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            movieList.add(scanner.nextLine());
        }

        // Print list of movies
        for (String s : movieList) {
            System.out.println(s);
        }

        // Select random movie from list
        int movieChallenge = (int) (Math.random() * movieList.size());

        // Print info about randomly selected movie
        System.out.println("Random movie: " + movieList.get(movieChallenge));
        System.out.println(movieList.get(movieChallenge).length());
        for (int i = 0; i<movieList.get(movieChallenge).length(); i++) {
            System.out.print("_ ");
        }
    }
}