package z.algorithm;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		int[] z;
		
		String pattern, text, concatenatedText, output = "\nPattern found at indices = { ";
		Scanner scanner = new Scanner(System.in);		// initializing scanner...
		
		System.out.print("Enter the pattern = ");
		
		pattern = scanner.nextLine();		// taking pattern as input from user...
		
		System.out.print("Enter the text = ");
		
		text = scanner.nextLine();		// taking text as input from user...
		
		scanner.close();		// closing scanner...
		System.out.println("\nConcatenating strings...");
		
		concatenatedText = pattern + '$' + text;		// concatenating two strings...
		
		System.out.println("Initializing array...");
		
		z = new int[concatenatedText.length()];		// initializing 'z' array...
		
		System.out.println("Generating values of the array...");
		
		generateZ(z, concatenatedText);		// generating 'z' array...
		
		for (int i = 0; i < z.length; i++) {
			if (z[i] == pattern.length()) {		// checking if z[i] is equal to the length of the pattern...
				output += (i - pattern.length() - 1) + ", ";		// adding the index/indices to the output...
			}
		}
		
		output = output.substring(0, output.lastIndexOf(", ")) + " }";		// polishing output by removing the last comma and adding closing curly brace...
		
		System.out.println(output);		// printing the output...
	}
	
	private static void generateZ(int[] z, String concatenatedText) {
		int l = 0, r = 0, k = 0;		// initializing local variables...

		for (int i = 1; i < concatenatedText.length(); i++) {
			if (i > r) {		// nothing matches... z[i] needs to be calculated...
				l = r = i;

				while (r < concatenatedText.length() && concatenatedText.charAt(r - l) == concatenatedText.charAt(r)) {		// initially (r - l) = 0...
					r++;
				}

				z[i] = r - l;
				r--;
			}
			else {
				k = i - l;		// 'k' corresponds to number which matches in [l, r] interval...

				if (z[k] < r - i + 1) {		// z[i] will be equal to z[k] if z[k] is less than remaining interval...
					z[i] = z[k];
				}
				else {		// starts from 'r' and checks manually...
					l = i;

					while (r < concatenatedText.length() && concatenatedText.charAt(r - l) == concatenatedText.charAt(r)) {
						r++;
					}

					z[i] = r - l;
					r--;
				}
			}
		}
	}
	
}