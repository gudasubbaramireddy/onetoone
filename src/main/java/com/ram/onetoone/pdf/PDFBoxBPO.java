package com.ram.onetoone.pdf;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PDFBoxBPO {

    public static void saveData(PDPageContentStream pdpcs, int x, int y, PDType1Font type1Font, int fontSize, String text) {
        try {
            pdpcs.beginText();
            pdpcs.setFont(type1Font, fontSize);
            pdpcs.setLeading(14.5f);
            pdpcs.newLineAtOffset(x, y);
            pdpcs.showText(text);
            pdpcs.endText();
        } catch (Exception e) {
           // LOGGER.debug("PDFBoxBPO@saveData", e);
        }
    }

    public static void rightToLeft(PDPageContentStream contentStream, int paddingRight, int y, PDPage page, PDFont font, int fontSize, String text) throws IOException {
        contentStream.beginText();
        contentStream.setFont(font, fontSize);
        contentStream.setLeading(14.5f);
        float pagewidth = page.getMediaBox().getWidth();
        float text_width = (font.getStringWidth(text) / 1000.0f) * fontSize;
        float x = pagewidth - ((paddingRight * 2) + text_width);
        contentStream.newLineAtOffset(x, y);
        contentStream.showText(text);
        contentStream.newLineAtOffset(-x, y);
        contentStream.endText();
    }

    public static void leftToRight(PDPageContentStream contentStream, int x, int y, PDPage page, PDFont font, int size, String text) throws IOException {
        try {
            contentStream.beginText();
            contentStream.setFont(font, size);
            contentStream.setLeading(14.5f);
            contentStream.newLineAtOffset(x, y);
            contentStream.showText(text);
            contentStream.endText();
        } catch (Exception e) {
     //       LOGGER.debug("PDFBoxBPO@leftToRight", e);
        }
    }

    public static void leftToRight1(PDPageContentStream contentStream, int x, int y, int allowedWidth, PDPage page, PDFont font, int fontSize, String text) throws IOException {
        contentStream.beginText();
        contentStream.newLineAtOffset(x, y);
        contentStream.setFont(font, fontSize);
        contentStream.setLeading(14.5f);
        List<String> lines = new ArrayList<>();
        String line = "";
        // split the text on spaces
        String[] words = text.split(" ");
        for (String word : words) {

            if (!line.isEmpty()) {
                line += " ";
            }
            // check if adding the word to the line surpasses the width of the page
            int size = (int) (fontSize * font.getStringWidth(line + word) / 1000);
            if (size > allowedWidth) {
                // if line + word surpasses the width of the page, add the line without the current word
                lines.add(line);
                // start new line with the current word
                line = word;
            } else {
                // if line + word fits the page width, add the current word to the line
                line += word;
            }
        }
        lines.add(line);

        for (String ln : lines) {
            System.out.println("Line- " + ln);
            contentStream.showText(ln);
            contentStream.newLine();
        }
        contentStream.endText();
    }

    public static void setAlignmentText(PDPageContentStream contentStream, int marginRight, int marginTop, PDPage page, PDFont font, int fontSize, String text) {
        try {
            PDRectangle mediaBox = page.getMediaBox();
            float tw = font.getStringWidth(text) / 1000 * 12;
            float th = font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * 12;
            float sx = (mediaBox.getWidth() - marginRight - tw) / 2;
            float sy = mediaBox.getHeight() - marginTop - th;
            contentStream.beginText();
            contentStream.setFont(font, fontSize);
            contentStream.setLeading(14.5f);
            contentStream.newLineAtOffset(sx, sy);
            contentStream.showText(text);
            contentStream.endText();
        } catch (Exception e) {
        }
    }

    public static int showMultiLineText(PDPageContentStream contentStream, int x, int y, int allowedWidth, PDPage page, PDFont font, int fontSize, String text) throws IOException {
        contentStream.beginText();
        contentStream.newLineAtOffset(x, y);
        contentStream.setFont(font, fontSize);
        contentStream.setLeading(14.5f);
        List<String> lines = new ArrayList<String>();
        String line = "";
        // split the text on spaces
        String[] words = text.split(" ");
        for (String word : words) {

            if (!line.isEmpty()) {
                line += " ";
            }
            // check if adding the word to the line surpasses the width of the page
            int size = (int) (fontSize * font.getStringWidth(line + word) / 1000);
            if (size > allowedWidth) {
                // if line + word surpasses the width of the page, add the line without the current word
                lines.add(line);
                // start new line with the current word
                line = word;
            } else {
                // if line + word fits the page width, add the current word to the line
                line += word;
            }
        }
        lines.add(line);

        for (String ln : lines) {
            System.out.println("Line- " + ln);
            contentStream.showText(ln);
            contentStream.newLine();
        }
        contentStream.endText();
        return lines.size();
    }

    public static int getSizeOfLines(int allowedWidth, PDFont font, int fontSize, String text) {
        try {
            List<String> lines = new ArrayList<String>();
            String line = "";
            // split the text on spaces
            String[] words = text.split(" ");
            for (String word : words) {

                if (!line.isEmpty()) {
                    line += " ";
                }
                // check if adding the word to the line surpasses the width of the page
                int size = (int) (fontSize * font.getStringWidth(line + word) / 1000);
                if (size > allowedWidth) {
                    // if line + word surpasses the width of the page, add the line without the current word
                    lines.add(line);
                    // start new line with the current word
                    line = word;
                } else {
                    // if line + word fits the page width, add the current word to the line
                    line += word;
                }
            }
            lines.add(line);
            return lines.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public static InputStream getCompaneyLogo(String path) {
        try {
            String propertiesFile = "resources/2.png";
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            InputStream resourceAsStream = contextClassLoader.getResourceAsStream(propertiesFile);
            return resourceAsStream;
        } catch (Exception e) {
            return null;
        }
    }

    public static String getCompaneyLogoFromPackage(String path) {
        try {
            return PDFBoxBPO.class.getClassLoader().getResource(path).getPath();
        } catch (Exception e) {
            return null;
        }
    }
}
