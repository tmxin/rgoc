package cn.com.mymodule.domain;

import cn.com.mymodule.domain.DownloadLog;
import org.sculptor.framework.domain.LeafProperty;
import org.sculptor.framework.domain.PropertiesCollection;
import org.sculptor.framework.domain.Property;

/**
 * This generated interface defines property names for all attributes and associatations in
 * {@link cn.com.mymodule.domain.DownloadLog}.
 * <p>
 * These properties are useful when building criteria with {@link org.sculptor.framework.accessapi.ConditionalCriteriaBuilder},
 * which can be used with findByCondition Repository operation.
 */
public class DownloadLogProperties {

	private DownloadLogProperties() {
	}

	private static final DownloadLogPropertiesImpl<DownloadLog> sharedInstance = new DownloadLogPropertiesImpl<DownloadLog>(
			DownloadLog.class);

	public static Property<DownloadLog> id() {
		return sharedInstance.id();
	}

	public static Property<DownloadLog> tokens() {
		return sharedInstance.tokens();
	}

	public static Property<DownloadLog> times() {
		return sharedInstance.times();
	}

	public static Property<DownloadLog> sorceNum() {
		return sharedInstance.sorceNum();
	}

	/**
	 * This class is used for references to {@link cn.com.mymodule.domain.DownloadLog}, i.e. nested property.
	 */
	public static class DownloadLogProperty<T> extends DownloadLogPropertiesImpl<T> implements Property<T> {
		private static final long serialVersionUID = 1L;

		public DownloadLogProperty(String parentPath, String additionalPath, Class<T> owningClass) {
			super(parentPath, additionalPath, owningClass);
		}
	}

	protected static class DownloadLogPropertiesImpl<T> extends PropertiesCollection {
		private static final long serialVersionUID = 1L;
		Class<T> owningClass;

		DownloadLogPropertiesImpl(Class<T> owningClass) {
			super(null);
			this.owningClass = owningClass;
		}

		DownloadLogPropertiesImpl(String parentPath, String additionalPath, Class<T> owningClass) {
			super(parentPath, additionalPath);
			this.owningClass = owningClass;
		}

		public Property<T> id() {
			return new LeafProperty<T>(getParentPath(), "id", false, owningClass);
		}

		public Property<T> tokens() {
			return new LeafProperty<T>(getParentPath(), "tokens", false, owningClass);
		}

		public Property<T> times() {
			return new LeafProperty<T>(getParentPath(), "times", false, owningClass);
		}

		public Property<T> sorceNum() {
			return new LeafProperty<T>(getParentPath(), "sorceNum", false, owningClass);
		}
	}
}
