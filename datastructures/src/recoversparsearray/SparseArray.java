package recoversparsearray;

/**
 * TODO
 *
 * @author chenjianglin
 * @date 2021/7/5
 * @since 1.0.0
 **/
public class SparseArray {
    public static void main(String[] args) {
        // 二维数组转稀疏数组
        // 创建二维数组
        int[][] dyadicArray = new int[11][11];
        // 0 表示没有棋子，1表示黑子 2 表示白子
        dyadicArray[1][2] = 1;
        dyadicArray[2][3] = 2;
        dyadicArray[3][4] = 2;
        dyadicArray[4][5] = 1;
        //打印二维数组 并统计有效棋子个数
        int num = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (dyadicArray[i][j] != 0) {
                    num++;
                }
                System.out.printf("%d\t", dyadicArray[i][j]);
            }
            System.out.println();
        }
        //稀疏数组初始化 稀疏数组的一共有三列 行数是有效个数棋子+1
        int[][] sparseArray = new int[num + 1][3];
        sparseArray[0][0] = dyadicArray.length;
        sparseArray[0][1] = dyadicArray.length;
        sparseArray[0][2] = num;
        // 遍历二维数组，为稀疏数组赋值
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (dyadicArray[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = dyadicArray[i][j];
                }
            }
        }
        System.out.println("打印稀疏数组");
        for (int[] ints : sparseArray) {
            // 行 列 值
            System.out.printf("%d\t%d\t%d\t\n", ints[0], ints[1], ints[2]);
        }
        // 遍历稀疏数组 ， 将稀疏数组转换为二维数组
        // 初始化转换后的二维数组 二维数组的行 稀疏数组中第一行的第一列， 二维数组的列 稀疏数组中的第一行的第二列
        // 二维数组中非零的个数为 稀疏数组的第一行的第三列
        int[][] recoverDyadicArray = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++) {
            recoverDyadicArray[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        // 遍历输出稀疏数组转二维数组的值
        System.out.println("遍历输出稀疏数组转二维数组的值");
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.printf("%d\t", recoverDyadicArray[i][j]);
            }
            System.out.println();
        }
    }
}
