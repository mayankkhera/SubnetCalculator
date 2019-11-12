
public class Calculations {

	private Subnet sub;
	private String wireAdd;
	private int[] wa;
	private String[] sid;
	private int dsnm;
	private int[] subnet;
	private int bits;
	private int subnetIndex;

	public Calculations(Subnet x) {
		sub = x;
		subnet = new int[32];
		for (int i = 0; i < subnet.length; i++) {
			subnet[i] = sub.getSubnet(i);
		}
	}

	private String getIPClass() {
		if (sub.getIP(0) < 127) {
			dsnm = 8;
			return "A";
		} else if (sub.getIP(0) < 192 && sub.getIP(0) > 127) {
			dsnm = 16;
			return "B";
		} else if (sub.getIP(0) < 223 && sub.getIP(0) > 191) {
			dsnm = 24;
			return "C";
		} else if (sub.getIP(0) == 127) {
			dsnm = 0;
			return "Reserved";
		} else {
			dsnm = 32;
			return "D";
		}
	}

	private String getIPtype() {
		if (sub.getIP(0) == 127)
			return "Reserved";
		if (getIPClass().equals("IPv4"))
			return "Miracast";
		if ((sub.getIP(0)==10) || (sub.getIP(0)==172 && sub.getIP(1)>15 && sub.getIP(1)<32) || (sub.getIP(0)==192 && sub.getIP(1) == 168))
			return "Private";
		else
			return "Public";
	}

	public void printIPClass() {
		System.out.println("IP Class: " + getIPClass());
		System.out.println("Type: " + getIPtype());
	}

	public void printSID() {
		if (getIPClass().equals("Reserved"))
			return;
		wireAdd = Integer.toString(sub.getIP(0) & sub.getSnm(0)) + "." + 
			      Integer.toString(sub.getIP(1) & sub.getSnm(1)) + "." + 
			      Integer.toString(sub.getIP(2) & sub.getSnm(2)) + "." + 
			      Integer.toString(sub.getIP(3) & sub.getSnm(3));
		sid = wireAdd.split("\\.");
		wa = new int[sid.length];
		for (int i = 0; i < sid.length; i++) {
			wa[i] = Integer.parseInt(sid[i]);
		}
		bits = sub.getSub() - dsnm;
		subnetIndex = si();
		System.out.println("Wire Address: " + wireAdd + "/" + sub.getSub());
		System.out.println("Number of bits borrowed: "+bits);
		System.out.println("Number of subnets: " + subnet());
		System.out.println("Subnet index: " + subnetIndex);
		System.out.println("Number of hosts: " + hosts());
		System.out.println("Number of usable hosts: " + (hosts() - 2));
	}

	private int subnet() {
		getIPClass();
		return ((int) Math.pow(2, bits));
	}

	private int hosts() {
		getIPClass();
		return ((int) Math.pow(2, 32 - sub.getSub()));
	}
	// String a = Integer.toBinaryString(sub.getIP(0) &
	// sub.getSnm(0))+Integer.toBinaryString(sub.getIP(1) &
	// sub.getSnm(1))+Integer.toBinaryString(sub.getIP(2) &
	// sub.getSnm(2))+Integer.toBinaryString(sub.getIP(3) & sub.getSnm(3));

