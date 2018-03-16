package c1;

import matei.utils.UnitTest;

final class Singleton {
	private static Singleton s = null;
	private int i;
	private Singleton(int x) { i = x; }
	public static Singleton getReference() {
		if(s == null) {
			s = new Singleton(47);
		}
		return s;
	}
	public int getValue() { return i; }
	public void setValue(int x) { i = x; }
}

public class SingletonPattern {
	public static void main(String[] args) {
		Singleton s = Singleton.getReference();
		System.out.println(s.getValue());
		Singleton s2 = Singleton.getReference();
		s2.setValue(9);
		System.out.println(s.getValue());
		try {
			//Singleton s3 = (Singleton)s2.clone();
		} catch(Exception e) {
			e.printStackTrace(System.err);
		}
	}
	public static class Test extends UnitTest {
		Singleton s1 = Singleton.getReference();
		Singleton s2 = Singleton.getReference();

		public void getInitialValue() {
			myAssert(s1.getValue() == 47);
		}
		public void setValue() {
			s2.setValue(9);
			myAssert(s1.getValue() == 9);
		}
		/*
		public void forceClone() throws Exception {
			try {
				Singleton s2 = (Singleton)s2.clone();
			} catch(Exception e) {
				System.out.println("Successfully caught exception: " + e.getStackTrace());
				return;
			}
			throw new Exception("Singleton is clonable");
		}
		//*/
	}
}