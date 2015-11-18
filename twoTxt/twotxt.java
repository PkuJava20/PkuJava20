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
	//我默认文本中只有单词不包含其他任何特殊符号（例如：问号，句号，叹号。。。。。。）
	public static void main(String [] agrs){
	    HashSet set1=new HashSet();
	   	HashSet set2=new HashSet();
	   	String [] a1=null;
	   	String [] b1=null;
		//读取第一个文件的内容
	 System.out.println("请输入文件1的位置：");
   	 Scanner in=new Scanner(System.in);
   	 String name1=in.nextLine();
   	 File a=new File(name1);//"D:\\txt1.txt"
   	 if(a.exists()&&a.isFile()){
   		 System.out.println("Read From "+name1);
   	 }
   	 else{
   		 System.out.println("File not exist");
   	 }
   	 System.out.println("内容是：");
   	 try{
   		 FileReader fr=new FileReader(a);
   		 BufferedReader br=new BufferedReader(fr);
   		 String content=null;
   		 StringBuffer temp=new StringBuffer();
   		 while((content=br.readLine())!=null){
   			 temp.append("  ");      //防止下一行的第一个单词直接和上一行的最后一个单词连接
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
   		 System.out.println("找不到文件"+e.getMessage());
   	 }catch(IOException e){
   		 e.printStackTrace();
   	 }
   	System.out.println();
   	 System.out.println("请输入文件2的位置：");
   	// Scanner in=new Scanner(System.in);
   	 String name2=in.nextLine();
   	 File b=new File(name2);//"D:\\txt1.txt"
   	 if(b.exists()&&b.isFile()){
   		 System.out.println("Read From "+name2);
   	 }
   	 else{
   		 System.out.println("File not exist");
   	 }
   	 System.out.println("内容是：");
   	 try{
   		 FileReader fr=new FileReader(b);
   		 BufferedReader br=new BufferedReader(fr);
   		 String content=null;
   		 StringBuffer temp=new StringBuffer();
   		 while((content=br.readLine())!=null){
   			 temp.append("  ");      //防止下一行的第一个单词直接和上一行的最后一个单词连接
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
   		 System.out.println("找不到文件"+e.getMessage());
   	 }catch(IOException e){
   		 e.printStackTrace();
   	 }
   	// System.out.println("实现交并运算");
   	
   	 //实现交并运算
   	 HashSet set3=new HashSet();
   	 StringBuffer bing=new StringBuffer();
   	 StringBuffer jiao=new StringBuffer();
   	 //并运算开始
   	System.out.println("实现并运算:");
   	 for(int i=0;i<a1.length;i++){
   		 if(set3.add(a1[i]))    bing.append(" "+a1[i]);
   	 }
     for(int i=0;i<b1.length;i++){
  		 if(set3.add(b1[i]))    bing.append(" "+b1[i]);
  	 }
     String txt3=bing.toString();
     System.out.println(txt3);
     //并运算结束
     
     //交运算开始
     HashSet set4=new HashSet();
     System.out.println("实现并交运算:");
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
     //交运算结束
     System.out.println("文件A中的单词数："+a1.length+"      "+"文件B中的单词数："+b1.length);
     int count1=0,count2=0;
     for(int i=0;i<a1.length;i++){
    	 if(!set2.contains(a1[i]) )  count1++;
     }
     for(int i=0;i<b1.length;i++){
    	 if(!set1.contains(b1[i]) )  count2++;
     }
     DecimalFormat df = new DecimalFormat("#####.00");//格式化小数，不足的补0
    // String filesize = df.format(size);//返回的是String类型的
     double result1=(100.0*count1)/a1.length;
     double result2=(100.0*count2)/b1.length;
     System.out.println("属于文件A但不属于B的单词占A中单词数的："+df.format(result1)+"%");
     System.out.println("属于文件b但不属于A的单词占B中单词数的："+df.format(result2)+"%");
	}
}
