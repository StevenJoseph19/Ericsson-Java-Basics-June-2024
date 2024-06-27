package com.mycompany.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileOperations {

    public static void main(String[] args) {
        File file = new File("myfile.txt");
        boolean exists = file.exists();
        System.out.println("File exists: " + exists);

        try (FileInputStream inputStream = new FileInputStream(file)) {
            int data;
            while ((data = inputStream.read()) != -1) {
                System.out.print((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
