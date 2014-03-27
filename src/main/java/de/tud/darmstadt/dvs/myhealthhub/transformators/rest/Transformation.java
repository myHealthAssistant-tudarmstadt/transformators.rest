package de.tud.darmstadt.dvs.myhealthhub.transformators.rest;

/**
 * @author Christian Seeger
 *
 */
public class Transformation {
	public String b64EncodedTransformator;
	public String URI;
	public String[] requiredEventTypes;
	public String providedEventType;
	
	
	public Transformation(String URI) {
		this.URI = URI;
	}
	
	/**
	 * @return the b64EncodedTransformator
	 */
	public String getB64EncodedTransformator() {
		return b64EncodedTransformator;
	}
	/**
	 * @param b64EncodedTransformator the b64EncodedTransformator to set
	 */
	public void setB64EncodedTransformator(String b64EncodedTransformator) {
		this.b64EncodedTransformator = b64EncodedTransformator;
	}
	/**
	 * @return the uRI
	 */
	public String getURI() {
		return URI;
	}
	/**
	 * @param uRI the uRI to set
	 */
	public void setURI(String uRI) {
		URI = uRI;
	}
	/**
	 * @return the requiredEventTypes
	 */
	public String[] getRequiredEventTypes() {
		return requiredEventTypes;
	}
	/**
	 * @param requiredEventTypes the requiredEventTypes to set
	 */
	public void setRequiredEventTypes(String[] requiredEventTypes) {
		this.requiredEventTypes = requiredEventTypes;
	}
	/**
	 * @return the providedEventType
	 */
	public String getProvidedEventType() {
		return providedEventType;
	}
	/**
	 * @param providedEventType the providedEventType to set
	 */
	public void setProvidedEventType(String providedEventType) {
		this.providedEventType = providedEventType;
	}
	
}
