import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class C2556 {
    public boolean isPossibleToCutPath(int[][] grid) {
        return false;
    }

    private static int[][] dirs = new int[][] {{0, 1},{1, 0},{-1, 0},{0, -1}};

    private static void dfs(Stack<TreeNode> stack, int[][] grid, boolean[][] step, Tracer tracer, HashMap<Integer, TreeNode> nodeList) {
        var top = stack.peek();
        for(int d=0;d<4;d++) {
            int ii = top.valI + dirs[d][0];
            int jj = top.valJ + dirs[d][1];
            if(ii<0||jj<0||ii>=grid.length||jj>=grid[0].length) {
                continue;
            }
            if(step[ii][jj]) {
                continue;
            }
            if(grid[ii][jj]==0) {
                continue;
            }
            if(top.children==null) {
                top.children = new ArrayList<>();
            }
            step[ii][jj] = true;
            var created = new TreeNode(top, ii, jj, tracer.get());
            nodeList.put(ii * grid.length + jj, created);
            top.children.add(created);
            tracer.inc();
            stack.push(created);
            dfs(stack, grid, step, tracer, nodeList);
            stack.pop();
        }
    }

    private static class Tracer{
        int step;
        public void inc() {
            ++step;
        }
        public int get() {
            return step;
        }
    }

    private static class TreeNode {
        public TreeNode(TreeNode parent, int valI, int valJ, int firstOrder) {
            this.parent = parent;
            this.valI = valI;
            this.valJ = valJ;
            this.firstOrder = firstOrder;
        }

        int valI;
        int valJ;
        List<TreeNode> children;
        TreeNode parent;

        int firstOrder;
    }
}
