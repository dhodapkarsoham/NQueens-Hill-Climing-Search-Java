import java.util.Scanner;

public class Solver {

	public static void main(String[] args) {

		int N, iterations = 1;
		int[] initial;
		int ch;
		String cont;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("Enter number of N (Must be greater than 3):");
			N = sc.nextInt();
			System.out.println("Enter number of iteration: ");
			iterations = sc.nextInt();
			System.out
					.println("Enter Choice: \n1: Steepest Ascent Hill Climbing\n2: Steepest Hill Climbing With Sideways"
							+ "\n3: Random Restart Without Sideways\n4: Random Restart With Sideways");
			ch = sc.nextInt();

			switch (ch) {
			case 1:
				for (int i = 0; i < iterations; i++) {
					initial = Board.createBoard(N);
					System.out.println("Initial Configuration:");
					Board.printBoard(initial, N);
					SteepestAscentHillClimbing.climb(initial);
				}
				SteepestAscentHillClimbing.printStatistics(iterations);
				break;
			case 2:
				for (int i = 0; i < iterations; i++) {
					initial = Board.createBoard(N);
					System.out.println("Initial Configuration:");
					Board.printBoard(initial, N);
					HillClimbingSideways.climb(initial);
				}
				HillClimbingSideways.printStatistics(iterations);
				break;
			case 3:
				for (int i = 0; i < iterations; i++) {
					initial = Board.createBoard(N);
					System.out.println("Initial Configuration:");
					Board.printBoard(initial, N);
					RandomRestartWithoutSideways.climb(initial);
				}
				RandomRestartWithoutSideways.printStatistics(iterations);
				break;
			case 4:
				for (int i = 0; i < iterations; i++) {
					initial = Board.createBoard(N);
					System.out.println("Initial Configuration:");
					Board.printBoard(initial, N);
					RandomRestartWithSideways.climb(initial);
				}
				RandomRestartWithSideways.printStatistics(iterations);
				break;
			}
			System.out.println("Run another variant? y/n");
			cont = sc.next();
		} while (cont.equalsIgnoreCase("y"));
		sc.close();
	}
}
