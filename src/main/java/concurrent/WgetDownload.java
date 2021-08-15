package concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class WgetDownload implements Runnable {
    private final String url;
    private final int speed;
    private final String pathDownload;

    public WgetDownload(String url, int speed, String pathDownload) {
        this.url = url;
        this.speed = speed;
        this.pathDownload = pathDownload;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(pathDownload)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long startTimeStamp = System.nanoTime();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                long finishTimeStamp = System.nanoTime();
                long deltaTime = finishTimeStamp - startTimeStamp;
                if (deltaTime < speed) {
                    Thread.sleep(speed - deltaTime);
                }
                startTimeStamp = System.nanoTime();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length < 2) {
            System.out.println("Введите 2 аргумента");
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new WgetDownload(url, speed, "out.txt"));
        wget.start();
        wget.join();
    }
}
