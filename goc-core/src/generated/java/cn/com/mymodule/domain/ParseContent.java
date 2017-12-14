package cn.com.mymodule.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.sculptor.framework.domain.AbstractDomainObject;
import org.sculptor.framework.domain.Identifiable;

/**
 * Entity representing ParseContent.
 */

@Entity
@Table(name = "PARSECONTENT")
@EntityListeners({})
public class ParseContent extends AbstractDomainObject implements Identifiable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	@Column(name = "RESNUM", length = 100)
	private String resNum;
	@Column(name = "URLS", length = 100)
	private String urls;
	@Column(name = "TITLE", length = 100)
	private String title;
	@Column(name = "FILENAME", length = 100)
	private String fileName;
	@Column(name = "UPTIME", length = 100)
	private String uptime;
	@Column(name = "FSIZE", length = 100)
	private String fsize;
	@Column(name = "CONTENTS", length = 100)
	private String contents;

	public ParseContent() {
	}

	public Long getId() {
		return id;
	}

	/**
	 * The id is not intended to be changed or assigned manually, but for test purpose it is allowed to assign the id.
	 */
	protected void setId(Long id) {
		if ((this.id != null) && !this.id.equals(id)) {
			throw new IllegalArgumentException("Not allowed to change the id property.");
		}
		this.id = id;
	}

	public String getResNum() {
		return resNum;
	}

	public void setResNum(String resNum) {
		this.resNum = resNum;

	}

	public String getUrls() {
		return urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;

	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;

	}

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;

	}

	public String getFsize() {
		return fsize;
	}

	public void setFsize(String fsize) {
		this.fsize = fsize;

	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;

	}

	/**
	 * This method is used by equals and hashCode.
	 * 
	 * @return {@link #getId}
	 */
	public Object getKey() {
		return getId();
	}

}
