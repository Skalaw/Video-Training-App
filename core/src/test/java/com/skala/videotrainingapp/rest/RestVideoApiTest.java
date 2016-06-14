package com.skala.videotrainingapp.rest;

import com.skala.core.api.RestVideoApi;
import com.skala.core.api.model.AuthenticationSessionId;
import com.skala.core.api.model.AuthenticationToken;
import com.skala.core.api.model.ConfigurationApi;
import com.skala.core.api.model.Images;
import com.skala.videotrainingapp.rest.helper.ApiKeyProperties;
import com.skala.videotrainingapp.rest.helper.InterceptorMock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
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
    private static String videoApiKey;

    @BeforeClass
    public static void setUpBeforeClass() {
        ApiKeyProperties apiKeyProperties = new ApiKeyProperties();
        videoApiKey = apiKeyProperties.getApiKey();
    }

    @Before
    public void setUp() {
        lock = new CountDownLatch(1);
    }

    @Test
    public void testGetConfigurationFromMock() throws Exception {
        RestVideoApi restVideoApi = getRestVideoApi(InterceptorMock.TYPE_CONFIG);

        final ConfigurationApi[] configurationApi = new ConfigurationApi[1];
        final ConfigurationApi expected = getExpectedConfigurationApi();

        Call<ConfigurationApi> config = restVideoApi.getConfiguration(videoApiKey);
        config.enqueue(new Callback<ConfigurationApi>() {
            @Override
            public void onResponse(Call<ConfigurationApi> call, Response<ConfigurationApi> response) {
                configurationApi[0] = response.body();
                lock.countDown();
            }

            @Override
            public void onFailure(Call<ConfigurationApi> call, Throwable t) {
                System.out.print(t.toString());
                lock.countDown();
            }
        });

        lock.await(DURATION_LOCK_IN_MILLISECOND, TimeUnit.MILLISECONDS);
        Assert.assertEquals("Configuration api is not parsed properly", expected, configurationApi[0]);
    }

    @Test
    public void testGetRequestTokenFromMock() throws Exception {
        RestVideoApi restVideoApi = getRestVideoApi(InterceptorMock.TYPE_AUTHENTICATION_TOKEN);

        final AuthenticationToken[] authenticationToken = new AuthenticationToken[1];
        final AuthenticationToken expected = getExpectedAuthenticationToken();

        Call<AuthenticationToken> config = restVideoApi.getRequestToken(videoApiKey);
        config.enqueue(new Callback<AuthenticationToken>() {
            @Override
            public void onResponse(Call<AuthenticationToken> call, Response<AuthenticationToken> response) {
                authenticationToken[0] = response.body();
                lock.countDown();
            }

            @Override
            public void onFailure(Call<AuthenticationToken> call, Throwable t) {
                System.out.print(t.toString());
                lock.countDown();
            }
        });

        lock.await(DURATION_LOCK_IN_MILLISECOND, TimeUnit.MILLISECONDS);
        Assert.assertEquals("AuthenticationToken is not parsed properly", expected, authenticationToken[0]);
    }

    @Test
    public void testGetSessionIdFromMock() throws Exception {
        RestVideoApi restVideoApi = getRestVideoApi(InterceptorMock.TYPE_AUTHENTICATION_SESSION_ID);

        final AuthenticationSessionId[] authenticationToken = new AuthenticationSessionId[1];
        final AuthenticationSessionId expected = getExpectedAuthenticationSessionId();

        Call<AuthenticationSessionId> config = restVideoApi.getSessionId(videoApiKey, "mock_token");
        config.enqueue(new Callback<AuthenticationSessionId>() {
            @Override
            public void onResponse(Call<AuthenticationSessionId> call, Response<AuthenticationSessionId> response) {
                authenticationToken[0] = response.body();
                lock.countDown();
            }

            @Override
            public void onFailure(Call<AuthenticationSessionId> call, Throwable t) {
                System.out.print(t.toString());
                lock.countDown();
            }
        });

        lock.await(DURATION_LOCK_IN_MILLISECOND, TimeUnit.MILLISECONDS);
        Assert.assertEquals("AuthenticationSessionId is not parsed properly", expected, authenticationToken[0]);
    }

    private RestVideoApi getRestVideoApi(String type) {
        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(InterceptorMock.newInstance(type))
                .build();
        return new RestVideoApi(client);
    }

    private ConfigurationApi getExpectedConfigurationApi() {
        List<String> backdropSizes = new ArrayList<>();
        backdropSizes.add("w300");
        backdropSizes.add("w780");
        backdropSizes.add("w1280");
        backdropSizes.add("original");

        List<String> logoSizes = new ArrayList<>();
        logoSizes.add("w45");
        logoSizes.add("w92");
        logoSizes.add("w154");
        logoSizes.add("w185");
        logoSizes.add("w300");
        logoSizes.add("w500");
        logoSizes.add("original");

        List<String> posterSizes = new ArrayList<>();
        posterSizes.add("w92");
        posterSizes.add("w154");
        posterSizes.add("w185");
        posterSizes.add("w342");
        posterSizes.add("w500");
        posterSizes.add("w780");
        posterSizes.add("original");

        List<String> profileSizes = new ArrayList<>();
        profileSizes.add("w45");
        profileSizes.add("w185");
        profileSizes.add("h632");
        profileSizes.add("original");

        List<String> stillSizes = new ArrayList<>();
        stillSizes.add("w92");
        stillSizes.add("w185");
        stillSizes.add("w300");
        stillSizes.add("original");

        Images images = new Images();
        images.setBaseUrl("http://image.tmdb.org/t/p/");
        images.setSecureBaseUrl("https://image.tmdb.org/t/p/");
        images.setBackdropSizes(backdropSizes);
        images.setLogoSizes(logoSizes);
        images.setPosterSizes(posterSizes);
        images.setProfileSizes(profileSizes);
        images.setStillSizes(stillSizes);

        List<String> changeKeys = new ArrayList<>();
        changeKeys.add("adult");
        changeKeys.add("also_known_as");
        changeKeys.add("alternative_titles");
        changeKeys.add("biography");
        changeKeys.add("birthday");
        changeKeys.add("budget");
        changeKeys.add("cast");
        changeKeys.add("character_names");
        changeKeys.add("crew");
        changeKeys.add("deathday");
        changeKeys.add("general");
        changeKeys.add("genres");
        changeKeys.add("homepage");
        changeKeys.add("images");
        changeKeys.add("imdb_id");
        changeKeys.add("name");
        changeKeys.add("original_title");
        changeKeys.add("overview");
        changeKeys.add("plot_keywords");
        changeKeys.add("production_companies");
        changeKeys.add("production_countries");
        changeKeys.add("releases");
        changeKeys.add("revenue");
        changeKeys.add("runtime");
        changeKeys.add("spoken_languages");
        changeKeys.add("status");
        changeKeys.add("tagline");
        changeKeys.add("title");
        changeKeys.add("trailers");
        changeKeys.add("translations");

        ConfigurationApi expected = new ConfigurationApi();
        expected.setImages(images);
        expected.setChangeKeys(changeKeys);

        return expected;
    }

    private AuthenticationToken getExpectedAuthenticationToken() {
        AuthenticationToken expected = new AuthenticationToken();
        expected.setSuccess(true);
        expected.setExpiresAt("2016-06-13 21:46:13 UTC");
        expected.setRequestToken("111111111111111111111111111111111");
        return expected;
    }

    public AuthenticationSessionId getExpectedAuthenticationSessionId() {
        AuthenticationSessionId expected = new AuthenticationSessionId();
        expected.setSuccess(true);
        expected.setSessionId("80b2bf99520cd795ff54e31af97917bc9e3a7c8c");
        return expected;
    }
}
