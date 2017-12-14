package cn.com.mymodule.domain;

import cn.com.mymodule.domain.GocFile;
import cn.com.mymodule.exception.GocFileNotFoundException;
import java.util.List;
import org.sculptor.framework.accessapi.ConditionalCriteria;
import org.sculptor.framework.domain.PagedResult;
import org.sculptor.framework.domain.PagingParameter;

/**
 * Generated interface for Repository for GocFile
 */
public interface GocFileRepository {

	public final static String BEAN_ID = "gocFileRepository";

	public PagedResult<GocFile> findByCondition(List<ConditionalCriteria> condition, PagingParameter pagingParameter);

	public GocFile findByResNum(String resNum);

	public GocFile findById(Long id) throws GocFileNotFoundException;

	public List<GocFile> findAll();

	public GocFile save(GocFile entity);

	public void delete(GocFile entity);

}
