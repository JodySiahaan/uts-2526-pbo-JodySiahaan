package fintech.driver;

import fintech.model.*;
import java.util.*;

/**
 * 12S24039 - Jody Alfonso Siahaan
 */

public class Driver2 {

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
                        String name = parts[1];
                        String username = parts[2];
                        accounts.put(username, new Account(name, username));
                    }
                    break;

                case "deposit":
                    if (parts.length == 5) {
                        String username = parts[1];
                        double amount = Double.parseDouble(parts[2]);
                        String timestamp = parts[3];
                        String description = parts[4];

                        Account acc = accounts.get(username);
                        if (acc != null) {
                            Transaction t = new DepositTransaction(
                                    transactionId++, username, amount, timestamp, description);
                            t.process(acc);
                        }
                    }
                    break;

                case "withdraw":
                    if (parts.length == 5) {
                        String username = parts[1];
                        double amount = Double.parseDouble(parts[2]);
                        String timestamp = parts[3];
                        String description = parts[4];

                        Account acc = accounts.get(username);
                        if (acc != null && acc.getBalance() >= amount) {
                            Transaction t = new WithdrawTransaction(
                                    transactionId++, username, amount, timestamp, description);
                            t.process(acc);
                        }
                    }
                    break;
            }
        }

        // Output
        for (Account acc : accounts.values()) {
            System.out.println(acc.toString());
        }

        scanner.close();
    }
}