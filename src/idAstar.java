import java.math.BigInteger;
import java.util.*;

public class idAstar{

    //fields
    /*the frontier*/
    private final Stack<ANode> frontier= new Stack<>();

    protected int[][] goal;
    protected String[] actions;

    /*Using type to decide which heuristic function to use.*/
    protected int type;
    /*record the number of nodes expanded.*/
    int NodesExpanded = 0;
    /*record the number of states repeated.*/
    int Repeated = 0;
    /*store the bound number for idastar search.*/
    private int bound;
    private int min = 99999999;
    /*decide if cutoff happans.*/
    private boolean cutoff;

    //Constructor
    public idAstar(int[][] goal, String[] actions, int type) {
        this.goal = goal;
        this.actions = actions;
        this.type = type;
    }

    //this function checks if the current state matches the goal.
    protected boolean check(int[][] state) {
        return Arrays.deepEquals(state, goal);
    }

    //this function checks if the node is already in the path.
    private boolean isCycle(ANode node) {

        Node Cycle_check = node.parent;
        boolean flag;

        while (Cycle_check!=null) {
            flag = Cycle_check.hashState.equals(node.hashState);
            if (flag) return true;
            Cycle_check = Cycle_check.parent;
        }

        return false;
    }

    //this function is to expand the current node.
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

                if (child.f_n<=bound) frontier.add(child);
//                else if (!cutoff) {
//                    cutoff = true;
//                    bound = child.f_n;
//                }
                else {
                    cutoff = true;
                    if (child.f_n<min) min = child.f_n; /*min is the smallest f_n that is bigger than the current bound*/
                }
            }
        }
    }

    //this function is to search from the source node.
    public ANode search(ANode node) {
        /*initially, add the root node to frontier.*/
        frontier.add(node);
        min = 99999999;

        //System.out.println("bound number: " + bound);
        cutoff = false;
        //NodesExpanded = 0;
        while (!frontier.isEmpty()) {
            NodesExpanded++;
            ANode current = frontier.pop();

            if (check(current.State)) {
                cutoff = false;
                return current;
            }

            if (!isCycle(current)) {
                expand(current);
            }
        }

        //System.out.println("bound number: " + bound);
        bound = min;
        return null;
    }

    //this function is to increment the 'bound' or 'depth' of the Search.
    public ANode deepening(ANode node) {
        /*decide the heuristic function.*/
        if (type == 1) node.heuristic1();
        else node.heuristic2();
        bound = node.h_n;

        ANode result;

        do {
            result = search(node);
        } while(cutoff); /*when the cutoff happens, go to the next search.*/

        return result;
    }
}
