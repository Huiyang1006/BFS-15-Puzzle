import java.util.Arrays;

public class FifteenPuzzle {

	private final int [][] fifteenpuzzle;
	public String moves ="";
	private int [][] goalpuzzle;
	private int zerotileXvalue,zerotileYvalue;
	
	//Constructor
	public FifteenPuzzle(FifteenPuzzle finalpuzzle) {	
		fifteenpuzzle=new int[finalpuzzle.fifteenpuzzle.length][finalpuzzle.fifteenpuzzle[0].length];
		for(int x=0;x<fifteenpuzzle.length;x++) {
			fifteenpuzzle[x]= Arrays.copyOf(finalpuzzle.fifteenpuzzle[x],fifteenpuzzle[x].length);
		}
		zerotileXvalue=finalpuzzle.zerotileXvalue;
		goalpuzzle =finalpuzzle.goalpuzzle;
		zerotileYvalue=finalpuzzle.zerotileYvalue;
		moves=finalpuzzle.moves;
	}
	
	//Constructor
	public FifteenPuzzle(int [][] fifteenpuzzle, int [][] goalpuzzle) {
		this.fifteenpuzzle=fifteenpuzzle;
		this.goalpuzzle=goalpuzzle;
		searchTileWithZero();
	}
	
	/*(Method Description)
	 * This function returns true if the blank tile (0) can be moved right,left,up,down.*/
	public boolean couldMove(String action) {
		if (action.equals("DOWN")) {
			if(zerotileYvalue!=fifteenpuzzle.length-1) {
				return true;
			}	
		}
		else if(action.equals("UP")) {
			if(zerotileYvalue!=0) {
				return true;
			}
		}
		else if(action.equals("RIGHT")) {
			if(zerotileXvalue!=fifteenpuzzle[zerotileYvalue].length-1){
				return true;
			}
		}
		else {
			if(zerotileXvalue!=0) {
				return true;
			}
		}
		return false;	
	}
	
	/*(Method Description)
	 * This function finds the position of the blank tile (0) in the 2D array.*/
	private void searchTileWithZero() {
		for(int j=0;j<fifteenpuzzle.length;++j) {
			for(int i=0;i<fifteenpuzzle[j].length;++i) {
				if(fifteenpuzzle[j][i] == 0) {
					zerotileXvalue=i;
					zerotileYvalue=j;
				}
			}
		}
		
	}
	
	/*(Method Description)
	 * This function moves the blank tile (0) either right,left,up or down.*/
	public void moveTile(String action) {
		if(action.equals("DOWN")) {
			shuffle(zerotileYvalue,zerotileXvalue,(zerotileYvalue+1),zerotileXvalue);
			moves=moves+"D";	
		}
		else if(action.equals("RIGHT")) {
			shuffle(zerotileYvalue,zerotileXvalue,zerotileYvalue,(zerotileXvalue+1));
			moves=moves +"R";	
		}
		else if(action.equals("UP")) {
			shuffle(zerotileYvalue,zerotileXvalue,(zerotileYvalue-1),zerotileXvalue);
			moves=moves+"U";	
		}
		else {
			shuffle(zerotileYvalue,zerotileXvalue,zerotileYvalue,(zerotileXvalue-1));
			moves=moves+"L";	
		}
	}
	
	/*(Method Description)
	 * This function swaps a number with the blank tile (0) 
	 * depending on the action selected:right,left,up,down.*/
	private void shuffle(int a, int b, int c, int d) {
		int lastposition=getPositionTile(a,b);
		setPositionTile(a,b,getPositionTile(c, d));
		setPositionTile(c, d, lastposition);
		zerotileXvalue=d;
		zerotileYvalue=c;	
	}
	
	/*(Method Description)
	 * This function is used to check if the goal state is reached.*/	
	public boolean check() {
		for(int j=0;j<fifteenpuzzle.length;++j) {
			for(int i=0;i<fifteenpuzzle[j].length;++i) {
				if(fifteenpuzzle[j][i]!=goalpuzzle[j][i]) {
					return false;
				}
			}
		}
		return true;
	}
	
	private void setPositionTile(int a, int b, int positionTile) {
		fifteenpuzzle[a][b]=positionTile;	
	}
	
	private int getPositionTile(int a, int b) {
		return fifteenpuzzle[a][b];
	}
	
	/*(Method Description)
	 * This function returns the moves taken to reach the goal state.*/
	public String getmoves() {
		return moves;
	}
		
}
