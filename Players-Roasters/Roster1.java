import java.util.TreeSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
// Roster1 class implements all the methods of Roster interface
// A Roster1 object is a Tree Set of Player type objects


public class Roster1 implements Roster {

	Set<Player> hs= new HashSet<Player> (); 
	
	//Constructor method
	//GIVEN: a tree set of players.
	Roster1(Set<Player> hs)
	{
		this.hs = hs;     //A HashSet is set of Player objects 
	}
	
	 // Returns a roster consisting of the given player together
    // with all players on this roster.
    // Example:
    //     r.with(p).with(p)  =>  r.with(p)
	@Override
	public Roster with(Player p) {
		
		Set<Player> newtm =  new HashSet<Player> ();
		newtm.addAll(hs);
		newtm.add(p);
		Roster r = new Roster1(newtm);
		return r;
	}
	
	// GIVEN: an object of type Player
	 // Returns a roster consisting of all players on this roster
    // except for the given player.
    // Examples:
    //     Rosters.empty().without(p)  =>  Rosters.empty()
    //     r.without(p).without(p)     =>  r.without(p)
	@Override
	public Roster without(Player p) {
		
		Set<Player> newtm =  new HashSet<Player> ();
		newtm.addAll(hs);
		newtm.remove(p);
		Roster r = new Roster1(newtm);
		return r;
	}
	// GIVEN: an object of type Player
	 // Returns true iff the given player is on this roster.
    // Examples:
    //
    //     Rosters.empty().has(p)  =>  false
    //
    // If r is any roster, then
    //
    //     r.with(p).has(p)     =>  true
    //     r.without(p).has(p)  =>  false
	@Override
	public boolean has(Player p) {
		return (hs.contains(p));
		
	}
	

    // Returns the number of players on this roster.
    // Examples:
    //
    //     Rosters.empty().size()  =>  0
    //
    // If r is a roster with r.size() == n, and r.has(p) is false, then
    //
    //     r.without(p).size()          =>  n
    //     r.with(p).size()             =>  n+1
    //     r.with(p).with(p).size()     =>  n+1
    //     r.with(p).without(p).size()  =>  n
	
	@Override
	public int size() {
	
		Set<Player> newtm =  new HashSet<Player> ();
		newtm.addAll(hs);
		return newtm.size();
	}
	
	 // Returns the number of players on this roster whose current
    // status indicates they are available.

	@Override
	public int readyCount() {
		
		Set<Player> newtm =  new HashSet<Player> ();
		newtm.addAll(hs);
		int count = 0;
		for(Player p : newtm)
			if(p.available())
				count++;
		
		return count;
	}
	 // Returns a roster consisting of all players on this roster
    // whose current status indicates they are available.
	@Override
	public Roster readyRoster() {
		
		Set<Player> newtm =  new HashSet<Player> ();
		for(Player p : hs) {
			if(p.available())
				newtm.add(p);	
		}
		
		Roster r = new Roster1(newtm);
		return r;
	}

	 // Returns an iterator that generates each player on this
    // roster exactly once, in alphabetical order by name.
	@Override
	public Iterator<Player> iterator() {
		
		Set<Player> newts =  new TreeSet<Player> ();
		newts.addAll(hs);
		newts = Collections.unmodifiableSet(newts);
		return newts.iterator();
	}
	
	//GIVEN : an object obj
	//RETURNS: true if and only if the object is equal to this roster.
	
	@Override
	public boolean equals(Object obj)
	{
		Iterator<Player> itr = ((Roster) obj).iterator();
		if(!(obj instanceof Roster))
			return false;
		
		else if(this.hs.size()==((Roster) obj).size())
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
	
	
	

	



