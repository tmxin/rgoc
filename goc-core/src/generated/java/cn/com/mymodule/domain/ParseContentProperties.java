package cn.com.mymodule.domain;

import cn.com.mymodule.domain.ParseContent;
import org.sculptor.framework.domain.LeafProperty;
import org.sculptor.framework.domain.PropertiesCollection;
import org.sculptor.framework.domain.Property;

/**
 * This generated interface defines property names for all attributes and associatations in
 * {@link cn.com.mymodule.domain.ParseContent}.
 * <p>
 * These properties are useful when building criteria with {@link org.sculptor.framework.accessapi.ConditionalCriteriaBuilder},
 * which can be used with findByCondition Repository operation.
 */
public class ParseContentProperties {

	private ParseContentProperties() {
	}

	private static final ParseContentPropertiesImpl<ParseContent> sharedInstance = new ParseContentPropertiesImpl<ParseContent>(
			ParseContent.class);

	public static Property<ParseContent> id() {
		return sharedInstance.id();
	}

	public static Property<ParseContent> resNum() {
		return sharedInstance.resNum();
	}

	public static Property<ParseContent> urls() {
		return sharedInstance.urls();
	}

	public static Property<ParseContent> title() {
		return sharedInstance.title();
	}

	public static Property<ParseContent> fileName() {
		return sharedInstance.fileName();
	}

	public static Property<ParseContent> uptime() {
		return sharedInstance.uptime();
	}

	public static Property<ParseContent> fsize() {
		return sharedInstance.fsize();
	}

	public static Property<ParseContent> contents() {
		return sharedInstance.contents();
	}

	/**
	 * This class is used for references to {@link cn.com.mymodule.domain.ParseContent}, i.e. nested property.
	 */
	public static class ParseContentProperty<T> extends ParseContentPropertiesImpl<T> implements Property<T> {
		private static final long serialVersionUID = 1L;

		public ParseContentProperty(String parentPath, String additionalPath, Class<T> owningClass) {
			super(parentPath, additionalPath, owningClass);
		}
	}

	protected static class ParseContentPropertiesImpl<T> extends PropertiesCollection {
		private static final long serialVersionUID = 1L;
		Class<T> owningClass;

		ParseContentPropertiesImpl(Class<T> owningClass) {
			super(null);
			this.owningClass = owningClass;
		}

		ParseContentPropertiesImpl(String parentPath, String additionalPath, Class<T> owningClass) {
			super(parentPath, additionalPath);
			this.owningClass = owningClass;
		}

		public Property<T> id() {
			return new LeafProperty<T>(getParentPath(), "id", false, owningClass);
		}

		public Property<T> resNum() {
			return new LeafProperty<T>(getParentPath(), "resNum", false, owningClass);
		}

		public Property<T> urls() {
			return new LeafProperty<T>(getParentPath(), "urls", false, owningClass);
		}

		public Property<T> title() {
			return new LeafProperty<T>(getParentPath(), "title", false, owningClass);
		}

		public Property<T> fileName() {
			return new LeafProperty<T>(getParentPath(), "fileName", false, owningClass);
		}

		public Property<T> uptime() {
			return new LeafProperty<T>(getParentPath(), "uptime", false, owningClass);
		}

		public Property<T> fsize() {
			return new LeafProperty<T>(getParentPath(), "fsize", false, owningClass);
		}

		public Property<T> contents() {
			return new LeafProperty<T>(getParentPath(), "contents", false, owningClass);
		}
	}
}
