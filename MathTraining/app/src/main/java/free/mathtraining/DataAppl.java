package free.mathtraining;

/**
 * Created by tony.maulaz on 31.01.2017.
 */
public class DataAppl {
    private static DataAppl ourInstance = new DataAppl();

    public static DataAppl getInstance() {
        return ourInstance;
    }

    public CalculBase Calcul;

    private DataAppl() {

    }
}
