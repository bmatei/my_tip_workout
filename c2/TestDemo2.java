package c2;

import matei.utils.UnitTest;
import c2.TestDemo;

public class TestDemo2 extends TestDemo {
	public TestDemo2(String s) { super(s); }

	public static class Test extends UnitTest {
		public void testA() {
			System.out.println("TestDemo2.testA");
			myAssert(1 + 1 == 2);
		}
		public void testB() {
			System.out.println("TestDemo2.testB");
			myAssert(2 * 2 == 4);
		}
	}
}