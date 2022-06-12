package com.revature.eval.java.core;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
//import org.junit.Ignore; //used mostly for isolating test while debugging

@RunWith(OrderedRunner.class)
public class EvaluationServiceTest {

	private static final EvaluationService evaluationService = new EvaluationService();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	/*******************************************************************
	 * Question 1
	 ******************************************************************/
	@Test
	@Order(order = 1)
	public void testAnEmptyString() {
		assertEquals("", evaluationService.reverse(""));
	}

	@Test
	@Order(order = 2)
	public void testAWord() {
		assertEquals("tobor", evaluationService.reverse("robot"));
	}

	@Test
	@Order(order = 3)
	public void testACapitalizedWord() {
		assertEquals("nemaR", evaluationService.reverse("Ramen"));
	}

	@Test
	@Order(order = 4)
	public void testASentenceWithPunctuation() {
		assertEquals("!yrgnuh m'I", evaluationService.reverse("I'm hungry!"));
	}

	@Test
	@Order(order = 5)
	public void testAPalindrome() {
		assertEquals("racecar", evaluationService.reverse("racecar"));
	}

	/*******************************************************************
	 * Question 2
	 ******************************************************************/
	@Test
	@Order(order = 6)
	public void basic() {
		final String phrase = "Portable Network Graphics";
		final String expected = "PNG";
		assertEquals(expected, evaluationService.acronym(phrase));
	}

	@Test
	@Order(order = 7)
	public void punctuation() {
		final String phrase = "First In, First Out";
		final String expected = "FIFO";
		assertEquals(expected, evaluationService.acronym(phrase));
	}

	@Test
	@Order(order = 8)
	public void NonAcronymAllCapsWord() {
		final String phrase = "GNU Image Manipulation Program";
		final String expected = "GIMP";
		assertEquals(expected, evaluationService.acronym(phrase));
	}

	@Test
	@Order(order = 9)
	public void punctuationWithoutWhitespace() {
		final String phrase = "Complementary metal-oxide semiconductor";
		final String expected = "CMOS";
		assertEquals(expected, evaluationService.acronym(phrase));
	}

	/*******************************************************************
	 * Question 3
	 ******************************************************************/

	@Test
	@Order(order = 10)
	public void trianglesWithNoEqualSidesAreNotEquilateral() {
		EvaluationService.Triangle triangle = new EvaluationService.Triangle(5, 4, 6);
		assertFalse(triangle.isEquilateral());
	}

	@Test
	@Order(order = 11)
	public void verySmallTrianglesCanBeEquilateral() {
		EvaluationService.Triangle triangle = new EvaluationService.Triangle(0.5, 0.5, 0.5);
		assertTrue(triangle.isEquilateral());
	}

	@Test
	@Order(order = 12)
	public void isoscelesTrianglesMustHaveAtLeastTwoEqualSides() {
		EvaluationService.Triangle triangle = new EvaluationService.Triangle(2, 3, 4);
		assertFalse(triangle.isIsosceles());
	}

	@Test
	@Order(order = 13)
	public void verySmallTrianglesCanBeIsosceles() {
		EvaluationService.Triangle triangle = new EvaluationService.Triangle(0.5, 0.4, 0.5);
		assertTrue(triangle.isIsosceles());
	}

	@Test
	@Order(order = 14)
	public void trianglesWithAllSidesEqualAreNotScalene() {
		EvaluationService.Triangle triangle = new EvaluationService.Triangle(4, 4, 4);
		assertFalse(triangle.isScalene());
	}

	@Test
	@Order(order = 15)
	public void verySmallTrianglesCanBeScalene() {
		EvaluationService.Triangle triangle = new EvaluationService.Triangle(0.5, 0.4, 0.6);
		assertTrue(triangle.isScalene());
	}

	/*******************************************************************
	 * Question 4
	 ******************************************************************/
	@Test
	@Order(order = 16)
	public void testAValuableLetter() {
		assertEquals(4, evaluationService.getScrabbleScore("f"));
	}

	@Test
	@Order(order = 17)
	public void testAShortValuableWord() {
		assertEquals(12, evaluationService.getScrabbleScore("zoo"));
	}

	@Test
	@Order(order = 18)
	public void testAMediumWord() {
		assertEquals(6, evaluationService.getScrabbleScore("street"));
	}

