// CreditCardInterface
interface CreditCardInterface {
    void viewCreditAmount();
    void useCard(double amount);
    void payCredit(double amount);
    void increaseLimit(double amount);
}

// SilverCardCustomer
class SilverCardCustomer implements CreditCardInterface {
    public String name;
    public String cardNumber;
    public double creditAmount;
    public double creditLimit;

    public SilverCardCustomer(String name, String cardNumber) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.creditAmount = 0;
        this.creditLimit = 50000;
    }

    @Override
    public void viewCreditAmount() {
        System.out.println("Name: " + name);
        System.out.println("Card Number: " + cardNumber);
        System.out.println("Credit Amount: " + creditAmount);
        System.out.println("Credit Limit: " + creditLimit);
    }

    @Override
    public void useCard(double amount) {
        if (amount <= creditLimit - creditAmount) {
            creditAmount += amount;
            System.out.println("Transaction successful. Credit amount increased by " + amount);
        } else {
            System.out.println("Transaction failed. Insufficient credit limit.");
        }
    }

    @Override
    public void payCredit(double amount) {
        if (amount <= creditAmount) {
            creditAmount -= amount;
            System.out.println("Payment successful. Credit amount reduced by " + amount);
        } else {
            System.out.println("Payment failed. Amount exceeds credit amount.");
        }
    }

    @Override
    public void increaseLimit(double amount) {
        System.out.println("SilverCardCustomer cannot increase credit limit.");
    }
}

// GoldCardCustomer
class GoldCardCustomer extends SilverCardCustomer {
    private int limitIncreaseCount;

    public GoldCardCustomer(String name, String cardNumber) {
        super(name, cardNumber);
        this.creditLimit = 100000;
        this.limitIncreaseCount = 0;
    }

    @Override
    public void increaseLimit(double amount) {
        if (limitIncreaseCount < 3 && amount <= 5000) {
            creditLimit += amount;
            limitIncreaseCount++;
            System.out.println("Credit limit increased by " + amount + ". New credit limit: " + creditLimit);
        } else {
            System.out.println("Credit limit increase failed. Maximum limit increase count reached or amount exceeds limit.");
        }
    }
}

public class Third{
    public static void main(String[] args) {
        SilverCardCustomer silverCustomer = new SilverCardCustomer("John", "1234567890123456");
        GoldCardCustomer goldCustomer = new GoldCardCustomer("Jane", "9876543210987654");

        // SilverCardCustomer transactions
        silverCustomer.viewCreditAmount();
        silverCustomer.useCard(20000);
        silverCustomer.payCredit(5000);
        silverCustomer.viewCreditAmount();

        // GoldCardCustomer transactions
        goldCustomer.viewCreditAmount();
        goldCustomer.useCard(80000);
        goldCustomer.payCredit(30000);
        goldCustomer.increaseLimit(3000);
        goldCustomer.increaseLimit(3000);
        goldCustomer.increaseLimit(3000);
        goldCustomer.increaseLimit(3000);

        goldCustomer.viewCreditAmount();
    }
}

