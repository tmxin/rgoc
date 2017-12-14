package cn.com.mymodule.sample;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CompleteMultipartUploadResult;
import com.aliyun.oss.model.UploadFileRequest;
import com.aliyun.oss.model.UploadFileResult;

/**
 * 断点续传上传用法示例
 *
 */
public class UploadSample {
    
    private static String endpoint = "oss-cn-beijing.aliyuncs.com";
    private static String accessKeyId = "LTAIjJnNXfRozs9Q";
    private static String accessKeySecret = "N2VLMxiG3ZJOAad4ek9dgpZmhWjLOx";
    private static String bucketName = "goc6test";
    private static String key = "scrap/timg.jpg";
    private static String uploadFile = "C:\\Users\\zhouyining\\Desktop\\timg.jpg";

//    public static void main(String[] args) throws IOException {
    public static void upload(String uploadFile,String key) throws IOException {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        
        try {
            UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, key);
            // 待上传的本地文件
            uploadFileRequest.setUploadFile(uploadFile);
            // 设置并发下载数，默认1
            uploadFileRequest.setTaskNum(5);
            // 设置分片大小，默认100KB
            uploadFileRequest.setPartSize(1024 * 1024 * 1);
            // 开启断点续传，默认关闭
            uploadFileRequest.setEnableCheckpoint(true);
            
//            PutObjectResult result = ossClient.putObject(bucketName, key, new File(uploadFile));///////////////////////
            UploadFileResult uploadResult = ossClient.uploadFile(uploadFileRequest);
            CompleteMultipartUploadResult multipartUploadResult = 
                    uploadResult.getMultipartUploadResult();
            System.out.println(multipartUploadResult.getETag());
            
//        	System.out.println(result.getETag());
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
    }
    public static void main(String args[]){
    	try {
			UploadSample.downLoadFromUrl(
					"http://dl.download.csdn.net/down11/20160705/be670ccadbc4e12ebc34a883ffa42f17.rar?response-content-disposition=attachment%3Bfilename%3D%22%E7%99%BE%E5%BA%A6%E8%AF%AD%E9%9F%B3%E6%BA%90%E7%A0%81.rar%22&OSSAccessKeyId=9q6nvzoJGowBj4q1&Expires=1511094604&Signature=6wfuUBlo4N02wa%2Fs7%2FvoXPPii9Y%3D&sourceid=9568118",
					"be670ccadbc4e12ebc34a883ffa42f17.rar", "./");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public static void  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{  
        URL url = new URL(urlStr);    
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
                //设置超时间为3秒  
        conn.setConnectTimeout(10*1000);  
        //防止屏蔽程序抓取而返回403错误  
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.221 Safari/537.36 SE 2.X MetaSr 1.0");
//        conn.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
//        conn.setRequestProperty("Referer", "http://download.csdn.net/");
//        conn.setRequestProperty("Host", "download.csdn.net");
//        conn.setRequestProperty("Connection", "keep-alive");
//        conn.setRequestProperty("Upgrade-Insecure-Requests", "1");
//        conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
//        conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
//        conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
//        conn.setRequestProperty("X-Requested-With","XMLHttpRequest");
        //得到输入流  
        InputStream inputStream = conn.getInputStream();    
        //获取自己数组  
        byte[] getData = readInputStream(inputStream);      
  
        //文件保存位置  
        File saveDir = new File(savePath);  
        if(!saveDir.exists()){  
            saveDir.mkdir();  
        }  
        File file = new File(saveDir+File.separator+fileName);      
        FileOutputStream fos = new FileOutputStream(file);       
        fos.write(getData);   
        if(fos!=null){  
            fos.close();    
        }  
        if(inputStream!=null){  
            inputStream.close();  
        }  
  
  
        System.out.println("info:"+url+" download success");   
  
    }  
  
  
  
    /** 
     * 从输入流中获取字节数组 
     * @param inputStream 
     * @return 
     * @throws IOException 
     */  
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {    
        byte[] buffer = new byte[1024];    
        int len = 0;    
        ByteArrayOutputStream bos = new ByteArrayOutputStream();    
        while((len = inputStream.read(buffer)) != -1) {    
            bos.write(buffer, 0, len);    
        }    
        bos.close();    
        return bos.toByteArray();    
    }    
    /**
     * 删除单个文件
     *
     * @param fileName
     *            要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }
}
