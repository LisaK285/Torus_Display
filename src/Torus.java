
public class Torus {
	
	private static int numCircles = 10;
	private static int pointsPerCircle = 5;
	
	//getter
	public int getNumCircles() {return numCircles;}
	public int getCoordinatePointsPerCircle() {return pointsPerCircle;}

	public static double getCurrentAngle(int numCircles, int currentCircle) {
		double  currentAngle = (Math.PI * 2) * currentCircle/numCircles;
		return currentAngle;
	}
	
	
	public static double[][][] getTheCoordinates(double innnerRadius, double outerRadius, int x_amountsCircle, int x_coordinatePointsPerCircle)
	{
		double[][][] coordinates = new double[numCircles][pointsPerCircle][3];
		
		
		for (int currentOuterPoint = 0; currentOuterPoint < x_amountsCircle; currentOuterPoint++ )
		{

			double currentOuterAngle = getCurrentAngle(numCircles, currentOuterPoint);
			
			for (int currentInnerPoint = 0; currentInnerPoint < x_coordinatePointsPerCircle; currentInnerPoint++ ) 
			{
				
				double currentInnerAngle = getCurrentAngle(numCircles, currentInnerPoint);
				
				coordinates[currentOuterPoint][currentInnerPoint][0] = ((outerRadius + innnerRadius * Math.cos(currentInnerAngle)) * Math.cos(currentOuterAngle));
				coordinates[currentOuterPoint][currentInnerPoint][1] = ((outerRadius+ innnerRadius*Math.cos(currentInnerAngle))*Math.sin(currentOuterAngle));
				coordinates[currentOuterPoint][currentInnerPoint][2] = innnerRadius*Math.sin(currentInnerAngle);
				
				for(int projectedCoordinate = 0; projectedCoordinate < coordinates.length; projectedCoordinate++) {
					
					coordinates[currentOuterPoint][currentInnerPoint] = viewerProjection(coordinates[currentOuterPoint][currentInnerPoint][projectedCoordinate]);
				}
			}
		}
		
		return coordinates;
	}
	
	public static double[] rotateCoordinate(boolean rotateZ, boolean rotateX, boolean rotateY) {
		double[] coordinate = new double[3];
		double[] rotatedCoordinate = new double[3];
		double rotationAngle = 0.05;
		
		double[][] rotateXMatrix = {{1, 0, 0},{0, Math.cos(rotationAngle), -Math.sin(rotationAngle)}, {0, Math.sin(rotationAngle), Math.cos(rotationAngle)}};
		double[][] rotateYMatrix = {{Math.cos(rotationAngle), 0, -Math.sin(rotationAngle)},{0, 1, 0},{Math.sin(rotationAngle), 0, Math.cos(rotationAngle)}};
		double[][] rotateZMatrix = {{Math.cos(rotationAngle), -Math.sin(rotationAngle), 0}, {Math.sin(rotationAngle), Math.cos(rotationAngle), 0}, {0, 0, 1}};
		
		if (rotateX) {
			for (int i = 0; i < rotateXMatrix.length; i++) {
				for (int j = 0; j < rotateXMatrix[0].length; j++) {
					rotatedCoordinate[i] = rotateXMatrix[i][j] * coordinate[j];
				}
			}
		}
		
		if (rotateY) {
			for (int i = 0; i < rotateYMatrix.length; i++) {
				for (int j = 0; j < rotateYMatrix[0].length; j++) {
					rotatedCoordinate[i] = rotateYMatrix[i][j] * coordinate[j];
				}
			}
		}
		
		if (rotateZ) {
			for (int i = 0; i < rotateZMatrix.length; i++) {
				for (int j = 0; j < rotateZMatrix[0].length; j++) {
					rotatedCoordinate[i] = rotateZMatrix[i][j] * coordinate[j];
				}
			}
		}
		return rotatedCoordinate;
	}
	
	
	public static double[] viewerProjection(double coordinate) {
		double[] viewerPosition = {0, 0, -50};
		double[] displayedVector = new double[3];
		double[] directionVector = new double[3];
		
		for(int i = 0; i < directionVector.length; i++) {
			directionVector[i] = coordinate;
		}
		
		for (int newPos = 0; newPos < viewerPosition.length; newPos++) {
			displayedVector[newPos] = viewerPosition[newPos] + (1.2 * directionVector[newPos]);
		}
		
		return displayedVector;
	}
	
	

public static void main(String[] args) {
	
	var test = getTheCoordinates(10, 20, numCircles, pointsPerCircle);
		
		/*
		 * x = (R+r * cos(v))cos(u)
		 * y = (R + r* cos(u)sin(u)
		 * z = r * sin(v)
		 * u, v el [0, 2pi]*/
		
		
		
		
		
	}

}

