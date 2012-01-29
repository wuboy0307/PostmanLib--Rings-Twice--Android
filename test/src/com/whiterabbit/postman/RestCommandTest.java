package com.whiterabbit.postman;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.whiterabbit.postman.commands.CommandFactory;
import com.whiterabbit.postman.commands.RestServerCommand;
import com.whiterabbit.postman.commands.RestServerCommand.Action;
import com.whiterabbit.postman.commands.ServerCommand;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class RestCommandTest {
	private class MyRestFactory extends CommandFactory{

		@Override
		public ServerCommand createCommand(String type) {
			MyRestCommand c = new MyRestCommand();
			return c;
		}
		
	}
	
	
	private class MyRestCommand extends RestServerCommand{

		public MyRestCommand(Action a) {
			super(a);
		}
		
		public MyRestCommand(){
			super();
		}

		@Override
		protected void setCallPayload(HttpEntityEnclosingRequestBase call) {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);  
            nameValuePairs.add(new BasicNameValuePair("FAVA", "RAVA"));               
            try {
				call.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 			
		}

		@Override
		protected String getUrl(Action a) {
			return "www.thisisatry.com";
		}

		@Override
		protected void processHttpResult(String result, Context c) {
			assertEquals(result, "OK");
			assertEquals(c, mContext);
		}

		@Override
		protected void authenticate(HttpRequestBase req) {
			// no authentication			
		}

		@Override
		protected void fillIntent(Intent i) {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected void fromIntent(Intent i) {
			// TODO Auto-generated method stub
			
		}
		
	}
	MyRestCommand mCommand;
	Activity mContext;
	final static String mReqId = "REQUEST";
	final static String mResultString = "RESULTSTRING";
	
	@Before
    public void setUp() throws Exception {
		mCommand = new MyRestCommand(Action.GET);
		mCommand.setRequestId(mReqId);
		mContext = new Activity();
    }

        
    @Test
    public void testSimpleExecute() throws Exception {
    	CommandFactory c = new TestCommandFactory();
    	ServerInteractionHelper h = ServerInteractionHelper.initWithCommandFactory(c);
    	
    	Robolectric.addPendingHttpResponse(200, "OK");
    	mCommand.execute(mContext);
    }
    
    @Test
    public void testHelperExecute() throws Exception {
    	CommandFactory c = new TestCommandFactory();
    	ServerInteractionHelper h = ServerInteractionHelper.initWithCommandFactory(c);
    	ServerInteractionResponseInterface i = new ServerInteractionResponseInterface(){

			@Override
			public void onServerResult(String result, String requestId) {
				assertEquals(requestId, mReqId);
			}

			@Override
			public void onServerError(String result, String requestId) {
				fail();
			}    		
    	};
    	
    	h.registerEventListener(i , mContext);
    	
    	Robolectric.addPendingHttpResponse(200, "OK");
    	
    	
    	// should be sending command to the intent service
    	mCommand.execute(mContext);
    	h.unregisterEventListener(i, mContext);
    }
    
    
}