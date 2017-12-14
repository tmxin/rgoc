package cn.com.mymodule.serviceapi;

import net.sf.json.JSONObject;
import org.sculptor.framework.errorhandling.ServiceContext;

/**
 * Generated interface for the Service AuthordService.
 */
public interface AuthordService {

	public final static String BEAN_ID = "authordService";

	public JSONObject getPolicy(ServiceContext ctx);

}
