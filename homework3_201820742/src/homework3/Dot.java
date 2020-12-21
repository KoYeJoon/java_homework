package homework3;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;

public class Dot {
	// 현재 바둑알 색깔을 나타내는 color 변수
	private final int Black = 1;
	private final int White = -1;
	static Color CBlack = new Color(0, 0, 0);
	static Color CWhite = new Color(255, 255, 255);
	int check = 0;
	static boolean checkColor = true;
	static int[][] map = new int[20][20];
	int x = 0;
	int y = 0;

	Dot() {

	}

	Dot(int x, int y) {
		this.x = x;
		this.y = y;
		if (map[x][y] == Black) {
			this.x = x;
			map[x][y] = Black;
		} else {
			this.y = y;
			map[x][y] = White;
		}
	}

	public boolean isCheckColor() {
		return checkColor;
	}

	public void setCheckColor(boolean checkColor) {
		this.checkColor = checkColor;
	}

	public int getBlack() {
		return Black;
	}

	public int getWhite() {
		return White;
	}

	void paintDot(Graphics g) {
		// Graphics를 사용하여 현재 바둑알을 그림
		// graphics 에 작성된 fillOval method로 원을 그림
		if (map[x][y] == Black) {
			g.setColor(CBlack);
			g.fillOval(x * 20, y * 20, 20, 20);
			g.drawOval(x * 20, y * 20, 20, 20);
		} else if(map[x][y] == White){
			g.setColor(CWhite);
			g.fillOval(x * 20, y * 20, 20, 20);
			g.drawOval(x * 20, y * 20, 20, 20);
		}

	}

	public void setMap(int x, int y) {
		if (checkColor) {
			this.x = x;
			map[x][y] = Black;

		} else {
			this.y = y;
			map[x][y] = White;
		}
	}

}
