package cn.com.mymodule.domain;

import cn.com.mymodule.domain.GocFile;

/**
 * Builder for GocFile class.
 */
public class GocFileBuilder {

	private String resNum;
	private String bucket;
	private String dir;
	private String fileName;
	private String title;
	private String descript;
	private String downId;

	/**
	 * Static factory method for GocFileBuilder
	 */
	public static GocFileBuilder gocFile() {
		return new GocFileBuilder();
	}

	public GocFileBuilder() {
	}

	public GocFileBuilder resNum(String val) {
		this.resNum = val;
		return this;
	}

	public GocFileBuilder bucket(String val) {
		this.bucket = val;
		return this;
	}

	public GocFileBuilder dir(String val) {
		this.dir = val;
		return this;
	}

	public GocFileBuilder fileName(String val) {
		this.fileName = val;
		return this;
	}

	public GocFileBuilder title(String val) {
		this.title = val;
		return this;
	}

	public GocFileBuilder descript(String val) {
		this.descript = val;
		return this;
	}

	public GocFileBuilder downId(String val) {
		this.downId = val;
		return this;
	}

	public String getResNum() {
		return resNum;
	}

	public String getBucket() {
		return bucket;
	}

	public String getDir() {
		return dir;
	}

	public String getFileName() {
		return fileName;
	}

	public String getTitle() {
		return title;
	}

	public String getDescript() {
		return descript;
	}

	public String getDownId() {
		return downId;
	}

	/**
	 * @return new GocFile instance constructed based on the values that have been set into this builder
	 */
	public GocFile build() {
		GocFile obj = new GocFile();
		obj.setResNum(resNum);
		obj.setBucket(bucket);
		obj.setDir(dir);
		obj.setFileName(fileName);
		obj.setTitle(title);
		obj.setDescript(descript);
		obj.setDownId(downId);

		return obj;
	}
}
