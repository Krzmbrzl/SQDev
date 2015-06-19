package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import SQF.Functions;

public class Fuctions_Test {

	@Test
	public void removeStringTest() {
		String test = "Hallo mein Freund und Kupferstecher!"; // Originalstring,
																// aus dem
																// Elemenete
																// entfent
																// werden sollen
		String removal1 = "Freund"; // Element, das aus test1 entfernt werden
									// soll
		String removal2 = "und Kupferstecher";
		String removal3 = "Halleluja!"; // Ist nicht in test1 enthalten

		assertEquals("Hallo mein  und Kupferstecher!",
				Functions.removeString(test, removal1)); // Test1
		assertEquals("Hallo mein Freund !",
				Functions.removeString(test, removal2)); // Test2
		assertEquals("Hallo mein Freund und Kupferstecher!",
				Functions.removeString(test, removal3)); // Test3 (Zu
															// entfernender
															// String ist nicht
															// im Originalstring
															// enthalten ->
															// Originalstring
															// muss
															// zur√ºckgegeben
															// werden)
	}

	@Test
	public void removeFromToTest() {
		String test = "Hallo mein Freund und Kupferstecher!"; // Originalstring,
																// aus dem
																// Elemenete
																// entfent
																// werden sollen

		String from1 = "mein";
		String to1 = "und";
		assertEquals("Hallo   und Kupferstecher!",
				Functions.removeFromTo(test, from1, to1));

		String from2 = "Hallo";
		String to2 = "und";
		assertEquals("  und Kupferstecher!",
				Functions.removeFromTo(test, from2, to2)); // FreUND enth√§lt
															// auch schon ein
															// "und", welches
															// dann durch die
															// Funktion entfernt
															// wird
	}

	@Test
	public void removeStartBlankTest() {
		String test1 = " Tralala Trololo";
		assertEquals("Tralala Trololo", Functions.removeStartBlank(test1)); // Es
																			// muss
																			// das
																			// Startleerzeichen
																			// entfernt
																			// werden

		String test2 = "Hihi      hoho";
		assertEquals("Hihi      hoho", Functions.removeStartBlank(test2)); // Es
																			// darf
																			// nichts
																			// entfernt
																			// werden
	}

	@Test
	public void howManyElementsTest() {
		String test1 = "Eins Zwei Drei Vier";
		assertEquals(4, Functions.howManyElements(test1)); // Leerzeichegetrennt

		String test2 = "Eins (Zwei, Zw√∂lf) Drei";
		assertEquals(3, Functions.howManyElements(test2)); // Mit runden
															// Klammern

		String test3 = "[Eins Hurra Jippie] Zwei Drei";
		assertEquals(3, Functions.howManyElements(test3)); // Mit eckigen
															// Klammern

		String test4 = "Eins [Zwei Dr√∂lf Test] Drei (Vier Aju Boak) (Miau, und)?";
		assertEquals(5, Functions.howManyElements(test4)); // Mit runden und
															// eckigen Klammern

		String test5 = "Eins";
		assertEquals(1, Functions.howManyElements(test5)); // Nur ein Element

		String test6 = "";
		assertEquals(-1, Functions.howManyElements(test6)); // Leerer String

		String test7 = null;
		assertEquals(-1, Functions.howManyElements(test7)); // Null

		String test8 = "[√Ñtsch!";
		assertEquals(-2, Functions.howManyElements(test8)); // unpaarige eckige
															// Klammer

		String test9 = "(Au √Ñtsch!";
		assertEquals(-3, Functions.howManyElements(test9)); // unpaarige runde
															// Klammer

		String test10 = "   Hallo";
		assertEquals(1, Functions.howManyElements(test10)); // Mit
															// Startleerzeichen

		String test11 = "eins zwei [drei, [immernochDrei]] Vier [Test]";
		assertEquals(5, Functions.howManyElements(test11));
	}

