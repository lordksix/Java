package pasquel.module1;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

/** HelloWorld
* An application with two maps side-by-side zoomed in on different locations.
 * Author: UC San Diego Coursera Intermediate Programming team
 * @author wLADIMIR Pasquel
 * Date: July 17, 2015
 * Date: 01/12/2022
 * * */
public class HelloWorld extends PApplet
{
	
	/** Your goal: add code to display second map, zoom in, and customize the background.
	 * Feel free to copy and use this code, adding to it, modifying it, etc.  
	 * Don't forget the import lines above. */

	// You can ignore this.  It's to keep eclipse from reporting a warning
	private static final long serialVersionUID = 1L;

	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	
	// IF YOU ARE WORKING OFFLINE: Change the value of this variable to true
	private static final boolean offline = false;
	
	/** The map we use to display our home town: La Jolla, CA */
	UnfoldingMap map1;
	
	/** The map you will use to display your home town */ 
	UnfoldingMap map2;
    public void settings(){
        size(800, 600,JAVA2D);// Set up the Applet window to be 800x600 The OPENGL argument indicates to use the 
        // Processing library's 2D drawing  You'll learn more about processing in Module 3
    }
	public void setup() { 

		// This sets the background color for the Applet. Play around with these numbers and see what happens!
		this.background(200, 200, 200);		
		AbstractMapProvider provider = new Microsoft.RoadProvider();// Select a map provider		
		int zoomLevel = 10; // Set a zoom level		
		if (offline) {
			// If you are working offline, you need to use this provider to work with the maps that are local on your computer.  
			provider = new MBTilesMapProvider(mbTilesString);
			// 3 is the maximum zoom level for working offline
			zoomLevel = 3;
		}
		settings();
		// Create a new UnfoldingMap to be displayed in this window.  The 2nd-5th arguments give the map's x, y, width and height
		// When you create your map we want you to play around with these arguments to get your second map in the right place.
		// The 6th argument specifies the map provider. There are several providers built-in.
		// Note if you are working offline you must use the MBTilesMapProvider
		map1 = new UnfoldingMap(this,"1",50, 50, 350, 500, true, false, provider, null);
		map2 = new UnfoldingMap(this,"2",400, 50,350,500, true, false, provider, null);

		// The next line zooms in and centers the map at 
	    // 32.9 (latitude) and -117.2 (longitude)
	    map1.zoomAndPanTo(zoomLevel, new Location(32.9f, -117.2f));
	    map2.zoomAndPanTo(zoomLevel+1, new Location(-12.1f, -77.0f));
		// This line makes the map interactive
		MapUtils.createDefaultEventDispatcher(this, map1,map2);

	}

	public void draw() {
		map1.draw();
		map2.draw();
	}
	static public void main(String[] passedArgs) {
		String[] appletArgs = new String[] { "pasquel.module1.HelloWorld"};
		if (passedArgs != null) {
		  PApplet.main(concat(appletArgs, passedArgs));
		} else {
		  PApplet.main(appletArgs);
		}
	}
}
