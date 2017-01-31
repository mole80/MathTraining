package free.mathtraining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_livret = (Button)findViewById(R.id.bt_menu_livret);
        Button bt_calc = (Button)findViewById(R.id.bt_menu_calcArith) ;

        bt_livret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LivretActivity.class );
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        bt_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<CalculBase.eOperation> op = new ArrayList<CalculBase.eOperation>();
                op.add(CalculBase.eOperation.Plus);

                    CalculArithm c = new CalculArithm(1,10,op);

                    c.NbrCalc = 10;
                    DataAppl.getInstance().Calcul = c;

                    Intent i = new Intent(getApplicationContext(), CalculActivity.class);
                    i.putExtra("write_answer", true );
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
            }
        });
    }
}
