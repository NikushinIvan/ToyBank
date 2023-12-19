package toyBank.request;

import java.util.Random;

public enum RequestType {
    CREDIT, REPAYMENT;

    private static final Random random = new Random();

    public static RequestType getRandomRequestType() {
        RequestType[] requestTypes = values();
        return requestTypes[random.nextInt(requestTypes.length)];
    }
}
