package Homework1;

import java.util.ArrayList;

public abstract class RentalCar {
	private int carNum;
	private Boolean flag;
	
	public RentalCar(int n) {
		this.setCarNum(n);
		this.flag=false;
	}
	abstract int earnings(int time, int way);
	abstract int earnings(int time);
	
	public int getCarNum() {
		return carNum;
	}
	public void setCarNum(int carNum) {
		this.carNum = carNum;
	}
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	
	@Override
	public boolean equals(Object o) {
		RentalCar r = (RentalCar)o;
		if(this==o) return true;
		if(o==null) return false;
		if(getClass()!=o.getClass()) return false;
		
		return this.carNum==r.carNum && this.getFlag()==r.getFlag();
		
		
	}
}
