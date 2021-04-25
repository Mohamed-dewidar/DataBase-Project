//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.FileReader;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.util.ArrayList;
import java.util.Hashtable;
//import java.util.Vector;

public class DBApp {
	static Hashtable<String, Table> tablelist = new Hashtable<String, Table>();
	// a hashtable of all indecies or smth id on't know how this will work
	static Hashtable<String,BPlusTree> Indexlist = new Hashtable<String,BPlusTree>();
	public void createTable(String tableName,
			Hashtable<String,String> tableColumnsNames,
			Hashtable<String,String>tableColumnsTypes,
			String clustringKey) {
		
			Table table = new Table (tableName , tableColumnsNames , tableColumnsTypes , clustringKey);
			tablelist.put(tableName, table);
	}
	
	
	public void insertIntoTable(String tablename,Hashtable<String,String> htblColNameValue) throws Exception{
		tablelist.get(tablename).insert(htblColNameValue);
	}
	
	
	public void deleteFromTable(String strTableName,Hashtable<String,String> htblColNameValue) throws Exception {
		Table table=tablelist.get(strTableName);
		table.delete(strTableName, htblColNameValue);
	}
	
	public void updateTable(String tablename) throws Exception {
		tablelist.get(tablename).update();
	}
	
	// a method to create indecies given a table's name 
	
	public void createIndex(String tableName) {
		//TODO create a BPlus tree give it a name or a serial and save it in Indexlist
		//done
		BPlusTree Index = new BPlusTree(tableName) ;
	
		//TODO loop on the given table's pages and then loop on
		//the given page's pageData vector and save the reference for every data entry 
		//in the BPlusTree
	    Indexlist.put(tableName ,Index);
	}
			
	public static void main(String args[]){ 
		DBApp test = new DBApp();
		
		Hashtable<String,String> tableColumnsNames = new Hashtable<String,String>();
		Hashtable<String,String>tableColumnsTypes = new Hashtable<String,String>();
		Hashtable<String,String>inserttest = new Hashtable<String,String>();
		tableColumnsNames.put("Student","GPA");
		tableColumnsNames.put("ID","SEX");
		tableColumnsTypes.put("String","String");
		tableColumnsTypes.put("String","String");
		inserttest.put("MAHMOUD", "1.0");
		inserttest.put("1234", "MALE");
		
		//IDK WHAT IM DOING HERE BUT IM TRYING TO TEST
  test.createTable("students",tableColumnsNames,tableColumnsTypes,"1.0");
  Table table=tablelist.get("students");
  
  
 
  try {
	  //File f=new File("metadata.csv");
		//String path=f.getAbsolutePath();
		//System.out.print(path);
	//  BufferedReader br=new BufferedReader(new FileReader(path));
	//  String line=br.readLine();
	  Hashtable<String, String> data=new Hashtable<String, String>();
	 
	  test.insertIntoTable("students", inserttest);
	  Page p= table.deSerializePage(table.pageNumber+"");
	  System.out.print(p.pageData.get(0));
	 
	 
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
  
    
    } 
}
