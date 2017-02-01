package free.mathtraining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;

public class config_calcul_activity extends AppCompatActivity {

    CalculArithm cal;

    int GetNumberOfCalcul()
    {
        try {
            int nbrCalc;
            RadioGroup rg = (RadioGroup)findViewById(R.id.rg_nbr_calcul);
            int rbId = rg.getCheckedRadioButtonId();
            View rb = rg.findViewById(rbId);
            nbrCalc = Integer.parseInt( ((RadioButton)rb).getText().toString() );
            return  nbrCalc;
        }
        catch (Exception ex)
        {
            Log.e("Exc : ", ex.getMessage());
            return 0;
        }
    }

    ArrayList<CalculBase.eOperation> GetOperandChoice()
    {
        try {
            ArrayList<CalculBase.eOperation> op = new ArrayList<>();
            int nbr;
            RadioGroup rg = (RadioGroup)findViewById(R.id.rg_calc_operation);
            int sel = rg.getChildCount();

            for( int k=0; k<sel; k++){
                CheckBox cb = (CheckBox)rg.getChildAt(k);
                if(cb.isChecked())
                    op.add( CalculBase.OpFromString( cb.getText().toString() ) );
            }
            return op;
        }
        catch (Exception ex)
        {
            Log.e("Exc : ", ex.getMessage());
            return null;
        }
    }

    int GetNumberMax()
    {
        try {
            int nbr;
            RadioGroup rg = (RadioGroup)findViewById(R.id.rg_calc_value_max_number);
            int rbId = rg.getCheckedRadioButtonId();
            View rb = rg.findViewById(rbId);
            nbr = Integer.parseInt( ((RadioButton)rb).getText().toString() );
            return  nbr;
        }
        catch (Exception ex)
        {
            Log.e("Exc : ", ex.getMessage());
            return 10;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_calcul_layout);

        cal = (CalculArithm)DataAppl.getInstance().Calcul;

        Button b_cont = (Button) findViewById(R.id.bt_calc_continu);
        b_cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int max = GetNumberMax();
                    int nbr = GetNumberOfCalcul();
                    CalculArithm cal = new CalculArithm(1,max,GetOperandChoice());
                    cal.NbrCalc = nbr;

                    DataAppl.getInstance().Calcul = cal;

                    Intent i = new Intent(getApplicationContext(), CalculActivity.class);
                    i.putExtra("write_answer", true );
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                }
                catch (Exception ex)
                {
                    Log.e("Exe : ", ex.getMessage());
                }
            }
        });

    }
}
