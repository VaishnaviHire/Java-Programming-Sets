//Players class contain static factory method for Player1

public class Players {

	//GIVEN: a string , any string will do
	//RETURNS:  a player with the given name who is (initially) available.
	public static Player make (String s) {
		return new Player1(s);
	}
	
	public static void main(String args[])
	{
		Tests.test();
		
	}}
	
		
	
