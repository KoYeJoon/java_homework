package homework3;

import javax.swing.JPanel;

import homework3.Grid.ButtonHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.*;
import javax.swing.JPanel;

public class MainBoard extends JPanel {
	static JLabel label = new JLabel("Black");
	JLabel label2 = new JLabel("JAVA GAME");
	private JButton button;
	private boolean whoTurn = true;
	Grid game = new Grid(20, 20);

	public MainBoard() {
		setLayout(new BorderLayout());
		Dogame();
	}
	public void Dogame() {
		add(label2, BorderLayout.NORTH);
		label2.setFont(new Font("Serif", Font.BOLD, 40));
		setBackground(Color.GRAY);
		button = new JButton("Reset game");
		add(button, BorderLayout.SOUTH);
		Grid.ButtonHandler handler = game.new ButtonHandler();
		button.addActionListener(handler);
		
		// 바둑판 역할을 하는 grid 만들어야됨
		
		add(game, BorderLayout.CENTER);
		game.setBackground(Color.GRAY);
		add(label, BorderLayout.EAST);
		
		label.setFont(label.getFont().deriveFont(20.0f));
		MouseListener gameStart = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				game.mousePressed(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		};
		
		game.addMouseListener(gameStart);
		game.setVisible(true);
	}


}
