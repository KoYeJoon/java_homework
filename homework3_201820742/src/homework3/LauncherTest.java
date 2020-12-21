package homework3;

import java.awt.Color;

import javax.swing.JFrame;


public class LauncherTest {

	public static void main(String[] args) {
		MainBoard content = new MainBoard();
		JFrame window = new JFrame("JAVA Game");
		window.setContentPane(content);
		window.setSize(530,550);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

}
