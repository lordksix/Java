package pasquel.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.data.ShapeFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.AbstractShapeMarker;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.MultiMarker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;
import pasquel.parsing.ParseFeed;
import processing.core.PApplet;
import processing.core.PConstants;

/** EarthquakeCityMap
 * An application with an interactive map displaying earthquake data.
 * Author: UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 * Date: July 17, 2015
 * */
public class EarthquakeCityMap extends PApplet {
	
	private static final long serialVersionUID = 1L; // You can ignore this.  It's to get rid of eclipse warnings

	private static final boolean offline = false; // IF YOU ARE WORKING OFFILINE, change the value of this variable to true
	
	public static String mbTilesString = "blankLight-1-3.mbtiles"; /** This is where to find the local tiles, for working without an Internet connection */

	private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom"; //feed with magnitude 2.5+ Earthquakes
	
	private String cityFile = "city-data.json"; // The files containing city names and info and country names and info
	private String countryFile = "countries.geo.json";
	
	// The map
	private UnfoldingMap mapEQ;
	private UnfoldingMap mapAir;
	UnfoldingMap mapLife;

	HashMap<String, Float> lifeExpMap;
	List<Feature> countries;

	private List<Marker> airportList;
	List<Marker> routeList;
	
	private List<Marker> cityMarkers;
	private List<Marker> quakeMarkers;

	private List<Marker> countryMarkers;
	private List<Marker> countryMarkersLife;
	
	private CommonMarker lastSelected;
	private CommonMarker lastClicked;

	private String  menu = "prinInterface";
	
	private int xbase = 25,ybase = 70;	
	private int  wWidth = 900,wHeight = 700;
	
	private int rectX, rectY;
	private int rectSize = 20;
	private boolean rectOver = false, rect2Over = false;
	private int rectColor,  baseColor,rectHighlight, rect2Highlight, currentColor, rect2Color;
	
