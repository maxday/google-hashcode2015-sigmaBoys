package sigma;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
	
		
		
		List<String> l = read("/Users/admin/Desktop/google-hashcode2015-sigmaBoys/sigma/test_round.in");
		
		List<Slice> slices = new ArrayList<Slice>();
		
		
		
		
		String[] split = l.get(0).split(" ");
		int varR = Integer.parseInt(split[0]);
		int varC = Integer.parseInt(split[1]);
		int varH = Integer.parseInt(split[2]);
		int varS = Integer.parseInt(split[3]);
		//
		
		
		for(int i=1; i<varR; ++i) {
			String theLine = l.get(i);
			
			
			
			
			
			
			
			int nbH = 0;
			for(int j=0; j<12; ++j) {
				if(theLine.charAt(j) == 'H') {
					nbH++;
				}
			}
			
			if(nbH >= varH)  {  //slice OK
				slices.add(new Slice(i-1, 0, i-1, 11));
			}
			
			
			nbH=0;
			for(int j=12; j<23; ++j) {
				if(theLine.charAt(j) == 'H') {
					nbH++;
				}
			}
				
			if(nbH >= varH)  {  //slice OK
				slices.add(new Slice(i-1, 12, i-1, 23));
			}
			
			
			
			
		}
		System.out.println(slices.size());
		for(Slice s : slices) {
			System.out.println(s);
		}
		
		
		
		

	}
	
	
	public static List<String> read(final String filename) {
		
		List<String> read = new ArrayList<String>();
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			return read;
		}
	    try {
	        
	        String line = br.readLine();

	        while (line != null) {
	            read.add(line);
	            line = br.readLine();
	        }
	        br.close();
	    }
	    catch (IOException e) {
			return read;
		}
	    return read;
	}

}
