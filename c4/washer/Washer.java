package c4.washer;

import java.util.*;
import java.io.*;
import matei.utils.UnitTest;

class MapLoader {
	public static void load(Map m, Object[][] pairs) {
		for(int i = 0; i < pairs.length; i++) {
			m.put(pairs[i][0], pairs[i][1]);
		}
	}
}

interface State {
	void run(String input);
}

public class Washer {
	private State currentState;
	static HashMap states = new HashMap();
	public Washer() {
		states.put("Wash", new Wash());
		states.put("Rinse", new Rinse());
		states.put("Spin", new Spin());
		currentState = (State)states.get("Wash");
	}
	private void
	nextState(Map stateTable, String input) {
		currentState = (State)states.get(
			stateTable.get(input));
	}

	class TState implements State {
		protected HashMap stateTable = new HashMap();

		public void run(String input) {
			String name = getClass().toString();
			System.out.println(
				name.substring(
					name.lastIndexOf("$") + 1));
			nextState(stateTable, input);
		}
	}

	class Wash extends TState {
		{
			MapLoader.load(stateTable, new String[][] {
				{ "Wash", "Spin" },
				{ "Spin", "Spin" },
				{ "Rinse", "Rinse" }
			});
		}
	}

	class Spin extends TState {
		{
			MapLoader.load(stateTable, new String[][] {
				{ "Wash", "Wash" },
				{ "Spin", "Rinse" },
				{ "Rinse", "Rinse" }
			});
		}
	}

	class Rinse extends TState {
		{
			MapLoader.load(stateTable, new String[][] {
				{ "Wash", "Wash" },
				{ "Spin", "Spin" },
				{ "Rinse", "Spin" }
			});
		}
	}

	public void run() {
		try {
			BufferedReader inputStream =
				new BufferedReader(
					new FileReader("state_file"));
			while(inputStream.ready()) {
				String input =
					inputStream.readLine().trim();
				if(input != null) {
					currentState.run(input);
				}
			}
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}

	public static class Test extends UnitTest {
		Washer w = new Washer();
		public void testRun() { w.run(); }
	}

	public static void main(String[] args) {
		new Test().testRun();
	}
}