package prova46;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.TrustManager;

public class CustomTrustManager implements X509TrustManager {

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        // Qui puoi aggiungere la logica per verificare la catena del client, se necessario.
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        if (chain == null || chain.length == 0) {
            throw new IllegalArgumentException("La catena di certificati è vuota");
        }
        if (authType == null || authType.length() == 0) {
            throw new IllegalArgumentException("Il tipo di autenticazione è vuoto");
        }

        // Tentativo di verificare la catena del certificato
        try {
            chain[0].checkValidity();
        } catch (CertificateException e) {
            throw new CertificateException("Il certificato non è valido.", e);
        }
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[]{};  // Accetta una gamma più ampia di certificati
    }

    public static void applyCustomTrustManager() {
        try {
            // Crea un contesto SSL che usa il tuo TrustManager personalizzato
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new CustomTrustManager()}, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
