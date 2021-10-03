import java.util.Arrays;
import java.util.Scanner;

public class Demo {

    //this function collect the input information and transform it into an array.
    private static int[][] InputPuzzle() {
        int[][] PuzzleArray = new int[4][4];

        Scanner puzzle = new Scanner(System.in);
        System.out.println("Please enter the numbers of the puzzle one by one, split with blank: ");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                PuzzleArray[i][j] = puzzle.nextInt();
            }
        }
        puzzle.close();
        return PuzzleArray;
    }

    //this is the main function.
    public static void main(String[] args) {

        /*get the initial state Org*/
        int[][] Org = InputPuzzle();

        System.out.println("Initial Puzzle State: ");
        for (int[] list : Org) {
            System.out.println(Arrays.toString(list));
        }

        /*the puzzle is solved when numbers are arranged as below.*/
        int[][] Goal = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 0}
        };

        /*actions are defined in terms of directions where the empty square can be moved to.*/
        String[] Actions = {"LEFT", "RIGHT", "UP", "DOWN"};

        /*create the root node with the inputted initial state.*/
        Node S1 = new Node(Org);
//        BFS solver1 = new BFS(Goal, Actions);
//        DFS solver2 = new DFS(Goal, Actions);
        IDDFS solver3 = new IDDFS(Goal, Actions);

//        /*start collecting memory and time information.*/
//        Long BFS_free_memory_before = Runtime.getRuntime().freeMemory();
//        Long BFS_time_start = System.currentTimeMillis();
//        /*step into the BFS.*/
//        Node S2 = new Node(solver1.search(S1));
//        /*BFS ended, record the current time and memory information.*/
//        Long BFS_time_end = System.currentTimeMillis();
//        long BFS_time_used = BFS_time_end - BFS_time_start;
//        /*calculate the used memory space and time.*/
//        Long BFS_free_memory_after = Runtime.getRuntime().freeMemory();
//        long BFS_memory_used = BFS_free_memory_before - BFS_free_memory_after;
//
//        /*output the information collected.*/
//        System.out.println("__________________BFS__________________");
//        System.out.println("Moves: " + S2.Moves);
//        System.out.println("Number of Nodes expanded: " + solver1.NodesExpanded);
//        System.out.println("Time Taken: " + BFS_time_used + "ms");
//        System.out.println("Memory Used: " + (BFS_memory_used / 1024) + "kb");
//        System.out.println("Number of states repeated: " + solver1.Repeated);

        /*start collecting memory and time information.*/
        long IDS_free_memory_before = Runtime.getRuntime().freeMemory();
        Long IDS_time_start = System.currentTimeMillis();
        /*step into the IDDFS.*/
        Node S3 = new Node(solver3.Deepening(S1));
        /*IDDFS ended, record the current time and memory information.*/
        Long IDS_time_end = System.currentTimeMillis();
        long IDS_time_used = IDS_time_end - IDS_time_start;
        /*calculate the used memory space and time.*/
        long IDS_free_memory_after = Runtime.getRuntime().freeMemory();
        long IDS_memory_used = IDS_free_memory_before - IDS_free_memory_after;

        /*output the information collected.*/
        System.out.println("__________________IDS__________________");
        System.out.println("Moves: " + S3.Moves);
        System.out.println("Number of Nodes expanded: " + solver3.NodesExpanded);
        System.out.println("Time Taken: " + IDS_time_used + "ms");
        System.out.println("Memory Used: " + (IDS_memory_used / 1024) + "kb");
    }
}