	public void setup() {		
		// (1) Initializing canvas and map tiles
		size(wWidth, wHeight, OPENGL);
		if (offline) {
		    mapEQ = new UnfoldingMap(this, 200, 70, 650, 600, new MBTilesMapProvider(mbTilesString));
		    earthquakesURL = "2.5_week.atom";  // The same feed, but saved August 7, 2015
			mapAir = new UnfoldingMap(this, 200, 70, 650, 600,new MBTilesMapProvider(mbTilesString));
			mapLife = new UnfoldingMap(this, 200, 70, 650, 600, new MBTilesMapProvider(mbTilesString));
		}
		else {
			mapEQ = new UnfoldingMap(this, 200, 70, 650, 600, new Google.GoogleMapProvider());
			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
		    //earthquakesURL = "2.5_week.atom";
			mapAir = new UnfoldingMap(this, 200, 70, 650, 600,new Google.GoogleMapProvider());
			mapLife = new UnfoldingMap(this, 200, 70, 650, 600, new Google.GoogleMapProvider());
		}

		MapUtils.createDefaultEventDispatcher(this, mapEQ);
		MapUtils.createDefaultEventDispatcher(this, mapAir);
		MapUtils.createDefaultEventDispatcher(this, mapLife);

		List<Feature> countries = GeoJSONReader.loadData(this, countryFile);
		List<Feature> countriesLife = GeoJSONReader.loadData(this, countryFile);
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		countryMarkersLife = MapUtils.createSimpleMarkers(countriesLife);
		lifeExpMap = ParseFeed.loadLifeExpectancyFromCSV(this,"LifeExpectancyWorldBank.csv");
		
		List<Feature> cities = GeoJSONReader.loadData(this, cityFile);
		cityMarkers = new ArrayList<Marker>();
		for(Feature city : cities) { cityMarkers.add(new CityMarker(city));}

	    List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
	    quakeMarkers = new ArrayList<Marker>();
	    
	    for(PointFeature feature : earthquakes) {	 
		  if(isLand(feature)) { //check if LandQuake
		    quakeMarkers.add(new LandQuakeMarker(feature));
		  }		  
		  else {// OceanQuakes
		    quakeMarkers.add(new OceanQuakeMarker(feature));
		  }
	    }
	    mapEQ.addMarkers(quakeMarkers);
	    mapEQ.addMarkers(cityMarkers);	
		
		List<PointFeature> features = ParseFeed.parseAirports(this, "airports.dat");
		
		airportList = new ArrayList<Marker>(); // list for markers, hashmap for quicker access when matching with routes
		HashMap<Integer, Location> airports = new HashMap<Integer, Location>();
		
		for(PointFeature feature : features) { // create markers from features
			AirportMarker m = new AirportMarker(feature);	
			m.setRadius(5);
			airportList.add(m); // put airport in hashmap with OpenFlights unique id for key
			airports.put(Integer.parseInt(feature.getId()), feature.getLocation());
		}
				
		List<ShapeFeature> routes = ParseFeed.parseRoutes(this, "routes.dat");// parse route data
		routeList = new ArrayList<Marker>();
		for(ShapeFeature route : routes) {			
			int source = Integer.parseInt((String)route.getProperty("source")); // get source and destination airportIds
			int dest = Integer.parseInt((String)route.getProperty("destination"));
			if(airports.containsKey(source) && airports.containsKey(dest)) { // get locations for airports on route
				route.addLocation(airports.get(source));
				route.addLocation(airports.get(dest));
			}
			
			SimpleLinesMarker sl = new SimpleLinesMarker(route.getLocations(), route.getProperties());
		
			//System.out.println(sl.getProperties());		
			//UNCOMMENT IF YOU WANT TO SEE ALL ROUTES
			//routeList.add(sl);
		}

		//UNCOMMENT IF YOU WANT TO SEE ALL ROUTES
		//map.addMarkers(routeList);
		
		mapAir.addMarkers(airportList);

		mapLife.addMarkers(countryMarkersLife);
		shadeCountries();
		rectColor = color(0);
		rect2Color = color(255,0,0);
		rect2Highlight = color(225,0,0);
		rectHighlight = color(51);
		baseColor = color(102);
		currentColor = baseColor;
		rectX = 70-rectSize-10;
		rectY = wHeight-100-rectSize/2;
	}  // End setup
	
	
	public void draw() {
		background(currentColor);

		
		
		if ( menu == "prinInterface" ) {
			menuInterface();
		}
		else if ( menu == "earthquake" ) {
			mapEQ.draw();
			addKeyEQ();
		}
        else if ( menu == "airport" ) {
			mapAir.draw();
			addKeyAir();
		}
		else if ( menu == "lifeexpectency" ) {
			mapLife.draw();
		}
		if (rectOver) {fill(rectHighlight);} 
		else {fill(rectColor);}
		stroke(255);
		rect(rectX, rectY, rectSize, rectSize,5);
		if (rect2Over) {fill(rect2Highlight);} 
		else {fill(rect2Color);}
		stroke(255);
		rect(rectX+rectSize*2, rectY, rectSize, rectSize,5);
		
	}
	
	boolean overRect(int x, int y, int width, int height)  {
		  if (mouseX >= x && mouseX <= x+width && 
		      mouseY >= y && mouseY <= y+height) {
		    return true;
		  } else {
		    return false;
		  }
		}
	void update(int x, int y) {
		if ( overRect(rectX, rectY, rectSize, rectSize) ) {
		    rectOver = true;}
		else if ( overRect(rectX+2*rectSize, rectY, rectSize, rectSize) ) {
		    rect2Over = true;
		} else {
			rectOver =false;
			rect2Over = false;
		}
	}
	
	private void sortAndPrint(int numToPrint){
		List<EarthquakeMarker> listEQ = new ArrayList<>();
		for(Marker m: quakeMarkers){
			listEQ.add((EarthquakeMarker) m);
		}
		System.out.println("sortAndPrint");
		Collections.sort(listEQ);
		for (int i = 0; i < Math.min(listEQ.size(),numToPrint); i++) {
			System.out.println(listEQ.get(i));
		}
	}
	
	/** Event handler that gets called automatically when the 
	 * mouse moves.
	 */
	@Override
	public void mouseMoved()
	{
		// clear the last selection
		if (lastSelected != null) {
			lastSelected.setSelected(false);
			lastSelected = null;		
		}
		selectMarkerIfHover(quakeMarkers);
		selectMarkerIfHover(cityMarkers);
		update(mouseX, mouseY);
	}
	
