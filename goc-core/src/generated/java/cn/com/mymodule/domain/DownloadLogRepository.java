package cn.com.mymodule.domain;

import cn.com.mymodule.domain.DownloadLog;
import cn.com.mymodule.exception.DownloadLogNotFoundException;
import java.util.List;
import org.sculptor.framework.accessapi.ConditionalCriteria;
import org.sculptor.framework.domain.PagedResult;
import org.sculptor.framework.domain.PagingParameter;

/**
 * Generated interface for Repository for DownloadLog
 */
public interface DownloadLogRepository {

	public final static String BEAN_ID = "downloadLogRepository";

	public PagedResult<DownloadLog> findByCondition(List<ConditionalCriteria> condition, PagingParameter pagingParameter);

	public List<DownloadLog> findByTokens(String tokens);

	public DownloadLog findByTAndS(String tokens, String sorceNum);

	public DownloadLog findById(Long id) throws DownloadLogNotFoundException;

	public List<DownloadLog> findAll();

	public DownloadLog save(DownloadLog entity);

	public void delete(DownloadLog entity);

}
