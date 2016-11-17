package lu.uni.programming3.SwingNavigator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Simulator {
	public static int MAX_SPEED=20;
	public static int MAX_ANGLE=360;
	private float REFRESH_RATE=60;
	private float REFRESH_RATE_AUTO_ROTATION=500;
	private int BOAT_SPEED=1;
	private int WINDOWS_SIZE_WIDTH=1000;
	private int WINDOWS_SIZE_HEIGHT=600;
	private int AUTO_ROTATION_ANGLE=0;
	private int ROTATION_TYPE=0;
	private boolean isActivated = true;
	
	public Simulator(){
		
	};
	
	public void CreatAndShowMainLayout(){
		
    JFrame mainFrame = new JFrame("Navigator");
    mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //LEFT SIDE
        BoatPanel boatPanel = new BoatPanel();
        boatPanel.setFocusable(true);      
        
        //RIGHT SIDE
        JPanel cmdPanel = new JPanel();
        cmdPanel.setLayout(new BoxLayout(cmdPanel, BoxLayout.Y_AXIS));
        
        //Angle Label & TextField
        JPanel angleIn = new JPanel(new FlowLayout());
        	//Label
	        	JLabel angle = new JLabel("Angle input");
	        //TextField
		        JTextField angleField = new JTextField(Integer.toString(MAX_ANGLE/2));
		        angleField.setPreferredSize(new Dimension(100, 20));
		        angleField.setMaximumSize(new Dimension(100, 20));
		        angleField.setFocusable(false);
        angleIn.add(angle);
        angleIn.add(angleField);
        
        
        //Speed Label & TextField
        JPanel speedIn = new JPanel(new FlowLayout());
        	//Label
	        	JLabel speed = new JLabel("Speed input");
        	//TextField
		        JTextField speedField = new JTextField(Integer.toString(BOAT_SPEED));
		        speedField.setFocusable(false);
		        speedField.setPreferredSize(new Dimension(100, 20));
		        speedField.setMaximumSize(new Dimension(100, 20)); 
        speedIn.add(speed);
        speedIn.add(speedField);
              
        
        //Speed Slider, Progress Bar and Warning Icon
        JPanel speedIndicatorPanel = new JPanel();
        speedIndicatorPanel.setLayout(new BorderLayout());
	        JPanel speedFlowLayout = new JPanel(new FlowLayout());
	        	//Slider
			        JSlider speedSlider = new JSlider(JSlider.VERTICAL, 0, MAX_SPEED, BOAT_SPEED);
			        speedSlider.setFocusable(false); 	//Set to false so that it doesn't mess up with the main key bindings
			        speedSlider.setPreferredSize(new Dimension(20,210));
		        //Progress Bar
		        	JProgressBar speedProgressBar = new JProgressBar(JProgressBar.VERTICAL, 0, MAX_SPEED);
		        	speedProgressBar.setPreferredSize(new Dimension(50,200));
		        	speedProgressBar.setValue(BOAT_SPEED);
		        //Warning Icon
			        ImageIcon warning = new ImageIcon(new ImageIcon("warning.png").getImage().getScaledInstance(90,70, Image.SCALE_DEFAULT));
			        JLabel warningLabel = new JLabel();
			        warningLabel.setIcon(warning);
			        warningLabel.setVisible(false);   
	        speedFlowLayout.add(speedSlider);
	        speedFlowLayout.add(speedProgressBar);
	        speedFlowLayout.add(warningLabel); 
        speedIndicatorPanel.add(speedFlowLayout,BorderLayout.WEST);


        //Auto Rotation & Auto Rectangles
        JPanel specialFeaturesPanel = new JPanel();
        specialFeaturesPanel.setLayout(new GridLayout(3, 3));
	        JButton select_circle = new JButton("Auto Circles");
	        JButton select_rectangle = new JButton("Auto Rectangles");
	        select_circle.setFocusable(false);
	        select_rectangle.setFocusable(false);
        specialFeaturesPanel.add(select_circle);
        specialFeaturesPanel.add(select_rectangle);
        
        //Angle Slider
        JSlider angleSlider = new JSlider(0, MAX_ANGLE, MAX_ANGLE/2);
        angleSlider.setFocusable(false); 			//Set to false so that it doesn't mess up with the main key bindings
        
        
        //cmdPanel Build
        cmdPanel.add(speedIn);
        cmdPanel.add(angleIn);
        cmdPanel.add(speedIndicatorPanel);
        cmdPanel.add(specialFeaturesPanel);
        cmdPanel.add(angleSlider);
        cmdPanel.setFocusable(true);
        
    mainFrame.add(boatPanel, BorderLayout.CENTER);
    mainFrame.add(cmdPanel,BorderLayout.EAST);
        

/*---------------------------------Program Dynamics----------------------------------------------------------------------*/
        

        //Key Bindings for the Arrow Keys
        cmdPanel.getInputMap().put(KeyStroke.getKeyStroke("UP"), "speedUp");
        cmdPanel.getActionMap().put("speedUp",new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(speedSlider.getValue() < MAX_SPEED)
				speedSlider.setValue(speedSlider.getValue()+1);
				
			}
		});
        
        cmdPanel.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "speedDown");
        cmdPanel.getActionMap().put("speedDown",new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(speedSlider.getValue() > 0)
				speedSlider.setValue(speedSlider.getValue()-1);
				
			}
		});
        
        cmdPanel.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "angleMin");
        cmdPanel.getActionMap().put("angleMin",new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(angleSlider.getValue() <= 0){
				angleSlider.setValue(MAX_ANGLE);
				}
				angleSlider.setValue(angleSlider.getValue()-10);
			}
		});
        
        cmdPanel.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "anglePlus");
        cmdPanel.getActionMap().put("anglePlus",new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(angleSlider.getValue() >= MAX_ANGLE){
				angleSlider.setValue(0);
				}
				angleSlider.setValue(angleSlider.getValue()+10);
			}
		});
        
        //SpeedSlider Listener changes displayed speed and the BOAT_SPEED value
        speedSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int speedSliderValue = speedSlider.getValue();
				speedProgressBar.setValue(speedSliderValue);
				speedField.setText(Integer.toString(speedSliderValue));
				BOAT_SPEED=speedSliderValue;
			}
		});
        
        
        //AngleSlider Listener changes Boat panel the ANGLE_DEG value 
        angleSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				angleField.setText(Integer.toString(angleSlider.getValue()));
				boatPanel.setAngleDeg(angleSlider.getValue());
				
			}
		});

        //Timer for RePainting the Boat Panel 
    	Timer repaintGridTimer  = new Timer((int)REFRESH_RATE,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boatPanel.setH(boatPanel.getHeight()/5*BOAT_SPEED/50);
				boatPanel.setV(boatPanel.getWidth()/5*BOAT_SPEED/50);
	        	boatPanel.repaint();
	        	if(BOAT_SPEED >=MAX_SPEED-2)
	        		warningLabel.setVisible(true);
	        	else 
	        		warningLabel.setVisible(false);
	        	
			}
		});	
        repaintGridTimer.start();
        	        
            Timer autoRotationTimer = new Timer((int)REFRESH_RATE_AUTO_ROTATION, new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				if(AUTO_ROTATION_ANGLE >= 360)
    					AUTO_ROTATION_ANGLE=0;
    				
    				if(speedSlider.getValue() > 0){
	    				switch (ROTATION_TYPE) {
							case 0: AUTO_ROTATION_ANGLE++;
									break;
							case 1: AUTO_ROTATION_ANGLE+=90;	
									break;
							default:
									break;
						}
    				}
    				
    				angleSlider.setValue(AUTO_ROTATION_ANGLE);	
    			}
    		});
            
        	//Auto Circle Listener
            select_circle.addActionListener(new ActionListener() {	
    			@Override
    			public void actionPerformed(ActionEvent e) {

    				ROTATION_TYPE=0;
    				autoRotationTimer.setDelay(40);
    				if(isActivated){
    					autoRotationTimer.start();
    					select_circle.setText("Stop");
    					select_rectangle.setEnabled(false);
    					angleField.setEnabled(false);
    					AUTO_ROTATION_ANGLE = angleSlider.getValue();
    					isActivated = false;
    				}
    				
    				else{
    					autoRotationTimer.stop();
    					select_circle.setText("Auto Circles");
    					angleField.setEnabled(true);
    					select_rectangle.setEnabled(true);
    					isActivated = true;
    				}
    			}
    		});
            
            //Auto Rectangle Listener
            select_rectangle.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					ROTATION_TYPE=1;
					autoRotationTimer.setDelay(500);
					if(isActivated){
    					
    					autoRotationTimer.start();
    					select_rectangle.setText("Stop");
    					select_circle.setEnabled(false);
    					angleField.setEnabled(false);
    					AUTO_ROTATION_ANGLE = angleSlider.getValue();
    					isActivated = false;
    				}
					
					else{
    					autoRotationTimer.stop();
    					select_rectangle.setText("Auto Rectangles");
    					angleField.setEnabled(true);
    					select_circle.setEnabled(true);
    					isActivated = true;
    				}					
				}
			});
            
            mainFrame.setMaximumSize(new Dimension(WINDOWS_SIZE_WIDTH, WINDOWS_SIZE_HEIGHT));
            mainFrame.setMinimumSize(new Dimension(WINDOWS_SIZE_WIDTH, WINDOWS_SIZE_HEIGHT));
            mainFrame.pack();
            mainFrame.setVisible(true);
            cmdPanel.requestFocusInWindow(); 			//Needed so that cmdPanel containing the Sliders is focused at Initialisation 
    }
}
