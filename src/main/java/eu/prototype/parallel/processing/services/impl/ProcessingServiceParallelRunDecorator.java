package eu.prototype.parallel.processing.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import eu.prototype.parallel.processing.services.api.ProcessingService;

/**
 * Service implementation for parallel chunk processing
 */
@Service
@Primary
@ConditionalOnProperty(prefix = "service", name = "parallel", havingValue = "true")
public class ProcessingServiceParallelRunDecorator implements ProcessingService {

	ProcessingService delegate;
	
	public ProcessingServiceParallelRunDecorator(ProcessingService delegate) {
		this.delegate = delegate;
	}

	/**
	 * In a real scenario it should be an external configuration
	 */
	private int batchSize = 10;

	@Override
	public List<Integer> processObjects(List<Integer> objectIds) {
		List<List<Integer>> chuncks = getBatches(objectIds, batchSize);
		List<List<Integer>> processedObjectIds = chuncks.parallelStream().map(delegate::processObjects)
				.collect(Collectors.toList());

		return flatList(processedObjectIds);
	}

	public static <T> List<List<T>> getBatches(List<T> collection, int batchSize) {
		return IntStream.iterate(0, i -> i < collection.size(), i -> i + batchSize)
				.mapToObj(i -> collection.subList(i, Math.min(i + batchSize, collection.size())))
				.collect(Collectors.toList());
	}

	private <T> List<T> flatList(List<List<T>> listOfLists) {
		return listOfLists.stream().collect(ArrayList::new, List::addAll, List::addAll);
	}

}
