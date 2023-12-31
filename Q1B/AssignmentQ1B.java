//-----------------------------------------------------
// Title: Q1 Part B
//-----------------------------------------------------

import java.io.File;
import java.util.Scanner;

public class AssignmentQ1B {

    public static int[][] readfile(String fileName) throws Exception { //Reading the given matrix and converting into 2D Array

        fileName = fileName.substring(1,fileName.length()-1);

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

        sc = new Scanner(new File(fileName));

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

    public static String[] coordinatesIntoArray(String fileName) throws Exception { //Taking coordinates of array and storing them in another array (I could have write these process in another method but for debugging reasons I created another method)

        fileName = fileName.substring(1,fileName.length()-1);

        Scanner sc = new Scanner(new File(fileName));

        int size = 0;

        sc = new Scanner(new File(fileName));

        if (sc.hasNextLine()) {
            size = sc.nextLine().split("-").length;
        }
        sc.close();

        sc = new Scanner(new File(fileName));

        String[] input = new String[size];
        String[] line = new String[size];
        line = sc.nextLine().trim().split("-");

        return line;
    }

    public static String[][] coordinatesArrayToIdentifierArray(int[][] matrixList, String[] coordinatesArr, String whichList) {
        //Naming the 2D arrays specified coordinates with a string I named whichList in the parameters of method (I used "fListElement" for the first list, and "sListElement" for the second)
        int row = matrixList.length;
        int column = matrixList[0].length;
        String[][] result = new String[row][column];

        for(int i = 0; i<coordinatesArr.length; i++) {
                int initialRow = Integer.parseInt(coordinatesArr[i].substring(0,1));
                int initialColumn = Integer.parseInt(coordinatesArr[i].substring(2,3));
                result[initialRow][initialColumn] = whichList;
        }
        return result;

    }

    public static void identifierToList(String[][] identifierArray, SLinkedList listToAdd) {
        for (int i = 0; i < identifierArray.length; i++) {
            for (int j = 0; j < identifierArray[i].length; j++) {
                if(identifierArray[i][j] != null){
                    if(listToAdd.getHead() == null){ //If it is the first element of the list use addFirst
                        listToAdd.addFirst(new Node(99)); //Adding 99 if it has an element at the point
                    }
                    else{ //If it is not the first element of the list use addLast
                        listToAdd.addLast(new Node(99)); //Adding 99 if it has an element at the point
                    }
                }
                else {
                    if(listToAdd.getHead() == null){ //If it is the first element of the list use addFirst
                        listToAdd.addFirst(new Node(0)); //Adding 99 if it has an element at the point
                    }
                    else{ //If it is not the first element of the list use addLast
                        listToAdd.addLast(new Node(0)); //Adding 99 if it has an element at the point
                    }
                }

            }
        }
    }

    public static boolean resultComparison(SLinkedList list1, SLinkedList list2, int columnCount) {
        boolean condition1 = false;
        boolean condition2 = false;
        boolean condition3 = false;
        boolean condition4 = false;


        for(int i = 0; i<list1.getSize(); i++) {
            if (list1.get(i).getData() != 0) {
                try {
                    //Check for Left (-1 because of left)
                    if(list1.get(i).getData() == list1.get(i-1).getData()){ //Case of including the element of itself
                        condition1 = true;
                    }
                    else{
                        condition1 = list1.get(i).getData() == list2.get(i - 1).getData();
                    }
                } catch (NullPointerException e) { //Reason of using try-catch is; for instance, if a value checked the right value and the value is null, it should give false, not the exception NullPointerException
                    condition1 = false;
                }

                try {
                    //Check for Right (+1 because of right)
                    if(list1.get(i).getData() == list1.get(i+1).getData()){ //Case of including the element of itself
                        condition2 = true;
                    }
                    else{
                        condition2 = list1.get(i).getData() == list2.get(i + 1).getData();
                    }
                } catch (NullPointerException e) {
                    condition2 = false;
                }

                try {
                    //Check for Top (-columnCount because of checking the top value) Case of including the element of itself cannot happen when checking the top and bottom
                    condition3 = list1.get(i).getData() == list2.get(i - columnCount).getData();
                } catch (NullPointerException e) {
                    condition3 = false;
                }

                try {
                    //Check for Bottom (+columnCount because of checking the bottom value)
                    condition4 = list1.get(i).getData() == list2.get(i + columnCount).getData();
                } catch (NullPointerException e) {
                    condition4 = false;
                }
            }
        }

        return condition1&&condition2&&condition3&&condition4;
    }


    public static void main(String[] args) throws Exception {
        Scanner keyboard = new Scanner(System.in);
        SLinkedList matrixList = new SLinkedList();

        System.out.print("Input filename:");
        String fileName = keyboard.nextLine();

        int[][] matrix = readfile(fileName); // Matrix text file to 2D Array
        int row = matrix.length;
        int column = matrix[0].length;

        System.out.print("Input filename:");
        String fileName2 = keyboard.nextLine(); //fList (firstList)

        System.out.print("Input filename:");
        String filename3 = keyboard.nextLine(); //sList (secondList)

        AssignmentQ1.spiralForm(row, column, matrix, matrixList); // Inserting the matrix's elements to list as we did in Q1A (Sprial Form)

        String coordinates1[] = coordinatesIntoArray(fileName2); // Reading the coordinates on text and converting to an array to indicate which elements contain a number

        //fListElement --> Element of first list
        //sListElement --> Element of second list

        String identifierMatrix1[][]  = coordinatesArrayToIdentifierArray(matrix,coordinates1,"fListElement"); //Creating a 2D matrix and filling the blanks that specified in the list text files as "fListElement"

        String coordinates2[] = coordinatesIntoArray(filename3); //Reading the coordinates for the second file

        String identifierMatrix2[][]  = coordinatesArrayToIdentifierArray(matrix,coordinates2,"sListElement"); //Creating a 2D matrix as we did for the first list but name it "sListElement" for the debugging

        SLinkedList identifierList = new SLinkedList();
        SLinkedList identifierList2 = new SLinkedList();

        identifierToList(identifierMatrix1,identifierList); //Iterating over the matrix (If iteration sees an element adds 99 and if not, adds 0)
        identifierToList(identifierMatrix2,identifierList2); //Same process for the second file's matrix

        System.out.println(resultComparison(identifierList2,identifierList, column)); //Checking the condition (right, left, top, and bottom for elements)

    }
}
