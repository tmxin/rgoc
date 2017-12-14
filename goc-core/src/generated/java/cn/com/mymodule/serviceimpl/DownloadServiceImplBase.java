package cn.com.mymodule.serviceimpl;

import cn.com.mymodule.domain.Download;
import cn.com.mymodule.domain.DownloadRepository;
import cn.com.mymodule.exception.DownloadNotFoundException;
import cn.com.mymodule.serviceapi.DownloadService;
import java.util.List;
import org.sculptor.framework.errorhandling.ServiceContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Generated base class for implementation of DownloadService.
 * <p>
 * Make sure that subclass defines the following annotations:
 * 
 * <pre>
 * @org.springframework.stereotype.Service("downloadService")
 * </pre>
 * 
 */
public abstract class DownloadServiceImplBase implements DownloadService {

	public DownloadServiceImplBase() {
	}

	@Autowired
	private DownloadRepository downloadRepository;

	protected DownloadRepository getDownloadRepository() {
		return downloadRepository;
	}

	/**
	 * Delegates to {@link cn.com.mymodule.domain.DownloadRepository#findByTokens}
	 */
	public Download findByTokens(ServiceContext ctx, String tokens) {
		return downloadRepository.findByTokens(tokens);
	}

	/**
	 * Delegates to {@link cn.com.mymodule.domain.DownloadRepository#findById}
	 */
	public Download findById(ServiceContext ctx, Long id) throws DownloadNotFoundException {
		return downloadRepository.findById(id);
	}

	/**
	 * Delegates to {@link cn.com.mymodule.domain.DownloadRepository#findAll}
	 */
	public List<Download> findAll(ServiceContext ctx) {
		return downloadRepository.findAll();
	}

	/**
	 * Delegates to {@link cn.com.mymodule.domain.DownloadRepository#save}
	 */
	public Download save(ServiceContext ctx, Download entity) {
		return downloadRepository.save(entity);
	}

	/**
	 * Delegates to {@link cn.com.mymodule.domain.DownloadRepository#delete}
	 */
	public void delete(ServiceContext ctx, Download entity) {
		downloadRepository.delete(entity);
	}

}
