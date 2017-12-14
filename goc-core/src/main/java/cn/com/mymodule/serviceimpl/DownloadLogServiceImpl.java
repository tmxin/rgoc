package cn.com.mymodule.serviceimpl;

import cn.com.mymodule.domain.Download;
import cn.com.mymodule.domin.Page;
import org.sculptor.framework.errorhandling.ServiceContext;
import org.springframework.stereotype.Service;

/**
 * Implementation of DownloadLogService.
 */
@Service("downloadLogService")
public class DownloadLogServiceImpl extends DownloadLogServiceImplBase {

	public DownloadLogServiceImpl() {
	}

	public Page<Download> findListByPage(ServiceContext ctx, int pageindex, int pagesize) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("findListByPage not implemented");
	}

}
