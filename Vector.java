
import jdk.incubator.vector.VectorSpecies;
import jdk.incubator.vector.FloatVector;

public class Vector {
	
	static final VectorSpecies<Float> SPECIES = FloatVector.SPECIES_PREFERRED;

	static void vectorComputation(float[] a, float[] b, float[] c) {
	    for (int i = 0; i < a.length; i += SPECIES.length()) {
	        // VectorMask<Float>  m;
	        var m = SPECIES.indexInRange(i, a.length);
	        // FloatVector va, vb, vc;
	        var va = FloatVector.fromArray(SPECIES, a, i, m);
	        var vb = FloatVector.fromArray(SPECIES, b, i, m);
	        var vc = va.mul(va)
	                   .add(vb.mul(vb))
	                   .neg();
	        vc.intoArray(c, i, m);
	        System.out.println(vc);
	    }
	}

	public static void main(String[] args) {
		float a[] = {1.2f, 5f, 1.05f, 56.36f};
		float b[] = {2.2f, 3f, 2.05f, 57.36f};
		float c[] = {0f, 0f, 0f, 0f};
		vectorComputation(a, b, c);
		System.out.println("Completed");
		
	}

}




// javac --release 20 --enable-preview --add-modules jdk.incubator.vector Vector.java
// java --enable-preview --add-modules jdk.incubator.vector Vector 