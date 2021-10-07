import java.lang.Math;

public class ANode extends Node implements Comparable<ANode>{

    /*the value of g(n), h(n) and f(n)*/
    int g_n;
    int h_n;
    int f_n;
    /*save the goal state*/
    int [][] goal;

    //constructor - regular
    public ANode(ANode TN) {
        super(TN);
        g_n = TN.g_n + 1;
        goal = TN.goal;
    }

    //constructor - Initial
    public ANode(int[][] state, int[][] Goal) {
        super(state);
        g_n = 0;
        goal = Goal;
    }

    //this function calculates h(n) basing on number of misplaced tiles.
    void heuristic1() {
        h_n = 0;
        for (int j = 0; j < ROW; j++) {
            for (int i = 0; i < COLUMN; i++) {
                if(State[i][j] != goal[i][j]) h_n++;
            }
        }
    }

    //this function calculates h(n) basing on manhattan distance.
    void heuristic2() {
        h_n = 0;
        for (int j = 0; j < ROW; j++) {
            for (int i = 0; i < COLUMN; i++) {
                h_n = h_n + distance(goal[j][i], j, i);
            }
        }
    }

    //this function calculates the tile's distance from goal.
    int distance(int num, int Y, int X) {
        int position_X, position_Y;

        for (position_Y = 0; position_Y < ROW; position_Y++) {
            for (position_X = 0; position_X < COLUMN; position_X++) {
                if (State[position_Y][position_X] == num) {
                    return Math.abs(X - position_X) + Math.abs(Y - position_Y);
                }
            }
        }

        return 99;
    }

    /*this is to implement comparable.*/
    @Override
    public int compareTo(ANode T) {
        return Integer.compare(this.f_n, T.f_n);
    }
}
