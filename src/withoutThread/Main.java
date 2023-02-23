package withoutThread;

import thread.FileThread;

import java.io.File;

public class Main {
    static int count = 0;
    static String target = ".txt";

    public static void main(String[] args) {
        String path = "C:\\";
        File file = new File(path);
        scanner(file.listFiles());
        System.out.println(count);
    }

    private static void scanner(File[] files) {
        if (files == null) {
            return;
        }
        for (int i = 0; i < files.length; i++) {
            File innerFile = files[i];
            if (innerFile.isFile() && innerFile.getName().contains(Main.target)) {
                count++;
                System.out.println("Filename  " + innerFile.getName() + " size = " + innerFile.length() + " Path " + innerFile.getPath());
            } else if (innerFile.isDirectory() && innerFile.getName().contains(Main.target)) {
                count++;
                System.out.println("Filename  " + innerFile.getName() + " size = " + innerFile.length() + " Path " + innerFile.getPath());
                scanner(innerFile.listFiles());
            } else if (innerFile.isDirectory()) {
                scanner(innerFile.listFiles());
            }
        }
    }
}
