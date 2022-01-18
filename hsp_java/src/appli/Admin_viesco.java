package appli;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Menu;
import com.dbconnexion.*;

import controller.Global;

public class Admin_viesco extends Global
{

	protected Shell shlListeDesElves;
	private Table table;




	/**
	* Ouverture de la fenetre
	*/
	public void open()
	{
		Display display = Display.getDefault();
		createContents();
		shlListeDesElves.open();
		shlListeDesElves.layout();
		while (!shlListeDesElves.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
		}
	}




	/**
	* @wbp.parser.entryPoint
	*/
	protected void createContents()
	{

		shlListeDesElves = new Shell();
		shlListeDesElves.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		shlListeDesElves.setSize(710, 463);
		shlListeDesElves.setText("Liste des El\u00E8ves");

		Composite composite = new Composite(shlListeDesElves, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(173, 216, 230));
		composite.setBounds(256, 108, 361, 266);


		Label Nom_Eleve = new Label(composite, SWT.NONE);
		Nom_Eleve.setForeground(SWTResourceManager.getColor(255, 255, 255));
		Nom_Eleve.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		Nom_Eleve.setText("Nom");
		Nom_Eleve.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		Nom_Eleve.setBounds(193, 85, 76, 35);

		Label Prenom_Eleve = new Label(composite, SWT.NONE);
		Prenom_Eleve.setForeground(SWTResourceManager.getColor(255, 255, 255));
		Prenom_Eleve.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		Prenom_Eleve.setText("Prenom");
		Prenom_Eleve.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		Prenom_Eleve.setBounds(193, 121, 91, 35);

		Label lblNom = new Label(composite, SWT.NONE);
		lblNom.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblNom.setText("Nom");
		lblNom.setBounds(62, 89, 67, 25);

		Label lblPrenom = new Label(composite, SWT.NONE);
		lblPrenom.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblPrenom.setText("Prenom");
		lblPrenom.setBounds(62, 125, 67, 35);

		Label lblClasse = new Label(composite, SWT.NONE);
		lblClasse.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblClasse.setBounds(62, 163, 81, 25);
		lblClasse.setText("Classe");

		Label listeeleves = new Label(shlListeDesElves, SWT.NONE);
		listeeleves.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		listeeleves.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		listeeleves.setBounds(41, 69, 220, 33);
		listeeleves.setText("Liste des \u00E9l\u00E8ves");

		table = new Table(shlListeDesElves, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(51, 104, 146, 283);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn libelleNomEleve = new TableColumn(table, SWT.NONE);
		libelleNomEleve.setWidth(100);
		libelleNomEleve.setText("Nom");

		TableItem tableItem_1 = new TableItem(table, SWT.NONE);
		tableItem_1.setText("test");

		Button btnAjouterUneAbsence = new Button(shlListeDesElves, SWT.NONE);
		btnAjouterUneAbsence.setBounds(459, 57, 186, 29);
		btnAjouterUneAbsence.setText("Modifier votre profil");
		
		Label lblListeDeslves = new Label(shlListeDesElves, SWT.NONE);
		lblListeDeslves.setText("Liste des \u00E9l\u00E8ves");
		lblListeDeslves.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblListeDeslves.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblListeDeslves.setBounds(286, 50, 121, 25);
		
		Label lblMenu = new Label(shlListeDesElves, SWT.NONE);
		lblMenu.setText("Ma Gestion");
		lblMenu.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		lblMenu.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.BOLD));
		lblMenu.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblMenu.setAlignment(SWT.CENTER);
		lblMenu.setBounds(256, 10, 151, 34);




		Database db = new Database();
		Connection cnx = db.DbConnexion();

		String sql = "SELECT * FROM eleve";
		ResultSet res = db.Request(cnx, sql);


		//�tape 5: extraire les donnees
		try
		{
			while(res.next())
			{
				//Recuperer par nom de colonne

				String nom = res.getString("nom");
				String prenom = res.getString("prenom");


				Nom_Eleve.setText(nom);
				Prenom_Eleve.setText(prenom);
				tableItem_1.setText(nom);


				//Etape 6: fermez l'objet de connexion

			}
			btnAjouterUneAbsence.addSelectionListener(new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent e)
				{
					shlListeDesElves.close();
					try
					{
						Form_Viesco form = new Form_Viesco();
						form.open();
					}
					catch (Exception e1)
					{
						e1.printStackTrace();
					}
				}
			});

		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
