package call;

import java.util.Random;

import com.example.AllPointsOnMap;

public class smarteralgorithm_base {
	static int current_x = 0;
	static int current_y = 0;
	static int Game_weight = 0;
	static int Game_height = 0;
	static AllPointsOnMap[][] GameMap = null;
	
	
	//weigh,height is the weight and height of map.
	public static void operator(AllPointsOnMap[][] MAP_MAIN, int weight, int height) {
		int Direction = 0;
		//generate the map and the beginning point by random
		Direction = Set_Begin(MAP_MAIN, weight, height);
//		Game_weight = weight;
//		Game_height = height;
//		System.out.println(current_x + "," + current_y + "~~" + Direction);
		
		//print one empty map
		for(int i = 0; i < height; i++) {
			System.out.println("");
			for(int j = 0; j < weight; j++) {
				System.out.print(GameMap[i][j]);
			}
		}
		
		while(true) {
//			int Direction = Find_Direction();
			int[] currentlocation = new int[2];
			currentlocation[0] = current_x;
			currentlocation[1] = current_y;
			
			//smarter algorithm apply for the next new direction
			Direction = smarteralgorithm.operator(MAP_MAIN, weight, height, Direction, currentlocation);
			
			if(Direction == 1) {//1 left, 2 up, 3 right, 4 down
				current_y--;
				GameMap[current_x][current_y].setState(AllPointsOnMap.State.TRACER);
			}
			else if(Direction == 2) {
				current_x--;
				GameMap[current_x][current_y].setState(AllPointsOnMap.State.TRACER);
			}
			else if(Direction == 3) {
				current_y++;
				GameMap[current_x][current_y].setState(AllPointsOnMap.State.TRACER);
			}
			else if(Direction == 4) {
				current_x++;
				GameMap[current_x][current_y].setState(AllPointsOnMap.State.TRACER);
			}
			else if(Direction == -1) {
				System.out.println("over");
				break;
			}

			for(int i = 0; i < height; i++) {
				System.out.println("");
				for(int j = 0; j < weight; j++) {
					System.out.print(GameMap[i][j]);
				}
			}
			
			
			//sleep for 0.5ms
		    try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		    
		   
		}
	}
	private static int Set_Begin(AllPointsOnMap[][] MAP_MAIN, int x, int y){
		Random random = new Random();
		current_x = random.nextInt(y);
		current_y = random.nextInt(x);
		MAP_MAIN[current_x][current_y].setState(AllPointsOnMap.State.TRACER);		
		GameMap = MAP_MAIN;
		return random.nextInt(4) + 1;
	}
}
