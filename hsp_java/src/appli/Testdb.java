package appli;
import com.dbconnexion.*;

public class Testdb
{

	public static void main(String[] args)
	{
		Database db = new Database();
		System.out.println(db.DbConnexion());

	}
}
