package datastructures.trees;


import java.util.List;

import static javax.swing.Spring.width;

/*
* this class is contains core concept's of the tree
* */
public class Tree {


    /*
    * diamiter of tree
    * */

    int diameter(Node root) {
        return diameter(root,0);
    }

    int diameter(Node root,int height) {
        int left=0;
        int right=0;
        if(root == null){
            height=0;
            return 0;
        }
        else{
            int leftd=diameter(root.leftNode,left);
            int rightd=diameter(root.rightNode,right);
            height=Math.max(left,right)+1;
            return Math.max(left+right+1,Math.max(leftd,rightd));

        }
    }


    /*
    * binery tree traversal is used vist all nodes in tree there are different flavour to travel through nodes
    * type:-
    * inorder-->left,root,right
    * preorder--->root,left,right
    * postorder-->left,right root
    * levelorder--> level by level
    * */
    public void inorder(Node root) {
        if(root==null) return;
        else{
            inorder(root.leftNode);
            System.out.println(root.data);
            inorder(root.rightNode);
        }
    }
    public void preorder(Node root) {
        if(root==null) return;
        else{
            System.out.println(root.data);
            preorder(root.leftNode);
            preorder(root.rightNode);
        }
    }

    public void postorder(Node root) {
        if(root==null) return;
        else{
            postorder(root.leftNode);
            postorder(root.rightNode);
            System.out.println(root.data);
        }
    }

    /*
    * level order traversal of binary tree
    * */


    /*
    * height of binery tree
    * */
    public int height(Node root) {
        if(root==null) return 0;
        else{
            return 1+Math.max(height(root.leftNode),height(root.rightNode));
        }
    }



    /*
    * uniques binary tree that can be from when n is given.
    * using dp formula is T(n)=sum of T(i)+T(n-i);
    * or equivalent catalon formula 2ncn/n+1
    * */
    /*table filling mathod
    * */
    public int numTrees(int n){
        int dp[] = new int[n+1];
       /* return numTrees(n, dp);*/
        dp[0] = 1;
        dp[1] = 1;
        for( int i=2; i<=n; ++i){
            for(int j=1; j<=i;++j){
                dp[i] += dp[j-1]*dp[i-j];
            }
        }
        return dp[n];
    }
    /*
    * memorization
    * */
    public int numTrees(int n,int dp[]){
        if(n==0)
            return 1;
        else if(n==1)
            return 1;
        else if(n==2)
            return 2;
        if(dp[n]!=0) return dp[n];
        else {
            int sum=0;
            for(int i=1;i<=n;i++){
                sum+=numTrees(i-1)*numTrees(n-i);
            }
            return dp[n]=sum;
        }
    }


}
