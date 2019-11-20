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

@RequestScoped
@Path("/aistart")
public class AICalls {

	
	private String userID;
	private String gameID;
	private String token;
	
	private String getCurrentState;
	
	private Integer index = 0;

	
	private List<AI> AiThread;
	{AiThread = new  LinkedList();}
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/ai-bot")
	public UserInfo start(UserInfo ui)
	{
		if(ui != null)
		{
			AI ai = new AI(ui.userID, ui.gameID, ui.token);
			AiThread.add(ai);
			for(int i = 0; i < AiThread.size(); i++)
			{
				if(!AiThread.get(i).isAlive())
				{
					AiThread.get(i).start();
				}
				
			}
		}
		return ui;
	}
	
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/ai-bot-get")
	public String getResult()
	{
	    String info = "";
		
		for(int i = 0; i < AiThread.size(); i++)
		{

				info += AiThread.get(i).getInfo() + "\n\n";
			
			
		}
		
		if(info == "")
			info = "INFO IS EMPTY";
		index++;
		return info;
	}
	

}
