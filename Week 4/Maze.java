/* Name : Prithiv Dev Devendran
 * CWID : 10453922
 * CS 570: Homework Assignment 4
*/

package Maze;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    
    
    public boolean findMazePath(int x, int y) {
        //PROBLEM 1
       if (x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows()) {
            return false;

        } else if (!maze.getColor(x, y).equals(NON_BACKGROUND)) {
            return false;

        } else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
            maze.recolor(x, y, PATH);
            return true;

        } else {
            maze.recolor(x, y, PATH);

            if (findMazePath(x - 1, y) || findMazePath(x + 1, y) ||
            findMazePath(x, y + 1) || findMazePath(x, y -1)) {
                return true;

            } else {
                maze.recolor(x, y, TEMPORARY);
                return false;
            }
        }
    
    }
    

    

    //PROBLEM 2 
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {

        ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
        Stack<PairInt> trace = new Stack<>();
        findMazePathStackBased(0, 0, result, trace);

        return result;
    }

    //The Helper Method
    public void findMazePathStackBased(int x, int y,
            ArrayList<ArrayList<PairInt>> result,
            Stack<PairInt> trace)
    {
    	//check for OutOfBoundsException
    	int xmaze=maze.getNCols()-1;
    	int ymaze= maze.getNRows()-1;
    	int state=0;
    	
        if (y < 0 || x < 0 || x > xmaze || y > ymaze||
        (!maze.getColor(x, y).equals(NON_BACKGROUND)))
        {state=0;} 
        else if (x == xmaze && y == ymaze) 
        {state=1; } 
        else 
        {state=2;}
        
        
        switch(state) {
        case 0:
          break;
        case 1:
        	//check for an exit
        	trace.push(new PairInt(x, y));
            ArrayList<PairInt> base = new ArrayList<>(trace);
            //get the list
            result.add(base);
            //pop
            trace.pop();
            //recolor
            maze.recolor(x, y, NON_BACKGROUND); 
          break;
        case 2:
        	//recursion
            trace.push(new PairInt(x, y));
            //recolor
            maze.recolor(x, y, PATH); 
            //recursion
            findMazePathStackBased(x, y + 1, result, trace);
            findMazePathStackBased(x, y - 1, result, trace);
            findMazePathStackBased(x + 1, y, result, trace);
            findMazePathStackBased(x - 1, y, result, trace);
            //recoloring
            maze.recolor(x, y, NON_BACKGROUND);
            trace.pop();
          break;
        default:
        	break;
      }
        
        
    }
    
      
    //PROBLEM 3
    public ArrayList<PairInt> findMazePathMin(int x, int y) 
    {
    	int ticker = 0;
    	//cc
    	int[] counterTicker;
    	ArrayList <ArrayList <PairInt>> pathsAll = findAllMazePaths(x, y);
    	int pathsize=pathsAll.size();
    	int LenghtTracker = pathsAll.get(0).size();
    	//cc
    	counterTicker = new int[pathsAll.size()];
    	
    	//checking all the paths 
    	for(int k = 0; k<pathsize; k++) {
    		if(LenghtTracker > pathsAll.get(k).size()) {
    			LenghtTracker = pathsAll.get(k).size();
    			pathsize=pathsAll.size();
    			//counter checker
    			counterTicker[k] = pathsAll.get(k).size();
    			//mark
    			ticker = k;
    		}
    	}
    	
    	int psizefinal = pathsAll.get(ticker).size();
    	if(psizefinal == 0) {
    		System.out.print("Out of bounds");
    	}
    	
    	return pathsAll.get(ticker);
    }

    

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
