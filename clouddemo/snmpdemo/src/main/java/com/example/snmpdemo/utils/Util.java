package com.example.snmpdemo.utils;

import java.net.*;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;

/**
 * 工具类
 *
 * @author : demo
 * @version : 1.0
 * @since : 2022/11/2 15:49
 */
public class Util {
    public static final String[] C = {
            "0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "a", "b", "c", "d", "e", "f"
    };

    /**
     * 拼接两个字节数组
     *
     * @param byte1 字节数组1
     * @param byte2 字节数组2
     * @return 返回拼接好的字节数组(字节数组1 + 字节数组2)
     */
    public static byte[] byteMerger(byte[] byte1, byte[] byte2) {
        byte[] byte3 = new byte[byte1.length + byte2.length];
        System.arraycopy(byte1, 0, byte3, 0, byte1.length);
        System.arraycopy(byte2, 0, byte3, byte1.length, byte2.length);
        return byte3;
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param bytes 字节数组
     * @return 转换后的十六进制字符串
     */
    public static String[] byteToHex(byte[] bytes) {
        String[] hex = new String[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            int h = (bytes[i] >> 4) & 0xf;
            int l = bytes[i] & 0xf;
            hex[i] = C[h] + C[l];
        }
        return hex;
    }

    /**
     * 将字节数组转换为二进制字符串
     *
     * @param bytes 字节数组
     * @return 转换后的二进制字符串
     */
    public static String byteToBinary(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
        sb.append('[');
        for (int i = 0; i < Byte.SIZE * bytes.length; i++) {
            if (i != 0 && i % 8 == 0) {
                sb.append(' ');
            }
            sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
        }
        sb.append(']');
        return sb.toString();
    }

    /**
     * 将字节数组转换为十进制数组
     *
     * @param bytes 字节数组
     * @return 返回转换后的十进制数组
     */
    public static int[] byteToDec(byte[] bytes) {
        int[] dec = new int[bytes.length];
        int i = 0;
        for (byte b : bytes) {
            dec[i++] = b & 0xff;
        }
        return dec;
    }

    /**
     * 将int型整数转换为字节数组
     *
     * @param num int整数
     * @return 字节数组
     */
    public static byte[] intToBytes(int num) {
        int n = 0;
        byte[] result;
        if (num <= 255 && num >= 0) {
            return new byte[]{(byte) (num)};
        } else if (num <= 65535 && num >= 256) {
            result = new byte[2];
            for (int i = 1; i >= 0; i--) {
                result[i] = (byte) (num >> n);
                n = n + 8;
            }
            return result;
        } else if (num <= 16777215 && num >= 65536) {
            result = new byte[3];
            for (int i = 2; i >= 0; i--) {
                result[i] = (byte) (num >> n);
                n = n + 8;
            }
            return result;
        } else if (num >= 16777216) {
            result = new byte[4];
            for (int i = 3; i >= 0; i--) {
                result[i] = (byte) (num >> n);
                n = n + 8;
            }
            return result;
        }
        return null;
    }

    /**
     * 分别以十进制、十六进制、二进制显示数据包
     *
     * @param data 数据包编码
     */
    public static void showPacket(byte[] data) {
        System.out.println("BER-TLV(DEC)=" + Arrays.toString(Util.byteToDec(data)));
        System.out.println("BER-TLV(HEX)=" + Arrays.toString(Util.byteToHex(data)));
        System.out.println("BER-TLV(BIN)=" + Util.byteToBinary(data) + "\n");
        char[] cArr = byteToChar(data);
        //System.out.println(String.valueOf(cArr)+ "\n");

        System.out.println("resp len-"+ cArr.length);
        for(int i=0,len=cArr.length;i<len;i++) {
            System.out.print(cArr[i]);
        }
        System.out.println("\n");

        System.out.println(new String(cArr));
    }

    /**
     * 将字节数转换为整型
     *
     * @param data 字节数据
     * @return 转换后的整型
     */
    public static int bytesToInt(byte[] data) {
        byte[] bytes = {0, 0, 0, 0};
        System.arraycopy(data, 0, bytes, (4 - data.length), data.length);
        return bytes[0] << 24 | (bytes[1] & 0xff) << 16 | (bytes[2] & 0xff) << 8 | (bytes[3] & 0xff);
    }

    /**
     * 将字节数组转换成对应的字符数组
     *
     * @param bytes 需要转换的字节数组
     * @return 转换后的字符数组
     */
    public static char[] byteToChar(byte[] bytes) {
        Charset charset = StandardCharsets.ISO_8859_1;
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
        byteBuffer.put(bytes);
        byteBuffer.flip();
        CharBuffer charBuffer = charset.decode(byteBuffer);
        return charBuffer.array();
    }

    /**
     * 生成一个100以内的随机数
     *
     * @return 返回随机数
     */
    public static String getNum() {
        Random rd = new Random();
        int r = rd.nextInt(100);
        return String.valueOf(r);
    }

}
