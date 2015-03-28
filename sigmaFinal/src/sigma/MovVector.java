package sigma;

public class MovVector {

	int deltaR;
	int deltaC;
	int alt;
	
	public MovVector(int alt, int deltaR, int deltaC) {

		this.alt = alt;
		this.deltaR = deltaR;
		this.deltaC = deltaC;
	}

	

	@Override
	public String toString() {
		return "MovVector [alt = " + alt + ", deltaR=" + deltaR + ", deltaC=" + deltaC + "]";
	}
	
	
	
	
	
}

