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
	private int WINDOWS_SIZE_WIDTH=1000;
	private int WINDOWS_SIZE_HEIGHT=600;

	
	public Simulator(){
		
	};
	
	public void CreatAndShowMainLayout(){
		
    JFrame mainFrame = new JFrame("Navigator");
    mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //LEFT SIDE
        BoatPanel boatPanel = new BoatPanel();
        boatPanel.setFocusable(true);      
        
        //RIGHT SIDE
        CmdPanel cmdPanel = new CmdPanel(boatPanel);
        
        
    mainFrame.add(boatPanel, BorderLayout.CENTER);
    mainFrame.add(cmdPanel,BorderLayout.EAST);
    mainFrame.setMaximumSize(new Dimension(WINDOWS_SIZE_WIDTH, WINDOWS_SIZE_HEIGHT));
    mainFrame.setMinimumSize(new Dimension(WINDOWS_SIZE_WIDTH, WINDOWS_SIZE_HEIGHT));
    mainFrame.pack();
    mainFrame.setVisible(true);
    cmdPanel.requestFocusInWindow(); 			//Needed so that cmdPanel containing the Sliders is focused at Initialisation 

	}
}
