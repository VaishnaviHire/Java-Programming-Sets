//RankComparator class contains methods for comparing two Competitors according to power
//ranking conditions
public class RankComparator implements Comparable<RankComparator> {
	
	Competitor competitor;    // is the given competitor
	int number_of_outranks;   // is the number of competitors the given competitor outranks.
	int number_of_outrankedby; // is the number of competitors that outrank the given competitor
	double non_losing_percent; // is the real value which determines the ratio of number of wins
	                           // to the number of mentions of the competitor
	
	
	//GIVEN: competitor,number_of_outranks, number_of_outrankedby,non_losing_percent
	//EFFECT: Constructor Method
	
	public RankComparator(Competitor competitor, int number_of_outranks, int number_of_outrankedby,
			double non_losing_percent) {
		super();
		this.competitor = competitor;
		this.number_of_outranks = number_of_outranks;
		this.number_of_outrankedby = number_of_outrankedby;
		this.non_losing_percent = non_losing_percent;
	}



	//RETURNS: the competitor
	public Competitor getCompetitor() {
		return competitor;
	}


	//GIVEN: an object of class RankComparator,o
	//RETURNS: An integer value such that
	//         value = 1, if the given object is greater than o
	//                    according to power rank conditions 
	//         value = 0, if the given object is less than o
	//                    according to power rank conditions 
	@Override
	public int compareTo(RankComparator o) {
		 if(this.number_of_outrankedby < o.number_of_outrankedby)
			  return -1;
	  
	  else if(this.number_of_outrankedby==o.number_of_outrankedby
			  && (this.number_of_outranks > o.number_of_outranks))
			  return -1;
	  
	  else if(this.number_of_outrankedby==o.number_of_outrankedby
			  && (this.number_of_outranks == o.number_of_outranks)
			  && (this.non_losing_percent > o.non_losing_percent))
		  return -1;
	  
	  else if(this.number_of_outrankedby==o.number_of_outrankedby
			  && (this.number_of_outranks == o.number_of_outranks)
			  && (this.non_losing_percent ==o.non_losing_percent)
			  && (this.competitor.name().compareTo(o.competitor.name()) < 0))
		  return -1;
	  
	  else 
		  return 1;
	}

}
