package mathhw;

import java.util.*;

public class MathHW {

    public static void main(String[] args) {
        System.out.println("Choose an option or enter -1 to quit: ");
        System.out.printf("1.Div and Mod%n2.Floor and Ceiling: ");
        Scanner input = new Scanner(System.in);
        int selection = input.nextInt();

        int number;
        int divisorModulus;
        int divisor;
        int modulus;
        double floorCeilingNumber;


        do {

            if (selection == 1) {
                System.out.printf("%nEnter a number: ");
                number = input.nextInt();
                System.out.print("Enter the divisor or modulus: ");
                divisorModulus = input.nextInt();
                divisor = number / divisorModulus;
                modulus = number % divisorModulus;


                if (number < 0) {
                    divisor = number / divisorModulus - 1;
                    modulus = number - (divisorModulus * divisor);

                }

                System.out.printf("The Divisor is (Q): %d%n", divisor);
                System.out.printf("The Modulo is (R): %d%n%n", modulus);


                System.out.println("Choose an option or enter -1 to quit: ");
                System.out.printf("1.Div and Mod%n2.Floor and Ceiling: ");
                selection = input.nextInt();

            }

            if (selection == 2) {
                System.out.print("Enter a number to find the Floor & Ceiling: ");
                floorCeilingNumber = input.nextDouble();

                System.out.printf("The Floor is: %.0f%n", Math.floor(floorCeilingNumber));
                System.out.printf("The Ceiling is: %.0f%n%n", Math.ceil(floorCeilingNumber));

                System.out.println("Choose an option or enter -1 to quit: ");
                System.out.printf("1.Div and Mod%n2.Floor and Ceiling: ");
                selection = input.nextInt();
            }

        } while (selection != -1);

    }

}
