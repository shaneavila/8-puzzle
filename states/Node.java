package states;

import heuristics.Heuristic;

public class Node implements Comparable<Node>{

    private Board start;
    private Board goal;
    private Heuristic heuristic;
    private Node parent;

    public Node(Board start, Board goal, Heuristic heuristic, Node parent) {
        this.start = start;
        this.goal = goal;
        this.heuristic = heuristic;
        this.parent = parent;
    }

    public int calcCost() {
        return getCost() + heuristic.calculate(start.getBoard(), goal.getBoard());
    }

    public int getCost() {
        if (parent == null)
            return 0;
        return parent.getCost() + 1;
    }

    public Node getRoot() {
        if (parent.parent == null)
            return parent;
        return parent.getRoot();
    }

    public void getPath(Node solution) {
        System.out.println(solution);
        if (solution.parent == null)
            return;
        parent.getPath(solution.parent);
    }

    public Board getBoard() {
        return start;
    }

    @Override
    public int compareTo(Node o) {
        int current = calcCost();
        int temp = o.calcCost();
        return current - temp;
    }

    @Override
    public int hashCode() {
        return start.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Board))
            return false;
        Board board = (Board) obj;
        return hashCode() == board.hashCode();
    }

    @Override
    public String toString() {
        int[][] start = this.start.getBoard();
        int[][] goal = this.goal.getBoard();
        String output = " start    end\n";
        for(int i = 0; i < start.length; i++) {
            output += "[";
            for(int j = 0; j < start.length; j++) {
                if(j != start.length - 1)
                    output += start[i][j] + " ";
                else
                    output += start[i][j];
            }
            output += "] ";
            output += "[";
            for(int j = 0; j < goal.length; j++) {
                if(j != goal.length - 1)
                    output += goal[i][j] + " ";
                else
                    output += goal[i][j];
            }
            output += "]\n";
        }
        output += "cost = " + getCost() + " + " + heuristic.calculate(start,goal) + " = " + calcCost() + "\n";
        return output;
    }

}
