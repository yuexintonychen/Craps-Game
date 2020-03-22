import java.io.PrintWriter;
import java.util.Scanner;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.io.FileOutputStream;

//CrapsSimulation.java

/*****************************************
*This class contains all the information *
*for the Simulation. This includes:      *
*A CrapsGame object                      *
*A CrapsMetricsMonitor object            *
*The user's name                         *
*The user's balance                      *
*The user's bet                          *
*The current win streak                  *
*The current lose streak                 *
*The start() method starts a loop of a   *
*single simulation run.                  *
*****************************************/

public class CrapsSimulation{
	private CrapsGame simulationGame;
	private CrapsMetricsMonitor gameMonitor;
	private String username;
	private double balance;
	private double bet;
	private int curWinStreak;
	private int curLoseStreak;
	private String threadName;
	private PrintWriter fileWriter;
	private PrintWriter trackWriter;
	
	public CrapsSimulation(String name, double balAmount, double betAmount) {
							  //The constructor assigns default values for all class variables.
							  //It also creates a new CrapsMetricsMonitor and CrapsGame objects.
		balance = balAmount;
		bet = betAmount;
		curWinStreak = 0;
		curLoseStreak = 0;
		username = name;
		gameMonitor = new CrapsMetricsMonitor();
		simulationGame = new CrapsGame(gameMonitor);
	}
	
	public void start() {//This method handles all information related to user interface and user input
						 //and initiates a simulation that contains a (set of) game(s).
		String currentDateAndTime;
		
		while (balance > 0) {//This while loop contains a single simulation,
							 //which keeps running until the balance becomes zero.
			boolean special = false;
			double testBet = bet;
			if (balance - bet < 0) {
				testBet = balance;
				special = true;
			}
			currentDateAndTime = Instant.now().atZone(ZoneId.systemDefault()).format( DateTimeFormatter.ISO_LOCAL_DATE_TIME ).replace("T"," ");
			if (special) {
				int parsedTestBet = (int)testBet;
				if ((double)parsedTestBet == testBet)
				{
					fileWriter.println(username + " bets " + parsedTestBet + " at " + currentDateAndTime + ".");
				}
				else {
					fileWriter.println(username + " bets " + testBet + " at " + currentDateAndTime + ".");
				}
			}
			else {
				int parsedBet = (int)bet;
				if ((double)parsedBet == bet) {
					fileWriter.println(username + " bets " + parsedBet + " at " + currentDateAndTime + ".");
				}
				else
				{
					fileWriter.println(username + " bets " + bet + " at " + currentDateAndTime + ".");
				}
			}
			boolean result = simulationGame.playGame();
			if (result)
			{
				balance += testBet;
				curLoseStreak = 0;
				curWinStreak += 1;
				if (curWinStreak > gameMonitor.getMaxWinStreak()) {
					gameMonitor.setMaxWinStreak(curWinStreak);
				}
				gameMonitor.setWon(gameMonitor.getWon()+1);
			}
			else
			{
				balance -= testBet;
				curWinStreak = 0;
				curLoseStreak += 1;
				if (curLoseStreak > gameMonitor.getMaxLoseStreak()) {
					gameMonitor.setMaxLoseStreak(curLoseStreak);
				}
				gameMonitor.setLost(gameMonitor.getLost()+1);
			}
			if (balance > gameMonitor.getMaxBalance())
			{
				gameMonitor.setMaxBalance(balance);
				gameMonitor.setMaxBalanceGameNum(gameMonitor.getGamesPlayed()+1);
			}
			if (curWinStreak > gameMonitor.getMaxWinStreak()) {
				gameMonitor.setMaxWinStreak(curWinStreak);
			}
			if (curLoseStreak > gameMonitor.getMaxLoseStreak()) {
				gameMonitor.setMaxLoseStreak(curLoseStreak);
			}
			gameMonitor.setGamesPlayed(gameMonitor.getGamesPlayed()+1);
			if (balance>0){
				int parsedBalance2 = (int)balance;
				currentDateAndTime = Instant.now().atZone(ZoneId.systemDefault()).format( DateTimeFormatter.ISO_LOCAL_DATE_TIME ).replace("T"," ");
				if ((double)parsedBalance2 == balance) {
					fileWriter.println(username + "'s balance: " + parsedBalance2 + ". Playing a new game..." + " at " + currentDateAndTime + ".");
				}
				else {
					fileWriter.println(username + "'s balance: " + balance + ". Playing a new game..." + " at " + currentDateAndTime + ".");
				}
			}
		}
		
		currentDateAndTime = Instant.now().atZone(ZoneId.systemDefault()).format( DateTimeFormatter.ISO_LOCAL_DATE_TIME ).replace("T"," ");
		System.out.println("Ending thread: " + threadName + " at " + currentDateAndTime + ".");
		trackWriter.println("Ending thread: " + threadName + " at " + currentDateAndTime + ".");
		
		int parsedBalance3 = (int)balance;
		currentDateAndTime = Instant.now().atZone(ZoneId.systemDefault()).format( DateTimeFormatter.ISO_LOCAL_DATE_TIME ).replace("T"," ");
		if ((double)parsedBalance3 == balance) {
			fileWriter.println(username + "'s balance: " + parsedBalance3 + " at " + currentDateAndTime + ".");
		}
		else {
			fileWriter.println(username + "'s balance: " + balance + " at " + currentDateAndTime + ".");
		}
		
		fileWriter.println();
		gameMonitor.printStatistics();
		
	}
	
	public void setFileWriter(PrintWriter pw) {
		fileWriter = pw;
	}
	
	public void setTrackWriter(PrintWriter pw) {
		trackWriter = pw;
	}
	
	public void setThreadName(String name) {
		threadName = name;
	}
	
	public CrapsGame getSimulationGame() {
		return simulationGame;
	}
	
	public CrapsMetricsMonitor getGameMonitor() {
		return gameMonitor;
	}
	
	public void reset() {//Resets all variables as a new simulation will start in the next loop
		System.out.println();
		balance = 0;
		bet = 0;
		curWinStreak = 0;
		curLoseStreak = 0;
		username = "";
		gameMonitor.reset();
		simulationGame = new CrapsGame(gameMonitor);
	}
}