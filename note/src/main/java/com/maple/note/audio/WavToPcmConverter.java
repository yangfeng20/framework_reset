package com.maple.note.audio;

import lombok.extern.slf4j.Slf4j;

import javax.sound.sampled.*;
import java.io.*;


@Slf4j
public class WavToPcmConverter {
    public static void main(String[] args) {
        System.out.println(wavToPcm("C:\\Users\\maple\\Downloads\\Music\\人工.wav"));
    }

    private static String wavToPcm(String wavPath) {
        String pcmFileName = wavPath.split("\\.")[0] + ".pcm";
        try {
            // 读取WAV文件
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(wavPath));

            // 获取输入文件的音频格式
            AudioFormat format = audioInputStream.getFormat();

            // 将WAV文件的音频数据写入到输出的PCM文件中
            AudioInputStream pcmAudioInputStream = new AudioInputStream(audioInputStream, new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    format.getSampleRate(),
                    16,
                    format.getChannels(),
                    format.getChannels() * 2,
                    format.getSampleRate(),
                    false), audioInputStream.getFrameLength());

            // 写入PCM文件
            AudioSystem.write(pcmAudioInputStream, AudioFileFormat.Type.WAVE, new File(pcmFileName));

            // 关闭流
            audioInputStream.close();
            pcmAudioInputStream.close();
        } catch (Exception e) {
            log.info("wavToPcm失败 message:{}", e.getMessage(), e);
        }

        return pcmFileName;
    }
}
