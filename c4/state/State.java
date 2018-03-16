package c4.state;

public class State implements StateBase {
	private StateBase implementation;
	public State(StateBase imp) {
		implementation = imp;
	}

	public void changeImp(StateBase newImp) {
		implementation = newImp;
	}

	public void f() { implementation.f(); }
	public void g() { implementation.g(); }
	public void h() { implementation.h(); }
}