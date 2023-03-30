
public class Record {
	
	public record Person(int id, String name) {
	}

	static void printPerson(Object obj) {
	    if (obj instanceof Person p) {
	        int id = p.id();
	        String name = p.name();
	        System.out.println("Person id - " + id + " and name - " + name);
	    } else if (obj instanceof String s) {
	    	System.out.println("String instance with value - " + s);
	    } else {
	    	System.out.println("Breakout");
	    }
	}
	
	public static void main(String args[]) {
		printPerson("Gokul");
		printPerson(new Person(11, "Gokul"));
		printPerson(1);
		printPerson(null);
		printStudentCourse(new Student(new Bio(new Name(11, "Gokulnath", "Madhivanan"), Course.CSE), new Year("2014-18")));
	}

	public record Year(String ys) {}
	public record Name(int rollno, String first, String last) {}
	enum Course { CSE, IT }
	public record Bio(Name n, Course  c) {}
	public record Student(Bio b, Year y) {}
	
	public static void printStudentCourse(Object obj) {
		if (obj instanceof Student st) {
			System.out.println("Student - " + st.b().n().first + " , course - " + st.b().c());
			printName(st.b);
		} else {
			System.out.println("Else part");
		}
	}
	
	public static void printName(Object obj) {
		if (obj instanceof Bio(Name n, Course c)) {
			System.out.println("FullName - " + n.first() + " "+ n.last());
		} else {
			System.out.println("Else part");
		}
	}
}



// javac --release 20 --enable-preview Record.java
// java --enable-preview Record