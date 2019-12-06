package call;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.hk2.api.Immediate;
import org.glassfish.jersey.process.internal.RequestScoped;

import call.AI;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.sound.midi.Track;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Application;

import java.io.FileNotFoundException; 
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors; 

import com.example.*;

@Singleton
@Path("/")
public class AICalls {

	
	private String userID;
	private String gameID;
	private String token;
	
	private AllPointsOnMap[][] map;
	
	private String getCurrentState;
	
	private Integer index = 0;

	
	private List<AI> aiList;
	{ aiList = new ArrayList<AI>(); }
	
	
	public AICalls()
	{
		map = new AllPointsOnMap[20][20];
		System.out.println(map.length + " | " + map[0].length);
		// ---------------HERE I GET ERROR-------------------
		for (int i = 0, j = 0; i < map.length;) {
			
			
				map[i][j] = new AllPointsOnMap();
				map[i][j].setState(AllPointsOnMap.State.EMPTY); // GIVES NULL POINTER EXCEPTION
			
			
//			System.out.println(map[i][j]);
			j++;
			if (j >= map[i].length) {
				i++;
				j = 0;
			}
		}
		
	}

	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Singleton
	@Path("/ai-bot")
	public UserInfo start(UserInfo ui)
	{
		try
		{
			AIOpp tmpAI = new AIOpp(ui.userID,ui.gameID,ui.token, map);
			tmpAI.start();	
		}catch(Exception e)
		{
			System.out.println("ERROR " + e);
		}
			
			
		return ui;
	}
	
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Singleton
	@Path("/ai-bot-get")
	public String getResult()
	{
	    String info = "";
		
		for(int i = 0; i < aiList.size(); i++)
		{
				info += aiList.get(i).getInfo() + "\n\n";
		}
		
		if(info == "")
			info = "INFO IS EMPTY";
		
		index++;
		return info;
	}
	

}
