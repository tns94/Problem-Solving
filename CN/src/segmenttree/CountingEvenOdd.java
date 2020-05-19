package segmenttree;

import java.util.Scanner;

public class CountingEvenOdd {
    static int rangeQuery(int[] tree,int treeIn,int st,int en, int l,int r,int type)
    {
        if(st>=l && en<=r)
            return type==1?tree[treeIn]:Math.abs(tree[treeIn]-(en-st+1));
        if (en<l || st>r)
            return 0;
        int mid=(st+en)/2;
        return rangeQuery(tree,2*treeIn,st,mid,l,r,type)
                +
                rangeQuery(tree,2*treeIn+1,mid+1,en,l,r,type);
    }
    static void updateTree(int[] arr,int[] tree,int treeIn, int st,int en, int index,int value){
        if(st==index && en==index)
        {
            tree[treeIn]=value%2==0?1:0;
            arr[index]=value;
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
            tree[treeIn]=arr[st]%2==0?1:0;
            return;
        }
        int mid=(st+en)/2;

        buildTree(arr,tree,treeIn*2,st,mid);
        buildTree(arr,tree,treeIn*2+1,mid+1,en);

        tree[treeIn]=tree[2*treeIn]+tree[2*treeIn+1];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int[] tree = new int[3 * n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        buildTree(arr,tree,1,0,n-1);
        int q=sc.nextInt();
        int type,x,y;
        StringBuilder str=new StringBuilder();
        for (int i = 0; i < q; i++) {
            type=sc.nextInt();x=sc.nextInt();y=sc.nextInt();
            if(type!=0)
                str.append(rangeQuery(tree,1,0,n-1,x-1,y-1,type)+"\n");
            else
                updateTree(arr,tree,1,0,n-1,x-1,y);
        }
        System.out.print(str);
    }

}
