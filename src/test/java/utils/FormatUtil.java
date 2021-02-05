/**
 *
 */
package utils;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author jair.souza
 *
 */
public class FormatUtil {
	
	public static String formatTimeElapsedMillis(long val) {
		StringBuilder buf = new StringBuilder(20);
		String sgn = "";

		if (val < 0) {
			sgn = "-";
			val = Math.abs(val);
		}

		append(buf, sgn, 0, (val / 3600000));
		append(buf, ":", 2, ((val % 3600000) / 60000));
		append(buf, ":", 2, ((val % 60000) / 1000));
		append(buf, ".", 3, (val % 1000));
		return buf.toString();
	}

	private static void append(StringBuilder tgt, String pfx, int dgt, long val) {
		tgt.append(pfx);
		if (dgt > 1) {
			int pad = (dgt - 1);
			for (long xa = val; xa > 9 && pad > 0; xa /= 10) {
				pad--;
			}
			for (int xa = 0; xa < pad; xa++) {
				tgt.append('0');
			}
		}
		tgt.append(val);
	}
	
	public String date() {
		Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
		String data = sd.format(date);
		return data;
		
	}
	public String obterDataDiferencaDias(int dias, String formato) {
		DateFormat format = new SimpleDateFormat(formato);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, dias);
		return format.format(calendar.getTime());
				
	}
}