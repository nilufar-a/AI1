package call;

import java.util.Random;
import java.util.Scanner;

public class Testmultiplayer {
	
	
	public static void main(String[] args) {
		System.out.println("input the size of the map, ie 12,55,spearter by ,");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String operator = sc.nextLine();
		String[] size = operator.split(",");
		
		System.out.println("# of players: ");
		Integer NOP = sc.nextInt();
		for(int i = 0; i < NOP; i++)
		{
			AIOpp ai = new AIOpp(Integer.parseInt(size[0]),Integer.parseInt(size[1]),i);
			Thread thread = new Thread(ai);
			thread.start();
		}
		
		
		
	}
	
	
}
