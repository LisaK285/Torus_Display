import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
class TorusFrame extends JPanel{
	
	Torus finalTorus = new Torus();
	
	double[][][] torusCoordinates = Torus.getTheCoordinates(10, 25, finalTorus.getNumCircles(), finalTorus.getCoordinatePointsPerCircle());
	
	
	
	public void drawTorus(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		int torusSize = 0;
		while(torusSize < torusCoordinates.length) {
		for (int i = 0; i < torusCoordinates.length; i++) {
			for (int j = 0; j < torusCoordinates.length; j++) {
				g2d.draw(new Line2D.Double(i, j, i++, j++));
				torusSize++;
			}
		}
	}}
	
	public void paint (Graphics g) {
		super.paint(g);
		drawTorus(g);
	}
		
		
	
	
	public static void main(String[] args) {
		

		JFrame torusMainFrame = new JFrame("Torus - LK");
		
		torusMainFrame.setSize(600, 600);
		torusMainFrame.setVisible(true);
		
		TorusFrame TorusPanel = new TorusFrame();
		
		torusMainFrame.add(TorusPanel);

		//setdefaultcloseoperation(windowconstant_exit_on_close)
	}

}
