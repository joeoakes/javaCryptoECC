import java.security.*;
import java.security.spec.*;
import java.util.Base64;
import java.io.UnsupportedEncodingException;


public class ECCExample {
    public static void main(String[] args) {
        try {
            // Generating ECC KeyPair
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
            keyGen.initialize(256);
            KeyPair keyPair = keyGen.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            // Message to be signed
            String message = "Hello, ECC!";
            byte[] messageBytes = message.getBytes("UTF-8");

            // Signing the message
            Signature ecdsa = Signature.getInstance("SHA256withECDSA");
            ecdsa.initSign(privateKey);
            ecdsa.update(messageBytes);
            byte[] signature = ecdsa.sign();

            // Printing the signature
            System.out.println("Signature: " + Base64.getEncoder().encodeToString(signature));

            // Verifying the signature
            ecdsa.initVerify(publicKey);
            ecdsa.update(messageBytes);
            boolean isVerified = ecdsa.verify(signature);

            // Printing verification result
            System.out.println("Signature Verified: " + isVerified);

        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
