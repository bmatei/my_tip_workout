package c4.smart_proxy;

import matei.utils.UnitTest;
import c4.proxy.ProxyBase;

public class SmartProxy implements ProxyBase {
	private int fcnt = 0;
	private int gcnt = 0;
	private int hcnt = 0;

	private Subject s = new Subject();

	public void f() {
		s.f();
		fcnt++;
	}
	public void g() {
		s.g();
		gcnt++;
	}
	public void h() {
		s.h();
		hcnt++;
	}

	public static class Test extends UnitTest {
		SmartProxy sp = new SmartProxy();
		public static final int TEST_SIZE = 2;
		public void testF() {
			for(int i = 0; i < TEST_SIZE; ++i) {
				myAssert(sp.fcnt == i);
				sp.f();
			}
			myAssert(sp.fcnt == TEST_SIZE);
		}
		public void testG() {
			for(int i = 0; i < TEST_SIZE; ++i) {
				myAssert(sp.gcnt == i);
				sp.g();
			}
			myAssert(sp.gcnt == TEST_SIZE);
		}
		public void testH() {
			for(int i = 0; i < TEST_SIZE; ++i) {
				myAssert(sp.hcnt == i);
				sp.h();
			}
			myAssert(sp.hcnt == TEST_SIZE);
		}
	}

}

class Subject implements ProxyBase {
	public void f() {
		System.out.println("Subject.f()");
	}
	public void g() {
		System.out.println("Subject.g()");
	}
	public void h() {
		System.out.println("Subject.h()");
	}
}