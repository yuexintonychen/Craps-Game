import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

//MainMenu.java

/******************************************
*This class contains the main method. The *
*main method has the main loop that       *
*controls the user interface and takes in *
*necessary user input. The while loop may *
*contain multiple groups of a 5-simulation*
*set. This main method is also responsible*
*for keeping track of thread start/end    *
*times and the total game count in each   *
*loop of simulation set.                  *
******************************************/

public class MainMenu{
	public static void main(String[] args) {//The main method starts a while loop.
		boolean repeatMode = false;
		while (true) {//The loop that may contain multiple 5-simulation sets(depends on the user's answer)
			String username;
			double balance;
			double bet;
			System.out.print("Welcome to SimCraps! Enter your user name: ");
			Scanner input = new Scanner(System.in);
			username = input.nextLine().trim();
			while (username.equals("") || username.equals(" ") || username.length() == 0) {
				System.out.print("Invalid user name, please try again: ");
				input = new Scanner(System.in);
				username = input.nextLine().trim();
			}
			System.out.println("Hello " + username);
			System.out.print("Enter the amount of money you will bring to the table: ");
			input = new Scanner(System.in);
			balance = input.nextDouble();
			while (balance <= 0) {
				System.out.print("Invalid balance, please try again: ");
				input = new Scanner(System.in);
				balance = input.nextDouble();
			}
			int parsedBalance1 = (int)balance;
			if ((double)parsedBalance1 == balance)
			{
				System.out.print("Enter the bet amount between $1 and $" + parsedBalance1 + ": ");
			}
			else {
				System.out.print("Enter the bet amount between $1 and $" + balance + ": ");
			}
			input = new Scanner(System.in);
			bet = input.nextDouble();
			while (bet<1 || bet>balance) {
				System.out.print("Invalid bet! Please enter a bet between $1 and balance: ");
				input = new Scanner(System.in);
				bet = input.nextDouble();
			}
			
			File trackFile = new File("src/tracking.txt");
			
			PrintWriter trackWriter = null;
			
			try {
				if(trackFile.exists()) {
					//System.out.println("Clearing existing files...");
				}
				else {
					trackFile.createNewFile();
				}
				if (repeatMode) {
					trackWriter = new PrintWriter(new FileOutputStream(trackFile, true));
				}else {
					trackWriter = new PrintWriter(trackFile);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			FileControl file1 = new FileControl("Simulation1", username, balance, bet, trackWriter);
			file1.setRepeatMode(repeatMode);
			file1.starting();
			
			FileControl file2 = new FileControl("Simulation2", username, balance, bet, trackWriter);
			file2.setRepeatMode(repeatMode);
			file2.starting();
			
			FileControl file3 = new FileControl("Simulation3", username, balance, bet, trackWriter);
			file3.setRepeatMode(repeatMode);
			file3.starting();
			
			FileControl file4 = new FileControl("Simulation4", username, balance, bet, trackWriter);
			file4.setRepeatMode(repeatMode);
			file4.starting();
			
			FileControl file5 = new FileControl("Simulation5", username, balance, bet, trackWriter);
			file5.setRepeatMode(repeatMode);
			file5.starting();
			
			while (true) {
				if (file1.getThread().isAlive() == false &&
					file2.getThread().isAlive() == false &&
					file3.getThread().isAlive() == false &&
					file4.getThread().isAlive() == false &&
					file5.getThread().isAlive() == false) {
					break;
				}
			}
			
			int totalGames = file1.getTotalGames() +
							 file2.getTotalGames() +
							 file3.getTotalGames() +
							 file4.getTotalGames() +
							 file5.getTotalGames();
			
			System.out.println("Total Games Played: " + totalGames + ".");
			System.out.println("Game activities recording completed with timestamps in files.");
			
			trackWriter.println("Total Games Played: " + totalGames + ".");
			trackWriter.println();
			trackWriter.close();
			
			//Asks the user if he/she wants to start another game(simulation)
			System.out.print("Replay? Enter 'y' or 'n': ");
			Scanner endRepeat = new Scanner(System.in);
			String check = endRepeat.nextLine();
			while (check.equals("n") == false && 
					check.equals("N") == false && 
					check.equals("y") == false && 
					check.equals("Y") == false)
			{
				System.out.print("Invalid input, please enter 'y' or 'n': ");
				endRepeat = new Scanner(System.in);
				check = endRepeat.nextLine();
			}
			if (check.equals("n") || check.equals("N")){
				break;
			}
			repeatMode = true;
			System.out.println();
			
			//simulation.reset();
		}
	}
}