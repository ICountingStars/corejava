package recursion;

/**
 * TODO
 *
 * @author chenjianglin
 * @date 2021/7/18
 * @since 1.0.0
 **/
public class Maze {
    public static void main(String[] args) {
        // 建立迷宫地图
        int[][] map = new int[8][7];
        // 第0行与第7行全部置为 1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        // 第0列和第6列全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
            if (i == 3) {
                map[i][1] = 1;
                map[i][2] = 1;
            }
        }
        //输出迷宫地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        // 递归回溯
//        setWay(map, 1, 1);
        setWay2(map, 1, 1);
        // 输出走过的路
        System.out.println("输出走过的路:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    //使用递归回溯来给小球找路
    //说明
    //1. map表示地图
    //2. i,j表示从地图的哪个位置开始出发(1,1)
    //3,如果小球能到map[6][5]位置,则说明通路找到
    //4.约定: 当map[i][3]为 表示该点没有走过当为1表示墙; 2表示通路可以走; 3表示该点已经走过,但是走不通
    //5.在走迷宫时,需要确定一个策略(方法)下->右->上->左

    /**
     * @param map 迷宫
     * @param i   开始行
     * @param j   开始列
     * @return TRUE 找到同步，FALSE 找不到
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) {// 向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {// 向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {// 向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {// 向左走
                    return true;
                } else {// 死路
                    map[i][j] = 3;
                    return false;
                }
            } else {// map[i][j] !=0  可能 1，2，3
                return false;
            }
        }
    }

    // 修改策略为 上->右->下->左
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                //上->右->下->左
                if (setWay2(map, i - 1, j)) {// 向上走
                    return true;
                } else if (setWay2(map, i, j + 1)) {// 向右走
                    return true;
                } else if (setWay2(map, i + 1, j)) {// 向下走
                    return true;
                } else if (setWay2(map, i, j - 1)) {// 向左走
                    return true;
                } else {// 死路
                    map[i][j] = 3;
                    return false;
                }
            } else {// map[i][j] !=0  可能 1，2，3
                return false;
            }
        }
    }
}
