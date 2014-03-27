package de.tud.darmstadt.dvs.myhealthhub.transformators.rest;

/**
 * @author Christian Seeger
 * 
 */
public class StoredTransformation {
	public String URI;
	public String[] requiredEventTypes;
	public String providedEventType;
	public String storageLocation;

	public StoredTransformation(String URI, String[] requiredEventTypes,
			String providedEventTypes, String storageLocation) {
		this.URI = URI;
		this.requiredEventTypes = requiredEventTypes;
		this.providedEventType = providedEventTypes;
		this.storageLocation = storageLocation;
	}

	/**
	 * @return the uRI
	 */
	public String getURI() {
		return URI;
	}

	/**
	 * @return the requiredEventTypes
	 */
	public String[] getRequiredEventTypes() {
		return requiredEventTypes;
	}

	/**
	 * @return the providedEventType
	 */
	public String getProvidedEventType() {
		return providedEventType;
	}

	/**
	 * @return the storageLocation
	 */
	public String getStorageLocation() {
		return storageLocation;
	}

}
