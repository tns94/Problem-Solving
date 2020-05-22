package segmenttree;

import java.util.Scanner;

public class MaximumSumInSubarray {
    static class Node{int sum,prefix,suffix, max;}
    static Node prepareNode(Node left,Node right){
        if(left==null)return right;
        if(right==null)return left;
        Node root=new Node();
        root.sum=left.sum+right.sum;
        root.prefix=Math.max(left.prefix,left.sum+right.prefix);
        root.suffix=Math.max(right.suffix,left.suffix+right.sum);
        root.max =Math.max(Math.max(left.suffix+right.prefix,left.sum+right.prefix),Math.max(right.sum+left.suffix,Math.max(left.max ,right.max)));
        return root;
    }
    static Node rangeQuery(Node[] tree, int treeIn, int st, int en, int l, int r)
    {
        if(st>=l && en<=r)
            return tree[treeIn];
        if (en<l || st>r)
            return null;
        int mid=(st+en)/2;
        Node left=rangeQuery(tree,2*treeIn,st,mid,l,r);
        Node right=rangeQuery(tree,2*treeIn+1,mid+1,en,l,r);
        return prepareNode(left,right);

    }
    static void buildTree(int[] arr,Node[] tree,int treeIn, int st, int en)
    {
        if(st==en)
        {
            Node node=new Node();
            node.suffix=node.prefix=node.sum=node.max=arr[st];
            tree[treeIn]=node;
            return;
        }
        int mid=(st+en)/2;

        buildTree(arr,tree,treeIn*2,st,mid);
        buildTree(arr,tree,treeIn*2+1,mid+1,en);

        tree[treeIn]=prepareNode(tree[treeIn*2],tree[treeIn*2+1]);
    }

    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] arr=new int[n];
        Node[] tree=new Node[3*n];
        int m;
        for (int i = 0; i < n; i++) arr[i]=sc.nextInt();
        buildTree(arr,tree,1,0,n-1);
       /* for (int i = 1; i < 2*n; i++) {
            System.out.println(tree[i].max+" "+tree[i].prefix+" "+tree[i].suffix+" "+tree[i].sum);
        }*/
        m=sc.nextInt();

        StringBuilder str=new StringBuilder();
        for (int i = 0; i < m; i++) {
            str.append(rangeQuery(tree,1,0,n-1,sc.nextInt()-1,sc.nextInt()-1).max+"\n");
        }
        System.out.print(str);

    }
}
