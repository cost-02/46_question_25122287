package main.java.prova46;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Applica il TrustManager personalizzato
        CustomTrustManager.applyCustomTrustManager();
        
        // Ora puoi procedere con qualsiasi connessione HTTPS sicura
    }
}
