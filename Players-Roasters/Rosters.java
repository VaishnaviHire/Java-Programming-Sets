
import java.util.HashSet;
import java.util.Set;

public class Rosters {
	
    // Rosters.empty() is a static factory method 
	//Returns: an empty roster.
   
	public static Roster empty()
	{
		Set<Player> newtm =  new HashSet<Player> ();
		Roster r  = new Roster1(newtm);
		return r;
	}
	

}
