package com.bsuir.lfsr;

import java.io.IOException;

// интерфейс для потоковых шифров
public interface StreamCipher {
    byte[] encrypt(byte[] plainText) throws IOException;
    byte[] decrypt(byte[] cipherBytes) throws IOException;
    byte generateKey();
}
