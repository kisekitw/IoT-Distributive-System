package com.rest.client;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Random;

import org.codehaus.jettison.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class MyThreadRunnable implements Runnable  {

	@Override
	public void run() {
		try{
			int i =0;
			System.out.println("Check !!");					
			MongoClientURI mongoLab = new MongoClientURI("mongodb://admin:admin@ds031802.mongolab.com:31802/cmpe273");
			MongoClient client;
			client = new MongoClient(mongoLab);
			DB database = client.getDB("cmpe273");
			DBCollection collection = database.getCollection("ClientResourceFridge");
			DBObject maxx = collection.findOne();
			String max = (String)maxx.get("MaximumPeriod");	
			String min = (String)maxx.get("MinimumPeriod");	
			int convertmin =Integer.parseInt(min);
			int convertmax = Integer.parseInt(max);
			//Sleeping for the minimum period.
			Thread.sleep(convertmin*1000);
			String modeValue = (String)maxx.get("Mode");


			


			while(i < convertmax && modeValue.equalsIgnoreCase("observeMode"))
			{

				Random randomGenerator = new Random();
				int randomInt = randomGenerator.nextInt(30);				

				java.util.Date date= new java.util.Date();
				JSONObject partsData = new JSONObject(maxx.toString());

				DBCollection collectionClientFridge = database.getCollection("ClientFridge");
				String Serial = partsData.optString("serialNo");
				String endPoint= partsData.optString("endPoint");
				String firmware= partsData.optString("firmware");
				String manufacturer=partsData.optString("manufacturer");

				BasicDBObject document = new BasicDBObject();
				document.put("serialNo", Serial);
				document.put("endPoint",endPoint );
				//document.put("Mode",partsData.optString("Mode"));
				document.put("firmware",firmware );      
				document.put("manufacturer",manufacturer );
				document.put("Temperature",randomInt );
				document.put("Pressure",randomInt +2  );
				document.put("Timestamp",new Timestamp(date.getTime()) );
				collectionClientFridge.insert(document);
				Thread.sleep(1000);
				i++;
				maxx = collection.findOne();
				modeValue = (String)maxx.get("Mode");

			} 

			//  for setting back to default mode
				DBCollection collectionResource = database.getCollection("ClientResourceFridge");
				DBObject abc = collectionResource.findOne();	
				BasicDBObject query1 = (BasicDBObject)abc;	
				BasicDBObject doc1 = new BasicDBObject("$set" , new BasicDBObject().append("Mode", "Default"));
				collectionResource.update(query1, doc1);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}




}
