package free.mathtraining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CalculActivity extends AppCompatActivity implements View.OnClickListener{

    final String NO_RESULT = "-";

    public static final String TAG_IS_WRITE_ANSWER = "is_write_answer";

    int nbrCalc;
    boolean writeAnswer;

    CalculBase cal;

    void SetCalcul(String text)
    {
        TextView tv = (TextView)findViewById(R.id.tv_calcul);
        tv.setText(cal.GetCalcul(""));
    }

    void SetNumberCalc()
    {
        TextView tv = (TextView)findViewById(R.id.tv_number_calc);
        String s = Integer.toString( cal.CptCalc ) + " / " + Integer.toString( cal.NbrCalc );
        tv.setText(s);
    }

    void SetTextResult(String text)
    {
        TextView tv = (TextView)findViewById(R.id.tv_result);

        if( text.equals(NO_RESULT) )
            tv.setText(NO_RESULT);
        else if( tv.getText().toString().equals(NO_RESULT) )
            tv.setText( text );
        else
            tv.append( text );
    }

    void NewCalcul()
    {
        TextView tv = (TextView)findViewById(R.id.tv_result);
        SetTextResult(NO_RESULT);

        cal.NextCalcul();
        SetCalcul( cal.GetCalcul("") );
        SetNumberCalc();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

         cal.StartNewGame();
         NewCalcul();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);

        writeAnswer = getIntent().getBooleanExtra(TAG_IS_WRITE_ANSWER, false);

        cal = DataAppl.getInstance().Calcul;
        cal.StartNewGame();
        NewCalcul();

        TableLayout tl = (TableLayout)findViewById(R.id.tl_clavier);
        for(int c = 0; c < tl.getChildCount(); c++) {
            TableRow tr = (TableRow) tl.getChildAt(c);
            for( int k=0; k < tr.getChildCount(); k ++)
            {
                Button b = (Button)tr.getChildAt(k);
                b.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View view) {

        try {
            Button b = (Button) view;

            if (b.getText().toString().toLowerCase().equals("v")) {
                TextView tv = (TextView)findViewById(R.id.tv_result);
                boolean res = cal.Check(tv.getText().toString());

                if( res ) {
                    if (cal.IsFinished()) {
                        cal.EndGame();

                        Intent i = new Intent(this, result_activity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);

                    } else
                        NewCalcul();
                }
                SetTextResult(NO_RESULT);

            } else if (b.getText().toString().toLowerCase().equals("c")) {
                SetTextResult(NO_RESULT);
            } else {
                SetTextResult( b.getText().toString() );
            }
        }
        catch (Exception ex)
        {
            Log.e("Exc : ", ex.getMessage());
        }
    }
}
