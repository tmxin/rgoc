package cn.com.mymodule.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.sculptor.framework.domain.AbstractDomainObject;
import org.sculptor.framework.domain.Identifiable;

/**
 * Entity representing Download.
 */

@Entity
@Table(name = "DOWNLOAD")
@EntityListeners({})
public class Download extends AbstractDomainObject implements Identifiable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	@Column(name = "TOKENS", length = 100)
	private String tokens;
	@Column(name = "NUM", nullable = false)
	@NotNull
	private Integer num;
	@Column(name = "STARTTIME", nullable = false, length = 100)
	@NotNull
	private String startTime;
	@Column(name = "ENDTIME", nullable = false, length = 100)
	@NotNull
	private String endTime;

	public Download() {
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

	public String getTokens() {
		return tokens;
	}

	public void setTokens(String tokens) {
		this.tokens = tokens;

	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;

	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;

	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;

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
