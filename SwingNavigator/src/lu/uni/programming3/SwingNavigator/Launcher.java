package lu.uni.programming3.SwingNavigator;

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
