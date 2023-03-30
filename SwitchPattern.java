
public class SwitchPattern {
	
	static String formatterPatternSwitch(Object obj) {
	    return switch (obj) {
	        case Integer i -> String.format("int %d", i);
	        case Long l    -> String.format("long %d", l);
	        case Double d  -> String.format("double %f", d);
	        case String s  -> String.format("String %s", s);
	        default        -> obj.toString();
	    };
	}
	
	static void testString(String s) {
	    switch (s) {
	        case null         -> System.out.println("Oops");
	        case "Foo", "Bar" -> System.out.println("Great");
	        default           -> System.out.println("Ok");
	    }
	}
	

	public static void main(String[] args) {
		System.out.println(formatterPatternSwitch("Gokul"));
		System.out.println(formatterPatternSwitch(11));
		System.out.println(formatterPatternSwitch(11L));
		System.out.println(formatterPatternSwitch(11.11));
		testString("Gokul");
		testString(null);
		testString("Foo");
		testTriangle(null);
		testTriangle(new Triangle(10, 100));
		testTriangle(new Triangle(10, 10));
		testTriangle(new Rectangle());
	}

	public static void testTriangle(Shape s) {
	    switch (s) {
	        case null -> 
	            { 
		            System.out.println("Null");
		            break; }
	        case Triangle t
	        when t.calculateArea() > 100 -> {
	            System.out.println("Large triangle");
	            break;
	        }
	        case Triangle t ->
	            System.out.println("Small triangle");
	        default ->
	            System.out.println("Non-triangle");
	    }
	}
}


class Shape { 	}
class Rectangle extends Shape { 	}
class Triangle extends Shape {
	int b;
	int h;
	Triangle(int b, int h) {
		this.b = b;
		this.h = h;
	}
	int calculateArea() {
		return  (b * h) / 2;
	}
}



// javac --release 20 --enable-preview SwitchPattern.java 
// java --enable-preview SwitchPattern
