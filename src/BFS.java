import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashSet;

public class BFS {
    //fields
    /*the frontier*/
    private final Queue<Node> frontier = new LinkedList<>();
    private final int[][] goal;
    private final String[] actions;
    private final HashSet<int[][]> reached = new HashSet<>();
    int NodesExpanded = 0;
    int Repeated = 0;
    /*the reached states should be stored in a hash table.*/

    //constructor
    public BFS(int[][] goal, String[] actions) {
        this.goal = goal;
        this.actions = actions;
    }

    //this function checks if the current state matches the goal.
    private boolean check(int[][] state) {
        return Arrays.deepEquals(state, goal);
    }

    //this function is to expand from the current node.
    private void expand(Node node) {
        for (String action : actions) {
            if (node.findActions(action)) {
                Node child = new Node(node);
                child.moveTile(action);
                child.track(action);
                if (!reached.contains(child.State)) {
                    Repeated++;
                    reached.add(child.State);
                    frontier.add(child);
                }
            }
        }
    }

    //this function is to search from the source state.//
    public Node search(Node node) {
        frontier.add(node);
        reached.add(node.State);

        while (!frontier.isEmpty()) {
            NodesExpanded++;
            Node current = frontier.poll();
            if (check(current.State)) {
                return current;
            }
            expand(current);
        }

        return null;
    }
}
