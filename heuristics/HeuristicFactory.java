package heuristics;

public enum HeuristicFactory implements Heuristic {

    DISTANCE {
        @Override
        public void calculate() {
            System.out.println("DISTANCE");
        }
    },
    MISPLACED {
        @Override
        public void calculate() {
            System.out.println("MISPLACED");
        }
    };

}
