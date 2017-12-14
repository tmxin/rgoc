package com.demo.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.mymodule.domain.Download;
import cn.com.mymodule.domain.DownloadLog;
import cn.com.mymodule.domain.DownloadRepository;
import cn.com.mymodule.domain.GocFile;
import cn.com.mymodule.domain.ParseContent;
import cn.com.mymodule.sample.UploadSample;
import cn.com.mymodule.serviceapi.AuthordService;
import cn.com.mymodule.serviceapi.DownloadLogService;
import cn.com.mymodule.serviceapi.DownloadService;
import cn.com.mymodule.serviceapi.GocFileService;
import cn.com.mymodule.serviceapi.ParseContentService;
 
@Controller
public class DownloadController {
	@Autowired
	DownloadService downloadService;
	
	@Autowired
	DownloadLogService downloadLogService;
	@Autowired
	DownloadRepository DownloadRepository;
	@Autowired
	ParseContentService parseContentService;
	
	@Autowired
	AuthordService authordService;
	@Autowired
	GocFileService gocFileService;
	Logger logger = LoggerFactory.getLogger(DownloadController.class);
	
	
	/**
	 * 获取授权
	 */
	@ResponseBody
	@RequestMapping(value="/policy", method = RequestMethod.GET)
    public String getPolicy(String tokens){
		Download tokenUser = downloadService.findByTokens(null, tokens);
		if(null!=tokenUser){
			JSONObject object = authordService.getPolicy(null);
			return object==null?"":object.toString();
		}else{
			return "Er:token does not exist";
		}
		
    }
	
	/**
	 * 获取连接
	 */
	@ResponseBody
	@RequestMapping(value="/downgoc", method = RequestMethod.GET)
    public String getFiles(String fileNum,String tokens){
		if(StringUtils.isNotBlank(fileNum)&&StringUtils.isNotBlank(tokens)){
			Download tokenUser = downloadService.findByTokens(null, tokens);
			if(null!=tokenUser){
				DownloadLog downLog = new DownloadLog();
				downLog.setSorceNum(fileNum);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				downLog.setTimes(format.format(new Date()));
				downLog.setTokens(tokens);
				downloadLogService.save(null, downLog);
				return gocFileService.getUrls(null, "sssss", fileNum);
			}else{
				return "Er:token does not exist";
			}
		}else{
			return "Er:data wrong";
		}
    }
	
