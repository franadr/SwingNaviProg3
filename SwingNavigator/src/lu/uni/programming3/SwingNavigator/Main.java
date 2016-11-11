package lu.uni.programming3.SwingNavigator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class Main {
	static float REFRESH_RATE=60;
	static int LINE_SPEED=1;
	public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CreatAndShowMainLayout();
            }
        });


    }
	
	
	
	private static void CreatAndShowMainLayout(){
        JFrame frame = new JFrame("Navigator");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        JPanel boatPanel = new JPanel();
        JPanel commandPanel = new JPanel();

        JButton b1 = new JButton();
        JButton b2 = new JButton();

        BoatPanel bp = new BoatPanel();
        

        commandPanel.setLayout(new BoxLayout(commandPanel, BoxLayout.Y_AXIS));
        
        //Manual input pannel declaration
        JPanel manualInputPanel = new JPanel();
        manualInputPanel.setLayout(new BoxLayout(manualInputPanel, BoxLayout.Y_AXIS));
        
        
        //Speed slider and indicator panel
        
        JPanel speedIndicatorPanel = new JPanel();
        JPanel sliderPb = new JPanel();
        speedIndicatorPanel.setLayout(new BorderLayout());
        JSlider speedSlider = new JSlider(JSlider.VERTICAL, 0, 30,1 );
        JProgressBar speedIndicator = new JProgressBar(JProgressBar.VERTICAL, 0, 30);
        sliderPb.add(speedSlider);
        sliderPb.add(speedIndicator);
        speedIndicatorPanel.add(sliderPb,BorderLayout.WEST);
        
        
        //Angle slider
        JSlider angleSlider = new JSlider(0, 180, 90);
        
        
        //speedPanel
        JPanel speedInput = new JPanel(new FlowLayout());
        //speedInput.setLayout(new BoxLayout(speedInput, BoxLayout.LINE_AXIS));
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
				Main.LINE_SPEED=speedSliderValue;
				
				
			}
		});
        
        angleSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				angleField.setText(Integer.toString(angleSlider.getValue()));
				
			}
		});

        frame.setMaximumSize(new Dimension(500, 300));
        frame.setMinimumSize(new Dimension(500, 300));
        frame.pack();
        frame.setVisible(true);
        
        
        	
        	Timer t  = new Timer((int)REFRESH_RATE,new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					bp.h=500/5*Main.LINE_SPEED/50;
		        	
		        	bp.repaint();
		        	
				}
			} );
        	
        	t.start();
        
        
        	
        
    }

    
	
}
