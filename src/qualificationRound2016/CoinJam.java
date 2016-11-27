package qualificationRound2016;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class CoinJam {
	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		final int t = Integer.parseInt(in.nextLine());  // Scanner has functions to read ints, longs, strings, chars, etc.
		String line  = in.nextLine();
		final int n = Integer.parseInt(line.split(" ")[0]);
		final int j = Integer.parseInt(line.split(" ")[1]);
		CoinJam coinjam = new CoinJam();
		
		String[] m = StringUtils.repeat("0", n).split("");
		m[0] = "1"; m[m.length - 1] = "1";
		String binary = String.join("", m);

		System.out.println("Case #1:");
		String maxBinary = StringUtils.repeat("1", n);
		int count = 0;
		while(!binary.equals(maxBinary) && count < j) {
			ArrayList<String> divisors = new ArrayList<>();
			for (int i = 2; i <= 10; i++) {
				long check = coinjam.toDecimal(binary, i);
				Long divisor = coinjam.isPrime(check); 
				if (divisor == 0) {
					break;
				} else {
					divisors.add(divisor.toString());
				}
			}
			if (divisors.size() == 9) {
				// binary is jamcoin
				divisors.add(0, binary);
				System.out.println(String.join(" ", divisors));
				count++;
			}
			binary = coinjam.toBinary(coinjam.toDecimal(binary, 2) + 2);
		}
	}

	private long toDecimal(String binary, int i) {
		ArrayList<String> array = new ArrayList<String>(Arrays.asList(binary.split("")));
		Collections.reverse(array);
		int j = 0;
		long decimal = 0;
		for (String c : array) {
			decimal += Integer.parseInt(c) * Math.pow(i, j++);
		}
		return decimal;
	}
	
	private String toBinary(long decimal) {
		ArrayList<String> binary = new ArrayList<>();
		while (decimal != 0) {
			Long remain = decimal % 2;
			binary.add(remain.toString());
			decimal /= 2;
		}
		Collections.reverse(binary);
		return String.join("", binary);
	}
	
	private Long isPrime(long check) {
		long divisor = 0;
		for (long i = 2; i < check; i++) {
			if (i * i > check)  break;
			
			if (check % i == 0) {
				divisor = i;
				break;
			}
		}
		return divisor;	
	}
}
