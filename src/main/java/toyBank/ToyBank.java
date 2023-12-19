package toyBank;

import toyBank.bankBackSystem.BankBackSystem;
import toyBank.client.Client;
import toyBank.frontalSystem.FrontalSystem;
import toyBank.requestHandler.RequestHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ToyBank {
    public static void main(String[] args) {
        BankBackSystem bankBackSystem = new BankBackSystem(15000);

        FrontalSystem frontalSystem = new FrontalSystem();

        ExecutorService requestHandlers = Executors.newFixedThreadPool(2);
        requestHandlers.submit(new RequestHandler(frontalSystem, bankBackSystem));
        requestHandlers.submit(new RequestHandler(frontalSystem, bankBackSystem));

        ExecutorService clients = Executors.newFixedThreadPool(5);
        clients.submit(new Client(frontalSystem));
        clients.submit(new Client(frontalSystem));
        clients.submit(new Client(frontalSystem));
        clients.submit(new Client(frontalSystem));
        clients.submit(new Client(frontalSystem));
        clients.submit(new Client(frontalSystem));
        clients.submit(new Client(frontalSystem));
    }
}
