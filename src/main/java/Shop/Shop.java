package Shop;

import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

public class Shop {
    AtomicLong totalValue = new AtomicLong();

    public int[] generateArray() {
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = ((int) (Math.random() * 12));
        }
        return array;
    }

    public void addValue(int[] list) {
        long result = IntStream.of(list).sum();
        long total = totalValue.addAndGet(result);
        System.out.println(total);
    }

    public long getCurrent() {
        return totalValue.get();
    }

    public static void main(String[] args) throws InterruptedException{

        Shop shop = new Shop();
        Thread thread1 = new Thread(null, () -> shop.addValue(shop.generateArray()), "shop 1");
        Thread thread2 = new Thread(null, () -> shop.addValue(shop.generateArray()), "shop 2");
        Thread thread3 = new Thread(null, () -> shop.addValue(shop.generateArray()), "shop 3");

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println(shop.getCurrent());

    }
}
