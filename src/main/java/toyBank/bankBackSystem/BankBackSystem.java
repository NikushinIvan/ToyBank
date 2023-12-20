package toyBank.bankBackSystem;

import toyBank.request.Request;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class BankBackSystem {
    private final AtomicLong balance = new AtomicLong();

    public BankBackSystem() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        var storageSystems = List.of(
                new StorageSystem(1),
                new StorageSystem(2),
                new StorageSystem(3));

        try {
            var futures = executorService.invokeAll(storageSystems);
            for (var future : futures) {
                balance.addAndGet(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
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
