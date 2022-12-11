import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class C2471 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int minimumOperations(TreeNode root) {
        int ret = 0;
        List<TreeNode> first = new ArrayList<>();
        List<TreeNode> second = new ArrayList<>();
        List<TreeNode> current = first;
        List<TreeNode> opr = second;
        first.add(root);
        while(!current.isEmpty()) {
            int[] a = new int[current.size()];
            for(int i=0;i<current.size();i++) {
                a[i] = current.get(i).val;
            }
            Arrays.sort(a);
            HashMap<Integer, Integer> index = new HashMap<>();
            for(int i=0;i<a.length;i++) {
                index.put(a[i], i);
            }
            for(int i=0;i<a.length;i++) {
                a[i] = index.get(current.get(i).val);
            }

            ret += swap(a);

            for(TreeNode node : current) {
                if(node.left!=null) {
                    opr.add(node.left);
                }
                if(node.right!=null) {
                    opr.add(node.right);
                }
            }
            List<TreeNode> temp = current;
            current = opr;
            opr = temp;
            opr.clear();
        }

        return ret;
    }

    private int swap(int[] a) {
        int ret = 0;
        for(int i=0;i<a.length;i++) {
            if(a[i] == i) {
                continue;
            }

            int eject = a[i];
            while(eject != i) {
                int in = a[eject];
                a[eject] = eject;
                eject = in;
                ++ret;
            }
            a[i] = i;
        }

        return ret;
    }
}
