package toyBank.requestHandler;

import toyBank.bankBackSystem.BankBackSystem;
import toyBank.frontalSystem.FrontalSystem;
import toyBank.request.Request;

import static java.lang.Thread.currentThread;

public class RequestHandler implements Runnable {
    private final FrontalSystem frontalSystem;
    private final BankBackSystem bankBackSystem;

    public RequestHandler(FrontalSystem frontalSystem, BankBackSystem bankBackSystem) {
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
            System.out.println(request + " is processed by " + currentThread().getName());
            bankBackSystem.executionRequest(request);
        }
    }
}
