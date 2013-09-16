package com.deepak.utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ProduceData {

    public static void main(String[] args) {

        if (args.length != 3) {
            log("Required number of parameters is 3");
            return;
        }

        try {
            String dataDirectory = args[0];
            // System.getProperty("user.home") + File.separator + "data";
            int fileSize = Integer.parseInt(args[1]);
            int fileCount = Integer.parseInt(args[2]);
            log("Directory in which logs will be created : " + dataDirectory);
            log("Size of each file : " + fileSize + " bytes");
            log("Number of files to be created : " + fileCount);

            Logger logger = Logger.getLogger("MyLog");
            File folder = new File(dataDirectory);
            folder.mkdirs();
            
            String[] st = { "this is first string", 
                    "got it.......................... yeah", 
                    "sorry...........................not found",
                    "find me if you can......................", 
                    "still not found......................",
                    "okay................try moreeeeee", 
                    "work harder...........................", 
                    "done..... not found",
                    "try and get it.................." };

            // This block configure the logger with handler and formatter
            FileHandler fh = new FileHandler(dataDirectory + File.separator + "data.log", fileSize, fileCount, true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            long successcount = 0;
            long almostSyccessCount = 0;
            while (getFolderSize(folder) <= (fileSize * fileCount)) {
                int random = (int) (Math.random() * 9);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < random; i++) {
                    int tmp = (int) (Math.random() * 9);
                    sb.append(st[tmp]).append("\n");
                    if (tmp == 1) {
                        successcount++;
                    } else if (tmp == 8) {
                        almostSyccessCount++;
                    }
                }
                logger.info(sb.toString());
            }
            log("Total number of hits: " + successcount);
            log("Total number of almost hits: " + almostSyccessCount);
        } catch (SecurityException e) {
            log(e.getMessage());
        } catch (IOException e) {
            log(e.getMessage());
        } catch (NumberFormatException e1) {
            log("Invalid size or file count");
        }
    }

    public static long getFolderSize(File dir) {
        long size = 0;
        for (File file : dir.listFiles()) {
            if (file.isFile()) {
                size += file.length();
            } else
                size += getFolderSize(file);
        }
        return size;
    }

    public static void log(String message) {
        System.out.println(message);
    }

}
