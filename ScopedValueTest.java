
import jdk.incubator.concurrent.ScopedValue;

public class ScopedValueTest {
	
    public static ScopedValue<Boolean> scope = ScopedValue.newInstance(); 
    public static ScopedValue<String> scopeString = ScopedValue.newInstance(); 

	public static void method1() {
		System.out.println("Value of scope - " + scope.get());
		System.out.println("Value of String scope - " + scopeString.get());
	}
	
	public static void method2() {
		System.out.println("Value of scope - " + scope.get());
		System.out.println("Value of String scope - " + scopeString.get());
	}
	
	public static int getValue() {
		System.out.println("Value of scope - " + scope.get());
		System.out.println("Value of String scope - " + scopeString.get());
		return 11;
	}

	public static void main(String[] args) {
		
		ScopedValue.where(scope, true)                            // (2)
        .run(() -> ScopedValue.where(scopeString, "Gokul").run(() ->   {
        	method1();
        	method2();
        }));
		
	}
}



// javac --release 20 --enable-preview --add-modules jdk.incubator.concurrent ScopedValueTest.java
// java --enable-preview --add-modules jdk.incubator.concurrent ScopedValueTest