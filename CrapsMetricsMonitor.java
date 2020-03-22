import java.io.PrintWriter;
import java.io.FileOutputStream;

//CrapsMetricsMonitor.java

/******************************************
*This class contains all of the statistics*
*gathered during a single simulation.     *
*The printStatistics() method prints each *
*statistic variable.                      *
*The reset() method resets the state of   *
*the CrapsMetricsMonitor object.          *
******************************************/

public class CrapsMetricsMonitor{//All essential statistical data is stored in this class.
	private int gamesPlayed;
	private int won;
	private int lost;
	private int maxRolls;
	private int naturalCount;
	private int crapsCount;
	private int maxWinStreak;
	private int maxLoseStreak;
	private double maxBalance;
	private int maxBalanceGameNum;
	private PrintWriter fileWriter;
	
	public CrapsMetricsMonitor() {//The constructor assigns default values for all class variables.
		gamesPlayed = 0;
		won = 0;
		lost = 0;
		maxRolls = 0;
		naturalCount = 0;
		crapsCount = 0;
		maxWinStreak = 0;
		maxLoseStreak = 0;
		maxBalance = 0;
		maxBalanceGameNum = 0;
	}
	
	public int getGamesPlayed() {
		return gamesPlayed;
	}
	
	public void setGamesPlayed(int gp) {
		gamesPlayed = gp;
	}
	
	public int getWon() {
		return won;
	}
	
	public void setWon(int num) {
		won = num;
	}
	
	public int getLost() {
		return lost;
	}
	
	public void setLost(int num) {
		lost = num;
	}
	
	public int getNaturalCount() {
		return naturalCount;
	}
	
	public void setNaturalCount(int count) {
		naturalCount = count;
	}
	
	public int getCrapsCount() {
		return crapsCount;
	}
	
	public void setCrapsCount(int count) {
		crapsCount = count;
	}
	
	public double getMaxBalance() {
		return maxBalance;
	}
	
	public void setMaxBalance(double bal) {
		maxBalance = bal;
	}
	
	public void setMaxBalanceGameNum(int num) {
		maxBalanceGameNum = num;
	}
	
	public int getMaxWinStreak() {
		return maxWinStreak;
	}
	
	public void setMaxWinStreak(int streak) {
		maxWinStreak = streak;
	}
	
	public int getMaxLoseStreak() {
		return maxLoseStreak;
	}
	
	public void setMaxLoseStreak(int streak) {
		maxLoseStreak = streak;
	}
	
	public int getMaxRolls() {
		return maxRolls;
	}
	
	public void setMaxRolls(int rolls) {
		maxRolls = rolls;
	}
	
	public void setFileWriter(PrintWriter pw) {
		fileWriter = pw;
	}
	
	public void printStatistics() {//This method is responsible for user output of the current simulation.
								   //It prints all relevant information to the console.
		fileWriter.println("*****************************");
		fileWriter.println("*** SIMULATION STATISTICS ***");
		fileWriter.println("*****************************");
		fileWriter.println("Games played: " + gamesPlayed);
		fileWriter.println("Games won: " + won);
		fileWriter.println("Games lost: " + lost);
		fileWriter.println("Maximum Rolls in a single game: " + maxRolls);
		fileWriter.println("Natural Count: " + naturalCount);
		fileWriter.println("Craps Count: " + crapsCount);
		fileWriter.println("Maximum Winning Streak: " + maxWinStreak);
		fileWriter.println("Maximum Losing Streak: " + maxLoseStreak);
		int parsedMaxBalance = (int)maxBalance;
		if ((double)parsedMaxBalance == maxBalance) {
			fileWriter.println("Maximum Balance: " + parsedMaxBalance + " during game " + 
			maxBalanceGameNum);
		}
		else {
			fileWriter.println("Maximum Balance: " + maxBalance + " during game " + 
			maxBalanceGameNum);
		}
		fileWriter.println();
	}
	
	public void reset(){//This method restores every class variable to its initial (default) value.
		gamesPlayed = 0;
		won = 0;
		lost = 0;
		maxRolls = 0;
		naturalCount = 0;
		crapsCount = 0;
		maxWinStreak = 0;
		maxLoseStreak = 0;
		maxBalance = 0;
		maxBalanceGameNum = 0;
	}
}