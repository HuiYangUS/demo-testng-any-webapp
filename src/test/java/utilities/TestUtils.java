package utilities;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestUtils {

	public static void pause(int time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// Do nothing
			System.out.println("Thread failed to sleep.");
		}
	}

	public static long getTimeStamp() {
		return Timestamp.valueOf(LocalDateTime.now()).getTime();
	}

	public static String getDateString() {
		return LocalDate.now().toString();
	}

}
