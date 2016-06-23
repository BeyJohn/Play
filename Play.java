import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;
public class Play
{
	public static void main(String[] args) throws Exception
	{
		String name = "";
		int cur = 0
		int last = 0;
		try
		{
			Scanner read = new Scanner(new File((""+path()).substring(0, (""+path()).length()-8)+"next.dat"));
			name = read.next().replace("_", " ");
			cur = read.nextInt();
			last = read.nextInt();
			read.close();
		}
		catch(FileNotFoundException e)
		{
			name = "None"
			cur = 0;
			last = 0;
		}
		if(!name.equals("None"))
		{
			File f = new File((""+path()).substring(0,(""+path()).length()-8)+name+"\\"+new File((""+path()).substring(0,(""+path()).length()-8)+name).list()[cur-1]);
			if((""+f).endsWith("desktop.ini"))
			{
				f = new File((""+path()).substring(0,(""+path()).length()-8)+name+"\\"+new File((""+path()).substring(0,(""+path()).length()-8)+name).list()[++cur-1]);
			}
			Desktop.getDesktop().open(f);
			if(cur == last)
			{
				cur = 0;
				last = 0;
				name = "None";
			}
			else
				cur++;
		}
		else
		{
			name = JOptionPane.showInputDialog("What folder would you like to watch next?");
			cur = 1;
			last = new File((""+path()).substring(0,(""+path()).length()-8)+name).listFiles().length;
		}
		FileWriter fw = new FileWriter(new File((""+path()).substring(0, (""+path()).length()-8)+"next.dat"));
		PrintWriter pw = new PrintWriter(fw);
		pw.print(name.replace(" ", "_")+" "+cur+" "+last);
		pw.close();
		fw.close();
	}
	private static File path() throws Exception
	{
		return new File(Play.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
	}
}
