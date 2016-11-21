package lu.uni.programming3.SwingNavigator;
/*Created by :
 * Adriano FRANCI
 * Zhi Kin MOK
 * Calors De Sa MATOS
 * 
 * For the purpose of Programming 3 project 2016
 * 
 */
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CmdPanel extends JPanel {
	public static int MAX_SPEED = 20;
	public static int MAX_ANGLE = 360;
	private float REFRESH_RATE = 60;
	private float REFRESH_RATE_AUTO_ROTATION = 500;
	private int BOAT_SPEED = 1;
	private int AUTO_ROTATION_ANGLE = 0;
	private int ROTATION_TYPE = 0;
	private boolean isActivated = true;
	private BoatPanel boatPanel;

	public CmdPanel(BoatPanel boatPanel) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.boatPanel = boatPanel;

		// Angle Label & TextField
		JPanel angleIn = new JPanel(new FlowLayout());
			// Label
				JLabel angle = new JLabel("Angle input");
			// TextField
				JTextField angleField = new JTextField(Integer.toString(MAX_ANGLE / 2));
				angleField.setPreferredSize(new Dimension(100, 20));
				angleField.setMaximumSize(new Dimension(100, 20));
//				angleField.setFocusable(false);
		angleIn.add(angle);
		angleIn.add(angleField);

		// Speed Label & TextField
		JPanel speedIn = new JPanel(new FlowLayout());
			// Label
				JLabel speed = new JLabel("Speed input");
			// TextField
				JTextField speedField = new JTextField(Integer.toString(BOAT_SPEED));
//				speedField.setFocusable(false);
				speedField.setPreferredSize(new Dimension(100, 20));
				speedField.setMaximumSize(new Dimension(100, 20));
		speedIn.add(speed);
		speedIn.add(speedField);

		// Speed Slider, Progress Bar and Warning Icon
		JPanel speedIndicatorPanel = new JPanel();
		speedIndicatorPanel.setLayout(new BorderLayout());
			JPanel speedFlowLayout = new JPanel(new FlowLayout());
					// Slider
						JSlider speedSlider = new JSlider(JSlider.VERTICAL, 0, MAX_SPEED, BOAT_SPEED);
						speedSlider.setFocusable(false); // Set to false so that it doesn't mess up with the main key bindings
						speedSlider.setPreferredSize(new Dimension(20, 210));
					// Progress Bar
						JProgressBar speedProgressBar = new JProgressBar(JProgressBar.VERTICAL, 0, MAX_SPEED);
						speedProgressBar.setPreferredSize(new Dimension(50, 200));
						speedProgressBar.setValue(BOAT_SPEED);
					// Warning Icon
						ImageIcon warning = new ImageIcon(new ImageIcon("warning.png").getImage().getScaledInstance(80, 70, Image.SCALE_DEFAULT));
						JLabel warningLabel = new JLabel();
						warningLabel.setIcon(warning);
						warningLabel.setVisible(false);
						
					//speed label
						JLabel speed_string = new JLabel();
						speed_string.setText("<HTML>S<br>P<br>E<br>E<br>D</HTML>");
						
			speedFlowLayout.add(speed_string);
			speedFlowLayout.add(speedSlider);
			speedFlowLayout.add(speedProgressBar);
			speedFlowLayout.add(warningLabel);
		speedIndicatorPanel.add(speedFlowLayout, BorderLayout.WEST);

		// Auto Rotation & Auto Rectangles
		JPanel specialFeaturesPanel = new JPanel();
		specialFeaturesPanel.setLayout(new GridLayout(3, 3));
			JButton select_circle = new JButton("Auto Circles");
			JButton select_rectangle = new JButton("Auto Rectangles");
			select_circle.setFocusable(false);
			select_rectangle.setFocusable(false);
		specialFeaturesPanel.add(select_circle);
		specialFeaturesPanel.add(select_rectangle);

		// Angle Slider
		JSlider angleSlider = new JSlider(0, MAX_ANGLE, MAX_ANGLE / 2);
		angleSlider.setFocusable(false); // Set to false so that it doesn't mess up with the main key bindings
		
		//Angle string
		JLabel angle_string = new JLabel("ANGLE");
		
		//Credit button
		JButton credits_button = new JButton("Show credits");
		credits_button.setFocusable(false);
		
		
		// cmdPanel Build
		this.add(speedIn);
		this.add(angleIn);
		this.add(speedIndicatorPanel);
		this.add(specialFeaturesPanel);
		this.add(angle_string);
		this.add(angleSlider);
		this.add(credits_button);
		
		this.setFocusable(true);
		
		
		
		/*---------------------------------Program Dynamics----------------------------------------------------------------------*/

		
		// Key Bindings for the Arrow Keys
		this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "speedUp");
		this.getActionMap().put("speedUp", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (speedSlider.getValue() < MAX_SPEED)
					speedSlider.setValue(speedSlider.getValue() + 1);

			}
		});

		this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "speedDown");
		this.getActionMap().put("speedDown", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (speedSlider.getValue() > 0)
					speedSlider.setValue(speedSlider.getValue() - 1);

			}
		});

		this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "angleMin");
		this.getActionMap().put("angleMin", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (angleSlider.getValue() <= 0) {
					angleSlider.setValue(MAX_ANGLE);
				}
				angleSlider.setValue(angleSlider.getValue() - 10);
			}
		});

		this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "anglePlus");
		this.getActionMap().put("anglePlus", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (angleSlider.getValue() >= MAX_ANGLE) {
					angleSlider.setValue(0);
				}
				angleSlider.setValue(angleSlider.getValue() + 10);
			}
		});

		// SpeedSlider Listener changes displayed speed and the BOAT_SPEED value
		speedSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int speedSliderValue = speedSlider.getValue();
				speedProgressBar.setValue(speedSliderValue);
				speedField.setText(Integer.toString(speedSliderValue));
				BOAT_SPEED = speedSliderValue;
			}
		});

		// AngleSlider Listener changes Boat panel the ANGLE_DEG value
		angleSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				angleField.setText(Integer.toString(angleSlider.getValue()));
				boatPanel.setAngleDeg(angleSlider.getValue());

			}
		});

		// Timer for RePainting the Boat Panel
		Timer repaintGridTimer = new Timer((int) REFRESH_RATE, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boatPanel.setH(boatPanel.getHeight() / 5 * BOAT_SPEED / 50);
				boatPanel.setV(boatPanel.getWidth() / 5 * BOAT_SPEED / 50);
				boatPanel.repaint();
					if (BOAT_SPEED >= MAX_SPEED - 2)
						warningLabel.setVisible(true);
					else
						warningLabel.setVisible(false);

			}
		});
		repaintGridTimer.start();
		
		angleField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				angleSlider.setValue(Integer.parseInt(angleField.getText()));
			
			requestFocus(true);	
			}
		});
		
		speedField.addActionListener(new  ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				speedSlider.setValue(Integer.parseInt(speedField.getText()));
				
				requestFocus(true);
				
			}
		});

		Timer autoRotationTimer = new Timer((int) REFRESH_RATE_AUTO_ROTATION, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (AUTO_ROTATION_ANGLE >= 360)
					AUTO_ROTATION_ANGLE = 0;

				if (speedSlider.getValue() > 0) {
					switch (ROTATION_TYPE) {
					case 0: AUTO_ROTATION_ANGLE++;
							break;
					case 1: AUTO_ROTATION_ANGLE += 90;
							break;
					default:
							break;
					}
				}

				angleSlider.setValue(AUTO_ROTATION_ANGLE);
			}
		});

		// Auto Circle Listener
		select_circle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				ROTATION_TYPE = 0;
				autoRotationTimer.setDelay(40);
				if (isActivated) {
					autoRotationTimer.start();
					select_circle.setText("Stop");
					select_rectangle.setEnabled(false);
					angleField.setEnabled(false);
					AUTO_ROTATION_ANGLE = angleSlider.getValue();
					isActivated = false;
				}
				

				else {
					autoRotationTimer.stop();
					select_circle.setText("Auto Circles");
					angleField.setEnabled(true);
					select_rectangle.setEnabled(true);
					isActivated = true;
				}
			}
		});

		// Auto Rectangle Listener
		select_rectangle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ROTATION_TYPE = 1;
				autoRotationTimer.setDelay(500);
				if (isActivated) {

					autoRotationTimer.start();
					select_rectangle.setText("Stop");
					select_circle.setEnabled(false);
					angleField.setEnabled(false);
					AUTO_ROTATION_ANGLE = angleSlider.getValue();
					
				}

				else {
					autoRotationTimer.stop();
					select_rectangle.setText("Auto Rectangles");
					angleField.setEnabled(true);
					select_circle.setEnabled(true);
					
				}
				
				isActivated = !isActivated;
			}
		});
		
		credits_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(Simulator.mainFrame, new String("<HTML><h3>Coded by :</h3> Adriano FRANCI <br> Zhi Kin Mok <br> Carlos De Sa Matos <br><br> In the purpose of Programming 3 Project 2016</HTML>"));
				
			}
		});

	}
}
