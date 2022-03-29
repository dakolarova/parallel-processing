package eu.prototype.parallel.processing.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eu.prototype.parallel.processing.services.api.ProcessingService;

@RunWith( SpringJUnit4ClassRunner.class )
@SpringBootTest(properties = { "service.parallel=false" })
public class ProcessingServiceTest {
	
	@Autowired
	ProcessingService processingService;
	
	ProcessingService processingServiceDecorator;
	
	@Test
	public void shouldRunParallelProcessingUsingDecorator() {
		processingServiceDecorator = new ProcessingServiceParallelRunDecorator(processingService);
		List<Integer> objectIds = Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
		
		List<Integer> resultList = processingServiceDecorator.processObjects(objectIds);
		
		Assert.assertEquals(objectIds, resultList);
	}
	
}