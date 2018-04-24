
import java.util.HashSet;
import java.util.Set;

public class RosterWithStreams {
	
    // Rosters.empty() is a static factory method 
	//Returns: an empty roster.
   
	public static RosterWithStream empty()
	{
		Set<Player> newtm =  new HashSet<Player> ();
		RosterWithStream r  = new RosterWithStream1(newtm);
		return r;
	}
	


}
