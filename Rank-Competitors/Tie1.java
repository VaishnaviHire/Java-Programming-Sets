// Constructor template for Tie1:
//     new Tie1 (Competitor c1, Competitor c2)
// Interpretation:
//     c1 and c2 have engaged in a contest that ended in a tie

public class Tie1 implements Outcome {

    // You should define your fields here.
	
	Competitor c1;
	Competitor c2;
	Outcome o;

    Tie1 (Competitor c1, Competitor c2) {

    	this.c1 = c1;
    	this.c2 = c2;
        // Your code goes here.

    }
    // GIVEN: no arguments
    // RETURNS: true iff this outcome represents a tie

    public boolean isTie () {
   
    		return true;

    }
    
    // GIVEN: no arguments
    // RETURNS: one of the competitors

    public Competitor first () {
    	
    	return c1;


    }
    // GIVEN: no arguments
    // RETURNS: the other competitor

    public Competitor second () {

    	return c2;

    }

    // GIVEN: no arguments
    // WHERE: this.isTie() is false
    // RETURNS: the winner of this outcome

    public Competitor winner () {
    	throw new UnsupportedOperationException();

        // Your code goes here.

    }

    // GIVEN: no arguments
    // WHERE: this.isTie() is false
    // RETURNS: the loser of this outcome

    public Competitor loser () {
    	throw new UnsupportedOperationException();
        // Your code goes here.

    }
}
