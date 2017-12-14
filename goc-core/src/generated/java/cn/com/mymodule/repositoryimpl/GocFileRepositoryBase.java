package cn.com.mymodule.repositoryimpl;

import cn.com.mymodule.domain.GocFile;
import cn.com.mymodule.domain.GocFileRepository;
import cn.com.mymodule.exception.GocFileNotFoundException;
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
 * Generated base class for implementation of Repository for GocFile
 * 
 * <p>
 * Make sure that subclass defines the following annotations:
 * 
 * <pre>
 *      @org.springframework.stereotype.Repository("gocFileRepository")
 * </pre>
 * 
 */
public abstract class GocFileRepositoryBase extends JpaDaoSupport implements GocFileRepository {

	public GocFileRepositoryBase() {
	}

	/**
	 * Delegates to {@link org.sculptor.framework.accessapi.FindByIdAccess}
	 */
	public GocFile findById(Long id) throws GocFileNotFoundException {

		FindByIdAccess<GocFile, Long> ao = createFindByIdAccess();

		ao.setId(id);
		ao.execute();
		if (ao.getResult() == null) {
			throw new GocFileNotFoundException("No GocFile found with id: " + id);
		}
		return ao.getResult();
	}

	/**
	 * Delegates to {@link org.sculptor.framework.accessapi.FindAllAccess}
	 */
	public List<GocFile> findAll() {
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
	public GocFile save(GocFile entity) {

		SaveAccess<GocFile> ao = createSaveAccess();

		ao.setEntity(entity);
		ao.execute();
		return ao.getResult();
	}

	/**
	 * Delegates to {@link org.sculptor.framework.accessapi.DeleteAccess}
	 */
	public void delete(GocFile entity) {

		DeleteAccess<GocFile> ao = createDeleteAccess();

		ao.setEntity(entity);
		ao.execute();
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

	public PagedResult<GocFile> findByCondition(List<ConditionalCriteria> condition, PagingParameter pagingParameter) {
		FindByConditionAccess2<GocFile> ao = createFindByConditionAccess();

		ao.setCondition(condition);

		if (pagingParameter.getStartRow() != PagedResult.UNKNOWN && pagingParameter.getRealFetchCount() != PagedResult.UNKNOWN) {
			ao.setFirstResult(pagingParameter.getStartRow());
			ao.setMaxResult(pagingParameter.getRealFetchCount());
		}

		ao.execute();
		List<GocFile> result = ao.getResult();
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

		PagedResult<GocFile> pagedResult = new PagedResult<GocFile>(result, pagingParameter.getStartRow(),
				pagingParameter.getRowCount(), pagingParameter.getPageSize(), rowCount, additionalRows);
		return pagedResult;
	}

	public GocFile findByResNum(String resNum) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("resNum", resNum);
		GocFile result = findByQuery("from GocFile where resNum=:resNum", parameters, true, GocFile.class);
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
	protected FindByConditionAccess2<GocFile> createFindByConditionAccess() {
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

	protected FindByIdAccess<GocFile, Long> createFindByIdAccess() {
		JpaFindByIdAccessImpl<GocFile, Long> ao = new JpaFindByIdAccessImpl<GocFile, Long>(getPersistentClass());
		ao.setEntityManager(getEntityManager());
		return ao;
	}

	// convenience method
	protected FindAllAccess2<GocFile> createFindAllAccess() {
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

	protected SaveAccess<GocFile> createSaveAccess() {
		JpaSaveAccessImpl<GocFile> ao = new JpaSaveAccessImpl<GocFile>(getPersistentClass());
		ao.setEntityManager(getEntityManager());
		return ao;
	}

	protected DeleteAccess<GocFile> createDeleteAccess() {
		JpaDeleteAccessImpl<GocFile> ao = new JpaDeleteAccessImpl<GocFile>(getPersistentClass());
		ao.setEntityManager(getEntityManager());
		return ao;
	}

	// convenience method
	protected FindByQueryAccess2<GocFile> createFindByQueryAccess() {
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

	protected Class<GocFile> getPersistentClass() {
		return GocFile.class;
	}

}
