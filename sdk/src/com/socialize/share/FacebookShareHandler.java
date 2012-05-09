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
package com.socialize.share;

import android.app.Activity;
import android.content.Context;
import com.socialize.api.action.ShareType;
import com.socialize.auth.AuthProviderType;
import com.socialize.entity.PropagationInfo;
import com.socialize.entity.SocializeAction;
import com.socialize.networks.SocialNetwork;
import com.socialize.networks.SocialNetworkListener;
import com.socialize.networks.facebook.FacebookSharer;


/**
 * @author Jason Polites
 *
 */
public class FacebookShareHandler extends AbstractShareHandler {

	private FacebookSharer facebookSharer;
	
	@Override
	protected void handle(Activity context, final SocializeAction action, String text, PropagationInfo info, final ShareHandlerListener listener) throws Exception {
		facebookSharer.share(context, action.getEntity(), info, text, true, action.getActionType(), new SocialNetworkListener() {
			@Override
			public void onError(Activity parent, SocialNetwork network, String message, Throwable error) {
				listener.onError(parent, action, message, error);
			}
			
			@Override
			public void onBeforePost(Activity parent, SocialNetwork network) {}
			
			@Override
			public void onAfterPost(Activity parent, SocialNetwork network) {}
		});
	}

	@Override
	protected ShareType getShareType() {
		return ShareType.FACEBOOK;
	}
	
	@Override
	public boolean isAvailableOnDevice(Context parent) {
		return getSocialize().isSupported(AuthProviderType.FACEBOOK);
	}	

	public void setFacebookSharer(FacebookSharer facebookSharer) {
		this.facebookSharer = facebookSharer;
	}
}