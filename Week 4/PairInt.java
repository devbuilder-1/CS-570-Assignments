/* Name : Prithiv Dev Devendran
 * CWID : 10453922
 * CS 570: Homework Assignment 4
 */

package Maze;

public class PairInt {

   
    private int x;
    private int y;


    public PairInt(int x, int y) {
    	//calls the superclass
        this.x = x;
        this.y = y;

    }

    //getter(X)
    public int getX() {
        return x;
    }

    //getter(Y)
    public int getY() {
        return y;
    }

    //setter(X)
    public void setX(int x) {
    	//calls the superclass
        this.x = x;
    }

    //setter(Y)
    public void setY(int y) {
    	//calls the superclass
        this.y = y;
    }


    @Override
    public boolean equals(Object p) {
        if (p == null) {
            return false;
        }

        PairInt pInt = (PairInt) p;
        return (this.x == pInt.x && this.y == pInt.y);

    }

    @Override
    public String toString() {

        return "(" + x + ", " + y + ")";
    }

    public PairInt copy() {
        return new PairInt(x, y);

    }
}