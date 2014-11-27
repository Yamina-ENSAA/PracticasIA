package aima.core.environment.spainmap;

import aima.core.util.datastructure.XYLocation;

public class City {

	private String _name;
	private XYLocation _xy;
	
	/**
	 * Default constructor, shouldn't be used.
	 */
	public City(){
		_name = "";
		_xy = new XYLocation(0,0);
	}
	
	/** Parameter constructor.
	 * @param name Name of the city
	 * @param xCoord X Coordenate (offset from madrid)
	 * @param yCoord Y Coordenate (offset from madrid)
	 */
	public City(String name, int xCoord, int yCoord){
		_name = name;
		_xy = new XYLocation(xCoord,yCoord);
	}
	
	/** Location getter
	 * @return location
	 */
	public XYLocation getLocation(){
		return _xy;
	}
	
	/** Calculates the distance in a straight line from the object city to the parameter city.
	 * @param city The one we want to calculate
	 * @return (double) Distance
	 */
	public double straightLineDistance(City city){		
		return  java.lang.Math.sqrt(( (_xy.getXCoOrdinate()-city.getLocation().getXCoOrdinate())^2) + (_xy.getYCoOrdinate()-city.getLocation().getYCoOrdinate())^2);
	}
	
}