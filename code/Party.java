/*
 * Party.java
 *
 * Version:
 *   $Id$
 *
 * Revisions:
 *   $Log: Party.java,v $
 *   Revision 1.3  2003/02/09 21:21:31  ???
 *   Added lots of comments
 *
 *   Revision 1.2  2003/01/12 22:23:32  ???
 *   *** empty log message ***
 *
 *   Revision 1.1  2003/01/12 19:09:12  ???
 *   Adding Party, Lane, Bowler, and Alley.
 *
 */

/**
 *  Container that holds bowlers
 *
 */

import java.util.*;

public class Party {

	/** Vector of bowlers in this party */	
    private Vector myBowlers;
	private HashMap curScores;
	private int[][] cumulScores;
	
	/**
	 * Constructor for a Party
	 * 
	 * @param bowlers	Vector of bowlers that are in this party
	 */
		
    public Party( Vector bowlers ) {
		myBowlers = new Vector(bowlers);
    }

	/**
	 * Accessor for members in this party
	 * 
	 * @return 	A vector of the bowlers in this party
	 */

    public Vector getMembers() {
		return (Vector) myBowlers.clone();
    }

    public void set_scores(int[][] cumul, HashMap cur){
    	cumulScores = cumul.clone();
    	curScores = (HashMap) cur.clone();
	}

	public HashMap get_CurScores() {
    	return curScores;
	}

	public int[][] get_CumulScores() {
		return cumulScores;
	}

	public void print_scores() {
		for(int i=0; i<myBowlers.size();i++) {
			for(int j = 0;j<10;j++) {
				System.out.print(cumulScores[i][j]);
			}
		}
	}


}
