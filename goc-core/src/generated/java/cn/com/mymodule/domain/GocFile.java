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
 * Entity representing GocFile.
 */

@Entity
@Table(name = "GOCFILE")
@EntityListeners({})
public class GocFile extends AbstractDomainObject implements Identifiable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	@Column(name = "RESNUM", length = 100)
	private String resNum;
	@Column(name = "BUCKET", length = 100)
	private String bucket;
	@Column(name = "DIR", length = 100)
	private String dir;
	@Column(name = "FILENAME", length = 100)
	private String fileName;
	@Column(name = "TITLE", length = 100)
	private String title;
	@Column(name = "DESCRIPT", length = 100)
	private String descript;
	@Column(name = "DOWNID", length = 100)
	private String downId;

	public GocFile() {
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

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;

	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;

	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;

	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;

	}

	public String getDownId() {
		return downId;
	}

	public void setDownId(String downId) {
		this.downId = downId;

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
