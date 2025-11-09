package ru.d51x.kaierutils.utils;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

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
        ArrayList<Integer> buffer = new ArrayList<>();
        if (hexStr.length() > 2 && hexStr.length() % 2 == 0 &&
                fromIdx < hexStr.length() && fromIdx % 2 == 0) {
            for (int i = fromIdx; i < hexStr.length(); i += 2) {
                String s = hexStr.substring(i, i + 2);
                int val = Integer.parseInt(s, 16);
                buffer.add(val);
            }
        }
        return buffer;
    }

    public static byte[] toByteArray(Collection<Integer> ints, int fromIdx) {
        // an integer in Java is a 32-bit number (i.e., 4 bytes)
        int capacity = Math.multiplyExact(ints.size(), 4);
        ByteBuffer buffer = ByteBuffer.allocate(capacity);
        //ints.forEach(buffer::putInt);
        AtomicInteger i = new AtomicInteger();
        ints.forEach( integer -> {
            if (i.get() >= fromIdx) {
                buffer.put(integer.byteValue());
            }
            i.getAndIncrement();
        });
        return buffer.array();
    }
}
