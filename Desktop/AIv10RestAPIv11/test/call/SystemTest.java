package call;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.example.AllPointsOnMap;

import junit.framework.Assert;

public class SystemTest {
	
	AllPointsOnMap[][] Map;
	
		
	@Test
	public void TestStartPlayerNoWayToMove()
	{
		Map = new AllPointsOnMap[25][25];
		for(int i = 0; i < 25; i++) {
			for(int j = 0; j < 25; j++) {
				
			Map[i][j] = new AllPointsOnMap();   // Map[][] = "."
			Map[i][j].setState(AllPointsOnMap.State.OBSTACLE);
			
			}
		}
		
		
		int[] currentlocation = new int[2];
		currentlocation[0] = 0;
		currentlocation[1] = 1;
		int result = smarteralgorithm.operator(Map,25,25,1,currentlocation); // DOWN
		System.out.println(result);
		Assert.assertEquals(Integer.valueOf(result), Integer.valueOf(-1));
	}
	
	@Test
	public void TestStartPlayerDOWN()
	{
		Map = new AllPointsOnMap[25][25];
		for(int i = 0; i < 25; i++) {
			for(int j = 0; j < 25; j++) {
				
			Map[i][j] = new AllPointsOnMap();   // Map[][] = "."
			Map[i][j].setState(AllPointsOnMap.State.OBSTACLE);
			
			}
		}
		
		Map[0][0].setState(AllPointsOnMap.State.EMPTY);
		Map[0][1].setState(AllPointsOnMap.State.EMPTY);
		Map[0][2].setState(AllPointsOnMap.State.EMPTY);
		Map[0][3].setState(AllPointsOnMap.State.EMPTY);
		
		
		int[] currentlocation = new int[2];
		currentlocation[0] = 0;
		currentlocation[1] = 1;
		int result = smarteralgorithm.operator(Map,25,25,1,currentlocation); // DOWN
		System.out.println("DOWS: " + result);
		Assert.assertEquals(Integer.valueOf(result), Integer.valueOf(1));
	}
	
	@Test
	public void TestStartPlayerLEFT()
	{
		Map = new AllPointsOnMap[25][25];
		for(int i = 0; i < 25; i++) {
			for(int j = 0; j < 25; j++) {
				
			Map[i][j] = new AllPointsOnMap();   // Map[][] = "."
			Map[i][j].setState(AllPointsOnMap.State.OBSTACLE);
			
			}
		}
		
		Map[0][0].setState(AllPointsOnMap.State.EMPTY);
		Map[0][1].setState(AllPointsOnMap.State.EMPTY);
		Map[0][2].setState(AllPointsOnMap.State.EMPTY);
		Map[0][3].setState(AllPointsOnMap.State.EMPTY);
		
		int[] currentlocation = new int[2];
		currentlocation[0] = 0;
		currentlocation[1] = 0;
		int result = smarteralgorithm.operator(Map,25,25,2,currentlocation); // LEFT
		System.out.println(result);
		Assert.assertEquals(Integer.valueOf(result), Integer.valueOf(3));
	}
	
