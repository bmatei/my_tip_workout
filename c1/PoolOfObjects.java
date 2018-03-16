package c1;

import matei.utils.UnitTest;

public final class PoolOfObjects {
	public static void main(String[] args) {
		for(int i = 0; i < 40; ++i) {
			PoolOfObjects x = PoolOfObjects.getReference(i);
			x.setValue(i);
			System.out.println(x.getValue());
		}
	}

	private final static int objNum = 40;
	private static PoolOfObjects[] objs = new PoolOfObjects[objNum];

	private int val = 0;
	private PoolOfObjects(){val = 0;}

	public static PoolOfObjects getReference(int pos) {
		if(pos >= 0 && pos < objNum) {
			if(objs[pos] == null) {
				objs[pos] = new PoolOfObjects();
			}
			return objs[pos];
		}
		return null;
	}

	public int getValue() { return val; }
	public void setValue(int x) { val = x; }

	public static class Test extends UnitTest {
		PoolOfObjects x;
		public void getTheWholePool() {
			for(int i = 0; i < 40; ++i) {
				x = PoolOfObjects.getReference(i);
				x.setValue(i);
				myAssert(x.getValue() == i);
			}
		}

		public void getOutOfPool() {
			x = PoolOfObjects.getReference(41);
			myAssert(x == null);
			x = PoolOfObjects.getReference(-1);
			myAssert(x == null);
		}
	}

}