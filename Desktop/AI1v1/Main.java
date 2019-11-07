package teamworkai;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		System.out.println("choose use data we get or we input");
		System.out.println("1:use given data, 2:enter the data");
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String operator = sc.nextLine();
		
		if(operator.equals("1")) {
			Get_data.Get();
		}
		else if(operator.equals("2")) {
			String[] size = Get_InputData.GetMap();
			String[][] map = new String[Integer.parseInt(size[0])][Integer.parseInt(size[1])];
			for (int i = 0, j = 0; i < map.length;) {
				map[i][j] = ".";
//				System.out.println(map[i][j]);
				j++;
				if (j >= map[i].length) {
					i++;
					j = 0;
				}
			}
			try {
				Operator.operator(map, Integer.parseInt(size[0]), Integer.parseInt(size[1]));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
