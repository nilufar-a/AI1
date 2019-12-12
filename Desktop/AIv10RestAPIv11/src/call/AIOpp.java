package call;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.inject.Singleton;

import com.example.AllPointsOnMap;
import com.example.Example;
import com.google.gson.Gson;

public class AIOpp extends Thread{
	
	private String userID;
	private int gameID;
	private String token;
	
	
	//int i = 0;
	
	int NumOfPlayer = 0;
	
	static AllPointsOnMap[][] Map;
	
	 int Player_x;
	 int Player_y;
	 int predirection;
	 Boolean isAlive;
	 String GLOBAL_DIRECTION;
	 
	
	public AIOpp(String userID, Integer gameID, String token)
	{
		
		this.userID = userID;
		this.gameID = gameID;
		this.token = token;
	    this.isAlive = true;
	    SET_MAP();
	}
	
	

	public  void runAI(int weight, int height) {
		// TODO Auto-generated method stub

		   SET_MAP(); //------------------------------SETS THE MAP WITH ITS POINT STATES FROM GATEWAY API------------------------------
		   
		   //----------------------------GETTING CURRENT STATE OF THE GAME------------------------------------
		   URL gameEngineURL = null;
		   int gameEngineURLResponse = 0;
		   Boolean gameFinished = false;
		   HttpURLConnection gameEngineConnection = null;
		   BufferedReader gameEngineJson = null;
		   Example gej = new Example();
		try {
		    	gameEngineURL = new URL("https://api-gateway-dot-trainingprojectlab2019.appspot.com//GetCurrentStateOfModel?GameID=" + gameID);
				gameEngineConnection = (HttpURLConnection) gameEngineURL.openConnection();
				gameEngineConnection.setRequestMethod("GET");
				gameEngineConnection.connect();
			
	            gameEngineURLResponse = 0;
				gameEngineURLResponse = gameEngineConnection.getResponseCode();
			
	        if (gameEngineURLResponse != 200) {
	            throw new RuntimeException("HTTPResponseCode:" + gameEngineURLResponse);}
	        
				 gameEngineJson = new BufferedReader(new InputStreamReader(gameEngineConnection.getInputStream()));

	        //---------------------------END OF GETTING CURRENT STATE--------------------------------
		
	     	// NOTE: NUMBER OF PLAYERS IN THE MAP INCREASES IF POST FUNCTION IS CALLED
			SetPlayer(weight, height); // Sets random start point for every player
			
			
			//----------------------GET GAME STATE FROM GAME ENGINE------------------------
	        gej = new Gson().fromJson(gameEngineJson ,Example.class);
	        gameFinished = gej.getGameFinished();
		}catch(Exception e)
		{
			System.out.println("RESPONCE IS NOT 200");
		}
	
	     	
		
			Random random = new Random();
			predirection = random.nextInt(4) + 1;
		
		int flag = 0; // flag of size # of players
		while(gameFinished != true && isAlive == true && gameEngineURLResponse == 200) {
			
				
				if(flag == -1)
				{
					continue;
				}
				//flag = startallplayer(weight, height, i);
				flag = StartPlayer(weight, height, userID); // CONVERT THIS LOGIC INTO THE THREAD . FLAG IDENTIFIES WHETHER PLAYER IS DEAD OR NOT AND BOT DIRECTION
				
			
			// PRINTING OUT THE MATRIX ( MAP ) 
			for(int i = 0; i < height; i++) {
			System.out.println("");
			for(int j = 0; j < weight; j++) {
				System.out.print(Map[i][j].getState().toString());
			}
		}
			
			
			// IF PLAYER IS DEAD ( IDENTIFIED BY FLAG ) THEN INCREASE THE COUNTER.
			int count = 0;
				if(flag == -1)
					count++;
			
			// WHEN THE COUNTER IS EQUAL TO # OF PLAYER , THEN GAME STOPS
			if(count == 1)
			{
				try {
					UnregisterBot();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("UNREGISTERIN BOT ERROR IS " + e);
				}
				System.out.println("USER: " + userID + " IS UNREGISTERED!");
				isAlive = false;
				
				break;
			}
			
			// POST MOVE
						Integer responceCode = POST_MOVE(GLOBAL_DIRECTION, this.userID,false);
						if(responceCode != 200) // If responce is not successfull break the loop , delete the thread
						{
							System.out.println("POST MOVE RESPONCE: " + responceCode);
						}
				
			//---------------Taking to account calculation time ( taking system time after i get game state and i get system time (timeSinceEpoc)) PROCESS JSON< AI MAGIC STUFF, POSTING MOVE = time
			// This time - previously mesasured time and use function which give time in ml second in EPOC 
			//------------------------- WAIT TimeToUpdate millisecs for the next turn ( loop iteration )----------------------
			try {
				Thread.sleep((new Double(gej.getTimeToUpdate())).longValue()); // Substract This time
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			// 
			
			
			
			// Updating getCurrentStateResponce
			try {
				gameEngineURLResponse = gameEngineConnection.getResponseCode();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("RESPONCE IS NOT 200");
				e1.printStackTrace();
			}
			
			// Updating gameFinished value
			//----------------------GET GAME STATE FROM GAME ENGINE------------------------
	        gej = new Gson().fromJson(gameEngineJson ,Example.class);
	        gameFinished = gej.getGameFinished();
			
			
			SET_MAP();
		}
		
	}
	
	public Integer POST_MOVE(String Direction, String UserID, Boolean TurboFlag)
	{
		String urlParameters  = "Direction=" + Direction + "&UserID=" + UserID + "&TurboFlag=" + TurboFlag;
		byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		String request        = "https://api-gateway-dot-trainingprojectlab2019.appspot.com//PostMove?" + urlParameters;
		URL url = null;
		try {
			url = new URL( request );
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}           
		conn.setDoOutput( true );
		conn.setInstanceFollowRedirects( false );
		try {
			conn.setRequestMethod( "POST" );
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
		conn.setRequestProperty( "charset", "utf-8");
		conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
		conn.setUseCaches( false );
		try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
		   wr.write( postData );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int responceCode = 0;
		
		try {
			responceCode = conn.getResponseCode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return responceCode;
	}

	public  int StartPlayer(int weight, int height, String WhichOfPlayer) {
		// TODO Auto-generated method stub
		
		int Direction = predirection;
		
		int[] currentlocation = new int[2];
		currentlocation[0] = Player_x;
		currentlocation[1] = Player_y;
		
		Direction = smarteralgorithm.operator(Map, weight, height, Direction, currentlocation);
		predirection = Direction;
		
		if(Direction == 1) { //-------------------------DOWN---------------------------------
			Player_y--;
//			Map[Player_x[WhichOfPlayer]][Player_y[WhichOfPlayer]] = "*";
			Map[Player_x][Player_y].setState(AllPointsOnMap.State.TRACER);
			GLOBAL_DIRECTION = "down";
		}
		else if(Direction == 2) { //-------------------------LEFT---------------------------------
			Player_x--;
//			Map[Player_x[WhichOfPlayer]][Player_y[WhichOfPlayer]] = "*";
			Map[Player_x][Player_y].setState(AllPointsOnMap.State.TRACER);
			GLOBAL_DIRECTION = "left";
		}
		else if(Direction == 3) { //-------------------------UP---------------------------------
			Player_y++;
//			Map[Player_x[WhichOfPlayer]][Player_y[WhichOfPlayer]] = "*";
			Map[Player_x][Player_y].setState(AllPointsOnMap.State.TRACER);
			GLOBAL_DIRECTION = "up";
		}
		else if(Direction == 4) { //-------------------------RIGHT---------------------------------
			Player_x++;
//			Map[Player_x[WhichOfPlayer]][Player_y[WhichOfPlayer]] = "*";
			Map[Player_x][Player_y].setState(AllPointsOnMap.State.TRACER);
			GLOBAL_DIRECTION = "right";
		}
		else if(Direction == -1) {
			return -1;			
		}
		return 1;
	}
	

	private  void SetPlayer(int weight, int height) {
		// TODO Auto-generated method stub
		while(true) {
			Random random = new Random();
			int current_x = random.nextInt(height); // Y of matrix
			int current_y = random.nextInt(weight); // X of matrix
			if(Map[current_x][current_y].getState() == AllPointsOnMap.State.EMPTY) {
				
				Map[current_x][current_y].setState(AllPointsOnMap.State.TRACER);
				Player_x = current_x; // Sets X value of player num
				Player_y = current_y; // Sets Y value of player num			
				break;
			}
		}
	}
	
	
	//---------------------------UNREGISTERING BOT------------------------------------
	public void UnregisterBot() throws IOException
	{
		URL url = new URL("https://api-gateway-dot-trainingprojectlab2019.appspot.com/deleteuser?token=" + token);
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestProperty(
		    "Content-Type", "application/x-www-form-urlencoded" );
		httpCon.setRequestMethod("DELETE");
		httpCon.connect();
	}
	
	
	
	//---------------------------SETTING MAP------------------------------------
	public void SET_MAP()
	{
		 //----------------------GET ALL POINTS FROM GAME ENGINE AND BUILDING MATRIX BASED ON THIS------------------------
		URL gameEngineURL = null;
		try {
			gameEngineURL = new URL("https://api-gateway-dot-trainingprojectlab2019.appspot.com/GetCurrentStateOfModel?GameID=" + this.gameID);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpURLConnection gameEngineConnection = null;
		try {
			gameEngineConnection = (HttpURLConnection) gameEngineURL.openConnection();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			gameEngineConnection.setRequestMethod("GET");
		} catch (ProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			gameEngineConnection.connect();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
        int gameEngineURLResponse = 0;
		try {
			gameEngineURLResponse = gameEngineConnection.getResponseCode();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
        if (gameEngineURLResponse != 200) {
          System.out.println("URL RESPONSE: " + gameEngineURLResponse);
        }else
        {
          
          try
          {
        	  BufferedReader gameEngineJson = new BufferedReader(new InputStreamReader(gameEngineConnection.getInputStream()));
              
              // FILLING THE MAP WITH POINTS
      	      Example gej = new Example();
              gej = new Gson().fromJson(gameEngineJson ,Example.class);
              
           	ArrayList<AllPointsOnMap> mapPoint = (ArrayList<AllPointsOnMap>) gej.getMap().getAllPointsOnMap(); // GETTING LIST OF POINTS
           	Map = new AllPointsOnMap[gej.getMap().getWidth()][gej.getMap().getHeight()]; // SETTING UP THE MATRIX SIZE
              for(int i = 0; i < mapPoint.size(); i++) // ASSIGNING STATE TO EACH ENTRY
              {
            	  Map[mapPoint.get(i).getX()][mapPoint.get(i).getY()].setState(mapPoint.get(i).getState());
              }
          }catch(Exception e)
          {
        	  System.out.println("ERROR: " + e);
          }  
	}
}

	
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		runAI(Map.length,Map[0].length);

	}
}
