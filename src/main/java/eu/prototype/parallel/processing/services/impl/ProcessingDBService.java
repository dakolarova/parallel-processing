package eu.prototype.parallel.processing.services.impl;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.prototype.parallel.processing.services.api.ProcessingService;

/**
 * Service implementation for database related ids processing
 */
@Service("ProcessingDBService")
public class ProcessingDBService implements ProcessingService {
	
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Transactional
	@Override
	public List<Integer> processObjects(List<Integer> objectIds) {
		// Process and save to DB
		
		logger.info("Running in thread " + Thread.currentThread().getName() + " with object ids " + objectIds.toString());
		
		return objectIds.stream().collect(Collectors.toList());
	}

}
