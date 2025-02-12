# CYB-2311 Lab 3: Image Encryption with Stream Ciphers - A Hands-on Lab

In this lab, you'll learn how to use stream ciphers to encrypt and decrypt images using Java's cryptography features. Don't worry if this sounds complex - we'll break it down step by step and create something really cool together!

## What We're Building

We're creating a simple but powerful application that can encrypt images using two different stream ciphers: RC4 and ChaCha20. You'll be able to:
- Load any image file
- Encrypt it using either RC4 or ChaCha20
- Decrypt it back to the original image
- See the encryption results in real-time

## Getting Started

### Setting Up Your Environment

1. You'll need:
    - Java 23 (JDK 23)
    - JavaFX 23
    - SceneBuilder

   Install everything from: https://taylorial.com/tools/java/

2. Important: Verify your project uses Java 23 in your IDE:
    - In IntelliJ: File → Project Structure → Project → SDK → Select "23"


### Project Structure

Your project contains these main files:
```
src/
    ├── Main.java           // Application entry point
    ├── Controller.java     // Handles user interactions
    ├── main.fxml          // Defines our user interface
    ├── RC4Cipher.java     // RC4 encryption/decryption operations
    └── ChaCha20Cipher.java // ChaCha20 encryption/decryption operations
```

## Your Tasks

### 1. Understanding the Interface
Open `main.fxml` using SceneBuilder and look around. You'll see:
- An ImageView on the left for displaying images
- Buttons on the right for loading and encrypting/decrypting
- A text field for entering encryption keys

### 2. Implementing RC4
RC4 is our first cipher. While it's not secure for real-world use anymore, it's perfect for learning! Here's how to use Java's crypto API:

```java
// Creating an RC4 cipher
Cipher cipher = Cipher.getInstance("RC4");

// Setting up the key
SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "RC4");
cipher.init(Cipher.ENCRYPT_MODE, keySpec);

// Encrypting data
byte[] encrypted = cipher.doFinal(imageBytes);
```

### 3. Implementing ChaCha20
ChaCha20 is a modern, secure stream cipher. It's a bit more complex because it needs a nonce (a random number used once), but don't worry - we'll help you through it!

```java
// Creating a ChaCha20 cipher
Cipher cipher = Cipher.getInstance("ChaCha20");

// ChaCha20 needs both a key and a nonce
SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "ChaCha20");
IvParameterSpec ivSpec = new IvParameterSpec(nonceBytes);
cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
```

### 4. Working with Images in JavaFX
Here's a code snippet to handle image encryption and display in JavaFX:

```java
public class Controller {
    @FXML
    private ImageView imageView;
    private Image originalImage;
    private byte[] imageBytes;

    // Converting Image to bytes for encryption
    private byte[] imageToBytes(Image image) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        WritablePixelFormat<ByteBuffer> format = WritablePixelFormat.getByteBgraInstance();
        byte[] buffer = new byte[width * height * 4];
        image.getPixelReader().getPixels(0, 0, width, height, format, buffer, 0, width * 4);
        return buffer;
    }

    // Creating Image from encrypted/decrypted bytes
    private Image bytesToImage(byte[] bytes, int width, int height) {
        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        PixelFormat<ByteBuffer> pixelFormat = PixelFormat.getByteBgraPreInstance();
        
        pixelWriter.setPixels(0, 0, width, height, pixelFormat, bytes, 0, width * 4);
        return writableImage;
    }

    // Example usage in your encrypt method
    public void encryptImage() {
        try {
            if (imageBytes == null) {
                showError("Please load an image first");
                return;
            }

            String key = keyField.getText();
            if (key.isEmpty()) {
                showError("Please enter an encryption key");
                return;
            }

            // Get key bytes (you might want to add proper key handling)
            byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);

            // Encrypt the image bytes
            byte[] encryptedBytes = rc4Cipher.encrypt(imageBytes, keyBytes);

            // Display the encrypted image
            int width = (int) originalImage.getWidth();
            int height = (int) originalImage.getHeight();
            Image encryptedImage = bytesToImage(encryptedBytes, width, height);
            imageView.setImage(encryptedImage);

        } catch (InvalidKeyException e) {
            showError("Invalid key: " + e.getMessage());
        } catch (Exception e) {
            showError("Encryption failed: " + e.getMessage());
        }
    }
}
```
### 5. Error Handling Examples

