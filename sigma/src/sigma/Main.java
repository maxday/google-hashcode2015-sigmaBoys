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
			
			
			int current = 0;
			int offset = 0;
			
			while(true) {
				offset = checkLine(slices, theLine, i-1, current);
				current = current+offset+1;
				if(offset == 0)
					break;
			}
			
			
			
		}
		
			
			
			
		System.out.println(slices.size());
		for(Slice s : slices) {
			System.out.println(s);
		}
		
		
		
		

	}
	
	
	public static int checkLine(List<Slice> slices, String line, int nbLine, int start)  {
		int nbH = 0;
		boolean found = false;
		int j;
		for(j=start; j<start+11; ++j) {
			if(j>=59) {
				if(found) {
					slices.add(new Slice(nbLine, start, nbLine, 59));
					
				}
				return 0;
			}
				
			if(line.charAt(j) == 'H') {
				nbH++;
				if(nbH == 3)  {
					found = true;
				}
				
				if(nbH == 4) {
					slices.add(new Slice(nbLine, start, nbLine, j-1));
					return j-start-1;
				}
			
			}
			
		}
		if(found) {
			slices.add(new Slice(nbLine, start, nbLine, j));
			return j-start;
		}
		return 1;
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
