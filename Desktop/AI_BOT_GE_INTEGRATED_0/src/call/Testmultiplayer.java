package call;

import java.util.Random;
import java.util.Scanner;

import com.example.AllPointsOnMap;

public class Testmultiplayer {
	
	
	public static void main(String[] args) {
		System.out.println("input the size of the map, ie 12,55,spearter by ,");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String operator = sc.nextLine();
		String[] size = operator.split(",");
		
		System.out.println("# of players: ");
		Integer NOP = sc.nextInt();
		
		AllPointsOnMap[][] map = new AllPointsOnMap[20][20];
		

			AIOpp ai = new AIOpp("1",1,"tw24");
			Thread thread = new Thread(ai);
			thread.start();
			
			AIOpp ai1 = new AIOpp("2",1,"tw24");
			Thread thread1 = new Thread(ai1);
			thread1.start();
		
		
		
		
	}
	
	
}
