package toyBank.frontalSystem;

import toyBank.request.Request;

import java.util.ArrayDeque;

public class FrontalSystem {
    private static final int CAPACITY = 2;
    private final ArrayDeque<Request> requests = new ArrayDeque<Request>();
    public synchronized void receiveRequest(Request request) {
        while (requests.size() >= CAPACITY) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        requests.addLast(request);
        notifyAll();
    }
    public synchronized Request getRequest() {
        while (requests.size() < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        notifyAll();
        return requests.pollFirst();
    }
}
