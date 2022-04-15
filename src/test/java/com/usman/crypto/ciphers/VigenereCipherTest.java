package com.usman.crypto.ciphers;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class VigenereCipherTest {

	//Test to check if Vigenere Key is correctly created
	@Test
	public void testVigenereKey() {
		String key="helloWorld";
		String plainText="A quick brown fox jumps over the lazy dog";
		VigenereCipher vc=new VigenereCipher();
		String e=vc.encrypt(plainText, key);
		String k=vc.getKey();
		assertTrue("Malformed vigenere key", k.equals("HELLOWORLDHELLOWORLDHELLOWORLDHELLOWORLDH"));
		
	}
	
	//Test to verify that non-alpha characters in key are rejected

/*	@Test
	public void testVigenereKeyChars() {
		
		//Data 
		String key="hello&#%World";
		String plainText="A quick brown fox jumps over the lazy dog";
		
		//Run test
		VigenereCipher vc=new VigenereCipher();
		String e=vc.encrypt(plainText, key);
		String k=vc.getKey();
		
		//Report result
		assertTrue("Malformed vigenere key", k.equals("HELLOWORLDHELLOWORLDHELLOWORLDHELLOWORLDH"));
		
	}
	
	//Test to verify that for a given plaintext and key, correct ciphertext is created
	@Test
	public void testEncryption() {
		String key="helloWorld";
		String plainText="A quick brown fox jumps over the lazy dog";
		VigenereCipher vc=new VigenereCipher();
		String e=vc.encrypt(plainText, key);
		String k=vc.getKey();
		assertTrue("Malformed vigenere key", k.equals("HELLOWORLDHELLOWORLDHELLOWORLDHELLOWORLDH"));
		
	}
	
	//Test to verify that for a given plain text and key, correct cipher text is created
	@Test
	public void testDecryption() {
		String key="helloWorld";
		String plainText="A quick brown fox jumps over the lazy dog";
		VigenereCipher vc=new VigenereCipher();
		String e=vc.encrypt(plainText, key);
		String k=vc.getKey();
		assertTrue("Malformed vigenere key", k.equals("HELLOWORLDHELLOWORLDHELLOWORLDHELLOWORLDH"));
		
	}
	
	//Test to verify that non-alpha characters in plaintext are ignored and not encrypted
	@Test
	public void testInvalidValues() {
		String key=null;
		String plainText=null;
		VigenereCipher vc=new VigenereCipher();
		String e=vc.encrypt(plainText, key);
		String k=vc.getKey();
		assertTrue("Malformed vigenere key", k.equals("HELLOWORLDHELLOWORLDHELLOWORLDHELLOWORLDH"));
		
	}	*/
}

