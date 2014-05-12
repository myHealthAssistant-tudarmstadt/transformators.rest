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

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultTransformatorsLacator implements ITransformatorsLocator {

	private Logger logger = LoggerFactory.getLogger(DefaultTransformatorsLacator.class);
	
	private Properties properties = new Properties();
	
	public DefaultTransformatorsLacator(){
		
		
		try {
			properties.load(Thread.currentThread().getContextClassLoader().
					getResourceAsStream(Constants.transformators_Properties_file_name));
			logger.info("properties " + properties);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	@Override
	public String getTransformatorPath(String transformatorKeyName) {
		
		logger.info("transformator property key " + transformatorKeyName);
		String pathToTransformatorPath = properties.getProperty(transformatorKeyName);
				
		logger.info("transformator path key value " + pathToTransformatorPath );
		
		return pathToTransformatorPath;
	}

}