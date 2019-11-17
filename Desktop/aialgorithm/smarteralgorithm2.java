package teamworkai;

public class smarteralgorithm2 {
	public static int operator(String[][] needtransfermap, int height, int weight, int predirection, int[] currentlocation ) {
		//需要一开始就转换地图的横跟纵的数据因为我想错了
		String[][] map = new String[weight][height];
		for(int i = 0; i < weight; i++) {
			for(int j = 0; j < height; j++) {
				map[i][j] = needtransfermap[j][i];
			}
		}
		
		int change = 0;
		change = currentlocation[0];
		currentlocation[0] = currentlocation[1];
		currentlocation[1] = change;
		
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
		if(currentlx - 1 <= 0)
			count++;
		else if(map[currentlx - 1][currently].equals("*"))
			count++;
		if(currently + 1 >= height)
			count++;
		else if(map[currentlx][currently + 1].equals("*"))
			count++;
		if(currentlx + 1 >= weight)
			count++;
		else if(map[currentlx + 1][currently].equals("*"))
			count++;
		if(currently - 1 <= 0)
			count++;
		else if(map[currentlx][currently - 1].equals("*"))
			count++;
		if(count == 4)
			return -1;	
		
		
		
		
		if(predirection == 1) {
			String[][] transdermap = transfermapfl(map, weight, height);
			direction = estimatearea(transdermap, height, weight, currently, currentlx);
			if(direction == 1) {
				return 1;
			}
			if(direction == 2) {
				return 3;
			}
		}
		else if(predirection == 2) {
			direction = estimatearea(map, weight, height, currentlx, currently);
			if(direction == 1) {
				return 2;
			}
			if(direction == 2) {
				return 4;
			}
		}
		else if(predirection == 3) {
			String[][] transfermap = transfermapfr(map, weight, height);
			direction = estimatearea(transfermap, height, weight, height - currently, weight - currentlx);
			if(direction == 1) {
				return 3;
			}
			if(direction == 2) {
				return 1;
			}
		}
		else if(predirection == 4) {
			String[][] transfermap = transfermapfd(map, weight, height);
			direction = estimatearea(transfermap, weight, height, currentlx, height - currently);
			if(direction == 1) {
				return 2;
			}
			if(direction == 2) {
				return 4;
			}
		}
		return -1;
	}
	private static String[][] transfermapfd(String[][] map, int weight, int height) {
		// TODO Auto-generated method stub
		String[][] transfermap = new String[weight][height];
		for(int i = 0; i < weight; i++) {
			for(int j = 0, y = height - 1; j < height; j++, y--) {
				transfermap[i][y] = map[i][j];
			}
		}
		return transfermap;
	}
	private static String[][] transfermapfr(String[][] map, int weight, int height) {
		// TODO Auto-generated method stub
		String[][] transfermap = new String[height][weight];
		for(int i = 0, y = weight - 1; i < weight; i++, y--) {
			for(int j = 0, x = height - 1; j < height; j++, x--) {
				transfermap[x][y] = map[i][j];
			}
		}
		return transfermap;
	}
	private static String[][] transfermapfl(String[][] map, int weight, int height) {
		// TODO Auto-generated method stub
		String[][] transfermap = new String[height][weight];
		for(int i = 0; i < weight; i++) {
			for(int j = 0; j < height; j++) {
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
		int[] limitedright = new int[2];
		limitedright[0] = 999;
		limitedright[1] = 999;
		int[] limitedleft = new int[2];
		limitedleft[0] = 999;
		limitedleft[1] = 999;
		for(int j = currentlocationy; j >= 0; j--) {
			for(int i = currentlocationx; i < weight; i++) {
				if(i == currentlocationx + 1 && map[i][j].equals("*") ) {
					rightheight = currentlocationy - j + 1;
					rightflag++;
					break;
				}
				if(map[i][j].equals("*")) {
					if(i < limitedright[0] && rightheight == 0) {
						limitedright[0] = i;
						limitedright[1] = j;
					}	
				}
			}
		}
		if(rightheight == 0 && rightflag != 0) {
			rightheight = currentlocationy;
		}
		rightarea = (limitedright[0] - currentlocationx) * rightheight;
		
		
		for(int j = currentlocationy; j >= 0; j--) {
			for(int i = currentlocationx; i >= 0; i--) {
				if(i == currentlocationx - 1 && map[i][j].equals("*") ) {
					leftheight = currentlocationy - j + 1;
					leftflag++;
					break;
				}
				if(map[i][j].equals("*")) {
					if(i < limitedleft[0] && leftheight == 0) {
						limitedleft[0] = i;
						limitedleft[1] = j;
					}	
				}
			}
		}
		if(leftheight == 0 && leftflag != 0) {
			leftheight = currentlocationy;
		}
		leftarea = (currentlocationx - limitedleft[0]) * leftheight;
		
		if(leftarea >= rightarea) {
			return 1;
		}
		else {
			return 2;
		}
		
	}
	private static int ifnextoccupy(String[][] map, int weight, int height, int predirection, int[] currentlocation) {
		if(predirection == 1) {
			if(currentlocation[0] - 1 < 0) {
				return 0;
			}
			else if(map[currentlocation[0] - 1][currentlocation[1]].equals("*")) {
				return 0;
			}
		}
		else if(predirection == 2) {
			if(currentlocation[1] + 1 == height) {
				return 0;
			}
			if(map[currentlocation[0]][currentlocation[1] + 1].equals("*")) {
				return 0;
			}
		}
		else if(predirection == 3) {
			if(currentlocation[0] + 1 == weight) {
				return 0;
			}
			if(map[currentlocation[0] + 1][currentlocation[1]].equals("*")) {
				return 0;
			}
		}
		else if(predirection == 4) {
			if(currentlocation[1] - 1 < 0) {
				return 0;
			}
			if(map[currentlocation[0]][currentlocation[1] - 1].equals("*")) {
				return 0;
			}
		}
		return predirection;
	}
}