	@Test
	public void getElementsTest() {
		String test1 = "Eins Zwei Drei Vier";
		String[] result1 = { "Eins", "Zwei", "Drei", "Vier" };
		assertArrayEquals(result1, Functions.getElements(test1)); // Leerzeichengetrennt

		String test2 = "Eins (Zwei, Zw√∂lf) Drei";
		String[] result2 = { "Eins", "(Zwei, Zw√∂lf)", "Drei" };
		assertArrayEquals(result2, Functions.getElements(test2)); // runde
																	// Klammern

		String test3 = "[Eins Hurra Jippie] Zwei Drei";
		String[] result3 = { "[Eins Hurra Jippie]", "Zwei", "Drei" };
		assertArrayEquals(result3, Functions.getElements(test3)); // eckige
																	// Klammern

		String test4 = "Eins [Zwei Dr√∂lf Test] Drei (Vier Aju Boak) (Miau, und)?";
		String[] result4 = { "Eins", "[Zwei Dr√∂lf Test]", "Drei",
				"(Vier Aju Boak)","(Miau, und)?" };
		assertArrayEquals(result4, Functions.getElements(test4)); // runde und
																	// eckige
																	// Klammern

		String test5 = "Eins";
		String[] result5 = { "Eins" };
		assertArrayEquals(result5, Functions.getElements(test5)); // nur ein
																	// Element

		String test6 = "Eins [Zwei,[Drei], Vier]";
		String[] result6 = { "Eins", "[Zwei,[Drei], Vier]" };
		assertArrayEquals(result6, Functions.getElements(test6));
	}

	@Test
	public void correctSyntaxTest() {
		String[] test1 = { "abs", "Syntax:", "Number", "=", "ABS", "n",
				"Parameters:", "n:", "Number", "Return", "Value:", "Number" };
		String[] result1 = { "abs", "Variable", "=", "ABS", "DOUBLE" };
		assertArrayEquals(result1, Functions.correctSyntax(test1, "abs"));

		String[] test2 = { "activateAddons", "Syntax:", "ACTIVATEADDONS",
				"[addon1, ...]", "Parameters:", "[addon1, ...]:", "Array",
				"activateAddons:", "Depp", "Return", "Value:", "Nothing" };
		String[] result2 = { "activateAddons", "ACTIVATEADDONS", "Array" };
		assertArrayEquals(result2,
				Functions.correctSyntax(test2, "activateAddons"));
	}

	@Test
	public void whichPositionTest() {
		String[] test = { "Null", "Eins", "Zwei", "Drei", "Vier", "F√ºnf",
				"Sechs" };

		assertEquals(0, Functions.whichPosition(test, "Null", true, true));

		assertEquals(3, Functions.whichPosition(test, "Drei", true, true));

		assertEquals(1, Functions.whichPosition(test, "i", false, true));
		// trifft auf "Eins" zu, da exactValue auf false gesetzt ist

		assertEquals(-1, Functions.whichPosition(test, "i", true, false)); // trifft
																			// nicht
																			// zu,
																			// da
																			// exactValue
																			// auf
																			// true
																			// gesetzt
																			// ist

		assertEquals(5, Functions.whichPosition(test, "F√ºnf", true, true));

		String[] test2 = { "test", "[test1 test2 test3, test4]" };
		assertEquals(1.1,
				Functions.whichPosition(test2, "test2", true, false, true), 0);

		String[] test3 = { "test", "[test1,test2,test3, test4]" };
		assertEquals(1.3,
				Functions.whichPosition(test3, "test4", true, false, true), 0);

		String[] test4 = { "test", "[test1 test2 test3, test4]" };
		assertEquals(1.0,
				Functions.whichPosition(test4, "test1", true, false, true), 0);
	}