	@Test
	@Order(order = 19)
	public void testAMediumValuableWord() {
		assertEquals(22, evaluationService.getScrabbleScore("quirky"));
	}

	@Test
	@Order(order = 20)
	public void testALongMixCaseWord() {
		assertEquals(41, evaluationService.getScrabbleScore("OxyphenButazone"));
	}

	/*******************************************************************
	 * Question 5
	 ******************************************************************/
	@Test
	@Order(order = 21)
	public void cleansTheNumber() {
		final String expectedNumber = "2234567890";
		final String actualNumber = evaluationService.cleanPhoneNumber("(223) 456-7890");
		assertEquals(expectedNumber, actualNumber);
	}

	@Test
	@Order(order = 22)
	public void cleansNumbersWithDots() {
		final String expectedNumber = "2234567890";
		final String actualNumber = evaluationService.cleanPhoneNumber("223.456.7890");
		assertEquals(expectedNumber, actualNumber);
	}

	@Test
	@Order(order = 23)
	public void cleansNumbersWithMultipleSpaces() {
		final String expectedNumber = "2234567890";
		final String actualNumber = evaluationService.cleanPhoneNumber("223 456   7890   ");
		assertEquals(expectedNumber, actualNumber);
	}

	@Test
	@Order(order = 24)
	public void invalidWhenMoreThan11Digits() {
		expectedException.expect(IllegalArgumentException.class);
		evaluationService.cleanPhoneNumber("321234567890");
	}

	@Test
	@Order(order = 25)
	public void invalidWithNonNumeric() {
		expectedException.expect(IllegalArgumentException.class);
		evaluationService.cleanPhoneNumber("123-abc-7890");
		expectedException.expect(IllegalArgumentException.class);
		evaluationService.cleanPhoneNumber("123-@:!-7890");
	}

	/*******************************************************************
	 * Question 6
	 ******************************************************************/
	@Test
	@Order(order = 26)
	public void countOneWord() {
		Map<String, Integer> expectedWordCount = new HashMap<>();
		expectedWordCount.put("word", 1);

		Map<String, Integer> actualWordCount = evaluationService.wordCount("word");
		assertEquals(expectedWordCount, actualWordCount);
	}

	@Test
	@Order(order = 27)
	public void countOneOfEachWord() {
		Map<String, Integer> expectedWordCount = new HashMap<>();
		expectedWordCount.put("one", 1);
		expectedWordCount.put("of", 1);
		expectedWordCount.put("each", 1);

		Map<String, Integer> actualWordCount = evaluationService.wordCount("one of each");
		assertEquals(expectedWordCount, actualWordCount);
	}

	@Test
	@Order(order = 28)
	public void multipleOccurrencesOfAWord() {
		Map<String, Integer> expectedWordCount = new HashMap<>();
		expectedWordCount.put("one", 1);
		expectedWordCount.put("fish", 4);
		expectedWordCount.put("two", 1);
		expectedWordCount.put("red", 1);
		expectedWordCount.put("blue", 1);

		Map<String, Integer> actualWordCount = evaluationService.wordCount("one fish two fish red fish blue fish");
		assertEquals(expectedWordCount, actualWordCount);
	}

	@Test
	@Order(order = 29)
	public void handlesCrampedLists() {
		Map<String, Integer> expectedWordCount = new HashMap<>();
		expectedWordCount.put("one", 1);
		expectedWordCount.put("two", 1);
		expectedWordCount.put("three", 1);

		Map<String, Integer> actualWordCount = evaluationService.wordCount("one,two,three");
		assertEquals(expectedWordCount, actualWordCount);
	}

	@Test
	@Order(order = 30)
	public void handlesExpandedLists() {
		Map<String, Integer> expectedWordCount = new HashMap<>();
		expectedWordCount.put("one", 1);
		expectedWordCount.put("two", 1);
		expectedWordCount.put("three", 1);

		Map<String, Integer> actualWordCount = evaluationService.wordCount("one,\ntwo,\nthree");
		assertEquals(expectedWordCount, actualWordCount);
	}

