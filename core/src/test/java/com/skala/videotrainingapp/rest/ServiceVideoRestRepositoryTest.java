package com.skala.videotrainingapp.rest;

import com.google.gson.Gson;
import com.skala.core.api.ConfigurationServiceApi;
import com.skala.core.api.VideoServiceApi;
import com.skala.core.api.model.AuthenticationSessionId;
import com.skala.core.api.model.AuthenticationToken;
import com.skala.core.api.model.ConfigurationApi;
import com.skala.core.api.model.Images;
import com.skala.core.api.net.CallApi;
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
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Skala
 */
public class ServiceVideoRestRepositoryTest {
    private static final String ENDPOINT = "http://api.themoviedb.org/3/";
    private static final String TYPE_CONFIG = "{\n"
            + "  \"images\": {\n"
            + "    \"base_url\": \"http://image.tmdb.org/t/p/\",\n"
            + "    \"secure_base_url\": \"https://image.tmdb.org/t/p/\",\n"
            + "    \"backdrop_sizes\": [\n"
            + "      \"w300\",\n"
            + "      \"w780\",\n"
            + "      \"w1280\",\n"
            + "      \"original\"\n"
            + "    ],\n"
            + "    \"logo_sizes\": [\n"
            + "      \"w45\",\n"
            + "      \"w92\",\n"
            + "      \"w154\",\n"
            + "      \"w185\",\n"
            + "      \"w300\",\n"
            + "      \"w500\",\n"
            + "      \"original\"\n"
            + "    ],\n"
            + "    \"poster_sizes\": [\n"
            + "      \"w92\",\n"
            + "      \"w154\",\n"
            + "      \"w185\",\n"
            + "      \"w342\",\n"
            + "      \"w500\",\n"
            + "      \"w780\",\n"
            + "      \"original\"\n"
            + "    ],\n"
            + "    \"profile_sizes\": [\n"
            + "      \"w45\",\n"
            + "      \"w185\",\n"
            + "      \"h632\",\n"
            + "      \"original\"\n"
            + "    ],\n"
            + "    \"still_sizes\": [\n"
            + "      \"w92\",\n"
            + "      \"w185\",\n"
            + "      \"w300\",\n"
            + "      \"original\"\n"
            + "    ]\n"
            + "  },\n"
            + "  \"change_keys\": [\n"
            + "    \"adult\",\n"
            + "    \"also_known_as\",\n"
            + "    \"alternative_titles\",\n"
            + "    \"biography\",\n"
            + "    \"birthday\",\n"
            + "    \"budget\",\n"
            + "    \"cast\",\n"
            + "    \"character_names\",\n"
            + "    \"crew\",\n"
            + "    \"deathday\",\n"
            + "    \"general\",\n"
            + "    \"genres\",\n"
            + "    \"homepage\",\n"
            + "    \"images\",\n"
            + "    \"imdb_id\",\n"
            + "    \"name\",\n"
            + "    \"original_title\",\n"
            + "    \"overview\",\n"
            + "    \"plot_keywords\",\n"
            + "    \"production_companies\",\n"
            + "    \"production_countries\",\n"
            + "    \"releases\",\n"
            + "    \"revenue\",\n"
            + "    \"runtime\",\n"
            + "    \"spoken_languages\",\n"
            + "    \"status\",\n"
            + "    \"tagline\",\n"
            + "    \"title\",\n"
            + "    \"trailers\",\n"
            + "    \"translations\"\n"
            + "  ]\n"
            + "}";
    private static final String TYPE_AUTHENTICATION_TOKEN = "{\"success\":true,\"expires_at\":\"2016-06-13 21:46:13 UTC\",\"request_token\":\"1111111111111\"}";
    private static final String TYPE_AUTHENTICATION_SESSION_ID = "{  \"session_id\": \"80b2bf99520cd795ff54e31af97917bc9e3a7c8c\",  \"success\": true}";

    private static final int DURATION_LOCK_IN_MILLISECOND = 6000;
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
        ConfigurationServiceApi configurationServiceApi = getConfigurationServiceApi(TYPE_CONFIG);

        final ConfigurationApi[] configurationApi = new ConfigurationApi[1];
        final ConfigurationApi expected = getExpectedConfigurationApi();

        configurationServiceApi.getConfiguration().subscribe(config -> {
            configurationApi[0] = config;
        }, throwable -> {
            System.out.print(throwable.toString());
        });

        Assert.assertEquals("Configuration api is not parsed properly", expected, configurationApi[0]);
    }

    @Test
    public void testGetRequestTokenFromMock() throws Exception {
        VideoServiceApi videoServiceApi = getRestVideoApi(TYPE_AUTHENTICATION_TOKEN);

        final AuthenticationToken[] authenticationToken = new AuthenticationToken[1];
        final AuthenticationToken expected = getExpectedAuthenticationToken();

        videoServiceApi.getRequestToken(new CallApi<AuthenticationToken, String>() {
            @Override
            public void onSuccess(AuthenticationToken responseAuthToken) {
                authenticationToken[0] = responseAuthToken;
                lock.countDown();
            }

            @Override
            public void onFailed(String error) {
                System.out.print(error);
                lock.countDown();
            }
        });

        boolean isStillWaiting = lock.await(DURATION_LOCK_IN_MILLISECOND, TimeUnit.MILLISECONDS);
        Assert.assertTrue(isStillWaiting);
        Assert.assertEquals("AuthenticationToken is not parsed properly", expected, authenticationToken[0]);
    }

    @Test
    public void testGetSessionIdFromMock() throws Exception {
        VideoServiceApi videoServiceApi = getRestVideoApi(TYPE_AUTHENTICATION_SESSION_ID);

        final AuthenticationSessionId[] authenticationSessionId = new AuthenticationSessionId[1];
        final AuthenticationSessionId expected = getExpectedAuthenticationSessionId();

        videoServiceApi.getSessionId(new CallApi<AuthenticationSessionId, String>() {
            @Override
            public void onSuccess(AuthenticationSessionId responseAuthSession) {
                authenticationSessionId[0] = responseAuthSession;
                lock.countDown();
            }

            @Override
            public void onFailed(String error) {
                System.out.print(error);
                lock.countDown();
            }
        }, "mock_token");

        boolean isStillWaiting = lock.await(DURATION_LOCK_IN_MILLISECOND, TimeUnit.MILLISECONDS);
        Assert.assertTrue(isStillWaiting);
        Assert.assertEquals("AuthenticationSessionId is not parsed properly", expected, authenticationSessionId[0]);
    }

    private ConfigurationServiceApi getConfigurationServiceApi(String type) {
        Retrofit retrofit = getRetrofit(type);
        return new ConfigurationServiceApi(retrofit, videoApiKey);
    }

    private VideoServiceApi getRestVideoApi(String type) {
        Retrofit retrofit = getRetrofit(type);
        return new VideoServiceApi(retrofit, videoApiKey);
    }

    private Retrofit getRetrofit(String type) {
        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(InterceptorMock.newInstance(type))
                .build();

        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .baseUrl(ENDPOINT)
                .client(client)
                .build();
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
        expected.setRequestToken("1111111111111");
        return expected;
    }

    public AuthenticationSessionId getExpectedAuthenticationSessionId() {
        AuthenticationSessionId expected = new AuthenticationSessionId();
        expected.setSuccess(true);
        expected.setSessionId("80b2bf99520cd795ff54e31af97917bc9e3a7c8c");
        return expected;
    }
}
