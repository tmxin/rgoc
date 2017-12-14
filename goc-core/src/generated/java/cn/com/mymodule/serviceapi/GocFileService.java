package cn.com.mymodule.serviceapi;

import cn.com.mymodule.domain.GocFile;
import cn.com.mymodule.domin.Page;
import cn.com.mymodule.exception.GocFileNotFoundException;
import java.util.List;
import org.sculptor.framework.errorhandling.ServiceContext;

/**
 * Generated interface for the Service GocFileService.
 */
public interface GocFileService {

	public final static String BEAN_ID = "gocFileService";

	public String getUrls(ServiceContext ctx, String tokens, String resNum);

	public GocFile findByResNum(ServiceContext ctx, String resNum);

	public Page<GocFile> findListByPage(ServiceContext ctx, int pageindex, int pagesize);

	public GocFile findById(ServiceContext ctx, Long id) throws GocFileNotFoundException;

	public List<GocFile> findAll(ServiceContext ctx);

	public GocFile save(ServiceContext ctx, GocFile entity);

	public void delete(ServiceContext ctx, GocFile entity);

}
