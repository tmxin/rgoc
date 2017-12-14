package cn.com.mymodule.domain;

import cn.com.mymodule.domain.ParseContent;
import cn.com.mymodule.exception.ParseContentNotFoundException;
import java.util.List;
import org.sculptor.framework.accessapi.ConditionalCriteria;
import org.sculptor.framework.domain.PagedResult;
import org.sculptor.framework.domain.PagingParameter;

/**
 * Generated interface for Repository for ParseContent
 */
public interface ParseContentRepository {

	public final static String BEAN_ID = "parseContentRepository";

	public PagedResult<ParseContent> findByCondition(List<ConditionalCriteria> condition, PagingParameter pagingParameter);

	public List<ParseContent> findByResNum(String resNum);

	public List<ParseContent> findByNull();

	public ParseContent findById(Long id) throws ParseContentNotFoundException;

	public List<ParseContent> findAll();

	public ParseContent save(ParseContent entity);

	public void delete(ParseContent entity);

}
