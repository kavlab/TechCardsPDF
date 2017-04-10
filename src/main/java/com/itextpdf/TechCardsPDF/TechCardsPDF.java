package com.itextpdf.TechCardsPDF;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceCmyk;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.element.Image;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.util.*;

class DataStruct {
    public int field;
    public int align;
    public float angle;
    public String text;
    public String font;
    public int fontSize;
    public int nPage;
    public Boolean isTemplate;
    public Boolean isImage;
}

class FileStruct
{
    public String filename;
    public PdfDocument reader;
    public ArrayList<DataStruct> texts;
}

public class TechCardsPDF {

    static Logger logger = Logger.getLogger(TechCardsPDF.class);

    public TechCardsPDF(String fileConf) throws IOException {
        String fileNameDest = "result.pdf";

        ArrayList<FileStruct> confArray = new ArrayList<>();
        ArrayList<String> fontsArray = new ArrayList<>();

        try {
            XMLStreamReader xmlReader = XMLInputFactory.newInstance().createXMLStreamReader(fileConf, new FileInputStream(fileConf));
            while(xmlReader.hasNext()) {
                xmlReader.next();
                if(xmlReader.isStartElement()) {
                    if(xmlReader.getLocalName().equals("file_result") && xmlReader.getAttributeCount() > 0) {
                        fileNameDest = xmlReader.getAttributeValue("", "name");
                    }
                    else if(xmlReader.getLocalName().equals("file")) {
                        FileStruct fs = null;
                        if(xmlReader.getAttributeCount() > 0) {
                            fs = new FileStruct();
                            fs.filename = xmlReader.getAttributeValue("", "name");
                        }
                        if(fs != null) {
                            fs.texts = new ArrayList<>();
                            while(!(xmlReader.isEndElement() && xmlReader.getLocalName().equals("file"))) {
                                if(xmlReader.isStartElement() && xmlReader.getLocalName().equals("text")) {
                                    DataStruct ds = new DataStruct();
                                    ds.field = Integer.valueOf(xmlReader.getAttributeValue("","field"));
                                    ds.align = Integer.valueOf(xmlReader.getAttributeValue("", "align"));
                                    ds.angle = Float.valueOf(xmlReader.getAttributeValue("","angle"));
                                    ds.font = xmlReader.getAttributeValue("","font");
                                    if(ds.font != null && !ds.font.isEmpty()) {
                                        if(!fontsArray.contains(ds.font)) {
                                            fontsArray.add(ds.font);
                                        }
                                    }
                                    ds.fontSize = Integer.valueOf(xmlReader.getAttributeValue("","fontsize"));
                                    if(xmlReader.getAttributeValue("","npage") == null) {
                                        ds.nPage = -1;
                                    } else {
                                        ds.nPage = Integer.valueOf(xmlReader.getAttributeValue("", "npage"));
                                    }
                                    String template = xmlReader.getAttributeValue("","template");
                                    if (template == null)
                                    {
                                        ds.isTemplate = false;
                                        ds.text = xmlReader.getElementText();
                                        ds.isImage = false;
                                    }
                                    else
                                    {
                                        ds.isTemplate = true;
                                        ds.text = template;
                                        ds.isImage = !(ds.text.equals("npage"));
                                    }
                                    fs.texts.add(ds);
                                }
                                xmlReader.next();
                            }
                            confArray.add(fs);
                        }
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException ex) {
            ex.printStackTrace();
        }

        if(confArray.size() == 0)
        {
            System.out.println("Error in the config file.");
            System.exit(2);
        }

        File f = new File(fileNameDest);
        if(f.exists()) {
            try {
                Boolean result = f.delete();
                if(!result) {
                    throw new Exception("Failed to delete the result file.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        for(String font : fontsArray) {
            if(!font.endsWith(".ttf")) {
                font += ".ttf";
            }
            PdfFontFactory.register(font);
        }
        //PdfFontFactory.getRegisteredFonts();

        Coordinates coordinates = new Coordinates();

        Color blackColor = new DeviceCmyk(0f, 0f, 0f, 1f);

        int nPage = 0;
        PdfDocument writer = new PdfDocument(new PdfWriter(fileNameDest));
        for (FileStruct fs : confArray)
        {
            fs.reader = new PdfDocument(new PdfReader(fs.filename));

            for (int i = 0; i < fs.reader.getNumberOfPages();)
            {
                nPage++;
                PdfPage origPage = fs.reader.getPage(++i);
                PdfPage page = writer.addPage(origPage.copyTo(writer));
                page.setIgnorePageRotationForContent(true);
                PdfCanvas pdfCanvas = new PdfCanvas(page);

                float Height = origPage.getPageSize().getHeight() - 7;

                try {
                    Format format = Coordinates.getFormat(origPage.getPageSize().getWidth(), origPage.getPageSize().getHeight());

                    for (DataStruct ds : fs.texts) {
                        if (ds.nPage == -1 || ds.nPage == nPage) {
                            if (!ds.isImage) {
                                PdfFont bf = PdfFontFactory.createRegisteredFont(ds.font, PdfEncodings.IDENTITY_H);
                                if (bf == null)
                                    bf = PdfFontFactory.createFont(FontConstants.HELVETICA, "cp1251");
                                pdfCanvas.beginText();
                                pdfCanvas.setColor(blackColor, true);
                                pdfCanvas.setFontAndSize(bf, ds.fontSize);

                                float dWidth = 0;
                                if (ds.align == 1)
                                    dWidth = bf.getWidth(ds.text, ds.fontSize) / 2;

                                Point point = coordinates.getPoints(format, ds.field);
                                if (point == null) {
                                    throw new Exception("Unsupported format");
                                }
                                if (ds.angle == 0) {
                                    pdfCanvas.setTextMatrix(1, 0, 0.2f, 1, point.getX() - dWidth, Height - point.getY());
                                } else {
                                    double alpha = ds.angle * Math.PI / 180.0;
                                    float cos = (float) Math.cos(alpha);
                                    float sin = (float) Math.sin(alpha);
                                    pdfCanvas.setTextMatrix(cos, sin, -sin, cos + 0.2f, point.getX(), Height - point.getY() + dWidth);
                                }
                            }
                            if (ds.isTemplate) {
                                if (ds.text.equals("npage")) {
                                    pdfCanvas.showText(Integer.toString(nPage));
                                } else if (ds.text.equals("sample")) {
                                    Rectangle rect = page.getPageSizeWithRotation();
                                    Image sample = new Image(ImageDataFactory.create(getClass().getResource("/img/sample.png")));
                                    pdfCanvas.addImage(ImageDataFactory.create(getClass().getResource("/img/sample.png")), rect.getWidth() / 2 - sample.getImageWidth() / 2, rect.getHeight() / 2 - sample.getImageHeight() / 2, false);
                                } else if (ds.text.equals("cancel")) {
                                    Rectangle rect = page.getPageSizeWithRotation();
                                    Image cancel = new Image(ImageDataFactory.create(getClass().getResource("/img/cancel.png")));
                                    pdfCanvas.addImage(ImageDataFactory.create(getClass().getResource("/img/cancel.png")), rect.getWidth() / 2 - cancel.getImageWidth() / 2, rect.getHeight() / 2 - cancel.getImageHeight() / 2, false);
                                }
                            }
                            if (!ds.isImage) {
                                if (!ds.isTemplate && !ds.text.equals("npage"))
                                    pdfCanvas.showText(ds.text);
                                pdfCanvas.endText();
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        writer.close();

        for (FileStruct fs : confArray)
        {
            fs.reader.close();
        }

        System.exit(0);
    }

    public static void main(String args[]) throws IOException {
        if(args.length == 0){
            System.out.println("Correct syntax:");
            System.out.println("\tjava.exe TechCardsPDF.java conf.xml");
            System.exit(1);
        }

        System.out.println("Please wait...");

        String fileConf = args[0];

        BasicConfigurator.configure();

        new TechCardsPDF(fileConf);
    }

}
