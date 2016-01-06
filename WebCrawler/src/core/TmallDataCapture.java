
package core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Util.RuleException;
import Util.TextUtil;

import Bean.TmallItemData;


/**
 * 
 * @author jh
 * 
 */
public class TmallDataCapture
{
	private static String url = "https://list.tmall.com/search_product.htm?q=";
	//private static final int  requestMoethod = Utility.GET;
	//private static final int  captureType = Utility.CLASS;
	private static final String resultTagName = "product";
	private String keyword ;
	
	public TmallDataCapture(String keyword){
		this.keyword = keyword;
		@SuppressWarnings("deprecation")
		String trans  = java.net.URLEncoder.encode(this.keyword);
		url = url+trans;
	}
	public static List<TmallItemData> dataCapture()
	{

		// ���ж�url�ı�ҪУ��
		validateRule(url);

		List<TmallItemData> datas = new ArrayList<TmallItemData>();
		TmallItemData data = null;
		try
		{
			/**
			 * ����url
			 */
			
			System.out.println(url);
			Connection conn = Jsoup.connect(url);
			
			Document doc = null;
			
			doc = conn.timeout(100000).get();
			
			//����������
			Elements results = new Elements();
			
			results = doc.getElementsByClass(resultTagName);
			//��Ҫ��ɸѡ
			for (Element e : results)
			{
				data = new TmallItemData(e);
				if(data.getData())
					datas.add(data);
				
			}

		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return datas;
	}

	/**
	 * �Դ���Ĳ������б�Ҫ��У��
	 */
	private static void validateRule(String url)
	{
		if (TextUtil.isEmpty(url))
		{
			throw new RuleException("url����Ϊ�գ�");
		}
		if (!(url.startsWith("http://")||url.startsWith("https://")))
		{
			throw new RuleException("url�ĸ�ʽ����ȷ��");
		}
	}


}
