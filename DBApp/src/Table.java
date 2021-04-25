import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class Table  implements Serializable{
	
	
	ArrayList<String> pages = new ArrayList<String>();
	Vector<Page> Table = new Vector<Page>();
	static int pageNumber =0; 
	String tableName ;
	String CK;
	Hashtable<String,String> tableColumnsNames ;
	Hashtable<String,String> tableColumnsTypes ;
	
	
	public void createPage() throws IOException{
		Page newPage = new Page(pageNumber);
		pageNumber ++;
		pages.add(pageNumber+"");
		Table.add(newPage);
		
		
	}
	public Table(String tableName,
			Hashtable<String,String> tableColumnsNames,
			Hashtable<String,String>tableColumnsTypes,
			String clustringKey) {
	
		this.tableName=tableName;
		this.tableColumnsNames = tableColumnsNames;
		this.tableColumnsTypes=tableColumnsTypes;
		this.CK=clustringKey;
	}
	public Page deSerializePage (String place) throws Exception{
		String s=place;;
		File f=new File(s);
		String path=f.getAbsolutePath();
		FileInputStream myInput = new FileInputStream(path);
		ObjectInputStream myStream = new ObjectInputStream(myInput);
		Page p = (Page) myStream.readObject();
		myStream.close();
		return p;
	}
	public void SerializePage(Page page) throws Exception{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				new File(pageNumber+"")));
		oos.writeObject(page);
		oos.close();
	}
	
	public void insert(Hashtable <String, String> htblColNameValue) throws Exception {
	/*	update();
		Page p;
		try{
			
		String s=pageNumber+"";
		File f=new File(s+".ser");
		String path=f.getAbsolutePath();
		 p = deSerializePage(path);
		 p.insertIntoPage(htblColNameValue);
		 }
		
		catch(Exception e){
			System.out.print("UNABLE TO DESERIALIZE");
			}*/
		Page p=new Page();
		pageNumber++;
	     p.insertIntoPage(htblColNameValue);
	     this.SerializePage(p);
	    
	}	

		
		
	
	public void update(){
		 Page p = null ;
	 // pageNumber++;
		try {
		
		if (Table.isEmpty()) { 	//if table is empty then create a new page and insert it.
		createPage();
		}
		else { 	//if table has >1 page then :-
			p = Table.get(pageNumber); //get that page and :-
			
			if (p.isFull()) {  //if said page is full then move to a new page, otherwise stay on the same page.
				createPage();
				Table.add(p);

			}
		}
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				new File(pageNumber+ ".ser")));
		oos.writeObject(p);
		oos.close();
		}  
	catch(IOException e) {System.out.println("UNABLE TO UPDATE!");}//end of update
	
}
	
	public void delete(String strTableName, Hashtable<String,String> htblColNameValue) throws Exception {
		Page p;
		Page target;
		Hashtable<String, String> delete_this=htblColNameValue;
		Enumeration data;
		Enumeration current;
		data =delete_this.elements();
		for(int i=0;i<Table.size();i++) {
		current=Table.get(i).pageData.elements();
		while(data.hasMoreElements()) {
			if(data.nextElement().equals(current.nextElement())) {
		         p=Table.get(pageNumber);
		         String place=""+pageNumber;
		        target= this.deSerializePage(place);
		        System.out.print(place);
		        target.deleteFromPage(delete_this);
		        Table.remove(pageNumber);
		         break;
			}
		}
		}
		
		
		
	}
	public static void main(String []args) {
		
	}
	
	}
