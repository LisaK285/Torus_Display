import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.JComponent;
import javax.swing.JFrame;


@SuppressWarnings("serial")
class TorusFrame extends JComponent{
	
	Torus finalTorus = new Torus();
	
	double[][][] torusCoordinates = finalTorus.getTheCoordinates(10, 25, finalTorus.getNumCircles(), finalTorus.getCoordinatePointsPerCircle());
	
	public void paint(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		
		for (int i = 0; i < torusCoordinates[0].length; i++) {
			for (int j = 0; j < torusCoordinates[0].length; j++) {
				g2d.draw(new Line2D.Double(torusCoordinates[i][j][0], torusCoordinates[i][j][1], torusCoordinates[i][j+1][0], torusCoordinates[i][j+1][1]));
			
			}
		}
	} //stage this, jerk 
		
	public static void main(String[] args) {
		

		JFrame torusMainFrame = new JFrame("Torus - LK");
		
		torusMainFrame.setSize(800, 800);
		torusMainFrame.setVisible(true);
		
		TorusFrame TorusPanel = new TorusFrame();
		
		torusMainFrame.add(TorusPanel);
		
		torusMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		torusMainFrame.getContentPane().add(new TorusFrame());

	}

}
