
import java.util.HashMap;
import java.util.Random;

public class RandomRestartWithoutSideways {

	static int count = 1;
	static int steps = 0;

	public static void printStatistics(int iterations) {
		float avgRestart = ((float) count / (float) iterations);
		float avgsteps = ((float) steps / (float) iterations);
		System.out.println("The average number of restarts:  " + (avgRestart));
		System.out.println("The average number of steps:  " + avgsteps);
	}

	public static void climb(int[] queens) {

		int c, j, N = queens.length;
		int[] temp = new int[N];
		int[][] mat = new int[N][N];
		int[] next = new int[N];
		Board.printBoard(queens, N);
		int bestSoFar = Board.getHeuristic(queens);
		HashMap<Integer, int[]> map = new HashMap<Integer, int[]>();
		mat = Board.getHMatrix(queens);
		int min = mat[0][0];
		for (c = 0; c < N; c++) {
			for (int r = 0; r < N; r++) {
				if (min > mat[c][r]) {
					min = mat[c][r];
				}
			}
		}
		int count = 1;
		for (c = 0; c < N; c++) {
			for (j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					temp[k] = queens[k];
				}
				if (mat[c][j] == min) {
					for (int k = 0; k < N; k++) {
						next[k] = temp[k];
					}
					next[j] = c;
					map.put(count, next);
					count++;
				}
			}
		}
		Random rand = new Random();
		int index = rand.nextInt(count);
		if (index == 0) {
			index = index + 1;
		}
		temp = map.get(index);

		if (min == 0) {
			System.out.println("Solution Found");
			Board.printBoard(temp, temp.length);
			System.out.println("Conflicts: " + Board.getHeuristic(temp));
			steps++;
		} else if (min < bestSoFar) {
			steps++;
			climb(temp);
		} else if (min == bestSoFar) {
			System.out.println("Doing random restart.");
			count++;
			climb(Board.createBoard(N));

		}

	}

}
