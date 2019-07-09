package recursive;

/**
 * @Author empcl
 * @Description
 * @Date 2019/5/21
 * @Time 11
 */

/**
 * 背包问题
 * 假设想要让背包精确地承重20磅，并且有 5 个可以放入的数据项，它们的重量分别是 11 磅，8 磅，7 磅，6 磅，5 磅。
 */
public class BackPackProblem {
    private int[] weights;
    private boolean[] selects;
    private int length;

    public BackPackProblem(int[] weights) {
        this.weights = weights;
        this.length = weights.length;
        this.selects = new boolean[length];
    }

    public void knapsack(int total, int index) {
        if (total < 0 || (total > 0 && index >= length))
            return;
        if (total == 0) {
            for (int i = 0; i < length; i++) {
                if (selects[i]) {
                    System.out.print(weights[i] + " ");
                }
            }
            System.out.println();
            return;
        }
        selects[index] = true;
        knapsack(total - weights[index], index + 1);
        selects[index] = false;
        knapsack(total, index + 1);
    }

    public static void main(String[] args) {
        int[] a = {11, 9, 7, 6, 5,2};
        int total = 20;
        BackPackProblem backPackProblem = new BackPackProblem(a);
        backPackProblem.knapsack(total,0);
    }
}