	/*******************************************************************
	 * Question 7
	 ******************************************************************/
	@Test
	@Order(order = 31)
	public void findsAValueInTheMiddleOfAnArray() {
		List<String> sortedList = Collections.unmodifiableList(Arrays.asList("1", "3", "4", "6", "8", "9", "11"));

		EvaluationService.BinarySearch<String> search = new EvaluationService.BinarySearch<>(sortedList);

		assertEquals(3, search.indexOf("6"));
	}

	@Test
	@Order(order = 32)
	public void findsAValueAtTheBeginningOfAnArray() {
		List<Integer> sortedList = Collections.unmodifiableList(Arrays.asList(1, 3, 4, 6, 8, 9, 11));

		EvaluationService.BinarySearch<Integer> search = new EvaluationService.BinarySearch<>(sortedList);

		assertEquals(0, search.indexOf(1));
	}

	@Test
	@Order(order = 33)
	public void findsAValueAtTheEndOfAnArray() {
		List<Integer> sortedList = Collections.unmodifiableList(Arrays.asList(1, 3, 4, 6, 8, 9, 11));

		EvaluationService.BinarySearch<Integer> search = new EvaluationService.BinarySearch<>(sortedList);

		assertEquals(6, search.indexOf(11));
	}

	@Test
	@Order(order = 34)
	public void findsAValueInAnArrayOfOddLength() {
		List<Integer> sortedListOfOddLength = Collections
				.unmodifiableList(Arrays.asList(1, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 634));

		EvaluationService.BinarySearch<Integer> search = new EvaluationService.BinarySearch<>(sortedListOfOddLength);

		assertEquals(9, search.indexOf(144));
	}

	@Test
	@Order(order = 35)
	public void findsAValueInAnArrayOfEvenLength() {
		List<Integer> sortedListOfEvenLength = Collections
				.unmodifiableList(Arrays.asList(1, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377));

		EvaluationService.BinarySearch<Integer> search = new EvaluationService.BinarySearch<>(sortedListOfEvenLength);

		assertEquals(5, search.indexOf(21));
	}

	/*******************************************************************
	 * Question 8
	 ******************************************************************/
	@Test
	@Order(order = 36)
	public void testWordBeginningWithA() {
		assertEquals("appleay", evaluationService.toPigLatin("apple"));
	}

	@Test
	@Order(order = 37)
	public void testThTreatedLikeAConsonantAtTheBeginningOfAWord() {
		assertEquals("erapythay", evaluationService.toPigLatin("therapy"));
	}

	@Test
	@Order(order = 38)
	public void testSchTreatedLikeAConsonantAtTheBeginningOfAWord() {
		assertEquals("oolschay", evaluationService.toPigLatin("school"));
	}

	@Test
	@Order(order = 39)
	public void testYTreatedLikeAConsonantAtTheBeginningOfAWord() {
		assertEquals("ellowyay", evaluationService.toPigLatin("yellow"));
	}

	@Test
	@Order(order = 40)
	public void testAWholePhrase() {
		assertEquals("uickqay astfay unray", evaluationService.toPigLatin("quick fast run"));
	}

	/*******************************************************************
	 * Question 9
	 ******************************************************************/
	@Test
	@Order(order = 41)
	public void singleDigitsAreArmstrongNumbers() {
		int input = 5;

		assertTrue(evaluationService.isArmstrongNumber(input));
	}

	@Test
	@Order(order = 42)
	public void noTwoDigitArmstrongNumbers() {
		int input = 10;

		assertFalse(evaluationService.isArmstrongNumber(input));
	}

	@Test
	@Order(order = 43)
	public void threeDigitNumberIsArmstrongNumber() {
		int input = 153;

		assertTrue(evaluationService.isArmstrongNumber(input));
	}

	@Test
	@Order(order = 44)
	public void threeDigitNumberIsNotArmstrongNumber() {
		int input = 100;

		assertFalse(evaluationService.isArmstrongNumber(input));
	}

	@Test
	@Order(order = 45)
	public void fourDigitNumberIsArmstrongNumber() {
		int input = 9474;

		assertTrue(evaluationService.isArmstrongNumber(input));
	}

	/*******************************************************************
	 * Question 10
	 ******************************************************************/

	@Test
	@Order(order = 46)
	public void testPrimeNumber() {
		assertEquals(Collections.singletonList(2L), evaluationService.calculatePrimeFactorsOf(2L));
	}

