
public class Player1 implements Player, Comparable<Player> {

	String name;
	boolean contract, suspended,injured;
	
	// 
	Player1(String name)
	{
		this.name = name;                   // s represents the name of the player
		contract = true;              // represents the status of the contract of the player
		suspended = false;            // represents the suspension status of the player
		injured = false;			 //  represents the injury status of the player
	}

	@Override
	// RETURNS: the name of this player.
    // Example:
    //     Players.make("Gordon Wayhard").name()  =>  "Gordon Wayhard"
	public String name() {
		
		return this.name;
	}

	// Returns true iff this player is
    //     under contract, and
    //     not injured, and
    //     not suspended
    // Example:
    //     Player gw = Players.make ("Gordon Wayhard");
    //     System.out.println (gw.available());  // prints true
    //     gw.changeInjuryStatus (true);
    //     System.out.println (gw.available());  // prints false
	@Override
	public boolean available() {
		if(contract==true && suspended == false && injured==false)
			return true;
		else
			return false;
	}
	
	// Returns true iff this player is under contract (employed).
    // Example:
    //     Player ih = Players.make ("Isaac Homas");
    //     System.out.println (ih.underContract());  // prints true
    //     ih.changeContractStatus (false);
    //     System.out.println (ih.underContract());  // prints false
    //     ih.changeContractStatus (true);
    //     System.out.println (ih.underContract());  // prints true
	
	@Override
	public boolean underContract() {
		// TODO Auto-generated method stub
		return contract;
	}

	// Returns true iff this player is injured.
	@Override
	public boolean isInjured() {
		// TODO Auto-generated method stub
		return injured;
	}

	// Returns true iff this player is suspended.
	@Override
	public boolean isSuspended() {
		// TODO Auto-generated method stub
		return suspended;
	}

	//GIVEN: a boolean value
	// Changes the underContract() status of this player
    // to the specified boolean.
	@Override
	public void changeContractStatus(boolean newStatus) {
		// TODO Auto-generated method stub
		contract = newStatus;
	}

	// Changes the isInjured() status of this player
    // to the specified boolean.
	@Override
	public void changeInjuryStatus(boolean newStatus) {
		// TODO Auto-generated method stub
		injured = newStatus;
		
	}

	// Changes the isSuspended() status of this player
    // to the specified boolean.
	@Override
	public void changeSuspendedStatus(boolean newStatus) {
		// TODO Auto-generated method stub
		suspended = newStatus;
	}
	
	//Returns the name of the player as a string
	@Override
	public String toString() {
		return this.name();
	}

	
	//Returns integer after comparing this player to the given player.
	@Override
	public int compareTo(Player p) {
		if(this == p)
			return 0;
		else if(this.name().equals(p.name()))
			return -1;
		else return this.name().compareTo(p.name());
		
	}
	
}
