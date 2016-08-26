package com.morgan.architecturepractice.api;


import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Authenticator;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpClient {

    private static HttpClient httpClient = new HttpClient();
    public BingApiService bingApiService;

    public static HttpClient getInstance() {
        return httpClient;
    }


    private HttpClient() {
        init();
    }

    private void init() {
        Retrofit.Builder retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create());
        bingApiService = createApiService(retrofit, BingApiService.class, ApiConstants.BING);
    }


    private <T> T createApiService(Retrofit.Builder retrofit, Class<T> clz, String api) {
        OkHttpClient okHttpClient;
        okHttpClient = getOkClient(true);
        retrofit.client(okHttpClient);
        retrofit.baseUrl(ApiConstants.PROTOCOL_HTTP + ApiConstants.BASE_URL + api);

        T t = retrofit.build().create(clz);
        return t;
    }


    public OkHttpClient.Builder getUnsafeOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    X509Certificate[] x509Certificates = new X509Certificate[0];
                    return x509Certificates;
                }
            }};

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {

                @Override
                public boolean verify(String hostname, SSLSession session) {
//                    return hostname.equals(verify_hostname);
                    return true;
                }
            });
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            builder.connectTimeout(30, TimeUnit.SECONDS);
            builder.readTimeout(30, TimeUnit.SECONDS);
            builder.connectionPool(new ConnectionPool(50, 30, TimeUnit.SECONDS));
            builder.retryOnConnectionFailure(true);
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        return builder;
    }

    public OkHttpClient getOkClient(boolean hasInterceptor) {
        OkHttpClient.Builder builder = getUnsafeOkHttpClient();
        if (hasInterceptor) {
            builder.addInterceptor(new okhttp3.Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Request newRequest = request.newBuilder().build();
                    Response response = chain.proceed(newRequest);
                    return response;
                }
            });
        }
        builder.authenticator(new TokenAuthenticator());
        return builder.build();
    }

    public void clear() {
        httpClient = null;
        bingApiService = null;
    }

    public class TokenAuthenticator implements Authenticator {

        @Override
        public Request authenticate(Route route, Response response) throws IOException {
            return response.request().newBuilder().build();
        }
    }

}
