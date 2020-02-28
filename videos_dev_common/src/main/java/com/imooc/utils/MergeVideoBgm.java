package com.imooc.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MergeVideoBgm {

    public static void converter(String videoInputPath, String bgmInputPath,
                          float seconds, String videoOutputPath) throws IOException {
        List<String> command = new ArrayList<>();
        ProcessBuilder builder = new ProcessBuilder();
        Process process = null;
        command.add("ffmpeg.exe");
        command.add("-i");
        command.add(bgmInputPath);
        command.add("-i");
        command.add(videoInputPath);
        command.add("-t");
        command.add(String.valueOf(seconds));
        command.add("-y");
        command.add(videoOutputPath);
        builder.command(command);
        process = builder.start();
        release(process);
        System.out.println("视频合并完毕...");
    }

    public static void getCover(String videoInputPath, String coverOutputPath) throws IOException {
        List<String> command = new ArrayList<>();
        ProcessBuilder builder = new ProcessBuilder();
        Process process = null;
        command.add("ffmpeg.exe");
        command.add("-ss");
        command.add("00:00:01");
        command.add("-y");
        command.add("-i");
        command.add(videoInputPath);
        command.add("-vframes");
        command.add("1");
        command.add(coverOutputPath);
        builder.command(command);
        process = builder.start();
        release(process);
        System.out.println("封面生成完毕...");
    }

    private static void release(Process process) throws IOException {
        InputStream errorStream = process.getErrorStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(errorStream);
        byte[] bytes = new byte[1024];
        int i ;
        while ((i = bufferedInputStream.read(bytes)) != -1) {}
        bufferedInputStream.close();
        errorStream.close();
        process.getInputStream().close();
        process.getOutputStream().close();
    }

    public static void main(String[] args) {
        try {
            MergeVideoBgm.getCover("C:\\Users\\A\\Videos\\Captures\\wxd523b10fd7eaf666.o6zAJs1GYc4u3zqRPgd9J1cRyfyA.ChVJVKPzziM0fbc6e579ceecab6e80e6746a4b8b2078.mp4","C:\\Users\\A\\Pictures\\Saved Pictures\\1.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
