package fintech.driver;

import fintech.model.*;
import java.util.*;

/**
 * 12S24039 - Jody Alfonso Siahaan
 */

public class Driver3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedHashMap<String, Account> accounts = new LinkedHashMap<>();
        int transactionId = 1;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();

            if (line.equals("---")) {
                break;
            }

            String[] parts = line.split("#");

            switch (parts[0]) {

                case "create-account":
                    if (parts.length == 3) {
                        accounts.put(parts[2], new Account(parts[1], parts[2]));
                    }
                    break;

                case "deposit":
                    if (parts.length == 5) {
                        Account acc = accounts.get(parts[1]);
                        if (acc != null) {
                            Transaction t = new DepositTransaction(
                                    transactionId++, parts[1],
                                    Double.parseDouble(parts[2]),
                                    parts[3], parts[4]);
                            t.process(acc);
                        }
                    }
                    break;

                case "withdraw":
                    if (parts.length == 5) {
                        Account acc = accounts.get(parts[1]);
                        double amount = Double.parseDouble(parts[2]);

                        if (acc != null && acc.getBalance() >= amount) {
                            Transaction t = new WithdrawTransaction(
                                    transactionId++, parts[1],
                                    amount, parts[3], parts[4]);
                            t.process(acc);
                        }
                    }
                    break;

                case "transfer":
                    if (parts.length == 6) {
                        Account sender = accounts.get(parts[1]);
                        Account receiver = accounts.get(parts[2]);
                        double amount = Double.parseDouble(parts[3]);

                        if (sender != null && receiver != null && sender.getBalance() >= amount) {
                            TransferTransaction t = new TransferTransaction(
                                    transactionId++, parts[1], parts[2],
                                    amount, parts[4], parts[5]);

                            t.process(sender, receiver);
                        }
                    }
                    break;
            }
        }

        // OUTPUT
        for (Account acc : accounts.values()) {
            System.out.println(acc.toString());
        }

        scanner.close();
    }
}