package com.itextpdf.TechCardsPDF;

enum Format {
    A4V, A3H, A3V, A2H, A2V
}

enum Field {
    Developer,
    Checker,
    TechConroller,
    StandardsController,
    Approver,
    InvNumber,
    Change1,
    Change2,
    Change3,
    Change4,
    Signed
}

class Point {
    private float x;
    private float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}

public class Coordinates {
    private Point points[][];

    public Coordinates() {
        points = new Point[5][11];

        points[Format.A4V.ordinal()][Field.Developer.ordinal()] = new Point(214, 747);
        points[Format.A4V.ordinal()][Field.Checker.ordinal()] = new Point(214, 761);
        points[Format.A4V.ordinal()][Field.TechConroller.ordinal()] = new Point(214, 775);
        points[Format.A4V.ordinal()][Field.StandardsController.ordinal()] = new Point(214, 817);
        points[Format.A4V.ordinal()][Field.Approver.ordinal()] = new Point(214, 803);
        points[Format.A4V.ordinal()][Field.InvNumber.ordinal()] = new Point(51, 817);
        points[Format.A4V.ordinal()][Field.Change1.ordinal()] = new Point(117, 718);
        points[Format.A4V.ordinal()][Field.Change2.ordinal()] = new Point(85, 718);
        points[Format.A4V.ordinal()][Field.Change3.ordinal()] = new Point(63, 718);
        points[Format.A4V.ordinal()][Field.Change4.ordinal()] = new Point(214, 718);
        points[Format.A4V.ordinal()][Field.Signed.ordinal()] = new Point(51, 747);

        points[Format.A3H.ordinal()][Field.Developer.ordinal()] = new Point(809, 747);
        points[Format.A3H.ordinal()][Field.Checker.ordinal()] = new Point(809, 761);
        points[Format.A3H.ordinal()][Field.TechConroller.ordinal()] = new Point(809, 775);
        points[Format.A3H.ordinal()][Field.StandardsController.ordinal()] = new Point(809, 817);
        points[Format.A3H.ordinal()][Field.Approver.ordinal()] = new Point(809, 803);
        points[Format.A3H.ordinal()][Field.InvNumber.ordinal()] = new Point(51, 818);
        points[Format.A3H.ordinal()][Field.Change1.ordinal()] = new Point(712, 718);
        points[Format.A3H.ordinal()][Field.Change2.ordinal()] = new Point(680, 718);
        points[Format.A3H.ordinal()][Field.Change3.ordinal()] = new Point(658, 718);
        points[Format.A3H.ordinal()][Field.Change4.ordinal()] = new Point(809, 718);
        points[Format.A3H.ordinal()][Field.Signed.ordinal()] = new Point(51, 747);

        points[Format.A3V.ordinal()][Field.Developer.ordinal()] = new Point(460, 1097);
        points[Format.A3V.ordinal()][Field.Checker.ordinal()] = new Point(460, 1110);
        points[Format.A3V.ordinal()][Field.TechConroller.ordinal()] = new Point(460, 1124);
        points[Format.A3V.ordinal()][Field.StandardsController.ordinal()] = new Point(460, 1166);
        points[Format.A3V.ordinal()][Field.Approver.ordinal()] = new Point(460, 1153);
        points[Format.A3V.ordinal()][Field.InvNumber.ordinal()] = new Point(51, 1165);
        points[Format.A3V.ordinal()][Field.Change1.ordinal()] = new Point(363, 1067);
        points[Format.A3V.ordinal()][Field.Change2.ordinal()] = new Point(331, 1067);
        points[Format.A3V.ordinal()][Field.Change3.ordinal()] = new Point(309, 1067);
        points[Format.A3V.ordinal()][Field.Change4.ordinal()] = new Point(460, 1067);
        points[Format.A3V.ordinal()][Field.Signed.ordinal()] = new Point(51, 1097);

        points[Format.A2H.ordinal()][Field.Developer.ordinal()] = new Point(1302, 1096);
        points[Format.A2H.ordinal()][Field.Checker.ordinal()] = new Point(1302, 1110);
        points[Format.A2H.ordinal()][Field.TechConroller.ordinal()] = new Point(1302, 1124);
        points[Format.A2H.ordinal()][Field.StandardsController.ordinal()] = new Point(1302, 1166);
        points[Format.A2H.ordinal()][Field.Approver.ordinal()] = new Point(1302, 1152);
        points[Format.A2H.ordinal()][Field.InvNumber.ordinal()] = new Point(51, 1166);
        points[Format.A2H.ordinal()][Field.Change1.ordinal()] = new Point(1205, 1067);
        points[Format.A2H.ordinal()][Field.Change2.ordinal()] = new Point(1173, 1067);
        points[Format.A2H.ordinal()][Field.Change3.ordinal()] = new Point(1151, 1067);
        points[Format.A2H.ordinal()][Field.Change4.ordinal()] = new Point(1302, 1067);
        points[Format.A2H.ordinal()][Field.Signed.ordinal()] = new Point(51, 1096);

        points[Format.A2V.ordinal()][Field.Developer.ordinal()] = new Point(809, 1589);
        points[Format.A2V.ordinal()][Field.Checker.ordinal()] = new Point(809, 1603);
        points[Format.A2V.ordinal()][Field.TechConroller.ordinal()] = new Point(809, 1617);
        points[Format.A2V.ordinal()][Field.StandardsController.ordinal()] = new Point(809, 1659);
        points[Format.A2V.ordinal()][Field.Approver.ordinal()] = new Point(809, 1645);
        points[Format.A2V.ordinal()][Field.InvNumber.ordinal()] = new Point(51, 1659);
        points[Format.A2V.ordinal()][Field.Change1.ordinal()] = new Point(712, 1560);
        points[Format.A2V.ordinal()][Field.Change2.ordinal()] = new Point(680, 1560);
        points[Format.A2V.ordinal()][Field.Change3.ordinal()] = new Point(658, 1560);
        points[Format.A2V.ordinal()][Field.Change4.ordinal()] = new Point(809, 1560);
        points[Format.A2V.ordinal()][Field.Signed.ordinal()] = new Point(51, 1589);
    }

    public static Format getFormat(float w, float h) {
        if(w >= 590 && w <= 610 & h >= 835 && h <= 855) {
            // 595 x 842
            return Format.A4V;
        }
        else if(w >= 1170 && w <= 1210 & h >= 835 && h <= 855) {
            // 1191 x 842
            return Format.A3H;
        }
        else if(w >= 835 && w <= 855 & h >= 1170 && h <= 1210) {
            // 842 x 1191
            return Format.A3V;
        }
        else if(w >= 1660 && w <= 1700 & h >= 1170 && h <= 1210) {
            // 1684 x 1191
            return Format.A2H;
        }
        else if(w >= 1170 && w <= 1210 & h >= 1660 && h <= 1700) {
            // 1191 x 1684
            return Format.A2V;
        }

        return null;
    }

    public Point getPoints(Format format, Field field) {
        if(format == null || field == null)
            return null;
        return points[format.ordinal()][field.ordinal()];
    }

    public Point getPoints(Format format, int field) {
        if(format == null)
            return null;
        try {
            return points[format.ordinal()][field];
        } catch (Exception e) {
            return null;
        }
    }
}
