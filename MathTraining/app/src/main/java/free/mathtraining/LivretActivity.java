package free.mathtraining;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableRow;

public class LivretActivity extends AppCompatActivity {

    final int EndLivret = 12;
    final int NbrCol = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.livret_choice_layout);

        RadioButton rb_res_choice = (RadioButton)findViewById(R.id.rb_res_choice);
        RadioButton rb_res_write = (RadioButton)findViewById(R.id.rb_res_write);

        InitializeLivretChoice();
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
    }

    void InitializeLivretChoice()
    {
        try {
            TableLayout tl = (TableLayout) findViewById(R.id.tl_livret_choice);

            int nbrCol = tl.getChildCount();
            for (int k = 0; k < nbrCol; k++) {
                TableRow tr = (TableRow) tl.getChildAt(k);
                int nbrCel = tr.getChildCount();
                for( int n=0; n<nbrCel; n ++) {
                    View v = tr.getChildAt(n);
                }
            }
        }
        catch (Exception ex)
        {
            Log.e("Exe : ", ex.getMessage());
        }

    }

}
