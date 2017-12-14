package cn.com.mymodule.serviceapi;

import cn.com.mymodule.serviceapi.GocFileService;
import org.junit.Test;
import org.sculptor.framework.test.AbstractDbUnitJpaTests;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Spring based transactional test with DbUnit support.
 */
public class GocFileServiceTest extends AbstractDbUnitJpaTests implements GocFileServiceTestBase {

	@Autowired
	protected GocFileService gocFileService;

	@Test
	public void testGetUrls() throws Exception {
		// TODO Auto-generated method stub
		fail("testGetUrls not implemented");
	}

	@Test
	public void testFindByResNum() throws Exception {
		// TODO Auto-generated method stub
		fail("testFindByResNum not implemented");
	}

	@Test
	public void testFindListByPage() throws Exception {
		// TODO Auto-generated method stub
		fail("testFindListByPage not implemented");
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
