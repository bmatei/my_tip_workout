package c4.cow;

import matei.utils.UnitTest;

interface Copy {
	public Copy copyAdd(String val);
	public String getVal();
}

public class CopyOnWrite implements Copy {
	private CopySubject cs;

	public CopyOnWrite(){
		cs = new CopySubject("");
	}
	private CopyOnWrite(String arg) {
		cs = new CopySubject(arg);
	}
	private CopyOnWrite(CopySubject arg) {
		cs = arg;
	}

	public Copy copyAdd(String val) {
		CopyOnWrite rez = this;
		if(val != null) {
			rez = new CopyOnWrite(cs.getVal() + val);
		}
		return (Copy)rez;
	}

	public String getVal() {
		return cs.getVal();
	}

	public static class Test extends UnitTest{
		private CopyOnWrite c1;
		private CopyOnWrite c2;
		public void testCopyThenWrite() {
			c1 = new CopyOnWrite("c1");
			c2 = (CopyOnWrite)c1.copyAdd(null);
			myAssert(c1 == c2);
			c2 = (CopyOnWrite)c2.copyAdd("<c2");
			myAssert(c1 != c2);
			System.out.println("c1: " + c1.getVal());
			System.out.println("c2: " + c2.getVal());

		}
	}

	public static void main(String[] args) {
		new Test().testCopyThenWrite();
	}

}

class CopySubject implements Copy {
	private String val = "";
	public CopySubject(String arg) {
		val = arg;
	}
	public Copy copyAdd(String val) {
		this.val = this.val + val;
		return this;
	}
	public String getVal() {
		return val;
	}
}