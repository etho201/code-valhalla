import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import static org.imgscalr.Scalr.*;
import javax.imageio.ImageIO;

/**
* Traversal.java
* Erik Thomsen
* 
* This program traverses a user-specified directory and allows the user
* to perform various actions on the files within the directory. 
*/

public class Traversal {
	
	public static void main(String[] args) throws IOException {
		String path = null;
		String response = "";
		File currentFile;
		
		Scanner stdIn = new Scanner(System.in);
		do {
			do {
				if (path == null || response.equalsIgnoreCase("c")) {	// gets directory for the first time
					// Keep trying until a valid path is entered.
					while (1+1!=3) {
						try {
							System.out.print("Please input a directory to scan: ");
							path = stdIn.nextLine();
							DirIndex.CreateIndex(path);
							break; // Exit the loop.
						}
						catch (NullPointerException e) {
							System.out.println("Error: Directory not found.");
						}
					}
				}
				else {	// checks if user wants to keep working in the same directory
					System.out.print("Continue working in: \"" + path + "\" (enter \"y\" for yes)? ");
					if (!stdIn.nextLine().toString().equalsIgnoreCase("y")) {
						// Keep trying until a valid path is entered.
						while (1+1!=3) {
							try {
								System.out.print("\nPlease input a directory to scan: ");
								path = stdIn.nextLine();
								DirIndex.CreateIndex(path);
								break; // Exit the loop.
							}
							catch (NullPointerException e) {
								System.out.println("Error: Directory not found.");
							}
						}
					}
					else // must be here to re-index files in case the user chooses to delete some files
						DirIndex.CreateIndex(path);
				}
				
				DirIndex.LastModified(path);
				
				System.out.println("\nCurrent path: \"" + path + "\"\n");
				
				System.out.println("Please select an action to perform:");
				System.out.println("-----------------------------------");
				System.out.println("A. Delete Files");
				System.out.println("B. Create Thumbnails");
				System.out.println("C. Change path");
				//System.out.println("New Action");
				System.out.println("D. Exit program");
				
				System.out.print("\nEnter choice by entering one of the letters above (ie: a, b, c, or d): ");
				response = stdIn.nextLine();
				if (response.equalsIgnoreCase("a")) {
					DirIndex.DeleteFiles(path);
				}
				else if (response.equalsIgnoreCase("b")) {	// create thumbnail images
					System.out.println("\nCreating thumbnail images...");
					for (int i = 0; i<DirIndex.ListOfFiles.size(); i++) {
						currentFile = new File(path + "\\" + DirIndex.ListOfFiles.get(i).filename);
						if (currentFile.getName().contains(".jpg")) {
							process(currentFile);
						}
					}
					System.out.println("Thumbnail images saved in: " + "\"" + path + "\\thumbnail\\\"" + "\n");
				}
				//	Modify and uncomment the next 3 lines to integrate a new action.
				//	else if (response.equalsIgnoreCase("new action")) {
				//		newAction();
				//	}
			} while (response.equalsIgnoreCase("c")); // allows the user to specify a new path
		} while (!response.equalsIgnoreCase("d")); // exits the program
		
		System.out.println("\nGood bye!");
	} // end main
	
	// For creating thumbnails.
	private static void process(File file) throws IOException {
		// Load image.
		BufferedImage image = ImageIO.read(file);

		// Resize image.
		image = resize(image, 200);

		// Save the resized image as the thumbnail.
		saveThumbnail(file, image);
	}
	
	// For saving the thumbnails.
	private static void saveThumbnail(File originalFile, BufferedImage thumbnail) throws IOException {
		String filename = originalFile.getName();

		// Determine file extension.
		String fileExt = filename.substring(filename.lastIndexOf('.') + 1);

		// Save the resized image to the thumbnail directory.
		File thumbnailDir = new File(originalFile.getParentFile() + "\\thumbnail\\");
		if (!thumbnailDir.exists()) {
			thumbnailDir.mkdir();
		}
		ImageIO.write(thumbnail, fileExt, new File(thumbnailDir + "\\(resized)" + filename));
	}
	
