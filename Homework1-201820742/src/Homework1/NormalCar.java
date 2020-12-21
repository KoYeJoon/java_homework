package Homework1;

public class NormalCar extends RentalCar{
	private int fee;
	static int cn=0;
	public NormalCar(int n) {
		super(n);
	}
	@Override
	int earnings(int time, int way) {
		setFee(time*4000+way*200);
		return getFee();
	}
	
	@Override
	int earnings(int time) {
		setFee(time*6000);
		return getFee();
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}
	public String toString() {
		return String.format("<Number : %d, Type : General, Fee/hour : 4000>",cn++);
	}
	
}
