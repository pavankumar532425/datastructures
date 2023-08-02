package datastructures.trees;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
public class Treeleetcodeproblems {
    /*
    *
    * */
    public void flatten(TreeNode root) {
        List<TreeNode> ls= new ArrayList<>();
        pre(root,ls);
        for(int i=0; i<ls.size()-1; i++) {
            ls.get(i).right=ls.get(i+1);
            ls.get(i).left=null;
        }
    }
    public void pre(TreeNode root,List<TreeNode> ls){
        if(root==null) return;
        else{
            ls.add(root);
            pre(root.left, ls);
            pre(root.right, ls);
        }
    }


    /*
    * Maximum Sum BST in Binary Tree
    * */

    private int maxSum = 0;
    public int maxSumBST(TreeNode root) {
        postOrderTraverse(root);
        return maxSum;
    }
    private int[] postOrderTraverse(TreeNode root) {
        if (root == null) return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0}; // {min, max, sum}, initialize min=MAX_VALUE, max=MIN_VALUE
        int[] left = postOrderTraverse(root.left);
        int[] right = postOrderTraverse(root.right);
        // The BST is the tree:
        if (!(     left != null             // the left subtree must be BST
                && right != null            // the right subtree must be BST
                && root.val > left[1]       // the root's key must greater than maximum keys of the left subtree
                && root.val < right[0]))    // the root's key must lower than minimum keys of the right subtree
            return null;
        int sum = root.val + left[2] + right[2]; // now it's a BST make `root` as root
        maxSum = Math.max(maxSum, sum);
        int min = Math.min(root.val, left[0]);
        int max = Math.max(root.val, right[1]);
        return new int[]{min, max, sum};
    }
   // int maxSum;
    int preindex=0;
    /*public int maxSumBST(TreeNode root) {
     List<Integer> inorder= new ArrayList<Integer>();
     List<Integer> preorder= new ArrayList<Integer>();
     this.inorder(root, inorder);
     this.preorder(root, preorder);
     maxSum=Integer.MIN_VALUE;
     if(issortedOrder(inorder,0,preorder.size()-1)){
         return getsum(inorder,0,preorder.size()-1);
     }
     maxSumBST(inorder,preorder,0,preorder.size()-1);
     return Math.max(maxSum, 0);
    }*/
    public void maxSumBST(List<Integer> inorder,List<Integer> preorder,int startIND,int endIND) {
     if(startIND>endIND) return ;
        if(preindex>=inorder.size())
            return;
       if(startIND==endIND&& maxSum<inorder.get(startIND))
           maxSum=inorder.get(startIND);
       int index=serch(inorder,startIND,endIND,preorder.get(preindex++));
        boolean l=issortedOrder(inorder,startIND,index-1);
        boolean r=issortedOrder(inorder,index+1,endIND);
       if(l){
           int s=getsum(inorder,startIND,index-1);
           System.out.println(s);
           if(maxSum<s);
           maxSum=s;
       }
      if(r){
           int s=getsum(inorder,index+1,endIND);
          // System.out.println(index+1+""+endIND+s);
          System.out.println(s);
           if(maxSum<s);
           maxSum=s;
       }
      if(!l&&!r){
          maxSumBST(inorder,preorder,startIND,index-1);
           maxSumBST(inorder,preorder,index+1,endIND);
       }

    }
    public int getsum(List<Integer> ls,int start,int end){
        int sum=0;
        if(start==end)
            return ls.get(start);
        for(int i=start;i<=end;i++) {
            sum=sum+ls.get(i);
        }
        return sum;
    }
    public int serch(List<Integer> inorder,int start,int end,int key){
        int i;
        for(i=start;i<=end;i++){
            if(inorder.get(i)==key)
                return i;
        }
        return i;
    }

    public boolean issortedOrder(List<Integer> ls,int start,int end){
        if(start>end)
            return false;
        if(start==end)
            return true;
      for(int i=start;i<end;i++) {
          if(ls.get(i)>= ls.get(i+1))
              return false;
      }


      return true;
    }

    public void inorder(TreeNode root,List<Integer> inorderls){
        if(root==null) return;
        else{
            inorder(root.left,inorderls);
            inorderls.add(root.val);
            inorder(root.right,inorderls);
        }
    }
    public void preorder(TreeNode root,List<Integer> preorderls){
        if(root==null) return;
        else{
            preorderls.add(root.val);
            preorder(root.left,preorderls);
            preorder(root.right,preorderls);
        }
    }


    /*
    *  Convert BST to Greater Tree
    * */
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if(root==null){
            return root;
        }
        reverseInorder(root);
        return root;
    }
    private void reverseInorder(TreeNode root){
        if(root==null){
            return;
        }
        reverseInorder(root.right);
        root.val = root.val + sum;
        sum = root.val;
        reverseInorder(root.left);
        return;
    }

    /*
    * Unique Binary Search Trees II need to now more on this
    * */
    public List<TreeNode> generateTrees(int n) {
        return generateSubtrees(1, n);
    }

    private List<TreeNode> generateSubtrees(int s, int e) {
        List<TreeNode> res = new LinkedList<TreeNode>();
        if (s > e) {
            res.add(null); // empty tree
            return res;
        }

        for (int i = s; i <= e; ++i) {
            List<TreeNode> leftSubtrees = generateSubtrees(s, i - 1);
            List<TreeNode> rightSubtrees = generateSubtrees(i + 1, e);

            for (TreeNode left : leftSubtrees) {
                for (TreeNode right : rightSubtrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }

    /*
    *Find a pair with given target in BST
    * */
    public int isPairPresent(Node root, int target)
    {
        if(target==0)
            return 1;
        else {

        }
        return 0;
    }
    /*
     * vertical order traversal
     * */
    TreeMap<Integer,TreeMap<Integer,List<Integer>>> map=null;
    public List<List<Integer>> verticalTraversal(TreeNode root) {
     map=new TreeMap<>();
    preorder(root,0,0);
        List<List<Integer>> ls= new ArrayList<>();
        //SortedSet<Integer> keys = new TreeSet<>(map.keySet());
        System.out.println(map.toString());
        for(int key:map.keySet()) {
            ArrayList<Integer> p= new ArrayList<>();
            for(int k:map.get(key).keySet()){
                for(int v:map.get(key).get(k))
                    p.add(v);

            }
                ls.add(p);
        }
        return ls;
    }

    int height(TreeNode root)
    {
        if (root == null)
            return 0;
        else {
            /* compute  height of each subtree */
            int lheight = height(root.left);
            int rheight = height(root.right);

            /* use the larger one */
            if (lheight > rheight)
                return (lheight + 1);
            else
                return (rheight + 1);
        }
    }

    /* Print nodes at the current level */
    void currentLevel(TreeNode root, int level,int hd,int vd)
    {
        if (root == null)
            return;
        if (level == 1){
            if(map.containsKey(hd)){
                  if(map.get(hd).containsKey(vd)){
                      if(map.get(hd).get(vd).get(map.get(hd).get(vd).size()-1)>root.val)
                          map.get(hd).get(vd).add(map.get(hd).get(vd).size()-1,root.val);
                      //System.out.println(map.get(hd).get(vd));
                       else{
                          map.get(hd).get(vd).add(root.val);
                      }
                  }
                  else{
                      //TreeMap<Integer,List<Integer>> map1=new TreeMap<>();
                      List<Integer> ls =new ArrayList<>();
                      ls.add(root.val);
                      map.get(hd).put(vd,ls);

                  }


            }
            else{
                TreeMap<Integer,List<Integer>> map1=new TreeMap<>();
                List<Integer> ls =new ArrayList<>();
                ls.add(root.val);
                map1.put(vd,ls);
                map.put(hd, map1);
            }
        }
        else if (level > 1) {
            currentLevel(root.left, level - 1,hd-1,vd+1);
            currentLevel(root.right, level - 1,hd+1,vd+1);
        }
    }

   public void LevelOrder( TreeNode root,int hd)
    {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode tempNode = queue.poll();
            System.out.print(tempNode.val + " ");
            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }
            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
    }
   public void preorder(TreeNode root,int hd,int vd) {
        if(root==null) return;
        else{
            if(map.containsKey(hd)){
                if(map.get(hd).containsKey(vd)){
                    int position=map.get(hd).get(vd).size();
                    if(map.get(hd).get(vd).get(map.get(hd).get(vd).size()-1)>root.val){
                        for(int i=map.get(hd).get(vd).size()-1;i>=0;i--){
                            if(map.get(hd).get(vd).get(i)>root.val){
                                position--;
                            }
                            else{
                                break;
                            }
                        }
                        map.get(hd).get(vd).add(position,root.val);
                    }
                    else{
                        map.get(hd).get(vd).add(root.val);
                    }
                }
                else{
                    List<Integer> ls =new ArrayList<>();
                    ls.add(root.val);
                    map.get(hd).put(vd,ls);
                }


            }
            else{
                TreeMap<Integer,List<Integer>> map1=new TreeMap<>();
                List<Integer> ls =new ArrayList<>();
                ls.add(root.val);
                map1.put(vd,ls);
                map.put(hd, map1);
            }
        }
            preorder(root.left,hd-1,vd+1);
            preorder(root.right,hd+1,vd+1);
        }


    }
