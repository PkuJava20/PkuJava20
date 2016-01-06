package Bean;


import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Util.TextUtil;



public class JDItemData {
	private String imgURL ;
	private float price;
	private String info ;
	private String comment;
	
	private Element element;
	private boolean isValid =false;
	public JDItemData(Element e){
		this.element = e;
	}
	public boolean isValid(){
		return isValid;
	}
	public boolean getData(){
		Elements results, results2 ;
		results=element.getElementsByClass("p-img");
		for (Element e : results)
		{
			results2 =e.getElementsByTag("img");
			for (Element e1 : results2)
			{
				String imgURL = e1.attr("src");
				if(!TextUtil.isEmpty(imgURL)){
					this.imgURL = "http:"+imgURL;
				}else{
					imgURL = e1.attr("data-lazy-img");
					if(!TextUtil.isEmpty(imgURL))
						this.imgURL = "http:"+imgURL;
				}
			}
		}
		if(TextUtil.isEmpty(this.imgURL))return false;
		results =element.getElementsByClass("p-name");
		for (Element e : results)
		{
			this.info =e.text();
		}
		if(TextUtil.isEmpty(this.info))return false;
		results =element.getElementsByClass("p-price");
		String price=null;
		for (Element e : results)
		{
			results2 =e.getElementsByTag("strong");
			for (Element e1 : results2)
			{
				price= e1.attr("data-price");
			}
		}
		if(TextUtil.isEmpty(price))return false;
		this.price = Float.parseFloat(price);
		results =element.getElementsByClass("p-commit");
		for (Element e : results)
		{
			this.comment =e.text();
		}
		if(TextUtil.isEmpty(this.comment))return false;
		return true;

	}
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}