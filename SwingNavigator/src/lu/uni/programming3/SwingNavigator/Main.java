package lu.uni.programming3.SwingNavigator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class Main{
	static float REFRESH_RATE=60;
	static float REFRESH_RATE_2=500;
	static int BOAT_SPEED=1;
	static int BOAT_ANGLE = 90;
	static int WINDOWS_SIZE_X=1000;
	static int WINDOWS_SIZE_Y=600;
	static int MAX_SPEED=20;
	static int MAX_ANGLE=360;
	static int AUTO_ROTATION_ANGLE=0;
	static double ACTION_FLAG=0;
	static int ROTATION_TYPE=0;
	
	public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CreatAndShowMainLayout();
            }
        });


    }
	
	
	
	private static void CreatAndShowMainLayout(){
		BufferedImage warningSign;
		
        JFrame frame = new JFrame("Navigator");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        JPanel boatPanel = new JPanel();
        JPanel commandPanel = new JPanel();

      

        BoatPanel bp = new BoatPanel();
        bp.setFocusable(true);
        

        commandPanel.setLayout(new BoxLayout(commandPanel, BoxLayout.Y_AXIS));
        
        //Manual input pannel declaration
        JPanel manualInputPanel = new JPanel();
        manualInputPanel.setLayout(new BoxLayout(manualInputPanel, BoxLayout.Y_AXIS));
        
        //Warning label
        ImageIcon warning = new ImageIcon(new ImageIcon("warning.png").getImage().getScaledInstance(90,70, Image.SCALE_DEFAULT));
        JLabel warningLabel = new JLabel();
        warningLabel.setIcon(warning);
        warningLabel.setVisible(false);
        
        //Speed slider and indicator panel
        JPanel speedIndicatorPanel = new JPanel();
        JPanel sliderPb = new JPanel();
        speedIndicatorPanel.setLayout(new BorderLayout());
        JSlider speedSlider = new JSlider(JSlider.VERTICAL, 0, MAX_SPEED,1 );
        JProgressBar speedIndicator = new JProgressBar(JProgressBar.VERTICAL, 0, MAX_SPEED);
        sliderPb.add(speedSlider);
        sliderPb.add(speedIndicator);
        sliderPb.add(warningLabel);
        speedIndicatorPanel.add(sliderPb,BorderLayout.WEST);
        
        
        
        //Angle slider
        JSlider angleSlider = new JSlider(0, MAX_ANGLE, MAX_ANGLE/2);
        
        
        //speedPanel
        JPanel speedInput = new JPanel(new FlowLayout());
        
        //angle panel
        JPanel angleIn = new JPanel(new FlowLayout());
        
        
        
        
        
        
        //Manual Speed inputs fields
        JLabel speed = new JLabel("Speed input");
        JTextField speedField = new JTextField("0");
        speedField.setFocusable(false);
        speedField.setPreferredSize(new Dimension(100, 20));
        speedField.setMaximumSize(new Dimension(100, 20));
        speedInput.add(speed);
        speedInput.add(speedField);
        
        //Manual Angle inputs fields
        JLabel angle = new JLabel("Angle input");
        JTextField angleField = new JTextField("0°");
        angleField.setFocusable(false);
        angleField.setPreferredSize(new Dimension(100, 20));
        angleField.setMaximumSize(new Dimension(100, 20));
        angleIn.add(angle);
        angleIn.add(angleField);
        
        //Auto rotation rectangle button
        JButton select_circle = new JButton("Auto Circles");
        JButton select_rectangle = new JButton("Auto rectangles");
        
        //Special features panel
        JPanel specialFeaturesPanel = new JPanel();
        specialFeaturesPanel.setLayout(new GridLayout(3, 3));
        specialFeaturesPanel.add(select_circle);
        specialFeaturesPanel.add(select_rectangle);
        

        
        //commandPanel build
        commandPanel.add(speedInput);
        commandPanel.add(angleIn);
        commandPanel.add(speedIndicatorPanel);
        commandPanel.add(specialFeaturesPanel);
        commandPanel.add(angleSlider);
        frame.add(bp, BorderLayout.CENTER);
        frame.add(commandPanel,BorderLayout.EAST);
        

/*---------------------------------Program dynamics----------------------------------------------------------------------*/
        
        
        //SpeedSLider listener changes indicated speed and static value of BOAT_SPEED
        speedSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int speedSliderValue = speedSlider.getValue();
				speedIndicator.setValue(speedSliderValue);
				speedField.setText(Integer.toString(speedSliderValue));
				Main.BOAT_SPEED=speedSliderValue;
				
				
			}
		});
        
        speedSlider.addKeyListener(new KeyAdapter(){
        	public void keyPressed(KeyEvent e) {

        	    int key = e.getKeyCode();
        	    int speed = speedSlider.getValue();

        	    if (key == KeyEvent.VK_UP) {
        	        if(speed != MAX_SPEED)
        	        	speedSlider.setValue(speed + 1);
        	        System.out.println(speed);
        	    }

        	    if (key == KeyEvent.VK_DOWN) {
        	        if(speed != 0)
        	        	speedSlider.setValue(speed - 1);
        	    }
        	    
        	}
        	
        		});
        
        //Angle slide listener changes Boat panel static ANGLE_DEG value 
        angleSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				angleField.setText(Integer.toString(angleSlider.getValue()));
				bp.ANGLE_DEG=angleSlider.getValue();
				
			}
		});
        
        angleSlider.addKeyListener(new KeyAdapter(){
        	public void keyPressed(KeyEvent e) {

        	    int key = e.getKeyCode();
        	    int angle = angleSlider.getValue();

        	    if (key == KeyEvent.VK_RIGHT) {
        	        if(angle != MAX_ANGLE)
        	        	angleSlider.setValue(angle + 1);
        	    }

        	    if (key == KeyEvent.VK_LEFT) {
        	        if(angle != 0)
        	        	angleSlider.setValue(angle - 1);
        	    }
        	    
        	}
        	
        		});

        //Redraw Boat panel timer
        	Timer drawLinesTimer  = new Timer((int)REFRESH_RATE,new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					bp.h=bp.getHeight()/5*Main.BOAT_SPEED/50;
					bp.v = bp.getWidth()/5*Main.BOAT_SPEED/50;
		        	bp.repaint();
		        	if(BOAT_SPEED >=MAX_SPEED-2)
		        		warningLabel.setVisible(true);
		        	else 
		        		warningLabel.setVisible(false);
		        	
				}
			} );
        	
        	drawLinesTimer.start();
        	
        	//Auto rotation dynamics
            Timer modelRotationTimer = new Timer((int)REFRESH_RATE_2, new ActionListener() {
    			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				if(AUTO_ROTATION_ANGLE>=360)
    					AUTO_ROTATION_ANGLE=0;
    				
    				if(speedSlider.getValue() >0)
    				switch (ROTATION_TYPE) {
					case 0:
						
						AUTO_ROTATION_ANGLE+=1;
						
						break;
					case 1:
						
						AUTO_ROTATION_ANGLE+=90;
					default:
						break;
					}
    				
    				
    				angleSlider.setValue(AUTO_ROTATION_ANGLE);
    				
    			}
    		});
            
        	//Auto circle listener
            select_circle.addActionListener(new ActionListener() {	
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				
    				
    				//If ACTION_FLAG is even it means that we clicked 1  or  2*n+1 times then Start option needs to be showed
    				ROTATION_TYPE=0;
    				REFRESH_RATE_2=20;
    				if(ACTION_FLAG/2 == (int)((ACTION_FLAG/2))){
    					
    					modelRotationTimer.start();
    					select_rectangle.setEnabled(false);
    					select_circle.setText("Stop");
    					AUTO_ROTATION_ANGLE = angleSlider.getValue();
    					angleField.setEnabled(false);
    				}
    				
    				else{
    					select_circle.setText("Auto Circles");
    					modelRotationTimer.stop();
    					angleField.setEnabled(true);
    					select_rectangle.setEnabled(true);
    				}

    				ACTION_FLAG+=1;
    				
    			}
    		});
            
            //Auto rectangle Listener
            select_rectangle.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					//If ACTION_FLAG is even it means that we clicked 1  or  2*n+1 times then Start option needs to be showed
					ROTATION_TYPE=1;
					REFRESH_RATE_2=500;
    				if(ACTION_FLAG/2 == (int)((ACTION_FLAG/2))){
    					
    					modelRotationTimer.start();
    					select_circle.setEnabled(false);
    					select_rectangle.setText("Stop");
    					AUTO_ROTATION_ANGLE = angleSlider.getValue();
    					angleField.setEnabled(false);
    				}
    				
    				else{
    					select_rectangle.setText("Auto Rectangles");
    					modelRotationTimer.stop();
    					angleField.setEnabled(true);
    					select_circle.setEnabled(true);
    				}

    				ACTION_FLAG+=1;
					
				}
			});
            
            frame.setMaximumSize(new Dimension(WINDOWS_SIZE_X, WINDOWS_SIZE_Y));
            frame.setMinimumSize(new Dimension(WINDOWS_SIZE_X, WINDOWS_SIZE_Y));
            frame.pack();
            frame.setVisible(true);
    }
}