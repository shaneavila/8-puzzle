package main;

import heuristics.HeuristicChoice;
import movements.MovementChoice;
import states.Board;
import states.Node;

import java.util.*;

public class Puzzle {

    private Board goalState;
    private HeuristicChoice heuristic;
    private Node root;
    private Queue<Node> frontier;
    private Map<Integer, Node> explored;
    private List<MovementChoice> movements;

    //TODO Implement PriorityQueue to make sure that ties are FIFO and not arbitrary
    public Puzzle(int[] start, int[] goal, HeuristicChoice heuristic) {
        Board startState = new Board(start);
        goalState = new Board(goal);
        this.heuristic = heuristic;
        root = new Node(startState, goalState, heuristic, null);
        frontier = new PriorityQueue<>();
        explored = new HashMap<>();
        movements = new ArrayList<>(Arrays.asList(MovementChoice.values()));
    }

    public Node solve() {
        frontier.add(root);

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            explored.put(current.hashCode(), current);

            if (isGoal(current)) {
                return current;
            }

            for (MovementChoice movement : movements) {
                Node next = new Node(movement.move(current.getBoard()), goalState, heuristic, current);
                if (!explored.containsKey(next.hashCode())) {
                    frontier.add(next);
                }
            }
        }
        return null;
    }

    private boolean isGoal(Node current) {
        return current.equals(goalState);
    }

    public Node getRoot() {
        return root;
    }
}
