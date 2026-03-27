package fintech.driver;

import fintech.model.Account;
import java.util.*;

/**
 * 12S24039 - Jody Alfonso Siahaan
 */

public class Driver1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedHashMap<String, Account> accounts = new LinkedHashMap<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();

            if (line.equals("---")) {
                break;
            }

            String[] parts = line.split("#");

            if (parts[0].equals("create-account") && parts.length == 3) {
                String name = parts[1];
                String username = parts[2];

                Account acc = new Account(name, username);
                accounts.put(username, acc);
            }
        }

        // Output
        for (Account acc : accounts.values()) {
            System.out.println(acc.toString());
        }

        scanner.close();
    }
}