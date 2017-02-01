package free.mathtraining;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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
                setResult(CalculActivity.RESULT_RESTART);
                finish();
            }
        });

        bt_menu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                setResult(CalculActivity.RESULT_GO_MENU);
                finish();
            }
        });

        TextView tv_res = (TextView)findViewById(R.id.tv_result_ok);
        tv_res.setText( "Nombre de fautes : " + Integer.toString( cal.NbrCalKo ) + " / " + Integer.toString(cal.NbrCalc) + " calc");

        TextView tv_time = (TextView)findViewById(R.id.tv_result_time);
        tv_time.setText("Temps total : " + cal.GetTimeTotal() );

        TextView tv_mean = (TextView)findViewById(R.id.tv_result_time_mean);
        tv_mean.setText("Temps moy : " + cal.GetTimeMean() + " sec / calc" );

        ListView lv = (ListView)findViewById(R.id.lv_listError);
        ArrayAdapter<String> ad = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                cal.ErrorList);
        lv.setAdapter(ad);
    }
}
