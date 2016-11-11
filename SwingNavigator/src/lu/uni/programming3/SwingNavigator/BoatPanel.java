package lu.uni.programming3.SwingNavigator;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

public class BoatPanel extends JPanel {
	float A_VALUE=1;
	float B_VALUE=1;
	float h=0;
	public void paint(Graphics g) {
        super.paint(g);  // fixes the immediate problem.
        Graphics2D g2 = (Graphics2D) g;
        
        	A_VALUE=A_VALUE+h;
        	B_VALUE=B_VALUE+h;
        	
        	if(A_VALUE >= this.getHeight())
        		A_VALUE=0;
        	if(B_VALUE >= this.getHeight())
        		B_VALUE=0;
        	
        	
        	 Line2D lin = new Line2D.Float(20, A_VALUE, 850, B_VALUE);
        	 Line2D lin2 = new Line2D.Float(20, A_VALUE+10, 850, B_VALUE+10);
        	 
        	 
        	 //Line2D col2 = new Line2D.Float(A_VALUE, 0, B_VALUE, 850);
             g2.draw(lin);
             g2.draw(lin2);
             
             //g2.draw(col2);
             
             
             
             
        
       
        
    }
}


