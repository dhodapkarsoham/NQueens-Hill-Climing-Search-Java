import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class RandomRestartWithSideways {
	public static int count = 1;
	public static int steps = 0;
	public static int sCount = 0;

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
		int count = 0;
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
					{
						if (!(Arrays.equals(nextBest, queens))) {
							map.put(count, nextBest);
							count++;
						}
					}
				}
			}
		}

		if (count > 0) {
			Random rand = new Random();
			int index = rand.nextInt(count);
			temp = map.get(index);
			Board.printBoard(temp, temp.length);

			if (min == 0) {
				System.out.println("Solution Found");
				Board.printBoard(temp, temp.length);
				System.out.println("Conflicts: " + Board.getHeuristic(temp));
				steps++;
			} else if (min < bestSoFar) {
				steps++;
				sCount = 0;
				climb(temp);
				bestSoFar = min;
			} else if (min == bestSoFar) {

				if (sCount > 99) {
					System.out.println("Doing random restart.");
					count++;
					climb(Board.createBoard(N));

				} else {
					System.out.println("Allowing the sideways move number " + sCount);
					steps++;
					sCount++;
					climb(temp);

				}

			}

		} else {
			System.out.println("Doing random restart.");
			count++;
			climb(Board.createBoard(N));
		}

	}

}
