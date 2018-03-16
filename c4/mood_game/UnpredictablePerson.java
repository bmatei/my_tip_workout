package c4.mood_game;

import matei.utils.UnitTest;
import java.util.Random;
import java.util.Scanner;

public class UnpredictablePerson implements Mood {
	Mood now = null;
	Random rg = new Random();
	Mood[] moods = {
		new Competitive(),
		new Anger(),
		new Shocked(),
		new Loving(),
		new Submissive(),
		new Abandoned(),
		new Excited(),
	};
	int i = 0;
	public UnpredictablePerson() {
	}

	public void run() {
		Scanner in = new Scanner(System.in);
		String s = null;
		while(in.hasNextLine()) {
			s = in.nextLine();
			if(s != null) {
				if(s.equals("hello")) {
					hello();
				} else {
					System.err.println("I don't understand that");
				}
			}
		}
	}

	public void hello() {
		newMood();
		now.hello();
	}

	private void newMood() {
		i = rg.nextInt(moods.length);
		now = moods[i];
	}

	public static class Test extends UnitTest {
		UnpredictablePerson up = new UnpredictablePerson();
		public void testNothing() {

		}
	}
	public static void main(String[] args) {
		UnpredictablePerson p = new UnpredictablePerson();
		p.run();
	}
}