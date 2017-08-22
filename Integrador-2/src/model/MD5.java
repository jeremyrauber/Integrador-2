package model;

	import java.security.*;
	import java.math.*;

	public class MD5 {
	    public static void main(String args[]) throws Exception{
	       String s="Texto de Exemplo";
	       MessageDigest m = MessageDigest.getInstance("MD5");
	       m.update(s.getBytes(),0,s.length());
	       System.out.println("MD5: "+new BigInteger(1,m.digest()).toString(16)+"senha orginial: "+s);
	    }
	}

