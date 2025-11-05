package ru.d51x.kaierutils.utils;

import java.util.ArrayList;

public class StringUtils {

    public static String bufferToHex(ArrayList<Integer> buffer, int fromIdx, boolean withSpace) {
        StringBuilder res = new StringBuilder();
        if (buffer.size() > 1 && fromIdx < buffer.size()) {
            for (int i = fromIdx; i < buffer.size(); i++) {
                res.append(String.format("%02X", buffer.get(i)));
                if (withSpace) {
                    res.append(" ");
                }
            }
        }
        return res.toString();
    }

    public static ArrayList<Integer> hexStringToBuffer(String hexStr, int fromIdx) {
        ArrayList<Integer> buffer = null;
        if (hexStr.length() > 2 && hexStr.length() % 2 == 0 &&
                fromIdx < hexStr.length() && fromIdx % 2 == 0) {
            for (int i = fromIdx; i < hexStr.length(); i += 2) {
                String s = hexStr.substring(i, i + 1);
                buffer.add(Integer.parseInt(s, 16));
            }
        }
        return buffer;
    }
}
