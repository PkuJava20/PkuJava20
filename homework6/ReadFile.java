import java.util.*;


public class ReadFile {

	/**
	 * @param args
	 */ 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String fileName1 = "C:/Users/姜昊/Desktop/test1.txt";
        String fileName2 = "C:/Users/姜昊/Desktop/test2.txt";

        FileVocabulary file1 =  new FileVocabulary(fileName1);
        FileVocabulary file2 =  new FileVocabulary(fileName2);
        
        TreeSet<String> intersection = new TreeSet<String>();//交集
        TreeSet<String> unionSet = new TreeSet<String>();//并集
        
        System.out.println("A文件的总字数为： "+file1.repeat.size());
        System.out.println("A文件的不重复单词总数为： "+file1.notRepeat.size());

        System.out.println("B文件的总字数为： "+file2.repeat.size());
        System.out.println("B文件的不重复单词总数为： "+file2.notRepeat.size());
        
        //求交集 
        for(String value :file1.notRepeat)
        {
        	if(file2.notRepeat.contains(value))
        		intersection.add(value);
        }
        //求并集
        for(String value :file1.notRepeat)
        {
        	unionSet.add(value);
        }
        for(String value :file2.notRepeat)
        {
        	unionSet.add(value);
        }
        
        //输出交集
        System.out.println("交集单词数： "+intersection.size());
        for(Iterator<String> it = intersection.iterator();it.hasNext();)
        {
        	System.out.print(it.next()+"  ");
        }
    	System.out.println();

        //输出并集
        System.out.println("并集单词数： "+unionSet.size());
        for(Iterator<String> it = unionSet.iterator();it.hasNext();)
        {
        	System.out.print(it.next()+"  ");
        }
    	System.out.println();

    	int count=0;
    	//求A中与B不重复的部分（A中与B不重复的单词占总字数的多少）
    	for(Iterator<String> it = file1.repeat.iterator();it.hasNext();)
        {
    		if(intersection.contains(it.next())) {
    			count++;
			}
        }
    	float f=(float)(file1.repeat.size()-count)/file1.repeat.size();
    	System.out.println("A中与B不重复的单词占总字数的"+f*100+"%");
    	
    	count=0;
    	//求B中与A不重复的部分（B中与A不重复的单词占总字数的多少）
    	for(Iterator<String> it = file2.repeat.iterator();it.hasNext();)
        {
    		if(intersection.contains(it.next())) {
    			count++;
			}
        }
    	f=(float)(file2.repeat.size()-count)/file2.repeat.size();
    	System.out.println("B中与A不重复的单词占总字数的"+f*100+"%");
	}

}
