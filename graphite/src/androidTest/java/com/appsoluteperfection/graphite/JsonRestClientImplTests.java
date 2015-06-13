package com.appsoluteperfection.graphite;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.appsoluteperfection.graphite.builders.GraphiteSearchUrlBuilder;
import com.appsoluteperfection.graphite.clients.JsonRestClientImpl;
import com.appsoluteperfection.graphite.dtos.ErrorDto;
import com.appsoluteperfection.graphite.dtos.ErrorsDto;
import com.appsoluteperfection.graphite.dtos.GoogleErrorDto;

import junit.framework.Assert;

public class JsonRestClientImplTests extends ApplicationTestCase<Application> {
    private GraphiteSearchUrlBuilder mockUrlBuilder;

    private String knownUrlWithJson = "https://www.googleapis.com/customsearch/v1?format=json";

    public JsonRestClientImplTests() {
        super(Application.class);
    }

    public void testShouldSerializeKnownUrlWithSomeKnownValues(){
        // Arrange
        JsonRestClientImpl classUnderTest = new JsonRestClientImpl();

        // Act
        GoogleErrorDto result = classUnderTest.get(knownUrlWithJson, GoogleErrorDto.class);

        // Assert
        Assert.assertNotNull(result);
        ErrorDto error = result.getError();
        Assert.assertNotNull(error);
        ErrorsDto[] errors = error.getErrors();
        Assert.assertNotNull(error.getCode());
        Assert.assertEquals("400", error.getCode());
        Assert.assertNotNull(error.getMessage());
        Assert.assertNotNull(errors);
        Assert.assertTrue(errors.length > 0);
        ErrorsDto firstError = errors[0];
        Assert.assertNotNull(firstError);
        Assert.assertNotNull(firstError.getMessage());
        Assert.assertNotNull(firstError.getReason());
        Assert.assertNotNull(firstError.getDomain());
        Assert.assertNotNull(firstError.getLocationType());
        Assert.assertNotNull(firstError.getLocation());
    }

}
