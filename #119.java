public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> lst = new ArrayList<Integer>();
        lst.add(1);  
        List<Integer> temp = new ArrayList<Integer>(lst);  
        for (int i = 1; i <= rowIndex; i++) {   
        	lst.clear();  
        	lst.add(1);  
            for (int j = 1; j < temp.size(); j++) {  
            	lst.add(temp.get(j) + temp.get(j-1));  
            }  
            lst.add(1);  
            temp.clear();  
            temp.addAll(lst);  
        }  
        return lst;
    }
    /*public long getResult(int i,int j)//组合，j个里面取i个
    {
        if(i==0&&j==0)
          return 1;
        if(i==0||i==j) return 1;
        if(i>=(j/2 + 1))
            i=j-i;
        long mulpi1=1;
        long mulpi2=1;
        while(i>0)
        {
            mulpi1*=i;
            mulpi2*=j;
            i--;j--;
        }
        return mulpi2/mulpi1;
    }*/
}