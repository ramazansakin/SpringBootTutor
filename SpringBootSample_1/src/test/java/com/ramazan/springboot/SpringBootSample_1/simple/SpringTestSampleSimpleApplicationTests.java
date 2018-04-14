package com.ramazan.springboot.SpringBootSample_1.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Tests for {@link SampleSimpleApplication}.
 * @author Ramazan Sakin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleSimpleApplication.class)
public class SpringTestSampleSimpleApplicationTests {

	@Test
	public void testContextLoads() throws Exception {
	}

}
