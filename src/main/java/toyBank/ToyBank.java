package toyBank;

import toyBank.bankBackSystem.BankBackSystem;
import toyBank.bankBackSystem.StorageSystem;
import toyBank.client.Client;
import toyBank.frontalSystem.FrontalSystem;
import toyBank.requestHandler.RequestHandler;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class ToyBank {
    public static void main(String[] args) {
        AtomicLong balance = new AtomicLong();

        ExecutorService executorStorageSystem = Executors.newFixedThreadPool(3);
        var storageSystems = List.of(
                new StorageSystem(1),
                new StorageSystem(2),
                new StorageSystem(3));

        try {
            var futures = executorStorageSystem.invokeAll(storageSystems);
            for (var future : futures) {
                balance.addAndGet(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        BankBackSystem bankBackSystem = new BankBackSystem(balance);

        FrontalSystem frontalSystem = new FrontalSystem();

        ExecutorService requestHandlers = Executors.newFixedThreadPool(2);
        requestHandlers.submit(new RequestHandler(frontalSystem, bankBackSystem, 1));
        requestHandlers.submit(new RequestHandler(frontalSystem, bankBackSystem, 2));

        ExecutorService clients = Executors.newFixedThreadPool(5);
        for (int i = 1; i < 8; i++) {
            clients.submit(new Client(frontalSystem, i));
        }
    }
}
