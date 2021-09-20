import java.util.Scanner;

public class MainPuzzle {
	
	/*(Method Description)
	 * This function asks user to load the puzzle(2D array) with initial state.*/
	private static int [][] fillPuzzleMatrix() {
		int [][] data=new int[4][4];
		Scanner input= new Scanner(System.in);
		System.out.println("Please Enter the Numbers in the Puzzle");
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				data[i][j]=input.nextInt();
			}
		}
		input.close();
		return data;
	}
	
	/*(Method Description)
	 * This function loads the 2D array with goal state.*/ 
	private static int [][] fillCorrectMatrix() {
		int [][] data=new int[4][4];
		int flag=1;
		for(int i=0;i<4;++i) {
			for(int j=0;j<4;++j) {
				data[i][j]=flag;
				flag++;
			}
		}
		data[3][3]=0;
		return data;
	}
	
	/*(Method Description)
	 * This function prints the initial state of puzzle entered by the user.*/
	private static void printMatrix(int [][] matrix,int row,int col) {
		for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                System.out.print(matrix[i][j]+"\t");
            }             
            System.out.println();
        }	
	}
		
	public static void main(String[] args)  {
		
		int [][] initialstate=new int[4][4]; //2D integer array to store the initial state of puzzle. 
		int [][] goalstate=new int[4][4];    //2D integer array to store the goal state of puzzle which will be used as a reference. 
		int  nodeExpanded=0;
		FifteenPuzzle fifteenPuzzle;
		initialstate=MainPuzzle.fillPuzzleMatrix();
		goalstate=MainPuzzle.fillCorrectMatrix();
		System.out.println("Initial Puzzle State:");
		MainPuzzle.printMatrix(initialstate, 4, 4);
		fifteenPuzzle=new FifteenPuzzle(initialstate, goalstate);
		Solver bfsSolver=new Solver();
		String [] actions= {"RIGHT","DOWN","UP","LEFT"}; //Possible moves for the blank tile
		Long memUsedBefore= Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		Long starttime=System.currentTimeMillis();
		try {
		FifteenPuzzle solved=bfsSolver.findsolution(fifteenPuzzle, actions);
		Long endtime=System.currentTimeMillis();
		Long memUsedAfter= Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		Long memoryUsedActual=memUsedAfter-memUsedBefore;
		nodeExpanded=bfsSolver.getnodeSearched();
		System.out.println("After Breadth-First Search:");
		System.out.println("Moves:"+solved.getmoves());
		System.out.println("Number of Nodes Expanded:"+nodeExpanded);
		System.out.println("Time taken:"+(endtime-starttime+"ms"));
		System.out.println("Memory Used:"+memoryUsedActual/1024+"KB");
		}
		catch(OutOfMemoryError e){
			System.out.println("No Solution found for the Entered Puzzle");
		}
		
	}
	
}
