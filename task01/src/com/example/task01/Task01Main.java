package com.example.task01;

import java.io.*;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:

        /*
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
        */
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();

//        processBuilder.directory(new File("C:\\Users\\Alex\\IdeaProjects\\8-java-io2\\task01\\ffmpeg-master-latest-win64-gpl-shared\\bin"));
        processBuilder.directory(new File(
                "ffmpeg-master-latest-win64-gpl-shared\\bin").getAbsoluteFile()
        );
        processBuilder.command(
                "cmd.exe",
                "/C",
                "ffprobe -v error -of flat -show_format",
                file.getAbsolutePath()
        );

        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(processBuilder.start().getInputStream())
        )){
            String input;
            while ((input = bufferedReader.readLine()) != null){
                if (input.contains("format.tags.title")){
                    return input.split("\"")[1];
                }
            }
        }
        return null;
    }
}
