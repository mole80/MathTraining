package free.mathtraining;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class result_activity extends AppCompatActivity {

    CalculBase cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_activity);

        cal = DataAppl.getInstance().Calcul;

        Button bt_cont = (Button)findViewById(R.id.bt_result_restart);
        Button bt_menu = (Button)findViewById(R.id.bt_result_menu);

        bt_cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView tv_res = (TextView)findViewById(R.id.tv_result_ok);
        tv_res.setText( Integer.toString( cal.NbrCalOk ) );

        TextView tv_time = (TextView)findViewById(R.id.tv_result_time);
        tv_res.setText("Temps : " + cal.GetTimeTotal() );

        TextView tv_mean = (TextView)findViewById(R.id.tv_result_time_mean);
        tv_mean.setText("Temps moy : " + cal.GetTimeMean() );
    }
}