	@Test
	public void isInTest() {
		String[] test = { "Null", "Eins", "Zwei", "Drei", "Vier", "F√ºnf",
				"Sechs" };

		assertTrue(Functions.isIn(test, "Null", true));

		assertTrue(Functions.isIn(test, "Drei", false));

		assertTrue(Functions.isIn(test, "i", false)); // Trifft zu, da
														// exactValue auf false
														// steht und die
														// Funktion somit das
														// "i" in "Eins" findet

		assertFalse(Functions.isIn(test, "i", true)); // Trifft nicht zu, da
														// exactValue auf true
														// steht und kein
														// Element im Array nur
														// aus "i" besteht

		String[] test2 = { "test", "[test1 test2 test3]" };
		assertTrue(Functions.isIn(test2, "test2", true));

		String[] test3 = { "test", "[test1, test2, test3]" };
		assertTrue(Functions.isIn(test3, "test2", true));
	}

	@Test
	public void removeDotsTest() {
		String test1 = "Miauuuuuuuuu!";
		assertEquals(test1, Functions.removeDots(test1)); // Muss originalstring
															// zur√ºckgeben, da
															// keine Punkte
															// enth√§lt

		String test2 = "Vier.F√ºnf";
		assertEquals("VierF√ºnf", Functions.removeDots(test2));

		String test3 = "(...) .";
		assertEquals("(...)", Functions.removeDots(test3)); // Darf nur die
															// Punkte au√üerhalb
															// der Klammern
															// entfernen

		String test4 = "[...]...";
		assertEquals("[...]", Functions.removeDots(test4)); // Darf nur die
															// Punkte au√üerhalb
															// der Klammern
															// entfernen

		String test5 = ". (Test...)";
		assertEquals("(Test...)", Functions.removeDots(test5)); // Muss auch
																// gehen, wenn
																// die Klammer
																// nicht am
																// Anfang steht

		String test6 = ". [Test...]";
		assertEquals("[Test...]", Functions.removeDots(test6));

		String test7 = "Test. (Juhu...) und [Juhu...]...";
		assertEquals("Test (Juhu...) und [Juhu...]",
				Functions.removeDots(test7)); // Kombiniert
	}

	@Test
	public void replaceWithTest() {
		String[] test1 = { "Null", "Eins", "Zwei", "Drei", "Vier", "F√ºnf" };

		String replace1 = "Null";
		String with1 = "Sieben";
		String[] result1 = { "Sieben", "Eins", "Zwei", "Drei", "Vier", "F√ºnf" };
		assertArrayEquals(result1,
				Functions.replaceWith(test1, replace1, with1));

		String[] test2 = { "Null", "Eins", "Zwei", "Drei", "Vier", "F√ºnf" };
		String replace2 = "Drei";
		String with2 = "Dr√∂lf";
		String[] result2 = { "Null", "Eins", "Zwei", "Dr√∂lf", "Vier", "F√ºnf" };
		assertArrayEquals(result2,
				Functions.replaceWith(test2, replace2, with2));

		String[] test3 = { "Null", "Eins", "Zwei", "Drei", "Vier", "F√ºnf" };
		String replace3 = "F√ºnf";
		String with3 = "Jepp";
		String[] result3 = { "Null", "Eins", "Zwei", "Drei", "Vier", "Jepp" };
		assertArrayEquals(result3,
				Functions.replaceWith(test3, replace3, with3));

		String[] test4 = { "Null", "Eins", "Zwei", "Drei", "Vier", "F√ºnf" };
		String replace4 = "Driee";
		String with4 = "Dr√∂lf";
		String[] result4 = { "Null", "Eins", "Zwei", "Drei", "Vier", "F√ºnf" };
		assertArrayEquals(result4,
				Functions.replaceWith(test4, replace4, with4)); // Wenn
																// "replace"
																// nicht im
																// Array
																// entahlten
																// ist, wird
																// einfach der
																// Originalarray
																// zur√ºckgegeben
		String[] test5 = { "Eins", "Zwei", "[Drei, Vier]" };
		String replace5 = "Vier";
		String with5 = "Miau";
		String[] result5 = { "Eins", "Zwei", "[Drei, Miau]" };
		assertArrayEquals(Functions.replaceWith(test5, replace5, with5),
				result5);

		String[] test6 = { "Eins", "Zwei", "[Drei, Vier]" };
		String replace6 = "Drei";
		String with6 = "Miau";
		String[] result6 = { "Eins", "Zwei", "[Miau, Vier]" };
		assertArrayEquals(result6,
				Functions.replaceWith(test6, replace6, with6));
	}

