package com.usman.crypto.ciphers;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class VigenereCipherTest {


	@Test
	public void testVigenereKey() {
		String key="helloWorld";
		String plainText="A quick brown fox jumps over the lazy dog";
		VigenereCipher vc=new VigenereCipher();
		String e=vc.encrypt(plainText, key);
		String k=vc.getKey();
		assertTrue(false));

		//assertTrue("Malformed vigenere key", k.equals("HELLOWORLDHELLOWORLDHELLOWORLDHELLOWORLDH"));
		
	}
	
	
}

