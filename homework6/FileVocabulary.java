import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


public class FileVocabulary {
	public  TreeSet<String> notRepeat = new TreeSet<String>();
	public  List<String>    repeat = new ArrayList<String>();
	public  String fileName;
	public FileVocabulary(String fileName)
	{
		this.fileName = fileName;
		readFileByChars(fileName);
	}
	public  void  readFileByChars(String fileName) {
		boolean isLetter = false;
        File file = new File(fileName);
        Reader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            StringBuilder buider = new StringBuilder();
            String tempStr;
            while ((tempchar = reader.read()) != -1) {
                // 因此，屏蔽掉\r，或者屏蔽\n。
                if (((char) tempchar) != '\r') {
                	if(Character.isLetter((char) tempchar))
                	{
                		isLetter=true;
                		buider.append((char)tempchar);
                	}
                	else{
                		if((char) tempchar == '\'')//'字符特殊处理
                		{
                    		isLetter=true;
                    		buider.append((char)tempchar);
                		}
                		else{
                			if(isLetter)
                			{
                				tempStr = buider.toString().toLowerCase();
                				notRepeat.add(tempStr);
                				repeat.add(tempStr);
                				buider = new StringBuilder();
                			}
                			isLetter = false;
                		}
                	}
                }
            }
    		if(isLetter)
    		{
    			tempStr = buider.toString().toLowerCase();
    			notRepeat.add(tempStr);
    			repeat.add(tempStr);
    			buider = new StringBuilder();
    		}
    		
            reader.close();
            
            notRepeat.comparator();  
            //for(String value :notRepeat)
            //	System.out.println(value);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                	e1.printStackTrace();
                }
            }
        }
    }
}
