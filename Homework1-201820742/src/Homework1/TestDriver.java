package Homework1;

import java.io.File;
import java.util.Scanner;

public class TestDriver {

	public static void main(String[] args) throws Exception {
		RCCompany com=new RCCompany();
		
		File rentreq = new File("src/RentalRequest.txt");
		Scanner scan = new Scanner(rentreq);
		while(scan.hasNext()) {
			String request[] = scan.nextLine().split(" ");
			User u = new User(request[0]);
			com.rent(u,request[1]);
		}
		System.out.println();
		File returnreq = new File("src/ReturnRequest.txt");
		Scanner scan1 = new Scanner(returnreq);
		while(scan1.hasNext()) {
			String request[] = scan1.nextLine().split(" ");
			User k = new User(request[0]);
			if(request.length==4) {
				com.back2(k,request[1],request[2],request[3]);
			}
			else {
				com.back(k,request[1],request[2],request[3],request[4]);
			}
		}
		
		
	}

}
