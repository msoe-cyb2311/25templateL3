/*
 *
 * @Course: TODO
 * Lab 3 - Stream cipher
 * @author: TODO
 * A class for performing ChaCha20 encryption and decryption using Java's Cipher API.
 * You will learn how to:
 * - Get a Cipher instance for ChaCha20
 * - Handle both keys and nonces correctly
 * - Use IvParameterSpec for the nonce
 * - Process data using the cipher
 */

public class ChaCha20Cipher {
    // TODO: Consider what constants might be useful here
    // Question: What are the required sizes for ChaCha20 keys and nonces?

    public byte[] encrypt(byte[] data, byte[] key, byte[] nonce) throws Exception {
        // TODO: Implement ChaCha20 encryption using Java's Cipher API
        // Hint: You'll need both SecretKeySpec and IvParameterSpec
        return null;
    }

    public byte[] decrypt(byte[] data, byte[] key, byte[] nonce) throws Exception {
        // TODO: Implement ChaCha20 decryption
        // Question: What happens if you use the wrong nonce?
        return null;
    }
}