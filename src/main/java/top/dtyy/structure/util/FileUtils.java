package top.dtyy.structure.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * 文件相关操作
 *
 * @author lzp
 * @version v1.0 at 2018/11/29
 */
public class FileUtils {
    public static boolean readFile(String filename, List<String> words) {
        if (filename == null || words == null) {
            System.out.println("文件名或单词集合为空");
            return false;
        }
        Scanner scanner;
        try {
            File file = new File(filename);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");
                scanner.useLocale(Locale.ENGLISH);
            } else {
                return false;
            }
        } catch (IOException e) {
            System.out.println("cannot open " + filename);
            return false;
        }
        // 分词处理
        if (scanner.hasNextLine()) {
            String contents = scanner.useDelimiter("\\A").next();

            int start = firstCharacterIndex(contents, 0);
            for (int i = start + 1; i <= contents.length(); ) {
                if (i == contents.length() || !Character.isLetter(contents.charAt(i))) {
                    String word = contents.substring(start, i).toLowerCase();
                    words.add(word);
                    start = firstCharacterIndex(contents, i);
                    i = start + 1;
                } else {
                    i++;
                }
            }
        }
        return true;
    }

    /**
     * 寻找字符串s中，从start的位置开始的第一个字母字符的位置
     *
     * @param s
     * @param start
     * @return
     */
    private static int firstCharacterIndex(String s, int start) {

        for (int i = start; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i))) {
                return i;
            }
        }
        return s.length();
    }
}
