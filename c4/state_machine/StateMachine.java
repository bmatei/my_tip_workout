package c4.state_machine;

public abstract class StateMachine {
	protected State currentState;
	abstract protected boolean changeState();
	protected final void runAll() {
		while(changeState()) { // Customizable
			currentState.run();
		}
	}
}