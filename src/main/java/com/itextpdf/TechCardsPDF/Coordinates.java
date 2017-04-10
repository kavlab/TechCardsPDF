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

class Coords {
    public float x;
    public float y;

    public Coords(float x, float y) {
        this.x = x;
        this.y = y;
    }
}

public class Coordinates {
    private Coords coords[][];

    public Coordinates() {
        coords = new Coords[5][11];

        coords[Format.A4V.ordinal()][Field.Developer.ordinal()] = new Coords(214, 747);
        coords[Format.A4V.ordinal()][Field.Checker.ordinal()] = new Coords(214, 761);
        coords[Format.A4V.ordinal()][Field.TechConroller.ordinal()] = new Coords(214, 775);
        coords[Format.A4V.ordinal()][Field.StandardsController.ordinal()] = new Coords(214, 817);
        coords[Format.A4V.ordinal()][Field.Approver.ordinal()] = new Coords(214, 803);
        coords[Format.A4V.ordinal()][Field.InvNumber.ordinal()] = new Coords(51, 817);
        coords[Format.A4V.ordinal()][Field.Change1.ordinal()] = new Coords(117, 718);
        coords[Format.A4V.ordinal()][Field.Change2.ordinal()] = new Coords(85, 718);
        coords[Format.A4V.ordinal()][Field.Change3.ordinal()] = new Coords(63, 718);
        coords[Format.A4V.ordinal()][Field.Change4.ordinal()] = new Coords(214, 718);
        coords[Format.A4V.ordinal()][Field.Signed.ordinal()] = new Coords(51, 747);

        coords[Format.A3H.ordinal()][Field.Developer.ordinal()] = new Coords(809, 747);
        coords[Format.A3H.ordinal()][Field.Checker.ordinal()] = new Coords(809, 761);
        coords[Format.A3H.ordinal()][Field.TechConroller.ordinal()] = new Coords(809, 775);
        coords[Format.A3H.ordinal()][Field.StandardsController.ordinal()] = new Coords(809, 817);
        coords[Format.A3H.ordinal()][Field.Approver.ordinal()] = new Coords(809, 803);
        coords[Format.A3H.ordinal()][Field.InvNumber.ordinal()] = new Coords(51, 818);
        coords[Format.A3H.ordinal()][Field.Change1.ordinal()] = new Coords(712, 718);
        coords[Format.A3H.ordinal()][Field.Change2.ordinal()] = new Coords(680, 718);
        coords[Format.A3H.ordinal()][Field.Change3.ordinal()] = new Coords(658, 718);
        coords[Format.A3H.ordinal()][Field.Change4.ordinal()] = new Coords(809, 718);
        coords[Format.A3H.ordinal()][Field.Signed.ordinal()] = new Coords(51, 747);

        coords[Format.A3V.ordinal()][Field.Developer.ordinal()] = new Coords(460, 1097);
        coords[Format.A3V.ordinal()][Field.Checker.ordinal()] = new Coords(460, 1110);
        coords[Format.A3V.ordinal()][Field.TechConroller.ordinal()] = new Coords(460, 1124);
        coords[Format.A3V.ordinal()][Field.StandardsController.ordinal()] = new Coords(460, 1166);
        coords[Format.A3V.ordinal()][Field.Approver.ordinal()] = new Coords(460, 1153);
        coords[Format.A3V.ordinal()][Field.InvNumber.ordinal()] = new Coords(51, 1165);
        coords[Format.A3V.ordinal()][Field.Change1.ordinal()] = new Coords(363, 1067);
        coords[Format.A3V.ordinal()][Field.Change2.ordinal()] = new Coords(331, 1067);
        coords[Format.A3V.ordinal()][Field.Change3.ordinal()] = new Coords(309, 1067);
        coords[Format.A3V.ordinal()][Field.Change4.ordinal()] = new Coords(460, 1067);
        coords[Format.A3V.ordinal()][Field.Signed.ordinal()] = new Coords(51, 1097);

        coords[Format.A2H.ordinal()][Field.Developer.ordinal()] = new Coords(1302, 1096);
        coords[Format.A2H.ordinal()][Field.Checker.ordinal()] = new Coords(1302, 1110);
        coords[Format.A2H.ordinal()][Field.TechConroller.ordinal()] = new Coords(1302, 1124);
        coords[Format.A2H.ordinal()][Field.StandardsController.ordinal()] = new Coords(1302, 1166);
        coords[Format.A2H.ordinal()][Field.Approver.ordinal()] = new Coords(1302, 1152);
        coords[Format.A2H.ordinal()][Field.InvNumber.ordinal()] = new Coords(51, 1166);
        coords[Format.A2H.ordinal()][Field.Change1.ordinal()] = new Coords(1205, 1067);
        coords[Format.A2H.ordinal()][Field.Change2.ordinal()] = new Coords(1173, 1067);
        coords[Format.A2H.ordinal()][Field.Change3.ordinal()] = new Coords(1151, 1067);
        coords[Format.A2H.ordinal()][Field.Change4.ordinal()] = new Coords(1302, 1067);
        coords[Format.A2H.ordinal()][Field.Signed.ordinal()] = new Coords(51, 1096);

        coords[Format.A2V.ordinal()][Field.Developer.ordinal()] = new Coords(809, 1589);
        coords[Format.A2V.ordinal()][Field.Checker.ordinal()] = new Coords(809, 1603);
        coords[Format.A2V.ordinal()][Field.TechConroller.ordinal()] = new Coords(809, 1617);
        coords[Format.A2V.ordinal()][Field.StandardsController.ordinal()] = new Coords(809, 1659);
        coords[Format.A2V.ordinal()][Field.Approver.ordinal()] = new Coords(809, 1645);
        coords[Format.A2V.ordinal()][Field.InvNumber.ordinal()] = new Coords(51, 1659);
        coords[Format.A2V.ordinal()][Field.Change1.ordinal()] = new Coords(712, 1560);
        coords[Format.A2V.ordinal()][Field.Change2.ordinal()] = new Coords(680, 1560);
        coords[Format.A2V.ordinal()][Field.Change3.ordinal()] = new Coords(658, 1560);
        coords[Format.A2V.ordinal()][Field.Change4.ordinal()] = new Coords(809, 1560);
        coords[Format.A2V.ordinal()][Field.Signed.ordinal()] = new Coords(51, 1589);
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

    public Coords getCoords(Format format, Field field) {
        if(format == null || field == null)
            return null;
        return coords[format.ordinal()][field.ordinal()];
    }

    public Coords getCoords(Format format, int field) {
        if(format == null)
            return null;
        try {
            return coords[format.ordinal()][field];
        } catch (Exception e) {
            return null;
        }
    }
}
