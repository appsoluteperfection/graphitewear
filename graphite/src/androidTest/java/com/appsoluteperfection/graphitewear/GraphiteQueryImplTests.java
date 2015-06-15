package com.appsoluteperfection.graphitewear;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.appsoluteperfection.graphitewear.builders.GraphiteSearchUrlBuilder;
import com.appsoluteperfection.graphitewear.queries.GraphiteQueryImpl;

import org.easymock.EasyMock;

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
