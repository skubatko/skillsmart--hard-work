package ru.skubatko.dev.skillsmart;

import edu.berkeley.cs.jqf.fuzz.Fuzz;
import edu.berkeley.cs.jqf.fuzz.JQF;
import org.junit.runner.RunWith;

@RunWith(JQF.class)
public class ConstructorExceptionTest {

    class Foo {
        public Foo(int x) throws Exception {
            this(thr0w());
        }

        public Foo(boolean b) {
            // Do nothing
        }
    }

    static boolean thr0w() throws Exception {
        throw new Exception();
    }

    @Fuzz
    public void testThrowingConstructors(int x) throws Exception {
        new Foo(x);
    }
}
