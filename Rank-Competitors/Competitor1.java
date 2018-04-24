// Constructor template for Competitor1:
//     new Competitor1 (Competitor c1)
//
// Interpretation: the competitor represents an individual or team

// Note:  In Java, you cannot assume a List is mutable, because all
// of the List operations that change the state of a List are optional.
// Mutation of a Java list is allowed only if a precondition or other
// invariant says the list is mutable and you are allowed to change it.

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;


// Class Competitor1 implements the interface Competitor

class Competitor1 implements  Competitor {
	
	String s; // s is the name of the given Competitor, where any sting will do
	

  //GIVEN: a string
 //EFFECT: Competitor name becomes the given string
	
    Competitor1 (String s) {

    	this.s = s;
   
    }

    //GIVEN:no arguments
    // returns the name of this competitor

    public String name () {
    	
    	return s;

        // Your code goes here.

    }

    // GIVEN: another competitor and a list of outcomes
    // RETURNS: true iff one or more of the outcomes indicates this
    //     competitor has defeated or tied the given competitor

    public boolean hasDefeated (Competitor cn, List<Outcome> outcomes) {
    
    	for(Outcome o : outcomes)
    	{	
    		if(!o.isTie())
    			if	(this.s == o.first().name() && cn.name()== o.second().name())
    				return true; 		
    			
    		if(o.isTie()) 
    			if ((this.s==o.second().name() && cn.name()==o.first().name())
    			|| ((this.s==o.first().name() && cn.name()==o.second().name())))
    					return true ;			
    	}
    
  	 return false;
    }
    

    // GIVEN: a list of outcomes
    // RETURNS: a list of the names of all competitors mentioned by
    //     the outcomes that are outranked by this competitor,
    //     without duplicates, in alphabetical order

    public  List<String>  outranks (List<Outcome> outcomes) {
    	List<String> st = new ArrayList<String>() ;
    	List<Competitor> cp = new ArrayList<Competitor>();	
    	List<Competitor> cp1 = new ArrayList<Competitor>();
    	Hashtable<Competitor, Boolean> ht1 = new Hashtable<Competitor, Boolean> ();
    	generate_competitors(outcomes,ht1);
    	
    	cp= directOutrank(this,outcomes,cp1, ht1);
    	
    	for(int i=0;i<cp.size(); i++)
    		st.add(cp.get(i).name());
    	
    	Collections.sort(st);
    	return st;

    	}
    
    // GIVEN: a competitor, list of outcomes, list of competitors complist, and Hashtable
    //of competitors htab
    // WHERE: complist is the list of competitors of the outcomes, htab is the hashtable
    //        with keys as competitors and default values of false.
    //RETURNS: the list of competitors which are directly outranked by the given competitor
  
   public static List<Competitor> directOutrank (Competitor1 c,List<Outcome> outcomes,
		  List<Competitor> complist, Hashtable <Competitor, Boolean> htab )
    {
    	
		for (Entry<Competitor, Boolean> entry : htab.entrySet())
		{
		    	
		    if(c.hasDefeated(entry.getKey(), outcomes) && entry.getValue() == false)
		    {
		    	complist.add(entry.getKey());
		    	htab.replace(entry.getKey(), true);
		    	
		    }
		    
		}
	
		indirectOutrank(outcomes,complist,htab);
		
		return complist;}


  //GIVEN:  list of outcomes, list of competitors complist, and Hashtable
  //of competitors htab	
  // WHERE: complist is the list of competitors of the outcomes, htab is the hashtable
  //        with keys as competitors and default values of false and true if competitor is
  //       explored.
  //RETURNS: the list of competitors which are indirectly outranked by the given competitor
  
 public static  List<Competitor> indirectOutrank (List<Outcome> outcomes,
		 				List<Competitor> complist,
		 				Hashtable <Competitor, Boolean> htab )
		    {
		for (int i =0;i < complist.size();i++) {
			int flag = 0;
			for (Entry<Competitor, Boolean> entry : htab.entrySet())
				  {
					  if(complist.get(i).hasDefeated(entry.getKey(), outcomes)
							  && entry.getValue()==true && flag==complist.size()-1)
						  break;
					  else if (complist.get(i).hasDefeated(entry.getKey(), outcomes)
					  && entry.getValue()==false)
					  {	
					
						 directOutrank((Competitor1) complist.get(i),outcomes, 
								 complist, htab);
					  }

				  }
			if(htab.get(complist.get(i))== true)
				flag = flag + 1;
			}
		
	return complist;
    }
  

	


    // GIVEN: a list of outcomes
    // RETURNS: a list of the names of all competitors mentioned by
    //     the outcomes that outrank this competitor,
    //     without duplicates, in alphabetical order

    public List<String> outrankedBy (List<Outcome> outcomes) {

        	List<String> st1 = new ArrayList<String>() ;
        	List<Competitor> cplist = new ArrayList<Competitor>();	
        	List<Competitor> cplist1 = new ArrayList<Competitor>();
        	Hashtable<Competitor, Boolean> ht1 = new Hashtable<Competitor, Boolean> ();
        	generate_competitors(outcomes,ht1);
        	
        	cplist= directOutranked(this,outcomes,cplist1, ht1);
        	
        	for(int i=0;i<cplist.size(); i++)
        		st1.add(cplist.get(i).name());
        	
        	Collections.sort(st1);
        	return st1;

        	}
    
