import java.util.Arrays;
import java.util.Stack;
import java.util.HashSet;

public class DFS {
    //fields
    /*the frontier*/
    private final Stack<Node> frontier = new Stack<>();
    /*record the goal state of the puzzle for check.*/
    private final int[][] goal;
    private final String[] actions;
    /*Use a hashset to keep track of explored nodes and fast lookup.*/
    private final HashSet<String> reached = new HashSet<>();
    /*record the number of nodes expanded.*/
    int NodesExpanded = 0;
    /*record the number of states repeated.*/
    int Repeated = 0;

    //constructor
    public DFS(int[][] goal, String[] actions) {
        this.goal = goal;
        this.actions = actions;
    }

    //this function checks if the current state matches the goal.
    private boolean check(int[][] state) {
        return Arrays.deepEquals(state, goal);
    }

    //this function is to expand from the current node.
    private void expand(Node node) {
        if (node.Moves.length()>36)
            return;
        for (String action : actions) {
            if (node.findActions(action)) {
                Node child = new Node(node);
                child.moveTile(action);
                child.setHashState();
                child.track(action);
                /*check if the child's state is reached.*/
                if (!reached.contains(child.hashState)) {
                    frontier.push(child);
                }
                else
                    Repeated++;
            }
        }
    }

    //this function is to search from the source state.//
    public Node search(Node node) {
        /*initially, add the root node to frontier.*/
        frontier.add(node);
        /*initially, add the initial state to frontier.*/
        reached.add(node.hashState);

        while (!frontier.isEmpty()) {
            NodesExpanded++;
            Node current = frontier.pop();
            if (check(current.State)) {
                return current;
            }
            expand(current);
            reached.add(current.hashState);
        }
        return null;
    }
}
