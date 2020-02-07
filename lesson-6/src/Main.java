/*
Создать 2 текстовых файла, примерно по 50-100 символов в каждом(особого значения не имеет);
Написать программу, «склеивающую» эти файлы, то есть вначале идет текст из первого файла, потом текст из второго.
* Написать программу, которая проверяет присутствует ли указанное пользователем слово в файле (работаем только с латиницей).
** Написать метод, проверяющий, есть ли указанное слово в папке (Почитайте реализацию класса File)*/

import java.io.*;
import java.util.*;

public class Main {
    private static final Random rnd = new Random();
    private static final int NUMBER_OF_SYMBOLS = 100;
    private static final int CHAR_BOUND_L = 65;
    private static final int CHAR_BOUND_H = 90;
    private static final int WORDS_AMOUNT = 5;
    private static final int WORD_LENGTH = 10;
    private static final String SEARCH_WORD = "hello";
    private  static final int FILES_AMOUNT = 2;

    public static void main(String[] args) {
        String[] files = new String[FILES_AMOUNT];
        for (int i = 0; i< files.length; i++) {
            files[i] = "file_" + i + ".txt";
        }
        try {
            //#1
            for (int i = 0; i < files.length; i++)
                if (i < 2)
                    writeFileContents(files[i], 100);
                else
                    writeFileContents(files[i], WORDS_AMOUNT, WORD_LENGTH);
            System.out.println("First task results are in file_0 and file_1.");
            //#2
            concatenate(files[0], files[1], "FILE_OUT.txt");
            System.out.println("Second task result is in FILE_OUT.");

        }
        catch (Exception ex) { throw new RuntimeException(ex); }
    }

    private static String generateSymbols(int amount) {
        StringBuilder sequence = new StringBuilder();
        for (int i = 0; i < amount; i++)
            sequence.append((char) (CHAR_BOUND_L + rnd.nextInt(CHAR_BOUND_H - CHAR_BOUND_L)));
        return sequence.toString();
    }

    private static void writeFileContents(String fileName, int length) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        fos.write(generateSymbols(length).getBytes());
        fos.flush();
        fos.close();
    }

    private static void writeFileContents(String fileName, int words, int length) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        for (int i = 0; i < words; i++) {
            if(rnd.nextInt(WORDS_AMOUNT) == WORDS_AMOUNT / 2)
                fos.write(SEARCH_WORD.getBytes());
            else
                fos.write(generateSymbols(length).getBytes());
            fos.write(' ');
        }
        fos.flush();
        fos.close();
    }

    private static void concatenate(String file_in1, String file_in2, String file_out) throws IOException {
        FileOutputStream fos = new FileOutputStream(file_out);
        int ch;
        FileInputStream fis = new FileInputStream(file_in1);
        while ((ch = fis.read()) != -1)
            fos.write(ch);
        fis.close();

        fis = new FileInputStream(file_in2);
        while ((ch = fis.read()) != -1)
            fos.write(ch);
        fis.close();
        fos.flush();
        fos.close();
    }

}
