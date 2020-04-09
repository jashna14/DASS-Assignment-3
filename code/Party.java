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

import java.io.*;
import java.util.*;

public class Party implements Serializable {

	/** Vector of bowlers in this party */
	public boolean isExisting;
    private Vector myBowlers;
	public HashMap Scores;
	public int[][] cumulScores;
	public boolean gameFinished;
	public int frameNumber;
	public int gameNumber;
	public boolean tenthFrameStrike;
	public int[][] finalScores;
	public int[] curScores;
	
	/**
	 * Constructor for a Party
	 * 
	 * @param bowlers	Vector of bowlers that are in this party
	 */
		
    public Party( Vector bowlers ) {
		myBowlers = new Vector(bowlers);
		isExisting = false;
    }

	/**
	 * Accessor for members in this party
	 * 
	 * @return 	A vector of the bowlers in this party
	 */

    public Vector getMembers() {
		return (Vector) myBowlers.clone();
    }

    public void set_scores(int[][] cumul, HashMap cur , boolean gamefinished , int framenumber , boolean tenthframestrike , int gamenumber , int[][] finalscores , int[] curscores){
    	cumulScores = cumul.clone();
    	Scores = (HashMap) cur.clone();
    	gameFinished = gamefinished;
    	frameNumber = framenumber;
    	tenthFrameStrike = tenthframestrike;
    	gameNumber = gamenumber;
    	finalScores = finalscores.clone();
    	curScores = curscores;

		ExistingGameData data = new ExistingGameData();
		ArrayList<Object> dataout = (ArrayList<Object>) data.get_data();
//		ArrayList<Object> dataout = new ArrayList<Object>();
		System.out.println(dataout.size());

		ArrayList<Object> datasub = new ArrayList<Object>();

		datasub.add(myBowlers);
		datasub.add(cumulScores);
		datasub.add(Scores);
		datasub.add(gameFinished);
		datasub.add(frameNumber);
		datasub.add(tenthFrameStrike);
		datasub.add(gameNumber);
		datasub.add(finalScores);
		datasub.add(curScores);

		dataout.add(datasub);

		data.set_data(dataout);
	}

}