	@Test
	public void TestStartPlayerUP()
	{
		Map = new AllPointsOnMap[25][25];
		for(int i = 0; i < 25; i++) {
			for(int j = 0; j < 25; j++) {
				
			Map[i][j] = new AllPointsOnMap();   // Map[][] = "."
			Map[i][j].setState(AllPointsOnMap.State.OBSTACLE);
			
			}
		}
		
		Map[0][0].setState(AllPointsOnMap.State.EMPTY);
		Map[0][1].setState(AllPointsOnMap.State.EMPTY);
		Map[0][2].setState(AllPointsOnMap.State.EMPTY);
		Map[0][3].setState(AllPointsOnMap.State.EMPTY);
		
		int[] currentlocation = new int[2];
		currentlocation[0] = 0;
		currentlocation[1] = 1;
		int result = smarteralgorithm.operator(Map,25,25,3,currentlocation); // UP
		System.out.println(result);
		Assert.assertEquals(Integer.valueOf(result), Integer.valueOf(3));
	}
	
	
	@Test
	public void TestStartPlayerRIGHT()
	{
		Map = new AllPointsOnMap[25][25];
		for(int i = 0; i < 25; i++) {
			for(int j = 0; j < 25; j++) {
				
			Map[i][j] = new AllPointsOnMap();   // Map[][] = "."
			Map[i][j].setState(AllPointsOnMap.State.OBSTACLE);
			
			}
		}
		
		Map[0][0].setState(AllPointsOnMap.State.EMPTY);
		Map[0][1].setState(AllPointsOnMap.State.EMPTY);
		Map[0][2].setState(AllPointsOnMap.State.EMPTY);
		Map[0][3].setState(AllPointsOnMap.State.EMPTY);
		
		
		int[] currentlocation = new int[2];
		currentlocation[0] = 0;
		currentlocation[1] = 1;
		int result = smarteralgorithm.operator(Map,25,25,4,currentlocation); // RIGHT
		System.out.println(result);
		Assert.assertEquals(Integer.valueOf(result), Integer.valueOf(3));
	}
	
	// ---------------------------------------NOT-----------------------------------------------
	
	@Test
	public void TestStartPlayerDownNOT()
	{
		Map = new AllPointsOnMap[25][25];
		for(int i = 0; i < 25; i++) {
			for(int j = 0; j < 25; j++) {
				
			Map[i][j] = new AllPointsOnMap();   // Map[][] = "."
			Map[i][j].setState(AllPointsOnMap.State.OBSTACLE);
			
			}
		}
		
		
		
		int[] currentlocation = new int[2];
		currentlocation[0] = 0;
		currentlocation[1] = 1;
		int result = smarteralgorithm.operator(Map,25,25,1,currentlocation); // DOWN
		System.out.println("DOWS: " + result);
		Assert.assertEquals(Integer.valueOf(result), Integer.valueOf(-1));
	}
	
	@Test
	public void TestStartPlayerLeftNOT()
	{
		Map = new AllPointsOnMap[25][25];
		for(int i = 0; i < 25; i++) {
			for(int j = 0; j < 25; j++) {
				
			Map[i][j] = new AllPointsOnMap();   // Map[][] = "."
			Map[i][j].setState(AllPointsOnMap.State.OBSTACLE);
			
			}
		}
		
		
		int[] currentlocation = new int[2];
		currentlocation[0] = 0;
		currentlocation[1] = 0;
		int result = smarteralgorithm.operator(Map,25,25,2,currentlocation); // LEFT
		System.out.println(result);
		Assert.assertEquals(Integer.valueOf(result), Integer.valueOf(-1));
	}
	
	@Test
	public void TestStartPlayerUpNOT()
	{
		Map = new AllPointsOnMap[25][25];
		for(int i = 0; i < 25; i++) {
			for(int j = 0; j < 25; j++) {
				
			Map[i][j] = new AllPointsOnMap();   // Map[][] = "."
			Map[i][j].setState(AllPointsOnMap.State.OBSTACLE);
			
			}
		}
		
		
		int[] currentlocation = new int[2];
		currentlocation[0] = 0;
		currentlocation[1] = 1;
		int result = smarteralgorithm.operator(Map,25,25,3,currentlocation); // UP
		System.out.println(result);
		Assert.assertEquals(Integer.valueOf(result), Integer.valueOf(-1));
	}
	
	
	@Test
	public void TestStartPlayerRightNOT()
	{
		Map = new AllPointsOnMap[25][25];
		for(int i = 0; i < 25; i++) {
			for(int j = 0; j < 25; j++) {
				
			Map[i][j] = new AllPointsOnMap();   // Map[][] = "."
			Map[i][j].setState(AllPointsOnMap.State.OBSTACLE);
			
			}
		}
		
		
		
		int[] currentlocation = new int[2];
		currentlocation[0] = 0;
		currentlocation[1] = 1;
		int result = smarteralgorithm.operator(Map,25,25,4,currentlocation); // RIGHT
		System.out.println(result);
		Assert.assertEquals(Integer.valueOf(result), Integer.valueOf(-1));
	}
	

}
