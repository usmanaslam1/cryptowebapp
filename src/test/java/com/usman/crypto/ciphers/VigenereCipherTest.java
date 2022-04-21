package com.usman.crypto.ciphers;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class VigenereCipherTest {

	// Test to check if Vigenere Key is correctly created
	@Test
	public void testVigenereKey() {

		// Test Data
		String testKey = "helloWorld";
		String testPT = "A quick brown fox jumps over the lazy dog";
		String expectedResult = "HELLOWORLDHELLOWORLDHELLOWORLDHELLOWORLDH";

		// Create objects
		VigenereCipher vc = new VigenereCipher();
		String e;
		String k;

		// Carry out and evaluate test
		try {
			e = vc.encrypt(testPT, testKey);
			k = vc.getKey();
			assertEquals("Malformed vigenere key",k, expectedResult);
		} catch (VigenereException ve) {
			fail(ve.getMessage());
		}
	}

	/*
	 * Test to verify that non-alpha characters in key are rejected Key must be
	 * rejected if it contains non-alpha characters
	 */
	@Test
	public void testVigenereKeyChars() {

		// Test Data
		String testKey = "hello&#%World";
		String testPT = "A quick brown fox jumps over the lazy dog";

		// Create objects
		VigenereCipher vc = new VigenereCipher();
		String e;
		String k;

		// Carry out and evaluate test
		try {
			e = vc.encrypt(testPT, testKey);
			k = vc.getKey();
			fail("Non-alpha characters must not be allowed in the key");
		} catch (VigenereException ignored) {
		}

	}

	/*
	 * Test to verify that if key with Null value is rejected
	 * 
	 */
	@Test
	public void testNullKey() {

		// Test Data
		String testKey = null;
		String testPT = "A quick brown fox jumps over the lazy dog";
		// Create objects
		VigenereCipher vc = new VigenereCipher();
		String e;
		String k;

		// Carry out and evaluate test
		try {
			e = vc.encrypt(testPT, testKey);
			k = vc.getKey();
			fail("Null key must not be allowed");
		} catch (VigenereException ignored) {
		}

	}

	// Test to verify that for a given plaintext and key, correct ciphertext is
	// created

	@Test
	public void testEncryption() {
		// Test Data
		String key = "complexkey";
		String plainText = "A quick brown fox jumps over the lazy dog";
		String expectedResult = "C CJTGH FPQKZ QSU NSODE ZZBB RJS ALDV HMI";

		// Create objects
		VigenereCipher vc = new VigenereCipher();
		String e;
		String k;

		// Carry out and evaluate test
		try {

			e = vc.encrypt(plainText, key);
			k = vc.getKey();
			
			assertEquals("Incorrect CipherText. Expected: " + expectedResult + " >>  Actual Result: " + e,e,expectedResult);
			
			
		} catch (VigenereException ve) {
			fail(ve.getMessage());
		}

	}

	// 

	// Test to verify that for a given plain text and key, correct cipher text is
	/*
	 * created
	 */
	@Test
	public void testDecryption() {
		// Test Data
		String key = "complexkey";
		String cipherText = "C CJTGH FPQKZ QSU NSODE ZZBB RJS ALDV HMI";
		String expectedResult = "A QUICK BROWN FOX JUMPS OVER THE LAZY DOG";
		
		// Create objects
		VigenereCipher vc = new VigenereCipher();
		String d;
		String k;
		
		// Carry out and evaluate test
		try {

			d = vc.decrypt(cipherText, key);
			k = vc.getKey();
			
			assertEquals("Incorrect PlainText. Expected: " + expectedResult + " >>  Actual Result: " + d,d, expectedResult);
			
		} catch (VigenereException ve) {
			fail(ve.getMessage());
		}

	}

}
