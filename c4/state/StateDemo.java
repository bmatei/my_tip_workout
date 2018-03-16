package c4.state;

import matei.utils.UnitTest;

public class StateDemo extends UnitTest {
	static void run(State b) {
		b.f();
		b.g();
		b.h();
	}
	State b = new State(new Implementation1());
	public void testStateChange() {
		run(b);
		b.changeImp(new Implementation2());
		run(b);
	}
	public static void main(String[] args) {
		new StateDemo().testStateChange();
	}
}