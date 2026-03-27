package fintech.driver;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import fintech.model.Account;

/**
 12S24039 - Alfonso Siahaan
 */
public class Driver1 {

    public static void main(String[] _args) {
        Scanner sc = new Scanner(System.in);
        List<Account> accounts = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            
            // Berhenti jika membaca "---"
            if (line.equals("---")) {
                break;
            }

            String[] data = line.split("#");
            String command = data[0];

            if (command.equals("create-account")) {
                String name = data[1];
                String username = data[2];

                // Menambahkan akun baru ke dalam list
                Account newAccount = new Account(name, username);
                accounts.add(newAccount);
                
                // Mencetak akun sesuai format output
                System.out.println(newAccount.toString());
            }
        }
        sc.close();
    }
}