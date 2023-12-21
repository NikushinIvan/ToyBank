package toyBank.client;

import toyBank.frontalSystem.FrontalSystem;
import toyBank.request.Request;
import toyBank.request.RequestType;

import java.util.Random;

public class Client implements Runnable {
    private final RequestType requestType;
    private final int amount;
    private final FrontalSystem frontalSystem;
    private final int numberClient;

    public Client(FrontalSystem frontalSystem, int numberClient) {
        this.numberClient = numberClient;
        Random random = new Random();
        this.requestType = RequestType.getRandomRequestType();
        this.amount = (random.nextInt(10) + 1) * 1_000;
        this.frontalSystem = frontalSystem;
    }

    public void run() {
        String clientName = "Client" + numberClient;
        Request request = new Request(clientName, amount, requestType);
        frontalSystem.receiveRequest(request);
        System.out.println(clientName + ": " + request + " send to the bank");
    }
}
