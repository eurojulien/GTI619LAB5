package controllers.modelsaccess;
import java.math.BigInteger;
import java.security.SecureRandom;

public final class Token {
	
	public static String nextSessionId() {
		
		SecureRandom random = new SecureRandom();
		return new BigInteger(256, random).toString(32);
	}
	
}