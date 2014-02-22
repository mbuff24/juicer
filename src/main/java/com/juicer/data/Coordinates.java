package com.juicer.data;

import java.util.List;

public class Coordinates {

		private String type;
		private List<Double> coordinates;
		
		public Coordinates() {}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public List<Double> getCoordinates() {
			return coordinates;
		}

		public void setCoordinates(List<Double> coordinates) {
			this.coordinates = coordinates;
		}

		public String getCoordsAsString() {
			if(coordinates != null && coordinates.size() == 2) {
				return "(" + coordinates.get(0) + "," + coordinates.get(1) + ")";
			}
			return null;
		}
		
		@Override
		public String toString() {
			return "Coordinates [type=" + type + ", coordinates=" + coordinates
					+ "]";
		}
}
