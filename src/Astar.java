import java.util.*;

public class Astar {

    //fields
    /*the frontier*/
    private final Queue<ANode> frontier= new PriorityQueue<>();
    /*record the goal state of the puzzle for check.*/
    protected int[][] goal;
    protected String[] actions;
    /*Use a hashMap to keep track of explored nodes and f_n and fast lookup.*/
    protected Map<String, Integer> reached = new HashMap<>();
    /*Using type to decide which heuristic function to use.*/
    protected int type;
    /*record the number of nodes expanded.*/
    int NodesExpanded = 0;
    /*record the number of states repeated.*/
    int Repeated = 0;

    //constructor
    public Astar(int[][] goal, String[] actions, int type) {
        this.goal = goal;
        this.actions = actions;
        this.type = type;
    }

    //this function checks if the current state matches the goal.
    protected boolean check(int[][] state) {
        return Arrays.deepEquals(state, goal);
    }

    //this function is to expand from the current node.
    private void expand(ANode node) {
        for (String action : actions) {
            if (node.findActions(action)) {
                ANode child = new ANode(node);
                child.moveTile(action);
                child.setHashState();
                child.track(action);
                if (type == 1) child.heuristic1();
                else child.heuristic2();
                child.f_n = child.g_n + child.h_n;

                /*check if the child's state is reached.*/
                if (!reached.containsKey(child.hashState) || reached.get(child.hashState)> child.f_n) {
                    reached.put(child.hashState, child.f_n);
                    frontier.offer(child);
                }
                else
                    Repeated++;
            }
        }
    }

    //this function is to search from the source node.
    public ANode search(ANode node) {

        /*initialize the cost.*/
        if (type == 1) node.heuristic1();
        else node.heuristic2();
        /*initially, add the root node to frontier.*/
        frontier.offer(node);
        /*initially, add the initial state to frontier.*/
        reached.put(node.hashState, node.h_n);

        while (!frontier.isEmpty()) {
            NodesExpanded++;
            ANode current = frontier.poll();
            if (check(current.State)) {
                return current;
            }
            expand(current);
        }

        return null;
    }

}