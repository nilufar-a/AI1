package call;

import com.example.AllPointsOnMap;

import call.smarteralgorithm_base;

public class AI extends Thread{
	
	private Integer userID;
	private Integer gameID;
	private String token;
	
	AllPointsOnMap[][] MAP_MAIN;
	
	private Boolean gameCurState = true; 
	
	
	public AI(Integer userID, Integer gameID, String token,AllPointsOnMap[][] MAP_MAIN)
	{
		this.userID = userID;
		this.gameID = gameID;
		this.token = token;
		
		this.MAP_MAIN = MAP_MAIN;
	}
	
	
	public String getInfo()
	{
		return "User ID: " + userID + ", " + "Game ID: " + gameID 
				+ ", Token:" + token;
	}
	
	@Override
	public void run()
	{
		// Wu's algorithm
		while(gameCurState == true)
		{
			try
	        { 
	            // Displaying the thread that is running 
		    	smarteralgorithm_base.operator(MAP_MAIN, 20, 20);

	            
	            
	  
	        } 
	        catch (Exception e) 
	        { 
	            // Throwing an exception 
	            System.out.println ("Exception is caught"); 
	        } 
		}
		
	}

	
	public void unregister()
	{
		// Unregisters the AI from game and deletes it from thread
	}


}
