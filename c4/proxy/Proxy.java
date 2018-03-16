package c4.proxy;

public class Proxy implements ProxyBase {
	private ProxyBase implementation;
	public Proxy() {
		implementation = new Implementation();
	}
	// Pass method calls to the implementation:
	public void f() { implementation.f(); }
	public void g() { implementation.g(); }
	public void h() { implementation.h(); }
}
