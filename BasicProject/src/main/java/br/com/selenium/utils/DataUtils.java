package br.com.selenium.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataUtils {

	public static Date calculateDate(int days) { // O método é Static para não precisar instanciar nas classes que irao usar o esta funcao
		Calendar calendar = Calendar.getInstance(); // Ja instanciou esta classe Calendar
		calendar.add(Calendar.DAY_OF_MONTH, days); // Soma a quantidade de dias passada por parametro (days) à data atual
		return calendar.getTime();
	}
	
	public static String formatDate(Date date) {
		DateFormat formatDate = new SimpleDateFormat("dd/MM/YYYY");
		return formatDate.format(date);
	}
	
	public static String formatHour(Date date) {
		DateFormat formatDate = new SimpleDateFormat("HH:mm:ss");
		return formatDate.format(date);
	}
	
	public static String returnCalculatedDate(int days) { // Ja retorna a data calculada e formatada
		Date futureDate = calculateDate(days);
		return formatDate(futureDate);
	}
	
}