/*
Summary: This program contains four separate methods, each with different O(n) time complexities, to find the 
         maximum subsequence sum given a test file containing n number of elements. MSS1 runs the program in 
         n^3 time, MSS2 runs the program in n^2 time, MSS3 runs the program in n*log(n) time, and MSS4 runs the
         program in n time. The fifth function runs all four programs and displays their runtimes. After each run,
         the user is prompted whether they would like to run the method again and whether they would like to 
         return to the 'main menu'. 
Authors: Alex Bae, Alec Henning
Date: 10/28/2021
*/
import java.util.Scanner;
import java.io.*;

public class proj3 {

    public static void main(String[] args) {

        boolean run = true;

        while (run == true) {
            BufferedReader input;
            String inputLine;

            try {
                Scanner kb = new Scanner(System.in);
                System.out.println("Please enter the name of the file: ");
                String inputFile = kb.nextLine();
                input = new BufferedReader(new FileReader(inputFile));
                inputLine = input.readLine();
                String[] inputs = inputLine.split(",");
                int[] intArray = new int[inputs.length];
                for (int i = 0; i < inputs.length; i++) {
                    intArray[i] = Integer.parseInt(inputs[i]);
                }

                System.out.println("Which algorithm would you like to run?");
                System.out.println("1: MSS1");
                System.out.println("2: MSS2");
                System.out.println("3: MSS3");
                System.out.println("4: MSS4");
                System.out.println("5: All Algorithms Subsequently");
                System.out.println("6: Exit");
                System.out.print("Please choose an index: ");
                String choice = kb.nextLine();
                System.out.println("\n");

                switch (choice) {
                case "1": // run MSS1 algorithm
                    while (true) {
                        MSS1(intArray);
                        System.out.println("Do you want to run again? (y for yes n for no)");
                        if (kb.nextLine().equalsIgnoreCase("y")) {
                            continue;
                        } else {
                            break;
                        }
                    }
                    break;

                case "2": // run MSS2 algorithm
                    while (true) {
                        MSS2(intArray);
                        System.out.println("Do you want to run again? (y for yes n for no)");
                        if (kb.nextLine().equalsIgnoreCase("y")) {
                            continue;
                        } else {
                            break;
                        }
                    }
                    break;
                case "3": // run MSS3 algorithm
                    while (true) {
                        MSS3(intArray);
                        System.out.println("Do you want to run again? (y for yes n for no)");
                        if (kb.nextLine().equalsIgnoreCase("y")) {
                            continue;
                        } else {
                            break;
                        }
                    }
                    break;
                case "4": // run MSS4 algorithm
                    while (true) {
                        MSS4(intArray);
                        System.out.println("Do you want to run again? (y for yes n for no)");
                        if (kb.nextLine().equalsIgnoreCase("y")) {
                            continue;
                        } else {
                            break;
                        }
                    }
                    break;

                case "5": // run all 4 algorithms subsequently
                    while (true) {
                        subsequently(intArray);
                        System.out.println("Do you want to run again? (y for yes n for no)");
                        if (kb.nextLine().equalsIgnoreCase("y")) {
                            continue;
                        } else {
                            break;
                        }
                    }
                    break;
                case "6":
                    System.exit(0);
                }
                run = runAgain(kb);
                input.close();

            } catch (

            FileNotFoundException e) { // Catches file not found errors
                System.out.println("File not found, please try again.");
            } catch (IOException e) { // catches IO errors
                System.out.println(e);
            }

        }

    }

