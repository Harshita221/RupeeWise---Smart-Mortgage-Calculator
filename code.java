import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Main {
        public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);

                // Get user input
                System.out.print("Enter the Principal (₹): ");
                int principal = scanner.nextInt();

                System.out.print("Enter loan term (in years): ");
                int years = scanner.nextInt();

                System.out.print("Enter the interest rate: ");
                float annualInterest = scanner.nextFloat();

                // Calculate values
                float monthlyInterest = (annualInterest / 100) / 12;
                int numberOfPayments = years * 12;

                double monthlyPayment = (principal * monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments)) /
                        (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

                // Use Indian Rupees (₹) currency format
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));

                System.out.println("\nYour Monthly Mortgage Payment: " + currencyFormat.format(monthlyPayment));

                double totalPayment = monthlyPayment * numberOfPayments;
                double totalInterest = totalPayment - principal;

                System.out.println("\nTotal Amount Paid Over the Loan Term: " + currencyFormat.format(totalPayment));
                System.out.println("Total Interest Paid: " + currencyFormat.format(totalInterest));

                // Amortization Schedule Header
                double balance = principal;
                System.out.println("\nAmortization Schedule:");
                System.out.printf("%-10s %-20s %-20s %-20s\n", "Month", "Principal Paid", "Interest Paid", "Remaining Balance");
                System.out.println("--------------------------------------------------------------------------------");

                for (int month = 1; month <= numberOfPayments; month++) {
                        double interestPayment = balance * monthlyInterest;
                        double principalPayment = monthlyPayment - interestPayment;
                        balance -= principalPayment;
                        balance = (balance < 0) ? 0 : balance; // Ensure balance doesn't go negative

                        System.out.printf("%-10d %-20s %-20s %-20s\n",
                                month,
                                currencyFormat.format(principalPayment),
                                currencyFormat.format(interestPayment),
                                currencyFormat.format(balance));
                }



                scanner.close();
        }
}
