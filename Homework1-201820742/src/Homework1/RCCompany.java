package Homework1;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * 없는 차량의 검색, 중복된 이름의 입력, 중복 차량의 대여
 * 없는 차량 =type가 0,1이 아닌 경우?ㅇ
 * 중복된 이름 : 한사람은 한대만 빌릴 수 있도록 해야하나?ㅇ
 * 중복된 차량의 대여 : 어떤 경우가 중복된 차량이 대여가 되지?
 * 한 사람 당 한
 * */
public class RCCompany {
	private int carN;
	ArrayList<User> users = new ArrayList<>();
	ArrayList<RentalCar> rCar = new ArrayList<>();
	private static int nn = 0;
	private static int en = 5;
	private static int earning = 0;

	public RCCompany() {
		rCar.add(new NormalCar(0));
		rCar.add(new NormalCar(1));
		rCar.add(new NormalCar(2));
		rCar.add(new NormalCar(3));
		rCar.add(new NormalCar(4));
		rCar.add(new ECar(5));
		rCar.add(new ECar(6));
		rCar.add(new ECar(7));
		rCar.add(new ECar(8));
		rCar.add(new ECar(9));
	}

	public int getCarN() {
		return carN;
	}

	public void setCarN(int carN) {
		this.carN = carN;
	}

	// rent
	public void rent(User u, String type) {
		int ty = Integer.parseInt(type);
		for (User k : users) {
			if (k.getName().equalsIgnoreCase(u.getName())) {
				System.out.printf("%s has already rented Car.\n", u.getName());
				return;
			}
		}
		if (type.equalsIgnoreCase("0") && nn < 5) {
			u.setCar(rCar.get(nn));
			u.getCar().setCarNum(nn);
			u.getCar().setFlag(true);
			RentalCar n = rCar.get(nn);
			rCar.get(nn).setFlag(true);
			users.add(u);
			System.out.print(u.getName());
			System.out.println(n.toString());
			nn = nn + 1;
		} else if (type.equalsIgnoreCase("0") && nn >= 5) {
			System.out.println("general Car is already rented.");
		}
		if (type.equalsIgnoreCase("1") && en < 10) {
			u.setCar(rCar.get(en));
			u.getCar().setCarNum(en);
			u.getCar().setFlag(true);
			RentalCar e = rCar.get(en);
			rCar.get(en).setFlag(true);
			users.add(u);
			System.out.print(u.getName());
			System.out.println(e.toString());
			en = en + 1;
		} else if (type.equalsIgnoreCase("1") && en >= 10) {
			System.out.println("Electronic Car is already rented.");
		}
		if (!(ty == 0 || ty == 1)) {
			System.out.println("This type doesn't exist");
		}
	}

	// return
	public void back(User u, String type, String num, String time, String way) {
		int flag = 0;
		int ty = Integer.parseInt(type);
		int n = Integer.parseInt(num);
		if (ty == 0) {
			RentalCar car = new NormalCar(n);
			u.setCar(car);
			u.getCar().setCarNum(n);
			u.getCar().setFlag(true);
		} else if (ty == 1) {
			RentalCar car = new ECar(n);
			u.setCar(car);
			u.getCar().setCarNum(n);
			u.getCar().setFlag(true);
		}
		else if(ty!=0 && ty!=1) {
			System.out.printf("<Error> %s tried to invalid return\n", u.getName());
			return;
		}

		for (User r : users) {
			for (RentalCar c : rCar) {
				if (u.equals(r)&&u.getCar().equals(c)) {
					int t = Integer.parseInt(time);
					int w = Integer.parseInt(way);
					if (u.getCar() instanceof NormalCar) {
						earning = earning + u.getCar().earnings(t, w);
						System.out.printf("%s paid %d won, Rental Car Service earned %d won today.\n", u.getName(),
								u.getCar().earnings(t, w), earning);
						c.setFlag(false);
						r.getCar().setFlag(false);

						flag = 1;
					} else if (u.getCar() instanceof ECar) {
						earning = earning + u.getCar().earnings(t, w);
						System.out.printf("%s paid %d won, Rental Car Service earned %d won today.\n", u.getName(),
								u.getCar().earnings(t, w), earning);
						c.setFlag(false);
						r.getCar().setFlag(false);
						flag = 1;
					}
				}

			}

		}
		if (flag == 0) {
			System.out.printf("<Error> %s tried to invalid return\n", u.getName());
		}
	}

	public void back2(User u, String type, String num, String time) {
		int flag = 0;
		int n = Integer.parseInt(num);
		int ty = Integer.parseInt(type);
		if (ty == 0) {
			RentalCar car = new NormalCar(n);
			u.setCar(car);
			u.getCar().setCarNum(n);
			u.getCar().setFlag(true);

		} else if (ty == 1) {
			RentalCar car = new ECar(n);
			u.setCar(car);
			u.getCar().setCarNum(n);
			u.getCar().setFlag(true);

		}
		else if(ty!=0 && ty!=1) {
			System.out.printf("<Error> %s tried to invalid return\n", u.getName());
			return;
		}
		for (User r : users) {
				for (RentalCar c : rCar) {
					if (u.equals(r)&&u.getCar().equals(c)) {
						int t = Integer.parseInt(time);
						if (u.getCar() instanceof NormalCar) {
							earning = earning + u.getCar().earnings(t);
							System.out.printf("%s paid %d won, Rental Car Service earned %d won today.\n", u.getName(),
									u.getCar().earnings(t), earning);
							c.setFlag(false);
							r.getCar().setFlag(false);

							flag = 1;
						} else if (u.getCar() instanceof ECar) {
							earning = earning + u.getCar().earnings(t);
							System.out.printf("%s paid %d won, Rental Car Service earned %d won today.\n", u.getName(),
									u.getCar().earnings(t), earning);
							c.setFlag(false);
							u.getCar().setFlag(false);
							flag = 1;
						}
					}

				}

			// 차량 반납 & 배열 초기화 -안해도ㅇ

		}
		if (flag == 0) {
			System.out.printf("<Error> %s tried to invalid return\n", u.getName());
		}
	}

	public static int getNn() {
		return nn;
	}

	public static void setNn(int nn) {
		RCCompany.nn = nn;
	}

	public static int getEn() {
		return en;
	}

	public static void setEn(int en) {
		RCCompany.en = en;
	}

}
