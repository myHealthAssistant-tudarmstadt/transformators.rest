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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudarmstadt.dvs.myhealthassistant.myhealthhub.events.notifications.NotificationEvent;
import de.tudarmstadt.dvs.myhealthassistant.myhealthhub.events.sensorreadings.SensorReadingEvent;
import de.tudarmstadt.dvs.myhealthassistant.myhealthhub.events.sensorreadings.cardiovascular.BloodPressureEvent;
import de.tudarmstadt.dvs.myhealthassistant.myhealthhub.events.sensorreadings.cardiovascular.ECGEvent;
import de.tudarmstadt.dvs.myhealthassistant.myhealthhub.events.sensorreadings.cardiovascular.HRFidelityEvent;
import de.tudarmstadt.dvs.myhealthassistant.myhealthhub.events.sensorreadings.cardiovascular.HeartRateEvent;
import de.tudarmstadt.dvs.myhealthassistant.myhealthhub.events.sensorreadings.environmental.raw.TemperatureEvent;
import de.tudarmstadt.dvs.myhealthassistant.myhealthhub.events.sensorreadings.physical.BodyTemperatureEventInCelsius;
import de.tudarmstadt.dvs.myhealthassistant.myhealthhub.events.sensorreadings.physical.BodyTemperatureEventInFahrenheit;
import de.tudarmstadt.dvs.myhealthassistant.myhealthhub.events.sensorreadings.physical.WeightEventInKg;
import de.tudarmstadt.dvs.myhealthassistant.myhealthhub.events.sensorreadings.physical.WeightEventInLbs;
import de.tudarmstadt.dvs.myhealthassistant.myhealthhub.events.sensorreadings.physical.activity.ActivityEventReha;

public class TransformationRepository implements ITransformationRepository {

	private Logger logger = LoggerFactory
			.getLogger(TransformationRepository.class);
	
	private ArrayList<StoredTransformation> transformations;
	
	public TransformationRepository() {
		transformations = new ArrayList<StoredTransformation>();
		
		String filePath = "C:\\Temp\\transformators.rest\\transformations\\";
		logger.info(filePath);
		
		transformations.add(new StoredTransformation(
				"myhealthassistant.transformation.BPtoHR", 
				new String[] {BloodPressureEvent.EVENT_TYPE}, 
				HeartRateEvent.EVENT_TYPE, 
				filePath+ "BPtoHR.jar"));
		
		transformations.add(new StoredTransformation(
				"myhealthassistant.transformation.BPandHRtoWeight", 
				new String[] {WeightEventInKg.EVENT_TYPE}, 
				WeightEventInLbs.EVENT_TYPE, 
				filePath + "WeightKgToLbs.jar"));
		
		transformations.add(new StoredTransformation(
				"myhealthassistant.transformation.ECGtoHR", 
				new String[] {ECGEvent.EVENT_TYPE}, 
				HeartRateEvent.EVENT_TYPE, 
				filePath + "ECGtoHR.jar"));
		
		/**
		 * neu hinzugefügt		
		 */
		transformations.add(new StoredTransformation(
				"myhealthassistant.transformation.CelsiusToFahrenheit", 
				new String[] {BodyTemperatureEventInCelsius.EVENT_TYPE}, 
				BodyTemperatureEventInFahrenheit.EVENT_TYPE, 
				filePath + "CelsiusToFahrenheit.jar"));
		
		transformations.add(new StoredTransformation(
				"myhealthassistant.transformation.FahrenheitToCelsius", 
				new String[] {BodyTemperatureEventInFahrenheit.EVENT_TYPE}, 
				BodyTemperatureEventInCelsius.EVENT_TYPE, 
				filePath + "FahrenheitToCelsius.jar"));
		
		transformations.add(new StoredTransformation(
				"myhealthassistant.transformation.WeightLbsToKg", 
				new String[] {WeightEventInLbs.EVENT_TYPE}, 
				WeightEventInKg.EVENT_TYPE, 
				filePath + "WeightLbsToKg.jar"));
		
		transformations.add(new StoredTransformation(
				"myhealthassistant.transformation.HrToHrFidelity", 
				new String[] {HeartRateEvent.EVENT_TYPE}, 
				HRFidelityEvent.EVENT_TYPE, 
				filePath + "HrToHrFidelity.jar"));
		
		transformations.add(new StoredTransformation(
				"myhealthassistant.transformation.HrToHrAlarm", 
				new String[] {HeartRateEvent.EVENT_TYPE}, 
				NotificationEvent.EVENT_TYPE, 
				filePath + "HrToHrAlarm.jar"));
		
		transformations.add(new StoredTransformation(
				"myhealthassistant.transformation.AccLegRecognition",
				new String[] {SensorReadingEvent.ACCELEROMETER_ANKLE},
				SensorReadingEvent.ACTIVITY_REHA, 
				filePath + "AccLegRecognition.jar"));
	}

	
	@Override
	public Transformation getB64EcodedTransformation(String[] availableEventTypes, String requestedEventType) {
		logger.info("Transformation for event type '" + getShortEventType(requestedEventType) + "' requested.");
		// create a list from availableEventType array
		ArrayList<String> availableEvents = new ArrayList<String>();
		for(int i = 0; i < availableEventTypes.length; i++) {
			availableEvents.add(availableEventTypes[i]);
		}
		
		boolean requiredEventsAvailable = true;
		for(StoredTransformation transformation : transformations) {
			// If the transformation provides the requested event type
			if(transformation.getProvidedEventType().equals(requestedEventType)) {
				// Check whether the required event types are availabe
				String[] requiredEventTypes = transformation.getRequiredEventTypes();
				for(int i = 0; i<requiredEventTypes.length; i++) {
					// stop loop if required event type is not available
					if(!availableEvents.contains(requiredEventTypes[i])) {
						requiredEventsAvailable = false;
						break;
					}						
				}
				
				if(requiredEventsAvailable) {
					// return transformation
					Transformation trans = new Transformation(transformation.getURI());
					trans.setProvidedEventType(transformation.getProvidedEventType());
					trans.setRequiredEventTypes(transformation.getRequiredEventTypes());
					trans.setB64EncodedTransformator(getBase64(transformation.getStorageLocation()));
					return trans;
				} else {
					requiredEventsAvailable = true;
				}
			}
		}		
		
		return null;
	}

	private String getBase64(String path) {

		if (path != null) {

			FileInputStream fileInputStream = null;
			try {

				File file = new File(path);
				fileInputStream = new FileInputStream(file);
				byte[] bytes = new byte[(int) file.length()];
				fileInputStream.read(bytes);
				String encodedFile = DatatypeConverter.printBase64Binary(bytes);

				return encodedFile;

			} catch (IOException ex) {

				ex.printStackTrace();
			} finally {
				try {
					fileInputStream.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Returns only the last part after the "." of an event type. 
	 * @return Short event type
	 */
    public String getShortEventType(String eventType) {
    	if(eventType.contains(".")) {
    		return eventType.substring(eventType.lastIndexOf(".")+1, eventType.length());
    	} else {
    		return eventType;
    	}
    	
    }

	
}