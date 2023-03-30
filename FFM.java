
import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SymbolLookup;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import java.util.Arrays;


public class FFM {
	
	static long invokeStrlen(String s) throws Throwable {
        
        try (Arena offHeap = Arena.openConfined()) {
            
            // 1. Allocate off-heap memory, and
            // 2. Dereference off-heap memory
            MemorySegment nativeString = offHeap.allocateUtf8String(s);
        
            // 3. Link and call C function
        
            // 3a. Obtain an instance of the native linker
            Linker linker = Linker.nativeLinker();
        
            // 3b. Locate the address of the C function
            SymbolLookup stdLib = linker.defaultLookup();
            MemorySegment strlen_addr = stdLib.find("strlen").get();
        
            // 3c. Create a description of the C function signature
            FunctionDescriptor strlen_sig =
                FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS);
            
            // 3d. Create a downcall handle for the C function    
            MethodHandle strlen = linker.downcallHandle(strlen_addr, strlen_sig);            
            
            // 3e. Call the C function directly from Java
            return (long)strlen.invoke(nativeString);
        } 
    }

	public static void main(String[] args) {
		try {
			System.out.println("String length for Gokul - " + invokeStrlen("Gokul"));
		} catch (Throwable ex) {
			System.out.println("Exception caught in main method - " + ex);
		}
	}
}
