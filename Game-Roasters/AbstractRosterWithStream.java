import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

abstract public class AbstractRosterWithStream implements RosterWithStream{
	
	Set<Player> hs = new HashSet<Player>();
	
	//The constructor method initializes the set with players
	AbstractRosterWithStream(Set<Player> hs)
	{
		this.hs=hs;
	}

	 // Returns a roster consisting of the given player together
    // with all players on this roster.
	@Override
	public abstract RosterWithStream with(Player p) ;

	// GIVEN: an object of type Player
	// Returns a roster consisting of all players on this roster
   // except for the given player.
	@Override
	public abstract RosterWithStream without(Player p) ;
	
	
	// GIVEN: an object of type Player
	// Returns true iff the given player is on this roster.
	@Override
	public abstract boolean has(Player p);

	// Returns the number of players on this roster.
	@Override
	public abstract int size();

	 // Returns the number of players on this roster whose current
    // status indicates they are available.
	@Override
	public abstract int readyCount();
	
	// Returns a roster consisting of all players on this roster
    // whose current status indicates they are available.
	@Override
	public abstract RosterWithStream readyRoster() ;

	// Returns an iterator that generates each player on this
    // roster exactly once, in alphabetical order by name.
	@Override
	public abstract Iterator<Player> iterator();

	 // Returns a sequential Stream with this RosterWithStream
    // as its source.
    // The result of this method generates each player on this
    // roster exactly once, in alphabetical order by name.
	@Override
	public abstract Stream<Player> stream(); 
	
	
	//GIVEN : an object obj
	//RETURNS: true if and only if the object is equal to this roster.
		
	@Override
	public boolean equals(Object obj) 
	{
		if(obj == null)
			return false;
		if(obj==this)
			return true;
		if(!(obj instanceof RosterWithStream))
			return false;
		
		RosterWithStream rs = (RosterWithStream) obj;
		Iterator<Player> itr = rs.iterator();
		
		if(this.hs.size()==rs.size())
		{
			
			while(itr.hasNext())
			{
				if(!this.hs.contains(itr.next()))
					return false;
			}
			return true;
		}
		
		else
			return false;
	
	}
	
	//Returns the hashcode of this roster
	@Override
	public int hashCode()
	{
		int res = hs.hashCode();
		return res;
	}
	
	
	//Returns the size of this roster as a string
	@Override
	public String toString()
	{
		return ((Integer)this.size()).toString();
	}
	



}
