package cn.com.mymodule.domin;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * Data transfer object for Page. Properties and associations are implemented in the generated base class
 * {@link cn.com.carsmart.eden.dafmanager.serviceapi.PageBase}.
 */

public class Page<T> {

	private static final long serialVersionUID = 1L;
	
	public Page() {
	}
	private int pageindex;
	private int pagesize;
	private int totalCount;
	private int totalPages;
	
	private List<T> items = new ArrayList<T>();

	
	
	public int getPageindex() {
		return pageindex;
	}

	public void setPageindex(int pageindex) {
		this.pageindex = pageindex;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	@XmlElementWrapper(name = "items")
	@XmlElement(name = "item")
	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}
	
}
