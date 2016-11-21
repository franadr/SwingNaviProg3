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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
        
        JMenuBar mb = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem mi1 = new JMenuItem("Help");
        mi1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "Use Up-Key and Down-Key to increase/decrease Speed \nUse Left-Key and Right-Key to increase/decrease Angle");
			}
        	
        });
        
        JMenuItem mi2 = new JMenuItem("Exit");
        mi2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
				
			}
        	
        });
        
        menu.add(mi1);
        menu.add(mi2);
        mb.add(menu);
        mainFrame.setJMenuBar(mb);
        
        
    mainFrame.add(boatPanel, BorderLayout.CENTER);
    mainFrame.add(cmdPanel,BorderLayout.EAST);
    mainFrame.setMaximumSize(new Dimension(WINDOWS_SIZE_WIDTH, WINDOWS_SIZE_HEIGHT));
    mainFrame.setMinimumSize(new Dimension(WINDOWS_SIZE_WIDTH, WINDOWS_SIZE_HEIGHT));
    mainFrame.pack();
    mainFrame.setVisible(true);
    cmdPanel.requestFocusInWindow(); 			//Needed so that cmdPanel containing the Sliders is focused at Initialisation 

	}
}
