package toyBank.bankBackSystem;

import toyBank.request.Request;

import java.util.concurrent.atomic.AtomicLong;

public class BankBackSystem {
    private final AtomicLong balance;

    public BankBackSystem(long balance) {
        this.balance = new AtomicLong(balance);
    }

    public synchronized void executionRequest(Request request) {
        switch (request.getRequestType()) {
            case CREDIT:
                if (balance.get() > request.getAmount()) {
                    balance.addAndGet(-request.getAmount());
                    System.out.println(request + " COMPLETED. Balance: " + balance);
                } else {
                    System.out.println(request + " REJECTED. Balance: " + balance);
                }
                break;
            case REPAYMENT:
                balance.addAndGet(request.getAmount());
                System.out.println(request + " COMPLETED. Balance: " + balance);
                break;
        }
    }
}
