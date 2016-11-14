package lu.uni.programming3.SwingNavigator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class Main {
	static float REFRESH_RATE=60;
	static int BOAT_SPEED=1;
	static int BOAT_ANGLE = 90;
	static int WINDOWS_SIZE_X=1000;
	static int WINDOWS_SIZE_Y=600;
	static int MAX_SPEED=20;
	static int MAX_ANGLE=360;
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
        speedField.setPreferredSize(new Dimension(100, 20));
        speedField.setMaximumSize(new Dimension(100, 20));
        speedInput.add(speed);
        speedInput.add(speedField);
        
        //Manual Angle inputs fields
        JLabel angle = new JLabel("Angle input");
        JTextField angleField = new JTextField("0°");
        angleField.setPreferredSize(new Dimension(100, 20));
        angleField.setMaximumSize(new Dimension(100, 20));
        angleIn.add(angle);
        angleIn.add(angleField);
        
      
        
        //commandPanel build
        commandPanel.add(speedInput);
        commandPanel.add(angleIn);
        commandPanel.add(speedIndicatorPanel);
        commandPanel.add(angleSlider);
        frame.add(bp, BorderLayout.CENTER);
        frame.add(commandPanel,BorderLayout.EAST);
        
        
        

        

        //Program dynamics
        speedSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int speedSliderValue = speedSlider.getValue();
				speedIndicator.setValue(speedSliderValue);
				speedField.setText(Integer.toString(speedSliderValue));
				Main.BOAT_SPEED=speedSliderValue;
				
				
			}
		});
        
        angleSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				angleField.setText(Integer.toString(angleSlider.getValue()));
				bp.ANGLE_DEG=angleSlider.getValue();
				
			}
		});

        frame.setMaximumSize(new Dimension(WINDOWS_SIZE_X, WINDOWS_SIZE_Y));
        frame.setMinimumSize(new Dimension(WINDOWS_SIZE_X, WINDOWS_SIZE_Y));
        frame.pack();
        frame.setVisible(true);
        
        
        
        	Timer t  = new Timer((int)REFRESH_RATE,new ActionListener() {
				
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
        	
        	t.start();
        
        
        	
        
    }

    
	
}
