import java.util.Arrays;
import java.util.Scanner;

public class Demo {

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

    public static void main(String[] args) {
       /*
        int[][] Org = {
                {1, 0, 2, 4},
                {5, 7, 3, 8},
                {9, 6, 11, 12},
                {13, 10, 14, 15}
        };
        */

        int[][] Org = InputPuzzle();

        System.out.println("Initial Puzzle State: ");
        for (int[] list : Org) {
            System.out.println(Arrays.toString(list));
        }

        int[][] Goal = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 0}
        };

        String[] Actions = {"LEFT", "RIGHT", "UP", "DOWN"};

        Node S1 = new Node(Org);
        BFS solver = new BFS(Goal, Actions);

        Long free_memory_before = Runtime.getRuntime().freeMemory();
        Long time_start = System.currentTimeMillis();
        Node S2 = new Node(solver.search(S1));

        Long time_end = System.currentTimeMillis();
        long time_used = time_end - time_start;

        Long free_memory_after = Runtime.getRuntime().freeMemory();
        long memory_used = free_memory_before - free_memory_after;

        System.out.println("Moves: " + S2.Moves);
        System.out.println("Number of Nodes expanded " + solver.NodesExpanded);
        System.out.println("Time Taken: " + time_used + "ms");
        System.out.println("Memory Used: " + (memory_used / 1024) + "kb");
    }
}
