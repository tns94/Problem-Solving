package segmenttree;

import java.util.Scanner;

public class SumOfSquares {
    static class Node{int sum,sqsum;}
    static class LNode{int type,x;}

    static int rangeQuery(LNode[] lazy,Node[] tree,int treeIn,int st,int en,int l,int r)
    {
        if(lazy[treeIn]!=null && lazy[treeIn].type!=-1)
            updateHelper(lazy,tree,treeIn,st,en,lazy[treeIn].x,lazy[treeIn].type,true);

        if(en<l || st>r)
            return 0;

        if(st>=l && en<=r)
            return tree[treeIn].sqsum;

        int mid=(st+en)/2;
        return rangeQuery(lazy,tree,treeIn*2,st,mid,l,r)
        + rangeQuery(lazy,tree,treeIn*2+1,mid+1,en,l,r);

    }
    static void updateHelper(LNode[] lazy,Node[] tree,int treeIn,int st,int en,int value,int type,boolean old)
    {
        if(type==1)
        {
            tree[treeIn].sqsum=tree[treeIn].sqsum+(value*value)*(en-st+1)+2*value*tree[treeIn].sum;
            tree[treeIn].sum=value*(en-st+1)+tree[treeIn].sum;
            if(st!=en){
                lazy[treeIn*2]=new LNode();
                lazy[treeIn*2+1]=new LNode();
                lazy[treeIn*2].x+=value;
                lazy[treeIn*2+1].x+=value;
                lazy[treeIn*2].type=type;
                lazy[treeIn*2+1].type=type;
            }
        }
        if(type==0)
        {
            tree[treeIn].sqsum=value*value*(en-st+1);
            tree[treeIn].sum=value*(en-st+1);
            if(st!=en){
                lazy[treeIn*2]=new LNode();
                lazy[treeIn*2+1]=new LNode();
                lazy[treeIn*2].x=value;
                lazy[treeIn*2+1].x=value;
                lazy[treeIn*2].type=type;
                lazy[treeIn*2+1].type=type;
            }
        }
        if(old)
        {
            lazy[treeIn]=null;
        }

    }

    static void update(LNode[] lazy,Node[] tree,int treeIn,int st,int en,int l,int r,int value,int type)
    {
        if(st>en)
            return;

        if(lazy[treeIn]!=null && lazy[treeIn].type!=-1)
        {
          updateHelper(lazy,tree,treeIn,st,en,lazy[treeIn].x,lazy[treeIn].type,true);
        }
        if(en<l || st>r)
            return;
        if(l<=st && en<=r)
        {
            updateHelper(lazy,tree,treeIn,st,en,value,type,false);
            return;
        }
        int mid=(st+en)/2;
        update(lazy,tree,treeIn*2,st,mid,l,r,value,type);
        update(lazy,tree,treeIn*2+1,mid+1,en,l,r,value,type);

        tree[treeIn].sqsum=tree[treeIn*2].sqsum+tree[treeIn*2+1].sqsum;
        tree[treeIn].sum=tree[treeIn*2].sum+tree[treeIn*2+1].sum;
    }
    static void buildTree(int[] arr,Node[] tree,int treeIn,int st,int en)
    {
        if(st==en)
        {
            Node node=new Node();
            node.sqsum=arr[st]*arr[st];
            node.sum=arr[st];
            tree[treeIn]=node;
            return;
        }
        int mid=(st+en)/2;
        buildTree(arr,tree,treeIn*2,st,mid);
        buildTree(arr,tree,treeIn*2+1,mid+1,en);

        Node node=new Node();
        node.sqsum=tree[treeIn*2].sqsum+tree[treeIn*2+1].sqsum;
        node.sum=tree[treeIn*2].sum+tree[treeIn*2+1].sum;
        tree[treeIn]=node;

    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        int n,q,l,r,x,type;
        int[] arr;
        Node[] tree;
        LNode[] lazy;
        StringBuilder str=new StringBuilder();
        for (int i = 0; i < t; i++) {
            str.append("Case "+(i+1)+":\n");
            n=sc.nextInt();
            q=sc.nextInt();
            arr=new int[n];
            for (int j = 0; j < n; j++) arr[j]=sc.nextInt();
            tree=new Node[3*n];
            lazy=new LNode[3*n];
            buildTree(arr,tree,1,0,n-1);
            for (int j = 0; j < q; j++) {
                    type=sc.nextInt();
                    l=sc.nextInt();
                    r=sc.nextInt();
                if(type==2)
                    str.append(rangeQuery(lazy,tree,1,0,n-1,l-1,r-1)+"\n");
                else
                {
                    x=sc.nextInt();
                    update(lazy,tree,1,0,n-1,l-1,r-1,x,type);
                }
            }

        }

        System.out.print(str);
    }
}