	@Test
	public void filterCommandTest() {
		String[] test1 = { "command_with_bullshit", "Blabla", "command" };
		String[] result1 = { "command", "Blabla", "command" };
		assertArrayEquals(result1, Functions.filterCommand(test1));

		String[] test2 = { "test_command_and_more", "Blabla", "command" };
		String[] result2 = { "command", "Blabla", "command" };
		assertArrayEquals(result2, Functions.filterCommand(test2));

		String[] test3 = { "command", "Blabla", "command" };
		assertArrayEquals(test3, Functions.filterCommand(test1)); // Wenn der
																	// command
																	// schon
																	// stimmt
																	// dann darf
																	// am Array
																	// nichts
																	// ge√§ndert
																	// werden
	}

	@Test
	public void changeTovarTest() {
		String[] test1 = { "Miauz", "=", "Mo" };
		String[] result1 = { "Variable", "=", "Mo" };
		assertArrayEquals(result1, Functions.changeToVar(test1)); // "Miauz"
																	// wird
																	// durch
																	// "Variable"
																	// ersetzt

		String[] test2 = { "Miauz", "Test", "Mo" };
		String[] result2 = { "Miauz", "Test", "Mo" };
		assertArrayEquals(result2, Functions.changeToVar(test2)); // nichts
																	// passiert,
																	// da kein
																	// "="
																	// enthalten
																	// ist
																	// (somit
																	// auch
																	// keine
																	// Deklaration)

		String[] test3 = { "Array", "=", "Mo" };
		String[] result3 = { "Array", "=", "Mo" };
		assertArrayEquals(result3, Functions.changeToVar(test3)); // Es wird bei
																	// "Array"
																	// belassen,
																	// da ein
																	// Array ja
																	// keine
																	// Variable
																	// ist
	}

	@Test
	public void markCommandTest() {
		String test = "commandLalala<nbissle Html> vˆllig ohne<Belang><b>taratata</b><b>command</b>lololo<tralala>";
		String result = "commandLalala<nbissle Html> vˆllig ohne<Belang><b>taratata</b<B>COMMAND</B>lololo<tralala>";

		assertEquals(result, Functions.markCommand(test, "command"));
	}

	@Test
	public void findPosTest() {
		String test = "Miau Mio Tralala Juhu Ich bin toll und so weiter!";

		String element1 = "Miau";
		int[] pos1 = { 0, 4 };
		assertArrayEquals(pos1, Functions.findPos(test, element1));

		String element2 = "Mio";
		int[] pos2 = { 5, 8 };
		assertArrayEquals(pos2, Functions.findPos(test, element2));

		String element3 = "M";
		int[] pos3 = { 0, 1 };
		assertArrayEquals(pos3, Functions.findPos(test, element3));

		String element4 = "!";
		int[] pos4 = { 48, 49 };
		assertArrayEquals(pos4, Functions.findPos(test, element4));
	}

	@Test
	public void unmarkCommandTest() {
		String[] test = { "Eins", "Zwei", "COMMAND", "DREI", "Vier" };
		String[] result = { "Eins", "Zwei", "command", "DREI", "Vier" };
		assertArrayEquals(result, Functions.unmarkCommand(test, "command"));
	}

