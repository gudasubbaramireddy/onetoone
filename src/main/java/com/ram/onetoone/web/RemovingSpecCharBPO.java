package com.ram.onetoone.web;

import org.apache.pdfbox.pdmodel.font.encoding.WinAnsiEncoding;

public class RemovingSpecCharBPO {
    public static String remove(String test) {
        try {
            StringBuilder b = new StringBuilder();
            for (int i = 0; i < test.length(); i++) {
                if (WinAnsiEncoding.INSTANCE.contains(test.charAt(i))) {
                    b.append(test.charAt(i));
                }
            }
            return b.toString();
        } catch (Exception e) {
           // LOGGER.debug("RemovingSpecCharBPO@remove", e);
            return null;
        }
    }
}
