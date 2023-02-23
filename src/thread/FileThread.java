package thread;

import java.io.File;

public class FileThread extends Thread {
    File[] files;

    public FileThread(File[] files) {
        this.files = files;
    }

    @Override
    public void run() {
        scanner(files);
    }

    private void scanner(File[] files) {
        if (files == null) {
            return;
        }
        for (int i = 0; i < files.length; i++) {

            File file = files[i];


            if (file.isFile() && file.getName().contains(Main.target)) {
                Main.count++;
                System.out.println("Filename  " + file.getName() + " size = " + file.length() + " Path " + file.getPath());
            } else if (file.isDirectory() && file.getName().contains(Main.target)) {
                Main.count++;
                System.out.println("Filename  " + file.getName() + " size = " + file.length() + " Path " + file.getPath());
                Main.service.submit(new FileThread(file.listFiles()));
            } else if (file.isDirectory()) {
                Main.service.submit(new FileThread(file.listFiles()));
            }
        }
    }
}
