public class FlipIt {
    public static void main(String[] args) {

        int[][] twoDimentional = {
                {1, 5},
                {2, 6},
                {3, 7},
                {4, 8},
                {5, 9}
        };

        flipItVerticalAxis(twoDimentional);

        for (int[] ints : twoDimentional) {
            for (int j = 0; j < twoDimentional[0].length; j++) {
                System.out.print(ints[j]);
            }
        }
    }

    public static void flipItVerticalAxis(int[][] matrix) {
        int r = matrix.length -1;
        int c = matrix[0].length -1;
        int temp;

        for (int i = 0; i<=r; i++) {
            for (int j = 0; j<=c/2; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][c - j];
                matrix[i][c - j] = temp;

                //System.out.print(matrix[i][j]);
            }
        }
    }
}
