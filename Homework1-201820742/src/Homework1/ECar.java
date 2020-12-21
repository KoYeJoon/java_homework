package Homework1;

public class ECar extends RentalCar{
	private int fee;
	static int cn=5;
	public ECar(int n) {
		super(n);
	}
	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	@Override
	int earnings(int time, int way) {
		setFee(time*3500+way*200);
		return getFee();
	}

	@Override
	int earnings(int time) {
		setFee(time*5000);
		return getFee();
	}
	public String toString() {
		return String.format("<Number : %d, Type : Electric, Fee/hour : 3500>",cn++);
	}
}
