package core;

public class Point {

	public Point(Double[] coordinates, Double value) {
		this.setCoordinates(coordinates);
		this.setValue(value);
	}
	
	public Double[] getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Double[] coordinates) {
		this.coordinates = coordinates;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
	public String toString() {
		String point = "";
		point += "[";
		for(int i = 0; i < coordinates.length; i++) {
			point += coordinates[i]+" ";
		}
		point += "]\n";
		point+=value;
		
		return point;
	}

	private Double[] coordinates;
	private Double value;
}
