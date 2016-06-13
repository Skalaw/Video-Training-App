package com.skala.videotrainingapp.rest;

import com.skala.core.api.RestVideoApi;
import com.skala.core.api.model.AuthenticationSessionId;
import com.skala.core.api.model.AuthenticationToken;
import com.skala.core.api.model.ConfigurationApi;
import com.skala.videotrainingapp.rest.helper.ApiKeyProperties;
import com.skala.videotrainingapp.rest.helper.InterceptorMock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Skala
 */
public class RestVideoApiTest {
    private final static int DURATION_LOCK_IN_MILLISECOND = 6000;
    private CountDownLatch lock;
    private boolean onResponseSuccess;
    private static String videoApiKey;

    @BeforeClass
    public static void setUpBeforeClass() {
        ApiKeyProperties apiKeyProperties = new ApiKeyProperties();
        videoApiKey = apiKeyProperties.getApiKey();
    }

    @Before
    public void setUp() {
        lock = new CountDownLatch(1);
        onResponseSuccess = false;
    }

    @Test
    public void testGetConfigurationFromMock() throws Exception {
        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(InterceptorMock.newInstance(InterceptorMock.TYPE_CONFIG))
                .build();

        RestVideoApi restVideoApi = new RestVideoApi(client);

        Call<ConfigurationApi> config = restVideoApi.getConfiguration(videoApiKey);
        config.enqueue(new Callback<ConfigurationApi>() {
            @Override
            public void onResponse(Call<ConfigurationApi> call, Response<ConfigurationApi> response) {
                ConfigurationApi body = response.body();

                onResponseSuccess = body != null;
                lock.countDown();
            }

            @Override
            public void onFailure(Call<ConfigurationApi> call, Throwable t) {
                System.out.print(t.toString());
                onResponseSuccess = false;
                lock.countDown();
            }
        });

        lock.await(DURATION_LOCK_IN_MILLISECOND, TimeUnit.MILLISECONDS);
        Assert.assertTrue(onResponseSuccess);
    }

    @Test
    public void testGetConfigurationFromServer() throws Exception {
        final OkHttpClient client = new OkHttpClient.Builder()
                .build();

        RestVideoApi restVideoApi = new RestVideoApi(client);

        Call<ConfigurationApi> config = restVideoApi.getConfiguration(videoApiKey);
        config.enqueue(new Callback<ConfigurationApi>() {
            @Override
            public void onResponse(Call<ConfigurationApi> call, Response<ConfigurationApi> response) {
                ConfigurationApi body = response.body();

                onResponseSuccess = body != null;
                lock.countDown();
            }

            @Override
            public void onFailure(Call<ConfigurationApi> call, Throwable t) {
                System.out.print(t.toString());
                onResponseSuccess = false;
                lock.countDown();
            }
        });

        lock.await(DURATION_LOCK_IN_MILLISECOND, TimeUnit.MILLISECONDS);
        Assert.assertTrue(onResponseSuccess);
    }

    @Test
    public void testGetRequestTokenFromMock() throws Exception {
        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(InterceptorMock.newInstance(InterceptorMock.TYPE_AUTHENTICATION_TOKEN))
                .build();

        RestVideoApi restVideoApi = new RestVideoApi(client);

        Call<AuthenticationToken> config = restVideoApi.getRequestToken(videoApiKey);
        config.enqueue(new Callback<AuthenticationToken>() {
            @Override
            public void onResponse(Call<AuthenticationToken> call, Response<AuthenticationToken> response) {
                AuthenticationToken body = response.body();
                if (body != null) {
                    onResponseSuccess = body.getSuccess();
                } else {
                    onResponseSuccess = false;
                }

                lock.countDown();
            }

            @Override
            public void onFailure(Call<AuthenticationToken> call, Throwable t) {
                System.out.print(t.toString());
                onResponseSuccess = false;
                lock.countDown();
            }
        });

        lock.await(DURATION_LOCK_IN_MILLISECOND, TimeUnit.MILLISECONDS);
        Assert.assertTrue(onResponseSuccess);
    }

