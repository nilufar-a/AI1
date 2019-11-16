package org.json;
 
 
public class JsonConvert {
	public String Demo(String input){
		JSONObject properties = new JSONObject(input);
		String userID =properties.getString("userID");
	    String gameID =properties.getString("gameID");
	    String token =properties.getString("token");
	    System.out.println("userID is："+userID);
	    System.out.println("gameID is："+gameID);
	    System.out.println("token is："+token);
	    //To do 
	    //for pass paramaters to functions.
	    if(userID!=null&&gameID!=null&&token!=null)
	    	return "200 OK";
	    else return "400 BAD REQUEST";
	    
	}
}