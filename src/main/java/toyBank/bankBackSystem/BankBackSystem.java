package toyBank.bankBackSystem;

import toyBank.request.Request;

public class BankBackSystem {
    private int balance;

    public BankBackSystem(int balance) {
        this.balance = balance;
    }

    public synchronized void executionRequest(Request request) {
        switch (request.getRequestType()) {
            case CREDIT:
                if (balance > request.getAmount()) {
                    balance -= request.getAmount();
                    System.out.println(request + " COMPLETED. Balance: " + balance);
                } else {
                    System.out.println(request + " REJECTED. Balance: " + balance);
                }
                break;
            case REPAYMENT:
                balance += request.getAmount();
                System.out.println(request + " COMPLETED. Balance: " + balance);
                break;
        }
    }
}
