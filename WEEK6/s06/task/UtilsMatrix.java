import java.util.Scanner;

// Note : le  message de mise en garde de Eclipse:
// the serializable class CustomException does not declare a static
// final serialVersionUID field of type long
// peut etre neglig'e.

class CustomException extends Exception {

	public CustomException(String string) {
		super(string);
	}

}

class UtilsMatrix {
	public static int[][] multiply(int[][] mat1, int[][] mat2) {
		int rows1 = mat1.length;
		int rows2 = mat2.length;
		int cols2 = mat2[0].length;		

		int[][] result = new int[rows1][cols2];
		for (int i = 0; i < rows1; i++) {
			for (int j = 0; j < cols2; j++) {
				for (int k = 0; k < rows2; k++) {
					result[i][j] += mat1[i][k] * mat2[k][j];
				}
			}
		}
		return result;
	}

	private static int readNextInt(Scanner scanner) {
		int val = -1;
		val = scanner.nextInt();
		System.out.println("lu = " + val);
		return val;

	}

	public static int[][] readMatrix() {
		Scanner scanner = new Scanner(System.in);

		int row = -1;
		int col = -1;

		System.out.println("Nouvelle matrice");

		System.out.print("\t Entrez nombre de lignes : ");
		row = readNextInt(scanner);
		System.out.print("\t Entrez nombre de colonnes : ");
		col = readNextInt(scanner);


		int[][] result = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print("\t Contenu cellule [" + i + "][" + j + "] : ");
				result[i][j] = scanner.nextInt();
			}
		}
		return result;
	}

	public static void display(int[][] mat) {
		for (int[] lines : mat) {
			for (int item : lines) {
				System.out.print(item + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[][] mat1 = null;
		int[][] mat2 = null;

		mat1 = readMatrix();
		mat2 = readMatrix();

		int[][] prod = null;
		prod = multiply(mat1, mat2);
		display(prod);

	}
}


