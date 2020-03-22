import java.io.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

//FileControl.java

/*****************************************
*This class implements the Runnable      *
*interface and handles thread and file   *
*control. Each thread contains a separate*
*and independent simulation.             *
*****************************************/

public class FileControl implements Runnable{
	private String threadName;
	private Thread t;
	private PrintWriter writer;
	private String username;
	double balance;
	double bet;
	private PrintWriter trackWriter;
	private int totalGames;
	private boolean repeatMode;
	
	public FileControl(String tName, String name, double balAmount, double betAmount, PrintWriter tWriter) {
		threadName = tName;
		username = name;
		balance = balAmount;
		bet = betAmount;
		trackWriter = tWriter;
		repeatMode = false;
	}
	
	public void run() {//The run() method is automatically called by the Runnable interface when a thread gets
					   //called .start() on. In this method, a new file is created with the corresponding thread
					   //name and a simulation is launched within synchronized(thread name).
		File outputFile = new File("src/" + this.threadName + ".txt");
		try {
			if(outputFile.exists()) {
				//System.out.println("Clearing existing files...");
			}
			else {
				outputFile.createNewFile();
			}
			if (repeatMode) {
				writer = new PrintWriter(new FileOutputStream(outputFile, true));
			}
			else {
				writer = new PrintWriter(outputFile);
			}
			
			CrapsSimulation simulation = new CrapsSimulation(username, balance, bet);
			
			synchronized (simulation){
				simulation.setFileWriter(writer);
				simulation.setTrackWriter(trackWriter);
				simulation.setThreadName(threadName);
				simulation.getSimulationGame().setFileWriter(writer);
				simulation.getGameMonitor().setFileWriter(writer);
				simulation.start();
				totalGames = simulation.getGameMonitor().getGamesPlayed();
			}
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			//System.out.println("Done writing to the file.");
			writer.close();
		}
	}
	
	public Thread getThread() {
		return t;
	}
	
	public int getTotalGames(){
		return totalGames;
	}
	
	public void setRepeatMode(boolean check) {
		repeatMode = check;
	}
	
	public void starting() {//Starting is called whenever the MainMenu.java class needs to create a new thread
		                    //for a new simulation to run.
		String currentDateAndTime;

		if (t==null) {
			currentDateAndTime = Instant.now().atZone(ZoneId.systemDefault()).format( DateTimeFormatter.ISO_LOCAL_DATE_TIME ).replace("T"," ");
			System.out.println("Starting thread: " + threadName + " at " + currentDateAndTime + ".");
			trackWriter.println("Starting thread: " + threadName + " at " + currentDateAndTime + ".");
			t = new Thread(this, threadName);
			t.start();
		}
	}
}