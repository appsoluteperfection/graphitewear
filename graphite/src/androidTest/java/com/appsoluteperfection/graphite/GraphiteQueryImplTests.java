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
        final Message wasCalled = new Message();
        wasCalled.obj = false;
        mockUrlBuilder.buildFrom(EasyMock.anyString());
        EasyMock.expectLastCall().andAnswer(new IAnswer<Object>() {
            @Override
            public Object answer() throws Throwable {
                wasCalled.obj = true;
                throw new Exception("Fuck!");
//                return null;
            }
        }).atLeastOnce();
        GraphiteQueryImpl classUnderTest = new GraphiteQueryImpl(mockUrlBuilder);

        // Act
        classUnderTest.getGraphFromSearchString("Ramalamadingdong");

        // Assert
        Assert.assertEquals(true, wasCalled.obj);
    }
}
