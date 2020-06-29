public class FlipIt2 {
    public static void main(String[] args) {
        char[][] twoDementional = {
                {'b', 'a'},
                {'d', 'c'},
                {'f', 'e'},
                {'h', 'g'},
                {'j', 'i'}
        };

        //flipItVerticalAxis(twoDementional);

        for (int i = 0; i<twoDementional.length; i++) {
            for (int j=0; j<twoDementional[0].length/2; j++) {
                System.out.print(twoDementional[i][j]);
            }
        }
    }

    public static void flipItVerticalAxis(int[][] matrix) {
        int r = matrix.length - 1;
        int c = matrix[0].length -1;
        int temp;

        for (int i = 0; i<=r; i++) {
            for (int j = 0; i<=c/2; j++) {
                temp = matrix[i][j];
                //matrix[i][j];
            }
        }
    }
}
