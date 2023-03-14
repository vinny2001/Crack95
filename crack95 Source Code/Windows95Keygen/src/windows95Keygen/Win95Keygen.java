// Author: Vincenzo D'Aria - 3/13/2023
// Free for distribution or any public & private use as long as author is given due credit for source code
// "Crack95"

package windows95Keygen;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Win95Keygen {
	
	private static int bound = 10;
	
   /*
	* First 3 digits of key must be any number other than any of the following blacklisted keys
	* If they are blacklisted, recursively call function again to generate new three digits
	*/
	
	private static void firstThreeDigits() {
		
		final int[] key1 = new int[3];
		final int[][] blackListArray = {{3,3,3},{4,4,4},{5,5,5},{6,6,6},{7,7,7},{8,8,8},{9,9,9}};

		for(int i = 0; i < 3; i++) {
			Random rand = new Random();
			int digit = rand.nextInt(bound);
			key1[i] = digit;
		}
		if(Arrays.equals(key1, blackListArray[0])|| Arrays.equals(key1, blackListArray[1])|| Arrays.equals(key1, blackListArray[2])|| Arrays.equals(key1, blackListArray[3]) || Arrays.equals(key1, blackListArray[4]) || Arrays.equals(key1, blackListArray[5]) || Arrays.equals(key1, blackListArray[6])) {
			firstThreeDigits();
		}else {
			for(int i = 0; i < key1.length; i++) {
				System.out.print(key1[i]);
			}
		}
	}
	
	/*
	 * The first segment of an OEM key is a 3-digit number between 1 to 366 inclusive,
	 * also appended with a number between 95-02 inclusive
	 * e.g: 35598
	 */
	
	private static void oemfirstSegment() {
		
		int oemHighBound = 366;
		int oemLowBound = 1;
		int digit1 = (int)Math.floor(Math.random()*((oemHighBound - oemLowBound + 1) + oemLowBound));
		
		Random rand2 = new Random();
		int[] items = {95, 96, 97, 98, 99, 00, 01, 02};
		int digit2 = rand2.nextInt(items.length);
		
		String digit1LeadZeros = String.format("%03d" , digit1);
		System.out.print(digit1LeadZeros);
		
		String digitTwoLeadZeros = String.format("%02d" , items[digit2]);
		System.out.print(digitTwoLeadZeros);
	}
	
	/*
	 * Last 7 digits of the key must add up to a sum mod 7 that == 0
	 * Last digit of this partial key cannot be 0 or >=8
	 * Else, recursively call function again to generate new seven digits
	 */
	
	private static void lastSevenDigits() {
		final int[] key2 = new int[7];
		
		for (int i = 0; i < 6; i++) {
			Random rand = new Random();
			int digit = rand.nextInt(bound);
			key2[i] = digit;
		}
		
		int digitSeven[] = {1,2,3,4,5,6,7};
		Random randomSeventhDigit = new Random();
		int digit7 = randomSeventhDigit.nextInt(digitSeven.length);
		key2[6] = digitSeven[digit7];
		
		int sum = 0;
		final int sumArray[] = new int [key2.length];
		
		for(int i=0; i<key2.length; i++){
	         sumArray[i] = key2[i];
	         sum += sumArray[i];
	    }
		if (sum % 7 != 0) {
			lastSevenDigits();
		}else {
			for(int i=0; i < key2.length; i++){
		         System.out.print(key2[i]);
		    }
		}
	}
	
	/*
	 * Similar to lastSevenDigits(), but for the OEM key,
	 * the 7 digit number must begin with zero.
	 * Function checks the rest of the array, making sure the sum of the partial key mod 7 == 0
	 */
	
	private static void oemMod7Check() {
		final int[] key2 = new int[7];
		
		for (int i = 1; i < 7; i++) {
			key2[0] = 0;
			Random rand = new Random();
			int digit = rand.nextInt(bound);
			key2[i] = digit;
		}
		
		int sum = 0;
		final int sumArray[] = new int [key2.length];
		
		for(int i=0; i<key2.length; i++){
	         sumArray[i] = key2[i];
	         sum += sumArray[i];
	    }
		if (sum % 7 != 0) {
			oemMod7Check();
		}else {
			for(int i=0; i < key2.length; i++){
		         System.out.print(key2[i]);
		    }
		}
	}
	
	/*
	 * The last five digits of an OEM key are 5 unchecked digits (so range of that number is 0 <= N <= 99999
	 */
	private static void oemLast5Digits() {
		Random rand = new Random();
		int range = 100000;
		int last5OEM = rand.nextInt(range);
		
		String last5OEMLeadZeros = String.format("%05d" , last5OEM);
		System.out.print(last5OEMLeadZeros);
	
	}
	
	/*
	 * Call The above functions respectively to generate an entire CD key or an OEM key
	 * (Depending on the appropriate Windows 95 edition)
	 */
	
	public static void main (String[]args){
		
		System.out.println("\n\nWelcome to the Windows 95 Product Key Generator!");
		System.out.println("Please select which product key you would like to obtain\n");
		System.out.println("1: CD Key (Please Press 1)");
		System.out.println("2: OEM Key (Please Press 2)\n");
		
		Scanner scan = new Scanner(System.in);
		String response = scan.next();
		
		switch (response){
			case "1":
				System.out.print("Your randomly-generated CD key: ");
				firstThreeDigits();
				System.out.print("-");
				lastSevenDigits();
				System.out.println("\n");
				break;
			case "2":
				System.out.print("Your randomly-generated OEM key: ");
				oemfirstSegment();
				System.out.print("-OEM-");
				oemMod7Check();
				System.out.print("-");
				oemLast5Digits();
				System.out.println("\n");
				break;
				
			default:
				System.out.print("Your input was incorrect, please try again!");
				main(args);
		}
		scan.close();
	}
}