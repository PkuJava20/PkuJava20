public class Solution {
    public int romanToInt(String s) {
 int result=0;  //�洢���
	        char[] ch=s.toCharArray();//ת�����ַ����飬�Ա�����ַ�����
	        for(int i=0;i<ch.length;i++){
	        	if(ch[i]=='M')  //����M��1000
	        		result=result+1000;
	        	else if(ch[i]=='D')//����D��500
	        		result=result+500;
	        	else if(ch[i]=='L') //����X��50
	        		result=result+50;
	        	else if(ch[i]=='V')//����V��5
	        		result=result+5;
	        	else if(ch[i]=='C'){ //����C��������
	        		if(i+1<ch.length){//��ֹԽ��
	        			if(ch[i+1]=='D'||ch[i+1]=='M') //CD��CM�������CҪ��100
	        				result=result-100;
	        			else
	        				result=result+100; //����CD��CM�������100
	        		}
	        		else
	        			result=result+100;//C�����һ���ַ�������CD��CM�������100
	        	}
	        	else if(ch[i]=='X'){ //����C�Ĵ���
	        		if(i+1<ch.length){
	        			if(ch[i+1]=='L'||ch[i+1]=='C')
	        				result=result-10;
	        			else
	        				result=result+10;
	        		}
	        		else
	        			result=result+10;
	        	}
	        	else if(ch[i]=='I'){//����C�Ĵ���
	        		if(i+1<ch.length){
	        			if(ch[i+1]=='V'||ch[i+1]=='X')
	        				result=result-1;
	        			else
	        				result=result+1;
	        		}
	        		else
	        			result=result+1;
	        	}
	        }
	        return result;
    }
}