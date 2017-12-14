package cn.com.mymodule.domain;

import cn.com.mymodule.domain.DownloadLog;

/**
 * Builder for DownloadLog class.
 */
public class DownloadLogBuilder {

	private String tokens;
	private String times;
	private String sorceNum;

	/**
	 * Static factory method for DownloadLogBuilder
	 */
	public static DownloadLogBuilder downloadLog() {
		return new DownloadLogBuilder();
	}

	public DownloadLogBuilder() {
	}

	public DownloadLogBuilder tokens(String val) {
		this.tokens = val;
		return this;
	}

	public DownloadLogBuilder times(String val) {
		this.times = val;
		return this;
	}

	public DownloadLogBuilder sorceNum(String val) {
		this.sorceNum = val;
		return this;
	}

	public String getTokens() {
		return tokens;
	}

	public String getTimes() {
		return times;
	}

	public String getSorceNum() {
		return sorceNum;
	}

	/**
	 * @return new DownloadLog instance constructed based on the values that have been set into this builder
	 */
	public DownloadLog build() {
		DownloadLog obj = new DownloadLog();
		obj.setTokens(tokens);
		obj.setTimes(times);
		obj.setSorceNum(sorceNum);

		return obj;
	}
}
