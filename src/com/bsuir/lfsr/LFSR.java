package com.bsuir.lfsr;

import java.io.IOException;
import java.util.BitSet;

// Класс реализации потокового шифрования методом LFSR
public  class LFSR implements StreamCipher {
  private static final int[] defaultPolinom = {23, 5};    //   многочлен по умолчанию (x^23 + x^5 + 1)
  private final int registerSize = 23;                    //   размер регистра
  private final BitSet register;                          //   регистр

  public LFSR() {
    register = new BitSet(registerSize);
    // установка всех бит регистра в 1
    register.set(0, registerSize);
  }

  @Override
  public byte[] encrypt(byte[] plainText) throws IOException {

    byte[] cipherBytes = new byte[plainText.length];

    for (int i = 0; i < plainText.length; i++) {
      cipherBytes[i] = (byte) (plainText[i] ^ generateKey());
    }

    return cipherBytes;
  }

  @Override
  public byte[] decrypt(byte[] cipherBytes) throws IOException {

    byte[] decodedText = new byte[cipherBytes.length];

    for (int i = 0; i < cipherBytes.length; i++) {
      decodedText[i] = (byte) (cipherBytes[i] ^ generateKey());
    }

    return decodedText;
  }

  @Override
  public byte generateKey() {

    BitSet resultKey = new BitSet(0);

    for (int i = 0; i < 8; i++) {
        resultKey.set(i, register.get(registerSize - 1));
        BitSet tempRegister = (BitSet) register.clone();

        // копирование всех бит кроме последнего
        for (int j = 1; j <= registerSize - 1; j++) {
          register.set(j, tempRegister.get(j - 1));
        }
        // установка последнего бита по маске
        register.set(0, (register.get(defaultPolinom[0]) ^ register.get(defaultPolinom[1])));
    }

      return resultKey.toByteArray()[0];
  }
}

