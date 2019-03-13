package cn.deepdraw.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

	public static Date toDate(String date) {

		SimpleDateFormat format = new SimpleDateFormat("yy-mm-dd");
		Date pd = null;
		try {
			pd = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return pd;
	}
}
