import java.text.DateFormat;
import java.text.DecimalFormat;
import  java.util.HashSet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class twotxt {
	//��Ĭ���ı���ֻ�е��ʲ����������κ�������ţ����磺�ʺţ���ţ�̾�š�������������
	public static void main(String [] agrs){
	    HashSet set1=new HashSet();
	   	HashSet set2=new HashSet();
	   	String [] a1=null;
	   	String [] b1=null;
		//��ȡ��һ���ļ�������
	 System.out.println("�������ļ�1��λ�ã�");
   	 Scanner in=new Scanner(System.in);
   	 String name1=in.nextLine();
   	 File a=new File(name1);//"D:\\txt1.txt"
   	 if(a.exists()&&a.isFile()){
   		 System.out.println("Read From "+name1);
   	 }
   	 else{
   		 System.out.println("File not exist");
   	 }
   	 System.out.println("�����ǣ�");
   	 try{
   		 FileReader fr=new FileReader(a);
   		 BufferedReader br=new BufferedReader(fr);
   		 String content=null;
   		 StringBuffer temp=new StringBuffer();
   		 while((content=br.readLine())!=null){
   			 temp.append("  ");      //��ֹ��һ�еĵ�һ������ֱ�Ӻ���һ�е����һ����������
   			// System.out.println(content);
   			 temp.append(content);
   		 }
   		 String txt1=temp.toString();
   		// System.out.print(temp);
   		// System.out.println();
   	//	 System.out.println("***1****");
   	     a1=txt1.split(" ");
   	     for(int i=0;i<a1.length;i++){
   	    	 System.out.print(a1[i]+" ");
   	    	 set1.add(a1[i]);
   	     }
   		 fr.close();
   		 br.close();
   	 }catch(FileNotFoundException e){
   		 System.out.println("�Ҳ����ļ�"+e.getMessage());
   	 }catch(IOException e){
   		 e.printStackTrace();
   	 }
   	System.out.println();
   	 System.out.println("�������ļ�2��λ�ã�");
   	// Scanner in=new Scanner(System.in);
   	 String name2=in.nextLine();
   	 File b=new File(name2);//"D:\\txt1.txt"
   	 if(b.exists()&&b.isFile()){
   		 System.out.println("Read From "+name2);
   	 }
   	 else{
   		 System.out.println("File not exist");
   	 }
   	 System.out.println("�����ǣ�");
   	 try{
   		 FileReader fr=new FileReader(b);
   		 BufferedReader br=new BufferedReader(fr);
   		 String content=null;
   		 StringBuffer temp=new StringBuffer();
   		 while((content=br.readLine())!=null){
   			 temp.append("  ");      //��ֹ��һ�еĵ�һ������ֱ�Ӻ���һ�е����һ����������
   			// System.out.println(content);
   			 temp.append(content);
   		 }
   		 String txt1=temp.toString();
   		// System.out.print(temp);
   		// System.out.println();
   		// System.out.println("*******");
   	    b1=txt1.split(" ");
   	     for(int i=0;i<b1.length;i++){
   	    	 System.out.print(b1[i]+" ");
   	    	 set2.add(b1[i]);
   	     }
   	     System.out.println();
   		 fr.close();
   		 br.close();
   	 }catch(FileNotFoundException e){
   		 System.out.println("�Ҳ����ļ�"+e.getMessage());
   	 }catch(IOException e){
   		 e.printStackTrace();
   	 }
   	// System.out.println("ʵ�ֽ�������");
   	
   	 //ʵ�ֽ�������
   	 HashSet set3=new HashSet();
   	 StringBuffer bing=new StringBuffer();
   	 StringBuffer jiao=new StringBuffer();
   	 //�����㿪ʼ
   	System.out.println("ʵ�ֲ�����:");
   	 for(int i=0;i<a1.length;i++){
   		 if(set3.add(a1[i]))    bing.append(" "+a1[i]);
   	 }
     for(int i=0;i<b1.length;i++){
  		 if(set3.add(b1[i]))    bing.append(" "+b1[i]);
  	 }
     String txt3=bing.toString();
     System.out.println(txt3);
     //���������
     
     //�����㿪ʼ
     HashSet set4=new HashSet();
     System.out.println("ʵ�ֲ�������:");
     for(int i=0;i<a1.length;i++){
   		 if(set2.contains(a1[i]))   { 
   			 jiao.append(" "+a1[i]);
   			 set4.add(a1[i]);
   		 }
   	 }
     for(int i=0;i<b1.length;i++){
  		 if(set1.contains(b1[i])&&!set4.contains(b1[i]))    jiao.append(" "+b1[i]);
  	 }
     String txt4=jiao.toString();
     System.out.println(txt4);
     //���������
     System.out.println("�ļ�A�еĵ�������"+a1.length+"      "+"�ļ�B�еĵ�������"+b1.length);
     int count1=0,count2=0;
     for(int i=0;i<a1.length;i++){
    	 if(!set2.contains(a1[i]) )  count1++;
     }
     for(int i=0;i<b1.length;i++){
    	 if(!set1.contains(b1[i]) )  count2++;
     }
     DecimalFormat df = new DecimalFormat("#####.00");//��ʽ��С��������Ĳ�0
    // String filesize = df.format(size);//���ص���String���͵�
     double result1=(100.0*count1)/a1.length;
     double result2=(100.0*count2)/b1.length;
     System.out.println("�����ļ�A��������B�ĵ���ռA�е������ģ�"+df.format(result1)+"%");
     System.out.println("�����ļ�b��������A�ĵ���ռB�е������ģ�"+df.format(result2)+"%");
	}
}
