
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.Future;
import jdk.incubator.concurrent.StructuredTaskScope;

public class StructuredConcurrent {
	
	public static String findUser() throws Exception {
        Thread.sleep(Duration.ofSeconds(1));
		System.out.println("Inside findUser");
		throw new Exception("Testing in findUser");
	}
	public static Integer fetchOrder() {
        //Thread.sleep(Duration.ofSeconds(1));
		System.out.println("Inside fetchOrder");
		return 11;
	}
	
	public static Boolean testFailureConcurrent() {
		try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
	        Future<String>  user  = scope.fork(() -> findUser());
	        Future<Integer> order = scope.fork(() -> fetchOrder());

	        scope.join();           // Join both forks
	        scope.throwIfFailed();  // ... and propagate errors

	        // Here, both forks have succeeded, so compose their results
	        return true;
	    } catch (Exception ex) {
	    	System.out.println("Caught exception in testFailureConcurrent method - " + ex.getMessage());
	    	return false;
	    }
	}
	
	public static Boolean testSuccessConcurrent() {
		try (var scope = new StructuredTaskScope.ShutdownOnSuccess()) {
	        Future<Integer> order = scope.fork(() -> fetchOrder());
	        Future<String>  user  = scope.fork(() -> findUser());

	        scope.join();           // Join both forks

	        // Here, both forks have succeeded, so compose their results
	        return true;
	    } catch (Exception ex) {
	    	System.out.println("Caught exception in testSuccessConcurrent method - " + ex.getMessage());
	    	return false;
	    }
	}

	public static void main(String[] args) {
		System.out.println("Started at");
		System.out.println(new Date(System.currentTimeMillis()));
		System.out.println(testFailureConcurrent());
		System.out.println(testSuccessConcurrent());
		System.out.println("Ended at");
		System.out.println(new Date(System.currentTimeMillis()));
	}
}



// javac --release 20 --enable-preview --add-modules jdk.incubator.concurrent StructuredConcurrent.java
// java --enable-preview --add-modules jdk.incubator.concurrent StructuredConcurrent