	// Indexes the files so the directory only needs to be scanned once. Filenames are stored in memory for faster retrieval.
	private static class DirIndex {
		private final String filename;
		private static List<DirIndex> ListOfFiles = new ArrayList<DirIndex>();

		private DirIndex(String filename) {
			this.filename = filename;
		}
		
		private static void CreateIndex(String path) {
			ListOfFiles.clear(); // This will ensure a new index is created. Useful in case the path is changed.
			String file;
			File folder = new File(path);
			File[] listOfFiles = folder.listFiles(); 
			System.out.println("Creating index of files...\n");
			
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					file = listOfFiles[i].getName();
					
					DirIndex currentFile = new DirIndex(file);
					ListOfFiles.add(currentFile);
				}
			}
		}
		
		public static void LastModified(String path) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
			final String HEADING_FMT_STR = "%-50s%-17s\n";
			final String DATA_FMT_STR = "%-50s%-17s\n";
			
			File currentFile;
			
			System.out.printf(HEADING_FMT_STR, "File", "Last Modified");
			System.out.printf(HEADING_FMT_STR, "----", "-------------");
			
			// This completes the DEFAULT ACTION of the program: Records last modified and file path from each file encountered (comma delimited).
			try {
				PrintWriter writer = new PrintWriter("file_list.txt", "UTF-8");
				
				for (int i = 0; i<ListOfFiles.size(); i++) {
					currentFile = new File(path + "\\" + ListOfFiles.get(i).filename);
					writer.print("\"" + currentFile.getAbsolutePath() + "\",\"" + sdf.format(currentFile.lastModified()) + "\"\r\n");
				}
				writer.close();
			}
			catch (IOException e) {
				System.out.println("Error: " + e);
			}
			// end of DEFAULT ACTION
			
			for (int i = 0; i<ListOfFiles.size(); i++) {
				currentFile = new File(path + "\\" + ListOfFiles.get(i).filename);
				System.out.printf(DATA_FMT_STR, currentFile.getName(), sdf.format(currentFile.lastModified()));
			}
		}
		
		public static void DeleteFiles(String path) {
			File currentFile;
			String keyword;
			
			System.out.print("Enter a sequence of characters to search for in files to delete: ");
			Scanner stdIn = new Scanner(System.in);
			keyword = stdIn.nextLine();			
			
			System.out.println("\nThe following files will be deleted:");
			System.out.println("------------------------------------");
			
			for (int i = 0; i<ListOfFiles.size(); i++) {
				currentFile = new File(path + "\\" + ListOfFiles.get(i).filename);
				if (currentFile.getName().contains(keyword)) {
					System.out.println(currentFile.getName());
				}
			}
			
			// Gives the user a preview of what will be deleted, and a chance to opt-out.
			System.out.print("\nDo you wish to continue? (enter \"y\" for yes): ");
			
			// Outputs a list of files that were deleted.
			try {
				PrintWriter writer = new PrintWriter("secondary_action.txt", "UTF-8");
				writer.print("The following files were deleted:\r\n");
				writer.print("---------------------------------\r\n\r\n");
				
				if (stdIn.nextLine().toString().equalsIgnoreCase("y")) {	// The files will only be deleted if the user agrees.
					for (int i = 0; i<ListOfFiles.size(); i++) {
						currentFile = new File(path + "\\" + ListOfFiles.get(i).filename);
						if (currentFile.getName().contains(keyword)) {
							writer.print("\"" + currentFile.getAbsolutePath() + "\"\r\n");
							currentFile.delete();
						}
					}
				}
				writer.close();
			}
			catch (IOException e) {
				System.out.println("Error: " + e);
			}
		}
	}
}