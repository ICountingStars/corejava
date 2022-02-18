package sparsearray;

/**
 * 稀疏数组
 *
 * @author chenjianglin
 * @date 2021/7/4
 * @since 1.0.0
 **/
public class SparseArray {
    public static void main(String[] args) {
        // 创建二维数组
        int[][] dyadicArray = new int[11][11];
        // 二维数组赋值  0待办没有棋子 1代表黑色棋子 2代表白色棋子
        dyadicArray[1][2] = 1;
        dyadicArray[2][3] = 2;
        dyadicArray[3][4] = 2;
        // 遍历二维数组，获取数组中有效值的个数
        int sum = 0;
        for (int[] row : dyadicArray) {
            for (int item : row) {
                if (item != 0) {
                    sum++;
                }
            }
        }
        //打印二维数组
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.printf("%d\t", dyadicArray[i][j]);
            }
            System.out.println();
        }
        // 根据sum值创建稀疏数组sparseArray int[sum+1][3]
        int[][] sparseArray = new int[sum + 1][3];
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;
        // 给稀疏数组存入二维数组中的有效值
        int count = 0;
        int dyadicRow = dyadicArray.length;
        for (int i = 0; i < dyadicRow; i++) {
            for (int j = 0; j < 11; j++) {
                if (dyadicArray[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = dyadicArray[i][j];
                }
            }
        }
        // 打印稀疏数组
        System.out.println("打印稀疏数组");
        for (int[] ints : sparseArray) {
            System.out.printf("%d\t%d\t%d\t\n", ints[0], ints[1], ints[2]);
        }
        // 稀疏数组转换回原始的二维数组
        int[][] recoverDyadicArray = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++) {
            recoverDyadicArray[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        System.out.println("打印 稀疏数组转换回原始的二维数组");
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.printf("%d\t", recoverDyadicArray[i][j]);
            }
            System.out.println();
        }
    }
}
