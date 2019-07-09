package recursive;

/**
 * @Author empcl
 * @Description 比如有5个登山队员，名称为 A,B,C,D和E。想要从这五个队员中选择三个队员去登峰，这时候如何列出所有的队员组合。（不考虑顺序）
 * @Date 2019/5/21
 * @Time 11
 */
public class ChooseTeam {
    private String[] members;
    private boolean[] selects;
    private int length;

    public ChooseTeam(String[] members) {
        this.members = members;
        this.length = members.length;
        this.selects = new boolean[length];
    }

    // total 表示选择几个人
    public void choose(int total, int index) {
        if (total == 0) {
            for (int i = 0; i < length; i++) {
                if (selects[i] == true) {
                    System.out.print(members[i] + " ");
                }
            }
            System.out.println();
            return;
        }
        // index超过组中人员总数，表示未找到
        if (index >= length) // 不能放在if(total == 0)的前面，否则，最后一个人员不能被判断
            return;
        selects[index] = true;
        choose(total - 1, index + 1);
        selects[index] = false;
        choose(total, index + 1);
    }

    public static void main(String[] args) {
        String[] members = {"A", "B", "C", "D"};
        ChooseTeam chooseTeam = new ChooseTeam(members);
        chooseTeam.choose(3, 0);
    }
}
