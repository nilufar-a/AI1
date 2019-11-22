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

@Singleton
@Path("/aistart")
public class AICalls {

	
	private String userID;
	private String gameID;
	private String token;
	
	private String[][] map;
	
	private String getCurrentState;
	
	private Integer index = 0;

	
	private List<AI> aiList;
	{ aiList = new ArrayList<AI>(); }
	
	private ExecutorService service;
	
	public AICalls()
	{
		map = new String[20][20];
		for (int i = 0, j = 0; i < map.length;) {
			map[i][j] = ".";
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
		
			AI tmpAI = new AI(ui.userID,ui.gameID,ui.token,map);
			aiList.add(tmpAI);
			service = Executors.newFixedThreadPool(aiList.size());
			service.execute(aiList.get(aiList.size()-1));	
			
			
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
