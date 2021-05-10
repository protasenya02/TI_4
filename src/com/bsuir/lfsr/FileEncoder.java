package com.bsuir.lfsr;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileEncoder {

  StreamCipher streamCipher;

  public FileEncoder() {
    streamCipher = new LFSR();
  }

  private byte[] readFile(String filePath) throws IOException {
    byte[] fileContent;

    try(FileInputStream fIn = new FileInputStream(filePath)){
      fileContent = fIn.readAllBytes();
    } catch (Exception e) {
      throw new IOException(e);
    }

    return fileContent;
  }

  private void writeFile(byte[] content, String filePath) throws IOException {
    try(FileOutputStream fOut = new FileOutputStream(filePath)){
      fOut.write(content);
    } catch (Exception e) {
      throw new IOException(e);
    }
  }

  public void encode(String fromFile, String toFile) throws IOException {

    byte[] plainText = readFile(fromFile);
    byte[] cipherBytes = streamCipher.encrypt(plainText);

    writeFile(cipherBytes, toFile);
    System.out.println("File was successfully encoded.");
  }

  public void decode(String fromFile, String toFile) throws IOException {

    byte[] cipherBytes = readFile(fromFile);
    byte[] decodedText = streamCipher.decrypt(cipherBytes);

    writeFile(decodedText, toFile);
    System.out.println("File was successfully decoded.");
  }
}
