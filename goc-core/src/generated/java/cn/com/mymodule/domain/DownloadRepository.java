package cn.com.mymodule.domain;

import cn.com.mymodule.domain.Download;
import cn.com.mymodule.exception.DownloadNotFoundException;
import java.util.List;
import org.sculptor.framework.accessapi.ConditionalCriteria;
import org.sculptor.framework.domain.PagedResult;
import org.sculptor.framework.domain.PagingParameter;

/**
 * Generated interface for Repository for Download
 */
public interface DownloadRepository {

	public final static String BEAN_ID = "downloadRepository";

	public PagedResult<Download> findByCondition(List<ConditionalCriteria> condition, PagingParameter pagingParameter);

	public Download findByTokens(String tokens);

	public Download findById(Long id) throws DownloadNotFoundException;

	public List<Download> findAll();

	public Download save(Download entity);

	public void delete(Download entity);

}
