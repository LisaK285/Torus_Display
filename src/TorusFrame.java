
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;

import javax.swing.*;


@SuppressWarnings("serial")
class TorusFrame extends JPanel{
	

	static double[][][] torusCoordinates = Torus.getTheCoordinates(50, 100);
	static double[][][] displayedTorusCoordinates = Torus.displayCoordinates(50, 100, torusCoordinates);
	static double[][][] rotatedTorusCoordinates = new double[torusCoordinates.length][torusCoordinates[0].length][3];
	
	static boolean rotateX = true;
	static boolean rotateY = false;
	static boolean rotateZ = false;
	
	
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		
		for (int i = 0; i < displayedTorusCoordinates[0].length; i++) {
			for (int j = 0; j < displayedTorusCoordinates[1].length-1; j++) {
				g2d.draw(new Line2D.Double(displayedTorusCoordinates[i][j][0], displayedTorusCoordinates[i][j][1], displayedTorusCoordinates[i][j+1][0], displayedTorusCoordinates[i][j+1][1]));
				g2d.draw(new Line2D.Double(displayedTorusCoordinates[i][j][0], displayedTorusCoordinates[i][j][1], displayedTorusCoordinates[i+1][j][0], displayedTorusCoordinates[i+1][j][1]));
				
			}
		}
		}
	
	public static void main(String[] args) {
		
		JFrame torusMainFrame = new JFrame("Torus - LK");
		TorusFrame TorusPanel = new TorusFrame();
		JButton changeRotation = new JButton("Change directions");
		
		
		
		
		ActionListener changeDirection = new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				torusCoordinates = Torus.getTheCoordinates(50, 100);
				displayedTorusCoordinates = Torus.displayCoordinates(50, 100, torusCoordinates);
				
				rotateX = !rotateX;
				rotateY = !rotateY;
				rotateZ = !rotateZ;
			}
		};
		
	
		
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
						
				for(int i = 0; i < rotatedTorusCoordinates.length; i++) {
					for (int j = 0; j < rotatedTorusCoordinates[0].length; j++) {
						rotatedTorusCoordinates[i][j] = Torus.rotateCoordinate(rotateX, rotateY, rotateZ, torusCoordinates[i][j]);
						torusCoordinates[i][j] = rotatedTorusCoordinates[i][j];
						
					}
				}
				displayedTorusCoordinates = Torus.displayCoordinates(50, 100, torusCoordinates);
				TorusPanel.revalidate();
				TorusPanel.repaint();
						}
				
				
				
			};
		
		Timer timer = new Timer(290, actionListener);
		timer.start();
		
		changeRotation.addActionListener(changeDirection);
		
		
		torusMainFrame.setSize(1200, 900);
		
		torusMainFrame.add(TorusPanel);
		TorusPanel.add(changeRotation);
		
		torusMainFrame.setVisible(true);
		torusMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
		}

 