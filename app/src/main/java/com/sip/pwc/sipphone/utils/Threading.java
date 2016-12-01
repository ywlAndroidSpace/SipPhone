/**
 * Copyright (C) 2010-2012 Regis Montoya (aka r3gis - www.r3gis.fr)
 * This file is part of CSipSimple.
 *
 *  CSipSimple is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  If you own a pjsip commercial license you can also redistribute it
 *  and/or modify it under the terms of the GNU Lesser General Public License
 *  as an android library.
 *
 *  CSipSimple is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with CSipSimple.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.sip.pwc.sipphone.utils;

import java.lang.reflect.Method;

import android.os.HandlerThread;
import android.util.Log;

public class Threading {

	
	private static final String THIS_FILE = "Threading";

	public final static void stopHandlerThread(HandlerThread handlerThread, boolean wait) {
		if(handlerThread == null) {
			//Nothing to do if already null
			return;
		}
		boolean fails = true;
		
		if(Compatibility.isCompatible(5)) {
			try {
				Method method = handlerThread.getClass().getDeclaredMethod("quit");
				method.invoke(handlerThread);
				fails = false;
			} catch (Exception e) {
				Log.d(THIS_FILE, "Something is wrong with api level declared use fallback method");
			}
		}
		if (fails && handlerThread.isAlive() && wait) {
			try {
				//This is needed for android 4 and lower
				handlerThread.join(500);
				/*
				if (handlerThread.isAlive()) {
					handlerThread.
				}
				*/
			} catch (Exception e) {
				Log.e(THIS_FILE, "Can t finish handler thread....", e);
			}
		}
	}
}
