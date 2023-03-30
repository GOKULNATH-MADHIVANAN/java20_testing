import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class VirtualThread {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		System.out.println(sdf.format(new Date(System.currentTimeMillis())));
		try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
		    IntStream.range(0, 10_000).forEach(i -> {
		        executor.submit(() -> {
		            Thread.sleep(Duration.ofSeconds(1));
		    		//System.out.println("Thread number - " + i);
		            return i;
		        });
		    });
		}  // executor.close() is called implicitly, and waits
		System.out.println(sdf.format(new Date(System.currentTimeMillis())));
	}
}



// javac --release 20 --enable-preview VirtualThread.java
// java --enable-preview VirtualThread