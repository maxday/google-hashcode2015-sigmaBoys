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
		List<MovVector> movVectorList = new ArrayList<MovVector>();
		
		
		int r = 75;
		int c = 300;
		int a = 8;
		
		int l = 2250;
		int v = 7;
		int b = 53;
		int t = 400;
		
		//departure cell of baloons
		//System.out.println(lines.get(2));
		MovVector[][][] bigTable = new MovVector[a][r][c];
		
		//parsing Target cells
		for(int i=0; i<l; ++i) {
			String[] split = lines.get(i+3).split(" ");
			TargetCell targetCell = new TargetCell(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
			targetCellList.add(targetCell);
			
		}
		
		
		//parsing sections
		for(int i=0; i<a; ++i)  {
			
			//R subsections
			for(int j=0; j<r; ++j) {
				
				String[] numbers = lines.get(i*r+3+l+j).split(" ");

				for(int k=0; k<numbers.length; k = k+2) {
					Integer deltaR = Integer.parseInt(numbers[k]);
					Integer deltaC = Integer.parseInt(numbers[k+1]);
					
					MovVector movVector = new MovVector(i, deltaR, deltaC);
					bigTable[i][j][k/2] = movVector;
					
					
				}
				
			}
			
		}
		
		
		System.out.println("targetCellList size = " + targetCellList.size());
		System.out.println("movVectorList size = " + movVectorList.size());
		
		System.out.println("nb item dans alt 0 = " + getArrayFromAltitude(movVectorList, r, c, 0));
		
		
		
		System.out.println(bigTable[7][74][0]);
		
	
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
	
	public static String getArrayFromAltitude(List<MovVector> movVectorList, int nbColonne, int nbLigne, int altitude) {
		MovVector[][] result = new MovVector[nbLigne][nbColonne];
		int i = 0;
		int j = 0;
		for(MovVector movV : movVectorList) {
			if(movV.alt == altitude) {
				result[i][j] = movV;
				j++;
			}
			if(j==nbColonne) {
				++i;
				j=0;
			}
		}
		System.out.println(result[1][8]);
		return "";
	}
	
	
	
	/*
	PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
	writer.println("The first line");
	writer.println("The second line");
	writer.close();
	
	zip -r archive_name.zip src/ 
	*/
}
