package cn.com.mymodule.serviceimpl;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.sculptor.framework.accessapi.ConditionalCriteriaBuilder;
import org.sculptor.framework.accessapi.ConditionalCriteriaBuilder.ConditionRoot;
import org.sculptor.framework.domain.PagedResult;
import org.sculptor.framework.domain.PagingParameter;
import org.sculptor.framework.errorhandling.ServiceContext;
import org.springframework.stereotype.Service;

import cn.com.mymodule.domain.GocFile;
import cn.com.mymodule.domain.GocFileProperties;
import cn.com.mymodule.domin.Page;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.ResponseHeaderOverrides;

/**
 * Implementation of GocFileService.
 */
@Service("gocFileService")
public class GocFileServiceImpl extends GocFileServiceImplBase {
	
	
	private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    private static String accessKeyId = "LTAIjJnNXfRozs9Q";
    private static String accessKeySecret = "N2VLMxiG3ZJOAad4ek9dgpZmhWjLOx";
    private static String bucketName = "goc6test";
    private static String key = "scrap/ACO_TSP.rar";
	public GocFileServiceImpl() {
	}
	public Page<GocFile> findListByPage(ServiceContext ctx, int pageindex, int pagesize) {
		ConditionRoot<GocFile> root=ConditionalCriteriaBuilder.criteriaFor(GocFile.class);
		root.orderBy(GocFileProperties.id()).descending();
        PagedResult<GocFile> resultPage=
            this.getGocFileRepository().findByCondition(root.build(), PagingParameter.pageAccess(pagesize, pageindex + 1, true));
        
        Page<GocFile> result = new Page<GocFile>();
        result.setPagesize(pagesize);
        result.setPageindex(pageindex + 1);
        result.setTotalCount(resultPage.getTotalRows());
        result.setTotalPages(resultPage.getTotalPages());
        result.setItems(resultPage.getValues());
		return result;
	}
	public String getUrls(ServiceContext ctx, String tokens, String resNum) {
		

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date expires = new Date(new Date().getTime()+10*60*1000);//format.parse("2017-11-27 10:10:00");
            
            GocFile gocFile = this.findByResNum(ctx, resNum);
            
            key = "user-dir/"+gocFile.getResNum();
            String name = gocFile.getFileName();
            
            GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, key);
            ResponseHeaderOverrides responseHeaders=new ResponseHeaderOverrides();
            responseHeaders.setContentDisposition("attachment;filename=\""+name+"\"");
            generatePresignedUrlRequest.setExpiration(expires);
            generatePresignedUrlRequest.setResponseHeaders(responseHeaders);
            URL url = ossClient.generatePresignedUrl(generatePresignedUrlRequest);
            System.out.println(url.toString());
            
            return url.toString();
            
            //For IE
//	            String new_filename = URLEncoder.encode(filename, "UTF8");  
//	            responseHeaders.setContentDisposition("attachment;filename="+new_filename);
//	            generatePresignedUrlRequest.setResponseHeaders(responseHeaders);
//	            URL url = ossClient.generatePresignedUrl(generatePresignedUrlRequest);
//	            System.out.println(url.toString());
            //For FireFox And Chrome
//	            responseHeaders.setContentDisposition("attachment;filename=\"a.rar\"");
//	            generatePresignedUrlRequest.setResponseHeaders(responseHeaders);
//	            generatePresignedUrlRequest.addHeader("reponse-content-disposition", "attachment;filename=\"a.rar\"");
            
            
            

            
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
        return "";
	}

}
