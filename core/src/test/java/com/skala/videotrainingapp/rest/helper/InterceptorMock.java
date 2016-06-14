package com.skala.videotrainingapp.rest.helper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author Skala
 */
public class InterceptorMock implements Interceptor {
    public final static String TYPE_CONFIG =
            "{\n" +
            "  \"images\": {\n" +
            "    \"base_url\": \"http://image.tmdb.org/t/p/\",\n" +
            "    \"secure_base_url\": \"https://image.tmdb.org/t/p/\",\n" +
            "    \"backdrop_sizes\": [\n" +
            "      \"w300\",\n" +
            "      \"w780\",\n" +
            "      \"w1280\",\n" +
            "      \"original\"\n" +
            "    ],\n" +
            "    \"logo_sizes\": [\n" +
            "      \"w45\",\n" +
            "      \"w92\",\n" +
            "      \"w154\",\n" +
            "      \"w185\",\n" +
            "      \"w300\",\n" +
            "      \"w500\",\n" +
            "      \"original\"\n" +
            "    ],\n" +
            "    \"poster_sizes\": [\n" +
            "      \"w92\",\n" +
            "      \"w154\",\n" +
            "      \"w185\",\n" +
            "      \"w342\",\n" +
            "      \"w500\",\n" +
            "      \"w780\",\n" +
            "      \"original\"\n" +
            "    ],\n" +
            "    \"profile_sizes\": [\n" +
            "      \"w45\",\n" +
            "      \"w185\",\n" +
            "      \"h632\",\n" +
            "      \"original\"\n" +
            "    ],\n" +
            "    \"still_sizes\": [\n" +
            "      \"w92\",\n" +
            "      \"w185\",\n" +
            "      \"w300\",\n" +
            "      \"original\"\n" +
            "    ]\n" +
            "  },\n" +
            "  \"change_keys\": [\n" +
            "    \"adult\",\n" +
            "    \"also_known_as\",\n" +
            "    \"alternative_titles\",\n" +
            "    \"biography\",\n" +
            "    \"birthday\",\n" +
            "    \"budget\",\n" +
            "    \"cast\",\n" +
            "    \"character_names\",\n" +
            "    \"crew\",\n" +
            "    \"deathday\",\n" +
            "    \"general\",\n" +
            "    \"genres\",\n" +
            "    \"homepage\",\n" +
            "    \"images\",\n" +
            "    \"imdb_id\",\n" +
            "    \"name\",\n" +
            "    \"original_title\",\n" +
            "    \"overview\",\n" +
            "    \"plot_keywords\",\n" +
            "    \"production_companies\",\n" +
            "    \"production_countries\",\n" +
            "    \"releases\",\n" +
            "    \"revenue\",\n" +
            "    \"runtime\",\n" +
            "    \"spoken_languages\",\n" +
            "    \"status\",\n" +
            "    \"tagline\",\n" +
            "    \"title\",\n" +
            "    \"trailers\",\n" +
            "    \"translations\"\n" +
            "  ]\n" +
            "}";
    public final static String TYPE_AUTHENTICATION_TOKEN = "{\"success\":true,\"expires_at\":\"2016-06-13 21:46:13 UTC\",\"request_token\":\"111111111111111111111111111111111\"}";
    public final static String TYPE_AUTHENTICATION_SESSION_ID = "{  \"session_id\": \"80b2bf99520cd795ff54e31af97917bc9e3a7c8c\",  \"success\": true}";

    private final String responseString;

    public static InterceptorMock newInstance(String type) {
        return new InterceptorMock(type);
    }

    private InterceptorMock(String type) {
        responseString = type;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = new Response.Builder()
                .code(200)
                .message(responseString)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()))
                .addHeader("content-type", "application/json")
                .build();

        return response;
    }
}
