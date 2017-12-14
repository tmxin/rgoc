package cn.com.mymodule.serviceapi;

import cn.com.mymodule.domain.Download;
import cn.com.mymodule.domain.DownloadLog;
import cn.com.mymodule.domin.Page;
import cn.com.mymodule.exception.DownloadLogNotFoundException;
import java.util.List;
import org.sculptor.framework.errorhandling.ServiceContext;

/**
 * Generated interface for the Service DownloadLogService.
 */
public interface DownloadLogService {

	public final static String BEAN_ID = "downloadLogService";

	public Page<Download> findListByPage(ServiceContext ctx, int pageindex, int pagesize);

	public List<DownloadLog> findByTokens(ServiceContext ctx, String tokens);

	public DownloadLog findByTAndS(ServiceContext ctx, String tokens, String sorceNum);

	public DownloadLog findById(ServiceContext ctx, Long id) throws DownloadLogNotFoundException;

	public List<DownloadLog> findAll(ServiceContext ctx);

	public DownloadLog save(ServiceContext ctx, DownloadLog entity);

	public void delete(ServiceContext ctx, DownloadLog entity);

}
