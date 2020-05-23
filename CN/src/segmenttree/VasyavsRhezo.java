package segmenttree;

import java.util.Scanner;

public class VasyavsRhezo {
    static class Node{int a,b,in;}
    static Node buildHelper(Node left,Node right)
    {
        if(left==null)return right;
        if(right==null)return left;
        if(left.a>right.a)
            return left;
        else if(left.a<right.a)
            return right;
        else if(left.b<right.b)
            return left;
        else if(left.b>right.b)
            return right;
        else if(left.in<right.in)
            return left;
        else
            return right;
    }
    static Node rangeQuery(Node[] tree,int treeIn,int st,int en, int l,int r)
    {
        if(st>=l && en<=r)
            return tree[treeIn];
        if (en<l || st>r)
            return null;
        int mid=(st+en)/2;
        Node left=rangeQuery(tree,2*treeIn,st,mid,l,r);
        Node right=rangeQuery(tree,2*treeIn+1,mid+1,en,l,r);
        return buildHelper(left,right);
    }
    static void buildTree(int[] arrA,int[] arrB,Node[] tree,int treeIn, int st, int en)
    {
        if(st==en)
        {
            Node node=new Node();
            node.a=arrA[st];
            node.b=arrB[st];
            node.in=st;
            tree[treeIn]=node;
            return;
        }
        int mid=(st+en)/2;

        buildTree(arrA,arrB,tree,treeIn*2,st,mid);
        buildTree(arrA,arrB,tree,treeIn*2+1,mid+1,en);

        tree[treeIn]=buildHelper(tree[treeIn*2],tree[treeIn*2+1]);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arrA = new int[n];
        int[] arrB = new int[n];
        Node[] tree = new Node[3 * n];
        for (int i = 0; i < n; i++) arrA[i] = sc.nextInt();
        for (int i = 0; i < n; i++) arrB[i] = sc.nextInt();

        buildTree(arrA,arrB, tree, 1, 0, n - 1);
        int x,y,q=sc.nextInt();
        StringBuilder str=new StringBuilder();
        for (int i = 0; i < q; i++) {
            x=sc.nextInt();
            y=sc.nextInt();
            str.append(rangeQuery(tree,1,0,n-1,x-1,y-1).in+1+"\n");
        }
        System.out.print(str);
    }
}
