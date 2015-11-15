import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;


public class Solution {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
          System.out.print("请输入第一个文本文件的绝对路径(如x:\\\\xxx.txt)，注意路径格式需正确:");
          String readLine1 = in.nextLine(); 
          //System.out.println();
          System.out.print("请输入第二个文本文件的绝对路径(如x:\\\\xxx.txt)，注意路径格式需正确:");
          String readLine2 = in.nextLine();
          //System.out.println();
          StringBuffer s1=new StringBuffer(""); //存储文件一的内容
          StringBuffer s2=new StringBuffer(""); //存储文件二的内容
          FileInputStream fs1;//文件一输入流
          FileInputStream fs2;//文件二输入流
          HashSet<String> hs1=new HashSet<String>(); //存储文件一单词
          HashSet<String> hs2=new HashSet<String>(); //存储文件二单词
          ArrayList<String> a1=new ArrayList<String>(); //存储并集单词表
          ArrayList<String> a2=new ArrayList<String>(); //存储交集单词表
          try{
           fs1=new FileInputStream(readLine1);
           fs2=new FileInputStream(readLine2);
          BufferedReader br1=new BufferedReader(new InputStreamReader(fs1));
          BufferedReader br2=new BufferedReader(new InputStreamReader(fs2));
          String temp1="";//暂存一行的值
          String temp2="";
          while((temp1=br1.readLine())!=null){ //将文件一内容放入s1
        	  s1.append(temp1);
        	  s1.append(" ");
          }
          while((temp2=br2.readLine())!=null){ //将文件二内容放入s2
        	  s2.append(temp2);
        	  s2.append(" ");
          }   
          fs1.close();//关闭文件流
          fs2.close();
          br1.close();
          br2.close();
          }
          catch(Exception e){System.out.println("请检查文件输入路径是否正确");System.exit(0);}
          StringBuffer word=new StringBuffer("");//存储某个单词的值
          for(int i=0;i<s1.length();i++){  //遍历文件一的内容
        	  if((int)s1.charAt(i)<=122&&(int)s1.charAt(i)>=97||(int)s1.charAt(i)<=90&&(int)s1.charAt(i)>=65)//若字符是字母
        		  word.append(s1.charAt(i));//暂存到word
        	  else{
        		  if(!word.toString().equals("")){//若word中记录了单词
        		  hs1.add(word.toString());//加入hs1
        		  word.delete(0,word.length());//清空word记录
        		  }
        	  }		  
          }
          for(int i=0;i<s2.length();i++){  //遍历文件二的内容
        	  if((int)s2.charAt(i)<=122&&(int)s2.charAt(i)>=97||(int)s2.charAt(i)<=90&&(int)s2.charAt(i)>=65)//若字符是字母
        		  word.append(s2.charAt(i));//暂存到word
        	  else{
        		  if(!word.toString().equals("")){//若word中记录了单词
        		  hs2.add(word.toString());//加入hs2
        		  word.delete(0,word.length());//清空word记录
        		  }
        	  }		  
          }
          //System.out.println(hs1.size()+" "+hs2.size());
          Iterator it1=hs1.iterator();
          Iterator it2=hs2.iterator();
          while(it1.hasNext())//遍历文件一单词
        	  a1.add((String)it1.next()); //将文件一中的单词放入并集单词表a1
          while(it2.hasNext()){//遍历文件二单词
        	  String wd=(String)it2.next();//取出我文件二中一个单词
        	//  System.out.println(wd);
        	  if(a1.contains(wd)) //a1中有了这个单词
        		  a2.add(wd);//放入交集单词表
        	  else//a1中还没有这个单词
        		  a1.add(wd);//放入并集单词表
          }
          Collections.sort(a1);//并集单词表中单词按字母排序
          Collections.sort(a2);//交集单词表中单词按字母排序
          System.out.println("并集单词表个数："+a1.size());
          System.out.print("它们是：");
          for(int i=0;i<a1.size();i++)
        	  System.out.print(a1.get(i)+" ");
          System.out.println();
          System.out.println("交集单词表个数："+a2.size());
          System.out.print("它们是：");
          for(int i=0;i<a2.size();i++)
        	  System.out.print(a2.get(i)+" ");
}
}