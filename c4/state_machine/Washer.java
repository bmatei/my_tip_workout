package c4.state_machine;

public class Washer extends StateMachine {
	private int i = 0;
	// The state table:
	private State states[] = {
		new Wash(), new Spin(),
		new Rinse(), new Spin(),
	};
	public Washer() { runAll();}
	public boolean changeState() {
		if(i < states.length) {
			currentState = states[i++];
			return true;
		} else {
			return false;
		}
	}
}