    // GIVEN: a competitor, list of outcomes, list of competitors complist, and Hashtable
    //of competitors htab
    // WHERE: complist is the list of competitors of the outcomes, htab is the hashtable
    //        with keys as competitors and default values of false.
    //RETURNS: the list of competitors which  directly outrank  the given competitor
 public static List<Competitor> directOutranked (Competitor1 c1, List<Outcome> outcomes, 
		 List<Competitor> complist,
  		  Hashtable <Competitor, Boolean> htab1 )
 {
 	
		for (Entry<Competitor, Boolean> entry : htab1.entrySet())
		{
		    	
		    if(entry.getKey().hasDefeated(c1, outcomes) && entry.getValue() == false)
		    {
		    	complist.add(entry.getKey());
		    	htab1.replace(entry.getKey(), true);
		    	
		    }
		    
		}
		
	
		indirectOutranked(outcomes,complist,htab1);
		
		return complist;}
  	
 
 // GIVEN: list of outcomes, list of competitors complist, and Hashtable
 //of competitors htab
 // WHERE: complist is the list of competitors of the outcomes, htab is the hashtable
 //        with keys as competitors and default values of false.
 //RETURNS: the list of competitors which  indirectly outrank  the given competitor
 
 public static  List<Competitor> indirectOutranked (List<Outcome> outcomes, 
		 		List<Competitor> complist,
				  Hashtable <Competitor, Boolean> htab )
 {

  		
  		for (int i =0;i < complist.size();i++) {
  			int flag = 0;
  			for (Entry<Competitor, Boolean> entry : htab.entrySet())
  				  {
  					  if(entry.getKey().hasDefeated(complist.get(i), outcomes)
  							  && entry.getValue()==true && flag==complist.size()-1)
  						  break;
  					  else if(entry.getKey().hasDefeated(complist.get(i), outcomes)
  					  && entry.getValue()==false)
  					  {				
  						  directOutranked(((Competitor1) complist.get(i)),outcomes, 
  								complist, htab);
  					  }
  					  
  					  
  					  else
  						  continue;		
  		}
  			if(htab.get(complist.get(i))== true)
  				flag = flag + 1;
  		
  			}


  	return complist;
      }
    
    
 
    // GIVEN: a list of outcomes
    // RETURNS: a list of the names of all competitors mentioned by
    //     one or more of the outcomes, without repetitions, with
    //     the name of competitor A coming before the name of
    //     competitor B in the list if and only if the power-ranking
    //     of A is higher than the power ranking of B.

    public List<String> powerRanking (List<Outcome> outcomes) {
    	
    	List<String> plist = new ArrayList<String>();
    	List<Competitor>cplist = new ArrayList<Competitor>();
    	List<RankComparator> powerlist = new ArrayList<RankComparator>();
    	Hashtable<Competitor, Boolean> ht3 = new Hashtable<Competitor, Boolean> ();
    	generate_competitors(outcomes,ht3);
    	for (Entry<Competitor, Boolean> entry : ht3.entrySet())
		  {
    		cplist.add(entry.getKey());
		  }
    	
    	
    	for(int i=0;i < cplist.size();i++)
    	{
    		
    	Competitor	c= cplist.get(i);
    
    	RankComparator r = new RankComparator(c, c.outranks(outcomes).size(),
    						c.outrankedBy(outcomes).size(),
    						 nonLosingPercent((Competitor1) c,outcomes));
    		powerlist.add(r);
        
    	}
    	  
    	 
    	Collections.sort(powerlist);

    	for(int i =0; i< powerlist.size();i++)
    		plist.add(powerlist.get(i).getCompetitor().name());
    	
    	return plist;
    	    
 }
    //GIVEN: a competitor c and list of outcomes
    //RETURNS: non losing percentage value, which is non negative, and gives the ratio 
    //        of number of wins to the number of mentions.

  public static double nonLosingPercent(Competitor1 c, List<Outcome> outcomes)
  {
	  int number_of_wins =0;
	  int number_of_mentions=0;
	  
	  for(Outcome o : outcomes)
	  {
		  if(o.first()== c ||(o.isTie() && o.second()== c))
		  {
			  number_of_wins += 1;
			  number_of_mentions += 1;
		  }
		  
		  else if (!o.isTie() && o.second()== c)
			  number_of_mentions += 1;
	  }
	  
	  double non_losing_percent = (double) ((number_of_wins / number_of_mentions) * 100) ;
	  
	  return non_losing_percent;
  }
  
  
 //GIVEN: the list of outcomes, hashtable of competitors
 //RETURNS: the hashtable of unique competitors as keys and all values as false.
  public static Hashtable<Competitor, Boolean> generate_competitors(List<Outcome> outcomes,
			 Hashtable<Competitor, Boolean> ht){
	    	
	    	
	    	for(int i=0; i< outcomes.size(); i++ )
			{
				Outcome o = outcomes.get(i);
				
				if (!ht.containsKey(o.first())) {
					ht.put(o.first(), false);			

				}
				
				if  (!ht.containsKey(o.second())) {
					ht.put(o.second(), false);
				
				}
								
				else
					continue;
	    	}
	    	
	    	return ht;
	    }

  //GIVEN: a string array
  //EFFECT: Result for the test cases
  public static void main (String args[])
  {
  	CompetitorTests.competitorsTest();
  
}
}
