package com.ram.onetoone.web;

import com.ram.onetoone.dto.EmployeeDao;
import com.ram.onetoone.entity.Employee;
import com.ram.onetoone.entity.StudentModel;
import com.ram.onetoone.pdf.PDFBoxBPO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StudentRestController {


    @GetMapping("/data")
    public StudentModel findAll() {
        StudentModel model = new StudentModel();
        model.setfName("ram");
        model.setlName("reddy");
        return model;
    }

    @PostMapping("/getdata")
    public StudentModel postdata(@RequestBody StudentModel model) throws IOException {
        try {
            String[] parts = model.getLogo().split("\\,");
            String imageString = parts[1];
            BufferedImage image = null;
            byte[] imageByte;
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
            File outputfile = new File("/home/ram/Downloads/olddownloads/onetoone/src/main/resources/file.png");
            ImageIO.write(image, "png", outputfile);
            System.out.println("hi check it once");
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            PDImageXObject pdImage = PDImageXObject.createFromFile("/home/ram/Downloads/olddownloads/onetoone/src/main/resources/file.png", document);
            contentStream.drawXObject(pdImage, 55, 690, 100, 100);
            //contentStream.drawImage(pdImage, 55, 725);
            contentStream.setNonStrokingColor(new Color(226, 229, 236));
            contentStream.setNonStrokingColor(new Color(173, 216, 230));
            // Top rectangle
            contentStream.fillRect(40, 800, 515, 7);

            // Bottom rectangle
            contentStream.fillRect(40, 20, 515, 7);

            // Left rectangle
            contentStream.fillRect(40, 20, 7, 783);

            // Right rectangle
            contentStream.fillRect(550, 20, 7, 787);
            contentStream.setNonStrokingColor(Color.BLACK);
            PDFBoxBPO.rightToLeft(contentStream, 30, 763, page, PDType1Font.HELVETICA, 9, model.getfName());
            contentStream.setNonStrokingColor(Color.GRAY);
            PDFBoxBPO.rightToLeft(contentStream, 55, 763, page, PDType1Font.HELVETICA, 9, model.getlName());
            contentStream.setNonStrokingColor(Color.BLACK);
            PDFBoxBPO.rightToLeft(contentStream, 30, 745, page, PDType1Font.HELVETICA, 9, model.getfName());
            contentStream.setNonStrokingColor(Color.GRAY);
            PDFBoxBPO.rightToLeft(contentStream, 55, 745, page, PDType1Font.HELVETICA, 9, model.getfName());
            contentStream.setNonStrokingColor(Color.BLACK);
            PDFBoxBPO.setAlignmentText(contentStream, 0, 120, page, PDType1Font.HELVETICA, 12, model.getfName());
            PDFBoxBPO.setAlignmentText(contentStream, 0, 155, page, PDType1Font.HELVETICA_BOLD, 16, "");
            contentStream.setNonStrokingColor(Color.GRAY);
            PDFBoxBPO.rightToLeft(contentStream, 30, 647, page, PDType1Font.TIMES_ROMAN, 10, "______________________________________________________________________________________________");
            contentStream.setNonStrokingColor(Color.BLACK);
            PDFBoxBPO.leftToRight(contentStream, 65, 630, page, PDType1Font.HELVETICA, 9, "Student ");
            PDFBoxBPO.rightToLeft(contentStream, 30, 630, page, PDType1Font.HELVETICA, 9, "Student(" + "" + ")");
            contentStream.setNonStrokingColor(Color.GRAY);
            PDFBoxBPO.rightToLeft(contentStream, 30, 620, page, PDType1Font.TIMES_ROMAN, 10, "______________________________________________________________________________________________");
            contentStream.setNonStrokingColor(Color.BLACK);
            PDFBoxBPO.leftToRight(contentStream, 65, 605, page, PDType1Font.HELVETICA, 9, "Student marks");
            PDFBoxBPO.rightToLeft(contentStream, 30, 605, page, PDType1Font.HELVETICA, 9, "INR ");
            PDFBoxBPO.leftToRight(contentStream, 65, 590, page, PDType1Font.HELVETICA, 9, "Extra Fare");
            PDFBoxBPO.rightToLeft(contentStream, 30, 590, page, PDType1Font.HELVETICA, 9, "INR " + "getExtraFare");
            PDFBoxBPO.leftToRight(contentStream, 65, 575, page, PDType1Font.HELVETICA, 9, "Extra Time Fare");
            PDFBoxBPO.rightToLeft(contentStream, 30, 575, page, PDType1Font.HELVETICA, 9, "INR " + "ExtraTimeFare");
            PDFBoxBPO.leftToRight(contentStream, 65, 560, page, PDType1Font.HELVETICA, 9, "Allowance");
            PDFBoxBPO.rightToLeft(contentStream, 30, 560, page, PDType1Font.HELVETICA, 9, "INR 0.00");
            PDFBoxBPO.leftToRight(contentStream, 65, 545, page, PDType1Font.HELVETICA, 9, "Discount");
            PDFBoxBPO.rightToLeft(contentStream, 30, 545, page, PDType1Font.HELVETICA, 9, "INR " + "getPromoAmount");
            PDFBoxBPO.leftToRight(contentStream, 65, 530, page, PDType1Font.HELVETICA, 9, "Tax");
            PDFBoxBPO.rightToLeft(contentStream, 30, 530, page, PDType1Font.HELVETICA, 9, "INR " + "getActualTax");
            PDFBoxBPO.leftToRight(contentStream, 65, 515, page, PDType1Font.HELVETICA, 9, "Sub Total");
            contentStream.setNonStrokingColor(Color.BLACK);
            PDFBoxBPO.rightToLeft(contentStream, 30, 515, page, PDType1Font.HELVETICA, 9, "INR " + "getActualTotalFare");
            contentStream.setNonStrokingColor(Color.GRAY);
            PDFBoxBPO.leftToRight(contentStream, 65, 500, page, PDType1Font.TIMES_ROMAN, 10, "---------------------------------------------------------------------------------------------------------------------------------------------");
            contentStream.setNonStrokingColor(Color.BLACK);
            PDFBoxBPO.leftToRight(contentStream, 65, 485, page, PDType1Font.HELVETICA_BOLD, 9, "Total Customer Fare");
            contentStream.setNonStrokingColor(Color.BLACK);
            PDFBoxBPO.rightToLeft(contentStream, 30, 485, page, PDType1Font.HELVETICA_BOLD, 9, "INR " + "getActualTotalFare");
            contentStream.setNonStrokingColor(Color.GRAY);
            PDFBoxBPO.leftToRight(contentStream, 65, 475, page, PDType1Font.TIMES_ROMAN, 10, "______________________________________________________________________________________________");
            contentStream.setNonStrokingColor(Color.BLACK);
            String actualSource = "Source : " + RemovingSpecCharBPO.remove("Details.getActualSource");
            String actualDest = "Destination : " + RemovingSpecCharBPO.remove("");
            int actualSourceLineSize = PDFBoxBPO.showMultiLineText(contentStream, 65, 450, 480, page, PDType1Font.HELVETICA, 9, actualSource);
            int actualDestLineSize = PDFBoxBPO.getSizeOfLines(480, PDType1Font.HELVETICA, 9, actualDest);
            if (actualSourceLineSize == 2 || actualSourceLineSize == 1) {
                PDFBoxBPO.showMultiLineText(contentStream, 65, 435 - (actualSourceLineSize * 13) + 10, 480, page, PDType1Font.HELVETICA, 9, actualDest);
                PDFBoxBPO.leftToRight(contentStream, 65, 428 - ((actualDestLineSize * 13) + (actualSourceLineSize * 13) - 20), page, PDType1Font.TIMES_ROMAN, 10, "______________________________________________________________________________________________");
                PDFBoxBPO.leftToRight(contentStream, 65, 410 - ((actualDestLineSize * 13) + (actualSourceLineSize * 13) - 20), page, PDType1Font.HELVETICA, 9, "Authorised Signatory");
                PDFBoxBPO.showMultiLineText(contentStream, 65, 375 - ((actualDestLineSize * 13) + (actualSourceLineSize * 13) - 20), 480, page, PDType1Font.HELVETICA, 7, "Please note: 1. This invoice is issued on behalf of  Service Provider. Best Solutions Private Limited acts in the capacity of an Electronic Commerce Operator as per Section 9(5) of the Central  Service Tax Act, 2017 & corresponding Section 5(5) of the State GST laws. 2. This invoice has been signed by the Authorized signatory of Best Solutions Limited only limited purposes of complying as an Electronic Commerce Operator.");
            } else if (actualSourceLineSize > 2 && (actualDestLineSize == 1 || actualDestLineSize == 2)) {
                PDFBoxBPO.showMultiLineText(contentStream, 65, 435 - (actualSourceLineSize * 13) + 10, 480, page, PDType1Font.HELVETICA, 9, actualDest);
                PDFBoxBPO.leftToRight(contentStream, 65, 428 - ((actualDestLineSize * 13) + (actualSourceLineSize * 13) - 20), page, PDType1Font.TIMES_ROMAN, 10, "______________________________________________________________________________________________");
                PDFBoxBPO.leftToRight(contentStream, 65, 410 - ((actualDestLineSize * 13) + (actualSourceLineSize * 13) - 20), page, PDType1Font.HELVETICA, 9, "Authorised Signatory");
                PDFBoxBPO.showMultiLineText(contentStream, 65, 375 - ((actualDestLineSize * 13) + (actualSourceLineSize * 13) - 20), 480, page, PDType1Font.HELVETICA, 7, "Please note: 1. This invoice is issued on behalf of Service Provider. Best  Solutions Private Limited acts in the capacity of an Electronic Commerce Operator as per Section 9(5) of the Central  Service Tax Act, 2017 & corresponding Section 5(5) of the State GST laws. 2. This invoice has been signed by the Authorized signatory of Best Solutions Limited only limited purposes of complying as an Electronic Commerce Operator.");
            } else {
                PDFBoxBPO.showMultiLineText(contentStream, 65, 435 - (actualSourceLineSize * 13), 480, page, PDType1Font.HELVETICA, 9, actualDest);
                PDFBoxBPO.leftToRight(contentStream, 65, 428 - ((actualDestLineSize * 13) + (actualSourceLineSize * 13)), page, PDType1Font.TIMES_ROMAN, 10, "______________________________________________________________________________________________");
                PDFBoxBPO.leftToRight(contentStream, 65, 410 - ((actualDestLineSize * 13) + (actualSourceLineSize * 13)), page, PDType1Font.HELVETICA, 9, "Authorised Signatory");
                PDFBoxBPO.showMultiLineText(contentStream, 65, 375 - ((actualDestLineSize * 13) + (actualSourceLineSize * 13)), 480, page, PDType1Font.HELVETICA, 7, "Please note: 1. This invoice is issued on behalf of Service Provider. Best  Solutions Private Limited acts in the capacity of an Electronic Commerce Operator as per Section 9(5) of the Central  Service Tax Act, 2017 & corresponding Section 5(5) of the State GST laws. 2. This invoice has been signed by the Authorized signatory of Best Solutions Limited only limited purposes of complying as an Electronic Commerce Operator.");
            }
            contentStream.close();
            document.save("/home/ram/Downloads/olddownloads/onetoone/src/main/resources/test.pdf");
            document.close();
        } catch (Exception e) {
            //LOGGER.debug("TripInvoiceBPO@customerCityTripInvoice", e);
            e.printStackTrace();
            return null;
        }
        return model;
    }
}
