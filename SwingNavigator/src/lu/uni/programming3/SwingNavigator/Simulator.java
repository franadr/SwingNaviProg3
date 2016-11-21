package lu.uni.programming3.SwingNavigator;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Simulator {
	private int WINDOWS_SIZE_WIDTH=1000;
	private int WINDOWS_SIZE_HEIGHT=600;
	static JFrame mainFrame;
	
	public Simulator(){
		
	};
	
	public void CreatAndShowMainLayout(){
		
    mainFrame = new JFrame("Navigator");
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
