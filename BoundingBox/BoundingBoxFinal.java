import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.*;

/**
* BoundingBoxFinal.java
* Erik Thomsen
* 
* This program can quickly query objects within a selected area of a dataset.
*/

public class BoundingBoxFinal {
	
	public static void main (String[] args) {
		Scanner stdIn = new Scanner(System.in);
		Location.createIndex();
		
		do {
			Location.searchData();
			System.out.println("Results saved to query_results.txt. Wanrning: Subsequent searches will erase previous results.");
			System.out.print("\nPerform another search (enter \"y\" for yes)? ");
		} while (stdIn.nextLine().equalsIgnoreCase("y"));
		
	}

	private static class Location {
		private final double first;		// x coordinate
		private final double second;	// y coordinate
		private final double third;		// value
		private static List<Location> data = new ArrayList<Location>();

		private Location(double first, double second, double third) {
			this.first = first;
		    this.second = second;
		    this.third = third;
		}
		  
		private static void createIndex() {
			try {
				Scanner src = new Scanner(new BufferedReader(new FileReader("sample_data.csv"))); // File containing comma-delimited coordinates and values 
				
				src.useDelimiter(",|\n"); // Values are separated by commas or new lines.
					
				System.out.print("Loading data into memory... ");
					
				while (src.hasNext()) {
					Location coordinates = new Location(src.nextDouble(), src.nextDouble(), src.nextDouble()); // Creates an object with an x coordinate, y coordinate, and value.
					data.add(coordinates);	// Loads the object into memory (ArrayList).
					}
				
				src.close();
				System.out.print("Done!\n");
			}
			catch (FileNotFoundException e) {
				System.out.println("Error: " + e.getMessage());
				}
			}
		
		private static void searchData() {
			double leftX, rightX, upperY, lowerY;
			  
			Scanner stdIn = new Scanner(System.in);
			System.out.println("\nEnter the coordinates:\n");
			
			try {
				System.out.print("Left X: ");
				leftX = stdIn.nextDouble();

				System.out.print("Right X: ");
				rightX = stdIn.nextDouble();

				System.out.print("Upper Y: ");
				upperY = stdIn.nextDouble();

				System.out.print("Lower Y: ");
				lowerY = stdIn.nextDouble();

				System.out.println();

				try {
					PrintWriter writer = new PrintWriter("query_results.txt", "UTF-8");
					
					writer.print("Searched for:\r\n");
					writer.print("-------------\r\n");
					writer.print("Left X: " + leftX);
					writer.print("\r\nRight X: " + rightX);
					writer.print("\r\nUpper Y: " + upperY);
					writer.print("\r\nLower Y: " + lowerY);
					writer.print("\r\n\r\nFound:\r\n");
					writer.print("------\r\n");
					
					for (int i=0; i<data.size(); i++) {
						if (data.get(i).first >= leftX && data.get(i).first <= rightX)	// Checks if the x coordinate is in between the left and right x boundaries.
							if (data.get(i).second >= upperY && data.get(i).second <= lowerY) {	// Checks if the y coordinate is in between the upper and lower y boundaries.
								writer.print(data.get(i).first + "," + data.get(i).second + "," + data.get(i).third + "\r\n");	// If the x and y coordinates are found within the specified boundaries, add them to the list of results.
								//System.out.println(data.get(i).first + "," + data.get(i).second + "," + data.get(i).third); // This is just visual feedback, but it adds considerable time to run the query.
							}
					}
					writer.close();
				}
				catch (IOException e) {
					System.out.println("Error: " + e);
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Error: Coordinate values can only be numbers.");
			}
		}
	}
}