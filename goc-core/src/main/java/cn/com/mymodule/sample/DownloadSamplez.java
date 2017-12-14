package cn.com.mymodule.sample;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.ResponseHeaderOverrides;

/**
 * 断点续传下载用法示例
 *
 */
public class DownloadSamplez {
    
//    private static String endpoint = "<endpoint, http://oss-cn-hangzhou.aliyuncs.com>";
//    private static String accessKeyId = "<accessKeyId>";
//    private static String accessKeySecret = "<accessKeySecret>";
//    private static String bucketName = "<bucketName>";
//    private static String key = "<downloadKey>";
//    private static String downloadFile = "<downloadFile>";
   
    private static String endpoint = "oss-cn-beijing.aliyuncs.com";
    private static String accessKeyId = "LTAIjJnNXfRozs9Q";
    private static String accessKeySecret = "N2VLMxiG3ZJOAad4ek9dgpZmhWjLOx";
    private static String bucketName = "goc6test";
    private static String key = "scrap/ shell程序模拟设计.doc";
    private static String downloadFile = "C://Users/Think/Desktop/sstp_1_Abstract/shell程序模拟设计.doc";
    
    public static void main(String[] args) throws IOException {        

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
//            DownloadFileRequest downloadFileRequest = new DownloadFileRequest(bucketName, key);
//            // 设置本地文件
//            downloadFileRequest.setDownloadFile(downloadFile);
//            // 设置并发下载数，默认1
//            downloadFileRequest.setTaskNum(5);
//            // 设置分片大小，默认100KB
//            downloadFileRequest.setPartSize(1024 * 1024 * 1);
//            // 开启断点续传，默认关闭
//            downloadFileRequest.setEnableCheckpoint(true);
            
//            DownloadFileResult downloadResult = ossClient.downloadFile(downloadFileRequest);
            
        	
        	Date expires = new Date (new Date().getTime() + 1000 * 60); // 1 minute to expire

        	GeneratePresignedUrlRequest generatePresignedUrlRequest =

        	new GeneratePresignedUrlRequest(bucketName, key);

        	generatePresignedUrlRequest.setExpiration(expires);
        	Map<String, String> str = new HashMap<String, String>();
        	//response-content-disposition=attachment; filename="mikako.jpg"
//        	generatePresignedUrlRequest.setContentType("application/octet-stream");
        	str.put("Content-Disposition", "attachment; filename=\"aaa.doc\"");
        	generatePresignedUrlRequest.setHeaders(str);
//        	ResponseHeaderOverrides responseHeaders = new ResponseHeaderOverrides();
//        	responseHeaders.setContentDisposition("attachment; filename=\"aaa.doc\"");
//        	generatePresignedUrlRequest.setResponseHeaders(responseHeaders);
        	URL url = ossClient.generatePresignedUrl(generatePresignedUrlRequest);

        	System.out.println(url.toString());
//            Date expiration = new Date(new Date().getTime() + 66 * 1000);// 生成URL
//            URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
//            System.out.println(url.toString());
        	
        	
        	
//            ObjectMetadata objectMetadata = downloadResult.getObjectMetadata();
//            System.out.println(objectMetadata.getETag());
//            System.out.println(objectMetadata.getLastModified());
//            System.out.println(objectMetadata.getUserMetadata().get("meta"));
//            http%E6%A8%A1%E6%8B%9FPOST%E8%A1%A8%E5%8D%95%E4%B8%8A%E4%BC%A0%E6%96%87%E4%BB%B6.rar%22
//            
//            response-content-disposition=attachment;filename="a.doc"&
//            		
//            http://goc6test.oss-cn-beijing.aliyuncs.com/scrap/%20shell%E7%A8%8B%E5%BA%8F%E6%A8%A1%E6%8B%9F%E8%AE%BE%E8%AE%A1.doc?response-content-disposition=attachment;filename="mikako.doc"&Expires=1511621455&OSSAccessKeyId=LTAIjJnNXfRozs9Q&Signature=SsR302S5PaUlfgVwEn9%2B6VfD%2FcI%3D
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
}
