package lu.uni.programming3.SwingNavigator;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BoatPanel extends JPanel {
	float V_LINE_OFFSET=0;
	float H_LINE_OFFSET = 0;
	float N_VLINES=4;
	float N_HLINES = 4;
	double ANGLE=90;
	
	float h=0;
	float v=0;
	BufferedImage image;
	
	public BoatPanel() {
		try {
			image = ImageIO.read(new File("plane-512.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void paint(Graphics g) {
        super.paint(g);  
        Graphics2D g2 = (Graphics2D) g;
        	
        	//V and H factor calculation depending on angle
        	double factor_V = Math.sin(Math.toRadians(ANGLE));
        	double factor_H = Math.cos(Math.toRadians(ANGLE));
        	
        	
        	
        	//Offset resets conditions, to give impression of continuous line redrawing
        	if(V_LINE_OFFSET >= this.getHeight()/N_VLINES || V_LINE_OFFSET <= -(this.getHeight()/(N_VLINES)))
        		V_LINE_OFFSET=0;       	
        	if(H_LINE_OFFSET >= this.getWidth()/N_HLINES || H_LINE_OFFSET <= -this.getWidth()/N_HLINES )
        		H_LINE_OFFSET=0;
        	
        	//Vertical and Horizontal line increment
        	V_LINE_OFFSET+=h*factor_V;
        	H_LINE_OFFSET+=v*factor_H;
        	
        	
        	//Vertical and Horizontal lines drawings
        	int i=0;
        	while(i<N_HLINES+1){
        		Line2D line = new Line2D.Float(1, V_LINE_OFFSET+i*this.getHeight()/N_HLINES, this.getWidth(),V_LINE_OFFSET+i*this.getHeight()/N_HLINES);
        		g2.draw(line);
        		i++;
        	}
        	int j=0;
        	while(j<N_VLINES+1){
        		Line2D line = new Line2D.Float( H_LINE_OFFSET+j*this.getWidth()/N_VLINES, 1, H_LINE_OFFSET+j*this.getWidth()/N_VLINES,this.getHeight());
        		g2.draw(line);
        		j++;
        	}
        	
        	
        	
        	g2.drawImage(image , this.getWidth()-image.getWidth(),this.getHeight()-image.getHeight(),null);
    }
}


