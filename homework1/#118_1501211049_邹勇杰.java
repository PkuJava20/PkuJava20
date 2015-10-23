public class Solution {
    public List<List<Integer>> generate(int numRows) {
         //List<List<Integer>> list =null;
        List<List<Integer>> list = new ArrayList();
        for(int i=0;i<numRows;i++){
        	//List<Integer> abc=null;
        	List<Integer> abc = new ArrayList();
        	if(i==0)
        		abc.add(1);
        	else
        	{
        		for(int j=0;j<=i;j++){
        			if(j==0||j==i)  //每行首位元素置1
        				abc.add(1);
        			else
        				//abc.add(list[i-1][j]+list[i-1][j-1]);
        				abc.add(list.get(i-1).get(j)+list.get(i-1).get(j-1));//由上一行推下一行
        		}
        	}
        	list.add(abc);
        }
        return list;
    }
}