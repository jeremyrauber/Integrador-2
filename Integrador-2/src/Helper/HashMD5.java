package Helper;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashMD5 {

	public String toMD5(String senha) throws Exception {
		
		MessageDigest algorithm;
		try {
			algorithm = MessageDigest.getInstance("MD5");
		
	    	byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
	    	StringBuilder hexString = new StringBuilder();
	    	for (byte b : messageDigest) {
	    	  hexString.append(String.format("%02X", 0xFF & b));
	    	}
	    	
	    	return hexString.toString(); 
		}catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
    }
}
