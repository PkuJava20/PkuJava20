public class Solution {
    public int trailingZeroes(int n) {//核心思想是找n!中所有乘数的含有因子5的个数，这个数目就是n!结果后零的个数
    	/*方法一:从1到n每个数都查找含有因子5的个数，再求总和，结果leetcode超时
    	if(n<=0)
    		return 0;
    	int count5=0;
        int p=0;
        int t=0;
        for(int i=1;i<=n;i++){
        	p=i%5;
        	t=i;
        	while(p==0){
        		count5++;
        		t=t/5;
        		p=t%5;
        	}
        }
        return count5;
        */
    	
    	/*方法二:分别求n!中含有因子5^1,5^2,5^3...的个数再求和，结果仍然超时
    	if(n<=0)
    		return 0;
    	int mi=1;
    	int count5=n/(int)Math.pow(5, mi);
    	int sum5=0;
    	while(count5!=0){
    		sum5=sum5+count5;
    		mi++;
    		count5=n/(int)Math.pow(5, mi);
    	}
    	return sum5;
    	*/
    	
    	//方法三思想和方法二类似，只不过求5的高阶幂次的因子个数建立在求5的低阶幂次的基础上，故时间复杂度降低很多
    	if(n<=0)
    		return 0;
    	int count5=n/5;//求含有因子5^1的个数
    	int sum5=0;//含有因子5的个数
    	while(count5!=0){//在n不含有5的某个高阶幂次因子情况下就退出判断，找到了所有因子5的个数
    		sum5=sum5+count5;//加上5的某个幂次因子个数
    		count5=count5/5;//求5的更高一阶幂次因子个数
    	}
    	return sum5;
    }
}