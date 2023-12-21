package toyBank.bankBackSystem;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;

public class StorageSystem implements Callable<Long> {
    private final AtomicLong balance;
    private final Random random = new Random();
    private final int numberSystem;

    public StorageSystem(int numberSystem) {
        this.numberSystem = numberSystem;
        this.balance = new AtomicLong((random.nextInt(5) + 1) * 1_000);
    }

    @Override
    public Long call() throws Exception {
        long delay = random.nextInt(5_000) + 5_000;
        Thread.sleep(delay);
        System.out.println("StorageSystem"+ numberSystem + " completed counting for " + delay + " ms");
        return balance.get();
    }
}
