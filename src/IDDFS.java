import java.util.Arrays;
import java.util.Stack;

public class IDDFS {
    //fields
    /*the frontier*/
    private final Stack<Node> frontier = new Stack<>();
    /*record the goal state of the puzzle for check.*/
    private final int[][] goal;
    private final String[] actions;
    /*record the number of nodes expanded.*/
    int NodesExpanded = 0;

    //constructor
    public IDDFS(int[][] goal, String[] actions) {
        this.goal = goal;
        this.actions = actions;
    }

    //this function checks if the current state matches the goal.
    private boolean check(int[][] state) {
        return Arrays.deepEquals(state, goal);
    }

    //this function checks if the node is already in the path.
    private boolean isCycle(Node node) {

        Node Cycle_check = node.parent;
        boolean flag;

        while (Cycle_check!=null) {
            flag = Cycle_check.hashState.equals(node.hashState);
            if (flag) return true;
            Cycle_check = Cycle_check.parent;
        }

        return false;
    }

    //this function is to expand from the current node.
    private void expand(Node node, int depth) {
        for (String action : actions) {
            if (node.findActions(action)) {
                Node child = new Node(node);
                child.moveTile(action);
                child.setHashState();
                child.track(action);
                if (child.Moves.length() <= depth + 1) frontier.push(child);
            }
        }
    }

    //this function is to search from the source state.//
    public Node search(Node node, int l) {
        /*initially, add the root node to frontier.*/
        frontier.add(node);

        while (!frontier.isEmpty()) {
            NodesExpanded++;
            Node current = frontier.pop();
            if (check(current.State)) return current;
            if (!isCycle(current)) {
                expand(current, l);
            }
        }

        return null;
    }

    //this function is to increment the depth for the Iterative-Deepening-Search
    public Node Deepening(Node node) {
        for (int depth = 0; true; depth++) {
            Node result = search(node, depth);
            if (result!=null) return result;
        }
    }
}
