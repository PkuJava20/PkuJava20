import java.util.Scanner;
public class climbingstairs {
	public int climbStairs(int n) {
        //쳲������ģ��
		//Ϊ�˲���ʱ�䳬ʱ�����ÿռ任ʱ�䣬�����õݹ�
		int []a=new int[100];
		a[0]=0;
		a[1]=1;
		a[2]=2;
		for(int i=3;i<100;i++)
			a[i]=a[i-1]+a[i-2];
		return a[n];
       
    }
	public static void main(String[] agrs){
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		climbingstairs kinds=new climbingstairs();
		int result=kinds.climbStairs(n);
		System.out.println(result);
	}
}
