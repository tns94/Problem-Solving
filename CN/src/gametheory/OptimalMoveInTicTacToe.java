package gametheory;

import java.util.Scanner;

public class OptimalMoveInTicTacToe
{
    static char p1 = 'x', p2 = 'o',empty;

    static boolean isLeft(char board[][])
    {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == empty)
                    return true;
        return false;
    }

    static int evaluate(char b[][])
    {
        for (int row = 0; row < 3; row++)
        {
            if (b[row][0] == b[row][1] &&
                    b[row][1] == b[row][2])
            {
                if (b[row][0] == p1)
                    return +10;
                else if (b[row][0] == p2)
                    return -10;
            }
        }

        for (int col = 0; col < 3; col++)
        {
            if (b[0][col] == b[1][col] &&
                    b[1][col] == b[2][col])
            {
                if (b[0][col] == p1)
                    return +10;

                else if (b[0][col] == p2)
                    return -10;
            }
        }

        if (b[0][0] == b[1][1] && b[1][1] == b[2][2])
        {
            if (b[0][0] == p1)
                return +10;
            else if (b[0][0] == p2)
                return -10;
        }

        if (b[0][2] == b[1][1] && b[1][1] == b[2][0])
        {
            if (b[0][2] == p1)
                return +10;
            else if (b[0][2] == p2)
                return -10;
        }

        return 0;
    }

    static int minmax(char board[][], int depth, Boolean isMax)
    {
        int score = evaluate(board);

        if (score == 10)
            return score;
        if (score == -10)
            return score;
        if (!isLeft(board))
            return 0;

        if (isMax)
        {
            int max = -1000;
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    if (board[i][j]==empty)
                    {
                        board[i][j] = p1;

                        max = Math.max(max, minmax(board,
                                depth + 1, false));

                        board[i][j] = empty;
                    }
                }
            }
            return max;
        }
        else {
            int min = 1000;
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    if (board[i][j] == empty)
                    {
                        board[i][j] = p2;

                        min = Math.min(min, minmax(board,
                                depth + 1, true));

                        board[i][j] = empty;
                    }
                }
            }
            return min;
        }
    }

    static Cordinate findBestMove(char board[][])
    {
        int max = -1000;
        Cordinate bestCordinate = new Cordinate();
        bestCordinate.row = -1;
        bestCordinate.col = -1;

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (board[i][j] == empty)
                {
                    board[i][j] = p1;
                    int moveVal = minmax(board, 0, false);

                    board[i][j] = empty;

                    if (moveVal > max)
                    {
                        bestCordinate.row = i;
                        bestCordinate.col = j;
                        max = moveVal;
                    }
                }
            }
        }
        bestCordinate.val=max;
        return bestCordinate;
    }
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        char board[][]=new char[3][3];
        for (int i = 0; i < n; i++) {
            board[sc.nextInt()][sc.nextInt()]=sc.next().charAt(0);
        }
        Cordinate bestCordinate = findBestMove(board);
        System.out.println(bestCordinate.val+" row: "+bestCordinate.row+" col: "+bestCordinate.col);
    }
    static class Cordinate{ int row, col,val;}
}
