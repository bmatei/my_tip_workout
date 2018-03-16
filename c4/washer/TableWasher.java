package c4.washer;

import matei.utils.UnitTest;
import matei.utils.TransitionTable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TableWasher {
	private Object[][][] transitionTable = {
		{
			{"Wash"},
			{"Wash", "Spin"},
			{"Spin", "Spin"},
			{"Rinse", "Rinse"}
		},
		{
			{"Spin"},
			{"Wash", "Wash"},
			{"Spin", "Rinse"},
			{"Rinse", "Rinse"}
		},
		{
			{"Rinse"},
			{"Wash", "Wash"},
			{"Spin", "Spin"},
			{"Rinse", "Spin"}
		}
	};

	TransitionTable.StateChanger m =
		new TransitionTable.StateChanger(
			transitionTable);
	String currentState = "Wash";

	public void run() {
		try {
			BufferedReader inputStream =
				new BufferedReader(
					new FileReader("state_file"));
			while(inputStream.ready()) {
				String input =
					inputStream.readLine().trim();
				if(input != null) {
					System.out.print("current = "
						+ currentState);
					System.out.print(", input = "
						 + input);
					currentState = 
					(String)m.nextState(currentState, input);
					System.out.println(
						", new state = "
						+ currentState);
				}
			}
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}

	public static class Test extends UnitTest {
		TableWasher tw = new TableWasher();
		public void testRun() {
			tw.run();
		}
	}

	public static void main(String[] args) {
		new Test().testRun();
	}
}