package fintech.driver;

import fintech.model.*;
import java.util.*;

/**
 * 12S24039 - Jody Alfonso Siahaan
 */

public class Driver4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedHashMap<String, Account> accounts = new LinkedHashMap<>();
        int transactionId = 1;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();

            if (line.equals("---")) break;

            String[] parts = line.split("#");

            switch (parts[0]) {

                case "create-account":
                    accounts.put(parts[2], new Account(parts[1], parts[2]));
                    break;

                case "deposit": {
                    Account acc = accounts.get(parts[1]);
                    double amount = Double.parseDouble(parts[2]);

                    if (acc != null) {
                        acc.deposit(amount);
                        Transaction t = new DepositTransaction(
                                transactionId++, parts[1], amount, parts[3], parts[4]);
                        acc.addTransaction(t);
                    }
                    break;
                }

                case "withdraw": {
                    Account acc = accounts.get(parts[1]);
                    double amount = Double.parseDouble(parts[2]);

                    if (acc != null) {
                        try {
                            acc.withdraw(amount);
                            Transaction t = new WithdrawTransaction(
                                    transactionId++, parts[1], amount, parts[3], parts[4]);
                            acc.addTransaction(t);
                        } catch (NegativeBalanceException e) {
                            // tidak berhenti
                        }
                    }
                    break;
                }

                case "transfer": {
                    Account sender = accounts.get(parts[1]);
                    Account receiver = accounts.get(parts[2]);
                    double amount = Double.parseDouble(parts[3]);

                    if (sender != null && receiver != null) {
                        try {
                            sender.transfer(amount, receiver);
                            Transaction t = new TransferTransaction(
                                    transactionId++, parts[1], parts[2], amount, parts[4], parts[5]);
                            sender.addTransaction(t);
                        } catch (NegativeBalanceException e) {
                            // lanjut
                        }
                    }
                    break;
                }

                case "show-account": {
                    Account acc = accounts.get(parts[1]);
                    if (acc != null) {
                        System.out.println(acc);

                        List<Transaction> list = acc.getTransactions();
                        list.sort(Comparator.comparing(Transaction::getTimestamp));

                        for (Transaction t : list) {
                            System.out.println(
                                t.id + "|" +
                                t.getType() + "|" +
                                t.getSignedAmount() + "|" +
                                t.getTimestamp() + "|" +
                                t.description
                            );
                        }
                    }
                    break;
                }
            }
        }

        scanner.close();
    }
}