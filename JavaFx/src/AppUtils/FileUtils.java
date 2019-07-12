package AppUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.Normalizer;
import java.util.ArrayList;

public class FileUtils {
    public static ArrayList listFilesForFolder(final File folder, boolean includeSubfolderFile) {
        ArrayList<String> fileNames = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                if (includeSubfolderFile){
                    fileNames.addAll(listFilesForFolder(fileEntry, includeSubfolderFile));
                }
            } else {
                String name = fileEntry.getName();
                fileNames.add(name);
                System.out.println(name);
            }
        }
        return fileNames;
    }

    public static String normalizeCharacter(String s) throws UnsupportedEncodingException {
        s = s.replace("Đ", "D");
        s = s.replace("đ", "d");
        String s1 = Normalizer.normalize(s, Normalizer.Form.NFKD);
        String regex = "[\\p{InCombiningDiacriticalMarks}\\p{IsLm}\\p{IsSk}]+";

        return new String(s1.replaceAll(regex, "").getBytes("ascii"), "ascii");
    }
    public static boolean rename(String folderPath, String fromName, String toName){
        return new File(folderPath+"\\"+fromName).renameTo(new File(folderPath+"\\"+toName));
    }
}
