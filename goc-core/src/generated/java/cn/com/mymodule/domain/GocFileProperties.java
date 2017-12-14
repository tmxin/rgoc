package cn.com.mymodule.domain;

import cn.com.mymodule.domain.GocFile;
import org.sculptor.framework.domain.LeafProperty;
import org.sculptor.framework.domain.PropertiesCollection;
import org.sculptor.framework.domain.Property;

/**
 * This generated interface defines property names for all attributes and associatations in {@link cn.com.mymodule.domain.GocFile}.
 * <p>
 * These properties are useful when building criteria with {@link org.sculptor.framework.accessapi.ConditionalCriteriaBuilder},
 * which can be used with findByCondition Repository operation.
 */
public class GocFileProperties {

	private GocFileProperties() {
	}

	private static final GocFilePropertiesImpl<GocFile> sharedInstance = new GocFilePropertiesImpl<GocFile>(GocFile.class);

	public static Property<GocFile> id() {
		return sharedInstance.id();
	}

	public static Property<GocFile> resNum() {
		return sharedInstance.resNum();
	}

	public static Property<GocFile> bucket() {
		return sharedInstance.bucket();
	}

	public static Property<GocFile> dir() {
		return sharedInstance.dir();
	}

	public static Property<GocFile> fileName() {
		return sharedInstance.fileName();
	}

	public static Property<GocFile> title() {
		return sharedInstance.title();
	}

	public static Property<GocFile> descript() {
		return sharedInstance.descript();
	}

	public static Property<GocFile> downId() {
		return sharedInstance.downId();
	}

	/**
	 * This class is used for references to {@link cn.com.mymodule.domain.GocFile}, i.e. nested property.
	 */
	public static class GocFileProperty<T> extends GocFilePropertiesImpl<T> implements Property<T> {
		private static final long serialVersionUID = 1L;

		public GocFileProperty(String parentPath, String additionalPath, Class<T> owningClass) {
			super(parentPath, additionalPath, owningClass);
		}
	}

	protected static class GocFilePropertiesImpl<T> extends PropertiesCollection {
		private static final long serialVersionUID = 1L;
		Class<T> owningClass;

		GocFilePropertiesImpl(Class<T> owningClass) {
			super(null);
			this.owningClass = owningClass;
		}

		GocFilePropertiesImpl(String parentPath, String additionalPath, Class<T> owningClass) {
			super(parentPath, additionalPath);
			this.owningClass = owningClass;
		}

		public Property<T> id() {
			return new LeafProperty<T>(getParentPath(), "id", false, owningClass);
		}

		public Property<T> resNum() {
			return new LeafProperty<T>(getParentPath(), "resNum", false, owningClass);
		}

		public Property<T> bucket() {
			return new LeafProperty<T>(getParentPath(), "bucket", false, owningClass);
		}

		public Property<T> dir() {
			return new LeafProperty<T>(getParentPath(), "dir", false, owningClass);
		}

		public Property<T> fileName() {
			return new LeafProperty<T>(getParentPath(), "fileName", false, owningClass);
		}

		public Property<T> title() {
			return new LeafProperty<T>(getParentPath(), "title", false, owningClass);
		}

		public Property<T> descript() {
			return new LeafProperty<T>(getParentPath(), "descript", false, owningClass);
		}

		public Property<T> downId() {
			return new LeafProperty<T>(getParentPath(), "downId", false, owningClass);
		}
	}
}
