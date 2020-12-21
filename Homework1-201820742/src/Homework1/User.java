package Homework1;

public class User {
	private String name;
	private RentalCar car;


	public User(String s1) {
		this.name=s1;
	}
	public User(String s1, RentalCar c1)
	{
		this.name=s1;
		this.setCar(c1);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RentalCar getCar() {
		return car;
	}
	public void setCar(RentalCar car) {
		this.car = car;
	}
	
	@Override
	public boolean equals(Object o) {
		User user = (User)o;
		if(this==o) return true;
		if(o==null) return false;
		if(getClass()!=o.getClass()) return false;
		
		return (this.getName().equals(user.getName())&&this.getCar().getCarNum()==user.getCar().getCarNum());
	
		
	}

	
}
