package fintech.driver;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import fintech.model.*;

/**
 * @author NIM Nama
 * @author NIM Nama
 */
public class Driver4 {

    public static void main(String[] _args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Account> accountMap = new HashMap<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("---")) break;

            String[] tokens = line.split("#");
            String command = tokens[0];

            try {
                if (command.equals("create-account")) {
                    accountMap.put(tokens[2], new Account(tokens[1], tokens[2]));

                } else if (command.equals("deposit")) {
                    Account acc = accountMap.get(tokens[1]);
                    if (acc != null) {
                        acc.addTransaction(new DepositTransaction(tokens[1], 
                            Double.parseDouble(tokens[2]), tokens[3], tokens[4]));
                    }

                } else if (command.equals("withdraw")) {
                    Account acc = accountMap.get(tokens[1]);
                    if (acc != null) {
                        double amt = Double.parseDouble(tokens[2]);
                        acc.validateWithdraw(amt); // Cek apakah saldo cukup
                        acc.addTransaction(new WithdrawTransaction(tokens[1], amt, tokens[3], tokens[4]));
                    }

                } else if (command.equals("transfer")) {
                    Account sender = accountMap.get(tokens[1]);
                    Account receiver = accountMap.get(tokens[2]);
                    if (sender != null && receiver != null) {
                        double amt = Double.parseDouble(tokens[3]);
                        sender.validateWithdraw(amt);
                        
                        Transaction t = new TransferTransaction(tokens[1], tokens[2], amt, tokens[4], tokens[5]);
                        sender.addTransaction(t);
                        // Untuk penerima, ini dianggap deposit dari transfer
                        receiver.addTransaction(new DepositTransaction(tokens[2], amt, tokens[4], tokens[5]));
                    }

                } else if (command.equals("show-account")) {
                    Account acc = accountMap.get(tokens[1]);
                    if (acc != null) acc.printAccountInfo();
                }
            } catch (NegativeBalanceException e) {
                // Program tidak berhenti, cukup abaikan transaksi yang gagal
            }
        }
        sc.close();
    }
}