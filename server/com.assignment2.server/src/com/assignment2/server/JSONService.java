package com.assignment2.server;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Timestamp;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.codehaus.jettison.json.JSONObject;

// Keep track of all the JSONServices.
@Path("/jsonservices")
public class JSONService {

	@GET
	@Path("/print/{id}")					// 1 for bootstrap
	@Produces(MediaType.APPLICATION_JSON)
	public String produceJSON( @PathParam("id") String id ) throws Exception {
		String details = MongoService.getResourceDetails(id);
		JSONObject n = new JSONObject(details);						//to JSON
		return n.toString();		
	}
	
	@GET
	@Path("/readLastFiveValues/{id}")					// to get the last 5 values of temperature, voltage etc
	@Produces(MediaType.TEXT_PLAIN)
	public String readFiveValues( @PathParam("id") String id ) throws Exception {
		String details = MongoService.getLastFiveValues(id);
		return details;	
	}

	@POST
	@Path("/register/registerDevice")					// 2 for registration
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerDevice( String id ) throws Exception {
		int find = id.lastIndexOf("}");
		int find2=id.lastIndexOf(",");
		String serialNumber = id.substring(find+1, find2);
		String clientURI=id.substring(find2+1,id.length());
		String sub = id.substring(0, find+1);
		String tem = MongoService.postValues(sub, serialNumber, clientURI);
		return Response.ok(tem).build();
	}

	@PUT
	@Path("/register/updateDevice/lifetime")					// 2 for Updating lifetime attribute
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateLifetime(  String id ) throws Exception {
		int find = id.lastIndexOf("}");
		String newLifetime = id.substring(find+1, id.length());
		String sub = id.substring(0, find+1);
		String tem = MongoService.updateLifetime(sub, newLifetime);
		return Response.ok(tem).build();
	}


	@PUT
	@Path("/register/updateDevice/bindingMode")					// 2 for Updating bindingMode attribute
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateBindingMode(  String id ) throws Exception {
		int find = id.lastIndexOf("}");
		String newBinding = id.substring(find+1, id.length());
		String sub = id.substring(0, find+1);
		String tem = MongoService.updateBindingMode(sub, newBinding);
		return Response.ok(tem).build();
	}


	@DELETE
	@Path("/register/delete/{id}")		
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	public Response deleteItem(@PathParam("id") String id) throws Exception {
		String tem = MongoService.deleteValue(id);
		return Response.ok(tem).build();
	}

	@GET
	@Path("/notify/{id}")					// 1 for bootstrap
	@Produces(MediaType.APPLICATION_JSON)
	public Response notify( @PathParam("id") String id ) throws Exception {	
		java.util.Date date= new java.util.Date();
		System.out.println("Notifying from Client to the Server: "+new Timestamp(date.getTime())+" > "+"Temperature : "+id);
		Thread.sleep(1000);		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String ch = br.readLine();		
		return Response.ok(ch).build();
	}
	
	
	@POST
	@Path("/registerDevice")					// for UI registeration
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public String registerDeviceUI(   @FormParam("endPoint") String endpoint,
										@FormParam("serialNumber") String serial) throws Exception {
		MongoService.registerDeviceUI(endpoint, serial);				
		return "Registered successfuully"+endpoint + "  "+serial;	
	}
	
	@GET
	@Path("/discover")					// discover
	@Produces(MediaType.APPLICATION_JSON)
	public String discover() throws Exception {
		String dis = MongoService.discover();
//		return Response.ok(dis).build();
		return dis;
		// ^ BOTHH WORKS !!!
	}
	
	@POST
	@Path("/write")					// write
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public void write(@FormParam("deviceType") String deviceType,
						@FormParam("attribute") String attribute,
						@FormParam("newValue") String newValue) throws Exception {
		MongoService.write(deviceType, attribute, newValue);
		
	}
	
	@POST
	@Path("/writeAttribute")					// write attribute
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public String writeAttribute( @FormParam("write") String write,
			@FormParam("what") String what) throws Exception {
		String val = MongoService.writeAttribute(write, what);
		return val;
	}
	
//	@GET
//	@Path("/execute")					// execute
//	@Produces(MediaType.APPLICATION_JSON)
//	public String execute() throws Exception {
//		String exe = MongoService.execute();
//		return exe;
//	}
	
	@GET
	@Path("/read/{combinedString}") 	 // read choice can be any attribute in the clientfridge, clientThermostat etc
	@Produces(MediaType.APPLICATION_JSON)
	public Response readAttribute (@PathParam("combinedString") String combinedString )throws Exception{		
		String details = MongoService.read(combinedString);
		return Response.ok(details).build();
	}
	
	@GET
	@Path("/createObject")	
	@Produces(MediaType.APPLICATION_JSON)
	public Response createObject ()throws Exception{	
	MongoService.createObject();;
	return Response.ok("Object Created !!").build();
	}
	
	@GET
	@Path("/deleteObject")	
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteObject ()throws Exception{	
	MongoService.deleteObject();;
	return Response.ok("Object Deleted !!").build();
	}
}
