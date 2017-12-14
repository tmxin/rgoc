package cn.com.mymodule.serviceimpl;

import cn.com.mymodule.domain.ParseContent;
import cn.com.mymodule.domain.ParseContentRepository;
import cn.com.mymodule.exception.ParseContentNotFoundException;
import cn.com.mymodule.serviceapi.ParseContentService;
import java.util.List;
import org.sculptor.framework.errorhandling.ServiceContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Generated base class for implementation of ParseContentService.
 * <p>
 * Make sure that subclass defines the following annotations:
 * 
 * <pre>
 * @org.springframework.stereotype.Service("parseContentService")
 * </pre>
 * 
 */
public abstract class ParseContentServiceImplBase implements ParseContentService {

	public ParseContentServiceImplBase() {
	}

	@Autowired
	private ParseContentRepository parseContentRepository;

	protected ParseContentRepository getParseContentRepository() {
		return parseContentRepository;
	}

	/**
	 * Delegates to {@link cn.com.mymodule.domain.ParseContentRepository#findByResNum}
	 */
	public List<ParseContent> findByResNum(ServiceContext ctx, String resNum) {
		return parseContentRepository.findByResNum(resNum);
	}

	/**
	 * Delegates to {@link cn.com.mymodule.domain.ParseContentRepository#findByNull}
	 */
	public List<ParseContent> findByNull(ServiceContext ctx) {
		return parseContentRepository.findByNull();
	}

	/**
	 * Delegates to {@link cn.com.mymodule.domain.ParseContentRepository#findById}
	 */
	public ParseContent findById(ServiceContext ctx, Long id) throws ParseContentNotFoundException {
		return parseContentRepository.findById(id);
	}

	/**
	 * Delegates to {@link cn.com.mymodule.domain.ParseContentRepository#findAll}
	 */
	public List<ParseContent> findAll(ServiceContext ctx) {
		return parseContentRepository.findAll();
	}

	/**
	 * Delegates to {@link cn.com.mymodule.domain.ParseContentRepository#save}
	 */
	public ParseContent save(ServiceContext ctx, ParseContent entity) {
		return parseContentRepository.save(entity);
	}

	/**
	 * Delegates to {@link cn.com.mymodule.domain.ParseContentRepository#delete}
	 */
	public void delete(ServiceContext ctx, ParseContent entity) {
		parseContentRepository.delete(entity);
	}

}
