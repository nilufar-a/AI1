package teamworkai;

import java.util.Random;

public class Operator {
	static int current_x = 0;
	static int current_y = 0;
	static int Game_weight = 0;
	static int Game_height = 0;
	static String[][] GameMap = null;
	
	
	public static void operator(String[][] map, int weight, int height) {
		Set_Begin(map, weight, height);
		Game_weight = weight;
		Game_height = height;
		while(true) {
			int Direction = Find_Direction();
			if(Direction == 1) {
				GameMap[current_x - 1][current_y] = "*";
				current_x -= 1;
			}
			else if(Direction == 2) {
				GameMap[current_x][current_y + 1] = "*";
				current_y += 1;
			}
			else if(Direction == 3) {
				GameMap[current_x + 1][current_y] = "*";
				current_x += 1;
			}
			else if(Direction == 4) {
				GameMap[current_x][current_y - 1] = "*";
				current_y -= 1;
			}
			else if(Direction == -1) {
				System.out.println("over");
				break;
			}
			for (int i = 0, j = 0; i < GameMap.length;) {
				System.out.print(GameMap[i][j]);
				j++;
				if (j >= GameMap[i].length) {
					i++;
					j = 0;
					System.out.println("");
				}
			}
		    try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    System.out.println("\n");
		}
	}
	
	
	private static void Set_Begin(String[][] map, int x, int y){
		Random random = new Random();
		current_x = random.nextInt(x);
		current_y = random.nextInt(y);
		map[current_x][current_y] = "*";		
		GameMap = map;
	}
	
	
	private static int Find_Direction() {
		int count = 0;
		if(current_x - 1 <= 0)
			count++;
		else if(GameMap[current_x - 1][current_y].equals("*"))
			count++;
		if(current_y + 1 >= Game_height)
			count++;
		else if(GameMap[current_x][current_y + 1].equals("*"))
			count++;
		if(current_x + 1 >= Game_weight)
			count++;
		else if(GameMap[current_x + 1][current_y].equals("*"))
			count++;
		if(current_y - 1 <= 0)
			count++;
		else if(GameMap[current_x][current_y - 1].equals("*"))
			count++;
		if(count == 4)
			return -1;		
		Random random = new Random();// 1 left, 2 head, 3 right, 4 back
		int direction = -1;
		while(true) {
			direction = random.nextInt(4) + 1;
			int flag = -1;
			flag = Find_IfOccupy(direction);
			if(flag == 8) {
				return direction;
			}
		}
	}
	
	
	private static int Find_IfOccupy(int dir) {
		
		if(dir == 1 && current_x - 1 > 0){
			if(GameMap[current_x - 1][current_y].equals("*"))
				return 6;
			else
				return 8;
		}
		else if(dir == 2 && current_y + 1 < Game_height) {
			if(GameMap[current_x][current_y + 1].equals("*"))
				return 6;
			else
				return 8;
		}
		else if(dir == 3 && current_x + 1 < Game_weight) {
			if(GameMap[current_x + 1][current_y].equals("*"))
				return 6;
			else
				return 8;
		}
		else if(dir == 4 && current_y -1 > 0) {
			if(GameMap[current_x][current_y - 1].equals("*"))
				return 6;
			else
				return 8;
		}
		return 0;
	}
	
	
}
