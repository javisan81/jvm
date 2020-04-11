import org.junit.jupiter.api.Test;

public class HeapMemoryCrashTest {
    public static void heapCheck() throws InterruptedException {
        Runtime rt = Runtime.getRuntime();
        long total = rt.totalMemory();
        long free = rt.freeMemory();
        long used = total - free;
        System.out.format("Total memory: %s%n", total);
        System.out.format(" Free memory: %s%n", free);
        System.out.format(" Used memory: %s%n", used);
        Thread.sleep(500);
    }

    @Test
    public void memoryCrash() throws InterruptedException {
        int max = 1000;
        Object[] arr = new Object[max];
        heapCheck();
        for (int n = 0; n < max; n++) {
            arr[n] = new long[10 * 1024 * 128];
            System.out.println((n + 1) + ": "
                    + ((n + 1) * 10) + " MB of objects created.");
            heapCheck();
        }
    }
}
