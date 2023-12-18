package toyBank;

import toyBank.bankBackSystem.BankBackSystem;
import toyBank.client.Client;
import toyBank.frontalSystem.FrontalSystem;
import toyBank.request.RequestType;
import toyBank.requestHandler.RequestHandler;

public class ToyBank {
    public static void main(String[] args) {
        BankBackSystem bankBackSystem = new BankBackSystem(15000);

        FrontalSystem frontalSystem = new FrontalSystem();

        RequestHandler requestHandler1 = new RequestHandler(frontalSystem, bankBackSystem);
        RequestHandler requestHandler2 = new RequestHandler(frontalSystem, bankBackSystem);

        Client client1 = new Client(RequestType.CREDIT, 10000, frontalSystem);
        Client client2 = new Client(RequestType.REPAYMENT, 1000, frontalSystem);
        Client client3 = new Client(RequestType.CREDIT, 20000, frontalSystem);
        Client client4 = new Client(RequestType.REPAYMENT, 10000, frontalSystem);
        Client client5 = new Client(RequestType.CREDIT, 10000, frontalSystem);

        Thread thread1 = new Thread(client1, "client1");
        Thread thread2 = new Thread(client2, "client2");
        Thread thread3 = new Thread(client3, "client3");
        Thread thread4 = new Thread(client4, "client4");
        Thread thread5 = new Thread(client5, "client5");

        Thread thread6 = new Thread(requestHandler1, "requestHandler1");
        Thread thread7 = new Thread(requestHandler2, "requestHandler2");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
    }
}
