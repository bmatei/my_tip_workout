package c4.proxy;

import matei.utils.UnitTest;

public class ProxyDemo extends UnitTest {
	Proxy p = new Proxy();
	public void testProxyFuncs() {
		p.f();
		p.g();
		p.h();
	}
	public static void main(String[] args) {
		new ProxyDemo().testProxyFuncs();
	}
}