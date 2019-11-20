package call;

public class AI extends Thread{
	
	private String userID;
	private String gameID;
	private String token;
	
	
	public AI(String userID, String gameID, String token)
	{
		this.userID = userID;
		this.gameID = gameID;
		this.token = token;
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
		try
        { 
            // Displaying the thread that is running 
            System.out.println ("Thread " + 
                  Thread.currentThread().getId() + 
                  " is running, BOT_ID: " + userID + ", GAME_ID: " + gameID 
                  + ", token: " + token); 
  
        } 
        catch (Exception e) 
        { 
            // Throwing an exception 
            System.out.println ("Exception is caught"); 
        } 
	}

	
	public void unregister()
	{
		// Unregisters the AI from game and deletes it from thread
	}


}