Here's how to handle common cryptographic operations safely:

```java
public class RC4Cipher {
    public byte[] encrypt(byte[] data, byte[] key) throws CryptoException {
        try {
            // Validate inputs
            if (data == null || data.length == 0) {
                throw new IllegalArgumentException("No data to encrypt");
            }
            if (key == null || key.length == 0) {
                throw new IllegalArgumentException("Encryption key cannot be empty");
            }

            // Create and initialize cipher
            Cipher cipher = Cipher.getInstance("RC4");
            SecretKeySpec keySpec = new SecretKeySpec(key, "RC4");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);

            return cipher.doFinal(data);

        } catch (NoSuchAlgorithmException e) {
            throw new CryptoException("RC4 algorithm not available", e);
        } catch (NoSuchPaddingException e) {
            throw new CryptoException("Padding error", e);
        } catch (InvalidKeyException e) {
            throw new CryptoException("Invalid key: " + e.getMessage(), e);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new CryptoException("Encryption failed", e);
        }
    }
}

// Custom exception class for better error handling
public class CryptoException extends Exception {
    public CryptoException(String message, Throwable cause) {
        super(message, cause);
    }
}

// In your controller, handle these exceptions appropriately:
private void showError(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
```

### 6. Performance Comparison

You should compare the performance between RC4 and ChaCha20. 
Here is a sample code snippet, you can use the following testing method to measure the encryption speed. 
You are also welcomed to propose and implement your own testing methods.

```java
public void compareSpeed() {
    // Generate test keys and data
    byte[] rc4Key = new byte[16];
    byte[] chachaKey = new byte[32];
    // Note: ChaCha20 requires a 12-byte nonce
    byte[] nonce = new byte[12];
    SecureRandom random = new SecureRandom();
    random.nextBytes(rc4Key);
    random.nextBytes(chachaKey);
    random.nextBytes(nonce);
    
    // Create test data (100MB)
    byte[] testData = new byte[100 * 1024 * 1024];
    Arrays.fill(testData, (byte) 42);
    
    // Test RC4 speed
    long startTime = System.nanoTime();
    try {
        Cipher rc4 = Cipher.getInstance("RC4");
        rc4.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(rc4Key, "RC4"));
        byte[] rc4Result = rc4.doFinal(testData);
        long rc4Time = System.nanoTime() - startTime;
        System.out.printf("RC4 encryption time: %.2f seconds%n", rc4Time / 1e9);
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    // Test ChaCha20 speed
    startTime = System.nanoTime();
    try {
        Cipher chacha = Cipher.getInstance("ChaCha20");
        chacha.init(Cipher.ENCRYPT_MODE, 
                   new SecretKeySpec(chachaKey, "ChaCha20"),
                   new IvParameterSpec(nonce));
        byte[] chachaResult = chacha.doFinal(testData);
        long chachaTime = System.nanoTime() - startTime;
        System.out.printf("ChaCha20 encryption time: %.2f seconds%n", chachaTime / 1e9);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```


## Tips and Tricks

- Always check that your key is the right size:
    - RC4 can use various key sizes
    - ChaCha20 needs a 32-byte key (256 bits)

- When working with images:
    - Java loads images as RGB bytes
    - Each pixel is usually 3 or 4 bytes
    - Don't worry about the file format - just encrypt the raw bytes!

- Debugging tips:
    - If your decrypted image looks scrambled, double-check your key handling
    - For ChaCha20, make sure you're using the same nonce for encryption and decryption
    - Try encrypting and decrypting small files first


## Important Note

Remember: This is a learning exercise! In the real world:
- Never use RC4 (it's not secure anymore)
- Never reuse encryption keys
- Always use secure key generation in real applications
- Be careful with error messages (they might leak sensitive information)

But for learning how stream ciphers work? This project is perfect! Have fun encrypting some images! 😊



## Submission

Submit to your GitHub Classroom repository:
1. Your complete source code
2. A PDF report including:
    - Implementation challenges you faced
    - Performance comparison results
    - Screenshots of your working application
    - Any feedback or suggestions

## Resources
- [JavaFX Documentation](https://openjfx.io/javadoc/23/)
- [Java 23 Cipher class](https://docs.oracle.com/en/java/javase/23/docs/api/java.base/javax/crypto/Cipher.html)

Good luck! 🚀

