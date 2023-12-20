package toyBank.requestHandler;

import toyBank.bankBackSystem.BankBackSystem;
import toyBank.frontalSystem.FrontalSystem;
import toyBank.request.Request;

public class RequestHandler implements Runnable {
    private final FrontalSystem frontalSystem;
    private final BankBackSystem bankBackSystem;
    private final int numberRequestHandler;

    public RequestHandler(FrontalSystem frontalSystem, BankBackSystem bankBackSystem, int numberRequestHandler) {
        this.numberRequestHandler = numberRequestHandler;
        this.frontalSystem = frontalSystem;
        this.bankBackSystem = bankBackSystem;
    }

    @Override
    public void run() {
        while (true) {
            Request request = frontalSystem.getRequest();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(request + " is processed by " + "RequestHandler" + numberRequestHandler);
            bankBackSystem.executionRequest(request);
        }
    }
}
