package toyBank.request;

public class Request {
    private final String clientName;
    private final int amount;
    private final RequestType requestType;

    public Request(String clientName, int amount, RequestType requestType) {
        this.clientName = clientName;
        this.amount = amount;
        this.requestType = requestType;
    }

    public int getAmount() {
        return amount;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    @Override
    public String toString() {
        return "Request{" +
                "clientName='" + clientName + '\'' +
                ", amount=" + amount +
                ", requestType=" + requestType +
                '}';
    }
}
