import java.util.Scanner;

public class Subnet {
	
	private String ipAdd;
	private String[] ipa;
	private int[] ip;
	private Scanner input;
	private int sub;
	private String [] sn;
	private String snmk;
	private String subnet[];
	private int[] snm;
	
	public Subnet() {
		input = new Scanner(System.in);
		ipAdd = "";
		sub = 0;
		sn = new String[4];
		snmk = "";
		subnet = new String[32];
		snm = new int[4];
	}
	
	public void scanIP() {
		do {
			System.out.print("Enter IPv4 IP address: ");
			ipAdd = input.next();
			ipa = ipAdd.split("\\.");
			ip = new int[ipa.length];
			for(int i=0; i<ipa.length; i++) {
				ip[i] = new Integer(Integer.parseInt(ipa[i]));
			}
			if(!validateIP(ip))
				System.out.println("Invalid ip entered, please try again!!");
		}while(!validateIP(ip));
	}
	
	private boolean validateIP(int[] ip) {
		
		if(ip.length > 4)
			return false;
		
		for(int i=0; i<ip.length; i++) {
			if(ip[i] < 0 || ip[i] > 255)
				return false;
			if(ip[0]<1)
				return false;
		}
		
		return true;
	}
	
	public void scanSubnet() {
		do {
			System.out.print("Enter subnet bits from 1-32: ");
			sub = input.nextInt();
			if(sub<1 || sub>32)
				System.out.println("Invalid subnet mask entered, Please try again!!");
		}while(sub<1 || sub>32);
		System.out.println("Subnet mask is: "+makeSnm(sub));
		splitSnm();
	}
	
	private String makeSnm(int sub) {
		
		for(int i=0; i<subnet.length; i++) {
			if(i<sub)
				subnet[i] = "1";
			else
				subnet[i] = "0";
		}
		
		String x = "";
		
		for(int i=0; i<subnet.length; i++) {
			x = x+subnet[i];
		}
		
		for(int i=0; i<sn.length; i++) {
			sn[i] = "";
		}
		
		for(int i=0; i<subnet.length; i++) {
			if(i<8)
				sn[0] = sn[0] + subnet[i];
			if(i<16 && i>7)
				sn[1] = sn[1] + subnet[i];
			if(i<24 && i>15)
				sn[2] = sn[2] + subnet[i];
			if(i<32 && i>23)
				sn[3] = sn[3] + subnet[i];
		}
		
		snmk = Integer.toString(Integer.parseInt(sn[0],2))+"."+Integer.toString(Integer.parseInt(sn[1],2))+"."+Integer.toString(Integer.parseInt(sn[2],2))+"."+Integer.toString(Integer.parseInt(sn[3],2));
		
		return snmk;
	}
	
	private void splitSnm() {
		for(int i=0; i<snm.length; i++) {
			snm[i] = Integer.parseInt(sn[i],2);
		}
	}
	
	public int getSnm(int element) {
		splitSnm();
		return snm[element];
	}
	
	public int getIP(int element) {
		return ip[element];
	}
	
	public int getSub() {
		return sub;
	}
	
	public int getSubnet(int element) {
		return Integer.parseInt(subnet[element]);
	}
	
	
}