	@Test
	public void cutFromTest() {
		String[] test = { "Eins", "Zwei", "COMMAND", "DREI", "Vier", "F¸nf" };

		String test1 = "Vier";
		String[] result1 = { "Eins", "Zwei", "COMMAND", "DREI" };
		assertArrayEquals(result1, Functions.cutFrom(test, test1, true));

		String test2 = "Zwei";
		String[] result2 = { "Eins" };
		assertArrayEquals(result2, Functions.cutFrom(test, test2, false));

		String test3 = "Depp";
		String[] result3 = { "Eins", "Zwei", "COMMAND", "DREI", "Vier", "F¸nf" };
		assertArrayEquals(result3, Functions.cutFrom(test, test3, true));
	}

	@Test
	public void turnIntotest() {
		String test = "Ich laufe durch DEN Baum und HABE Spaﬂ!";

		String test1 = "Ich";
		String result1 = "ICH laufe durch DEN Baum und HABE Spaﬂ!";
		assertEquals(result1, Functions.turnInto(test, test1, "UP"));

		String test2 = "und";
		String result2 = "Ich laufe durch DEN Baum UND HABE Spaﬂ!";
		assertEquals(result2, Functions.turnInto(test, test2, "UP"));

		String test3 = "DEN";
		String result3 = "Ich laufe durch den Baum und HABE Spaﬂ!";
		assertEquals(result3, Functions.turnInto(test, test3, "LOW"));

		String test4 = "HABE Spaﬂ";
		String result4 = "Ich laufe durch DEN Baum und habe spaﬂ!";
		assertEquals(result4, Functions.turnInto(test, test4, "LOW"));
	}

	@Test
	public void getArrayContentFromTest() {
		String[] test = { "Null", "Eins", "Zwei", "Drei", "Vier", "F¸nf",
				"Sechs", "Sieben", "Acht" };

		int pos1 = 3;
		String[] result1 = { "Drei", "Vier", "F¸nf", "Sechs", "Sieben", "Acht" };
		assertArrayEquals(result1, Functions.getArrayContentFrom(test, pos1));

		int pos2 = 6;
		String[] result2 = { "Sechs", "Sieben", "Acht" };
		assertArrayEquals(result2, Functions.getArrayContentFrom(test, pos2));

		int pos3 = 0;
		String[] result3 = { "Null", "Eins", "Zwei", "Drei", "Vier", "F¸nf",
				"Sechs", "Sieben", "Acht" };
		assertArrayEquals(result3, Functions.getArrayContentFrom(test, pos3));

		int pos4 = 8;
		String[] result4 = { "Acht" };
		assertArrayEquals(result4, Functions.getArrayContentFrom(test, pos4));

		int pos5 = 11;
		assertArrayEquals(null, Functions.getArrayContentFrom(test, pos5)); // Wenn
																			// Position
																			// Arrayl‰nge
																			// ¸bersteigt,
																			// gibt
																			// es
																			// null
																			// zur¸ck

		int pos6 = -1;
		assertArrayEquals(null, Functions.getArrayContentFrom(test, pos6)); // Wenn
																			// Position
																			// Arrayl‰nge
																			// ¸bersteigt,
																			// gibt
																			// es
																			// null
																			// zur¸ck
	}

	@Test
	public void containsAlternativeSyntaxPartTest() {
		String[] array1 = { "Syntax:", "Eins", "Zwei", "Alternative", "Syntax",
				"Drei" };
		assertTrue(Functions.containsAlternativeSyntax(array1));

		String[] array2 = { "Hier", "ist", "keine", "andere", "Syntax", "drin" };
		assertFalse(Functions.containsAlternativeSyntax(array2));

		String[] array3 = { "Syntax:", "Test", "Eins", "ZWei", "Drei" };
		assertFalse(Functions.containsAlternativeSyntax(array3));

		String[] array4 = { "Syntax:", "test", "unit", "Alternative", "Syntax",
				"object" };
		assertTrue(Functions.containsAlternativeSyntax(array4));

	}

