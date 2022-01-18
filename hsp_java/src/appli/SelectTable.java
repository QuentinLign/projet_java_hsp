package appli;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import com.dbconnexion.Database;

import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.custom.CBanner;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import controller.Controller_connexion;

import java.sql.*;

public class SelectTable
{
  public static void main(String args[])
  {
    try
    {

      Class.forName("com.mysql.jdbc.Driver");


      Connection conn = DriverManager.getConnection(
      "jdbc:mysql://localhost/lycee_java?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");

      //�tape 3: cr�er l'objet statement
      Database db = new Database();
      Connection cnx = db.DbConnexion();

      String sql = "SELECT id, nom, prenom, identifiant FROM utilisateur";
      ResultSet res = db.Request(cnx, sql);

      //�tape 5: extraire les donn�es
      while(res.next())
      {
        //R�cup�rer par nom de colonne
        int id = res.getInt("id");
        String nom = res.getString("nom");
        String prenom = res.getString("Prenom");
        String identifiant = res.getString("Identifiant");


        //Afficher les valeurs
        System.out.print("ID: " + id);
        System.out.print(", Nom: " + nom);
        System.out.print(", Prenom: " + prenom);
        System.out.print(", identifiant: " + identifiant);


      }

      //�tape 6: fermez l'objet de connexion
      conn.close();
    }
    catch(Exception e)
    {
      System.out.println(e);
    }
  }
}
