import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

/**
 * Hello World!
 * 
 * This is the basic stub to start creating interactive maps.
 */
public class HelloUCSDWorld extends PApplet {

	UnfoldingMap map;
    public void settings(){
        size(800, 600);
    }

	public void setup() {
		

		map = new UnfoldingMap(this, new Microsoft.RoadProvider());
		map.zoomAndPanTo(14, new Location(32.881, -117.238)); // UCSD

		MapUtils.createDefaultEventDispatcher(this, map);
	}

	public void draw() {
		background(0);
		map.draw();
	}
	static public void main(String[] passedArgs) {
		String[] appletArgs = new String[] { "HelloUCSDWorld"};
		if (passedArgs != null) {
		  PApplet.main(concat(appletArgs, passedArgs));
		} else {
		  PApplet.main(appletArgs);
		}
	  }
}