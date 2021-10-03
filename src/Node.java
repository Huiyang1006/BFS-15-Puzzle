public class Node {
    //fields
    final int[][] State;
    /*A pointer to the node's parent*/
    final Node parent;
    /*transform the State to a string for comparing with reached*/
    String hashState = "";
    /*position of the blank tile*/
    int Agent_X;
    int Agent_Y;
    /*track the path*/
    String Moves = "";
    /*Rows and Columns*/
    public final static int ROW = 4;
    public final static int COLUMN = 4;

    //constructor - regular
    public Node(Node TN) {
        parent = TN;
        State = new int[ROW][];
        /*can not directly use clone function because it copies the reference.*/
        for (int i = 0; i < ROW; i++) {
            /*clone every list of the array.*/
            State[i] = TN.State[i].clone();
        }
        Moves = TN.Moves;
        find0();
    }

    //constructor - initial
    public Node(int[][] state) {
        State = state;
        parent = null;
        find0();
        setHashState();
    }

    //this function finds the position of the blank
    void find0() {
        for (int j = 0; j < ROW; j++) {
            for (int i = 0; i < COLUMN; i++) {
                if (State[i][j] == 0) {
                    Agent_X = j;
                    Agent_Y = i;
                    break;
                }
            }
        }
    }

    //this function create a String for the node, to serve as a key value.
    void setHashState() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j<COLUMN; j++) {
                hashState = hashState + Integer.toHexString(State[i][j]);
            }
        }
    }

    //this function checks if the blank can be moved in response to different actions.
    boolean findActions(String Action) {
        switch (Action) {
            case "LEFT":
                if (Agent_X != 0)
                    return true;
                break;
            case "RIGHT":
                if (Agent_X != State[0].length - 1)
                    return true;
                break;
            case "UP":
                if (Agent_Y != 0)
                    return true;
                break;
            case "DOWN":
                if (Agent_Y != State.length - 1)
                    return true;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + Action);
        }
        return false;
    }

    //this function add an action to Moves
    void track(String Action) {
        switch (Action) {
            case "LEFT" -> Moves = Moves + "L";
            case "RIGHT" -> Moves = Moves + "R";
            case "UP" -> Moves = Moves + "U";
            case "DOWN" -> Moves = Moves + "D";
        }
    }

    //this function moves the blank tile. Only use this function after a new node is created.
    void moveTile(String Action) {
        int temp;
        switch (Action) {
            case "LEFT" -> {
                temp = State[Agent_Y][Agent_X];
                State[Agent_Y][Agent_X] = State[Agent_Y][Agent_X - 1];
                State[Agent_Y][Agent_X - 1] = temp;
                find0();
            }
            case "RIGHT" -> {
                temp = State[Agent_Y][Agent_X];
                State[Agent_Y][Agent_X] = State[Agent_Y][Agent_X + 1];
                State[Agent_Y][Agent_X + 1] = temp;
                find0();
            }
            case "UP" -> {
                temp = State[Agent_Y][Agent_X];
                State[Agent_Y][Agent_X] = State[Agent_Y - 1][Agent_X];
                State[Agent_Y - 1][Agent_X] = temp;
                find0();
            }
            case "DOWN" -> {
                temp = State[Agent_Y][Agent_X];
                State[Agent_Y][Agent_X] = State[Agent_Y + 1][Agent_X];
                State[Agent_Y + 1][Agent_X] = temp;
                find0();
            }
        }
    }
}
