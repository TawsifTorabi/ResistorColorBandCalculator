import java.text.DecimalFormat;

public class MainFunctions {
    public String[] BandCalculator(int BandCount, long d1, long d2, long d3, double Mul, String Tol, String tc){
        String resStr;
        double res, resRaw;
        String MaxMin = "";
        String []DataArr = new String[3];

        //If Band 3 is passed Blank
        if (d3 != -1) {
            res = resRaw = ((100 * d1) + (10 * d2) + d3) * Mul;
        }else {
            res = resRaw = ((10 * d1) + d2) * Mul;
        }

        if (res >= 1e6) {
            res /= 1e6;
            resStr = res + "M Ω";
        } else if (res >= 1e3) {
            res /= 1e3;
            resStr = Double.toString(res) + "k Ω";
        } else {
            resStr = Double.toString(res) + " Ω";
        }
        resStr += " " + Tol;

        if(tc != ""){
            resStr += " " + tc;
        }

        DataArr[0] = resStr;

        if(Tol != ""){
            double toleranceStr = Double.parseDouble(Tol.split("%")[0]);
            double def = (resRaw * toleranceStr)/100;
            double maxResistance = resRaw + def;
            double minResistance = resRaw - def;
            DataArr[1] = ResistorMinifier(maxResistance);
            DataArr[2] = ResistorMinifier(minResistance);
            System.out.println("MaxRes - " + ResistorMinifier(maxResistance));
            System.out.println("MinRes - " + ResistorMinifier(minResistance));
        }

        System.out.println("+++++++++++++++++++++++++++++++++");
        System.out.println("Color Band 1: " + d1);
        System.out.println("Color Band 2: " + d2);
        System.out.println("Multiplier: " + Mul);
        System.out.println("Tolerance: " + Tol);
        System.out.println("Result: " + resStr);
        System.out.println("+++++++++++++++++++++++++++++++++");
        return DataArr;
    }

    public String ResistorMinifier(double num){
        String resStr1 = null;
        if (num >= 1e6) {
            num /= 1e6;
            resStr1 = num + "M Ω";
        } else if (num >= 1e3) {
            num /= 1e3;
            resStr1 = String.valueOf(new DecimalFormat("##.##").format(num)) + "k Ω";
        } else {
            resStr1 = String.valueOf(new DecimalFormat("##.##").format(num)) + " Ω";
        }
        return resStr1;
    }
}