	@Test
	public void getAlternativeSyntaxPartTest() {
		String[] array1 = { "Syntax:", "Eins", "Zwei", "Alternative", "Syntax",
				"Drei" };
		String[] result1 = { "Drei" };
		assertArrayEquals(result1, Functions.getAlternativeSyntaxPart(array1));

		String[] array2 = { "Syntax:", "test", "unit", "Alternative", "Syntax",
				"object" };
		String[] result2 = { "object" };
		assertArrayEquals(result2, Functions.getAlternativeSyntaxPart(array2));
	}

	@Test
	public void meltArraysTest() {
		String[] array11 = { "Eins", "Zwei" };
		String[] array12 = { "Drei" };
		String[] result1 = { "Eins", "Zwei", "Drei" };
		assertArrayEquals(result1, Functions.meltArrays(array11, array12));

		String[] array21 = { "tap" };
		String[] array22 = { "Hello", "World" };
		String[] result2 = { "tap", "Hello", "World" };
		assertArrayEquals(result2, Functions.meltArrays(array21, array22));
	}

	@Test
	public void CheckHasParametersTest() {
		String[] array1 = { "eins", "zwei", "Return", "Value", "Nothing" };
		String[] result1 = { "eins", "zwei", "Parameters:", "HatGarKeine",
				"Return", "Value", "Nothing" };
		assertArrayEquals(result1, Functions.checkHasParameters(array1));

		String[] array2 = { "test", "Return", "Value", "Nothing" };
		String[] result2 = { "test", "Parameters:", "HatGarKeine", "Return",
				"Value", "Nothing" };
		assertArrayEquals(result2, Functions.checkHasParameters(array2));
	}

	@Test
	public void seperateElementsTest() {
		String string1 = "Hallo mein Freund";
		String result1 = "Hallo, mein, Freund";
		assertEquals(result1, Functions.seperateElements(string1));

		String string2 = "Juhu";
		assertEquals(string2, Functions.seperateElements(string2));

		String string3 = "Test ";
		String result3 = "Test, ";
		assertEquals(result3, Functions.seperateElements(string3));
	}

	@Test
	public void changeFirstLetterTest() {
		String test1 = "Hallo";
		assertEquals("hallo", Functions.changeFirstLetter(test1));

		String test2 = "hallo";
		assertEquals("Hallo", Functions.changeFirstLetter(test2));

		String test3 = "HALLO";
		assertEquals("hALLO", Functions.changeFirstLetter(test3));

		String test4 = "hAlLo";
		assertEquals("HAlLo", Functions.changeFirstLetter(test4));
	}

	@Test
	public void removeNullElementsTest() {
		String[] test1 = { "Test", "Miau", null, "Dong" };
		String[] result1 = { "Test", "Miau", "Dong" };
		assertArrayEquals(result1, Functions.removeNullElements(test1));

		String[] test2 = { null, "Test", "test" };
		String[] result2 = { "Test", "test" };
		assertArrayEquals(result2, Functions.removeNullElements(test2));

		String[] test3 = { "Test", "Miau", null };
		String[] result3 = { "Test", "Miau" };
		assertArrayEquals(result3, Functions.removeNullElements(test3));

		String[] test4 = { "Test", null, "Miau", null };
		String[] result4 = { "Test", "Miau" };
		assertArrayEquals(result4, Functions.removeNullElements(test4));

		String[] test5 = { null, "Test", null, "Miau", null };
		String[] result5 = { "Test", "Miau" };
		assertArrayEquals(result5, Functions.removeNullElements(test5));

		String[] test6 = { null, null, null, null };
		String[] result6 = {};
		assertArrayEquals(result6, Functions.removeNullElements(test6));
	}

