import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MemoryLeakTest {
    private static class MyObjectKey {
        private final String text;

        public MyObjectKey(String text) {
            this.text = text;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MyObjectKey myObject = (MyObjectKey) o;
            return Objects.equals(text, myObject.text);
        }

    }

    @Test
    public void memoryLeak() {
        Map<MyObjectKey, String> map = new HashMap<>();
        int numberOfKeysAded = 0;
        assertEquals(new MyObjectKey("key"), new MyObjectKey("key"));
        while (true) {
            map.put(new MyObjectKey("key"), "anyValue");
            System.out.println("Number of keys added " + numberOfKeysAded);
            numberOfKeysAded++;
        }
    }
}
