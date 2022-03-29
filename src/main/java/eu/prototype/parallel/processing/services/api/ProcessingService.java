package eu.prototype.parallel.processing.services.api;

import java.util.List;

/**
 * Service interface defining the contract for object identifiers processing
 */
public interface ProcessingService {

	/**
	 * Processes the list of objects identified by id and returns a an identifiers
	 * list of the successfully processed objects
	 * 
	 * @param objectIds List of object identifiers
	 * 
	 * @return identifiers list of the successfully processed objects
	 */
	List<Integer> processObjects(List<Integer> objectIds);
}
