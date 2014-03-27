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
