import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StackMemoryTest {
    private void timesCalled(int time){
        System.out.println("This is time: "+time);
        timesCalled(time+1);
    }

    @Test
    public void maxStackSizeAllowed(){
        timesCalled(0);
        assertTrue(true);
    }
}
