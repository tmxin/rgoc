package cn.com.mymodule.serviceapi;

import cn.com.mymodule.serviceapi.AuthordService;
import org.junit.Test;
import org.sculptor.framework.test.AbstractDbUnitJpaTests;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Spring based transactional test with DbUnit support.
 */
public class AuthordServiceTest extends AbstractDbUnitJpaTests implements AuthordServiceTestBase {

	@Autowired
	protected AuthordService authordService;

	@Test
	public void testGetPolicy() throws Exception {
		// TODO Auto-generated method stub
		fail("testGetPolicy not implemented");
	}
}
