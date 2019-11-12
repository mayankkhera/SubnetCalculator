
public class TempMain {
	
	public static void main(String[] args) {
		System.out.println("Welcome to IP Subnet Calculator");
		System.out.println("--------Created by Mayank Khera");
		System.out.println();
		System.out.println();
		Subnet sub = new Subnet();
		
		sub.scanIP();
		sub.scanSubnet();
		Calculations cal = new Calculations(sub);
		cal.printIPClass();
		cal.printSID();
		System.out.println();
		System.out.println("Network Address\t\tFirst host IP\t\tLast host IP\t\tBroadcast Address");
		cal.subprint();
		System.out.println("_______________________________");
		System.out.println("Have a good day!!");
	}

}
