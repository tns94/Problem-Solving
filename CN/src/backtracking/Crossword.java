package backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Crossword {

    static boolean isPossible(char[][] board,int[] ind,String word)
    {
        int i=ind[0],j=ind[1],x=ind[2],y=ind[3],len=ind[4];
        if (word.length()!=len)
            return false;
        int wordIn=0;
        if(ind[5]==0)
        {
            for (int k = i; k <= x; k++) {
                if(board[k][j]=='-' || board[k][j]==word.charAt(wordIn))
                {
                    board[k][j]=word.charAt(wordIn);
                    wordIn++;
                }
                else
                {
                    for (int l = i; l < k; l++) {
                        board[l][j]='-';
                    }
                    return false;
                }
            }
        }
        if(ind[5]==1)
        {
            for (int k = j; k <= y; k++) {
                if(board[i][k]=='-' || board[i][k]==word.charAt(wordIn))
                {
                    board[i][k]=word.charAt(wordIn);
                    wordIn++;
                }
                else
                {
                    for (int l = j; l < k; l++) {
                        board[i][k]='-';
                    }
                    return false;
                }
            }
        }

        return true;
    }
    static void indexMap(char[][] board,int [][] indexMap,int n)
    {
        int in=0;
        int x;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j]=='-')
                {
                    if((i>0 && board[i-1][j]!='-') || i==0) {
                        x = i;
                        while (x < 9 && board[x+1][j] == '-') x++ ;
                        if (x > i) {
                            indexMap[in][0] = i;
                            indexMap[in][1] = j;
                            indexMap[in][2] = x;
                            indexMap[in][3] = j;
                            indexMap[in][4] = x - i + 1;
                            indexMap[in][5]=0;
                            in++;
                        }
                    }
                    if((j>0 && board[i][j-1]!='-') || j==0) {
                        x = j;
                        while (x < 9 && board[i][x+1] == '-') x++;
                        if (x > j) {
                            indexMap[in][0] = i;
                            indexMap[in][1] = j;
                            indexMap[in][2] = i;
                            indexMap[in][3] = x;
                            indexMap[in][4] = x - j + 1;
                            indexMap[in][5]=1;
                            in++;
                        }
                    }

                }
            }
        }
    }
    static void revert(char[][] board,int[] ind)
    {

        int i=ind[0],j=ind[1],x=ind[2],y=ind[3],len=ind[4];


        if(ind[5]==0)
        {
            for (int k = i; k <= x; k++) {
                board[k][j]='-';
            }
        }
        if(ind[5]==1)
        {
            for (int k = j; k <= y; k++) {
                board[i][k]='-';
            }
        }

    }
    static boolean crossword(char[][] board,int in,int wordCo,String[] words,int [][] indexMap,List<String> list)
    {
        if(in >= wordCo)
            return true;

        for (int i = 0; i < wordCo; i++) {

            if( (!list.contains(words[i])) && isPossible(board,indexMap[in],words[i]))
            {
                list.add(words[i]);
                if(crossword(board,in+1,wordCo,words,indexMap,list))
                    return true;
                else
                {
                    list.remove(words[i]);
                    revert(board,indexMap[in]);
                }
            }

        }
        return false;
    }
    static void print(char[][] board)
    {
        StringBuilder str=new StringBuilder(110);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                str.append(board[i][j]);
            }
            str.append("\n");
        }
        System.out.println(str);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        char board[][]=new char[10][10];
        String str,words[];

        for (int i = 0; i < 10; i++) {
            str=sc.next();
            for (int j = 0; j < 10; j++) {
                board[i][j]=str.charAt(j);
            }
        }
        words=sc.next().split(";");

        int[][] indexMap=new int[words.length][6];
        List<String> list=new ArrayList<>();
        indexMap(board,indexMap,words.length);
        crossword(board,0,words.length,words,indexMap,list);
        print(board);
    }

}
