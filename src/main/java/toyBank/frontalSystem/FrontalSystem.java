package toyBank.frontalSystem;

import toyBank.request.Request;

import java.util.concurrent.ArrayBlockingQueue;

public class FrontalSystem {
    private static final int CAPACITY = 2;
    private final ArrayBlockingQueue<Request> requests = new ArrayBlockingQueue<>(CAPACITY);
    public void receiveRequest(Request request) {
        try {
            requests.put(request);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public synchronized Request getRequest() {
        try {
            return requests.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
