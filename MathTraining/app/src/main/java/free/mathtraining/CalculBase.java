package free.mathtraining;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CalculBase {
    public enum eOperation {Plus, Minus, Divid, Multiply};
    public enum eType {FindFirst, FindSecond, FindRes};

    public int NbrCalc;
    public int NbrCalOk;
    public int CptCalc;

    public long StartTime;
    public long StopTime;
    public long MaxTime;
    public long MinTime;
    public long MeanTime;

    public String Res;
    public String Nbr1;
    public String Nbr2;
    public eOperation Operation;
    public eType Type;

    Random rand = new Random();

    public CalculBase()
    {
        NbrCalc = 0;
        NbrCalOk = 0;
        CptCalc = 0;
        Res = "";
        Nbr1 = "";
        Nbr2 = "";
        Operation = eOperation.Plus;
        Type = eType.FindFirst;
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
        return Long.toString((StopTime - StartTime)/1000);
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
        int tmp = rand.nextInt(3);
        Type = eType.values()[tmp];
    }

    public void StartNewGame()
    {
        CptCalc = 0;
        NbrCalOk = 0;
        StartTime = System.currentTimeMillis();
    }

    public void EndGame()
    {
        StopTime = System.currentTimeMillis();
        MeanTime = (StopTime - StartTime) / NbrCalc;
    }

    public void NextCalcul(){
        CptCalc++;
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
        if( res )
            NbrCalOk++;

        return res;
    }

    public boolean CheckCalcul(String val){ return false; }
}


class CalculArithm extends CalculBase
{
    public List<eOperation> ListOperation;
    public int MaxNumber;
    public int MinNumber;
    public int ResInt;

    public CalculArithm(int min, int max, List<eOperation> op)
    {
        MinNumber = min;
        MaxNumber = max;
        ListOperation = op;
    }

    public void NextCalcul() {
        super.NextCalcul();

        int opInd = rand.nextInt( ListOperation.size() );
        Operation = ListOperation.get(opInd);

        int n1 = rand.nextInt( MaxNumber - MinNumber ) + MinNumber;
        int n2 = rand.nextInt( MaxNumber - MinNumber ) + MinNumber;
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

    public Livret(List<String> livret)
    {
        LivretList = livret;
        Operation = eOperation.Multiply;
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

        int tmp = rand.nextInt(2) + 1;
        Type = eType.values()[tmp];
    }
}
