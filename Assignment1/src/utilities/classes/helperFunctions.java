package utilities.classes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class helperFunctions {
	public static boolean passwordValidation(String pw ,String pwConfirm) {
	    
		if (pw == null || pw.trim().isEmpty() || !pw.equals(pwConfirm)) {
	         System.out.println("Incorrect format of string");
	         return false;
	     }
	     
	     Pattern p = Pattern.compile("[^A-Z]");
	     Matcher m = p.matcher(pw);
	    // boolean b = m.matches();
	     boolean b = m.find();
	     if (b == true ) {
	    	 	System.out.println("password valid******");
	    	 	return true;
	     }
	    	 return false;
	    	 	
	         
	    
	}
}
