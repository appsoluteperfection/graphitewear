package com.appsoluteperfection.graphite;

import android.app.Application;
import android.os.Message;
import android.test.ApplicationTestCase;

import com.appsoluteperfection.graphite.builders.GraphiteSearchUrlBuilder;
import com.appsoluteperfection.graphite.queries.GraphiteQueryImpl;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.easymock.IAnswer;

public class GraphiteQueryImplTests extends ApplicationTestCase<Application> {
    private GraphiteSearchUrlBuilder mockUrlBuilder;

    public GraphiteQueryImplTests() {
        super(Application.class);
    }


    public void setUp(){
        mockUrlBuilder = EasyMock.createNiceMock(GraphiteSearchUrlBuilder.class);
    }

    public void testShouldNotCallUrlBuilderIfNoSearchPassedIn(){
        // Arrange
        mockUrlBuilder.buildFrom(EasyMock.anyString());
        EasyMock.expectLastCall().andThrow(new AssertionError()).anyTimes();
        GraphiteQueryImpl classUnderTest = new GraphiteQueryImpl(mockUrlBuilder);

        // Act
        classUnderTest.getGraphFromSearchString(null);

        // Assert will fail above
    }

    public void testShouldNotCallUrlBuilderIfEmptySearchPassedIn(){
        // Arrange
        mockUrlBuilder.buildFrom(EasyMock.anyString());
        EasyMock.expectLastCall().andThrow(new AssertionError()).anyTimes();
        GraphiteQueryImpl classUnderTest = new GraphiteQueryImpl(mockUrlBuilder);

        // Act
        classUnderTest.getGraphFromSearchString("");

        // Assert will fail above
    }

    public void testShouldCallUrlBuilderIfAnySearchPassedIn(){
        // Arrange
        EasyMock.expect(mockUrlBuilder.buildFrom(EasyMock.anyString()))
                .andReturn("anything")
                .atLeastOnce();
        EasyMock.replay(mockUrlBuilder);
        GraphiteQueryImpl classUnderTest = new GraphiteQueryImpl(mockUrlBuilder);

        // Act
        classUnderTest.getGraphFromSearchString("Ramalamadingdong");

        // Assert
        EasyMock.verify(mockUrlBuilder);
    }
}
