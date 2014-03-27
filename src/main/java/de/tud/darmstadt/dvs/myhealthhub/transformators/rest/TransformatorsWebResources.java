package de.tud.darmstadt.dvs.myhealthhub.transformators.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.http.HttpStatus;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/transformators")
public class TransformatorsWebResources {

	// for JSON communication
	public final static String JSON_TRANSFORMATION_URI = "uri";
	public final static String JSON_TRANSFORMATION_BYTE_CODE = "transformationByteCode";
	public final static String JSON_REQUESTED_EVENT_TYPE = "requestedEventType";
	public final static String JSON_AVAILABLE_EVENT_TYPES = "availableEventTypes";
	public final static String JSON_REQUIRED_EVENT_TYPES = "requiredEventTypes";
	public final static String JSON_TRANSFORMATION_COSTS = "transformationCosts";
	
	Logger logger = LoggerFactory.getLogger(TransformatorsWebResources.class);

	private ITransformationRepository transformatorsService = new TransformationRepository();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/query")
	public Response getTransformator(@QueryParam("jsonString") String jsonStringRequest) {
		
		logger.info("Transformator of type: "+ jsonStringRequest + " has been requested");
		
		JSONObject jsonObject = new JSONObject();

		
		try {			
			JSONObject jsObject = new JSONObject(jsonStringRequest);
		
			String[] availableEventTypes = toArray(new JSONArray(jsObject.getString(JSON_AVAILABLE_EVENT_TYPES)));
			String requestedEventType = jsObject.getString(JSON_REQUESTED_EVENT_TYPE);
			
			Transformation transformation = transformatorsService
					.getB64EcodedTransformation(availableEventTypes, requestedEventType);

			
			if (transformation != null) {
				jsonObject.put(JSON_TRANSFORMATION_URI,
						transformation.getURI());
				jsonObject.put(JSON_REQUIRED_EVENT_TYPES,
						new JSONArray(arrayToList(transformation.getRequiredEventTypes())));
				//TODO add costs
				jsonObject.put(JSON_REQUESTED_EVENT_TYPE, requestedEventType);
				jsonObject.put(JSON_TRANSFORMATION_COSTS, "10");
				jsonObject.put(JSON_TRANSFORMATION_BYTE_CODE,
						transformation.getB64EncodedTransformator());
				
				logger.info(jsonObject.toString());
				
				return Response.status(HttpStatus.SC_OK).entity(jsonObject.toString()).build(); 
			} else {
				logger.info("The requested transformator cannot be found");
				//jsonObject.put("status", Constants.failed_status);
				return Response.status(HttpStatus.SC_NO_CONTENT).build();
			}
		} catch (JSONException ex) {

			ex.printStackTrace();
		}
		return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).build();
	}
	
	private String[] toArray(JSONArray jsonArray){
		
		String[] list = new String[jsonArray.length()];
		
		if (jsonArray != null) { 
		   int len = jsonArray.length();
		   for (int i=0;i<len;i++){ 
		    try {
				list[i] = jsonArray.get(i).toString();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		   } 
		} 
		return list;
	}
	
	private List<String> arrayToList(String[] advertisedEvents) {
		List<String> names = new ArrayList<String>();
		
		for(String eventName : advertisedEvents){
			//names.add(getShortEventType(eventName));
			names.add(eventName);
		}
		return names;
	}

}
