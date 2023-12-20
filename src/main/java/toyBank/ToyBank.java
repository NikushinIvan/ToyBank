package toyBank;

import toyBank.bankBackSystem.BankBackSystem;
import toyBank.client.Client;
import toyBank.frontalSystem.FrontalSystem;
import toyBank.requestHandler.RequestHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ToyBank {
    public static void main(String[] args) {
        BankBackSystem bankBackSystem = new BankBackSystem();

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
