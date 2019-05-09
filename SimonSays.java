/**
 * Nicholas Glenn
 * Ms. Krasteva
 * 16/1/18
 * This program lets the user play "Simon Says"
 * VARIABLES
 * NAME            TYPE          DESCRIPTION
 * choice          char          Input from mainMenu() which either determines what is processed, displays instructions, or exits program
 * c               Console       Window used to display everything in the program (except for error messages)
 * username        String        Stores username that user inputs in saveUser()
 * level           char          Stores difficulty that user inputs in levelSelect(), can be '1', '2', or '3'
 */

// Imports
import java.awt.*;
import java.io.*;
import hsa.Console;
import javax.swing.JOptionPane; // Message dialogue box

public class SimonSays // Main Class
{
    Console c; // Initiating console
    char choice = ' ', level = ' '; // main menu choice and level difficulty choice
    String username = ""; // stores the file name to use

    /**
     * Clears screen and displays title at top of screen
     */
    private void title ()
    {
	c.clear ();
	c.print ("", 35);
	c.println ("Simon Says\n");
    }


    /**
     * Pauses program until a key is pressed
     */
    private void pauseProgram ()
    {
	c.println ("\nPress any key to continue...");
	c.getChar ();
    }


    /**
     * Introduces program and displays animation of "Simon" toy
     * VARIABLES
     * NAME         TYPE       DESCRIPTION
     * a            Animation  Animation of Simon toy
     * LOOPS
     * 1    Clears keyboard buffer
     * TRY/CATCH
     * 1    Stops animation from running parallel to program
     * 2    Delays window before the program changes the window
     */
    public void splashScreen ()
    {
	title ();
	Animation a = new Animation(c);
	a.start();
	try
	{ // Try/Catch 1
	    a.join ();
	}
	catch (InterruptedException e)
	{
	}
	c.println ("This program lets you play the game 'Simon Says'.");
	try
	{ // Try/Catch 2
	    Thread.sleep (2000);
	}
	catch (InterruptedException e)
	{
	}
	while (c.isCharAvail ())
	{ // Loop 1
	    c.getChar ();
	}
    }


    /**
     * Saves username to use for high scores leaderboard
     * VARIABLES
     * NAME         TYPE       DESCRIPTION
     * nameGood     boolean    Can makes loop run or end
     * LOOPS
     * 1    Constantly prompts for username until it is acceptable
     * 2    Checks each character in username
     * CONDIIONALS
     * 1    Displays different errors depending on username length
     * 2    Displays error if username contains a non-alphanumerical character
     */
    public void saveUser ()
    {
	title ();
	boolean nameGood = false;
	while (!nameGood) // Loop 1
	{
	    c.setCursor (3, 1);
	    c.println ();
	    c.setCursor (3, 1);
	    c.print ("Enter your username: ");
	    username = c.readLine ();
	    if (username.length () > 8)
	    { // Conditional 1
		JOptionPane.showMessageDialog (null, "Your username must be at most 8 characters long", "Simon Says", JOptionPane.ERROR_MESSAGE); // Displays message dialogue box with error message
	    }
	    else if (username.length () == 0)
	    {
		JOptionPane.showMessageDialog (null, "Your username cannot be blank", "Simon Says", JOptionPane.ERROR_MESSAGE); // Displays message dialogue box with error message
	    }
	    else
	    {
		nameGood = true;
		for (int i = 0 ; i < username.length () ; i++) // Loop 2
		{
		    if (!((username.charAt (i) >= '0' && username.charAt (i) <= '9') || (username.charAt (i) >= 'a' && username.charAt (i) <= 'z') || (username.charAt (i) >= 'A' && username.charAt (i) <= 'Z')))
		    { // Conditional 2
			JOptionPane.showMessageDialog (null, "Your username must only include alphanumerical characters", "Simon Says", JOptionPane.ERROR_MESSAGE); // Displays message dialogue box with error message
			nameGood = false;
			break;
		    }
		}
	    }
	}
    }


