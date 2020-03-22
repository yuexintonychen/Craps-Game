import java.io.PrintWriter;
import java.util.Random;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.io.FileOutputStream;

//CrapsGame.java

/*****************************************
*This class contains all the information *
*for a single craps game, including      *
*Number of times a roll happened         *
*A CrapsMetricsMonitor object for        *
*statistics of a single simulation       *
*The playGame() contains the algorithm   *
*of running a single game with boolean   *
*result.                                 *
*****************************************/

public class CrapsGame{
	private int rollCount;
	private CrapsMetricsMonitor gameMonitor;
	PrintWriter fileWriter;
	
	public CrapsGame(CrapsMetricsMonitor monitor){//The constructor takes in a CrapsMetricsMonitor
												  //object and sets default values for class variables.
		rollCount = 1;
		gameMonitor = monitor;
	}
	public void setFileWriter(PrintWriter pw) {
		fileWriter = pw;
	}
	
	public boolean playGame() {//This method includes the algorithm of a single game.
		int pointNumber = 0;
		rollCount = 1;
		while (true)//Two separate random generators are created in each loop.
					//In each loop, the number of rolls will always be checked first.
		{
		String currentDateAndTime;
			
		Random generator1 = new Random();
		int dieone = generator1.nextInt(5) + 1;
		Random generator2 = new Random();
		int dietwo = generator2.nextInt(5) + 1;
		int sum = dieone + dietwo;
		currentDateAndTime = Instant.now().atZone(ZoneId.systemDefault()).format( DateTimeFormatter.ISO_LOCAL_DATE_TIME ).replace("T"," ");
		fileWriter.println("Rolled a " + sum + " at " + currentDateAndTime + ".");
		
		if (rollCount == 1) {
			currentDateAndTime = Instant.now().atZone(ZoneId.systemDefault()).format( DateTimeFormatter.ISO_LOCAL_DATE_TIME ).replace("T"," ");
			if (sum == 7 || sum == 11) {
				fileWriter.println("*****Natural! You win!*****" + " at " + currentDateAndTime + ".");
				gameMonitor.setNaturalCount(gameMonitor.getNaturalCount()+1);
				if (rollCount > gameMonitor.getMaxRolls()) {
					gameMonitor.setMaxRolls(rollCount);
				}
				return true;
			}
			else if (sum == 2 || sum == 3 || sum == 12) {
				fileWriter.println("*****Craps! You lose.*****" + " at " + currentDateAndTime + ".");
				gameMonitor.setCrapsCount(gameMonitor.getCrapsCount()+1);
				if (rollCount > gameMonitor.getMaxRolls()) {
					gameMonitor.setMaxRolls(rollCount);
				}
				return false;
			}
			else
			{
				pointNumber = sum;
			}
		}
		
		if (rollCount > 1) {
			currentDateAndTime = Instant.now().atZone(ZoneId.systemDefault()).format( DateTimeFormatter.ISO_LOCAL_DATE_TIME ).replace("T"," ");
			if (sum == 7) {
				fileWriter.println("*****Crap out! You lose.*****" + " at " + currentDateAndTime + ".");
				if (rollCount > gameMonitor.getMaxRolls()) {
					gameMonitor.setMaxRolls(rollCount);
				}
				return false;
			}
			if (sum == pointNumber) {
				fileWriter.println("*****Rolled the point! You win!*****" + " at " + currentDateAndTime + ".");
				if (rollCount > gameMonitor.getMaxRolls()) {
					gameMonitor.setMaxRolls(rollCount);
				}
				return true;
			}
		}
		rollCount += 1;
		}
		
	}
	
}