	public void subprint() {
		if(getIPClass().equals("Reserved") || getIPClass().equals("D"))
			return;
		/*if (sub.getSub()>7 && sub.getSub()<16) {
			for (int i = 0; i < subnet(); i++) {
				for (int j = 0; j < 256; j++) {
					for (int k = 0; k < 256; k++) {
						for (int l = 0; l < 256; l = l + hosts()) {
							System.out.print(sid[0] + ".");
							System.out.print(j + ".");
							System.out.print(k + ".");
							System.out.print(l + "\t\t");
							System.out.print(sid[0] + "." + j + "." + "." + k + "." + (l + 1) + "\t");
							System.out.print(sid[0] + "." + j + "." + "." + k + "." + (l + hosts() - 2) + "\t");
							System.out.println(sid[0] + "." + j + "." + "." + k + "." + (l + hosts() - 1) + "\t");
						}
					}
				}
			}
		} else if (sub.getSub()>15 && sub.getSub()<24) {
			for (int i = 0; i < subnet(); i++) {
				for (int j = 0; j < 256; j++) {
					for (int k = 0; k < 256; k = k + hosts()) {
						System.out.print(sid[0] + "." + sid[1] + ".");
						System.out.print(j + ".");
						System.out.print(k + "\t\t");
						System.out.print(sid[0] + "." + sid[1] + "." + j + "." + (k + 1) + "\t\t");
						System.out.print(sid[0] + "." + sid[1] + "." + j + "." + (k + hosts() - 2) + "\t\t");
						System.out.println(sid[0] + "." + sid[1] + "." + j + "." + (k + hosts() - 1) + "\t\t");
					}
				}
			}
		} else if (sub.getSub()>23 && sub.getSub()<33) {

			for (int i = 0; i < 256; i = i + hosts()) {
				System.out.print(sid[0] + "." + sid[1] + "." + sid[2] + ".");
				System.out.print(i + "\t\t");
				System.out.print(sid[0] + "." + sid[1] + "." + sid[2] + "." + (i + 1) + "\t\t");
				System.out.print(sid[0] + "." + sid[1] + "." + sid[2] + "." + (i + hosts() - 2) + "\t\t");
				System.out.println(sid[0] + "." + sid[1] + "." + sid[2] + "." + (i + hosts() - 1) + "\t\t");

			}
		} else
			return;*/
		int[] a = wa;
		
		if(getIPClass().equals("A")) {
			a[1]=0;
			a[2]=0;
			a[3]=0;
		}else if(getIPClass().equals("B")) {
			a[2]=0;
			a[3]=0;
		}else
			a[3]=0;
		
		/*if(sub.getSub()>7 && sub.getSub()<16) {
			a[1] = 0;
			a[2] = 0;
			a[3] = 0;
		}else if(sub.getSub()>15 && sub.getSub()<24) {
			a[2] = 0;
			a[3] = 0;
		}else if(sub.getSub()>23 && sub.getSub()<33) {
			a[3] = 0;
		}else {
			for(int i=0; i<a.length; i++) {
				a[i] = 0;
			}
		}
		
		
		for(int i=0; i<subnet(); i++) {
			if(a[3]>255) {
				a[3] = 0;
				a[2] = a[2] + 1;
			}else if(a[2]>255) {
				a[2] = 0;
				a[1] = a[1] + 1;
			}else if(a[1]>255) {
				a[1] = 0;
				a[0] = a[0] + 1;
			}else if(hosts()>256){
				System.out.print(a[0]+"."+a[1]+"."+a[2]+"."+a[3]+"\t\t");
				System.out.print(a[0]+"."+a[1]+"."+(a[2]++)+"."+(a[3]+1)+"\t\t");
				System.out.print(a[0]+"."+a[1]+"."+(a[2])+"."+(a[3]+hosts()-258)+"\t\t");
				System.out.println(a[0]+"."+a[1]+"."+a[2]+"."+(a[3]+hosts()-257)+"\t\t");
				a[3]+= hosts();
			}else {
				System.out.print(a[0]+"."+a[1]+"."+a[2]+"."+a[3]+"\t\t");
				System.out.print(a[0]+"."+a[1]+"."+a[2]+"."+(a[3]+1)+"\t\t");
				System.out.print(a[0]+"."+a[1]+"."+a[2]+"."+(a[3]+hosts()-2)+"\t\t");
				System.out.println(a[0]+"."+a[1]+"."+a[2]+"."+(a[3]+hosts()-1)+"\t\t");
				a[3]+=hosts();
			}*/

			for(int i=0; i<subnet(); i++) {
				System.out.print(add(a,0)+"\t\t");
				System.out.print(add(a,1)+"\t\t");
				System.out.print(add(a,(hosts()-3))+"\t\t");
				System.out.println(add(a,1)+"\t\t");
				a[3]+=1;
		}
		
	}
	
	private String add(int[] a, int add) {
		int count = add;
		do {
			/*f(count>255) {
				a[3]+=255;
				count-=255;
			}else {
				a[3]+=count;
				count = 0;
			}*/
			a[3]+=count;
			count=0;
			
			if(a[3]>255) {
				a[3] = a[3]-256;
				a[2] = a[2] + 1;
			}else if(a[2]>255) {
				a[2] = a[2]-256;
				a[1] = a[1] + 1;
			}else if(a[1]>255) {
				a[1] = a[1]-256;
				a[0] = a[0] + 1;
			}
					
		}while(a[0]>255||a[1]>255||a[2]>255||a[3]>255);
		
		
		return (a[0]+"."+a[1]+"."+a[2]+"."+a[3]);
	}
	
	private int si() {
		int[] a = wa;
		String temp = "";
		if(getIPClass().equals("A")) {
			a[1]=0;
			a[2]=0;
			a[3]=0;
		}else if(getIPClass().equals("B")) {
			a[2]=0;
			a[3]=0;
		}else
			a[3]=0;
		
		for(int i=0; i<subnet(); i++) {
			temp = (add(a,0));
			add(a,1);
			add(a,(hosts()-3));
			add(a,1);
			a[3]+=1;
			if(temp.equals(wireAdd))
				return i;
		}
		return 0;
	}
	
}
