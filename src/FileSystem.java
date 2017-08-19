import java.io.*;
import java.util.Vector;


public class FileSystem {
	File f;
	public FileSystem(String name_of_file) {
		f=new File(name_of_file);
		if(!f.exists())
		{
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Vector<String> list=new Vector<String>();
		}
	}
	
	public Vector<String> readLines()
	{
		if(f.canRead())
		{
			Vector<String> list=new Vector<String>();
			
			try (BufferedReader br = new BufferedReader(new FileReader(f.getName()))) {

				String sCurrentLine;

				while ((sCurrentLine = br.readLine()) != null) {
					list.add(sCurrentLine);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return list;
		}
		else
		{
			System.out.println("error can't read file");
		}
		return null;
	}
}
