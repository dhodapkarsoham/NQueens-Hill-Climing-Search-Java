import java.util.Random;

public class Board {

	/**
	 * Create a random board
	 * 
	 * @param N
	 * @return
	 */
	public static int[] createBoard(int N) {
		int[] newPosition = new int[N];
		Random random = new Random();

		for (int i = 0; i < N; i++) {
			newPosition[i] = random.nextInt(N);
		}
		return newPosition;
	}

	/**
	 * Get the heuristic value of the current state
	 * 
	 * @param board
	 * @return
	 */
	public static int getHeuristic(int[] board) {
		int heuristic = 0;
		for (int i = 0; i < board.length; i++) {
			int temp = board[i];
			for (int j = i + 1; j < board.length; j++) {
				if (temp == board[j] || Math.abs(i - j) == Math.abs(temp - board[j])) {
					heuristic++;
				}
			}
		}
		return heuristic;
	}

	/**
	 * Generate matrix of heuristic values
	 * 
	 * @param queen
	 * @return
	 */
	public static int[][] getHMatrix(int[] queen) {
		int n = queen.length;
		int[] temp = new int[n];
		int[][] mat = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				temp[j] = queen[j];
			}
			for (int p = 0; p < n; p++) {
				for (int k = 0; k < n; k++) {
					if (i == k) {
						temp[k] = p;
					}
				}
				mat[p][i] = getHeuristic(temp);
			}
		}
		return mat;
	}

	public static void printBoard(int[] queens, int N) {
		int[][] board = new int[N][N];
		for (int column = 0; column < N; column++) {
			board[queens[column]][column] = 1;
		}
		for (int row = 0; row < N; row++) {
			for (int column = 0; column < N; column++) {
				if (board[row][column] == 1) {
					System.out.print("Q\t");
				} else {
					System.out.print(board[row][column] + "\t");
				}
			}
			System.out.println();
		}

	}

}
