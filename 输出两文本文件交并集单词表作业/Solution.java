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
          System.out.print("�������һ���ı��ļ��ľ���·��(��x:\\\\xxx.txt)��ע��·����ʽ����ȷ:");
          String readLine1 = in.nextLine(); 
          //System.out.println();
          System.out.print("������ڶ����ı��ļ��ľ���·��(��x:\\\\xxx.txt)��ע��·����ʽ����ȷ:");
          String readLine2 = in.nextLine();
          //System.out.println();
          StringBuffer s1=new StringBuffer(""); //�洢�ļ�һ������
          StringBuffer s2=new StringBuffer(""); //�洢�ļ���������
          FileInputStream fs1;//�ļ�һ������
          FileInputStream fs2;//�ļ���������
          HashSet<String> hs1=new HashSet<String>(); //�洢�ļ�һ����
          HashSet<String> hs2=new HashSet<String>(); //�洢�ļ�������
          ArrayList<String> a1=new ArrayList<String>(); //�洢�������ʱ�
          ArrayList<String> a2=new ArrayList<String>(); //�洢�������ʱ�
          try{
           fs1=new FileInputStream(readLine1);
           fs2=new FileInputStream(readLine2);
          BufferedReader br1=new BufferedReader(new InputStreamReader(fs1));
          BufferedReader br2=new BufferedReader(new InputStreamReader(fs2));
          String temp1="";//�ݴ�һ�е�ֵ
          String temp2="";
          while((temp1=br1.readLine())!=null){ //���ļ�һ���ݷ���s1
        	  s1.append(temp1);
        	  s1.append(" ");
          }
          while((temp2=br2.readLine())!=null){ //���ļ������ݷ���s2
        	  s2.append(temp2);
        	  s2.append(" ");
          }   
          fs1.close();//�ر��ļ���
          fs2.close();
          br1.close();
          br2.close();
          }
          catch(Exception e){System.out.println("�����ļ�����·���Ƿ���ȷ");System.exit(0);}
          StringBuffer word=new StringBuffer("");//�洢ĳ�����ʵ�ֵ
          for(int i=0;i<s1.length();i++){  //�����ļ�һ������
        	  if((int)s1.charAt(i)<=122&&(int)s1.charAt(i)>=97||(int)s1.charAt(i)<=90&&(int)s1.charAt(i)>=65)//���ַ�����ĸ
        		  word.append(s1.charAt(i));//�ݴ浽word
        	  else{
        		  if(!word.toString().equals("")){//��word�м�¼�˵���
        		  hs1.add(word.toString());//����hs1
        		  word.delete(0,word.length());//���word��¼
        		  }
        	  }		  
          }
          for(int i=0;i<s2.length();i++){  //�����ļ���������
        	  if((int)s2.charAt(i)<=122&&(int)s2.charAt(i)>=97||(int)s2.charAt(i)<=90&&(int)s2.charAt(i)>=65)//���ַ�����ĸ
        		  word.append(s2.charAt(i));//�ݴ浽word
        	  else{
        		  if(!word.toString().equals("")){//��word�м�¼�˵���
        		  hs2.add(word.toString());//����hs2
        		  word.delete(0,word.length());//���word��¼
        		  }
        	  }		  
          }
          //System.out.println(hs1.size()+" "+hs2.size());
          Iterator it1=hs1.iterator();
          Iterator it2=hs2.iterator();
          while(it1.hasNext())//�����ļ�һ����
        	  a1.add((String)it1.next()); //���ļ�һ�еĵ��ʷ��벢�����ʱ�a1
          while(it2.hasNext()){//�����ļ�������
        	  String wd=(String)it2.next();//ȡ�����ļ�����һ������
        	//  System.out.println(wd);
        	  if(a1.contains(wd)) //a1�������������
        		  a2.add(wd);//���뽻�����ʱ�
        	  else//a1�л�û���������
        		  a1.add(wd);//���벢�����ʱ�
          }
          Collections.sort(a1);//�������ʱ��е��ʰ���ĸ����
          Collections.sort(a2);//�������ʱ��е��ʰ���ĸ����
          System.out.println("�������ʱ������"+a1.size());
          System.out.print("�����ǣ�");
          for(int i=0;i<a1.size();i++)
        	  System.out.print(a1.get(i)+" ");
          System.out.println();
          System.out.println("�������ʱ������"+a2.size());
          System.out.print("�����ǣ�");
          for(int i=0;i<a2.size();i++)
        	  System.out.print(a2.get(i)+" ");
}
}