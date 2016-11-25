package qualificationRound2016;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.io.*;

public class CountingSheep {
  public static void main(String[] args) {
	Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
	int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.

	Set<String> asleep = IntStream.rangeClosed(0, 9).mapToObj(i -> Integer.valueOf(i).toString()).collect(Collectors.toSet());
	// loop for each test case
	for (int i = 1; i <= t; ++i) {
		Set<String> seen = new HashSet<String>();
		final Integer n = in.nextInt();
		int count = 1;
		if (n == 0) {
			System.out.println(String.format("Case #%d: INSOMNIA", i));
			continue;
		}
		while (true) {
			Integer m = n * count++;
			Collections.addAll(seen, m.toString().split(""));
			if (seen.containsAll(asleep)) {
				System.out.println(String.format("Case #%d: %d", i, m));
				break;
			}
		}
	}
  }
}
