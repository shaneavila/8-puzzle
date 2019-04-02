package heuristics;

public enum HeuristicChoice implements Heuristic {
    MANHATTAN {
        @Override
        public int calculate(int[] start, int[] goal) {
            return manhattan(start, goal);
        }

        private int manhattan(int[] start, int[] goal) {
            int squareLength = (int) Math.sqrt(start.length);
            int distance = 0;
            for (int i = 0; i < start.length; i++) {
                if (start[i] != goal[i] && start[i] != 0)
                    distance += (Math.abs(start[i] / squareLength - goal[i] / squareLength)
                            + Math.abs(start[i] % squareLength - goal[i] % squareLength));
            }
            return distance;
        }
    },
    MISPLACED {
        @Override
        public int calculate(int[] start, int[] goal) {
            return misplaced(start, goal);
        }

        private int misplaced(int[] start, int[] goal) {
            int count = 0;
            for (int i = 0; i < start.length; i++)
                if (start[i] != goal[i] && start[i] != 0)
                    count++;
            return count;
        }
    }
}
