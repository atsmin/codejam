package qualificationRound2016;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pancakes {
	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		final int t = Integer.parseInt(in.nextLine());  // Scanner has functions to read ints, longs, strings, chars, etc.
		
		// loop for each test case
		for (int i = 1; i <= t; ++i) {
			String stack = in.nextLine();
			Pancakes pancakes = new Pancakes();
			int count = 0;
			while(true){
				// 1. remove continuous '+' of the bottom.
				stack = stack.replaceAll("\\++$", "");
				if (stack.isEmpty()) {
					// all cakes are "+"
					System.out.println(String.format("Case #%d: %d", i, count));
					break;  
				}
				// 2a. when stack starts with '-', flip all cakes.
				if (stack.startsWith("-")) {
					stack = pancakes.maneuver(stack, stack.length());
				}
				// 2b. when stack starts with '+', flip continuous '+' of the top.
				else {
					Matcher m =  Pattern.compile("^\\++").matcher(stack);
					m.find();
					stack = pancakes.maneuver(stack, m.end());
				}
				count++;

			}
		}
	}

	private String maneuver(String stack, int index){
		String[] target = stack.substring(0, index).split("");
		ArrayList<String> result = new ArrayList<>();
		for (String cake : target) {
			result.add(flip(cake));
		}
		Collections.reverse(result);
		return String.join("", result) + stack.substring(index);
	}

	private String flip(String cake) {
		if (cake.equals("+")){
			return "-";
		} else {
			return "+";
		}
	}
}
