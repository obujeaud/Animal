package hash;

import java.security.MessageDigest;
import java.util.Base64;

public class Encoder {
	private static Encoder instance = null;
	private Encoder() {
	}
	public static Encoder getInstance() {
		if (instance == null) {
			instance = new Encoder();
		}
		return instance;
	}
	public String sha256Encoder (String src) throws Exception  {
		// on crypte en SHA-256
		byte[] bs = MessageDigest.getInstance("SHA-256").digest(src.getBytes());
		// on passe en Base64 pour obtenir une String "lisible"
		return Base64.getEncoder().encodeToString(bs);
	}
	public static void main(String[] args) throws Exception {
		String str = "user";
		System.out.println(Encoder.getInstance().sha256Encoder(str));
	}
}