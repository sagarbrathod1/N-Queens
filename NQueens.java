/*
 * This code solves the N-Queens problem - no two queens can attack each other on a chessboard.
 * User inputs size of n x n chessboard.
 * Returns the number of solutions to the puzzle, as well as all the distinct board configurations.
 */

import java.util.Stack;
import java.util.Scanner;
 
public class NQueens {
    
    public static void main (String[] args){
        System.out.print("Enter N: ");     // prompt user for size of board
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        solveNQueens(n);     // call solveNQueens() method
        input.close();     // close scanner
    }
     
    public static void solveNQueens (int n){

        Stack<Integer> stack = new Stack<Integer>();     // initialize empty stack
        int currPos = 0;
        int counter = 0;        

        while (true){

            boolean isValidPos = false;

            while (currPos < n){
                if (isValidPos(stack, currPos, n)){
                    stack.push(currPos);
                    currPos = 0;
                    isValidPos = true;
                } else {
                    currPos++;
                }
            }
 
            if (!isValidPos){
                if (stack.isEmpty()){
                    System.out.println("All posible solutions have been returned.");
                    break;
                } else {
                    currPos = stack.pop();
                    currPos++;
                }
            }

            if (stack.size() == n){
                displaySolution(stack);
                currPos = stack.pop();
                currPos++;
                counter++;
            }

        }
        
        if (counter > 0){
            System.out.println("Number of solutions found: " + counter);
        } else {
            System.out.println("No solution found.");
        }
         
    }
     
    private static boolean isValidPos (Stack<Integer> stack, int currentPosition, int n){

        if (stack.isEmpty()){
            return true;
        }

        int size = stack.size();
        int[][] arr = new int[n][n];
        int[] colStore = new int[stack.size()];
        int positionRow = n - size - 1;
        int positionCol = currentPosition;
        boolean isValid =  true;

        for (int i = 1; i <= size; i++){     // row is i, col is stack.pop()
            int col = stack.pop();
            colStore[i - 1] = col;
            if (col == positionCol){
                isValid = false;
            }
        }

        for (int i = 0, row = n - size; i < size; i++, row++){
            arr[row][colStore[i]] = 1;
        }

        for (int i = colStore.length - 1; i>=0; i--) {
            stack.push(colStore[i]);
        }

        if (!isValid){     // return false if same column with previous queen
            return false;
        }

        for (int i = positionCol - 1, j = positionRow + 1; i >= 0 && j < n; i--, j++){           
            if (arr[j][i] == 1){
                return false;
            }
        }

        for (int i = positionCol + 1, j = positionRow + 1; i < n && j < n; i++, j++){     // check right diagonal
            if (arr[j][i] == 1){
                return false;
            }
        }

        return true;

    }
     
    private static void displaySolution (Stack<Integer> stack){

        int boardSize = stack.size();     // initialize size of board as size of stack
        Stack<Integer> temp = new Stack<Integer>();
         
        for (int i = 0; i < boardSize; i++){

            int col;
            col = stack.pop();
            temp.push(col);

            for (int j = 0; j < boardSize; j++){
                if (j == col){
                    System.out.print("Q ");
                } else{
                    System.out.print("* ");
                }
            }
             
            System.out.println();

        }

        while (temp.isEmpty() == false){
            stack.push(temp.pop());
        }

        System.out.println();

    }
     
}