	// If there is a marker selected 
	private void selectMarkerIfHover(List<Marker> markers)
	{// Abort if there's already a marker selected		
		if (lastSelected != null) {return;}	
		for (Marker m : markers) 
		{
			CommonMarker marker = (CommonMarker)m;
			if (marker.isInside(mapEQ,  mouseX, mouseY)) {
				lastSelected = marker;
				marker.setSelected(true);
				return;
			}
		}
	}
	
	/** The event handler for mouse clicks
	 * It will display an earthquake and its threat circle of cities
	 * Or if a city is clicked, it will display all the earthquakes 
	 * where the city is in the threat circle
	 */
	@Override
	public void mouseClicked()
	{
		if (lastClicked != null) {
			unhideMarkers();
			lastClicked = null;
		}
		else if (lastClicked == null) 
		{
			checkEarthquakesForClick();
			if (lastClicked == null) {
				checkCitiesForClick();
			}
		}
		if (rectOver) {currentColor = rectColor;}
		if (rect2Over) {currentColor = rect2Color;}
	}
	
	// Helper method that will check if a city marker was clicked on
	// and respond appropriately
	private void checkCitiesForClick()
	{
		if (lastClicked != null) return;
		// Loop over the earthquake markers to see if one of them is selected
		for (Marker marker : cityMarkers) {
			if (!marker.isHidden() && marker.isInside(mapEQ, mouseX, mouseY)) {
				lastClicked = (CommonMarker)marker;
				// Hide all the other earthquakes and hide
				for (Marker mhide : cityMarkers) {
					if (mhide != lastClicked) {
						mhide.setHidden(true);
					}
				}
				for (Marker mhide : quakeMarkers) {
					EarthquakeMarker quakeMarker = (EarthquakeMarker)mhide;
					if (quakeMarker.getDistanceTo(marker.getLocation())> quakeMarker.threatCircle()) {
						quakeMarker.setHidden(true);
					}
				}
				return;
			}
		}		
	}
	
	// Helper method that will check if an earthquake marker was clicked on
	// and respond appropriately
	private void checkEarthquakesForClick()
	{
		if (lastClicked != null) return;
		// Loop over the earthquake markers to see if one of them is selected
		for (Marker m : quakeMarkers) {
			EarthquakeMarker marker = (EarthquakeMarker)m;
			if (!marker.isHidden() && marker.isInside(mapEQ, mouseX, mouseY)) {
				lastClicked = marker;
				// Hide all the other earthquakes and hide
				for (Marker mhide : quakeMarkers) {
					if (mhide != lastClicked) {
						mhide.setHidden(true);
					}
				}
				for (Marker mhide : cityMarkers) {
					if (mhide.getDistanceTo(marker.getLocation())> marker.threatCircle()) {
						mhide.setHidden(true);
					}
				}
				return;
			}
		}
	}
	
	// loop over and unhide all markers
	private void unhideMarkers() {
		for(Marker marker : quakeMarkers) {
			marker.setHidden(false);
		}
			
		for(Marker marker : cityMarkers) {
			marker.setHidden(false);
		}
	}
	private void addBG(String title) {


		fill(255, 250, 240);
		textSize(24);		
		Float x = wWidth/2- textWidth(title)/2-40;
		Float y = 35 -16.0f;
		rectMode(PConstants.CORNER);
		rect(x, y, textWidth(title)+80, 36,32);		
		stroke(110);
		fill(255,255,255);		
		textAlign(PConstants.LEFT, PConstants.TOP);
		fill(0);
		text(title, x + 40 , y+4);		
	}
	
