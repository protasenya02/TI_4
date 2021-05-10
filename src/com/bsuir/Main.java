package com.bsuir;

import com.bsuir.lfsr.FileEncoder;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        FileEncoder fileEncoder = new FileEncoder();
        fileEncoder.encode("plainText.txt", "encodedText.txt");
        fileEncoder.decode("encodedText.txt","decodedText.txt");
    }
}
