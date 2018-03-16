package matei.utils;

import java.util.ArrayList;

public class UnitTest {
	static String testID;
	static ArrayList<String> errors = new ArrayList<String>();

	protected void cleanup() {}
	protected final void myAssert(boolean condition) {
		if(!condition) {
			errors.add("failed: " + testID);
		}
	}
}