package main;

import aima.core.environment.map.ExtendableMap;

public class SimplifiedRoadOfSpain extends ExtendableMap{

	public SimplifiedRoadOfSpain(){
		initMap(this);
	}
	
	//Locations ---------------------------------------------
	//Castilla-Leon -----------------------------------------
	public static final String AVILA = "�vila";
	public static final String BURGOS = "Burgos";
	public static final String LEON= "Le�n";
	public static final String PALENCIA = "Palencia";
	public static final String SALAMANCA = "Salamanca";
	public static final String SEGOVIA = "Segovia";
	public static final String SORIA = "Soria";
	public static final String VALLADOLID = "Valladolid";
	public static final String ZAMORA = "Zamora";
	//Castilla-La Mancha ------------------------------------
	public static final String ALBACETE = "Albacete";
	public static final String CIUDADREAL = "Ciudad Real";
	public static final String CUENCA = "Cuenca";
	public static final String GUADALAJARA = "Guadalajara";
	public static final String TOLEDO = "Toledo";
	//Madrid ------------------------------------------------
	public static final String MADRID = "Madrid";
	
	public static void initMap(ExtendableMap map) {
		map.clear();
		
		map.setPosition(AVILA,-82.418, -28.751);
		map.setPosition(BURGOS, 2.304, -213.951);
		map.setPosition(LEON,-150.894, -245.196);
		map.setPosition(PALENCIA, -66.886, -177.728);
		map.setPosition(SALAMANCA,-164.412, -64.601);
		map.setPosition(SEGOVIA,-33.625, -58.756);
		map.setPosition(SORIA,103.011, -149.739);
		map.setPosition(VALLADOLID,-83.891, -138.349);
		map.setPosition(ZAMORA,-169.545, -124.038);
		map.setPosition(ALBACETE,158.559, 157.485);
		map.setPosition(CIUDADREAL,-20.613, 158.752);
		map.setPosition(CUENCA,133.263, 38.328);
		map.setPosition(GUADALAJARA,46.163, -23.719);
		map.setPosition(TOLEDO,-28.16, 61.217);
		map.setPosition(MADRID, 0, 0);
		
		map.addBidirectionalLink(SALAMANCA,VALLADOLID, 119.0);
		map.addBidirectionalLink(SALAMANCA,ZAMORA, 66.1);
		map.addBidirectionalLink(ZAMORA,VALLADOLID, 101.0);
		map.addBidirectionalLink(SALAMANCA, AVILA, 109.0);
		map.addBidirectionalLink(AVILA, SEGOVIA, 64.3);
		map.addBidirectionalLink(SEGOVIA, VALLADOLID, 118.0);
		map.addBidirectionalLink(VALLADOLID, PALENCIA, 50.8);
		map.addBidirectionalLink(PALENCIA, BURGOS, 91.6);
		map.addBidirectionalLink(BURGOS, LEON, 173.0);
		map.addBidirectionalLink(LEON, ZAMORA, 140.0);
		map.addBidirectionalLink(LEON, VALLADOLID, 137.0);
		map.addBidirectionalLink(SEGOVIA, BURGOS, 201.0);
		map.addBidirectionalLink(BURGOS, SORIA, 142.0);
		map.addBidirectionalLink(SEGOVIA, SORIA, 189.0);
		map.addBidirectionalLink(SORIA, GUADALAJARA, 172.0);
		map.addBidirectionalLink(MADRID, GUADALAJARA, 59.5);
		map.addBidirectionalLink(MADRID, SEGOVIA, 93.0);
		map.addBidirectionalLink(MADRID, AVILA, 109.0);
		map.addBidirectionalLink(MADRID, TOLEDO, 72.4);
		map.addBidirectionalLink(MADRID, CUENCA, 165.0);
		map.addBidirectionalLink(CUENCA, GUADALAJARA, 136.0);
		map.addBidirectionalLink(TOLEDO, CUENCA, 182.0);
		map.addBidirectionalLink(TOLEDO, CIUDADREAL, 117.0);
		map.addBidirectionalLink(CIUDADREAL, ALBACETE, 217.0);
		map.addBidirectionalLink(ALBACETE, CUENCA, 141.0);
		
	}
				
}
