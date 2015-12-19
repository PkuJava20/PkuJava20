public class Solution {
    public int trailingZeroes(int n) {
		int zeroes = 0;
		int power =0;
		int temp =n;
		while(temp/5>0){
			power++;
			temp/=5;
		}
		for(int i=1;i<=power;i++){
			int product =1;
			for(int j=1;j<=i;j++)
				product = product* 5;
			zeroes += n/product;
		}
			
        return zeroes;
    }
}