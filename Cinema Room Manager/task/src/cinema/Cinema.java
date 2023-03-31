package cinema;

import java.util.Scanner;

public class Cinema {
    private static final Scanner scanner = new Scanner(System.in);
    private static final char emptySeat = 'S';
    private static final char boughtSeat = 'B';
    private static int numOfPurchasedTicket = 0;
    static char[][] cinema = initializeSeat();

    private static int currentIncome = 0;
    private static final int totalSeat = cinema.length * cinema[0].length;

    private static final int smallCinemaTotalIncome = totalSeat * 10;

    private static final int largeCinemaTotalIncome = totalSeat * 9 - cinema[0].length;

    public static void main(String[] args) {
        // Write your code here
        int option = -1;
        while (option != 0) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            option = scanner.nextInt();
            chooseAction(option);
        }
    }

    public static char[][] initializeSeat() {
        System.out.println("Enter the number of rows:");
        int numOfRow = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numOfCol = scanner.nextInt();
        char[][] temp = new char[numOfCol][numOfCol];
        for (int i = 0; i < numOfRow; i++) {
            for (int j = 0; j < numOfCol; j++) {
                temp[i][j] = emptySeat;
            }
        }
        return temp;
    }

    public static void showSeat() {
        System.out.println("Cinema:");

        StringBuilder line = new StringBuilder("  ");
        for (int i = 1; i <= cinema[0].length; i++) {
            line.append(i).append(" ");
        }
        System.out.println(line);

        for (int i = 0; i < cinema.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < cinema[0].length; j++) {
                System.out.print(" " + cinema[i][j]);
            }
            System.out.println();
        }
    }

    public static void buyTicket() {
        System.out.println("Enter a row number:");
        int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int col = scanner.nextInt();
        if (row < 1 || col < 1 || row  > cinema.length || col > cinema[0].length){
            System.out.println("\nWrong input!\n");
            buyTicket();
            return;
        }
        if (cinema[row - 1][col - 1] == boughtSeat) {
            System.out.println("That ticket has already been purchased!\n");
            buyTicket();
            return;
        }
        cinema[row - 1][col - 1] = boughtSeat;
        numOfPurchasedTicket++;
        int cinemaSize = cinema.length * cinema[0].length;
        int price = 0;
        if (cinemaSize <= 60) {
            price = 10;
        } else {
            price = row > cinema.length / 2 ? 8 : 10;
        }
        currentIncome += price;
        System.out.println("Ticket price: $" + price);
    }

    public static void statistic() {
        System.out.println("Number of purchased tickets: " + numOfPurchasedTicket);
        System.out.printf("Percentage: %.2f%%\n", (double) numOfPurchasedTicket / (cinema.length * cinema[0].length) * 100);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + (totalSeat > 60 ? largeCinemaTotalIncome : smallCinemaTotalIncome));
    }

    public static void chooseAction(int option) {
        switch (option) {
            case 1:
                showSeat();
                break;
            case 2:
                buyTicket();
                break;
            case 3:
                statistic();
                break;
            case 0:
                break;
            default:
                System.out.println("Try again");
                break;
        }
    }

}