	@ResponseBody
	@RequestMapping(value="/file/upload", method = RequestMethod.POST)
    public String gocFileService(@RequestBody String info){
		JSONObject jsob = JSONObject.fromObject(info);
		logger.info("传入数据为：");
		logger.info(info);
		if(null!=jsob && StringUtils.isNotBlank(jsob.optString("fileName"))
				&& StringUtils.isNotBlank(jsob.optString("titles"))
				&& StringUtils.isNotBlank(jsob.optString("desc"))){
			String tokens = jsob.optString("tokens");
			Download tokenUser = downloadService.findByTokens(null, tokens);
			if(null!=tokenUser){
				GocFile file = new GocFile();
				file.setBucket("goc6test");
				file.setDir("user-dir");
				file.setFileName(jsob.optString("fileName"));
				file.setResNum(new Date().getTime()+"");
				file.setTitle(jsob.optString("titles"));
				file.setDescript(jsob.optString("desc"));
				GocFile newFile = gocFileService.save(null, file);
				return newFile.getResNum();
			}else{
				return "Er:token does not exist";
			}
		}else{
			return "Er:data wrong";
		}
    }
	
	
	
	
	
	
	
	
	
	
	/**
	 * 2.已经下载过了不扣次数
	 * 1.没下载过扣次数
	 * @param tokens
	 * @param num
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/download", method = RequestMethod.POST)
    public String downloadss(@RequestBody String info){
		JSONObject in = JSONObject.fromObject(info);
		String tokens = in.getString("tokens");
		String num = in.getString("num");
		JSONObject json = new JSONObject();
		Download down = downloadService.findByTokens(null, tokens);
		if(null!=down){
			Integer left = down.getNum();
			String time = down.getEndTime();
			SimpleDateFormat myFmt1=new SimpleDateFormat("yy-MM-dd");
			boolean timeOk = true;
			try {
				Date date = myFmt1.parse(time);
				if(date.getTime()+1000*3600*24 < new Date().getTime()){
					timeOk = false;
				}else{
					timeOk = true;
				}
			} catch (ParseException e) {
				//e.printStackTrace();
			}
			
			if(!timeOk){
				json.put("status", "-100");
			}else if(null!=left && left>0){
				DownloadLog log = downloadLogService.findByTAndS(null, tokens, num);
				//还可以下载
				String url = getUrl(num);//"http://dl.download.csdn.net/down11/20151228/5aeefc87abd63fbaddb10dc9270318a2.zip?response-content-disposition=attachment%3Bfilename%3D%22chat2.zip%22&OSSAccessKeyId=9q6nvzoJGowBj4q1&Expires=1505036482&Signature=hVzdoOn8InXNLRTepYWKo2gFC0A%3D&user=tengmuxin&sourceid=9380037";
				if(!url.startsWith("http://dl.download.csdn.net") || !url.endsWith("="+num)){
					json.put("status", "0");
					json.put("left", left);
					return json.toString();			
				}
				
//				Thread thread = new MyThread(url);
//				thread.start();
				
				if(time.contains("-")){
					json.put("time", time);
				}
				if(null!=log){
					json.put("status", "2");
					json.put("url", url);
					json.put("left", left);
				}else{
					System.out.println("left"+left);
					left = left-1;
					System.out.println("left"+left);
					down.setNum(left);
					json.put("status", "1");
					json.put("url", url);
					json.put("left", left);
					downloadService.save(null,down);
					
					DownloadLog downloadLog = new DownloadLog();
					downloadLog.setSorceNum(num);
					downloadLog.setTimes(new Date().getTime()+"");
					downloadLog.setTokens(tokens);
					downloadLogService.save(null, downloadLog);
				}
			}else{
				json.put("status", "-1");
			}
		}else{
			json.put("status", "-2");
		}
		return json.toString();
    }
	
//	public static void main(String arg[]){
//		new DownloadController().getUrl("9568118"); 
//	}
	
	public String getUrl(String num){
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
		
		
		
		String url = null;
		Response recRes = null;
		try{
//			String ips = new Proxy().getProx().get(0);
//			System.getProperties().setProperty("http.proxyHost", ips.split(":")[0]);
//			System.getProperties().setProperty("http.proxyPort", ips.split(":")[1]);
			
			String save = "http://download.csdn.net/index.php/vip_download/download_client/"+num+"?_"+new Date().getTime();
			
			recRes = Jsoup.connect(save)
//					.followRedirects(true)
//					.ignoreContentType(true)
//					.validateTLSCertificates(false)
				.timeout(30000).method(Method.GET)
				.cookies(cook)
//				.header("Cookie", "uuid_tt_dd=-4442938979533829526_20170905; __message_district_code=110000; TY_SESSION_ID=03dbe089-c0c4-4fa8-bf0e-da82525903db; PHPSESSID=735201537071307abe7d7e26b7b5e8d5; __message_sys_msg_id=0; __message_gu_msg_id=0; __message_cnel_msg_id=0; __message_in_school=0; UserName=tengmuxin; UserInfo=n3hLa%2FZ889jGjIOm2kvGbNi9aHBDmmvrOujziMs%2FDdTUssGQrZYngaFSnXHDIAb9NALpinUgKnJsoq%2FGqLcGqe0IH8BcSG6vvyzQFI33D28WMuMaRGOB2VBArks0pCKu; UserNick=%E8%97%A4%E6%9C%A8; AU=7C2; UD=AB; UN=tengmuxin; UE=\"754300227@qq.com\"; BT=1504602355895; access-token=74a6c440-b16d-4b92-b101-57c02d92bdfd; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1504601403,1504601422,1504601619,1504602156; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1504602351; dc_tos=ovsulq; dc_session_id=1504595521027_0.7235278081110834")
//				.header("Content-Type","application/x-www-form-urlencoded; charset=UTF-8")
				.header("Accept","*/*")//text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
				.header("Referer", "http://download.csdn.net/")
				.header("Host", "download.csdn.net")
				.header("Connection","keep-alive")
				.header("Upgrade-Insecure-Requests","1")
				.header("Accept-Encoding","gzip, deflate")
				.header("Accept-Language","zh-CN,zh;q=0.8")
				.header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.221 Safari/537.36 SE 2.X MetaSr 1.0")
				.execute();
			url = recRes.url().toString();//.parse().location());
		} catch (IOException e) {
		    String a = getExceptions(e);
		    url = a.substring(a.indexOf("URL=")+4, a.indexOf("sourceid")+9)+num;
		    url = url.replaceAll("&user=jinzhongzhao2918", "");
		    //System.out.println(url);
		}
		//.checkUrls(null,"2370504","百度语音");
		return url;

	}
	class MyThread extends Thread {
	    private String url;

	    public MyThread(String url) {
	        this.url = url;
	    }

	    public void run() {
	    	parseContentService.longUrls(null,url);
	    }
	}

    public String getExceptions(Exception ex) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream pout = new PrintStream(out);
        ex.printStackTrace(pout);
        String ret = new String(out.toByteArray());
        pout.close();
        try {
             out.close();
        } catch (Exception e) {
        }
        return ret;
	}
	@ResponseBody
	@RequestMapping(value="/download/info", method = RequestMethod.GET)
    public String downloadInfo(String tokens){
		JSONObject json = new JSONObject();
		Download down = downloadService.findByTokens(null, tokens);
		if(null!=down){
			json.put("status", "1");
			json.put("left", down.getNum());
		}else{
			json.put("status", "-2");
		}
		return json.toString();
    }

	
	@ResponseBody
	@RequestMapping(value="/download/info/vip/list", method = RequestMethod.GET)
    public String vipList(String tokens){
		JSONObject json = new JSONObject();
		parseContentService.getAllHrefs(null);
		return json.toString();
    }
	
	@ResponseBody
	@RequestMapping(value="/download/oneByone/list", method = RequestMethod.GET)
    public void downLoadOneByOne(){
		
		List<ParseContent> conList = parseContentService.findByNull(null);
		int n = 0;
		for(ParseContent pc:conList){
//		if(true){
//			ParseContent pc = conList.get(0);
			//pc.getUrls();
			String sss = pc.getResNum();
			if(sss.contains("down")){
				continue;
			}
			String longUrl = this.getUrl(sss);
			parseContentService.longUrls(null, longUrl);
			
			String fileName = longUrl.substring(longUrl.indexOf("filename")+14,longUrl.indexOf("OSSAccessKeyId")-4);//filename
			try {
				fileName = URLDecoder.decode(fileName,"UTF-8");
				System.out.println(fileName);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			try {
				UploadSample.downLoadFromUrl(longUrl, fileName, "./");
				//这里开始上传
				UploadSample.upload("./"+fileName,"scrap/"+fileName);
				
//				UploadSample.deleteFile("./"+fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				
			}
			pc.setFileName(fileName);
			parseContentService.save(null, pc);
			n++;
			System.out.println(n+"/"+conList.size());
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
}