package teamworkai;

import java.util.Scanner;

public class Get_InputData {
	public static String[] GetMap() {
		System.out.println("input the size of the map, ie 12,55,spearter by ,");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String operator = sc.nextLine();
		String[] size = operator.split(",");
		return size;
	}
	
}
