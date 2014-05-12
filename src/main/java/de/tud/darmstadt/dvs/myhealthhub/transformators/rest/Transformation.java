/* 
 * Copyright (C) 2014 TU Darmstadt, Hessen, Germany.
 * Department of Computer Science Databases and Distributed Systems
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */ 
 
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