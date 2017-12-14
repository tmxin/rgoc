package cn.com.mymodule.serviceapi;

import cn.com.mymodule.domain.ParseContent;
import cn.com.mymodule.exception.ParseContentNotFoundException;
import java.util.List;
import java.util.Map;
import org.sculptor.framework.errorhandling.ServiceContext;

/**
 * Generated interface for the Service ParseContentService.
 */
public interface ParseContentService {

	public final static String BEAN_ID = "parseContentService";

	public void parseF(ServiceContext ctx, String urls, String fileName);

	public Map<String, String> logins(ServiceContext ctx);

	public List<String> getVipList(ServiceContext ctx, Map<String, String> cook, int i);

	public boolean checkUrls(ServiceContext ctx, String resNum, String fileName);

	public void longUrls(ServiceContext ctx, String longUrl);

	public List<ParseContent> findByResNum(ServiceContext ctx, String resNum);

	public List<ParseContent> findByNull(ServiceContext ctx);

	public void getAllHrefs(ServiceContext ctx);

	public ParseContent findById(ServiceContext ctx, Long id) throws ParseContentNotFoundException;

	public List<ParseContent> findAll(ServiceContext ctx);

	public ParseContent save(ServiceContext ctx, ParseContent entity);

	public void delete(ServiceContext ctx, ParseContent entity);

}