	@Test
	public void reduceSpaceBetween() {
		String test1 = "Das   ist     ein Test";
		String result1 = "Das ist ein Test";
		assertEquals(result1, Functions.reduceSpaceBetween(test1));

		String test2 = "Noch               ein           Test      ";
		String result2 = "Noch ein Test ";
		assertEquals(result2, Functions.reduceSpaceBetween(test2));

		String test3 = "(optional,   TKOH  only):";
		String result3 = "(optional, TKOH only):";
		assertEquals(result3, Functions.reduceSpaceBetween(test3));
	}

	@Test
	public void checkSpecialParameterTest() {
		String[] syntax1 = { "command", "param1", "param2" };
		String[] parameter1 = { "miau", "don't care", "param2:",
				"(optional, TKOH only):", "jkdv" };
		String[] result1 = { "command", "param1" };
		assertArrayEquals(result1,
				Functions.checkSpecialParameter(syntax1, parameter1, false));

		String[] syntax2 = { "command", "[param1, param2]" };
		String[] parameter2 = { "Tada", "test", "param1:", "parameter",
				"param2:", "(optional, TKOH only):" };
		String[] result2 = { "command", "[param1]" };
		assertArrayEquals(result2,
				Functions.checkSpecialParameter(syntax2, parameter2, false));
	}

	@Test
	public void roundTest() {
		double test1 = 2.34111111;
		assertEquals(2.34, Functions.round(test1, 2), 0);

		double test2 = 5.67389;
		assertEquals(5.674, Functions.round(test2, 3), 0);

		double test3 = 126.89;
		assertEquals(126.9, Functions.round(test3, 1), 0);

		double test4 = 2.99;
		assertEquals(3.0, Functions.round(test4, 1), 0);

		double test5 = 129.99;
		assertEquals(130.0, Functions.round(test5, 1), 0);

		double test6 = 9.99;
		assertEquals(10.0, Functions.round(test6, 1), 0);

		double test7 = 9.9;
		assertEquals(9.9, Functions.round(test7, 1), 0);
	}

	@Test
	public void getBracketsElementsTest() {
		String test1 = "[Miau Owei, Hurra] Tick Tock";
		String result1 = "[Miau Owei, Hurra]";
		assertEquals(result1, Functions.getBracketElements(test1));

		String test2 = "[Miau [Nochmal Miau] Uns so]Tap Tap";
		String result2 = "[Miau [Nochmal Miau] Uns so]";
		assertEquals(result2, Functions.getBracketElements(test2));

		String test3 = "[Miau, [Again,Again], Test [Tipp Test] [Und]] Tick Tock";
		String result3 = "[Miau, [Again,Again], Test [Tipp Test] [Und]]";
		assertEquals(result3, Functions.getBracketElements(test3));
	}

	@Test
	public void removeBetweenBracketElementsTest() {
		String test1 = "[] Miau";
		String result1 = " Miau";
		assertEquals(result1, Functions.removeBetweenBracketElements(test1));

		String test2 = "[Tes t [Miau] und [s0]]Miau";
		String result2 = "Miau";
		assertEquals(result2, Functions.removeBetweenBracketElements(test2));
	}

	@Test
	public void getBetweenBracketElementsTest() {
		String test1 = "[Test [Miau]]";
		String result1 = "Test [Miau]";
		assertEquals(result1, Functions.getBetweenBracketElements(test1));

		String test2 = "[]";
		String result2 = "";
		assertEquals(result2, Functions.getBetweenBracketElements(test2));

		String test3 = "[ Test [Miau] Test [Miau2]]";
		String result3 = "Test [Miau] Test [Miau2]";
		assertEquals(result3, Functions.getBetweenBracketElements(test3));
	}

	@Test
	public void howOftenTest() {
		String test1 = "Miau1Miau1Wau1Wau1 Wuff1";
		assertEquals(5, Functions.howOften(test1, "1"));

		assertEquals(2, Functions.howOften(test1, "Miau"));

		assertEquals(2, Functions.howOften(test1, "Wau"));

		assertEquals(1, Functions.howOften(test1, "Wuff"));

		assertEquals(0, Functions.howOften(test1, "Dummy"));
	}

}