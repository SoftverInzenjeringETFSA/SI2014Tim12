package ba.unsa.etf.si.tim12.ui.components;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Validator {
	
	public static java.util.Date ValidAndParse(String simpleDateFormat, String dateAsText) throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat(simpleDateFormat);
		df.setLenient(false);
		java.util.Date newDate = null;
		
		try{
			newDate = df.parse(dateAsText);
			
		} catch (ParseException e){
			throw new ParseException("Unesite datum u formatu " + simpleDateFormat, e.getErrorOffset());
		}
		
		if(!df.format(newDate).equals(dateAsText))
			throw new ParseException("Datum nije ispravan. Provjerite da li imate viška ili manjka znakova.",
					df.format(newDate).length());
		
		return newDate;
	}
	
	public static String formatDate(String simpleDateFormat, java.util.Date date){
		SimpleDateFormat df = new SimpleDateFormat(simpleDateFormat);
		df.setLenient(false);
		
		return df.format(date);
	}
	
	public static boolean isPhoneNumber(String phone){
		return phone.matches("^\\+??\\d{6,}$");
	}
	
	public static void ValidateName(String text) throws Exception{
		if(text.length() < 3)
			throw new Exception("Ime mora imati najmanje 3 karaktera");
		if(!text.matches("^[a-zA-Z \\.ćĆčČšŠđŠžŽ]*$"))
			throw new Exception("Ime može imati samo slova, razmake i tačke.");
		if(text.matches("^.*[ ]{2,}.*$"))
			throw new Exception("Ime ne može imati duple razmake.");
		if(text.matches("^.*([\\. ]\\.).*$"))
			throw new Exception("Ime ne može imati duplu tačku.");
		
	}
}
