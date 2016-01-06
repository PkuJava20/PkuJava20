package Bean;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Util.TextUtil;



public class TmallItemData {
	private String imgURL ;
	private float price;
	private String info ;
	private String productStatus;
	private Element element;
	private boolean isValid =false;
	public TmallItemData(Element e){
		this.element = e;
	}
	public boolean isValid(){
		return isValid;
	}
	public boolean getData(){
		Elements results, results2 ;
		results=element.getElementsByClass("product-iWrap");
		for (Element e : results)
		{
			results2 =e.getElementsByTag("img");
			for (Element e1 : results2)
			{
				String imgURL = e1.attr("data-ks-lazyload");
				if(!TextUtil.isEmpty(imgURL)){
					this.imgURL = "http:"+imgURL;
				}else{
					imgURL = e1.attr("src");
					if(!TextUtil.isEmpty(imgURL))
						this.imgURL = "http:"+imgURL;
				}
			}
		}
		
		if(TextUtil.isEmpty(this.imgURL))return false;
		results =element.getElementsByClass("productTitle");
		for (Element e : results)
		{
			this.info =e.text();
		}
		if(TextUtil.isEmpty(this.info))return false;
		results =element.getElementsByClass("productPrice");
		String price=null;
		for (Element e : results)
		{
			results2 =e.getElementsByTag("em");
			for (Element e1 : results2)
			{
				price= e1.attr("title");
			}
		}
		if(TextUtil.isEmpty(price))return false;
		this.price = Float.parseFloat(price);
		results =element.getElementsByClass("productStatus");
		for (Element e : results)
		{
			this.productStatus =e.text();
		}
		if(TextUtil.isEmpty(this.productStatus))return false;
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
	public String getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	
}