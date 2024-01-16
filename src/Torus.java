
public class Torus {
	
	private static int numCircles = 100;
	private static int pointsPerCircle = 99;
	private static double[][][] coordinates = new double[numCircles][pointsPerCircle][3];
	private static double[][][] displayedCoordinates = new double[numCircles][pointsPerCircle][2];
	
	//getter
	public static int getNumCircles() {return numCircles;}
	public int getCoordinatePointsPerCircle() {return pointsPerCircle;}
	

	public static double getCurrentAngle(int numCircles, int currentCircle) {
		double  currentAngle = (Math.PI * 2 * currentCircle)/numCircles;
		return currentAngle;
	}
	
	
	public static double[][][] getTheCoordinates(double innerRadius, double outerRadius){
		
		for (int currentOuterPoint = 0; currentOuterPoint < numCircles; currentOuterPoint++ )
		{

			double currentOuterAngle = getCurrentAngle(pointsPerCircle, currentOuterPoint);
			
			for (int currentInnerPoint = 0; currentInnerPoint < pointsPerCircle; currentInnerPoint++ ) 
			{
				
				double currentInnerAngle = getCurrentAngle(pointsPerCircle, currentInnerPoint);
				
				coordinates[currentOuterPoint][currentInnerPoint][0] = ((outerRadius + innerRadius * Math.cos(currentInnerAngle)) * Math.cos(currentOuterAngle));
				coordinates[currentOuterPoint][currentInnerPoint][1] = ((outerRadius + innerRadius * Math.cos(currentInnerAngle)) * Math.sin(currentOuterAngle));
				coordinates[currentOuterPoint][currentInnerPoint][2] = innerRadius * Math.sin(currentInnerAngle);
				
			}
		}
		
		return coordinates;
	}
	
	public static double[][][] displayCoordinates(double innerRadius, double outerRadius, double[][][] coordinatesToDisplay) {
		for (int currentOuterPoint = 0; currentOuterPoint < numCircles; currentOuterPoint++) {
			for (int currentInnerPoint = 0; currentInnerPoint < pointsPerCircle; currentInnerPoint++) {
				displayedCoordinates[currentOuterPoint][currentInnerPoint] = viewerProjection(coordinatesToDisplay[currentOuterPoint][currentInnerPoint]);
				displayedCoordinates[currentOuterPoint][currentInnerPoint][0] += 1.8 * (outerRadius + innerRadius);
				displayedCoordinates[currentOuterPoint][currentInnerPoint][1] += 1.8 * (outerRadius + innerRadius);
			}
		}
		return displayedCoordinates;
	}
	
	public static double[] rotateCoordinate(boolean rotateX, boolean rotateY, boolean rotateZ, double[] coordinateToRotate) {
		double[] rotatedCoordinate = new double[3];
		double rotationAngle = 90;
		
		double[][] rotateXMatrix = {{1, 0, 0},{0, Math.cos(rotationAngle), (-Math.sin(rotationAngle))}, {0, Math.sin(rotationAngle), Math.cos(rotationAngle)}};
		double[][] rotateYMatrix = {{Math.cos(rotationAngle), 0, -Math.sin(rotationAngle)},{0, 1, 0},{Math.sin(rotationAngle), 0, Math.cos(rotationAngle)}};
		double[][] rotateZMatrix = {{Math.cos(rotationAngle), -Math.sin(rotationAngle), 0}, {Math.sin(rotationAngle), Math.cos(rotationAngle), 0}, {0, 0, 1}};
		

		
		if(rotateX) {
				for (int i = 0; i < rotateXMatrix.length; i++) {
					for (int j = 0; j < rotateXMatrix[1].length; j++) {
						rotatedCoordinate[i] += rotateXMatrix[i][j] * coordinateToRotate[j];
					}
				
			}}
		if(rotateY) {
			for (int i = 0; i < rotateYMatrix.length; i++) {
				for (int j = 0; j < rotateYMatrix[1].length; j++) {
					rotatedCoordinate[i] += rotateYMatrix[i][j] * coordinateToRotate[j];
				}
			
		}}
		
		if(rotateZ) {
			for (int i = 0; i < rotateZMatrix.length; i++) {
				for (int j = 0; j < rotateZMatrix[1].length; j++) {
					rotatedCoordinate[i] += rotateZMatrix[i][j] * coordinateToRotate[j];
				}
			
		}}
		
			
		
	return rotatedCoordinate;
	}


	
	
	public static double[] viewerProjection(double[] coordinate) {
		double[] viewerPosition = {0, 0, -25};
		double[] displayedVector = new double[3];
		double[] directionVector = new double[3];
		double s = (viewerPosition[2]/(directionVector[2]-viewerPosition[2]));
		double[] finalVector = new double[2];
		
		for(int i = 0; i < directionVector.length-1; i++) {
			directionVector[i] = coordinate[i] - viewerPosition[i];
		}
		
		for (int newPos = 0; newPos < viewerPosition.length; newPos++) {
			displayedVector[newPos] = viewerPosition[newPos] + (1.2 * directionVector[newPos]);
		}
		
		for (int index = 0; index < viewerPosition.length-1; index++) {
			finalVector[index] = displayedVector[index] * s;
		}
		
		return finalVector;
	}
public static void main(String[] args) {

	 
}
}