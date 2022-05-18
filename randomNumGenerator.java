import java.io.*;
import java.util.Random;

public class randomNumGenerator {

    public static void main(String[] args) {

        PrintWriter output;

        try {
            output = new PrintWriter(new FileOutputStream("100000n.txt")); // change file name before use.

            Random rand = new Random();

            int n = 100000; // manipulate n value
            int upperbound = 50;

            for (int i = 0; i < n; i++) {
                int int_random = (rand.nextInt(upperbound) - 25);
                if (i < n - 1) {
                    output.print(int_random + ",");
                } else {
                    output.print(int_random);
                }
            }
            output.close();

        } catch (IOException ex) {
            System.out.println(ex);
            System.exit(1);
        }

    }

}
