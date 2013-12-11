/*
 * Copyright (c) 2012 Socialize Inc.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.socialize.test.unit;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import com.socialize.SocializeService;
import com.socialize.config.SocializeConfig;
import com.socialize.log.SocializeLogger;
import com.socialize.test.R;
import com.socialize.test.SocializeActivityTest;
import com.socialize.test.util.TestUtils;
import com.socialize.testapp.Main;
import com.socialize.util.DefaultAppUtils;
import org.mockito.Mockito;

/**
 * @author Jason Polites
 * 
 */
public class AppUtilsTest extends SocializeActivityTest {
	
	public void testGetAppIconId() {
		DefaultAppUtils utils = new DefaultAppUtils();
		int appIconId = utils.getAppIconId(getContext());
		assertEquals(R.drawable.ic_launcher, appIconId);
	}
	
	public void testLaunchMainApp() {

        Instrumentation.ActivityMonitor monitor = TestUtils.setUpActivityMonitor(this, Main.class);

        DefaultAppUtils.launchMainApp(getContext());
		
		Activity activity = monitor.waitForActivityWithTimeout(10000);
		
		assertNotNull(activity);
		activity.finish();
		assertEquals(Main.class.getName(), activity.getClass().getName());
		
	}
	
	public void test_isNotificationsAvailable() {
		
		final SocializeConfig config = Mockito.mock(SocializeConfig.class);
		final SocializeService socialize = Mockito.mock(SocializeService.class);
		
		Mockito.when(config.getBooleanProperty(SocializeConfig.SOCIALIZE_NOTIFICATIONS_ENABLED, true)).thenReturn(true);
		Mockito.when(config.isEntityLoaderCheckEnabled()).thenReturn(true);
		Mockito.when(socialize.getEntityLoader()).thenReturn(null);
		
		SocializeLogger mockLogger = new SocializeLogger();
		
		DefaultAppUtils appUtils = new DefaultAppUtils() {
			@Override
			public boolean hasPermission(Context context, String permission) {
				addResult(permission);
				return false;
			}

			@Override
			protected SocializeService getSocialize() {
				return socialize;
			}
		};
		
		appUtils.setConfig(config);
		appUtils.setLogger(mockLogger);
		
		assertFalse(appUtils.isNotificationsAvailable(getContext()));

		String result0 = getResult(0);
		String result1 = getResult(1);
		
		assertNotNull(result0);
		assertNotNull(result1);
		
		assertEquals(getContext().getPackageName() + ".permission.C2D_MESSAGE", result0);
		assertEquals("com.google.android.c2dm.permission.RECEIVE", result1);
	}
}
