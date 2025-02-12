/*
 *
 * @Course: TODO
 * Lab 3 - Stream cipher
 * @author: TODO
 * ChaCha20 is a modern stream cipher designed by Daniel J. Bernstein.
 * It offers several advantages over older ciphers like RC4:
 * - Requires a nonce which prevents key reuse
 * - Uses 256-bit keys for strong security
 * - Provides high performance on modern processors
 *
 * Key size: 32 bytes (256 bits)
 * Nonce size: 12 bytes (96 bits)
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