    /**
     * Runs the MSS1 algorithm with O(n^3) to find the maximum subsequence sum,
     * prints the maximum subsequence sum and calculates run time
     * 
     * @param a The array that we are checking
     */
    private static void MSS1(int[] a) {
        long start = System.nanoTime();
        int maxSum = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += a[k];
                    if (sum > maxSum) {
                        maxSum = sum;
                    }
                }
            }
        }
        long end = System.nanoTime();
        System.out.println("n: " + a.length);
        System.out.println("Maximum Subsequence Sum: " + maxSum); // Print the maximum subsequent sum
        System.out.println("MSS1 runtime:");
        computeTime(start, end); // calculate and print the elapsed time
    }

    /**
     * Runs the MSS2 algorithm with O(n^2) to find the maximum subsequence sum,
     * prints the maximum subsequence sum and calculates run time
     * 
     * @param a The array that we are checking
     */
    private static void MSS2(int[] a) {
        long start = System.nanoTime();
        int maxSum = 0;
        for (int i = 0; i < a.length; i++) {
            int sum = 0;
            for (int j = i; j < a.length; j++) {
                sum += a[j];
                if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }
        long end = System.nanoTime();
        System.out.println("n: " + a.length);
        System.out.println("Maximum Subsequence Sum: " + maxSum); // Print the maximum subsequent sum
        System.out.println("MSS2 runtime:");
        computeTime(start, end); // calculate and print the elapsed time
    }

    /**
     * Runs the MSS3 algorithm with O(n*log(n)) calls the MSS3Helper function are
     * sends in the left and right bounds prints the maximum subsequence sum and
     * calculates run time
     * 
     * @param a The array that we are checking
     */
    private static void MSS3(int[] a) {
        long start = System.nanoTime();
        int maxSum = MSS3Helper(a, 0, a.length - 1);
        long end = System.nanoTime();
        System.out.println("n: " + a.length);
        System.out.println("Maximum Subsequence Sum: " + maxSum); // Print the maximum subsequent sum
        System.out.println("MSS3 runtime:");
        computeTime(start, end); // calculate and print the elapsed time
    }

    /**
     * Finds the maximum subsequence sum using the divide and conquer method
     * 
     * @param a     The array that we are checking
     * @param left  The left bound of the array
     * @param right The right bound of the array
     * @return The maximum subsequence sum
     */
    private static int MSS3Helper(int[] a, int left, int right) {
        int maxLeftBorderSum = 0, maxRightBorderSum = 0;
        int leftBorderSum = 0, rightBorderSum = 0;
        int center = (left + right) / 2;

        if (left == right) {
            return a[left];
        }
        int maxLeftSum = MSS3Helper(a, left, center);
        int maxRightSum = MSS3Helper(a, center + 1, right);

        for (int i = center; i >= left; i--) {
            System.out.println("leftBorderSum: " + leftBorderSum);
            leftBorderSum += a[i];
            if (leftBorderSum > maxLeftBorderSum)
                maxLeftBorderSum = leftBorderSum;
                System.out.println("maxLeftBorderSum: " + maxLeftBorderSum);
        }

        for (int i = center + 1; i <= right; i++) {
            rightBorderSum += a[i];
            System.out.println("rightBorderSum: " + rightBorderSum);
            if (rightBorderSum > maxRightBorderSum)
                maxRightBorderSum = rightBorderSum;
                System.out.println("maxRightBorderSum: " + maxRightBorderSum);
        }

        return Math.max(Math.max(maxLeftSum, maxRightSum), (maxLeftBorderSum + maxRightBorderSum));
    }

    /**
     * Runs the MSS4 algorithm with O(n) to find the maximum subsequence sum, prints
     * the maximum subsequence sum and calculates run time
     * 
     * @param a The array that we are checking
     */
    private static void MSS4(int[] a) {

        long start = System.nanoTime();
        int maxSum = 0;
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            if (sum > maxSum) {
                maxSum = sum;
            } else {
                if (sum < 0) {
                    sum = 0;
                }
            }
        }
        long end = System.nanoTime();
        System.out.println("n: " + a.length);
        System.out.println("Maximum Subsequence Sum: " + maxSum); // Print the maximum subsequent sum
        System.out.println("MSS4 runtime:");
        computeTime(start, end); // calculate and print the elapsed time
    }

    /**
     * Runs all algorithms subsequently
     * 
     * @param intArray The array that is being checked
     */
    private static void subsequently(int[] intArray) {
        MSS1(intArray);
        MSS2(intArray);
        MSS3(intArray);
        MSS4(intArray);
    }

    /**
     * Asks the user if they would like to return to the main menu
     * 
     * @param kb The Scanner object used to record answer
     * @return true or false boolean value
     */
    public static boolean runAgain(Scanner kb) {
        System.out.println("Would you like to return to the main menu? (y for yes n for no)");
        if (kb.nextLine().equalsIgnoreCase("y")) {
            return true;
        } else {
            kb.close();
            return false;
        }
    }

    /**
     * Computes the runtime of the algorithm and prints out the value
     */
    public static void computeTime(long start, long end) {
        long elapsedTime = (end - start); // returns time in nano seconds
        System.out.println("Elapsed time in nanoseconds: " + elapsedTime);
        System.out.println("---------------------------------------");
    }

}
