package homework3;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.Array;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;

public class Grid extends JPanel implements MouseListener {
	static Dot[][] slots;
	static boolean whoTurn = true;
	Dot click = new Dot();
	static int count = -1;
	int[][] gMap = new int[20][20];
	static int ini = 0;
	JFrame congratulationMSG = new JFrame("Message");

	Grid(int x, int y) {
		this.slots = new Dot[x][y];
		for(int i=0;i<x;i++) {
			for(int j=0;j<y;j++) {
				slots[i][j]=null;
			}
		}
	}

	public boolean isWhoTurn() {
		return whoTurn;
	}

	public void setWhoTurn(boolean whoTurn) {
		this.whoTurn = whoTurn;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Color b = new Color(200,130,0);
		int x = 20;
		int y = 20;
		g.drawRect(0, 0, 400, 400);
		g.setColor(b);
		g.fillRect(0, 0, 400, 400);

		
		// 바둑판그리기
		for (int i = 1; i < 20; i++) {
			g.setColor(Color.BLACK);
			g.drawLine(0, i * y, 400, i * y);
			g.drawLine(i * x, 0, i * x, 400);
		}

		// 바둑알보이
		for (int i = 0; i < 20; i++) {
			for (int k = 0; k < 20; k++) {
				if (slots[i][k] != null) {
					slots[i][k].paintDot(g);
				}
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
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

	@Override
	public void mousePressed(MouseEvent e) {
		// 마우스 누른 위치에 바둑알을 배치하는 기능
		// 다음차례가 누구인지 (흑,백)오른쪽에 표시
		int x = e.getX();
		int y = e.getY();
		Graphics g = null;

		boolean flag = false;
		if (x < 400 && y < 400) {
			int col = x / 20;
			int row = y / 20;
			ini++;
			if (ini == 1) {
				slots[col][row] = new Dot(col, row);
				slots[col][row].setCheckColor(true);
				count = -1;
				setWhoTurn(false);
				
				Dot.map[col][row] = 1;
				slots[col][row].check = 1;
				click.setMap(col, row);
				flag = Checkwin(col, row);
				ini++;
				count++;
			}
			if (ini!=1 &&count == 1 && slots[col][row]==null) {
				slots[col][row] = new Dot(col, row);
				if (isWhoTurn() == true) {
					slots[col][row].setCheckColor(true);
					setWhoTurn(false);
					
					Dot.map[col][row] = 1;
					slots[col][row].check = 1;
					click.setMap(col, row);
					flag = Checkwin(col, row);
				} else if (isWhoTurn() == false) {
					slots[col][row].setCheckColor(false);
					setWhoTurn(true);
					
					Dot.map[col][row] = -1;
					slots[col][row].check =- 1;
					click.setMap(col, row);
					flag = Checkwin(col, row);
				}
				count = -1;
				count++;
			} else if (ini!=1&&count == 0 && slots[col][row]==null) {
				slots[col][row] = new Dot(col, row);
				if (isWhoTurn() == true) {
					slots[col][row].setCheckColor(true);
					setWhoTurn(true);
					
					Dot.map[col][row] = 1;
					slots[col][row].check = 1;
					click.setMap(col, row);
					flag = Checkwin(col, row);
				} else if (isWhoTurn() == false) {
					slots[col][row].setCheckColor(false);
					setWhoTurn(false);
					
					Dot.map[col][row] = -1;
					slots[col][row].check = -1;
					click.setMap(col, row);
					flag = Checkwin(col, row);
				}
				count++;
			}
			if(isWhoTurn()) {
				MainBoard.label.setText("Black");
				MainBoard.label.setForeground(Dot.CBlack);
			}
			else {
				MainBoard.label.setText("White");
				MainBoard.label.setForeground(Dot.CWhite);
			}

			if (flag == true) {
				if (slots[col][row].isCheckColor()) {
					Congrats(true);
				} else {
					Congrats(false);
				}
			}

		}

		repaint();

	}

	// check 기능 6개 이상 -> 놓는 순간 마다 check 기능
	// dot이
	boolean Checkwin(int x, int y) {
		int flag[] = new int[12];
		int count = 1;
		int i, j, n;
		int pivot, pivot2;
		if (Dot.map[x][y] == 1) {
			// 가로
			for (int k = 0; k < 12; k++) {
				flag[k] = 0;
			}
			for (i = max(0, x - 5), j = 0; i <= min(x + 5, 19); i++, j++) {
				if (Dot.map[i][y] == 1) {
					flag[j] = 1;
					if (j > 0) {
						if (flag[j] == flag[j - 1] && flag[j] == 1) {
							count++;
							if (count == 6) {
								return true;
							}
						} else {
							count = 1;
						}
					}
				}
			}
			// 세로
			for (int k = 0; k < 12; k++) {
				flag[k] = 0;
			}
			count = 1;
			for (i = max(0, y - 5), j = 0; i <= min(y + 5, 19); i++, j++) {
				if (Dot.map[x][i] == 1) {
					flag[j] = 1;
					if (j > 0) {
						if (flag[j] == flag[j - 1] && flag[j] == 1) {
							count++;
							if (count == 6) {
								return true;
							}
						} else {
							count = 1;
						}
					}
				}
			}
			// 왼쪽 대각선
			pivot = comparing(x - 5, y - 5);
			for (int k = 0; k < 12; k++) {
				flag[k] = 0;
			}
			count = 1;

			for (i = x - pivot, j = 0, n = y - pivot; i <= min(19, x + 5) && n <= min(19, y + 5); i++, j++, n++) {
				if (Dot.map[i][n] == 1) {
					flag[j] = 1;
					if (j > 0) {
						if (flag[j] == flag[j - 1] && flag[j] == 1) {
							count++;
							if (count == 6) {
								return true;
							}
						} else {
							count = 1;
						}
					}
				}
			}
			// 오른쪽 대각선
			pivot = comparing2(x - 5, y + 5);
			pivot2 = comparing3(x + 5, y - 5);
			for (int k = 0; k < 12; k++) {
				flag[k] = 0;
			}
			count = 1;
			for (i = x - pivot, j = 0, n = y + pivot; i <= x + pivot2 && n >= y - pivot2; i++, j++, n--) {
				if (Dot.map[i][n] == 1) {
					flag[j] = 1;
					if (j > 0) {
						if (flag[j] == flag[j - 1] && flag[j] == 1) {
							count++;
							if (count == 6) {
								return true;
							}
						} else {
							count = 1;
						}
					}
				}
			}
		}

		// 흰
		else if (click.map[x][y] == -1) {
			// 가로
			for (int k = 0; k < 12; k++) {
				flag[k] = 0;
			}
			for (i = max(0, x - 5), j = 0; i <= min(x + 5, 19); i++, j++) {
				if (Dot.map[i][y] == -1) {
					flag[j] = 1;
					if (j > 0) {
						if (flag[j] == flag[j - 1] && flag[j] == 1) {
							count++;
							if (count == 6) {
								return true;
							}
						} else {
							count = 1;
						}
					}
				}
			}
			// 세로
			for (int k = 0; k < 12; k++) {
				flag[k] = 0;
			}
			count = 1;
			for (i = max(0, y - 5), j = 0; i <= min(y + 5, 19); i++, j++) {
				if (Dot.map[x][i] == -1) {
					flag[j] = 1;
					if (j > 0) {
						if (flag[j] == flag[j - 1] && flag[j] == 1) {
							count++;
							if (count == 6) {
								return true;
							}
						} else {
							count = 1;
						}
					}
				}
			}
			// 왼쪽 대각선
			pivot = comparing(x - 5, y - 5);
			for (int k = 0; k < 12; k++) {
				flag[k] = 0;
			}
			count = 1;
			for (i = x - pivot, j = 0, n = y - pivot; i <= min(19, x + 5) && n <= min(19, y + 5); i++, j++, n++) {
				if (Dot.map[i][n] == -1) {
					flag[j] = 1;
					if (j > 0) {
						if (flag[j] == flag[j - 1] && flag[j] == 1) {
							count++;
							if (count == 6) {
								return true;
							}
						} else {
							count = 1;
						}
					}
				}
			}
			// 오른쪽 대각선
			pivot = comparing2(x - 5, y + 5);
			pivot2 = comparing3(x + 5, y - 5);
			for (int k = 0; k < 12; k++) {
				flag[k] = 0;
			}
			count = 1;
			for (i = x - pivot, j = 0, n = y + pivot; i <= x + pivot2 && n >= y - pivot2; i++, j++, n--) {
				if (Dot.map[i][n] == -1) {
					flag[j] = 1;
					if (j > 0) {
						if (flag[j] == flag[j - 1] && flag[j] == 1) {
							count++;
							if (count == 6) {
								return true;
							}
						} else {
							count = 1;
						}
					}
				}
			}
		}
		return false;

	}

	// Congrats
	// 승자가 누구인지 말해줌 ->JOptionPane.showMessageDialog()

	public void Congrats(Boolean a) {

		if (a == true) {
			JOptionPane.showMessageDialog(congratulationMSG, String.format("Congratulations! Black win!"), "Message",
					JOptionPane.PLAIN_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(congratulationMSG, String.format("Congratulations! White win!"), "Message",
					JOptionPane.PLAIN_MESSAGE);
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	// reset
	// reset버튼 기능
	class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) { 
			for(int i=0;i<20;i++) {
				for(int j=0;j<20;j++) {
					Dot.map[i][j]=0;
				}	
			}
			for(int i=0;i<20;i++) {
				for(int j=0;j<20;j++) {
					slots[i][j]=null;
				}
			}
			click = new Dot();
			gMap = Dot.map;
			whoTurn = true;
			MainBoard.label.setText("Black");
			MainBoard.label.setForeground(Dot.CBlack);
			count = 0;
			ini=0;
			repaint();
		}
	}


	public int min(int a, int b) {
		if (a < b) {
			return a;
		}
		return b;
	}

	public int max(int a, int b) {
		if (a > b) {
			return a;
		}
		return b;
	}

	// 왼쪽 대각선 왼쪽 방향
	public int comparing(int b, int c) {
		if (0 > b || 0 > c) {
			if (b < c) {
				for (int i = 0; i < 12; i++) {
					if (b + i == 0) {
						return 5 - i;
					}
				}
			} else {
				for (int i = 0; i < 12; i++) {
					if (c + i == 0) {
						return 5 - i;
					}
				}
			}
		}
		return 5;
	}

	// 오른쪽 대각선 왼쪽방향
	public int comparing2(int b, int c) {
		int temp=0;
		int temp2=0;
		if (b < 0 || c > 19) {
			// x축 공간부족
			if (b < 0 && c < 20) {
				for (int i = 0; i < 12; i++) {
					if (b + i == 0) {
						return 5 - i;
					}
				}
			}
			// y축 공간 부족
			else if (b >= 0 && c > 19) {
				for (int i = 0; i < 12; i++) {
					if (c - i == 19) {
						return 5 - i;
					}
				}
			}
			// x 축, y축 둘다 부족
			else {
				for (int i = 0; i < 12; i++) {
					if (c - i == 19) {
						temp=i;
					}
				}
				for(int i=0;i<12;i++) {
					if (b + i == 0) {
						temp2=i;
					}
				}

				if(temp<temp2) {
					return 5-temp2;
				}
				
				return 5-temp;
				
			}
		}
		return 5;
	}

	// 오른쪽 대각선 오른쪽 방향
	public int comparing3(int b, int c) {
		int temp=0;
		int temp2=0;
		if (b > 19 || c < 0) {
			// x축 공간부족
			if (b > 19 && c >= 0) {
				for (int i = 0; i < 12; i++) {
					if (b - i == 19) {
						return 5 - i;
					}
				}
			}
			// y축 공간 부족
			else if (b <= 19 && c < 0) {
				for (int i = 0; i < 12; i++) {
					if (c + i == 0) {
						return 5 - i;
					}
				}
			}
			// x 축, y축 둘다 부족
			else {
				for (int i = 0; i < 12; i++) {
					if (b - i == 19) {
						temp=i;
						break;
					}
				}
				for(int i=0;i<12;i++) {
					if (c + i == 0) {
						temp2=i;
						break;
					}
				}
				
				if(temp<temp2) {
					return 5-temp2;
				}
				
				return 5-temp;
			}
		}
		return 5;
	}
}
