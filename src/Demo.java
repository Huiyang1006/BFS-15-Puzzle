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
       /*
        int[][] Org = {
                {1, 0, 2, 4},
                {5, 7, 3, 8},
                {9, 6, 11, 12},
                {13, 10, 14, 15}
        };
        */

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

        /*create the root node with the inputed initial state.*/
        Node S1 = new Node(Org);
        BFS solver = new BFS(Goal, Actions);

        /*start collecting memory and time information.*/
        Long free_memory_before = Runtime.getRuntime().freeMemory();
        Long time_start = System.currentTimeMillis();
        /*step into the BFS.*/
        Node S2 = new Node(solver.search(S1));
        /*BFS ended, record the current time and memory information.*/
        Long time_end = System.currentTimeMillis();
        long time_used = time_end - time_start;
        /*calculate the used memory space and time.*/
        Long free_memory_after = Runtime.getRuntime().freeMemory();
        long memory_used = free_memory_before - free_memory_after;

        /*output the information collected.*/
        System.out.println("Moves: " + S2.Moves);
        System.out.println("Number of Nodes expanded " + solver.NodesExpanded);
        System.out.println("Time Taken: " + time_used + "ms");
        System.out.println("Memory Used: " + (memory_used / 1024) + "kb");
        System.out.println("Number of states repeated " + solver.Repeated);

    }
}
