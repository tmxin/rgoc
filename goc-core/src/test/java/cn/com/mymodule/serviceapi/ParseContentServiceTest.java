package cn.com.mymodule.serviceapi;

import cn.com.mymodule.serviceapi.ParseContentService;
import org.junit.Test;
import org.sculptor.framework.test.AbstractDbUnitJpaTests;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Spring based transactional test with DbUnit support.
 */
public class ParseContentServiceTest extends AbstractDbUnitJpaTests implements ParseContentServiceTestBase {

	@Autowired
	protected ParseContentService parseContentService;

	@Test
	public void testParseFiles() throws Exception {
		// TODO Auto-generated method stub
		fail("testParseFiles not implemented");
	}

	@Test
	public void testFindById() throws Exception {
		// TODO Auto-generated method stub
		fail("testFindById not implemented");
	}

	@Test
	public void testFindAll() throws Exception {
		// TODO Auto-generated method stub
		fail("testFindAll not implemented");
	}

	@Test
	public void testSave() throws Exception {
		// TODO Auto-generated method stub
		fail("testSave not implemented");
	}

	@Test
	public void testDelete() throws Exception {
		// TODO Auto-generated method stub
		fail("testDelete not implemented");
	}
}
