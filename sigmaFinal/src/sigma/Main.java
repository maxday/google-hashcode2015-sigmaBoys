package sigma;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		List<String> lines = read("final_round.in");
		
		List<TargetCell> targetCellList = new ArrayList<TargetCell>(); 
		
		System.out.println(lines.get(0));
		System.out.println(lines.get(1));
		
		int r = 75;
		int c = 300;
		int a = 8;
		
		int l = 2250;
		int v = 7;
		int b = 53;
		int t = 400;
		
		//departure cell of baloons
		System.out.println(lines.get(2));
		
		
		//parsing Target cells
		for(int i=0; i<l; ++i) {
			System.out.println(lines.get(i+3));
			String[] split = lines.get(i+3).split(" ");
			TargetCell targetCell = new TargetCell(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
			System.out.println(targetCell);
			targetCellList.add(targetCell);
			
		}
		
		
		//parsing sections
		
		
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
	
	/*
	PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
	writer.println("The first line");
	writer.println("The second line");
	writer.close();
	
	zip -r archive_name.zip src/ 
	*/
}
