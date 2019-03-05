package heuristics;

public enum HeuristicChoice implements Heuristic {
    DISTANCE {
        @Override
        public int calculate(int[][] start, int[][] goal) {
            return manhattan(start, goal);
        }

        private int manhattan(int[][] start, int[][] goal) {
            int distance = 0;
            for (int i = 0; i < start.length; i++) {
                for(int j = 0; j < start[i].length; j++) {
                    for (int k = 0; k < goal.length; k++) {
                        for(int l = 0; l < goal[k].length; l++) {
                            if(start[i][j] == goal[k][l] && start[i][j] != 0) {
                                distance += (Math.abs(i-k) + Math.abs(j-l));
                            }
                        }
                    }
                }
            }
            return distance;
        }
    },
    MISPLACED {
        @Override
        public int calculate(int[][] start, int[][] goal) {
            return misplaced(start, goal);
        }

        private int misplaced(int[][] start, int[][] goal) {
            int count = 0;
            for(int i = 0; i < start.length; i++)
                for(int j = 0; j < start[i].length; j++)
                    if(start[i][j] != goal[i][j] && start[i][j] != 0)
                        count++;
            return count;
        }
    }
}
