package toyBank.client;

import toyBank.frontalSystem.FrontalSystem;
import toyBank.request.Request;
import toyBank.request.RequestType;

import static java.lang.Thread.currentThread;

public class Client implements Runnable {
    private final RequestType requestType;
    private final int amount;
    private final FrontalSystem frontalSystem;

    public Client(RequestType requestType, int amount, FrontalSystem frontalSystem) {
        this.requestType = requestType;
        this.amount = amount;
        this.frontalSystem = frontalSystem;
    }

    public void run() {
        String clientName = currentThread().getName();
        Request request = new Request(clientName, amount, requestType);
        frontalSystem.receiveRequest(request);
        System.out.println(currentThread().getName() + ": " + request + " send to the bank");
    }
}
