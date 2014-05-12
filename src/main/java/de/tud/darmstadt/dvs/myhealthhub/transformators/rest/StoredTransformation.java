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