package segmenttree;

import java.util.Scanner;

public class MaximumPairSum {
    static class Node{int fm, sm;}
    static Node prepareTreeNode(Node left,Node right)
    {
        int a,b;
        if(left.fm>right.fm)
        {
            a=left.fm;
            b=Math.max(right.fm,left.sm);
        }
        else if(left.fm<right.fm) {
            a=right.fm;
            b=Math.max(right.sm,left.fm);
        }
        else
        {
            a=left.fm;
            b=right.fm;
        }

        Node node=new Node();
        node.fm=a;
        node.sm=b;
        return node;
    }
    static Node rangeQuery(Node[] tree,int treeIn,int st,int en, int l,int r)
    {
        if(st>=l && en<=r)
            return tree[treeIn];
        if (en<l || st>r)
        {
            Node node=new Node();
            node.fm=node.sm=0;
            return node;
        }
        int mid=(st+en)/2;
        Node left = rangeQuery(tree,2*treeIn,st,mid,l,r);

        Node right = rangeQuery(tree,2*treeIn+1,mid+1,en,l,r);

        return prepareTreeNode(left,right);
    }
    static void updateTree(int[] arr,Node[] tree,int treeIn, int st,int en, int index,int value){
        if(st==index && en==index)
        {
            tree[treeIn].fm=value;
            tree[treeIn].sm=0;
            arr[index]=value;
            return;
        }
        int mid=(st+en)/2;
        if(index>mid)
            updateTree(arr,tree,treeIn*2+1,mid+1,en,index,value);
        else
            updateTree(arr,tree,treeIn*2,st,mid,index,value);
       tree[treeIn]= prepareTreeNode(tree[treeIn*2],tree[treeIn*2+1]);
    }
    static void buildTree(int[] arr,Node[] tree,int treeIn, int st, int en)
    {
        if(st==en)
        {
            Node node=new Node();
            node.fm=arr[st];
            node.sm=0;
            tree[treeIn]=node;
            return;
        }
        int mid=(st+en)/2;

        buildTree(arr,tree,treeIn*2,st,mid);
        buildTree(arr,tree,treeIn*2+1,mid+1,en);

        tree[treeIn]= prepareTreeNode(tree[treeIn*2],tree[treeIn*2+1]);
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] arr=new int[n];
        Node[] tree=new Node[3*n];
        for (int i = 0; i < n; i++) arr[i]=sc.nextInt();
        buildTree(arr,tree,1,0,n-1);
       /* for (int i = 1; i < 2*n; i++) {
            System.out.println(tree[i].fm+" "+tree[i].sm);
        }*/
        StringBuilder str=new StringBuilder();
        int q=sc.nextInt();
        for (int i = 0; i < q; i++) {
            String s=sc.next();
            if(s.equals("U"))
                updateTree(arr,tree,1,0,n-1,sc.nextInt()-1,sc.nextInt());
            else
            {
                Node node=rangeQuery(tree,1,0,n-1,sc.nextInt()-1,sc.nextInt()-1);
                str.append((node.fm+node.sm)+"\n");
            }
        }
        System.out.print(str);
    }
}
