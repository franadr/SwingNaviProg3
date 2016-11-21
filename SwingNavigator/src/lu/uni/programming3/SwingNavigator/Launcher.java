package lu.uni.programming3.SwingNavigator;
/*Created by :
 * Adriano FRANCI
 * Zhi Kin MOK
 * Calors De Sa MATOS
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
