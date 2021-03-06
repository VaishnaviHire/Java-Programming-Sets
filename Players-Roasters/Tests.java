import java.util.Iterator;

public class Tests {
	
	public static void test()
	{

		Player p1 = Players.make("abc");
		Player p2 = Players.make("bcde");
		Player p3 = Players.make("Gordon Wayhard");
		Player p4 = Players.make("bcde");
		Player p5 = Players.make("Vaishnavi Hire");
		Player p6 = Players.make("A");
		Player p7 = Players.make("b");
		Player p8 = Players.make("c");
		

		Roster r1 = Rosters.empty();
		Roster r2 = Rosters.empty();
		Roster r3 = Rosters.empty();
		Roster r4 = Rosters.empty();
		Roster r5 = Rosters.empty();
		Roster r6 = Rosters.empty();
	
	

		
		r1 = r1.with(p1).with(p8).with(p5).with(p3).without(p2);
		r2 = r2.with(p3).with(p5).with(p1).with(p8);
		r3 = r3.with(p1).with(p2).with(p3).with(p4).without(p8).without(p7).with(p6);
		r4 = r4.with(p8);
		
		
	
	
	//Tests for Player 
	checkTrue(p1.available() == true, "p1 is initially available");
	p1.changeInjuryStatus(true);


	checkTrue(p1.available() == false, "p1 is unavailable after change in injury status" );
	
	checkTrue(p2.underContract()==true, "p2 is initially under contract");
	p2.changeContractStatus(false);
	checkTrue(p2.underContract() == false, "p2 is unavailable after change in contract status");
	
	checkTrue(p3.isInjured() == false, "initial injury status of p3 is false");
	p3.changeInjuryStatus(true);
	checkTrue(p3.isInjured() == true, "p3 injury status should be true");
	
	checkTrue(p4.isSuspended()==false,  "initial suspended status of p4 is false");
	p4.changeSuspendedStatus(true);
	checkTrue(p4.isSuspended()==true, "p4 injury status should be true");
	
	checkTrue(p2.toString() == p4.toString(), "name of p2 equals name of p3");
	checkFalse(p3.toString() == p4.toString(), "name of p3 is not equal to name of p4");
	
	
// Tests for Roster
	checkTrue(r1.with(p8).with(p8).equals(r1.with(p8)), "rosters should be equal");
	checkTrue(r5.without(p6).without(p6).equals(r5), "rosters should be equal");
	checkTrue(r2.has(p2) == false, "Player p2 is not present in roster r2");
	checkTrue(r3.has(p2) == true, "roster r3 contains player p2");
	checkTrue(r1.size()== 4, "the size of roster r1 is 4");
	checkTrue(r1.readyCount()==2, "the number of ready players in r1 are 2 "); // p1 and p3 are unavailable
	checkTrue(r1.readyRoster().equals(r1.without(p1).without(p3)), "the rosters should be equal");
	checkTrue(r5.equals(r6), "roster r5 equals roster r6");
	checkTrue(r5.readyRoster().equals(r6.readyRoster()), "ready rosters of r5 and r6 are equals");


	
	
	
	r5 = r5.with(p1).with(p2).with(p7);
	Iterator<Player> itr = r5.iterator();
	
	
	while(itr.hasNext()) {
		checkTrue(itr.next().toString()== "abc" );
		checkTrue(itr.next().toString()== "b");
		checkTrue(itr.next().toString()== "bcde");
		
	}
	
	
	checkTrue(r1.readyRoster().hashCode() == r1.without(p1).without(p3).hashCode(), 
			"hashcode of r1 should be constant");
	checkFalse(r3.toString().equals(r2.toString()), "size of r3 and r2 should be equal");
	
	
	
	
	summarize();
		
	}
	
	  private static int testsPassed = 0;
	    private static int testsFailed = 0;
	//
	    private static final String FAILED
	        = "    TEST FAILED: ";
	//
	    static void checkTrue (boolean result) {
	        checkTrue (result, result + " is not the expected result");
	    }
	//
	    static void checkTrue (boolean result, String name) {
	        if (result)
	            testsPassed = testsPassed + 1;
	        else {
	            testsFailed = testsFailed + 1;
	            System.err.println (FAILED + name);
	        }
	    }
	//
	    static void checkFalse (boolean result) {
	        checkFalse (result, result + " is not the expected result");
	    }
	//
	    static void checkFalse (boolean result, String name) {
	        checkTrue (! result, name);
	    }

	    static void summarize () {
	        System.err.println ("Passed " + testsPassed + " tests");
	        if (testsFailed > 0) {
	            System.err.println ("Failed " + testsFailed + " tests");
	        }
	    }
	}





