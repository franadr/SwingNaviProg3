package lu.uni.programming3.SwingNavigator;
/*Created by :
 * Adriano FRANCI : 011109344A
 * Zhi Kin MOK : 0150096140
 * Carlos De Sa MATOS : 0160543949
 * 
 * For the purpose of Programming 3 project 2016
 * 
 */
public class Launcher {

	public static void main(String[] args) {
		
		Simulator S = new Simulator();
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
			S.CreatAndShowMainLayout();
			}
		});
		
		
	}
}
