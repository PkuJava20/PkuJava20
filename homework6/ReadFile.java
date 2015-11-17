import java.util.*;


public class ReadFile {

	/**
	 * @param args
	 */ 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String fileName1 = "C:/Users/���/Desktop/test1.txt";
        String fileName2 = "C:/Users/���/Desktop/test2.txt";

        FileVocabulary file1 =  new FileVocabulary(fileName1);
        FileVocabulary file2 =  new FileVocabulary(fileName2);
        
        TreeSet<String> intersection = new TreeSet<String>();//����
        TreeSet<String> unionSet = new TreeSet<String>();//����
        
        System.out.println("A�ļ���������Ϊ�� "+file1.repeat.size());
        System.out.println("A�ļ��Ĳ��ظ���������Ϊ�� "+file1.notRepeat.size());

        System.out.println("B�ļ���������Ϊ�� "+file2.repeat.size());
        System.out.println("B�ļ��Ĳ��ظ���������Ϊ�� "+file2.notRepeat.size());
        
        //�󽻼� 
        for(String value :file1.notRepeat)
        {
        	if(file2.notRepeat.contains(value))
        		intersection.add(value);
        }
        //�󲢼�
        for(String value :file1.notRepeat)
        {
        	unionSet.add(value);
        }
        for(String value :file2.notRepeat)
        {
        	unionSet.add(value);
        }
        
        //�������
        System.out.println("������������ "+intersection.size());
        for(Iterator<String> it = intersection.iterator();it.hasNext();)
        {
        	System.out.print(it.next()+"  ");
        }
    	System.out.println();

        //�������
        System.out.println("������������ "+unionSet.size());
        for(Iterator<String> it = unionSet.iterator();it.hasNext();)
        {
        	System.out.print(it.next()+"  ");
        }
    	System.out.println();

    	int count=0;
    	//��A����B���ظ��Ĳ��֣�A����B���ظ��ĵ���ռ�������Ķ��٣�
    	for(Iterator<String> it = file1.repeat.iterator();it.hasNext();)
        {
    		if(intersection.contains(it.next())) {
    			count++;
			}
        }
    	float f=(float)(file1.repeat.size()-count)/file1.repeat.size();
    	System.out.println("A����B���ظ��ĵ���ռ��������"+f*100+"%");
    	
    	count=0;
    	//��B����A���ظ��Ĳ��֣�B����A���ظ��ĵ���ռ�������Ķ��٣�
    	for(Iterator<String> it = file2.repeat.iterator();it.hasNext();)
        {
    		if(intersection.contains(it.next())) {
    			count++;
			}
        }
    	f=(float)(file2.repeat.size()-count)/file2.repeat.size();
    	System.out.println("B����A���ظ��ĵ���ռ��������"+f*100+"%");
	}

}
