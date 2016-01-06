
import java.io.IOException;
import java.util.List;

import core.JDDataCapture;
import core.TmallDataCapture;

import Bean.JDItemData;
import Bean.TmallItemData;




public class MyCrawler
{
	public static void main(String[] args)throws IOException {


		List<JDItemData> extracts = new JDDataCapture("閲墅耗").dataCapture();
		myprintf2(extracts);

		System.out.println("，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，");
		List<TmallItemData> extracts2 = new TmallDataCapture("閲墅耗").dataCapture();
		myprintf(extracts2);
	}
	public static void myprintf(List<TmallItemData> datas)
	{
		for (TmallItemData data : datas)
		{
			System.out.println(data.getImgURL());
			System.out.println(data.getPrice());
			System.out.println(data.getInfo());
			System.out.println(data.getProductStatus());
			System.out.println("***********************************");
		}

	}
	public static void myprintf2(List<JDItemData> datas)
	{
		for (JDItemData data : datas)
		{
			System.out.println(data.getImgURL());
			System.out.println(data.getPrice());
			System.out.println(data.getInfo());
			System.out.println(data.getComment());
			System.out.println("***********************************");
		}

	}
}
