/*
 *
 * @Course: TODO
 * Lab 3 - Stream cipher
 * @author: TODO
 *
 * RC4 (Rivest Cipher 4) is a stream cipher designed by Ron Rivest in 1987.
 * While historically significant and widely used in protocols like WEP and early SSL,
 * RC4 is now considered cryptographically broken and should not be used in production.
 * However, it remains an excellent learning tool for understanding stream ciphers because:
 * - It has a simple and elegant design
 * - It accepts flexible key lengths (1-256 bytes)
 * - It generates a pseudorandom stream without a nonce requirement
 * 
 * Key Length Information:
 * - Theoretical range: 1-256 bytes (8-2048 bits)
 * - Historical usage:
 *   • 40-bit (5 bytes): Used in export-restricted versions
 *   • 128-bit (16 bytes): Common in WEP and early SSL/TLS
 *   • 256-bit (32 bytes): Maximum security level
 * - For this lab, we recommend using 128-bit (16 bytes) keys for learning purposes
 * 
 * This implementation uses Java's Cipher API to demonstrate:
 * - How to obtain cipher instances from Java's cryptography framework
 * - The proper handling of cryptographic keys
 * - The symmetrical nature of stream cipher encryption/decryption
 * - Basic cryptographic error handling
 * 
 * Security Note:
 * RC4 has several known vulnerabilities:
 * - Biases in its pseudorandom generation
 * - Susceptibility to related-key attacks
 * - Weak key scheduling algorithm
 * For modern applications, prefer ChaCha20 or AES-GCM.
 * 
 */

public class RC4Cipher {
    // TODO: Consider what constants might be useful here

    public byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // TODO: Implement RC4 encryption using Java's Cipher API
        // Hint: Look up Cipher.getInstance() and SecretKeySpec in Java documentation
        return null;
    }

    public byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // TODO: Implement RC4 decryption
        // Question: How is this similar to or different from encryption?
        return null;
    }
}
