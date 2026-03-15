
package fintech.model;

public class Account {

    private String username;
    private String name;
    private double balance;

    public Account(String name, String username) {
        this.name = name;
        this.username = username;
        this.balance = 0.0;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if(balance - amount < 0) {
            return false;
        }
        balance -= amount;
        return true;
    }

}
