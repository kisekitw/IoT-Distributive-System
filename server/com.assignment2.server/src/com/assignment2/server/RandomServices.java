package com.assignment2.server;

import java.sql.Timestamp;
import java.util.Random;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class RandomServices {
	public static void randomFridge(String end, String serial) throws Exception
	{
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(20);//temp
		Random randomGenerator2 = new Random();
		int randomInt2 = randomGenerator2.nextInt(80);//pressure
		int randomInt3 = randomGenerator.nextInt(35); // voltage
		MongoClientURI mongoLab = new MongoClientURI("mongodb://admin:admin@ds047722.mongolab.com:47722/cmpe273");
		MongoClient client;
		client = new MongoClient(mongoLab);
		java.util.Date date= new java.util.Date();
		
		BasicDBObject document = new BasicDBObject();
		document.put("endPoint",end );
		document.put("serialNo", serial);
		document.put("Temperature",randomInt );
		document.put("Pressure",randomInt2 );
		document.put("Voltage",randomInt3 );
		document.put("Mode","default");
		document.put("Timestamp",new Timestamp(date.getTime()) );

		//String temp="{\"Object\":\"fridge\",\"timeStamp\":\""+new Timestamp(date.getTime())+"\",\"Resource\":{\"serial\":\"7891\",\"Temprature\":"+randomInt+",\"Pressure\":"+randomInt2+"}}";
		DB database = client.getDB("cmpe273");
		DBCollection collection = database.getCollection("ClientFridge"); 
		//	   DBObject dbObject = (DBObject)JSON.parse(temp);
		collection.insert(document);
	}
	
	public static void randomAC(String end, String serial) throws Exception
	{
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(20);//temp
		Random randomGenerator2 = new Random();
		int randomInt2 = randomGenerator2.nextInt(80);//pressure
		int randomInt3 = randomGenerator.nextInt(35); // voltage
		
		MongoClientURI mongoLab = new MongoClientURI("mongodb://admin:admin@ds047722.mongolab.com:47722/cmpe273");
		MongoClient client;
		client = new MongoClient(mongoLab);
		java.util.Date date= new java.util.Date();
		
		BasicDBObject document = new BasicDBObject();
		document.put("endPoint",end );
		document.put("serialNo", serial);
		document.put("Temperature",randomInt+1 );
		document.put("Pressure",randomInt2+1 );
		document.put("Voltage",randomInt3+1 );

		document.put("Mode","default");
		document.put("Timestamp",new Timestamp(date.getTime()) );

		//String temp="{\"Object\":\"fridge\",\"timeStamp\":\""+new Timestamp(date.getTime())+"\",\"Resource\":{\"serial\":\"7891\",\"Temprature\":"+randomInt+",\"Pressure\":"+randomInt2+"}}";
		DB database = client.getDB("cmpe273");
		DBCollection collection = database.getCollection("ClientAC"); 
		//	   DBObject dbObject = (DBObject)JSON.parse(temp);
		collection.insert(document);
	}
	
	public static void randomThermostat(String end, String serial) throws Exception
	{
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(20);//temp
		Random randomGenerator2 = new Random();
		int randomInt2 = randomGenerator2.nextInt(80);//pressure
		int randomInt3 = randomGenerator.nextInt(35); // voltage
		MongoClientURI mongoLab = new MongoClientURI("mongodb://admin:admin@ds047722.mongolab.com:47722/cmpe273");
		MongoClient client;
		client = new MongoClient(mongoLab);
		java.util.Date date= new java.util.Date();
		
		BasicDBObject document = new BasicDBObject();
		document.put("endPoint",end );
		document.put("serialNo", serial);
		document.put("Temperature",randomInt+1 );
		document.put("Pressure",randomInt2+1 );
		document.put("Voltage",randomInt3+1 );
		document.put("Mode","default");
		document.put("Timestamp",new Timestamp(date.getTime()) );

		//String temp="{\"Object\":\"fridge\",\"timeStamp\":\""+new Timestamp(date.getTime())+"\",\"Resource\":{\"serial\":\"7891\",\"Temprature\":"+randomInt+",\"Pressure\":"+randomInt2+"}}";
		DB database = client.getDB("cmpe273");
		DBCollection collection = database.getCollection("ClientThermostat"); 
		//	   DBObject dbObject = (DBObject)JSON.parse(temp);
		collection.insert(document);
	}
	
	// have to work on door lock code, needs to see what needs to be put up in door lock attributes
	public static void randomDoorLock(String end, String serial) throws Exception
	{
//		Random randomGenerator = new Random();
//		int randomInt = randomGenerator.nextInt(30);//temp
//		Random randomGenerator2 = new Random();
//		int randomInt2 = randomGenerator2.nextInt(115);//pressure
		MongoClientURI mongoLab = new MongoClientURI("mongodb://admin:admin@ds047722.mongolab.com:47722/cmpe273");
		MongoClient client;
		client = new MongoClient(mongoLab);
		java.util.Date date= new java.util.Date();
		
		BasicDBObject document = new BasicDBObject();
		document.put("endPoint",end );
		document.put("serialNo", serial);
		document.put("Status","closed");
		document.put("image","_images/door4.jpg");
//		document.put("Pressure",randomsInt2 );
		document.put("Mode","default");
		document.put("Timestamp",new Timestamp(date.getTime()) );

		//String temp="{\"Object\":\"fridge\",\"timeStamp\":\""+new Timestamp(date.getTime())+"\",\"Resource\":{\"serial\":\"7891\",\"Temprature\":"+randomInt+",\"Pressure\":"+randomInt2+"}}";
		DB database = client.getDB("cmpe273");
		DBCollection collection = database.getCollection("ClientDoorLock"); 
		//	   DBObject dbObject = (DBObject)JSON.parse(temp);
		collection.insert(document);
	}
}
