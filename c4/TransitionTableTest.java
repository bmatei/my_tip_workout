package c4;

import matei.utils.UnitTest;
import matei.utils.TransitionTable;

public class TransitionTableTest extends UnitTest {
	Object[][][] transitionTable = {
		{
			{"one"},
			{"one", "two"},
			{"two", "two"},
			{"three", "two"}
		},
		{
			{"two"},
			{"one","three"},
			{"two","three"},
			{"three","three"},
		},
		{
			{"three"},
			{"one","one"},
			{"two","one"},
			{"three","one"},
		}
	};
	TransitionTable.StateChanger m =
		new TransitionTable.StateChanger(
			transitionTable);
	public void testStateChangeProgression() {
		System.out.println(m);
		String current = "one";
		String[] inputs = {"one", "two", "three"};
		for(int i = 0; i < 20; i++) {
			String input = inputs[
				(int)(Math.random() * inputs.length)];
			System.out.print("input = " + input);
			current = (String)m.nextState(current, input);
			System.out.println(", new state = " + current);
		}
	}
	public static void main(String[] args) {
		new TransitionTableTest().testStateChangeProgression();
	}
}