	@Test
	@Order(order = 47)
	public void testSquareOfAPrime() {
		assertEquals(Arrays.asList(3L, 3L), evaluationService.calculatePrimeFactorsOf(9L));
	}

	@Test
	@Order(order = 48)
	public void testCubeOfAPrime() {
		assertEquals(Arrays.asList(2L, 2L, 2L), evaluationService.calculatePrimeFactorsOf(8L));
	}

	@Test
	@Order(order = 49)
	public void testProductOfPrimesAndNonPrimes() {
		assertEquals(Arrays.asList(2L, 2L, 3L), evaluationService.calculatePrimeFactorsOf(12L));
	}

	@Test
	@Order(order = 50)
	public void testProductOfPrimes() {
		assertEquals(Arrays.asList(5L, 17L, 23L, 461L), evaluationService.calculatePrimeFactorsOf(901255L));
	}

	/*******************************************************************
	 * Question 11
	 ******************************************************************/

	@Test
	@Order(order = 51)
	public void rotateSingleCharacterWithWrapAround() {
		EvaluationService.RotationalCipher rotationalCipher = new EvaluationService.RotationalCipher(13);
		assertEquals("a", rotationalCipher.rotate("n"));
	}

	@Test
	@Order(order = 52)
	public void rotateCapitalLetters() {
		EvaluationService.RotationalCipher rotationalCipher = new EvaluationService.RotationalCipher(5);
		assertEquals("TRL", rotationalCipher.rotate("OMG"));
	}

	@Test
	@Order(order = 53)
	public void rotateNumbers() {
		EvaluationService.RotationalCipher rotationalCipher = new EvaluationService.RotationalCipher(4);
		assertEquals("Xiwxmrk 1 2 3 xiwxmrk", rotationalCipher.rotate("Testing 1 2 3 testing"));
	}

	@Test
	@Order(order = 54)
	public void rotatePunctuation() {
		EvaluationService.RotationalCipher rotationalCipher = new EvaluationService.RotationalCipher(21);
		assertEquals("Gzo'n zvo, Bmviyhv!", rotationalCipher.rotate("Let's eat, Grandma!"));
	}

	@Test
	@Order(order = 55)
	public void rotateAllLetters() {
		EvaluationService.RotationalCipher rotationalCipher = new EvaluationService.RotationalCipher(13);
		assertEquals("The quick brown fox jumps over the lazy dog.",
				rotationalCipher.rotate("Gur dhvpx oebja sbk whzcf bire gur ynml qbt."));
	}

	/*******************************************************************
	 * Question 12
	 ******************************************************************/
	@Test
	@Order(order = 56)
	public void testFirstPrime() {
		assertThat(evaluationService.calculateNthPrime(1), is(2));
	}

	@Test
	@Order(order = 57)
	public void testSecondPrime() {
		assertThat(evaluationService.calculateNthPrime(2), is(3));
	}

	@Test
	@Order(order = 58)
	public void testSixthPrime() {
		assertThat(evaluationService.calculateNthPrime(6), is(13));
	}

	@Test
	@Order(order = 59)
	public void testBigPrime() {
		assertThat(evaluationService.calculateNthPrime(10001), is(104743));
	}

	@Test
	@Order(order = 60)
	public void testUndefinedPrime() {
		expectedException.expect(IllegalArgumentException.class);
		evaluationService.calculateNthPrime(0);
	}

	/*******************************************************************
	 * Question 13
	 ******************************************************************/

	@Test
	@Order(order = 61)
	public void testEncodeYes() {
		assertEquals("bvh", EvaluationService.AtbashCipher.encode("yes"));
	}

	@Test
	@Order(order = 62)
	public void testEncodeOmgInCapital() {
		assertEquals("lnt", EvaluationService.AtbashCipher.encode("OMG"));
	}

	@Test
	@Order(order = 63)
	public void testEncodeMindBlowingly() {
		assertEquals("nrmwy oldrm tob", EvaluationService.AtbashCipher.encode("mindblowingly"));
	}

	@Test
	@Order(order = 64)
	public void testEncodeNumbers() {
		assertEquals("gvhgr mt123 gvhgr mt", EvaluationService.AtbashCipher.encode("Testing,1 2 3, testing."));
	}

	@Test
	@Order(order = 65)
	public void testEncodeDeepThought() {
		assertEquals("gifgs rhurx grlm", EvaluationService.AtbashCipher.encode("Truth is fiction."));
	}