    /**
     * Displays options of items to choose from
     * CONDIIONALS
     * 1    Errortraps main menu choice
     */
    public void mainMenu ()
    {
	title ();
	c.print ("", 32);
	c.println ("M a i n  M e n u\n");
	c.println ("\t\t\t1.\tInstructions");
	c.println ("\t\t\t2.\tLevel Selection");
	c.println ("\t\t\t3.\tHigh Scores");
	c.println ("\t\t\t4.\tExit");
	c.print ("\nEnter 1 - 4: ");
	choice = c.getChar ();
	if (choice < '1' || choice > '4')
	{ // Conditional 1
	    JOptionPane.showMessageDialog (null, "Enter 1 - 4", "Error", JOptionPane.ERROR_MESSAGE); // Displays message dialogue box with error message
	}
    }


    /**
     * Displays how to use program and how to play game
     */
    public void instructions ()
    {
	title ();
	c.println ("In level selection, you may choose the difficulties easy, medium, and hard which\nhave different delays and number of colours. You have to repeat the sequence\nshown in the correct order by inputting the letters 'r', 'y', 'g', 'b', 'o', and\n'p'. High scores shows the highest scores of all time and exit leaves the\nprogram.");
	pauseProgram ();
    }


    /**
     * Lets user choose level
     * CONDIIONALS
     * 1    Errortraps level selection choice
     */
    public void levelSelect ()
    {
	title ();
	c.println ("\t\t\t1.\tEasy");
	c.println ("\t\t\t2.\tMedium");
	c.println ("\t\t\t3.\tHard");
	c.print ("\nEnter 1 - 3: ");
	level = c.getChar ();
	if (level < '1' || level > '3')
	{ // Conditional 1
	    JOptionPane.showMessageDialog (null, "Enter 1 - 3", "Error", JOptionPane.ERROR_MESSAGE); // Displays message dialogue box with error message
	}
    }


    /**
     * Generates random sequence of given amount of colours that user repeats in game()
     * CONDITIONALS
     * 1    Generates different character based on random number that is generated
     */
    private char seqGen (int len)
    {
	int seed = (int) ((Math.random () * len) + 1);
	if (seed == 1)
	{ // Conditional 1
	    return 'r';
	}
	else if (seed == 2)
	{
	    return 'b';
	}
	else if (seed == 3)
	{
	    return 'y';
	}
	else if (seed == 4)
	{
	    return 'g';
	}
	else if (seed == 5)
	{
	    return 'o';
	}
	else
	{
	    return 'p';
	}
    }


