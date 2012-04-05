/*
 * Copyright (c) 2011 Socialize Inc.
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
package com.socialize.launcher;

import android.app.Activity;
import android.content.Intent;


/**
 * @author Jason Polites
 *
 */
public abstract class BaseLauncher implements Launcher {
	
	protected LaunchListener launchListener;

	/* (non-Javadoc)
	 * @see com.socialize.launcher.Launcher#onResult(android.app.Activity, int, int, android.content.Intent, android.content.Intent)
	 */
	@Override
	public void onResult(Activity context, int requestCode, int resultCode, Intent returnedIntent, Intent originalIntent) {}

	/* (non-Javadoc)
	 * @see com.socialize.launcher.Launcher#shouldFinish()
	 */
	@Override
	public boolean shouldFinish() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.socialize.launcher.Launcher#getLaunchListener()
	 */
	@Override
	public LaunchListener getLaunchListener() {
		return launchListener;
	}

	/* (non-Javadoc)
	 * @see com.socialize.launcher.Launcher#setLaunchListener(com.socialize.launcher.LaunchListener)
	 */
	@Override
	public void setLaunchListener(LaunchListener listener) {
		this.launchListener = listener;
	}
}
