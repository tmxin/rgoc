package cn.com.mymodule.repositoryimpl;

import cn.com.mymodule.domain.DownloadLog;
import cn.com.mymodule.domain.DownloadLogRepository;
import cn.com.mymodule.exception.DownloadLogNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.sculptor.framework.accessapi.ConditionalCriteria;
import org.sculptor.framework.accessapi.DeleteAccess;
import org.sculptor.framework.accessapi.FindAllAccess2;
import org.sculptor.framework.accessapi.FindByConditionAccess2;
import org.sculptor.framework.accessapi.FindByIdAccess;
import org.sculptor.framework.accessapi.FindByQueryAccess2;
import org.sculptor.framework.accessapi.SaveAccess;
import org.sculptor.framework.accessimpl.jpa2.JpaDeleteAccessImpl;
import org.sculptor.framework.accessimpl.jpa2.JpaFindAllAccessImplGeneric;
import org.sculptor.framework.accessimpl.jpa2.JpaFindByConditionAccessImplGeneric;
import org.sculptor.framework.accessimpl.jpa2.JpaFindByIdAccessImpl;
import org.sculptor.framework.accessimpl.jpa2.JpaFindByQueryAccessImplGeneric;
import org.sculptor.framework.accessimpl.jpa2.JpaSaveAccessImpl;
import org.sculptor.framework.domain.PagedResult;
import org.sculptor.framework.domain.PagingParameter;
import org.springframework.orm.jpa.support.JpaDaoSupport;

/**
 * Generated base class for implementation of Repository for DownloadLog
 * 
 * <p>
 * Make sure that subclass defines the following annotations:
 * 
 * <pre>
 *      @org.springframework.stereotype.Repository("downloadLogRepository")
 * </pre>
 * 
 */
public abstract class DownloadLogRepositoryBase extends JpaDaoSupport implements DownloadLogRepository {

	public DownloadLogRepositoryBase() {
	}

	/**
	 * Delegates to {@link org.sculptor.framework.accessapi.FindByIdAccess}
	 */
	public DownloadLog findById(Long id) throws DownloadLogNotFoundException {

		FindByIdAccess<DownloadLog, Long> ao = createFindByIdAccess();

		ao.setId(id);
		ao.execute();
		if (ao.getResult() == null) {
			throw new DownloadLogNotFoundException("No DownloadLog found with id: " + id);
		}
		return ao.getResult();
	}

	/**
	 * Delegates to {@link org.sculptor.framework.accessapi.FindAllAccess}
	 */
	public List<DownloadLog> findAll() {
		return findAll(getPersistentClass());
	}

	public <R> List<R> findAll(Class<R> resultType) {

		FindAllAccess2<R> ao = createFindAllAccess(resultType);

		ao.execute();
		return ao.getResult();
	}

	/**
	 * Delegates to {@link org.sculptor.framework.accessapi.SaveAccess}
	 */
	public DownloadLog save(DownloadLog entity) {

		SaveAccess<DownloadLog> ao = createSaveAccess();

		ao.setEntity(entity);
		ao.execute();
		return ao.getResult();
	}

	/**
	 * Delegates to {@link org.sculptor.framework.accessapi.DeleteAccess}
	 */
	public void delete(DownloadLog entity) {

		DeleteAccess<DownloadLog> ao = createDeleteAccess();

		ao.setEntity(entity);
		ao.execute();
	}

	/**
	 * Delegates to {@link org.sculptor.framework.accessapi.FindByQueryAccess}
	 */
	protected List<DownloadLog> findByQuery(String query, Map<String, Object> parameters) {
		return findByQuery(query, parameters, getPersistentClass());
	}

	protected <R> List<R> findByQuery(String query, Map<String, Object> parameters, Class<R> resultType) {

		FindByQueryAccess2<R> ao = createFindByQueryAccess(resultType);

		ao.setQuery(query);
		ao.setParameters(parameters);
		ao.execute();
		return ao.getResult();
	}

	/**
	 * Delegates to {@link org.sculptor.framework.accessapi.FindByQueryAccess}
	 */
	protected Object findByQuery(String query, Map<String, Object> parameters, boolean useSingleResult) {
		return findByQuery(query, parameters, useSingleResult, getPersistentClass());
	}

	protected <R> R findByQuery(String query, Map<String, Object> parameters, boolean useSingleResult, Class<R> resultType) {

		FindByQueryAccess2<R> ao = createFindByQueryAccess(resultType);

		ao.setQuery(query);
		ao.setParameters(parameters);
		ao.setUseSingleResult(useSingleResult);
		ao.execute();
		return ao.getSingleResult();
	}

