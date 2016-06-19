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
