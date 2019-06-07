package akguen.liquidschool.paulirotlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class KlasseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klasse);

        Intent intent = new Intent();
        String vergehensTitle = intent.getStringExtra(Intent.EXTRA_TEXT);
        //new Datenbanksachen(vergehensTitle).execute("someParams");

    }
}