    @Test
    public void testGetRequestTokenFromServer() throws Exception {
        final OkHttpClient client = new OkHttpClient.Builder()
                .build();

        RestVideoApi restVideoApi = new RestVideoApi(client);

        Call<AuthenticationToken> config = restVideoApi.getRequestToken(videoApiKey);
        config.enqueue(new Callback<AuthenticationToken>() {
            @Override
            public void onResponse(Call<AuthenticationToken> call, Response<AuthenticationToken> response) {
                AuthenticationToken body = response.body();
                if (body != null) {
                    onResponseSuccess = body.getSuccess();
                } else {
                    onResponseSuccess = false;
                }

                lock.countDown();
            }

            @Override
            public void onFailure(Call<AuthenticationToken> call, Throwable t) {
                System.out.print(t.toString());
                onResponseSuccess = false;
                lock.countDown();
            }
        });

        lock.await(DURATION_LOCK_IN_MILLISECOND, TimeUnit.MILLISECONDS);
        Assert.assertTrue(onResponseSuccess);
    }

    @Test
    public void testGetSessionIdFromMock() throws Exception {
        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(InterceptorMock.newInstance(InterceptorMock.TYPE_AUTHENTICATION_SESSION_ID))
                .build();

        RestVideoApi restVideoApi = new RestVideoApi(client);

        Call<AuthenticationSessionId> config = restVideoApi.getSessionId(videoApiKey, "mock_token");
        config.enqueue(new Callback<AuthenticationSessionId>() {
            @Override
            public void onResponse(Call<AuthenticationSessionId> call, Response<AuthenticationSessionId> response) {
                AuthenticationSessionId body = response.body();
                if (body != null) {
                    onResponseSuccess = body.getSuccess();
                } else {
                    onResponseSuccess = false;
                }

                lock.countDown();
            }

            @Override
            public void onFailure(Call<AuthenticationSessionId> call, Throwable t) {
                System.out.print(t.toString());
                onResponseSuccess = false;
                lock.countDown();
            }
        });

        lock.await(DURATION_LOCK_IN_MILLISECOND, TimeUnit.MILLISECONDS);
        Assert.assertTrue(onResponseSuccess);
    }

    @Test
    public void testGetSessionIdFromServer() throws Exception {
        final OkHttpClient client = new OkHttpClient.Builder()
                .build();

        RestVideoApi restVideoApi = new RestVideoApi(client);

        String apiToken = getApiToken(restVideoApi);// TODO: refactor

        // TODO: add getValidateApiToken

        Call<AuthenticationSessionId> config = restVideoApi.getSessionId(videoApiKey, apiToken);
        config.enqueue(new Callback<AuthenticationSessionId>() {
            @Override
            public void onResponse(Call<AuthenticationSessionId> call, Response<AuthenticationSessionId> response) {
                AuthenticationSessionId body = response.body();
                if (body != null) {
                    onResponseSuccess = body.getSuccess();
                } else {
                    onResponseSuccess = false;
                }

                lock.countDown();
            }

            @Override
            public void onFailure(Call<AuthenticationSessionId> call, Throwable t) {
                System.out.print(t.toString());
                onResponseSuccess = false;
                lock.countDown();
            }
        });

        lock.await(DURATION_LOCK_IN_MILLISECOND, TimeUnit.MILLISECONDS);
        Assert.assertTrue(onResponseSuccess);
    }

    private String getApiToken(RestVideoApi restVideoApi) throws InterruptedException {
        final String[] apiToken = new String[1];

        Call<AuthenticationToken> config = restVideoApi.getRequestToken(videoApiKey);
        config.enqueue(new Callback<AuthenticationToken>() {
            @Override
            public void onResponse(Call<AuthenticationToken> call, Response<AuthenticationToken> response) {
                AuthenticationToken body = response.body();
                if (body != null) {
                    onResponseSuccess = body.getSuccess();
                    apiToken[0] = body.getRequestToken();
                } else {
                    onResponseSuccess = false;
                }

                lock.countDown();
            }

            @Override
            public void onFailure(Call<AuthenticationToken> call, Throwable t) {
                System.out.print(t.toString());
                onResponseSuccess = false;
                lock.countDown();
            }
        });

        lock.await(DURATION_LOCK_IN_MILLISECOND, TimeUnit.MILLISECONDS);

        Assert.assertTrue(onResponseSuccess);
        onResponseSuccess = false;

        lock = new CountDownLatch(1);
        return apiToken[0];
    }
}
