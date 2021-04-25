import java.io.File;
import java.io.FileReader;
import java.io.IOException;
//import java.io.Reader;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;
public class Page implements Serializable{
	int id ;
	int maxSize=200;
	int nodeSize=15;
	Vector<Hashtable <String, String>> pageData = new Vector<Hashtable <String, String>>();
	
	public Page() {}


	public Page(int ID) throws IOException{
	
		
		
		File f=new File("/DBApp/config/DBApp.properties");
		String path=f.getAbsolutePath();
		
		File configFile = new File(path);
		FileReader reader = new FileReader(configFile) ;
		
		Properties property = new Properties();
		
		property.load(reader);
		
		maxSize = Integer.parseInt(property.getProperty("MaximumRowsCountinPage")) ;
    	
		nodeSize = Integer.parseInt(property.getProperty("NodeSize")) ;
    	
		reader.close();
    	
		this.id=ID;

}

public boolean insertIntoPage(Hashtable <String, String> entry) {
	
	if(pageData.size()==this.maxSize)
		return false ;
	else {
		this.pageData.add(entry);
		return true;
		}
		
		}
	public boolean isFull() {
		
		if (this.pageData.size()== maxSize)
			return true ;
		else
			return false;
	}	

 public boolean deleteFromPage(Hashtable <String, String>  entry) {
	 
		pageData.remove(entry);
			if(pageData.size()==0)
				return false;
			else
				return true; 
 }	 
 
 


	public static void main (String []args) {
		

		Page p=new Page();
		System.out.print(p.maxSize);
	}
	
	
	
//lmao nigga dab yeet
//zeyad was here
//mina was here


}