	private void addKeyAir() {	
		// Remember you can use Processing's graphics methods here
		
		String title = "Map of Airports";
		addBG(title);
		fill(255, 250, 240);
		rect(xbase, ybase, 150, 250,10);
		fill(0);
		textAlign(LEFT, CENTER);
		textSize(12);
		text("Earthquake Key", xbase+25, ybase+25);
		
		fill(150, 30, 30);
		int tri_xbase = xbase + 35;
		int tri_ybase = ybase + 50;
		triangle(tri_xbase, tri_ybase-CityMarker.TRI_SIZE, tri_xbase-CityMarker.TRI_SIZE, 
				tri_ybase+CityMarker.TRI_SIZE, tri_xbase+CityMarker.TRI_SIZE, 
				tri_ybase+CityMarker.TRI_SIZE);

		fill(0, 0, 0);
		textAlign(LEFT, CENTER);
		text("City Marker", tri_xbase + 15, tri_ybase);
		
		text("Land Quake", xbase+50, ybase+70);
		text("Ocean Quake", xbase+50, ybase+90);
		text("Size ~ Magnitude", xbase+25, ybase+110);
		
		fill(255, 255, 255);
		ellipse(xbase+35,ybase+70,10,10);
		rect(xbase+35-5, ybase+90-5, 10, 10);
		
		fill(color(255, 255, 0));
		ellipse(xbase+35, ybase+140, 12, 12);
		fill(color(0, 0, 255));
		ellipse(xbase+35, ybase+160, 12, 12);
		fill(color(255, 0, 0));
		ellipse(xbase+35, ybase+180, 12, 12);
		
		textAlign(LEFT, CENTER);
		fill(0, 0, 0);
		text("Shallow", xbase+50, ybase+140);
		text("Intermediate", xbase+50, ybase+160);
		text("Deep", xbase+50, ybase+180);

		text("Past hour", xbase+50, ybase+200);
		
		fill(255, 255, 255);
		int centerx = xbase+35;
		int centery = ybase+200;
		ellipse(centerx, centery, 12, 12);

		strokeWeight(2);
		line(centerx-8, centery-8, centerx+8, centery+8);
		line(centerx-8, centery+8, centerx+8, centery-8);
	}
	
	// helper method to draw key in GUI
	private void addKeyEQ() {	
		// Remember you can use Processing's graphics methods here
		String title = "Map of Earthquakes";
		addBG(title);
		fill(255, 250, 240);
		rect(xbase, ybase, 150, 250,10);
		fill(0);
		textAlign(LEFT, CENTER);
		textSize(12);
		text("Earthquake Key", xbase+25, ybase+25);
		
		fill(150, 30, 30);
		int tri_xbase = xbase + 35;
		int tri_ybase = ybase + 50;
		triangle(tri_xbase, tri_ybase-CityMarker.TRI_SIZE, tri_xbase-CityMarker.TRI_SIZE, 
				tri_ybase+CityMarker.TRI_SIZE, tri_xbase+CityMarker.TRI_SIZE, 
				tri_ybase+CityMarker.TRI_SIZE);

		fill(0, 0, 0);
		textAlign(LEFT, CENTER);
		text("City Marker", tri_xbase + 15, tri_ybase);
		
		text("Land Quake", xbase+50, ybase+70);
		text("Ocean Quake", xbase+50, ybase+90);
		text("Size ~ Magnitude", xbase+25, ybase+110);
		
		fill(255, 255, 255);
		ellipse(xbase+35,ybase+70,10,10);
		rect(xbase+35-5, ybase+90-5, 10, 10);
		
		fill(color(255, 255, 0));
		ellipse(xbase+35, ybase+140, 12, 12);
		fill(color(0, 0, 255));
		ellipse(xbase+35, ybase+160, 12, 12);
		fill(color(255, 0, 0));
		ellipse(xbase+35, ybase+180, 12, 12);
		
		textAlign(LEFT, CENTER);
		fill(0, 0, 0);
		text("Shallow", xbase+50, ybase+140);
		text("Intermediate", xbase+50, ybase+160);
		text("Deep", xbase+50, ybase+180);

		text("Past hour", xbase+50, ybase+200);
		
		fill(255, 255, 255);
		int centerx = xbase+35;
		int centery = ybase+200;
		ellipse(centerx, centery, 12, 12);

		strokeWeight(2);
		line(centerx-8, centery-8, centerx+8, centery+8);
		line(centerx-8, centery+8, centerx+8, centery-8);
	}

	private void menuInterface(){
		addBG("Application of Maps");
	}	
	
