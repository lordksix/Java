package pasquel.guimodule;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class EarthquakeCityMapDemo extends PApplet{
  private UnfoldingMap map;
  public void settings(){size(950,600);}

  public void setup(){
    AbstractMapProvider provider = new Microsoft.RoadProvider();
    map = new UnfoldingMap(this,200, 50,700, 500, provider,P2D);
    map.zoomToLevel(2);
    MapUtils.createDefaultEventDispatcher(this,map);
    Location valLoc = new Location(-38.14f,-73.03f);
    Marker val = new SimplePointMarker(valLoc);
  	map.addMarker(val);
  }
  public void draw() {
    background(10);
    map.draw();
    addKey();
  }
  private void addKey() 
	{	
		// Remember you can use Processing's graphics methods here
	
	}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "pasquel.guimodule.EarthquakeCityMapDemo"};
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
