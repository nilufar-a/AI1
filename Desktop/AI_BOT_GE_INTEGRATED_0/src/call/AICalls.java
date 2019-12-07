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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors; 

import com.example.*;
import com.google.gson.Gson;

@Singleton
@Path("/")
public class AICalls {

	
	private String userID;
	private Integer gameID;
	private String token;
	
	private AllPointsOnMap[][] Map;
	
	private String getCurrentState;
	
	private Integer index = 0;

	
	private List<AI> aiList;
	{ aiList = new ArrayList<AI>(); }
	
	
	/*
	public AICalls()
	{
		/* Map = new AllPointsOnMap[20][20];
		System.out.println(Map.length + " | " + Map[0].length);
		// ---------------HERE I GET ERROR-------------------
		for (int i = 0, j = 0; i < Map.length;) {
			
			
			Map[i][j] = new AllPointsOnMap();
			Map[i][j].setState(AllPointsOnMap.State.EMPTY); // GIVES NULL POINTER EXCEPTION
			
			
//			System.out.println(map[i][j]);
			j++;
			if (j >= map[i].length) {
				i++;
				j = 0;
			}
		}  
	}*/
		
		
	

	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Singleton
	@Path("/ai-bot")
	public UserInfo start(UserInfo ui)
	{
		try
		{
			// AIOpp tmpAI = new AIOpp(ui.userID,ui.gameID,ui.token, Map);
			AIOpp tmpAI = new AIOpp(ui.userID,ui.gameID,ui.token);
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
