package cn.com.mymodule.domain;

import cn.com.mymodule.domain.Download;

/**
 * Builder for Download class.
 */
public class DownloadBuilder {

	private String tokens;
	private Integer num;
	private String startTime;
	private String endTime;

	/**
	 * Static factory method for DownloadBuilder
	 */
	public static DownloadBuilder download() {
		return new DownloadBuilder();
	}

	public DownloadBuilder() {
	}

	public DownloadBuilder tokens(String val) {
		this.tokens = val;
		return this;
	}

	public DownloadBuilder num(Integer val) {
		this.num = val;
		return this;
	}

	public DownloadBuilder startTime(String val) {
		this.startTime = val;
		return this;
	}

	public DownloadBuilder endTime(String val) {
		this.endTime = val;
		return this;
	}

	public String getTokens() {
		return tokens;
	}

	public Integer getNum() {
		return num;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	/**
	 * @return new Download instance constructed based on the values that have been set into this builder
	 */
	public Download build() {
		Download obj = new Download();
		obj.setTokens(tokens);
		obj.setNum(num);
		obj.setStartTime(startTime);
		obj.setEndTime(endTime);

		return obj;
	}
}