	@Test
	@Order(order = 66)
	public void testEncodeAllTheLetters() {
		assertEquals("gsvjf rxpyi ldmul cqfnk hlevi gsvoz abwlt",
				EvaluationService.AtbashCipher.encode("The quick brown fox jumps over the lazy dog."));
	}

	/*******************************************************************
	 * Question 14
	 ******************************************************************/
	@Test
	@Order(order = 67)
	public void testDecodeExercism() {
		assertEquals("exercism", EvaluationService.AtbashCipher.decode("vcvix rhn"));
	}

	@Test
	@Order(order = 68)
	public void testDecodeASentence() {
		assertEquals("anobstacleisoftenasteppingstone",
				EvaluationService.AtbashCipher.decode("zmlyh gzxov rhlug vmzhg vkkrm thglm v"));
	}

	@Test
	@Order(order = 69)
	public void testDecodeNumbers() {
		assertEquals("testing123testing", EvaluationService.AtbashCipher.decode("gvhgr mt123 gvhgr mt"));
	}

	@Test
	@Order(order = 70)
	public void testDecodeAllTheLetters() {
		assertEquals("thequickbrownfoxjumpsoverthelazydog",
				EvaluationService.AtbashCipher.decode("gsvjf rxpyi ldmul cqfnk hlevi gsvoz abwlt"));
	}

	/*******************************************************************
	 * Question 15
	 ******************************************************************/
	@Test
	@Order(order = 71)
	public void validIsbnNumber() {
		assertTrue(evaluationService.isValidIsbn("3-598-21508-8"));
	}

	@Test
	@Order(order = 72)
	public void invalidIsbnCheckDigit() {
		assertFalse(evaluationService.isValidIsbn("3-598-21508-9"));
	}

	@Test
	@Order(order = 73)
	public void validIsbnNumberWithCheckDigitOfTen() {
		assertTrue(evaluationService.isValidIsbn("3-598-21507-X"));
	}

	@Test
	@Order(order = 74)
	public void checkDigitIsACharacterOtherThanX() {
		assertFalse(evaluationService.isValidIsbn("3-598-21507-A"));
	}

	@Test
	@Order(order = 75)
	public void invalidCharacterInIsbn() {
		assertFalse(evaluationService.isValidIsbn("3-598-2K507-0"));
	}

	/*******************************************************************
	 * Question 16
	 ******************************************************************/
	@Test
	@Order(order = 76)
	public void emptySentenceIsNotPangram() {
		assertFalse(evaluationService.isPangram(""));
	}

	@Test
	@Order(order = 77)
	public void recognizesPerfectLowerCasePangram() {
		assertTrue(evaluationService.isPangram("abcdefghijklmnopqrstuvwxyz"));
	}

	@Test
	@Order(order = 78)
	public void pangramWithOnlyLowerCaseLettersIsRecognizedAsPangram() {
		assertTrue(evaluationService.isPangram("the quick brown fox jumps over the lazy dog"));
	}

	@Test
	@Order(order = 79)
	public void phraseMissingCharacterXIsNotPangram() {
		assertFalse(evaluationService.isPangram("a quick movement of the enemy will jeopardize five gunboats"));
	}

	@Test
	@Order(order = 80)
	public void phraseMissingAnotherCharacterIsNotPangram() {
		assertFalse(evaluationService.isPangram("five boxing wizards jump quickly at it"));
	}

	/*******************************************************************
	 * Question 17
	 ******************************************************************/
	@Test
	@Order(order = 81)
	public void modernTime() {
		assertEquals(LocalDateTime.of(2043, Month.JANUARY, 1, 1, 46, 40),
				evaluationService.getGigasecondDate(LocalDate.of(2011, Month.APRIL, 25)));
	}

	@Test
	@Order(order = 82)
	public void afterEpochTime() {
		assertEquals(LocalDateTime.of(2009, Month.FEBRUARY, 19, 1, 46, 40),
				evaluationService.getGigasecondDate(LocalDate.of(1977, Month.JUNE, 13)));
	}

	@Test
	@Order(order = 83)
	public void beforeEpochTime() {
		assertEquals(LocalDateTime.of(1991, Month.MARCH, 27, 1, 46, 40),
				evaluationService.getGigasecondDate(LocalDate.of(1959, Month.JULY, 19)));
	}

