package com.usman.crypto.ciphers;

import com.usman.crypto.ciphers.VigenereCipher.OP;

public class VigenereCipher {

	private  char[] vKey; // Vigenere key (same length as the length of message)
	static final int MODULUS = 26; // for cyclic arithmetic for finite set of English alphabets
	static final char CHAR_OFFSET = 'A';
    static enum OP {ENCRYPT, DECRYPT};	


	//Return key
	public  String getKey() {
		return String.valueOf(vKey);
	}
	
	
	//Encrypt
	public String encrypt(String plainText, String key) {
		return process (key,plainText,OP.ENCRYPT);
	}
	
	//Decrypt
	
	public String decrypt(String plainText, String key) {	
		return process (key,plainText,OP.DECRYPT);
	}

	
	//Initialize key, ensure that key legth is same as plaintext length 
	private void initialize(String vigenreKey, String inputText) {

		vigenreKey=vigenreKey.toUpperCase();
		int clearTextLength=inputText.length();
		vKey = new char[inputText.length()];
		
		int idx = 0;

		for (int x = 0; x < clearTextLength; x++) {
			vKey[x] = vigenreKey.charAt(idx);
			idx = (idx >= vigenreKey.length() - 1 ? 0 : idx + 1);
		}

	}

	//Process encryption / decryption requests
	private String process (String key, String text, OP op) {	
		text=text.toUpperCase();
		initialize(key, text);
		
		char[] out = new char[text.length()];		
		for (int x = 0; x < text.length(); x++) {
			char c=text.charAt(x);	
			if((c>='A'&&c<='Z')||(c>='a'&&c<='z')) {
				char keychar=vKey[x];
				int r=(op==OP.ENCRYPT?c+keychar:c-keychar);
				
				if(r<0)r+=MODULUS;
				r=r%MODULUS;
				
				out[x] = (char) (r+CHAR_OFFSET);
			}
			else {
				out[x]=c;
			}
		}
		return String.valueOf(out);			
	}
}
