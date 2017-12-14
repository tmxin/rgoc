package cn.com.mymodule.serviceimpl;

import cn.com.mymodule.domain.GocFile;
import cn.com.mymodule.domain.GocFileRepository;
import cn.com.mymodule.exception.GocFileNotFoundException;
import cn.com.mymodule.serviceapi.GocFileService;
import java.util.List;
import org.sculptor.framework.errorhandling.ServiceContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Generated base class for implementation of GocFileService.
 * <p>
 * Make sure that subclass defines the following annotations:
 * 
 * <pre>
 * @org.springframework.stereotype.Service("gocFileService")
 * </pre>
 * 
 */
public abstract class GocFileServiceImplBase implements GocFileService {

	public GocFileServiceImplBase() {
	}

	@Autowired
	private GocFileRepository gocFileRepository;

	protected GocFileRepository getGocFileRepository() {
		return gocFileRepository;
	}

	/**
	 * Delegates to {@link cn.com.mymodule.domain.GocFileRepository#findByResNum}
	 */
	public GocFile findByResNum(ServiceContext ctx, String resNum) {
		return gocFileRepository.findByResNum(resNum);
	}

	/**
	 * Delegates to {@link cn.com.mymodule.domain.GocFileRepository#findById}
	 */
	public GocFile findById(ServiceContext ctx, Long id) throws GocFileNotFoundException {
		return gocFileRepository.findById(id);
	}

	/**
	 * Delegates to {@link cn.com.mymodule.domain.GocFileRepository#findAll}
	 */
	public List<GocFile> findAll(ServiceContext ctx) {
		return gocFileRepository.findAll();
	}

	/**
	 * Delegates to {@link cn.com.mymodule.domain.GocFileRepository#save}
	 */
	public GocFile save(ServiceContext ctx, GocFile entity) {
		return gocFileRepository.save(entity);
	}

	/**
	 * Delegates to {@link cn.com.mymodule.domain.GocFileRepository#delete}
	 */
	public void delete(ServiceContext ctx, GocFile entity) {
		gocFileRepository.delete(entity);
	}

}
