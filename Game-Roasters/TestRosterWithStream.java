import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class TestRosterWithStream {
	

	public static void main(String args[])
	{

		Player p1 = Players.make("abc");
		Player p2 = Players.make("bcde");
		Player p3 = Players.make("Gordon Wayhard");
		Player p4 = Players.make("bcde");
		Player p5 = Players.make("Vaishnavi Hire");
		Player p6 = Players.make("A");
		Player p7 = Players.make("b");
		Player p8 = Players.make("c");
		
		RosterWithStream r1 = RosterWithStreams.empty();
		RosterWithStream r2 = RosterWithStreams.empty();
		RosterWithStream r3 = RosterWithStreams.empty();
		RosterWithStream r4 = RosterWithStreams.empty();
		RosterWithStream r5 = RosterWithStreams.empty();
		RosterWithStream r6 = RosterWithStreams.empty();
		RosterWithStream r7 = RosterWithStreams.empty();
		

		
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
	
	//with-equals
	checkTrue(r1.with(p8).with(p8).equals(r1.with(p8)), "rosters should be equal");
	
	//without-equals
	checkTrue(r5.without(p6).without(p6).equals(r5), "rosters should be equal");
	
	//has
	checkTrue(r2.has(p2) == false, "Player p2 is not present in roster r2");
	checkTrue(r3.has(p2) == true, "roster r3 contains player p2");
	
	//size
	checkTrue(r1.size()== 4, "the size of roster r1 is 4");
	
	//readyCount
	checkTrue(r1.readyCount()==2, "the number of ready players in r1 are 2 "); // p1 and p3 are unavailable
	
	//readyRoster - equals
	checkTrue(r1.readyRoster().equals(r1.without(p1).without(p3)), "the rosters should be equal");
	checkTrue(r5.equals(r6), "roster r5 equals roster r6");
	checkTrue(r5.readyRoster().equals(r6.readyRoster()), "ready rosters of r5 and r6 are equals");

//	//equals
	r5 = r5.with(p1).with(p7).with(p2);
	r6 = r6.with(p7).with(p2).with(p1);
	Iterator<Player> itr = r5.iterator();
	while(itr.hasNext()) {	
		checkTrue(itr.next().toString()== "abc" );
		checkTrue(itr.next().toString()== "b");
		checkTrue(itr.next().toString()== "bcde");
		
	}
	
	//hashCode
	checkTrue(r1.readyRoster().hashCode() == r1.without(p1).without(p3).hashCode(), 
			"hashcode of r1 should be constant");
	checkTrue(r6.hashCode()==(r5.hashCode()) , "hashcode of equal rosters is equal");
	
	//toString
	checkFalse(r3.toString().equals(r2.toString()), "size of r3 and r2 should be equal");
	
	
	// Stream methods
	
	// allMatch
	checkTrue(r5.readyRoster().stream().allMatch(pl -> pl.available()) == true, "All players should be available");
	checkTrue(r1.stream().allMatch(pl -> pl.available())== false, "Some players maybe be unavailable in r1");
	
	//anyMatch
	checkTrue(r1.stream().anyMatch(pl-> pl.isInjured()) == true, "Player p3 is injured in r1");
	checkTrue(r6.stream().anyMatch(pl->pl.name().endsWith("d"))==false, "no player name ends with d");
	
	//count
	checkTrue(r1.size()== (int)r1.stream().count(), "Size of roster and stream should be equal");
	
	//filter
	Iterator<Player> itr1 = r1.stream().filter(pl->pl.available()).iterator();
	Iterator<Player> itr2 = r1.readyRoster().stream().iterator();
	
	while(itr1.hasNext() && itr2.hasNext())
		checkTrue(itr1.next().equals(itr2.next()), "every element in filtered r1 and ready roster r1 should be equal");
	
	
	//distinct
	Iterator<Player> itr3 = r5.stream().distinct().iterator();
	Iterator<Player> itr4 = r5.stream().iterator();
	
	while( itr4.hasNext())
		checkTrue(itr3.next().equals(itr4.next()), "roster r5 has distinct elements");


	//findFirst
	checkTrue(r5.stream().findFirst().get().name()=="abc", "first element of r5 is player named abc");
	checkTrue(r1.stream().findFirst().get().equals(p3), "first element of r1 is p6 ");
	
	
	//findAny
	checkTrue(r5.stream().filter(pl->pl.name().startsWith("b")).findAny().get().name()== "bcde" 
			|| r5.stream().filter(pl->pl.name().startsWith("b")).findAny().get().name()== "b", "two players starting with b exist in r5");
	
	
	//map - reduce
	checkFalse(r1.readyRoster().stream().map(Player::available).reduce(true,(pl1,pl2)-> pl1 && pl2)
			== r1.stream().map(Player::available).reduce(true,(pl1,pl2)-> pl1 && pl2), "r1 and r1 ready roster have different players" );
	
	//skip
	checkTrue((int)r5.stream().skip(2).count()== r5.size()-2, "size of r5 decreases by 2");
	
	//toArray
	checkTrue(r5.size()== r5.stream().toArray().length, "size remains constant after converting to array");
	
	
	//forEach
	TreeSet<Player> l1= new TreeSet<Player>(Comparator.comparing(Player :: name));
	TreeSet<Player> l2= new TreeSet<Player>(Comparator.comparing(Player :: name));
	
	r5.stream().forEach(pl1->l1.add(pl1));
	r6.stream().forEach(pl2->l2.add(pl2));
	
	checkTrue(l1.equals(l2), "lists l1 and l2 are equal");
	
	
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
