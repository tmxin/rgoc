package cn.com.mymodule.serviceimpl;

import cn.com.mymodule.domain.DownloadLog;
import cn.com.mymodule.domain.DownloadLogRepository;
import cn.com.mymodule.exception.DownloadLogNotFoundException;
import cn.com.mymodule.serviceapi.DownloadLogService;
import java.util.List;
import org.sculptor.framework.errorhandling.ServiceContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Generated base class for implementation of DownloadLogService.
 * <p>
 * Make sure that subclass defines the following annotations:
 * 
 * <pre>
 * @org.springframework.stereotype.Service("downloadLogService")
 * </pre>
 * 
 */
public abstract class DownloadLogServiceImplBase implements DownloadLogService {

	public DownloadLogServiceImplBase() {
	}

	@Autowired
	private DownloadLogRepository downloadLogRepository;

	protected DownloadLogRepository getDownloadLogRepository() {
		return downloadLogRepository;
	}

	/**
	 * Delegates to {@link cn.com.mymodule.domain.DownloadLogRepository#findByTokens}
	 */
	public List<DownloadLog> findByTokens(ServiceContext ctx, String tokens) {
		return downloadLogRepository.findByTokens(tokens);
	}

	/**
	 * Delegates to {@link cn.com.mymodule.domain.DownloadLogRepository#findByTAndS}
	 */
	public DownloadLog findByTAndS(ServiceContext ctx, String tokens, String sorceNum) {
		return downloadLogRepository.findByTAndS(tokens, sorceNum);
	}

	/**
	 * Delegates to {@link cn.com.mymodule.domain.DownloadLogRepository#findById}
	 */
	public DownloadLog findById(ServiceContext ctx, Long id) throws DownloadLogNotFoundException {
		return downloadLogRepository.findById(id);
	}

	/**
	 * Delegates to {@link cn.com.mymodule.domain.DownloadLogRepository#findAll}
	 */
	public List<DownloadLog> findAll(ServiceContext ctx) {
		return downloadLogRepository.findAll();
	}

	/**
	 * Delegates to {@link cn.com.mymodule.domain.DownloadLogRepository#save}
	 */
	public DownloadLog save(ServiceContext ctx, DownloadLog entity) {
		return downloadLogRepository.save(entity);
	}

	/**
	 * Delegates to {@link cn.com.mymodule.domain.DownloadLogRepository#delete}
	 */
	public void delete(ServiceContext ctx, DownloadLog entity) {
		downloadLogRepository.delete(entity);
	}

}
