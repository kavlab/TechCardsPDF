package com.itextpdf.TechCardsPDF;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.element.Image;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.util.*;

class DataStruct
{
    public float x;
    public float y;
    public int align;
    public float angle;
    public String text;
    public String font;
    public int fontsize;
    public Boolean isTemplate;
}

class FileStruct
{
    public String filename;
    public PdfDocument reader;
    public ArrayList<DataStruct> texts;
}

public class TechCardsPDF {

    /*public static final String FONTS = "src/main/resources/fonts";
    public static final String SAMPLE_IMG = "src/main/resources/img/sample.png";
    public static final String CANCEL_IMG = "src/main/resources/img/cancel.png";*/

    public TechCardsPDF(String fileconf) throws IOException {
        String filename_dst = "result.pdf";

        ArrayList<FileStruct> conf_array = new ArrayList<>();

        try {
            XMLStreamReader xmlreader = XMLInputFactory.newInstance().createXMLStreamReader(fileconf, new FileInputStream(fileconf));
            while(xmlreader.hasNext()) {
                xmlreader.next();
                if(xmlreader.isStartElement()) {
                    if(xmlreader.getLocalName().equals("file_result") && xmlreader.getAttributeCount() > 0) {
                        filename_dst = xmlreader.getAttributeValue("", "name");
                    }
                    else if(xmlreader.getLocalName().equals("file")) {
                        FileStruct fs = null;
                        if(xmlreader.getAttributeCount() > 0) {
                            fs = new FileStruct();
                            fs.filename = xmlreader.getAttributeValue("", "name");
                        }
                        if(fs != null) {
                            fs.texts = new ArrayList<>();
                            while(!(xmlreader.isEndElement() && xmlreader.getLocalName().equals("file"))) {
                                if(xmlreader.isStartElement() && xmlreader.getLocalName().equals("text")) {
                                    DataStruct ds = new DataStruct();
                                    ds.x = Float.valueOf(xmlreader.getAttributeValue("","x"));
                                    ds.y = Float.valueOf(xmlreader.getAttributeValue("","y"));
                                    ds.align = Integer.valueOf(xmlreader.getAttributeValue("", "align"));
                                    ds.angle = Float.valueOf(xmlreader.getAttributeValue("","angle"));
                                    ds.font = xmlreader.getAttributeValue("","font");
                                    ds.fontsize = Integer.valueOf(xmlreader.getAttributeValue("","fontsize"));
                                    String template = xmlreader.getAttributeValue("","template");
                                    if (template == null)
                                    {
                                        ds.isTemplate = false;
                                        ds.text = xmlreader.getElementText();
                                    }
                                    else
                                    {
                                        ds.isTemplate = true;
                                        ds.text = template;
                                    }
                                    fs.texts.add(ds);
                                }
                                xmlreader.next();
                            }
                            conf_array.add(fs);
                        }
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException ex) {
            ex.printStackTrace();
        }

        if(conf_array.size() == 0)
        {
            System.out.println("Error in the config file.");
            System.exit(2);
        }

        File f = new File(filename_dst);
        if(f.exists())
            f.delete();

        //System.out.println(getClass().getResource("/fonts").getPath());
        //PdfFontFactory.registerDirectory(getClass().getResource("/fonts").getPath());
        //PdfFontFactory.registerDirectory("src/main/resources/fonts");
        //PdfFontFactory.register(getClass().getResource("/fonts/ISOCPEUR.ttf"));
        InputStream font = getClass().getResourceAsStream("/fonts/ISOCPEUR.ttf");
        OutputStream fontFile = new FileOutputStream("./ISOCPEUR.ttf");
        byte[] buffer = new byte[1024];
        int length;
        while ((length = font.read(buffer)) > 0) {
            fontFile.write(buffer, 0, length);
        }
        font.close();
        fontFile.close();
        PdfFontFactory.register("./ISOCPEUR.ttf");

        int npage = 0;
        PdfDocument writer = new PdfDocument(new PdfWriter(filename_dst));
        for (FileStruct fs : conf_array)
        {
            fs.reader = new PdfDocument(new PdfReader(fs.filename));

            for (int i = 0; i < fs.reader.getNumberOfPages();)
            {
                npage++;
                PdfPage origPage = fs.reader.getPage(++i);
                PdfPage page = writer.addPage(origPage.copyTo(writer));
                PdfCanvas pdfCanvas = new PdfCanvas(page);

                float Height = origPage.getPageSize().getHeight() - 7;

                for (DataStruct ds : fs.texts)
                {
                    PdfFont bf = PdfFontFactory.createRegisteredFont(ds.font, PdfEncodings.IDENTITY_H);
                    if(bf == null)
                        bf = PdfFontFactory.createFont(FontConstants.HELVETICA, "cp1251");
                    pdfCanvas.beginText();
                    pdfCanvas.setFontAndSize(bf, ds.fontsize);

                    float dwidth = 0;
                    if (ds.align == 1)
                        dwidth = bf.getWidth(ds.text, ds.fontsize) / 2;

                    if (ds.angle == 0)
                    {
                        pdfCanvas.setTextMatrix(1, 0, 0.2f, 1, ds.x - dwidth, Height - ds.y);
                    }
                    else
                    {
                        double alpha = ds.angle * Math.PI / 180.0;
                        float cos = (float)Math.cos(alpha);
                        float sin = (float)Math.sin(alpha);
                        pdfCanvas.setTextMatrix(cos, sin, -sin, cos + 0.2f, ds.x, Height - ds.y + dwidth);
                    }
                    if(ds.isTemplate)
                    {
                        if(ds.text.equals("npage")) {
                            pdfCanvas.showText(Integer.toString(npage));
                        }
                        else if(ds.text.equals("sample")) {
                            Rectangle rect = page.getPageSizeWithRotation();
                            Image sample = new Image(ImageDataFactory.create(getClass().getResource("/img/sample.png")));
                            pdfCanvas.addImage(ImageDataFactory.create(getClass().getResource("/img/sample.png")), rect.getWidth() / 2 - sample.getImageWidth() / 2, rect.getHeight() / 2 - sample.getImageHeight() / 2, false);
                            //Image sample = new Image(ImageDataFactory.create(SAMPLE_IMG));
                            //pdfCanvas.addImage(ImageDataFactory.create(SAMPLE_IMG), rect.getWidth() / 2 - sample.getImageWidth() / 2, rect.getHeight() / 2 - sample.getImageHeight() / 2, false);
                        }
                        else if(ds.text.equals("cancel")) {
                            Rectangle rect = page.getPageSizeWithRotation();
                            Image cancel = new Image(ImageDataFactory.create(getClass().getResource("/img/cancel.png")));
                            pdfCanvas.addImage(ImageDataFactory.create(getClass().getResource("/img/cancel.png")), rect.getWidth() / 2 - cancel.getImageWidth() / 2, rect.getHeight() / 2 - cancel.getImageHeight() / 2, false);
                            //Image cancel = new Image(ImageDataFactory.create(CANCEL_IMG));
                            //pdfCanvas.addImage(ImageDataFactory.create(CANCEL_IMG), rect.getWidth() / 2 - cancel.getImageWidth() / 2, rect.getHeight() / 2 - cancel.getImageHeight() / 2, false);
                        }
                    }
                    else
                        pdfCanvas.showText(ds.text);
                    pdfCanvas.endText();
                }
            }
        }

        writer.close();

        for (FileStruct fs : conf_array)
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

        String fileconf = args[0];

        new TechCardsPDF(fileconf);
    }

}
