package free.mathtraining;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class LivretFullActivity extends AppCompatActivity {

    final int EndLivret = 12;
    final int NbrCol = 3;

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

    boolean IsWriteAnswer()
    {
        try
        {
            RadioButton rb = (RadioButton)findViewById(R.id.rb_res_write);
            if( rb.isChecked() )
                return true;
            else
                return false;
        }
        catch (Exception ex)
        {
            Log.e("Exc : ", ex.getMessage());
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.livret_choice_layout);

        Button b_cont = (Button) findViewById(R.id.bt_livret_continu);
        b_cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Selection of livret
                    List<String> livret = new ArrayList<String>();

                    TableLayout tl = (TableLayout) findViewById(R.id.tl_livret_choice);

                    int nbrCol = tl.getChildCount();
                    for (int k = 0; k < nbrCol; k++) {
                        TableRow tr = (TableRow) tl.getChildAt(k);
                        int nbrCel = tr.getChildCount();
                        for( int n=0; n<nbrCel; n ++) {
                            CheckBox rb = (CheckBox) tr.getChildAt(n);

                            if( rb.isChecked() )
                            {
                                livret.add( rb.getText().toString() );
                            }
                        }
                    }

                    ArrayList<CalculBase.eType> type = new ArrayList<>();
                    type.add(CalculBase.eType.FindRes);
                    Livret l = new Livret(livret,type);
                    l.NbrCalc = GetNumberOfCalcul();
                    DataAppl.getInstance().Calcul = l;

                    Intent i = new Intent(getApplicationContext(), CalculActivity.class);
                    i.putExtra("write_answer", IsWriteAnswer() );
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

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
    }
}
