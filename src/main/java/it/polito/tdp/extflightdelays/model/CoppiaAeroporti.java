package it.polito.tdp.extflightdelays.model;

public class CoppiaAeroporti {

	private Airport aeroportoA;
	private Airport aeroportoB;
	private float avgDistanza;
	
	public CoppiaAeroporti(Airport aeroportoA, Airport aeroportoB, float avgDistanza) {
		super();
		this.aeroportoA = aeroportoA;
		this.aeroportoB = aeroportoB;
		this.avgDistanza = avgDistanza;
	}
	
	public float getAvgDistanza() {
		return avgDistanza;
	}
	public void setAvgDistanza(float avgDistanza) {
		this.avgDistanza = avgDistanza;
	}
	public Airport getAeroportoA() {
		return aeroportoA;
	}
	public void setAeroportoA(Airport aeroportoA) {
		this.aeroportoA = aeroportoA;
	}
	public Airport getAeroportoB() {
		return aeroportoB;
	}
	public void setAeroportoB(Airport aeroportoB) {
		this.aeroportoB = aeroportoB;
	}
	
}
