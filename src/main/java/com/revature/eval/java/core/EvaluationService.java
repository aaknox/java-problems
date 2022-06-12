package com.revature.eval.java.core;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.swing.text.DateFormatter;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		String reversed = "";
		char[] letters = string.toCharArray();

		for (int i = letters.length - 1; i > -1; i--) {
			reversed += letters[i];
		}

		return reversed;
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		String result = "";
		// replace dashes with whitespace in phrase
		phrase = phrase.replace("-", " ");

		// separate phrase words by whitespace
		String[] words = phrase.split(" ");

		// iterate through words array
		for (int i = 0; i < words.length; i++) {

			// get and capitalize the first letter in each word
			char letter = words[i].toUpperCase().charAt(0);

			// add letter to acronym
			result += letter;
		}
		// return acronym
		return result;
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			return (this.sideOne == this.sideTwo && this.sideTwo == this.sideThree) ? true : false;
		}

		public boolean isIsosceles() {
			int countOfSameSides = 0;

			if (this.sideOne == this.sideTwo) {
				countOfSameSides++;
			} else if (this.sideOne == this.sideThree) {
				countOfSameSides++;
			} else if (this.sideTwo == this.sideThree) {
				countOfSameSides++;
			}

			// false if no side is the same value => count = 0
			// true if at least two sides are same value => count is >= 1
			return (countOfSameSides >= 1) ? true : false;
		}

		public boolean isScalene() {
			return (this.sideOne != this.sideTwo && this.sideTwo != this.sideThree && this.sideOne != this.sideThree)
					? true
					: false;
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		// make a hashmap of letter values
		Map<Character, Integer> scrabbleMap = new HashMap<Character, Integer>();
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

		for (int i = 0; i < alphabet.length; i++) {
			switch (alphabet[i]) {
			case 'q':
			case 'z':
				scrabbleMap.put(alphabet[i], 10);
				break;

			case 'j':
			case 'x':
				scrabbleMap.put(alphabet[i], 8);
				break;

			case 'k':
				scrabbleMap.put(alphabet[i], 5);
				break;

			case 'f':
			case 'h':
			case 'v':
			case 'w':
			case 'y':
				scrabbleMap.put(alphabet[i], 4);
				break;

			case 'b':
			case 'c':
			case 'm':
			case 'p':
				scrabbleMap.put(alphabet[i], 3);
				break;

			case 'g':
				scrabbleMap.put(alphabet[i], 2);
				break;

			default:
				scrabbleMap.put(alphabet[i], 1);
				break;
			}
		}

		// iterate through each character in string
		int score = 0;
		for (char letter : string.toLowerCase().toCharArray()) {
			// add letter's points to total word points
			score += scrabbleMap.get(letter);
		}

		// return total word points
		return score;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {

		// remove the country code if present as first character in string
		if (string.charAt(0) == '1' || string.charAt(0) == '+') {
			string = string.substring(0);
		}
		// now remove the special characters and whitespaces from phone number
		string = string.replaceAll("[^A-Za-z0-9]", "");
		String whitespace = " ";
		string = string.replace(whitespace, "");

		// check if valid phone number
		if (string.length() != 10) {
			string = null;
		}

		// if number was nulled out, then throw exception
		if (string == null) {
			throw new IllegalArgumentException();
		}

		// else return number reformatted
		return string;
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		// remove commas and new lines from string
		string = string.replace(",", " ");
		string = string.replace("\n", " ");
		String[] words = string.split(" ");
		Map<String, Integer> count = new HashMap<String, Integer>();
		for (String word : words) {
			if (count.containsKey(word)) {
				if (word.isBlank() == false) {
					// increment count under given key by one in map
					count.replace(word, count.get(word) + 1);
				}
			} else {
				if (word.isBlank() == false) {
					// add new entry into map
					count.put(word, 1);
				}
			}
		}
		return count;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T> {
		private List<T> sortedList;

		public int indexOf(T t) {
			// define our left and rightmost elements in list
			int[] array = new int[sortedList.size()];
			for (int i = 0; i < array.length; i++) {
				array[i] = Integer.parseInt(String.valueOf(sortedList.get(i)));
			}

			int target = Integer.parseInt(String.valueOf(t));
			int left = 0;
			int right = array.length - 1;

			while (left <= right) {
				// define the mid point of both sides
				int mid = left + (right - left) / 2;

				// check if target located at midpoint
				if (array[mid] == target) {
					return mid;
				}

				if (array[mid] < (int) target) {
					// if target is greater than midpoint, ignore the left side
					left = mid + 1;
				} else {
					// if target is less than midpoint, ignore the right side
					right = mid - 1;
				}
			}

			return -1;
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word.
	 * 
	 * Rule 2: If a word begins with a consonant sound, move it to the end of the
	 * word, and then add an "ay" sound to the end of the word.
	 * 
	 * There are a few more rules for edge cases, and there are regional variants
	 * too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */

	public boolean isVowel(char letter) {
		String vowels = "aeiou";
		return vowels.indexOf(letter) != -1;
	}

	public String toPigLatin(String string) {
		int size = string.length();
		String pigLatin = "";
		int i = 0;
		String[] words = string.split(" ");
		for (String word : words) {
			for (i = 0; i < size; i++) {
				if (isVowel(word.charAt(i))) {
					break;
				}
			}

			if (i == size) {
				return "";
			}

			if (word == words[0]) {
				pigLatin = word.substring(i);
				pigLatin += word.substring(0, i);
				pigLatin += "ay";
			} else {
				pigLatin += " ";
				pigLatin += word.substring(i);
				pigLatin += word.substring(0, i);
				pigLatin += "ay";
			}
		}

		return pigLatin;
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * - 9 is an Armstrong number, because 9 = 9^1 = 9
	 * 
	 * - 10 is not an Armstrong number, because 10 != 1^2 + 0^2 = 2
	 * 
	 * - 153 is an Armstrong number, because: 153 = 1^3 + 5^3 + 3^3 = 1 + 125 + 27 =
	 * 153
	 * 
	 * - 154 is not an Armstrong number, because: 154 != 1^3 + 5^3 + 4^3 = 1 + 125 +
	 * 64 = 190
	 * 
	 * Write some code to determine whether a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		int result = 0;
		int digitCount = ((int) Math.floor(Math.log10(input))) + 1;
		char[] numbers = String.valueOf(input).toCharArray();

		for (char num : numbers) {
			result += ((int) Math.pow(Integer.parseInt(String.valueOf(num)), digitCount));
		}

		return (result == input) ? true : false;
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		List<Long> primeFactors = new ArrayList<>();

		for (int i = 2; i <= l; i++) {
			while (l % i == 0) {
				l /= i;
				primeFactors.add((long) i);
			}
		}

		return primeFactors;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives: Gur dhvpx oebja sbk whzcf
	 * bire gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives:
	 * The quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			String result = "";
			key %= 26;
			char current;

			for (int i = 0; i < string.length(); i++) {
				current = string.charAt(i);

				// check if current is letter or number
				if (Character.isLetter(current)) {
					// check if letter is capitalized or not
					if (Character.isUpperCase(current)) {
						// capitalized
						if (current + key > 'Z') {
							current = (char) (current + key);
							current = (char) (current - 26);
							result += current;
						} else {
							result += (char) (current + key);
						}
					} else {
						// lowercased
						if (current + key > 'z') {
							current = (char) (current + key);
							current = (char) (current - 26);
							result += current;
						} else {
							result += (char) (current + key);
						}
					}
				} else {
					// not a letter
					result += current;
				}
			}
			return result;
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */

	public int calculateNthPrime(int i) {
		int target, count, index;
		target = 1;
		count = 0;

		while (count < i) {
			target += 1;
			for (index = 2; index <= target; index++) {
				if (target % index == 0) {
					break;
				}
			}

			if (index == target) {
				count += 1;
			}
		}

		if (target == 1) {
			throw new IllegalArgumentException();
		}

		// next prime found!
		// System.out.println(String.format("Value of %dth prime number: %d", i,
		// target));
		return target;
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples: Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */

		public static String transform(String text) {
			// \W = any character that is not a word
			// here we are replace 1+ non-words with empty char
			return text.replaceAll("\\W+", "")
					// lowercase all letter in string
					.toLowerCase()
					// The codePoints() = gets a stream of code point values from the given sequence
					// returns the ASCII values of the characters passed as an argument
					.codePoints()
					// returns a stream consisting of the results of applying the given
					// function to the elements of this stream
					// true if char is letter value then return the reverse ASCII value of that
					// letter
					// and append to output
					// false return original ascii value of that character
					.map(code -> Character.isAlphabetic(code) ? 2 * ((int) 'a') + 25 - code : code)
					// collect() = allow to perform operations on our output results
					// = appending chars to output string using StringBuilder
					.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		}

		public static String encode(String string) {
			// regex explanation:
			/*
			 * CapturingGroup ZeroWidthPositiveLookbehind Sequence: match all of the
			 * followings in order EndOfPreviousMatch Repeat AnyCharacterExcept\n 5 times
			 */
			// meaning: every 5 characters will have a space between them as long as they
			// match regex pattern
			String output = String.join(" ", transform(string).split("(?<=\\G.{5})"));
			return output;
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			String output = transform(string);
			return output;
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		// System.out.println(string);
		String target = string.replaceAll("-", "");
		// System.out.println(target);
		char[] numbers = target.toCharArray();
		int count;
		int i = 0;
		Map<String, Character> map = new HashMap<>();
		for (count = 1; count <= 10; count++) {
			map.put("x" + count, numbers[i]);
			i++;
		}
		// System.out.println(map.toString());
		if (map.get("x10") == 'X') {
			return true;
		} else {
			int calc = Math.floorMod((map.get("x1") * 10 + map.get("x2") * 9 + map.get("x3") * 8 + map.get("x4") * 7
					+ map.get("x5") * 6 + map.get("x6") * 5 + map.get("x7") * 4 + map.get("x8") * 3 + map.get("x9") * 2
					+ map.get("x10") * 1), 11);
			return calc == 0 ? true : false;
		}
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		char[] phrase = string.replaceAll(" ", "").toLowerCase().toCharArray();
		Map<Character, Integer> charCount = new HashMap<>();

		if (string.isEmpty()) {
			return false;
		} else {
			for (char c : phrase) {
				if (charCount.containsKey(c)) {
					charCount.put(c, charCount.get(c) + 1);
				} else {
					charCount.put(c, 1);
				}
			}

			return charCount.entrySet().size() == 26 ? true : false;
		}
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		//System.out.println(given);
		Period GIGA_SECOND = Period.ofDays(11574);
		DateTimeFormatter dateTimeFormatter = null;
		String dateValue = null;
		String timeValue = null;
		if(given.toString().contains("T")) {
			//2015-01-24T22:00
			dateValue = given.toString().substring(0, 10);
			//System.out.println(dateValue);
			timeValue = given.toString().substring(11).concat(":00");
			//System.out.println(timeValue);
			dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		}else {
			
			dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		}
		
		if(timeValue == null) {
			TemporalAccessor temporalAccessor = dateTimeFormatter.parse(given.toString());
			//System.out.println("temporalAccessor: " + LocalDate.from(temporalAccessor));
			LocalDate date = LocalDate.from(temporalAccessor).plus(GIGA_SECOND);
			//System.out.println(date);
			
			//now need to set time
			LocalDateTime todaytime = LocalDateTime.of(date, LocalTime.MIDNIGHT).plusHours(1).plusMinutes(46).plusSeconds(40);
			//System.out.println(todaytime);
			return todaytime;
		}else {
			//there is a time value in given this time
			TemporalAccessor temporalAccessor = dateTimeFormatter.parse(dateValue.toString());
			LocalDate date = LocalDate.from(temporalAccessor).plus(GIGA_SECOND);
			//System.out.println("DATE CHECK: " + date);
			
			String[] results = timeValue.replaceAll(":", "").split("(?<=\\G.{" + 2 + "})");
			LocalDateTime todaytime = LocalDateTime.of(date, LocalTime.of(Integer.valueOf(results[0]), Integer.valueOf(results[1]), Integer.valueOf(results[2]))).plusHours(1).plusMinutes(46).plusSeconds(40);
			//System.out.println(todaytime);
			return todaytime;
		}
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		List<Integer> multiples = new ArrayList<>();
		for(int count = set[0]; count < i; count++) {
			for(int j = 0; j < set.length; j++) {
				if(count % set[j] == 0) {
					if(!multiples.contains(count)) {
						multiples.add(count);
					}
				}
			}
		}
		
		int result = 0;
		for (Integer m : multiples) {
			result += m;
		}
		
		return result;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: 
	 * valid credit card number 1 = 4539 1488 0343 6467 
	 * The first step of the Luhn algorithm is to double every second digit, 
	 * starting from the right.
	 * 
	 * We will be doubling 4_3_ 1_8_ 0_4_ 6_6_ 
	 * 
	 * If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 8_6_ 2_16_ 0_8_ 12_12_ ==> 8_6_2_7_0_8_3_3
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		//1. format string to have spaces if no invalid characters are present
		if(string.matches(".*[a-zA-Z]+.*") || string.contains("-")) {
			return false;
		}else if(string.trim().length() <= 1) {
			return false;
		}else {
			String target = string.replaceAll(" ", "");
			int[] numbers = target.chars().map(x -> x - '0').toArray();
			List<Integer> results = new ArrayList<>();
			int count = 0;
			
			for (int i = numbers.length-1; i >= 0; i--) {
				if(i % 2 == 1) {
					 results.add(Integer.valueOf(numbers[i] * 2));
					 count++;
				}else {
					results.add(Integer.valueOf(numbers[i]));
				}
			}
			
			//checking if any digit is greater than 9
			for(int j = 0; j < results.size(); j++) {
				int num = results.get(j);
				if(num > 9) {
					num = num%10 + num/10;
					results.set(j, num);
				}
			}
			int[] ints = results.stream().mapToInt(i -> i).toArray();
			int answer = Arrays.stream(ints).sum();
			return answer % 10 == 0 ? true : false;
		}
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		// TODO Write an implementation for this method declaration
		return 0;
	}

}