    /**
     * Runs "Simon Says" game at specified difficulty
     *
     * VARIABLES
     * NAME             TYPE            DESCRIPTION
     * all lightcolours Color           Colours used to make game flash
     * wrong            boolean         Toggles to true when user makes a mistake with matching sequence, ending game round.
     * continueGame     char            Default value: 'y'. Stores user choice to continue after failing game
     * diffNum          int             Stores difficulty but as an integer. Used for calculating sequence length and number of colours
     * place            int             Place of user on leaderboard. Default value is
     * score            int             Stores user's current high score on current difficulty
     * tempHigh         String          New high score to be read to file/outputted in highscores
     * points           int[]           array of all points from highscores file.
     * contents         String[]        array of output scores from highscores file.
     * write            PrintWriter     Writes contents to highscores file.
     * read             BufferedReader  Reads contents in highscores file.
     * loopBound        int             Makes program read different part of file depending on difficulty.
     * skipped          boolean         Skips checking where user's score should be written
     *
     * LOOPS
     * 1    Keeps on running the game until the user chooses 'n'
     * 2    Generates starting sequence with different length and colours depending on difficulty
     * 3    Loops game, adds random character to sequence each loop, and exits when user input is wrong.
     * 4    Loops through and displays sequence by flashing the game.
     * 5    Clears keyboard buffer
     * 6    Loops through sequence and compares it to user input
     * 7    Errortraps sequence input depending on difficulty
     * 8    Stores file score as integer to points[]
     * 9    Stores file output as String to contents[]
     * 10   Writes scores to file at a lower difficulty than the user's difficulty
     * 11   Writes scores and user's score to file at the same difficulty as user's difficulty
     * 12   Writes scores to file at a higher difficulty than the user's difficulty
     * 13   Writes output scores displayed above user's output score
     * 14   Writes output scores displayed below user's output score
     * 15   Writes all output scores if user's output score is too low to be displayed
     * 16   Loops until input to continue the game is appropriate ('y' or 'n')
     *
     * CONDITIONALS
     * 1    Draws different amount of colours depending on difficulty
     * 2    Makes game flash depending on difficulty.
     * 3-5  Changes the colour and angle of flash depending on the character in the sequence.
     * 6    Displays different error messages depending on difficulty.
     * 7    Ends game round as soon as user's input is incorrect.
     * 8    Runs if user's input for whole sequence is correct.
     * 9    Adds different amounts of score depending on difficulty and sequence length.
     * 10   Changes user's output score depending on difficulty.
     * 11   Writes user's score to file if it hasn't already been written and it is greater than the existing score.
     * 12   Does not record last score in same difficulty if user's score has been written to file
     * 13   Writes user's output score and other scores if it is high enough
     * 14   Exits game if user would not like to continue
     *
     * TRY/CATCH
     * Try/Catch1-3    Adds delay during the flash (diffferent based on difficulty). Catches InterruptedException.
     * Try/Catch4      Adds delay after the flash. Catches InterruptedException.
     * Try/Catch5      Writes user's score to highscores. Catches IOException.
     */
    public void game (char difficulty)
    {
	title ();
	Color lightyellow = new Color (255, 255, 127);
	Color lightred = new Color (255, 127, 127);
	Color lightgreen = new Color (127, 255, 127);
	Color lightblue = new Color (127, 127, 255);
	Color lightorange = new Color (255, 255, 127);
	Color lightpurple = new Color (255, 127, 255);
	boolean wrong = false;
	char continueGame = 'y';
	int diffNum = (int) difficulty - '0';
	int score = 0;
	while (continueGame == 'y')
	{ // Loop 1
	    String sequence = "";
	    for (int i = 0 ; i < diffNum * 2 ; i++)
	    { // Loop 2
		sequence += seqGen (diffNum * 2);
	    }
	    for (; wrong == false ; sequence += seqGen (diffNum * 2))
	    { // Loop 3
		title ();
		if (difficulty == '1')
		{ // Conditional 1
		    c.setColor (Color.red);
		    c.fillArc (275, 250, 250, 250, 0, 180);
		    c.setColor (Color.blue);
		    c.fillArc (275, 250, 250, 250, 180, 180);
		}
		else if (difficulty == '2')
		{
		    c.setColor (Color.yellow);
		    c.fillArc (275, 250, 250, 250, 0, 90);
		    c.setColor (Color.red);
		    c.fillArc (275, 250, 250, 250, 90, 90);
		    c.setColor (Color.green);
		    c.fillArc (275, 250, 250, 250, 180, 90);
		    c.setColor (Color.blue);
		    c.fillArc (275, 250, 250, 250, 270, 90);
		}
		else
		{
		    c.setColor (Color.yellow);
		    c.fillArc (275, 250, 250, 250, 0, 60);
		    c.setColor (new Color (255, 0, 255));
		    c.fillArc (275, 250, 250, 250, 60, 60);
		    c.setColor (Color.red);
		    c.fillArc (275, 250, 250, 250, 120, 60);
		    c.setColor (Color.green);
		    c.fillArc (275, 250, 250, 250, 180, 60);
		    c.setColor (Color.orange);
		    c.fillArc (275, 250, 250, 250, 240, 60);
		    c.setColor (Color.blue);
		    c.fillArc (275, 250, 250, 250, 300, 60);
		}
		for (int i = 0 ; i < sequence.length () ; i++)
		{ // Loop 4
		    int angle;
		    if (difficulty == '1')
		    { // Conditional 2
			if (sequence.charAt (i) == 'r')
			{ // Conditional 3
			    c.setColor (lightred);
			    angle = 0;
			}
			else
			{
			    c.setColor (lightblue);
			    angle = 180;
			}
			c.fillArc (275, 250, 250, 250, angle, 180);
			try // Try/Catch 1
			{
			    Thread.sleep (2000);
			}
			catch (InterruptedException e)
			{
			}
			c.setColor (Color.red);
			c.fillArc (275, 250, 250, 250, 0, 180);
			c.setColor (Color.blue);
			c.fillArc (275, 250, 250, 250, 180, 180);
		    }
		    else if (difficulty == '2')
		    {
			if (sequence.charAt (i) == 'y')
			{ // Conditional 4
			    c.setColor (lightyellow);
			    angle = 0;
			}
			else if (sequence.charAt (i) == 'r')
			{
			    c.setColor (lightred);
			    angle = 90;
			}
			else if (sequence.charAt (i) == 'g')
			{
			    c.setColor (lightgreen);
			    angle = 180;
			}
			else
			{
			    c.setColor (lightblue);
			    angle = 270;
			}
			c.fillArc (275, 250, 250, 250, angle, 90);
			try // Try/Catch 2
			{
			    Thread.sleep (1000);
			}
			catch (InterruptedException e)
			{
			}
			c.setColor (Color.yellow);
			c.fillArc (275, 250, 250, 250, 0, 90);
			c.setColor (Color.red);
			c.fillArc (275, 250, 250, 250, 90, 90);
			c.setColor (Color.green);
			c.fillArc (275, 250, 250, 250, 180, 90);
			c.setColor (Color.blue);
			c.fillArc (275, 250, 250, 250, 270, 90);
		    }
		    else
		    {
			if (sequence.charAt (i) == 'y')
			{ // Conditional 5
			    c.setColor (lightyellow);
			    angle = 0;
			}
			else if (sequence.charAt (i) == 'p')
			{
			    c.setColor (lightpurple);
			    angle = 60;
			}
			else if (sequence.charAt (i) == 'r')
			{
			    c.setColor (lightred);
			    angle = 120;
			}
			else if (sequence.charAt (i) == 'g')
			{
			    c.setColor (lightgreen);
			    angle = 180;
			}
			else if (sequence.charAt (i) == 'o')
			{
			    c.setColor (lightorange);
			    angle = 240;
			}
			else
			{
			    c.setColor (lightblue);
			    angle = 300;
			}
			c.fillArc (275, 250, 250, 250, angle, 60);
			try // Try/Catch 3
			{
			    Thread.sleep (500);
			}
			catch (InterruptedException e)
			{
			}
			c.setColor (Color.yellow);
			c.fillArc (275, 250, 250, 250, 0, 60);
			c.setColor (new Color (255, 0, 255));
			c.fillArc (275, 250, 250, 250, 60, 60);
			c.setColor (Color.red);
			c.fillArc (275, 250, 250, 250, 120, 60);
			c.setColor (Color.green);
			c.fillArc (275, 250, 250, 250, 180, 60);
			c.setColor (Color.orange);
			c.fillArc (275, 250, 250, 250, 240, 60);
			c.setColor (Color.blue);
			c.fillArc (275, 250, 250, 250, 300, 60);
		    }
		    try // Try/Catch 4
		    {
			Thread.sleep (500);
		    }
		    catch (InterruptedException e)
		    {
		    }
		}
		while (c.isCharAvail ())
		{ // Loop 5
		    c.getChar ();
		}
		c.print ("Input the sequence shown: ");
		for (int i = 0 ; i < sequence.length () ; i++)
		{ // Loop 6
		    char input = c.getChar ();
		    while (input != 'r' && input != 'b')
		    { // Loop 7
			if (level == '1')
			{ // Conditional 6
			    JOptionPane.showMessageDialog (null, "Enter 'r' or 'b'", "Error", JOptionPane.ERROR_MESSAGE); // Displays message dialogue box with error message
			}
			else if (level == '2' && input != 'y' && input != 'g')
			{
			    JOptionPane.showMessageDialog (null, "Enter 'r', 'y', 'g', or 'b'", "Error", JOptionPane.ERROR_MESSAGE); // Displays message dialogue box with error message
			}
			else if (level == '3' && input != 'y' && input != 'g' && input != 'o' && input != 'p')
			{
			    JOptionPane.showMessageDialog (null, "Enter 'r', 'y', 'o', 'g', 'p', or 'b'", "Error", JOptionPane.ERROR_MESSAGE); // Displays message dialogue box with error message
			}
			else
			{
			    break;
			}
			input = c.getChar ();
		    }
		    if (sequence.charAt (i) != input)
		    { // Conditional 7
			wrong = true;
			break;
		    }
		}
		if (!wrong)
		{ // Conditional 8
		    if (difficulty == '1')
		    { // Conditional 9
			score += 100 * sequence.length ();
		    }
		    else if (difficulty == '2')
		    {
			score += 200 * sequence.length ();
		    }
		    else
		    {
			score += 400 * sequence.length ();
		    }
		    c.setColor (Color.black);
		    c.setFont (new Font ("Jokerman", 1, 24));
		    c.drawString ("Good job", 340, 200);
		    pauseProgram ();
		}
	    }
	    wrong = false;
	    try
	    { // Try/Catch 5
		boolean skipped = false;
		int place = -1;
		int loopBound = (diffNum - 1) * 3;
		String tempHigh;
		int[] points = new int [9];
		String[] contents = new String [9];
		BufferedReader read = new BufferedReader (new FileReader ("highscores.txt"));
		for (int i = 0 ; i < 9 ; i++)
		{ // Loop 8
		    points [i] = Integer.parseInt (read.readLine ());
		}
		for (int i = 0 ; i < 9 ; i++)
		{ // Loop 9
		    contents [i] = read.readLine ();
		}
		if (level == '1')
		{ // Conditional 10
		    tempHigh = username + " - " + score + " - " + "EASY";
		}
		else if (level == '2')
		{
		    tempHigh = username + " - " + score + " - " + "MEDIUM";
		}
		else
		{
		    tempHigh = username + " - " + score + " - " + "HARD";
		}
		PrintWriter write = new PrintWriter (new FileWriter ("highscores.txt"));
		for (int i = 0 ; i < loopBound ; i++)
		{ // Loop 10
		    write.println (points [i]);
		}
		for (int i = loopBound ; i < loopBound + 3 ; i++)
		{ // Loop 11
		    if (score > points [i] && !skipped)
		    { // Conditional 11
			write.println (score);
			place = i;
			skipped = true;
		    }
		    if (!(skipped && i == loopBound + 2)) // Conditional 12
			write.println (points [i]);
		}
		for (int i = loopBound + 3 ; i < 9 ; i++)
		{ // Loop 12
		    write.println (points [i]);
		}
		if (place != -1)
		{ // Conditional 13
		    for (int i = 0 ; i < place ; i++)
		    { // Loop 13
			write.println (contents [i]);
		    }
		    write.println (tempHigh);
		    for (int i = place + 1 ; i < 9 ; i++)
		    { // Loop 14
			write.println (contents [i]);
		    }
		}
		else
		{
		    for (int i = 0 ; i < 9 ; i++)
		    { // Loop 15
			write.println (contents [i]);
		    }
		}
		write.close ();
	    }
	    catch (IOException e)
	    {
	    }
	    score = 0;
	    c.setColor (Color.black);
	    c.setFont (new Font ("Comic Sans MS", 1, 24));
	    c.drawString ("Bad job", 350, 200);
	    c.print ("Would you like to continue? (y/n): ");
	    continueGame = c.getChar ();
	    while (continueGame != 'y' && continueGame != 'n')
	    { // Loop 16
		JOptionPane.showMessageDialog (null, "Enter 'y' or 'n'", "Error", JOptionPane.ERROR_MESSAGE); // Displays message dialogue box with error message
		continueGame = c.getChar ();
	    }
	    if (continueGame == 'n') // Conditional 14
		return;
	}
    }


