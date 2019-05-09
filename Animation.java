/**
*  Nicholas Glenn
*  Mrs. Krasteva
*  16/1/18
*  This class animates a simon says game.
 * NAME            TYPE             DESCRIPTION
 * c               private Console  Console used to display animation
*/

import java.awt.*;
import hsa.Console;
import java.lang.*;

public class Animation extends Thread
{
    private Console c;

    /**
     * Animation in splash screen
     * LOOPS
     * 1    Loop to animate splashscreen
     *
     * TRY/CATCH
     * 1    Changes speed of animation
     */
    public void animation ()
    {
	for (int i = 0 ; i <= 400 ; i++)
	{ // Loop 1
	    c.setColor (Color.white);
	    c.fillArc (676 - i, -151 + i, 250, 250, 0, 90);
	    c.fillArc (-126 + i, -151 + i, 250, 250, 90, 90);
	    c.fillArc (-126 + i, 651 - i, 250, 250, 180, 90);
	    c.fillArc (676 - i, 651 - i, 250, 250, 270, 90);
	    c.setColor (Color.yellow);
	    c.fillArc (675 - i, -150 + i, 250, 250, 0, 90);
	    c.setColor (Color.red);
	    c.fillArc (-125 + i, -150 + i, 250, 250, 90, 90);
	    c.setColor (Color.green);
	    c.fillArc (-125 + i, 650 - i, 250, 250, 180, 90);
	    c.setColor (Color.blue);
	    c.fillArc (675 - i, 650 - i, 250, 250, 270, 90);
	    try
	    { // Try/Catch 1
		Thread.sleep (10);
	    }
	    catch (InterruptedException e)
	    {
	    }
	}
    }


    public Animation (Console con)
    {
	c = con;
    }


    public void run ()
    {
	animation ();
    }
}

