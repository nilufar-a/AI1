package teamworkai;

public class smarteralgorithm {
	public static int operator(String[][] map, int weight, int height, int predirection, int[] currentlocation ) {
		//需要一开始就转换地图的横跟纵的数据因为我想错了
//		String[][] map = new String[weight][height];
//		for(int i = 0; i < weight; i++) {
//			for(int j = 0; j < height; j++) {
//				map[i][j] = needtransfermap[j][i];
//			}
//		}
		
//		int change = 0;
//		change = currentlocation[0];
//		currentlocation[0] = currentlocation[1];
//		currentlocation[1] = change;
		
		//1,left,2 head,3 right,4 back
		int direction = ifnextoccupy(map, weight, height, predirection, currentlocation);
		if(direction == 0) {
			direction = transferdata(map, weight, height, currentlocation[0], currentlocation[1], predirection);
			predirection = direction;
		}		
		return predirection;
	}
	private static int transferdata(String[][] map, int weight, int height, int currentlx, int currently, int predirection) {
		// TODO Auto-generated method stub
		int direction = 0;
		
		
		int count = 0;
		if(currentlx - 1 < 0)
			count++;
		else if(map[currentlx - 1][currently].equals("*"))
			count++;
		if(currently + 1 >= weight)
			count++;
		else if(map[currentlx][currently + 1].equals("*"))
			count++;
		if(currentlx + 1 >= height)
			count++;
		else if(map[currentlx + 1][currently].equals("*"))
			count++;
		if(currently - 1 < 0)
			count++;
		else if(map[currentlx][currently - 1].equals("*"))
			count++;
		if(count == 4)
			return -1;	
		
		
		if(predirection == 1) {
			String[][] transdermap = transfermapfl(map, weight, height);
			direction = estimatearea(transdermap, height, weight, currently, currentlx);
			if(direction == 1) {
				return 2;
			}
			if(direction == 2) {
				return 4;
			}
		}
		else if(predirection == 2) {
			direction = estimatearea(map, weight, height, currentlx, currently);
			if(direction == 1) {
				return 1;
			}
			if(direction == 2) {
				return 3;
			}
		}
		else if(predirection == 3) {
			String[][] transfermap = transfermapfr(map, weight, height);
			direction = estimatearea(transfermap, height, weight, height - currentlx, weight - currently);
			if(direction == 1) {
				return 4;
			}
			if(direction == 2) {
				return 2;
			}
		}
		else if(predirection == 4) {
			String[][] transfermap = transfermapfd(map, weight, height);
			direction = estimatearea(transfermap, weight, height, height - currentlx, currently);
			if(direction == 1) {
				return 1;
			}
			if(direction == 2) {
				return 3;
			}
		}
		return -1;
	}
	private static String[][] transfermapfd(String[][] map, int weight, int height) {
		// TODO Auto-generated method stub
		String[][] transfermap = new String[height][weight];
		for(int i = 0, x = height -1; i < height; i++, x--) {
			for(int j = 0; j < weight; j++) {
				transfermap[x][j] = map[i][j];
			}
		}
		return transfermap;
	}
	private static String[][] transfermapfr(String[][] map, int weight, int height) {
		// TODO Auto-generated method stub
		String[][] transfermap = new String[weight][height];
		for(int i = 0, y = height - 1; i < height; i++, y--) {
			for(int j = 0, x = weight - 1; j < weight; j++, x--) {
				transfermap[x][y] = map[i][j];
			}
		}
		return transfermap;
	}
	private static String[][] transfermapfl(String[][] map, int weight, int height) {
		// TODO Auto-generated method stub
		String[][] transfermap = new String[weight][height];
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < weight; j++) {
				transfermap[j][i] = map[i][j];
			}
		}
		return transfermap;
			
	}
	private static int estimatearea(String[][] map, int weight, int height, int currentlocationx, int currentlocationy) {
		// TODO Auto-generated method stub
		int rightflag = 0;
		int leftflag = 0;
		int rightheight = 0;
		int leftheight = 0;
		int rightarea = 0;
		int leftarea = 0;
		int haveenterflagl = 0;
		int haveenterflagr = 0;
		int[] limitedright = new int[2];
		limitedright[0] = 999;
		limitedright[1] = 999;
		int[] limitedleft = new int[2];
		limitedleft[0] = 999;
		limitedleft[1] = 999;
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		for(int i = 0; i < height; i++) {
			System.out.println("");
			for(int j = 0; j < weight; j++) {
				System.out.print(map[i][j]);
			}
		}
		System.out.println("~~~~~~~~~~~~~");
		for(int i = currentlocationx; i < height; i++) {
			for(int j = currentlocationy; j < weight; j++) {
				if(j == currentlocationy + 1 && map[i][j].equals("*") ) {
					rightheight = i - currentlocationx + 1;
					rightflag++;
					break;
				}
//				System.out.println("");
//				System.out.println(i + " " + j + "weightl" + weight + "heightl" + height);
				if(map[i][j].equals("*")) {
					if(j < limitedright[1] && rightheight == 0) {
						limitedright[0] = i;
						limitedright[1] = j;
						haveenterflagr++;
					}	
				}
			}
		}
		if(rightheight == 0 && rightflag != 0) {
			rightheight = height - currentlocationx;
		}
		if(haveenterflagr == 0) {
			limitedright[1] = weight;
		}
		rightarea = (limitedright[1] - currentlocationy) * rightheight;
		

		for(int i = currentlocationx; i < height; i++) {
			for(int j = currentlocationy; j >= 0; j--) {
				if(j == currentlocationy - 1 && map[i][j].equals("*") ) {
					leftheight = height - currentlocationx + 1;
					leftflag++;
					break;
				}
//				System.out.println("");
//				System.out.println(i + " " + j + "weightl" + weight + "heightl" + height);
				if(map[i][j].equals("*")) {
					if(j < limitedleft[1] && leftheight == 0) {
						limitedleft[0] = i;
						limitedleft[1] = j;
						haveenterflagl++;
					}	
				}
			}
		}
				
		if(leftheight == 0 && leftflag != 0) {
			leftheight = height - currentlocationx;
		}
		if(haveenterflagl == 0) {
			limitedleft[1] = 0;
		}
		leftarea = (currentlocationy - limitedleft[1]) * leftheight;
		
		
		if(leftarea > rightarea) {
			System.out.println("leftwin" + " " + leftarea + " " + rightarea);
			return 1;
		}
		else if(leftarea < rightarea){
			System.out.println("rightwin" + " " + leftarea + " " + rightarea);
			return 2;
		}
		else if(leftarea == rightarea){
			System.out.println("equal" + " " + leftarea + " " + rightarea);
			if(currentlocationy - 1 < 0)
				return 2;
			else if(currentlocationy + 1 == weight)
				return 1;
			else {
				return 1;
			}
		}
		return -1;
	}
	private static int ifnextoccupy(String[][] map, int weight, int height, int predirection, int[] currentlocation) {
		if(predirection == 1) {
			if(currentlocation[1] - 1 < 0) {
				return 0;
			}
			else if(map[currentlocation[0]][currentlocation[1] - 1].equals("*")) {
				return 0;
			}
		}
		else if(predirection == 2) {
			if(currentlocation[0] - 1 < 0) {
				return 0;
			}
			if(map[currentlocation[0] - 1][currentlocation[1]].equals("*")) {
				return 0;
			}
		}
		else if(predirection == 3) {
			if(currentlocation[1] + 1 == weight) {
				return 0;
			}
			if(map[currentlocation[0]][currentlocation[1] + 1].equals("*")) {
				return 0;
			}
		}
		else if(predirection == 4) {
			if(currentlocation[0] + 1 == height) {
				return 0;
			}
			if(map[currentlocation[0] + 1][currentlocation[1]].equals("*")) {
				return 0;
			}
		}
		return predirection;
	}
}
