//-----------------------------------------------------
// Title: Q2
//-----------------------------------------------------

import java.io.File;
import java.util.Scanner;

public class AssignmentQ2 {

    public static int[] pricesToArray(String fileName) throws Exception { //Placing the text file's values to array as an integer

        Scanner sc = new Scanner(new File(fileName));

        int size = 0;

        sc = new Scanner(new File(fileName));

        if (sc.hasNextLine()) {
            size = sc.nextLine().split("-").length;
        }
        sc.close();

        sc = new Scanner(new File(fileName));

        String[] line = new String[size];
        line = sc.nextLine().trim().split(" ");

        //Modified part
        int[] intResult = new int[line.length];
        for(int i = 0; i< line.length; i++) {
            intResult[i] = Integer.parseInt(line[i]);
        }
        //Modified part

        return intResult;
    }

    public static void deneme(int[] prices, Stack<Integer> stack, Queue<Integer> queue){
        int max = 0;

        for (int i = 0; i < prices.length; i++) { //Adding all values into queue
            queue.enqueue(prices[i]);
        }

        for (int j = 0; j< prices.length; j++) {
            int result = 0;
            stack.push(queue.dequeue()); //Taking the value from the queue and placing onto stack

            for (int y = 0; y < stack.getSize(); y++) {
                    if (stack.get(0) <= stack.get(y)) { //Checking the condition
                        result++;
                    }
                }

            if (result>max) { //Changing the max value
                max = result;
                System.out.print(result + " ");
            }
            else {
                System.out.print(1 + " ");
            }

        }

    }


    public static void main(String[] args) throws Exception {
        Scanner keyboard = new Scanner(System.in);
        Stack <Integer> stack = new Stack();
        Queue <Integer> queue = new Queue();

        System.out.print("Input filename:");
        String fileName = keyboard.nextLine();
        int[] prices = AssignmentQ2.pricesToArray(fileName); //Modified to return an integer array (modified parts are specified)

        AssignmentQ2.deneme(prices, stack, queue);

    }
}
