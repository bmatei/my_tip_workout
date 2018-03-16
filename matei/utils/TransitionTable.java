package matei.utils;

import java.util.*;

public class TransitionTable {
	public static Map
	build(Object[][][] table, Map m) {
		for(int i = 0; i < table.length; i++) {
			Object[][] row = table[i];
			Object key = row[0][0];
			Map val = new HashMap();
			for(int j = 1; j < row.length; j++) {
				val.put(row[j][0], row[j][1]);
			}
			m.put(key, val);
		}
		return m;
	}
	public interface Transitioner {
		Object nextState(Object curr, Object input);
	}
	// Default implementation and example
	// of nextState() method code:
	public static class StateChanger implements Transitioner {
		private Map map = new HashMap();
		public StateChanger(Object[][][] table) {
			TransitionTable.build(table, map);
		}
		public Object nextState(Object curr, Object input) {
			return ((Map)map.get(curr)).get(input);
		}
		public String toString() {
			return map.toString();
		}
	}
}