	@Test
	@Order(order = 84)
	public void withFullTimeSpecified() {
		assertEquals(LocalDateTime.of(2046, Month.OCTOBER, 2, 23, 46, 40),
				evaluationService.getGigasecondDate(LocalDateTime.of(2015, Month.JANUARY, 24, 22, 0, 0)));
	}

	@Test
	@Order(order = 85)
	public void withFullTimeSpecifiedAndDayRollover() {
		assertEquals(LocalDateTime.of(2046, Month.OCTOBER, 3, 1, 46, 39),
				evaluationService.getGigasecondDate(LocalDateTime.of(2015, Month.JANUARY, 24, 23, 59, 59)));
	}

	/*******************************************************************
	 * Question 18
	 ******************************************************************/
	@Test
	@Order(order = 86)
	public void testSumOfMultiplesOf4and6UpToFifteen() {

		int[] set = { 4, 6 };
		int output = evaluationService.getSumOfMultiples(15, set);
		assertEquals(30, output);

	}

	@Test
	@Order(order = 87)
	public void testSumOfMultiplesOf5and6and8UpToOneHundredFifty() {

		int[] set = { 5, 6, 8 };
		int output = evaluationService.getSumOfMultiples(150, set);
		assertEquals(4419, output);

	}

	@Test
	@Order(order = 88)
	public void testSumOfMultiplesOf5and25UpToFiftyOne() {

		int[] set = { 5, 25 };
		int output = evaluationService.getSumOfMultiples(51, set);
		assertEquals(275, output);

	}

	@Test
	@Order(order = 89)
	public void testSumOfMultiplesOf43and47UpToTenThousand() {

		int[] set = { 43, 47 };
		int output = evaluationService.getSumOfMultiples(10000, set);
		assertEquals(2203160, output);

	}

	@Test
	@Order(order = 90)
	public void testSumOfMultiplesOfOneUpToOneHundred() {

		int[] set = { 1 };
		int output = evaluationService.getSumOfMultiples(100, set);
		assertEquals(4950, output);

	}

	/*******************************************************************
	 * Question 19
	 ******************************************************************/
	@Test
	@Order(order = 91)
	public void testThatAValidCanadianSocialInsuranceNumberIsIdentifiedAsValidV1() {
		assertTrue(evaluationService.isLuhnValid("046 454 286"));
	}

	@Test
	@Order(order = 92)
	public void testThatAnInvalidCanadianSocialInsuranceNumberIsIdentifiedAsInvalid() {
		assertFalse(evaluationService.isLuhnValid("046 454 287"));
	}

	@Test
	@Order(order = 93)
	public void testThatAnInvalidCreditCardIsIdentifiedAsInvalid() {
		assertFalse(evaluationService.isLuhnValid("8273 1232 7352 0569"));
	}

	@Test
	@Order(order = 94)
	public void testThatAddingANonDigitCharacterToAValidStringInvalidatesTheString() {
		assertFalse(evaluationService.isLuhnValid("046a 454 286"));
	}

	@Test
	@Order(order = 95)
	public void testThatStringContainingPunctuationIsInvalid() {
		assertFalse(evaluationService.isLuhnValid("055-444-285"));
	}

	/*******************************************************************
	 * Question 20
	 ******************************************************************/
	@Test
	@Order(order = 96)
	public void testSingleAddition1() {
		assertEquals(2, evaluationService.solveWordProblem("What is 1 plus 1?"));
	}

	@Test
	@Order(order = 97)
	public void testSingleAdditionWithNegativeNumbers() {
		assertEquals(-11, evaluationService.solveWordProblem("What is -1 plus -10?"));
	}

	@Test
	@Order(order = 98)
	public void testSingleSubtraction() {
		assertEquals(16, evaluationService.solveWordProblem("What is 4 minus -12?"));
	}

	@Test
	@Order(order = 99)
	public void testSingleMultiplication() {
		assertEquals(-75, evaluationService.solveWordProblem("What is -3 multiplied by 25?"));
	}

	@Test
	@Order(order = 100)
	public void testSingleDivision() {
		assertEquals(-11, evaluationService.solveWordProblem("What is 33 divided by -3?"));
	}

}
