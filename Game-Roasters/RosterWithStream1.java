import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RosterWithStream1 extends AbstractRosterWithStream implements RosterWithStream{
	
	//Constructor method
	//GIVEN: a tree set of players.
	RosterWithStream1(Set<Player> hs)
	{
		super(hs); 			//invoke constructor of super class
	}

	 // Returns a roster consisting of the given player together
    // with all players on this roster.
    // Example:
    //     r.with(p).with(p)  =>  r.with(p)
	@Override
	public RosterWithStream with(Player p) {
		
		RosterWithStream rs = new RosterWithStream1
				(Stream.concat(this.stream(), Stream.of(p))
						.collect(Collectors.toSet()));
		return rs;
	}

	// GIVEN: an object of type Player
	// Returns a roster consisting of all players on this roster
   // except for the given player.
   // Examples:
   //     RosterWithStreams.empty().without(p)  =>  RosterWithStreams.empty()
   //     r.without(p).without(p)     =>  r.without(p)
	@Override
	public RosterWithStream without(Player p) {
		RosterWithStream rs = new RosterWithStream1
				(this.stream().filter(p1->!p1.equals(p))
						.collect(Collectors.toSet()));
		return rs;
	}
	
	// GIVEN: an object of type Player
	 // Returns true iff the given player is on this roster.
   // Examples:
   //
   //     RosterWithStreams.empty().has(p)  =>  false
   //
   // If r is any roster, then
   //
   //     r.with(p).has(p)     =>  true
   //     r.without(p).has(p)  =>  false
	@Override
	public boolean has(Player p) {
		return this.stream().anyMatch(p1->p1.equals(p));
	}
	
	// Returns the number of players on this roster.
    // Examples:
    //
    //     RosterWithStreams.empty().size()  =>  0
    //
    // If r is a roster with r.size() == n, and r.has(p) is false, then
    //
    //     r.without(p).size()          =>  n
    //     r.with(p).size()             =>  n+1
    //     r.with(p).with(p).size()     =>  n+1
    //     r.with(p).without(p).size()  =>  n

	@Override
	public int size() {
		return hs.size();
	}
	
	 // Returns the number of players on this roster whose current
    // status indicates they are available.

	@Override
	public int readyCount() {
		return (int)this.stream().filter(p1->p1.available()).count();
	}

	// Returns a roster consisting of all players on this roster
    // whose current status indicates they are available.
	@Override
	public RosterWithStream readyRoster() {
		
		return new RosterWithStream1
				(this.stream().filter(p1->p1.available())
						.collect(Collectors.toSet()));
	}
	
	
	 // Returns an iterator that generates each player on this
    // roster exactly once, in alphabetical order by name.
	@Override
	public Iterator<Player> iterator() {
		Set<Player> st = new LinkedHashSet<Player>();
		st = Collections.unmodifiableSet(this.stream()
				.collect(Collectors.toCollection(LinkedHashSet:: new)));
		return st.iterator();
	}
	

    // Returns a sequential Stream with this RosterWithStream
    // as its source.
    // The result of this method generates each player on this
    // roster exactly once, in alphabetical order by name.
    // Examples:
    //
    //     RosterWithStreams.empty().stream().count()  =>  0
    //
    //     RosterWithStreams.empty().stream().findFirst().isPresent()
    //         =>  false
    //
    //     RosterWithStreams.empty().with(p).stream().findFirst().get()
    //         =>  p
    //
    //     this.stream().distinct()  =>  this.stream()
    //
    //     this.stream().filter((Player p) -> p.available()).count()
    //         =>  this.readyCount()

	@Override
	public Stream<Player> stream() {
		return hs.stream().sorted(Comparator.comparing(Player :: name));
	}

	


}
