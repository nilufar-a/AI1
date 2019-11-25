package teamworkai;

import java.util.Random;
import java.util.Scanner;

public class Testmultiplayer {
	static String[][] Map;
	static int[] Player_x;
	static int[] Player_y;
	static int[] predirection;
	public static void main(String[] args) {
		System.out.println("input the size of the map, ie 12,55,spearter by ,");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String operator = sc.nextLine();
		String[] size = operator.split(",");
		createmultip(Integer.parseInt(size[0]), Integer.parseInt(size[1]));
		
		
	}

	private static void createmultip(int weight, int height) {
		// TODO Auto-generated method stub
		String[][] map = new String[height][weight];
		Map = map;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < weight; j++) {
			Map[i][j] = ".";
			}
		}
		System.out.println("please input how many players do you want to set, suggest not more than 5 players");
		Scanner sc = new Scanner(System.in);
		String NumOfPlayer = sc.nextLine();
		int current_x[] = new int[Integer.parseInt(NumOfPlayer)];
		int current_y[] = new int[Integer.parseInt(NumOfPlayer)];
		Player_x = current_x;
		Player_y = current_y;
		predirection = new int[Integer.parseInt(NumOfPlayer)];
		for(int i = 0; i < Integer.parseInt(NumOfPlayer); i++) {
			SetPlayer(weight, height, i);
		}
		for(int i = 0; i < Integer.parseInt(NumOfPlayer); i++) {
			Random random = new Random();
			predirection[i] = random.nextInt(4) + 1;
		}
		int[] flag = new int[Integer.parseInt(NumOfPlayer)];
		while(true) {
			for(int i = 0; i < Integer.parseInt(NumOfPlayer); i++) {
				if(flag[i] == -1)
					continue;
				flag[i] = startallplayer(weight, height, i);
			}
			for(int i = 0; i < height; i++) {
			System.out.println("");
			for(int j = 0; j < weight; j++) {
				System.out.print(Map[i][j]);
			}
		}
			int count = 0;
			for(int i = 0; i < Integer.parseInt(NumOfPlayer); i++) {
				if(flag[i] == -1)
					count++;
			}
			if(count == Integer.parseInt(NumOfPlayer))
				break;
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private static int startallplayer(int weight, int height, int WhichOfPlayer) {
		// TODO Auto-generated method stub
		int Direction = predirection[WhichOfPlayer];
		int[] currentlocation = new int[2];
		currentlocation[0] = Player_x[WhichOfPlayer];
		currentlocation[1] = Player_y[WhichOfPlayer];
		Direction = smarteralgorithm.operator(Map, weight, height, Direction, currentlocation);
		predirection[WhichOfPlayer] = Direction;
		if(Direction == 1) {
			Player_y[WhichOfPlayer]--;
//			Map[Player_x[WhichOfPlayer]][Player_y[WhichOfPlayer]] = "*";
			Map[Player_x[WhichOfPlayer]][Player_y[WhichOfPlayer]] = String.valueOf(WhichOfPlayer);
		}
		else if(Direction == 2) {
			Player_x[WhichOfPlayer]--;
//			Map[Player_x[WhichOfPlayer]][Player_y[WhichOfPlayer]] = "*";
			Map[Player_x[WhichOfPlayer]][Player_y[WhichOfPlayer]] = String.valueOf(WhichOfPlayer);
		}
		else if(Direction == 3) {
			Player_y[WhichOfPlayer]++;
//			Map[Player_x[WhichOfPlayer]][Player_y[WhichOfPlayer]] = "*";
			Map[Player_x[WhichOfPlayer]][Player_y[WhichOfPlayer]] = String.valueOf(WhichOfPlayer);
		}
		else if(Direction == 4) {
			Player_x[WhichOfPlayer]++;
//			Map[Player_x[WhichOfPlayer]][Player_y[WhichOfPlayer]] = "*";
			Map[Player_x[WhichOfPlayer]][Player_y[WhichOfPlayer]] = String.valueOf(WhichOfPlayer);
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
	

	private static void SetPlayer(int weight, int height, int num) {
		// TODO Auto-generated method stub
		while(true) {
			Random random = new Random();
			int current_x = random.nextInt(height);
			int current_y = random.nextInt(weight);
			if(Map[current_x][current_y] == ".") {
//				Map[current_x][current_y] = "*";
				Map[current_x][current_y] = String.valueOf(num);
				Player_x[num] = current_x;
				Player_y[num] = current_y;
				break;
			}
		}
	}
}
