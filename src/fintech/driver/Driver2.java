package fintech.driver;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import fintech.model.*;

/**
 * @author NIM Nama
 * @author NIM Nama
 */
public class Driver2 {

    public static void main(String[] _args) {
        Scanner sc = new Scanner(System.in);
        // Menggunakan Map untuk mempermudah pencarian akun berdasarkan username
        Map<String, Account> accountMap = new HashMap<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("---")) break;

            String[] tokens = line.split("#");
            String command = tokens[0];

            if (command.equals("create-account")) {
                String name = tokens[1];
                String username = tokens[2];
                accountMap.put(username, new Account(name, username));

            } else if (command.equals("deposit")) {
                String username = tokens[1];
                double amount = Double.parseDouble(tokens[2]);
                // Logika transaksi (timestamp dan deskripsi disimpan di objek transaksi)
                if (accountMap.containsKey(username)) {
                    accountMap.get(username).deposit(amount);
                    // Contoh instansiasi objek inheritance (meski belum dicetak di Task 02)
                    new DepositTransaction(username, amount, tokens[3], tokens[4]);
                }

            } else if (command.equals("withdraw")) {
                String username = tokens[1];
                double amount = Double.parseDouble(tokens[2]);
                if (accountMap.containsKey(username)) {
                    accountMap.get(username).withdraw(amount);
                    new WithdrawTransaction(username, amount, tokens[3], tokens[4]);
                }
            }
        }

        // Output semua akun yang terdaftar
        for (Account acc : accountMap.values()) {
            System.out.println(acc.toString());
        }

        sc.close();
    }
}