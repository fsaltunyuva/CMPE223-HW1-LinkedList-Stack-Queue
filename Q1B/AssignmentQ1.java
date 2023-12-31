//-----------------------------------------------------
// Title: Q1 Part A
//-----------------------------------------------------

import java.io.*;
import java.util.Scanner;

public class AssignmentQ1 {

    public static int[][] readfile(String fileName) throws Exception {

        Scanner sc = new Scanner(new File(fileName));

        int row = 0;
        int column = 0;

        while (sc.hasNextLine()) {
            row++;
            sc.nextLine();
        }

        sc.close();

        sc = new Scanner(new File(fileName));

        if (sc.hasNextLine()) {
            column = sc.nextLine().split(" ").length;
        }
        sc.close();

        sc = new Scanner(new File( fileName));

        int[][] matrix = new int[row][column];
        while (sc.hasNextLine()) {
            for (int i = 0; i < matrix.length; i++) {
                String[] line = sc.nextLine().trim().split(" ");
                for (int j = 0; j < line.length; j++) {
                    matrix[i][j] = Integer.parseInt(line[j]);
                }
            }
        }
        return matrix;
    }

    public static void spiralForm(int rLastIndex, int cLastIndex, int[][] a, SLinkedList list) { //Although I declared them "Index", they will adapt as 0,1,2... or 1,2,3... dynamically
        int rFirstIndex = 0, cFirstIndex = 0;
        int i = 0;

        while (rFirstIndex < rLastIndex && cFirstIndex < cLastIndex) {

            for (i = rFirstIndex; i < rLastIndex; ++i) {
                if (list.get(0) != null) { //If it is not the first element, use addLast method
                    list.addLast(new Node(a[i][cFirstIndex]));
                } else { //If it is the first element, use addFirst method
                    list.addFirst(new Node(a[i][cFirstIndex]));
                }
            }
            cFirstIndex++; //Increasing the columnFirstIndex to prevent the add the corner values 2 times

            for (i = cFirstIndex; i < cLastIndex; ++i) {
                if (list.get(0) != null) { //If it is not the first element, use addLast method
                    list.addLast(new Node(a[rLastIndex - 1][i])); //rLastIndex-1 to adapt the case 1,2,3... to 0,1,2...
                } else { //If it is the first element, use addFirst method
                    list.addFirst(new Node(a[rLastIndex - 1][i]));
                }
            }
            rLastIndex--; //Decreasing the rowLastIndex to prevent IndexOutOfBounds exception

            if (cFirstIndex < cLastIndex) { //If this statement false, it cannot go upwards
                for (i = rLastIndex - 1; i >= rFirstIndex; --i) { //Going upward case (-1 to prevent repeating bottom right corner value)
                    if (list.get(0) != null) { //If it is not the first element, use addLast method
                        list.addLast(new Node(a[i][cLastIndex - 1]));
                    } else { //If it is the first element, use addFirst method
                        list.addFirst(new Node(a[i][cLastIndex - 1]));
                    }
                }
                cLastIndex--; //End of the first loop for going upwards and downwards (Decreasing the columnLastIndex value to start the next loop with respect to columnCount-1)
            }

            if (rFirstIndex < rLastIndex) { //If this statement false, it cannot go to the left
                for (i = cLastIndex - 1; i >= cFirstIndex; --i) { //Going to the left case (-1 to prevent repeating top right corner value
                    if (list.get(0) != null) { //If it is not the first element, use addLast method
                        list.addLast(new Node(a[rFirstIndex][i]));
                    } else { //If it is the first element, use addFirst method
                        list.addFirst(new Node(a[rFirstIndex][i]));
                    }
                }
                rFirstIndex++; //End of the first general loop (Increasing the rowFirstIndex value to start the next loop from the second row)
            }
        }
    }


    public static void main(String[] args) throws Exception {
        Scanner keyboard = new Scanner(System.in);
        SLinkedList list = new SLinkedList();

        System.out.print("Input filename:");
        String fileName = keyboard.nextLine();

        int[][] matrix = readfile(fileName);
        int row = matrix.length;
        int column = matrix[0].length;

        spiralForm(row, column, matrix, list);

        for (int i = 0; i < list.getSize(); i++) {
            if (list.get(i).getData() == -1) { //Stop when you see the value -1
                break;
            }
            System.out.print(list.get(i).getData() + " ");
        }

    }


}