	public void keyPressed() {
		if ( menu == "prinInterface" ) {
			if ( key == 'e' ) menu = "earthquake";
			else if ( key == 'a' ) menu = "airport";
			else if ( key == 'l' ) menu = "lifeexpectency";
		}
		else if ( menu == "earthquake" ) {
			if ( key == 'm' ) menu = "prinInterface";
			else if ( key == 'p' ) printQuakes();
			else if ( key == 't' ) sortAndPrint(10);
		}
        else if ( menu == "airport" ) {
			if ( key == 'm' ) menu = "prinInterface";			
		}
		else if ( menu == "lifeexpectency" ) {
			if ( key == 'm' ) menu = "prinInterface";
			else if ( key == 'p' ) System.out.println(countryMarkers.get(0).getId());
		}
    }


	// Checks whether this quake occurred on land.  If it did, it sets the 
	// "country" property of its PointFeature to the country where it occurred
	// and returns true.  Notice that the helper method isInCountry will
	// set this "country" property already.  Otherwise it returns false.
	private boolean isLand(PointFeature earthquake) {
		for (Marker country : countryMarkers) {
			if (isInCountry(earthquake, country)) {
				return true;
			}
		}
		return false;
	}
	
	// prints countries with number of earthquakes
	// You will want to loop through the country markers or country features
	// (either will work) and then for each country, loop through
	// the quakes to count how many occurred in that country.
	// Recall that the country markers have a "name" property, 
	// And LandQuakeMarkers have a "country" property set.
	private void printQuakes() {
		int totalWaterQuakes = quakeMarkers.size();
		for (Marker country : countryMarkers) {
			String countryName = country.getStringProperty("name");
			int numQuakes = 0;
			for (Marker marker : quakeMarkers)
			{
				EarthquakeMarker eqMarker = (EarthquakeMarker)marker;
				if (eqMarker.isOnLand()) {
					if (countryName.equals(eqMarker.getStringProperty("country"))) {
						numQuakes++;
					}
				}
			}
			if (numQuakes > 0) {
				totalWaterQuakes -= numQuakes;
				System.out.println(countryName + ": " + numQuakes);
			}
		}
		System.out.println("OCEAN QUAKES: " + totalWaterQuakes);
	}	
	
	private void shadeCountries() {
		for (Marker marker : countryMarkersLife) {
			// Find data for country of the current marker
			String countryId = marker.getId();
			//System.out.println(lifeExpMap.containsKey(countryId));
			if (lifeExpMap.containsKey(countryId)) {
				float lifeExp = lifeExpMap.get(countryId);
				// Encode value as brightness (values range: 40-90)
				int colorLevel = (int) map(lifeExp, 40, 90, 10, 255);
				marker.setColor(color(255-colorLevel, 100, colorLevel));
			}
			else {
				marker.setColor(color(150,150,150));
			}
		}
	}
	// helper method to test whether a given earthquake is in a given country
	// This will also add the country property to the properties of the earthquake feature if 
	// it's in one of the countries.
	// You should not have to modify this code
	private boolean isInCountry(PointFeature earthquake, Marker country) {
		// getting location of feature
		Location checkLoc = earthquake.getLocation();

		// some countries represented it as MultiMarker
		// looping over SimplePolygonMarkers which make them up to use isInsideByLoc
		if(country.getClass() == MultiMarker.class) {
				
			// looping over markers making up MultiMarker
			for(Marker marker : ((MultiMarker)country).getMarkers()) {
					
				// checking if inside
				if(((AbstractShapeMarker)marker).isInsideByLocation(checkLoc)) {
					earthquake.addProperty("country", country.getProperty("name"));
						
					// return if is inside one
					return true;
				}
			}
		}
			
		// check if inside country represented by SimplePolygonMarker
		else if(((AbstractShapeMarker)country).isInsideByLocation(checkLoc)) {
			earthquake.addProperty("country", country.getProperty("name"));
			
			return true;
		}
		return false;
	}
    static public void main(String[] passedArgs) {
        String[] appletArgs = new String[] { "pasquel.module4.EarthquakeCityMap"};
        if (passedArgs != null) {
          PApplet.main(concat(appletArgs, passedArgs));
        } else {
          PApplet.main(appletArgs);
        }
    }

}
