package c3;

public abstract class ApplicationFramework {
	public ApplicationFramework() {
		templateMethod();
	}
	abstract void customize1();
	abstract void customize2();
	private void templateMethod() {
		for(int i = 0; i < 5; ++i) {
			customize1();
			customize2();
		}
	}
}