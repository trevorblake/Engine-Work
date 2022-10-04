package Data;

public class Box {
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	

	public Box(int x1, int x2, int y1, int y2){
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	// Methods
	public int getX1(){
		return x1;
	}
	
	public int getX2(){
		return x2;
	}
	
	public int getY1(){
		return y1;
	}
	
	public int getY2(){
		return y2;
	}
	
	public void setX1(int newX){
		x1 = newX;
	}
	
	public void setX2(int newX){
		x2 = newX;
	}
	
	
	public void setY1(int newY){
		y1 = newY;
	}
	
	public void setY2(int newY){
		y2 = newY;
	}
	
	//checks if this box is within argument box
	public boolean collision(Box box2)
	{
		if(this.x1 > box2.getX2() || this.x2 < box2.getX1() || this.y1 > box2.getY2() || this.y2 < box2.getY1())
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	
	
}


