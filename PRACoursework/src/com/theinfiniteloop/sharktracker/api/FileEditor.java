package com.theinfiniteloop.sharktracker.api;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileEditor {

        BufferedWriter bw;
        File file;

        public FileEditor(String user) {
                try {
                        file = new File(user+".txt");

                        if (!file.exists()) {
                                file.createNewFile();
                        }

                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        public void writeThis(String string) {
                try {
                        FileWriter fw = new FileWriter(file, true);
                        bw = new BufferedWriter(fw);
                        bw.newLine();
                        bw.write(string);
                        bw.close();
                        fw.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
}

