package free.mathtraining;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CalculBase {
    public enum eOperation {Plus, Minus, Divid, Multiply};
    public enum eType {FindFirst, FindSecond, FindRes};

    public ArrayList<String> ErrorList;
    public List<eType> ListType;

    public int NbrCalc;
    public int NbrCalOk;
    public int NbrCalKo;
    public int CptCalc;

    public long StartTime;
    public long StopTime;
    public long MaxTime;
    public long MinTime;
    public long MeanTime;
    long InterTime;

    public String Res;
    public String Nbr1;
    public String Nbr2;
    public eOperation Operation;
    public eType Type;

    Random rand = new Random();

    public static eOperation OpFromString(String s)
    {
        switch (s){
            case "+":
                return eOperation.Plus;
            case "-":
                return eOperation.Minus;
            case "*":
            case "x":
            case "X":
                return eOperation.Multiply;
            case "/":
                return eOperation.Divid;

            default:
                return eOperation.Plus;
        }
    }

    public CalculBase(List<eType> listType)
    {
        ListType = listType;
        ErrorList = new ArrayList<String>();
        NbrCalc = 0;
        NbrCalOk = 0;
        CptCalc = 0;
        Res = "";
        Nbr1 = "";
        Nbr2 = "";
        Operation = eOperation.Plus;
        Type = eType.FindFirst;
        NbrCalKo = 0;
        MaxTime = 0;
        MinTime = Integer.MAX_VALUE;
    }

    public double Calcul(double n1, double n2, eOperation op)
    {
        switch (op)
        {
            case Plus:
                return n1 + n2;

            case Minus:
                return n1 - n2;

            case Multiply:
                return n1 * n2;

            case Divid:
                return n1 / n2;
        }

        return 0;
    }

    public String GetTimeTotal()
    {
        long t =(StopTime - StartTime);

        long second = (t / 1000) % 60;
        long minute = (t/ (1000 * 60)) % 60;

        String time = Long.toString(minute) + " min  :  " + Long.toString(second) + " sec";

        return time;
    }

    public String GetTimeMean()
    {
        return Long.toString((MeanTime)/1000);
    }

    public boolean IsFinished()
    {
        if( NbrCalc <= CptCalc )
            return true;
        else
            return false;
    }

    public String GetOperand()
    {
        switch (Operation){
            case Plus:
                return "+";
            case Minus:
                return "-";
            case Multiply:
                return "X";
            case Divid:
                return "/";
            default:
                return "";
        }
    }

    public String GetCalcul(String val)
    {
        if( val.equals("") )
            val = "---";

        String res = "";

        switch (Type) {
            case FindFirst:
                res = "---   " + GetOperand() + "  " + Nbr2 + "  =  "  + Res;
                break;

            case FindSecond:
                res = Nbr1 + "  " + GetOperand() + "   ---" + "  =  "  + Res;
                break;

            case FindRes:
                res = Nbr1 + "  " + GetOperand() + "  " + Nbr2 + "  =   ---";
                break;

            default:
        }

        return res;
    }

    public void SetNextType()
    {
        int ind = rand.nextInt( ListType.size() );
        Type = ListType.get(ind);
    }

    public void StartNewGame()
    {
        ErrorList.clear();
        MaxTime = 0;
        MinTime = Integer.MAX_VALUE;
        CptCalc = 0;
        NbrCalOk = 0;
        NbrCalKo = 0;
        StartTime = System.currentTimeMillis();
    }

    public void EndGame()
    {
        StopTime = System.currentTimeMillis();
        MeanTime = (StopTime - StartTime) / NbrCalc;
    }

    public void NextCalcul(){
       // CptCalc++;
        InterTime = System.currentTimeMillis();
    }

    public boolean Check(String val)
    {
        String resComp = "";
        switch (Type) {
            case FindFirst:
                resComp = Nbr1;
                break;

            case FindSecond:
                resComp = Nbr2;
                break;

            case FindRes:
                resComp = Res;
                break;

            default:
        }

        boolean res = resComp.equals(val);
        if( res ) {
            NbrCalOk++;
            long t = System.currentTimeMillis() - InterTime;
            MaxTime = t > MaxTime ? t : MaxTime;
            MinTime = t < MinTime ? t : MinTime;
        }
        else {
            ErrorList.add( GetCalcul("") + "  :  " + val );
            NbrCalKo++;
        }

        CptCalc++;
        return res;
    }

    public boolean CheckCalcul(String val){ return false; }
}


class CalculArithm extends CalculBase
{
    public List<eOperation> ListOperation;
    public int MaxNumber;
    public int MinNumber;
    public int MultipleNumber;
    public int ResInt;

    public CalculArithm(int min, int max, int multipleNumber, List<eOperation> op, List<eType> listType)
    {
        super(listType);
        MultipleNumber = multipleNumber;
        MinNumber = min / multipleNumber;
        MaxNumber = max / multipleNumber;
        ListOperation = op;
    }

    public void NextCalcul() {
        super.NextCalcul();

        int opInd = rand.nextInt( ListOperation.size() );
        Operation = ListOperation.get(opInd);

        int n1 = 0;
        int max = 0;
        int n2 = 0;

        int tmpMax = MaxNumber / MultipleNumber;
        int tmpMin = MinNumber / MultipleNumber;

        n1 = rand.nextInt(tmpMax - tmpMin) + tmpMin;

        switch (Operation) {
            case Plus:
            max = tmpMax - Math.abs(n1);
            n2 = rand.nextInt(max - tmpMin) + tmpMin;
                break;

            case Minus:
                n2 = rand.nextInt(n1);
                break;

            case Multiply:
                n1 = rand.nextInt(tmpMax - tmpMin) + tmpMin;
                break;

            case Divid:
               n2 = n1;
        }

        n1 = n1 * MultipleNumber;
        n2 = n2 * MultipleNumber;

        ResInt = (int)( Calcul(n1,n2,Operation) );
        Nbr1 = Integer.toString(n1);
        Nbr2 = Integer.toString(n2);
        Res = Integer.toString(ResInt);

        SetNextType();
        Type = eType.FindRes;
    }
}


class Livret extends CalculBase{
    public List<String> LivretList;
    public int ResInt;

    public Livret(List<String> livret, List<eType> listType)
    {
        super(listType);
        LivretList = livret;
        Operation = eOperation.Multiply;
    }

    public void TestAllValues()
    {
        int nbr = LivretList.size() * 12;
    }

    public void NextCalcul()
    {
        super.NextCalcul();

        int ind = rand.nextInt( LivretList.size() );
        Nbr1 = LivretList.get(ind);
        int n1 = Integer.parseInt( Nbr1 );
        int n2 = rand.nextInt(12) + 1;
        ResInt = n1 * n2;

        Nbr2 = Integer.toString(n2);
        Res = Integer.toString(ResInt);

        SetNextType();
    }
}
