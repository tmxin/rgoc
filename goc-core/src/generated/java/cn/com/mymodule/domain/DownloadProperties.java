package cn.com.mymodule.domain;

import cn.com.mymodule.domain.Download;
import org.sculptor.framework.domain.LeafProperty;
import org.sculptor.framework.domain.PropertiesCollection;
import org.sculptor.framework.domain.Property;

/**
 * This generated interface defines property names for all attributes and associatations in {@link cn.com.mymodule.domain.Download}.
 * <p>
 * These properties are useful when building criteria with {@link org.sculptor.framework.accessapi.ConditionalCriteriaBuilder},
 * which can be used with findByCondition Repository operation.
 */
public class DownloadProperties {

	private DownloadProperties() {
	}

	private static final DownloadPropertiesImpl<Download> sharedInstance = new DownloadPropertiesImpl<Download>(Download.class);

	public static Property<Download> id() {
		return sharedInstance.id();
	}

	public static Property<Download> tokens() {
		return sharedInstance.tokens();
	}

	public static Property<Download> num() {
		return sharedInstance.num();
	}

	public static Property<Download> startTime() {
		return sharedInstance.startTime();
	}

	public static Property<Download> endTime() {
		return sharedInstance.endTime();
	}

	/**
	 * This class is used for references to {@link cn.com.mymodule.domain.Download}, i.e. nested property.
	 */
	public static class DownloadProperty<T> extends DownloadPropertiesImpl<T> implements Property<T> {
		private static final long serialVersionUID = 1L;

		public DownloadProperty(String parentPath, String additionalPath, Class<T> owningClass) {
			super(parentPath, additionalPath, owningClass);
		}
	}

	protected static class DownloadPropertiesImpl<T> extends PropertiesCollection {
		private static final long serialVersionUID = 1L;
		Class<T> owningClass;

		DownloadPropertiesImpl(Class<T> owningClass) {
			super(null);
			this.owningClass = owningClass;
		}

		DownloadPropertiesImpl(String parentPath, String additionalPath, Class<T> owningClass) {
			super(parentPath, additionalPath);
			this.owningClass = owningClass;
		}

		public Property<T> id() {
			return new LeafProperty<T>(getParentPath(), "id", false, owningClass);
		}

		public Property<T> tokens() {
			return new LeafProperty<T>(getParentPath(), "tokens", false, owningClass);
		}

		public Property<T> num() {
			return new LeafProperty<T>(getParentPath(), "num", false, owningClass);
		}

		public Property<T> startTime() {
			return new LeafProperty<T>(getParentPath(), "startTime", false, owningClass);
		}

		public Property<T> endTime() {
			return new LeafProperty<T>(getParentPath(), "endTime", false, owningClass);
		}
	}
}
