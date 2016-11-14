package lu.uni.programming3.SwingNavigator;

import java.awt.Graphics;
import java.awt.Graphics2D;
 
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BoatPanel extends JPanel {
	float V_LINE_OFFSET=0;
	float H_LINE_OFFSET = 0;
	float N_VLINES=4;
	float N_HLINES = 4;
	double ANGLE_DEG=Main.MAX_ANGLE/2;
	double ANGLE_RAD = 0;
	
	double IMG_RESIZE_X=0.1;
	double IMG_RESIZE_Y=0.1;
	
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
        	ANGLE_RAD= Math.toRadians(ANGLE_DEG);
        
        	//V and H factor calculation depending on ANGLE_DEG
        	double factor_V = Math.sin(ANGLE_RAD);
        	double factor_H = Math.cos(ANGLE_RAD);
        	
        	
        	
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
        	
        	//Definition of image placement , will depends on img size factor
        	int image_x = (int) ((this.getWidth()-image.getWidth()/(1/IMG_RESIZE_X))/2);
        	int image_y = (int) ((this.getHeight()-image.getHeight()/(1/IMG_RESIZE_Y))/2);
        	
        	//Definition of rotation center points x y , which are absolute in the panel
        	int rotation_x = (int) (image.getWidth()/(2/IMG_RESIZE_X));
        	int rotation_y = (int) (image.getHeight()/(2/IMG_RESIZE_Y));
        			
        	
        	//First transform executes rotation, second resizes
        	AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(ANGLE_DEG-180),rotation_x,rotation_y);
        	tx.scale(IMG_RESIZE_X, IMG_RESIZE_Y);
        	
        	//Assign tx to linear mapping 
        	AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        	
        	//Apply transform to original image, and draw it
        	g2.drawImage(op.filter(image, null) , image_x,image_y,null);
        	
        	
    }
}


