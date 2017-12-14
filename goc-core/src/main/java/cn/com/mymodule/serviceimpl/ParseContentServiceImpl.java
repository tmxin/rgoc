package cn.com.mymodule.serviceimpl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.sculptor.framework.errorhandling.ServiceContext;
import org.springframework.stereotype.Service;

import cn.com.mymodule.domain.ParseContent;
import cn.com.mymodule.sample.UploadSample;

/**
 * Implementation of ParseContentService.
 */
@Service("parseContentService")
public class ParseContentServiceImpl extends ParseContentServiceImplBase {

	public ParseContentServiceImpl() {
	}
	public void parseF(ServiceContext ctx,String urls,String fileName){
		
		try{
			Response recRes = null;
			recRes = Jsoup.connect(urls)
				.timeout(30000).method(Method.GET)
				.header("Accept","*/*")//text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
				.header("Referer", "http://download.csdn.net/")
				.header("Host", "download.csdn.net")
				.header("Connection","keep-alive")
				.header("Upgrade-Insecure-Requests","1")
				.header("Accept-Encoding","gzip, deflate")
				.header("Accept-Language","zh-CN,zh;q=0.8")
				.header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.221 Safari/537.36 SE 2.X MetaSr 1.0")
				.execute();
			
			//System.out.println(recRes.parse());
			System.out.println(recRes.parse().getElementsByClass("pre_description").get(0).text());
			System.out.println(recRes.parse().getElementsByClass("follow_menu_l").get(0).text());
			System.out.println(recRes.parse().getElementsByClass("dl_b").get(0).getElementsByTag("span").get(0).text().split(" ")[0]);
			System.out.println(recRes.parse().getElementsByClass("dl_b").get(0).getElementsByTag("em").text());
			
			System.out.println(urls);
			System.out.println(urls.split("/")[urls.split("/").length-1]);
			
			
			ParseContent pc = new ParseContent();
			pc.setContents(recRes.parse().getElementsByClass("pre_description").get(0).text());
			pc.setFileName(fileName);
			pc.setFsize(recRes.parse().getElementsByClass("dl_b").get(0).getElementsByTag("em").text());
			pc.setResNum(urls.split("/")[urls.split("/").length-1]);
			pc.setTitle(recRes.parse().getElementsByClass("follow_menu_l").get(0).text());
			pc.setUptime(recRes.parse().getElementsByClass("dl_b").get(0).getElementsByTag("span").get(0).text().split(" ")[0]);
			pc.setUrls(urls);
			this.save(null, pc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Map<String,String> logins(ServiceContext ctx){
		Map<String,String> cook = new HashMap<String,String>();
		String lt = null;
		String execution = null;
		try{
			String save = "https://passport.csdn.net/account/login";
			Response recRes = Jsoup.connect(save).validateTLSCertificates(false)
				.execute();
			cook = recRes.cookies();
			lt = recRes.parse().getElementsByAttributeValue("name", "lt").val();
			execution = recRes.parse().getElementsByAttributeValue("name", "execution").val();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try{
			String save = "https://passport.csdn.net/account/login?ref=toolbar";
			Response recRes = Jsoup.connect(save).validateTLSCertificates(false)
				.timeout(30000).method(Method.POST)
				.data("username","jinzhongzhao2918")
				.data("password","tiebushan2409")
				.data("lt",lt)
				.data("execution",execution)
				.data("_eventId","submit")
				.cookies(cook)

				.header("Content-Type","application/x-www-form-urlencoded; charset=UTF-8")
				.header("Accept","application/json, text/javascript, */*; q=0.01")
				.header("Referer", "http://my.csdn.net/")
				.header("Connection","keep-alive")
				.header("X-Requested-With","XMLHttpRequest")
				.header("Accept","application/json, text/javascript, */*; q=0.01")
				.header("Accept-Encoding","gzip, deflate")
				.header("Accept-Language","zh-CN,zh;q=0.8")
				.header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.221 Safari/537.36 SE 2.X MetaSr 1.0")
				.execute();
			
			cook = recRes.cookies();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return cook;
	}
	public List<String> getVipList(ServiceContext ctx,Map<String,String> cook,int page){
		Response recRes = null;
		List<String> strList = new ArrayList<String>();
		try{
			String save = "http://download.csdn.net/my/vip/"+page;
			recRes = Jsoup.connect(save)
				.timeout(30000).method(Method.GET)
				.cookies(cook)
				.header("Accept","*/*")//text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
				.header("Referer", "http://download.csdn.net/")
				.header("Host", "download.csdn.net")
				.header("Connection","keep-alive")
				.header("Upgrade-Insecure-Requests","1")
				.header("Accept-Encoding","gzip, deflate")
				.header("Accept-Language","zh-CN,zh;q=0.8")
				.header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.221 Safari/537.36 SE 2.X MetaSr 1.0")
				.execute();
			Elements eles = recRes.parse().getElementsByClass("orderid");
			for(Element ele : eles){
				Elements els = ele.getElementsByTag("a");
				if(null!=els && els.size()>0){
					String href = els.get(0).attr("href");
					System.out.println(href);
					strList.add(href);
				}
			}
		} catch (IOException e) {
		}
		return strList;
	}
	
	public boolean checkUrls(ServiceContext ctx,String resNum,String fileName){
		List<ParseContent> parList = this.findByResNum(ctx, resNum);
		if(null==parList || parList.size()==0){//判断数据库是否存在数据
			Map<String,String> cook = logins(ctx);//此cookie也可以传过来
			List<String> hrefs = getVipList(ctx,cook,1);
			for(String url:hrefs){
				if(url.endsWith("/"+resNum)){
					parseF(ctx,url,fileName);
					return true;
				}
			}
		}
		return false;
	}
	
	public void getAllHrefs(ServiceContext ctx){
		Map<String,String> cook = logins(ctx);//此cookie也可以传过来
		for(int i=1;i<101;i++){
			List<String> hrefs = getVipList(ctx,cook,i);
			for(int m=0;m<hrefs.size();m++){
				if(StringUtils.isNotBlank(hrefs.get(m))){
					String nums = hrefs.get(m).split("/")[hrefs.get(m).split("/").length-1];
					List<ParseContent> list = this.findByResNum(null, nums);
					if(null==list || list.size()==0){
						ParseContent parseContent = new ParseContent();
						parseContent.setUrls(hrefs.get(m));
						parseContent.setResNum(nums);
						this.save(ctx, parseContent);
					}
				}
				
			}
		}
	}
	
	public void longUrls(ServiceContext ctx,String longurl){
		String sourceid = longurl.substring(longurl.indexOf("sourceid")+9);
		System.out.println(sourceid);
		
		String fileName = longurl.substring(longurl.indexOf("filename")+14,longurl.indexOf("OSSAccessKeyId")-4);//filename
		try {
			fileName = URLDecoder.decode(fileName,"UTF-8");
			System.out.println(fileName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(checkUrls(ctx,sourceid, fileName)){
			try {
				UploadSample.downLoadFromUrl(longurl, fileName, "./");
				//这里开始上传
				UploadSample.upload("./"+fileName,"scrap/"+fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				UploadSample.deleteFile("./"+fileName);
			}
		}
		
	}
	
	public static void main(String args[]) {
		//  http://download.csdn.net/download/github_37951477/
		new ParseContentServiceImpl().longUrls(null,
				"http://dl.download.csdn.net/down11/20160705/be670ccadbc4e12ebc34a883ffa42f17.rar?response-content-disposition=attachment%3Bfilename%3D%22%E7%99%BE%E5%BA%A6%E8%AF%AD%E9%9F%B3%E6%BA%90%E7%A0%81.rar%22&OSSAccessKeyId=9q6nvzoJGowBj4q1&Expires=1511094604&Signature=6wfuUBlo4N02wa%2Fs7%2FvoXPPii9Y%3D&sourceid=9568118"
				);
		//.checkUrls(null,"2370504","百度语音");
	}

}
