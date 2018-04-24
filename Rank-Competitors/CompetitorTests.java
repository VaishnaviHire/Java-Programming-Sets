import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Competitor test is a class for testing all the methods of class Competitor1.

public class CompetitorTests {

    public static void competitorsTest ()
    {
    	
    	Competitor c1 = new Competitor1("A");
    	Competitor c2 = new Competitor1("B");
    	Competitor c3 = new Competitor1("C");
    	Competitor c4 = new Competitor1("D");
    	Competitor c5 = new Competitor1("E");
    	Competitor c6 = new Competitor1("F");
    	
    	Outcome o1 = new Defeat1(c1,c4);
    	Outcome o2 = new Defeat1(c1,c5);
    	Outcome o3 = new Tie1(c2,c4);
    	Outcome o4 = new Defeat1(c4,c2);
    	Outcome o5 = new Defeat1(c3,c6);
    	Outcome o6 = new Defeat1(c6,c5);
    	Outcome o7 = new Defeat1(c3,c2);
    	Outcome o8 = new Defeat1(c1, c3);
    	
    	
    	
    	List<Outcome> outcomes1 = new ArrayList<Outcome>();
    	outcomes1.add(o1);
    	outcomes1.add(o2);
    	outcomes1.add(o3);
    	outcomes1.add(o4);
    	outcomes1.add(o5);
    	outcomes1.add(o6);
    	
       
    	List<Outcome> outcomes2 = new ArrayList<Outcome>();
    	outcomes2.add(o7);
    	outcomes2.add(o8);
    	outcomes2.add(o3);
    	
    	List<Outcome> outcomes3 =  new ArrayList<Outcome>();
    	outcomes3.add(o1);
    	outcomes3.add(o3);
    	outcomes3.add(o7);
    	outcomes3.add(o5);

  
    	
    	checkTrue(c1.hasDefeated(c2, outcomes1)== false);
    	checkTrue(c3.hasDefeated(c4, outcomes1)== false);
    	checkTrue(c4.hasDefeated(c2, outcomes2)== true);
    	checkFalse(c4.hasDefeated(c3,outcomes3)== true);
    	checkFalse(c2.hasDefeated(c6, outcomes1) == true);
   	
    	checkTrue(c1.outranks(outcomes1).equals(Arrays.asList("B","D","E")));
    	checkTrue(c1.outranks(outcomes2).equals(Arrays.asList("B","C","D")));
    	checkFalse(c2.outranks(outcomes3).equals(Arrays.asList("A","B","C","D")));
    	checkFalse(c4.outranks(outcomes3).equals(Arrays.asList("B")));
    	
   	
    	checkTrue(c3.outrankedBy(outcomes1).equals(Arrays.asList()));
    	checkTrue(c2.outrankedBy(outcomes2).equals(Arrays.asList( "A","B","C","D")));
    	checkFalse(c4.outrankedBy(outcomes3).equals(Arrays.asList( "A","D","E"))); //[A, B, C, D]
    	checkFalse(c1.outranks(outcomes1).equals(Arrays.asList("B", "C"))); // [B, D, E]
    
    	checkTrue(c1.powerRanking(outcomes1).equals(Arrays.asList( "A","C","F","B","D","E")));
    	checkTrue(c1.powerRanking(outcomes2).equals(Arrays.asList( "A","C","D","B")));
    	checkFalse(c1.powerRanking(outcomes3).equals(Arrays.asList( "A","F","D","B")));//[C, A, F, B, D]
    
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
