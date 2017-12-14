package cn.com.mymodule.domain;

import cn.com.mymodule.domain.ParseContent;

/**
 * Builder for ParseContent class.
 */
public class ParseContentBuilder {

	private String resNum;
	private String urls;
	private String title;
	private String fileName;
	private String uptime;
	private String fsize;
	private String contents;

	/**
	 * Static factory method for ParseContentBuilder
	 */
	public static ParseContentBuilder parseContent() {
		return new ParseContentBuilder();
	}

	public ParseContentBuilder() {
	}

	public ParseContentBuilder resNum(String val) {
		this.resNum = val;
		return this;
	}

	public ParseContentBuilder urls(String val) {
		this.urls = val;
		return this;
	}

	public ParseContentBuilder title(String val) {
		this.title = val;
		return this;
	}

	public ParseContentBuilder fileName(String val) {
		this.fileName = val;
		return this;
	}

	public ParseContentBuilder uptime(String val) {
		this.uptime = val;
		return this;
	}

	public ParseContentBuilder fsize(String val) {
		this.fsize = val;
		return this;
	}

	public ParseContentBuilder contents(String val) {
		this.contents = val;
		return this;
	}

	public String getResNum() {
		return resNum;
	}

	public String getUrls() {
		return urls;
	}

	public String getTitle() {
		return title;
	}

	public String getFileName() {
		return fileName;
	}

	public String getUptime() {
		return uptime;
	}

	public String getFsize() {
		return fsize;
	}

	public String getContents() {
		return contents;
	}

	/**
	 * @return new ParseContent instance constructed based on the values that have been set into this builder
	 */
	public ParseContent build() {
		ParseContent obj = new ParseContent();
		obj.setResNum(resNum);
		obj.setUrls(urls);
		obj.setTitle(title);
		obj.setFileName(fileName);
		obj.setUptime(uptime);
		obj.setFsize(fsize);
		obj.setContents(contents);

		return obj;
	}
}