    /**
     * Displays high scores on a leaderboard
     * VARIABLES
     * NAME         TYPE            DESCRIPTION
     * zero         boolean[]       Shows if the file has a score of zero (and skips it in output)
     * write        PrintWriter     Writes contents to highscores file.
     * read         BufferedReader  Reads contents in highscores file.
     *
     * LOOPS
     * 1            Reads the value of the scores and stores where the zeroes are
     * 2            Outputs scores if they aren't zero
     * 3            Resets scores to 0
     * 4            Resets outputs to '<no user>'
     */
    public void highScores ()
    {
	title ();
	try
	{
	    boolean[] zero = new boolean [9];
	    BufferedReader read = new BufferedReader (new FileReader ("highscores.txt"));
	    for (int i = 0 ; i < 9 ; i++)
	    { // Loop 1
		if (read.readLine ().equals ("0"))
		    zero [i] = true;
	    }
	    for (int i = 0 ; i < 9 ; i++)
	    { // Loop 2
		String input = read.readLine ();
		if (!zero [i])
		    c.println (input);
	    }
	}
	catch (IOException e)
	{
	}
	c.println ("Would you like to reset the high scores? (y/n): ");
	char input = c.getChar ();
	while (input != 'y' && input != 'n')
	{
	    JOptionPane.showMessageDialog (null, "Enter 'y' or 'n'", "Error", JOptionPane.ERROR_MESSAGE); // Displays message dialogue box with error message
	    input = c.getChar ();
	}
	if (input == 'y')
	{
	    try
	    {
		PrintWriter write = new PrintWriter (new FileWriter ("highscores.txt"));
		for (int i = 0 ; i < 9 ; i++)
		{ // Loop 3
		    write.println (0);
		}
		for (int i = 0 ; i < 9 ; i++)
		{ // Loop 4
		    write.println ("<no user>");
		}
		write.close ();
	    }
	    catch (IOException e)
	    {
	    }
	}
	pauseProgram ();
    }


