import java.util.Queue;
import java.util.LinkedList;


public class Solver {

	private final Queue<FifteenPuzzle> openlist=new LinkedList<>();
	int nodeSearched=0;
	
	/*(Method Description)
	 * This function returns the solved puzzle state (2D array) 
	 * and the number of nodes expanded according to BFS logic*/
	public FifteenPuzzle findsolution(FifteenPuzzle fifteenpuzzle,String [] actions) {
		int counter=0;
		openlist.add(fifteenpuzzle);
		while(!openlist.isEmpty()) {
			FifteenPuzzle finalpuzzle =openlist.poll();
			counter++;
			if(finalpuzzle.check()) {
				setnodeSearched(counter);
				return finalpuzzle;
			}
			for(int x=0; x < actions.length;x++) {
				if(finalpuzzle.couldMove(actions[x])) {
					FifteenPuzzle newPuzzleToTest = new FifteenPuzzle(finalpuzzle);
					newPuzzleToTest.moveTile(actions[x]);
					openlist.add(newPuzzleToTest);
				}
			}
		}
		return null;
	}
	
	public void setnodeSearched(int counter) {
		nodeSearched=counter;
	}
	
	/*(Method Description)
	 * This function returns the number of nodes expanded during BFS in search of the goal state*/
	public int getnodeSearched() {
		return nodeSearched;
	}
}
