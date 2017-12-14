package cn.com.mymodule.serviceapi;

import cn.com.mymodule.domain.Download;
import cn.com.mymodule.domin.Page;
import cn.com.mymodule.exception.DownloadNotFoundException;
import java.util.List;
import org.sculptor.framework.errorhandling.ServiceContext;

/**
 * Generated interface for the Service DownloadService.
 */
public interface DownloadService {

	public final static String BEAN_ID = "downloadService";

	public Page<Download> findListByPage(ServiceContext ctx, int pageindex, int pagesize);

	public Download findByTokens(ServiceContext ctx, String tokens);

	public Download findById(ServiceContext ctx, Long id) throws DownloadNotFoundException;

	public List<Download> findAll(ServiceContext ctx);

	public Download save(ServiceContext ctx, Download entity);

	public void delete(ServiceContext ctx, Download entity);

}
