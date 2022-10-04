/* This will handle the "Hot Key" system. */

package Main;

import Data.spriteInfo;
import logic.Control;
import timer.stopWatchX;

public class KeyProcessor{
	// Static Fields
	private static char last = ' ';			// For debouncing purposes
	private static stopWatchX sw = new stopWatchX(250);





	spriteInfo active = new spriteInfo(null, null);
	// Static Method(s)
	public static void processKey(char key){

		if(key == ' ')	
		{
			Main.trigger = "";
			return;
		}

		if(key == last)
			if(sw.isTimeUp() == false)			
				return;
		
		last = key;
		sw.resetWatch();
		
		/* TODO: You can modify values below here! */
		switch(key){
		case '%':								// ESC key
			System.exit(0);
			break;
			
		case 'w':
			Main.trigger = "W";
			break;
			
		case 'a':
			Main.trigger = "A";
			break;
			
		case 's':
			Main.trigger = "S";
			break;
			
		case 'd':
			Main.trigger = "D";
			break;
			
		case '$':
			Main.trigger = "$";
			break;
			
		case 'm':
			// For mouse coordinates
			Control.isMouseCoordsDisplayed = !Control.isMouseCoordsDisplayed;
			break;
		}
	}
}