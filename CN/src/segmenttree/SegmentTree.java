package segmenttree;

import java.util.Scanner;
/*
SegmentTree Data Structure.
 */
public class SegmentTree {

    static int rangeQuery(int[] tree,int treeIn,int st,int en, int l,int r)
    {
        if(st>=l && en<=r)
            return tree[treeIn];
        if (en<l || st>r)
            return 0;
        int mid=(st+en)/2;
        return rangeQuery(tree,2*treeIn,st,mid,l,r)
                +
                rangeQuery(tree,2*treeIn+1,mid+1,en,l,r);
    }
    static void updateTree(int[] arr,int[] tree,int treeIn, int st,int en, int index,int value){
        if(st==index && en==index)
        {
            tree[treeIn]=arr[index]=value;
            return;
        }
        int mid=(st+en)/2;
        if(index>mid)
            updateTree(arr,tree,treeIn*2+1,mid+1,en,index,value);
        else
            updateTree(arr,tree,treeIn*2,st,mid,index,value);

        tree[treeIn]=tree[2*treeIn]+tree[2*treeIn+1];
    }
    static void buildTree(int[] arr,int[] tree,int treeIn, int st, int en)
    {
        if(st==en)
        {
            tree[treeIn]=arr[st];
            return;
        }
        int mid=(st+en)/2;

        buildTree(arr,tree,treeIn*2,st,mid);
        buildTree(arr,tree,treeIn*2+1,mid+1,en);

        tree[treeIn]=tree[2*treeIn]+tree[2*treeIn+1];
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] arr=new int[n];
        int[] tree=new int[3*n];
        for (int i = 0; i < n; i++) arr[i]=sc.nextInt();
        buildTree(arr,tree,1,0,n-1);
        updateTree(arr,tree,1,0,n-1,7,20);
        for (int i = 1; i < 2*n; i++) {
            System.out.println(tree[i]);
        }

        int ans=rangeQuery(tree,1,0,n-1,3,6);
        System.out.println(ans);
    }
}
