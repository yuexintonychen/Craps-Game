## Craps Game Simulator
A simple Java implementation of Craps game that allows 5 simulations of the game to be run at the same time using multithreading (runnable interface).

• The user name, balance and bet will be shared by all the threads.

• Each thread will write the output including the statistics to a text file, including games played, games won, games lost, maximum rolls in a single game, natural count, craps count, maximum winning streak, maximum losing streak and maximum balance during a game. In addition,	the time in milliseconds of each event occurs (For example the time a dice is rolled, the time a bet is made, etc.) will also be recorded in the files.

• The start time and the end time of each thread, as well as total games played for all threads are tracked, recoreded and printed to the console and written into "tracking.txt".

• To start the program, simply compile and run "MainMenu.java".
