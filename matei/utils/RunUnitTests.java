package matei.utils;


import java.lang.reflect.*;
import java.util.Iterator;
import matei.utils.UnitTest;

public class RunUnitTests {
	public static void
	require(boolean requirement, int exitCode, String errmsg) {
		if(!requirement) {
			System.err.println(errmsg);
			System.exit(exitCode);
		}
	}

	public static void main(String[] args) {
		require(args.length == 1, 1,
		"Usage: RunUnitTests qualified-class");
		try {
			Class c = Class.forName(args[0]);
			// Only find the inner classes
			// declared in the current class:
			Class[] classes = c.getDeclaredClasses();
			Class ut = null;
			for(int j = 0; j < classes.length; j++) {
				// Skip inner classes that are
				// not derived from UnitTest:
				if (!UnitTest.class.isAssignableFrom(classes[j])) {
					continue;
				}
				ut = classes[j];
				break; //find the first test class only
			}
			// If it found an inner class,
			// that class must be static:
			if(ut != null) {
				require(
				Modifier.isStatic(ut.getModifiers()), 1,
				"inner UnitTest class must be static");
			}
			// If it couldn't find the inner class,
			// maybe it's a regular class (for black-
			// box testing):
			if(ut == null) {
				if(UnitTest.class.isAssignableFrom(c)) {
					ut = c;
				}
			}
			require(ut != null, 2,
				"No UnitTest class found");
			require(Modifier.isPublic(ut.getModifiers()), 1,
				"UnitTest class must be public");
			Method[] methods = ut.getDeclaredMethods();
			for(int k = 0; k < methods.length; k++) {
				Method m = methods[k];
				// Ignore overriden UnitTest methods:
				if(m.getName().equals("cleanup")) {
					continue;
				}
				// Only public methods with no
				// arguments and void return
				// types will be used as test code:
				if(m.getParameterTypes().length == 0 &&
				   m.getReturnType() == void.class &&
				   Modifier.isPublic(m.getModifiers())) {
					// The name of the test is
					// used in error messages;
					UnitTest.testID = m.getName();
					Object test = ut.newInstance();
					m.invoke(test, new Object[0]);
					((UnitTest)test).cleanup();
				}
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
			// Any exception will return a nonzero
			// value to the console, so that
			// 'make' will abort:
			System.exit(1);
		}
		// After all tests in this class are run,
		// display any results. If there were errors,
		// abort 'make' by returning a nonzero value.
		if(UnitTest.errors.size() != 0) {
			Iterator it = UnitTest.errors.iterator();
			while(it.hasNext()) {
				System.err.println(it.next());
			}
			System.exit(1);
		}
		System.out.println("Success");
	}
}