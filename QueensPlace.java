package AlgorithimDesign;

//Sara Nikoui 400149076
//Fatemieh Nikkhah 400149075
//Pouria Hosseini Dokht 400149019

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class QueensPlace {

    private int numberQ;
    private int[] queens;
    private ArrayList<int[]> allSolutions;

    public QueensPlace(int numberQ) {
        this.numberQ = numberQ;
        this.queens = new int[numberQ];
        this.allSolutions = new ArrayList<>();
    }

    //O(n!)
    public ArrayList<int[]> solve(){
        startPlacingQ(0);

        //check if there was no answer
        if (allSolutions.isEmpty())
            System.out.println("No Answer Found !!");
        else { //if it found solution print them
            System.out.println(allSolutions.size() + " solutions found:");
            int count = 0;
            for (int[] solution : allSolutions) {
                System.out.println("----".repeat(numberQ*2));
                printSolution(solution,count);
                count++;
            }
        }
        return allSolutions;
    }


    private void printSolution(int[] solution,int count) {
        System.out.println("Solution : " + count);
        for (int i = 0; i < numberQ; i++) {
            System.out.print("|");
            for (int j = 0; j < numberQ; j++) {
                if (solution[i] == j) {
                    System.out.print(" Q |");
                } else {
                    System.out.print("   |");
                }
            }
            System.out.println();
            System.out.println("+---".repeat(numberQ) + "+");
        }
    }

    private void startPlacingQ(int row) {
        if (row == numberQ) { // all queens have been placed
            allSolutions.add(queens.clone()); // add solution to list
        } else {
            for (int col = 0; col < numberQ; col++) {
                if (isSafe(row, col)) {
                    queens[row] = col; // place queen in this column
                    startPlacingQ(row + 1); // recursively place next queen
                }
            }
        }
    }

    private boolean isSafe(int row, int col) {
        // check if no two queens threaten each other
        for (int i = 0; i < row; i++) {
            if (queens[i] == col || // same column
                    queens[i] - i == col - row || // same diagonal
                    queens[i] + i == col + row) { // same diagonal
                return false;
            }
        }
        return true;
    }

    public static void testCase() throws InterruptedException {
        Scanner input = new Scanner(System.in);
        Random rd = new Random();

        int numberQ = rd.nextInt(2,13);

        System.out.println(numberQ + " Queens are going to be placed");
        Thread.sleep(3000);
        QueensPlace q1 = new QueensPlace(numberQ);

        q1.solve();

        System.out.println("Do you want to continue ?(1=>yes)");
        int nextMove = input.nextInt();

        if (nextMove == 1)
            testCase();
        else
            return;
    }

    public static void main(String[] args) throws InterruptedException {
        testCase();
    }
}
