package call;

import call.smarteralgorithm_base;

public class AI extends Thread{
	
	private Integer userID;
	private Integer gameID;
	private String token;
	
	String[][] map;
	
	private Boolean gameCurState = true; 
	
	
	public AI(Integer userID, Integer gameID, String token,String[][] map)
	{
		this.userID = userID;
		this.gameID = gameID;
		this.token = token;
		
		this.map = map;
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
		    	smarteralgorithm_base.operator(map, 20, 20);

	            
	            
	  
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
