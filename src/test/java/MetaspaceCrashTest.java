import javassist.CannotCompileException;
import org.junit.jupiter.api.Test;

public class MetaspaceCrashTest {
    static javassist.ClassPool cp = javassist.ClassPool.getDefault();

    @Test
    public void crashMetaspace() throws CannotCompileException {
        for (int i=0;;i++) {
            Class c = cp.makeClass("eu.plumbr.demo.Generated" + i).toClass();
            System.out.println(c);
        }
    }
}