	public PagedResult<DownloadLog> findByCondition(List<ConditionalCriteria> condition, PagingParameter pagingParameter) {
		FindByConditionAccess2<DownloadLog> ao = createFindByConditionAccess();

		ao.setCondition(condition);

		if (pagingParameter.getStartRow() != PagedResult.UNKNOWN && pagingParameter.getRealFetchCount() != PagedResult.UNKNOWN) {
			ao.setFirstResult(pagingParameter.getStartRow());
			ao.setMaxResult(pagingParameter.getRealFetchCount());
		}

		ao.execute();
		List<DownloadLog> result = ao.getResult();
		int rowCount = PagedResult.UNKNOWN;
		int additionalRows = PagedResult.UNKNOWN;
		if (pagingParameter.getStartRow() != PagedResult.UNKNOWN && pagingParameter.getRealFetchCount() != 0) {
			int resultSize = result.size();
			if (resultSize > 0 && resultSize < pagingParameter.getRealFetchCount()) {
				// Not enough rows fetched - end of result reached, we should fill row
				// count and also additional pages without real counting.
				// Fill it even when nobody ask (isCountTotal), don't cost nothing and can be used on client side
				rowCount = pagingParameter.getStartRow() + resultSize;
				additionalRows = resultSize - pagingParameter.getRowCount();
				additionalRows = additionalRows < 0 ? 0 : additionalRows;
			} else {
				if (pagingParameter.isCountTotal()) {
					// If you need an alternative way to calculate max pages you could define hint="countOperation=..." or
					// hint="countQuery=..."
					ao.executeResultCount();
					Long countNumber = ao.getResultCount();
					rowCount = countNumber == null ? PagedResult.UNKNOWN : countNumber.intValue();
				}
				if (rowCount != PagedResult.UNKNOWN) {
					additionalRows = rowCount - pagingParameter.getEndRow();
					additionalRows = additionalRows < 0 ? 0 : additionalRows;
				} else {
					additionalRows = resultSize - pagingParameter.getRowCount();
					additionalRows = additionalRows < 0 ? 0 : additionalRows;
				}

				additionalRows = additionalRows > pagingParameter.getAdditionalResultRows() ? pagingParameter
						.getAdditionalResultRows() : additionalRows;
			}
		}

		PagedResult<DownloadLog> pagedResult = new PagedResult<DownloadLog>(result, pagingParameter.getStartRow(),
				pagingParameter.getRowCount(), pagingParameter.getPageSize(), rowCount, additionalRows);
		return pagedResult;
	}

	public List<DownloadLog> findByTokens(String tokens) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("tokens", tokens);
		List<DownloadLog> result = findByQuery("from DownloadLog where tokens=:tokens", parameters, DownloadLog.class);
		return result;
	}

	public DownloadLog findByTAndS(String tokens, String sorceNum) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("tokens", tokens);
		parameters.put("sorceNum", sorceNum);
		DownloadLog result = findByQuery("from DownloadLog where tokens=:tokens and sorceNum=:sorceNum", parameters, true,
				DownloadLog.class);
		return result;
	}

	private EntityManager entityManager;

	/**
	 * Dependency injection
	 */
	@PersistenceContext(unitName = "MyAppEntityManagerFactory")
	protected void setEntityManagerDependency(EntityManager entityManager) {
		this.entityManager = entityManager;
		// for JpaDaoSupport, JpaTemplate
		setEntityManager(entityManager);
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	// convenience method
	protected FindByConditionAccess2<DownloadLog> createFindByConditionAccess() {
		return createFindByConditionAccess(getPersistentClass(), getPersistentClass());
	}

	// convenience method
	protected <R> FindByConditionAccess2<R> createFindByConditionAccess(Class<R> resultType) {
		return createFindByConditionAccess(getPersistentClass(), resultType);
	}

	protected <T, R> FindByConditionAccess2<R> createFindByConditionAccess(Class<T> type, Class<R> resultType) {
		JpaFindByConditionAccessImplGeneric<T, R> ao = new JpaFindByConditionAccessImplGeneric<T, R>(type, resultType);
		ao.setEntityManager(getEntityManager());
		return ao;
	}

	protected FindByIdAccess<DownloadLog, Long> createFindByIdAccess() {
		JpaFindByIdAccessImpl<DownloadLog, Long> ao = new JpaFindByIdAccessImpl<DownloadLog, Long>(getPersistentClass());
		ao.setEntityManager(getEntityManager());
		return ao;
	}

	// convenience method
	protected FindAllAccess2<DownloadLog> createFindAllAccess() {
		return createFindAllAccess(getPersistentClass(), getPersistentClass());
	}

	// convenience method
	protected <R> FindAllAccess2<R> createFindAllAccess(Class<R> resultType) {
		return createFindAllAccess(getPersistentClass(), resultType);
	}

	protected <T, R> FindAllAccess2<R> createFindAllAccess(Class<T> type, Class<R> resultType) {
		JpaFindAllAccessImplGeneric<T, R> ao = new JpaFindAllAccessImplGeneric<T, R>(type, resultType);
		ao.setEntityManager(getEntityManager());
		return ao;
	}

	protected SaveAccess<DownloadLog> createSaveAccess() {
		JpaSaveAccessImpl<DownloadLog> ao = new JpaSaveAccessImpl<DownloadLog>(getPersistentClass());
		ao.setEntityManager(getEntityManager());
		return ao;
	}

	protected DeleteAccess<DownloadLog> createDeleteAccess() {
		JpaDeleteAccessImpl<DownloadLog> ao = new JpaDeleteAccessImpl<DownloadLog>(getPersistentClass());
		ao.setEntityManager(getEntityManager());
		return ao;
	}

	// convenience method
	protected FindByQueryAccess2<DownloadLog> createFindByQueryAccess() {
		return createFindByQueryAccess(getPersistentClass(), getPersistentClass());
	}

	// convenience method
	protected <R> FindByQueryAccess2<R> createFindByQueryAccess(Class<R> resultType) {
		return createFindByQueryAccess(getPersistentClass(), resultType);
	}

	protected <T, R> FindByQueryAccess2<R> createFindByQueryAccess(Class<T> type, Class<R> resultType) {
		JpaFindByQueryAccessImplGeneric<T, R> ao = new JpaFindByQueryAccessImplGeneric<T, R>(type, resultType);
		ao.setEntityManager(getEntityManager());
		return ao;
	}

	protected Class<DownloadLog> getPersistentClass() {
		return DownloadLog.class;
	}

}
