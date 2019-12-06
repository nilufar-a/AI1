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
	
	private Boolean isDead = false;
	
	int NumOfPlayer = 0;
	
	// We send back gameID to the game engine and it always returns me back the same
	// map and points along with the game status
	static String[][] Map;
	
	
	static AllPointsOnMap[][] MAP_MAIN;
	
	
	 int Player_x;
	 int Player_y;
	 int predirection;
	
	public AIOpp(String userID, Integer gameID, String token,String[][] map) throws IOException
	{
		Map = new String[map.length][map[0].length];
		this.userID = userID;
		this.gameID = gameID;
		this.token = token;
		
		SetMap();
	
	}
	
	public void SetMap()
	{
		 //----------------------GET ALL POINTS FROM GAME ENGINE------------------------
		URL mapEDitorURL = null;
		try {
			mapEDitorURL = new URL("https://game-engine-devs-dot-trainingprojectlab2019.appspot.com/GetCurrentStateOfModel?GameID=" + gameID);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpURLConnection gameEngineConnection = null;
		try {
			gameEngineConnection = (HttpURLConnection) mapEDitorURL.openConnection();
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
          System.out.println(gameEngineURLResponse);
          
          try
          {
        	  BufferedReader gameEngineJson = new BufferedReader(new InputStreamReader(gameEngineConnection.getInputStream()));
              
              // FILLING THE MAP WITH POINTS
      		Example gej = new Example();
              gej = new Gson().fromJson(gameEngineJson ,Example.class);
           	ArrayList<AllPointsOnMap> mapPoint = (ArrayList<AllPointsOnMap>) gej.getMap().getAllPointsOnMap();
           	MAP_MAIN = new AllPointsOnMap[gej.getMap().getWidth()][gej.getMap().getHeight()];
              for(int i = 0; i < mapPoint.size(); i++)
              {
              	MAP_MAIN[mapPoint.get(i).getX()][mapPoint.get(i).getY()] = mapPoint.get(i);
              }
          }catch(Exception e)
          {
        	  System.out.println(e);
          }
        
        
        
	}
	}
	
	

	private  void runAI(int weight, int height) throws Exception {
		// TODO Auto-generated method stub
		String[][] map = new String[height][weight];
		Map = map;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < weight; j++) {
			Map[i][j] = ".";
			}
		}   
		
		URL mapEDitorURL = new URL("https://game-engine-devs-dot-trainingprojectlab2019.appspot.com/GetCurrentStateOfModel?GameID=" + gameID);
		HttpURLConnection gameEngineConnection = (HttpURLConnection) mapEDitorURL.openConnection();
		gameEngineConnection.setRequestMethod("GET");
		gameEngineConnection.connect();
        int gameEngineURLResponse = gameEngineConnection.getResponseCode();
        if (gameEngineURLResponse != 200) {
            throw new RuntimeException("HTTPResponseCode:" + gameEngineURLResponse);}
        BufferedReader gameEngineJson = new BufferedReader(new InputStreamReader(gameEngineConnection.getInputStream()));
        
     
		// NUMBER OF PLAYERS IN THE MAP INCREASES IF POST FUNCTION IS CALLED
			SetPlayer(weight, height); // Sets random start point for every player
	
		
			Random random = new Random();
			predirection = random.nextInt(4) + 1;
			

	       //----------------------GET GAME STATE FROM GAME ENGINE------------------------
			Example gej = new Example();
	        gej = new Gson().fromJson(gameEngineJson ,Example.class);
	     	Boolean gameFinished = gej.getGameFinished();
	     	
		int flag = 0; // flag of size # of players
		
		while(gameFinished != true && isDead != true) { 
			
			
			
				
				if(flag == -1)
				{
					continue;
				}
				
				//flag = startallplayer(weight, height, i);
				flag = StartPlayer(gej.getMap().getWidth(), gej.getMap().getHeight(), userID); // CONVERT THIS LOGIC INTO THE THREAD . FLAG IDENTIFIES WHETHER PLAYER IS DEAD OR NOT
				
			
			// PRINTING OUT THE MATRIX ( MAP ) 
			for(int i = 0; i < height; i++) {
			System.out.println("");
			for(int j = 0; j < weight; j++) {
				System.out.print(Map[i][j]);
			}
		}
			
			
			// IF PLAYER IS DEAD ( IDENTIFIED BY FLAG ) THEN INCREASE THE COUNTER.
			    int count = 0;
				if(flag == -1)
				{
					UnregisterBot(); // CALL USER AUTHENTICATION FOR UNREGISTERING THE BOT FROM THE SERVER
					System.out.println("THREAD IS DELETED FOR PLAYER " + userID);
					isDead = true;
					count++;
				}
					
			
			// WHEN THE COUNTER IS EQUAL TO # OF PLAYER , THEN GAME STOPS 
			if(count == 1)
			{
				break;
			}
				
			// WAIT 50 millisecs for the next turn ( loop iteration ) IT HAS TO BE TIME TO UPDATE
			try {
				Thread.sleep((new Double(gej.getTimeToUpdate())).longValue());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
			// Updating gameFinished value
			gameFinished = gej.getGameFinished();
			
		}
		
		// SET MAP
		SetMap();
		
	}
	
	
	
	public void UnregisterBot() throws IOException
	{
		URL url = new URL("https://userauth-dot-trainingprojectlab2019.appspot.com//deleteuser?token=" + token);
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestProperty(
		    "Content-Type", "application/x-www-form-urlencoded" );
		httpCon.setRequestMethod("DELETE");
		httpCon.connect();
	}

	public void PostMove(String direction, String UserID, boolean TurboFlag) throws IOException
	{
		String urlParameters  = "Direction=" + direction + "&" + "UserID=" + UserID + "&" + "TurboFlag=" + TurboFlag; 
		
		byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		String request        = "https://game-engine-devs-dot-trainingprojectlab2019.appspot.com//PostMove?" + urlParameters;
		URL    url            = new URL( request );
		HttpURLConnection conn= (HttpURLConnection) url.openConnection();           
		conn.setDoOutput( true );
		conn.setInstanceFollowRedirects( false );
		conn.setRequestMethod( "POST" );
		conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
		conn.setRequestProperty( "charset", "utf-8");
		conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
		conn.setUseCaches( false );
		try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
		   wr.write( postData );
		}

	}
	
	private  int StartPlayer(int weight, int height, String WhichOfPlayer) throws IOException {
		// TODO Auto-generated method stub
		
		int Direction = predirection;
		
		String DIRECTION_STATE = "";
		
		int[] currentlocation = new int[2];
		currentlocation[0] = Player_x;
		currentlocation[1] = Player_y;
		
		Direction = smarteralgorithm.operator(MAP_MAIN, weight, height, Direction, currentlocation);
		predirection = Direction;
		
		if(Direction == 1) { // down
			Player_y--;
			
			MAP_MAIN[Player_x][Player_y].setState(AllPointsOnMap.State.TRACER);
			DIRECTION_STATE = "down";
		}
		else if(Direction == 2) { // left
			Player_x--;
			MAP_MAIN[Player_x][Player_y].setState(AllPointsOnMap.State.TRACER);
			DIRECTION_STATE = "left";
		}
		else if(Direction == 3) { // up
			Player_y++;
			MAP_MAIN[Player_x][Player_y].setState(AllPointsOnMap.State.TRACER);
			DIRECTION_STATE = "up";
		}
		else if(Direction == 4) { // right
			Player_x++;
			MAP_MAIN[Player_x][Player_y].setState(AllPointsOnMap.State.TRACER);
			DIRECTION_STATE = "right";
		}
		else if(Direction == -1) {
			return -1;			
		}
		
		
		// CALLS POST MOVE REQUEST IN THE GAME_ENGINE
		PostMove(DIRECTION_STATE,userID,false);
		
		return 1;
	}
	
	

	private  void SetPlayer(int weight, int height) {
		// TODO Auto-generated method stub
		while(true) {
			Random random = new Random();
			int current_x = random.nextInt(height); // Y of matrix
			int current_y = random.nextInt(weight); // X of matrix
			if(Map[current_x][current_y] == ".") {
//				Map[current_x][current_y] = "*";
				// Map[current_x][current_y] = String.valueOf(i);
				Map[current_x][current_y] = String.valueOf(userID);
				Player_x = current_x; // Sets X value of player num
				Player_y = current_y; // Sets Y value of player num
				break;
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			runAI(Map.length,Map[0].length);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}