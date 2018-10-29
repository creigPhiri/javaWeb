package utilities.classes;

public class Person {
	public String firstname , lastname , email, password = "";
	public final String role ="user";
	
	public String enrolmentLetter() {
		String letter = "Dear " + this.firstname + "\nThank you for joining our humble application. \n \n Sincerely Admin";
		return letter;
	}
}
