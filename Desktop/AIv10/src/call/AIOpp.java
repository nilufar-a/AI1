package call;

import java.util.Random;
import java.util.Scanner;

import javax.inject.Singleton;

public class AIOpp implements Runnable{
	
	private int userID;
	private int gameID;
	private String token;
	
	
	int i = 0;
	
	int NumOfPlayer = 0;
	
	static String[][] Map;
	
	 int Player_x;
	 int Player_y;
	 int predirection;
	
	public AIOpp(int weight, int height,int i)
	{
		Map = new String[weight][height];
		this.i = i;
	}
	
	

	private  void runAI(int weight, int height) {
		// TODO Auto-generated method stub
		String[][] map = new String[height][weight];
		Map = map;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < weight; j++) {
			Map[i][j] = ".";
			}
		}

		
		// NUMBER OF PLAYERS IN THE MAP INCREASES IF POST FUNCTION IS CALLED
		

		
			SetPlayer(weight, height); // Sets random start point for every player
	
		
			Random random = new Random();
			predirection = random.nextInt(4) + 1;
		
		int flag = 0; // flag of size # of players
		while(true) {
			
				
				if(flag == -1)
				{
					continue;
				}
				
				flag = startallplayer(weight, height, i); // CONVERT THIS LOGIC INTO THE THREAD . FLAG IDENTIFIES WHETHER PLAYER IS DEAD OR NOT
				
			
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
					count++;
			
			// WHEN THE COUNTER IS EQUAL TO # OF PLAYER , THEN GAME STOPS 
			if(count == 1)
			{
				break;
			}
				
			// WAIT 50 millisecs for the next turn ( loop iteration )
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private  int startallplayer(int weight, int height, int WhichOfPlayer) {
		// TODO Auto-generated method stub
		
		int Direction = predirection;
		
		int[] currentlocation = new int[2];
		currentlocation[0] = Player_x;
		currentlocation[1] = Player_y;
		
		Direction = smarteralgorithm.operator(Map, weight, height, Direction, currentlocation);
		predirection = Direction;
		
		if(Direction == 1) {
			Player_y--;
//			Map[Player_x[WhichOfPlayer]][Player_y[WhichOfPlayer]] = "*";
			Map[Player_x][Player_y] = String.valueOf(WhichOfPlayer);
		}
		else if(Direction == 2) {
			Player_x--;
//			Map[Player_x[WhichOfPlayer]][Player_y[WhichOfPlayer]] = "*";
			Map[Player_x][Player_y] = String.valueOf(WhichOfPlayer);
		}
		else if(Direction == 3) {
			Player_y++;
//			Map[Player_x[WhichOfPlayer]][Player_y[WhichOfPlayer]] = "*";
			Map[Player_x][Player_y] = String.valueOf(WhichOfPlayer);
		}
		else if(Direction == 4) {
			Player_x++;
//			Map[Player_x[WhichOfPlayer]][Player_y[WhichOfPlayer]] = "*";
			Map[Player_x][Player_y] = String.valueOf(WhichOfPlayer);
		}
		else if(Direction == -1) {
			return -1;			
		}
		return 1;
//		for(int i = 0; i < height; i++) {
//			System.out.println("");
//			for(int j = 0; j < weight; j++) {
//				System.out.print(Map[i][j]);
//			}
//		}
//	    try {
//			Thread.sleep(50);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
	}
	

	private  void SetPlayer(int weight, int height) {
		// TODO Auto-generated method stub
		while(true) {
			Random random = new Random();
			int current_x = random.nextInt(height); // Y of matrix
			int current_y = random.nextInt(weight); // X of matrix
			if(Map[current_x][current_y] == ".") {
//				Map[current_x][current_y] = "*";
				Map[current_x][current_y] = String.valueOf(i);
				Player_x = current_x; // Sets X value of player num
				Player_y = current_y; // Sets Y value of player num
				break;
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		runAI(Map.length,Map[0].length);
	}
}
