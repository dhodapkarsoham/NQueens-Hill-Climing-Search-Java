import java.util.HashMap;
import java.util.Random;

public class SteepestAscentHillClimbing {

	static int success = 0;
	static int steps = 0;
	static int successSteps = 0;
	static int failSteps = 0;

	public static void printStatistics(int iterations) {
		float successRate;
		successRate = (float) success * 100 / (float) iterations;
		System.out.println("Success rate: " + successRate + "%");
		System.out.println("Failure rate: " + (100 - successRate) + "%");
		float successStepCount = ((float) successSteps / (float) success);
		float failureStepCount = ((float) failSteps / (float) (iterations - success));

		System.out.println("Average steps in success: " + successStepCount);
		System.out.println("Average steps in failure: " + failureStepCount);
	}

	public static void climb(int[] queens) {

		int c, j, N = queens.length;
		int[] temp = new int[N];
		int[][] mat = new int[N][N];
		HashMap<Integer, int[]> map = new HashMap<Integer, int[]>();
		int bestSoFar = Board.getHeuristic(queens);
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
					int[] nextBest = new int[N];

					for (int k = 0; k < N; k++) {
						nextBest[k] = temp[k];
					}
					nextBest[j] = c;
					map.put(count, nextBest);
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
			success++;
			successSteps = successSteps + steps;
			steps = 0;

		} else if (min < bestSoFar) {
			steps++;
			climb(temp);
		} else if (min == bestSoFar) {
			failSteps = failSteps + steps;
			steps = 0;
		}

	}

}
