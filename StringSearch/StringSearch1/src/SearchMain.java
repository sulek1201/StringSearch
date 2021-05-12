import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SearchMain {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		ArrayList<String> statement=new ArrayList<String>();
		String showScript = "";
		int i = 0;
		try {
			InputStream is = new FileInputStream("statements.txt");
			BufferedReader buf = new BufferedReader(new InputStreamReader(is));
			
			InputStream is1 = new FileInputStream("the_truman_show_script.txt");
			BufferedReader buf1 = new BufferedReader(new InputStreamReader(is1));
			try {
				statement.add(buf.readLine());
				while(statement.get(i) != null) {
					statement.add(buf.readLine());
					i++;
				}
				showScript = buf1.readLine();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int stoplength;
		int index;
		int index2;
		String[] arr;
		String result;
		for (int j = 0; j < statement.size()-1; j++) {
			
			String[] arr1 = {statement.get(j).substring(0, statement.get(j).indexOf("_")),statement.get(j).substring(statement.get(j).indexOf("_")+3,statement.get(j).length())};
			arr = statement.get(j).split("_", 0);
			if(arr1[1].length()>arr1[0].length() ) {
				index = search(arr1[1], showScript);
				stoplength= index-(arr1[1].length());
				if(index == -1) {
					System.out.println("Statement NOT found\n");
					continue;
				}
				result = "";
				String result2 = "";
				for(int m=stoplength-1;m>0;m-- ) {
					if(showScript.charAt(m) ==' ') {
						break;
					}
					
					
					result+=String.valueOf(showScript.charAt(m));
					
				}
				for(int a=result.length()-1;a>=0;a--) {
					result2 += result.charAt(a);
				}
				System.out.println("Original statement: " + statement.get(j));
				System.out.println("Completed statement: "+ arr1[0]   + result2 +  arr1[1]+"\n");
				
				}
			else if(arr1[1].length()==0){
				
				index2 = search(arr[0], showScript);
				
				if(index2 == -1) {
					System.out.println("Statement NOT found\n");
					continue;
				}
				result = "";
				for (int k = index2; k < showScript.length(); k++) {
					if (showScript.charAt(k) == ' ') {
						break;
					}
					result += String.valueOf(showScript.charAt(k));	
				}
				
				System.out.println("Original statement: " + statement.get(j));
				System.out.println("Completed statement: " + arr[0] + result+"\n");
				
			}
			else {
				//arr = statement.get(j).split("_", 0);
			stoplength = arr[0].length()+3;
			index = search(arr[0], showScript);
			
			if(index == -1) {
				System.out.println("Statement NOT found\n");
				continue;
			}
			result = "";
			for (int k = index; k < showScript.length(); k++) {
				if (showScript.charAt(k) == statement.get(j).charAt(stoplength)) {
					break;
				}
				result += String.valueOf(showScript.charAt(k));	
			}
			
			System.out.println("Original statement: " + statement.get(j));
			System.out.println("Completed statement: " + arr[0] + result + statement.get(j).substring(stoplength,statement.get(j).length())+"\n");
			}
			}
		long stopTime = System.currentTimeMillis();
		System.out.println((double)(stopTime - startTime)/1000 + " seconds");
		
	}
	
	
	
	
	
	public static int search(String statement, String showScript) {
		for(int i=0;i<showScript.length()-statement.length();i++) {
			int j = 0;
			if(showScript.charAt(i)!=statement.charAt(0)) {
				continue;
			}
			while (j < statement.length() && (statement.charAt(j) == showScript.charAt(i+j))) {
				j++;
			}
			if(j == statement.length()) {
				return i+statement.length();
			}
		}
		return -1;
	}

}
