package pasquel.guimodule;

import processing.core.PApplet;

public class MyDisplay extends PApplet {
	public void settings(){
        size(400, 400);
		
    }
	public void setup()
	{
		surface.setTitle("Hello World!");
		background(200, 200, 200);
	}
	
	public void draw()
	{
		fill(255, 255, 0);
		ellipse(200, 200, 390, 390);
		fill(0, 0, 0);
		ellipse(120, 130, 50, 70);
		ellipse(280, 130, 50, 70);
		
		noFill();
		arc(200, 280, 75, 75, 0, PI);
	}
    static public void main(String[] passedArgs) {
		String[] appletArgs = new String[] { "pasquel.guimodule.MyDisplay"};
		if (passedArgs != null) {
		  PApplet.main(concat(appletArgs, passedArgs));
		} else {
		  PApplet.main(appletArgs);
		}
	  }
}
