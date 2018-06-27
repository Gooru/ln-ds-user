package org.gooru.ds.user.app.components.utilities;

import java.util.Calendar;

/**
 * @author szgooru on 27-Jun-2018
 */
public final class CommonUtils {

	private CommonUtils() {
		throw new AssertionError();
	}
	
    public static int currentYear() {
    	return Calendar.getInstance().get(Calendar.YEAR);
    }
    
    public static int currentMonth() {
    	return Calendar.getInstance().get(Calendar.MONTH);
    }
}