    /**
     * Displays closing remarks and closes console
     * TRY/CATCH
     * 1    Delays goodbye message before closing window.
     */
    public void goodbye ()
    {
	title ();
	c.print ("", 37);
	c.println ("Goodbye");
	c.setCursor (24, 1);
	c.println ("By: Nicholas Glenn\tMade 16/1/19");
	try
	{ // Try/Catch 1
	    Thread.sleep (3000);
	}
	catch (InterruptedException e)
	{
	}
	c.close ();
    }


    /**
     * Constructor
     */
    public SimonSays ()
    {
	c = new Console (16, "Simon Says");
    }

    /**
     * Main Method
     * LOOPS
     * 1    Runs program until user selects '4' (Exit) in main menu
     *
     * CONDITIONALS
     * 1    Runs different methods depending on main menu choice
     */
    public static void main (String[] args)
    {
	SimonSays ss = new SimonSays ();
	ss.splashScreen ();
	ss.saveUser ();
	while (ss.choice != '4')
	{ // Loop 1
	    ss.mainMenu ();
	    if (ss.choice == '1')
	    { // Conditional 1
		ss.instructions ();
	    }
	    else if (ss.choice == '2')
	    {
		ss.levelSelect ();
		ss.game (ss.level);
	    }
	    else if (ss.choice == '3')
	    {
		ss.highScores ();
	    }
	}
	ss.goodbye ();
    }
} // End of Class
