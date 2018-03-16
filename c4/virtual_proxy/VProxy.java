package c4.virtual_proxy;

import matei.utils.UnitTest;
import c4.proxy.ProxyBase;

public class VProxy implements ProxyBase {
	private Subject s = null;

	public void f() {
		if(s == null) {
			s = new Subject();
		}
		s.f();
	}

	public void h() {
		if(s == null) {
			s = new Subject();
		}
		s.h();
	}

	public void g() {
		if(s == null) {
			s = new Subject();
		}
		s.g();
	}

	public static class Test extends UnitTest {
		VProxy v = new VProxy();

		public void testNotInitialized() {
			myAssert(v.s == null);
		}
		public void testRunFThenInitialized() {
			myAssert(v.s == null);
			v.f();
			myAssert(v.s != null);
		}
		public void testRunGThenInitialized() {
			myAssert(v.s == null);
			v.g();
			myAssert(v.s != null);
		}
		public void testRunHThenInitialized() {
			myAssert(v.s == null);
			v.h();
			myAssert(v.s != null);
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