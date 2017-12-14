package cn.com.mymodule.sample;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.ServiceSignature;
import com.aliyun.oss.common.comm.RequestMessage;
import com.aliyun.oss.internal.OSSUtils;
import com.aliyun.oss.internal.SignUtils;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.ResponseHeaderOverrides;

/**
 * 断点续传下载用法示例
 *
 */
public class DownloadSample {
    
	private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    private static String accessKeyId = "LTAIjJnNXfRozs9Q";
    private static String accessKeySecret = "N2VLMxiG3ZJOAad4ek9dgpZmhWjLOx";
    private static String bucketName = "goc6test";
    private static String key = "scrap/ACO_TSP.rar";
//    private static String downloadFile = "C:\\Users\\zhouyining\\Desktop\\timg.jpg";
   
    
    public static void main(String[] args) throws IOException {        

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date expires = format.parse("2017-11-27 10:10:00");
            GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, key);
            ResponseHeaderOverrides responseHeaders=new ResponseHeaderOverrides();
            responseHeaders.setContentDisposition("attachment;filename=\"this.rar\"");
            generatePresignedUrlRequest.setExpiration(expires);
            generatePresignedUrlRequest.setResponseHeaders(responseHeaders);
            URL url = ossClient.generatePresignedUrl(generatePresignedUrlRequest);
            System.out.println(url.toString());
            
            
            
            
            
            
            //For IE
//            String new_filename = URLEncoder.encode(filename, "UTF8");  
//            responseHeaders.setContentDisposition("attachment;filename="+new_filename);
//            generatePresignedUrlRequest.setResponseHeaders(responseHeaders);
//            URL url = ossClient.generatePresignedUrl(generatePresignedUrlRequest);
//            System.out.println(url.toString());
            //For FireFox And Chrome
//            responseHeaders.setContentDisposition("attachment;filename=\"a.rar\"");
//            generatePresignedUrlRequest.setResponseHeaders(responseHeaders);
//            generatePresignedUrlRequest.addHeader("reponse-content-disposition", "attachment;filename=\"a.rar\"");
            
            
            
//            String signature = url.toString().substring( url.toString().indexOf("Signature=")+10);
//            System.out.println(signature.toString());
//            
//            RequestMessage request = new RequestMessage();
//            request.addHeader("reponse-content-disposition", "attachment;filename=\"a.rar\"");
//            String canonicalString = SignUtils.buildCanonicalString("GET", "/scrap/ACO_TSP.rar",
//            		request, "2017-11-26 14:15:00");
//            String newSignature = ServiceSignature.create().computeSignature(accessKeyId, canonicalString);
//            System.out.println(newSignature.toString());
//            String newSignature2 = OSSUtils.composeRequestAuthorization(accessKeyId, signature);
//            System.out.println(newSignature2.toString());
//            
//            System.out.println(url.toString().replace(signature, newSignature));
//            System.out.println(url.toString().replace(signature, newSignature));
            //response-content-disposition=attachment%3Bfilename%3D%22a.rar%22&
            
            //http://goc6test.oss-cn-beijing.aliyuncs.com/scrap/ACO_TSP.rar?response-content-disposition=attachment%3Bfilename%3D%22a.rar%22&Expires=1511675700&OSSAccessKeyId=LTAIjJnNXfRozs9Q&Signature=ygYnW3bPBWSgXrnS01ZAUuxZEIg%3D

//            DownloadFileRequest downloadFileRequest = new DownloadFileRequest(bucketName, key);
//            // 设置本地文件
//            downloadFileRequest.setDownloadFile(downloadFile);
//            // 设置并发下载数，默认1
//            downloadFileRequest.setTaskNum(5);
//            // 设置分片大小，默认100KB
//            downloadFileRequest.setPartSize(1024 * 1024 * 1);
//            // 开启断点续传，默认关闭
//            downloadFileRequest.setEnableCheckpoint(true);
//            
//            DownloadFileResult downloadResult = ossClient.downloadFile(downloadFileRequest);
//            
//            ObjectMetadata objectMetadata = downloadResult.getObjectMetadata();
//            System.out.println(objectMetadata.getETag());
//            System.out.println(objectMetadata.getLastModified());
//            System.out.println(objectMetadata.getUserMetadata().get("meta"));
            
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
