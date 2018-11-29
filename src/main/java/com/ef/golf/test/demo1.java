package com.ef.golf.test;

import org.apache.http.client.methods.HttpGet;
import org.junit.Test;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * com.ef.golf.test
 * Administrator
 * 2018/6/11 9:38
 */
public class demo1 implements X509TrustManager {
    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

    }

    @Override
    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
    @Test
    public static String httpsRequest(String requestUrl,String requestMethod){
        HttpGet httpGet = new HttpGet();

        return null;
    }

}
