import java.util.Scanner;
public class intToRoman {
	 //private static final ArrayList String = null;

	public String intToRoman1(int num) {
		// ArrayList<String>=new ArrayList<String>();
		 StringBuffer s=new StringBuffer();
		 while(num>=1000){
			 s.append("M");
			 num-=1000;
		 }
		 while(num>=900){
			 s.append("CM");
			 num-=900;
		 }  
		 while(num>=500){
			 s.append("D");
			 num-=500;
		 }
		 while(num>=400){
			 s.append("CD");
			 num-=400;
		 }  
		 while(num>=100){
			 s.append("C");
			 num-=100;
		 }
		 while(num>=90){
			 s.append("XC");
			 num-=90;
		 }  
		 while(num>=50){
			 s.append("L");
			 num-=50;
		 }
		 while(num>=40){
			 s.append("XL");
			 num-=40;
		 }  
		 while(num>=10){
			 s.append("X");
			 num-=10;
		 }
		 while(num>=9){
			 s.append("IX");
			 num-=9;
		 }  
		 while(num>=5){
			 s.append("V");
			 num-=5;
		 }
		 while(num>=4){
			 s.append("IV");
			 num-=4;
		 }  
		 while(num>0){
			 s.append("I");
			 num--;
		 }
		 return s.toString();
	    }
	public static void main(String[] agrs){
		int n;
		Scanner in= new Scanner(System.in);
		n=in.nextInt();
		 intToRoman test=new  intToRoman();
		 String s=test.intToRoman1(n);
		 System.out.print(s);
	}
}
