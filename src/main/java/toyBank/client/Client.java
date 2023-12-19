package toyBank.client;

import toyBank.frontalSystem.FrontalSystem;
import toyBank.request.Request;
import toyBank.request.RequestType;

import java.util.Random;

import static java.lang.Thread.currentThread;

public class Client implements Runnable {
    private final RequestType requestType;
    private final int amount;
    private final FrontalSystem frontalSystem;

    public Client(FrontalSystem frontalSystem) {
        Random random = new Random();
        this.requestType = RequestType.getRandomRequestType();
        this.amount = random.nextInt(30000);
        this.frontalSystem = frontalSystem;
    }

    public void run() {
        String clientName = currentThread().getName();
        Request request = new Request(clientName, amount, requestType);
        frontalSystem.receiveRequest(request);
        System.out.println(currentThread().getName() + ": " + request + " send to the bank");
    }
}
