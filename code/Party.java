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

    	// save the data
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(myBowlers);
		data.add(cumulScores);
		data.add(curScores);

		try {
			FileOutputStream fileOut = new FileOutputStream("data.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(data);
			out.close();
			fileOut.close();
			System.out.println("data saved");
		} catch(IOException i) {
			i.printStackTrace();
		}

		// extract the data
		ArrayList<Object> dataout = new ArrayList<Object>();

		try {
			FileInputStream fileIn  = new FileInputStream("data.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			dataout = (ArrayList<Object>)in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return;
		}

		// extract data from object
		 Vector mybowlernew = (Vector)dataout.get(0);
		 int[][] cumulnew = (int[][])dataout.get(1);
		 HashMap curnew = (HashMap)dataout.get(2);

		for(int i=0; i<mybowlernew.size();i++) {
			for(int j = 0;j<10;j++) {
				System.out.print(cumulnew[i][j]);
			}
		}
		System.out.println("");


	}

	public HashMap get_CurScores() {
    	return curScores;
	}

	public int[][] get_CumulScores() {
		return cumulScores;
	}

	public void print_scores(){

		for(int i=0; i<myBowlers.size();i++) {
			for(int j = 0;j<10;j++) {
				System.out.print(cumulScores[i][j]);
			}
		}
		System.out.println("");

	}


}
