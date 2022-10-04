//Trevor Blake CSC130-04
package Main;

import java.awt.Color;
import java.util.*;

import Data.Box;
import Data.Vector2D;
import Data.spriteInfo;
import logic.Control;
import timer.stopWatchX;

public class Main{
	// Fields (Static) below...
	public static int i = 0; //increment for switch statements
	public static int x = 100; //x coordinate of cat
	public static int y = 100; //y coordinate of cat
	public static boolean collide = false; //collision boolean used for item interaction
	public static String activate = ""; //dialog when activating an item
	public static int collideTag = 0; //tag used to determine given item
	public static String tag = "s1"; //tag for the cat
	public static String trigger = ""; //holds trigger of KeyProcessor
	public static Color c = new Color(0, 0, 0); //color of text
	public static stopWatchX timer = new stopWatchX(75); //tick speed of animation
	public static ArrayList<spriteInfo> spritesw = new ArrayList<>(); //holds w key walking animations
	public static ArrayList<spriteInfo> spritess = new ArrayList<>(); //holds s key walking animations
	public static ArrayList<spriteInfo> spritesa = new ArrayList<>(); //holds a key walking animations
	public static ArrayList<spriteInfo> spritesd = new ArrayList<>(); //holds d key walking animations
	public static ArrayList<Box> boxes = new ArrayList<>(); //holds box objects
	public static Vector2D initCoords = new Vector2D(x,y); //initial coords for walking animations

	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();	// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(){
		// TODO: Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)
		for(int k = 1; k < 6; k++)
		{
			spritesw.add(new spriteInfo(initCoords, "w" + k));
			spritesa.add(new spriteInfo(initCoords, "a" + k));
			spritess.add(new spriteInfo(initCoords, "s" + k));
			spritesd.add(new spriteInfo(initCoords, "d" + k));
		}
		
		boxes.add(new Box(x, x+128, y, y+128)); //cat
		boxes.add(new Box(0, 1280, 10, 40)); //top border
		boxes.add(new Box(0, 1280, 680, 710)); //bottom border
		boxes.add(new Box(10, 40, 50, 670)); //left border
		boxes.add(new Box(1230, 1270, 50, 670)); //right border
		boxes.add(new Box(620, 680, 320, 350)); //chest
		boxes.add(new Box(110, 170, 480, 500)); //fossil

	}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)
	    
		
		ctrl.drawString(x-15, y-10, activate, c); //displays the active string
		
		//add the background
	    ctrl.addSpriteToFrontBuffer(0, 0, "bg");
	    ctrl.addSpriteToFrontBuffer(0, 0, "top");
	    ctrl.addSpriteToFrontBuffer(0, 670, "bottom");
	    ctrl.addSpriteToFrontBuffer(0, 50, "left");
	    ctrl.addSpriteToFrontBuffer(1230, 50, "right");
	   	    
	    //add the items
	    ctrl.addSpriteToFrontBuffer(600, 300, "chest");
	    ctrl.addSpriteToFrontBuffer(100, 450, "fossil");
	    
	    //add the cat
	   	ctrl.addSpriteToFrontBuffer(x, y, tag);

	    
	    if(timer.isTimeUp())
	    {
	    	switch(trigger){	    	
	    	
	    	case "W":
	    		activate = "";
	    		y-=8;
	    		updateCat();
	    		
	    		if(testCollision() == true)
	    		{
	    			y+=8;
	    			updateCat();
	    		}
	    		
	    		if (i < 5)
	    		{
	    			tag = spritesw.get(i).getTag();
	    			i++;
	    		}
	    		
	    		else 
	    		{
	    			i = 0;
	    			tag = spritesw.get(i).getTag();
	    		}
	    		
	    		break;
	    		
	    	case "A":
	    		activate = "";
	    		x-=8;
	    		updateCat();
	    		
	    		if(testCollision())
	    		{
	    			x+=8;
	    			updateCat();
	    		}
	    		
	    		if (i < 5)
	    		{
	    			tag = spritesa.get(i).getTag();
	    			i++;
	    		}
	    		
	    		else 
	    		{
	    			i = 0;
	    			tag = spritesa.get(i).getTag();
	    		}
	    		
	    		break;
	    		
	    	case "S":
	    		activate = "";
	    		y+=8;
	    		updateCat();
	    		
	    		if(testCollision())
	    		{
	    			y-=8;
	    			updateCat();
	    		}
	    		
	    		if (i < 5)
	    		{
	    			tag = spritess.get(i).getTag();
	    			i++;
	    		}
	    		
	    		else 
	    		{
	    			i = 0;
	    			tag = spritess.get(i).getTag();
	    		}
	    		break;
	    		
	    	case "D":
	    		activate = "";
	    		x+=8;
	    		updateCat();
	    		
	    		if(testCollision())
	    		{
	    			x-=8;
	    			updateCat();

	    		}
	    		
	    		if (i < 5)
	    		{
	    			tag = spritesd.get(i).getTag();
					i++;
	    		}
	    		
	    		else 
	    		{
	    			i = 0;
	    			tag = spritesd.get(i).getTag();
	    		}
	    		
	    		break;
	    		
	    	case "$":
	    		if (collide)
	    		{
	    			if (collideTag == 5) //5 is index of chest
	    			{
	    				activate = "We found the catnip";
	    			}
	    			
	    			if (collideTag == 6) //6 is index of fossil
	    			{
	    				activate = "This is an old bird";
	    			}
	    		}
	    		
	    		break;
  	
	    	}
	    timer.resetWatch();
	    }
	}
	
	// Additional Static methods below...(if needed)
	
	//tests to see if a collision occurs
	public static boolean testCollision()
	{
		collide = false;
		collideTag = 0;
		for (int k = 1; k < boxes.size(); k++)
		{
			if(!boxes.get(0).collision(boxes.get(k)))
			{
				collide = true;
				collideTag = k;
				k+=boxes.size();
			}	
		}
		return collide;
	}
	
	//simplifies updating the cat
	public static void updateCat()
	{
		boxes.remove(0);
		boxes.add(0, new Box(x, x+128, y, y+128));
	}

}
