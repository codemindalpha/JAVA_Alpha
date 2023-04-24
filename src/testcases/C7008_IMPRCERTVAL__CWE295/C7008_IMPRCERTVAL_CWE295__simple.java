package testcases.C7008_IMPRCERTVAL__CWE295;

import javax.net.ssl.*;
import javax.ws.rs.client.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class C7008_IMPRCERTVAL_CWE295__simple {
    public void bad() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[]{new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {}
            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {}

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }}, new java.security.SecureRandom());

        Client client = ClientBuilder.newBuilder().sslContext(sslContext)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String requestedHost, SSLSession remoteServerSession) {
                        /* FLAW: CWE-295 */
                        return true;    // hostname을 검증하지 않고 항상 true로 리턴하므로 취약
                    }
                }).build();
    }

    public void good() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
        sslContext.init(null, new TrustManager[]{new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {}
            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {}

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }}, new java.security.SecureRandom());

        Client client = ClientBuilder.newBuilder().sslContext(sslContext)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String requestedHost, SSLSession remoteServerSession) {
                        return requestedHost.equalsIgnoreCase(remoteServerSession.getPeerHost());
                    }
                }).build();
    }

}
