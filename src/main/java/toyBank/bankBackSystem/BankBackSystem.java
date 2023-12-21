package toyBank.bankBackSystem;

import toyBank.request.Request;

import java.util.concurrent.atomic.AtomicLong;

public class BankBackSystem {
    private final AtomicLong balance;

    public BankBackSystem(AtomicLong balance) {
        System.out.println("BANK BALANCE: " + balance.get());
        this.balance = balance;
    }

    public void executionRequest(Request request) {
        switch (request.getRequestType()) {
            case CREDIT:
                if (balance.get() > request.getAmount()) {
                    changeBalance(-request.getAmount());
                    System.out.println(request + " COMPLETED. Balance: " + balance.get());
                } else {
                    System.out.println(request + " REJECTED. Balance: " + balance.get());
                }
                break;
            case REPAYMENT:
                changeBalance(request.getAmount());
                System.out.println(request + " COMPLETED. Balance: " + balance.get());
                break;
        }
    }

    public void changeBalance(int amount) {
        boolean success = false;
        while (!success) {
            long value = balance.get();
            long newValue = value + amount;

            success = balance.compareAndSet(value, newValue);
        }
    }
}
