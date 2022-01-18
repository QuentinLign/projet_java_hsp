package appli;


import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Tree;

import com.dbconnexion.Database;

import controller.Global;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.browser.Browser;
import org.eclipse.wb.swt.SWTResourceManager;

public class Form_Viesco extends Global
{
	protected Shell shlAjouterVieScollaire;
	private Text Justification;
	private String nom;
	private String type;
	private String date;
	private String justification;

	/**
	 * Lancement de l'application.
	 * @param args
	 */


	/**
	 * Ouverture de la fenetre
	 * @wbp.parser.entryPoint
	 */
	public void open() throws SQLException
	{
		Display display = Display.getDefault();
		createContents();
		shlAjouterVieScollaire.open();
		shlAjouterVieScollaire.layout();
		while (!shlAjouterVieScollaire.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
		}
	}

	/**
	 * fondation du contenu de la fenetre.
	 */
	protected void createContents() throws SQLException
	{
		shlAjouterVieScollaire = new Shell();
		shlAjouterVieScollaire.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		shlAjouterVieScollaire.setSize(583, 642);
		shlAjouterVieScollaire.setText("Ajouter Vie Scolaire");

		Justification = new Text(shlAjouterVieScollaire, SWT.BORDER | SWT.V_SCROLL);
		Justification.setBounds(149, 215, 256, 125);


		Button btnNewButton = new Button(shlAjouterVieScollaire, SWT.NONE);
		btnNewButton.setBounds(168, 365, 200, 35);
		btnNewButton.setText("Ajouter");



		Combo Type = new Combo(shlAjouterVieScollaire, SWT.READ_ONLY);
		Type.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		Type.setToolTipText("");
		Type.setItems(new String[] {"Retard", "Absence", "Sanction"});
		Type.setBounds(168, 123, 200, 33);
		Type.select(0);

		Label lblType = new Label(shlAjouterVieScollaire, SWT.NONE);
		lblType.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblType.setBounds(106, 123, 56, 33);
		lblType.setText("Type");

		Label lblDate = new Label(shlAjouterVieScollaire, SWT.NONE);
		lblDate.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblDate.setBounds(106, 170, 56, 39);
		lblDate.setText("Date");

		Label lblJustification = new Label(shlAjouterVieScollaire, SWT.NONE);
		lblJustification.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblJustification.setBounds(42, 215, 101, 35);
		lblJustification.setText("Justification");

		Label lblEleve = new Label(shlAjouterVieScollaire, SWT.NONE);
		lblEleve.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblEleve.setBounds(27, 83, 485, 25);
		lblEleve.setText("Pour :");

		DateTime champDate = new DateTime(shlAjouterVieScollaire, SWT.BORDER);
		champDate.setBounds(169, 170, 199, 35);

		Button btnRetour = new Button(shlAjouterVieScollaire, SWT.NONE);
		btnRetour.setBounds(10, 10, 105, 35);
		btnRetour.setText("Retour");
		btnRetour.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				shlAjouterVieScollaire.close();
				Globideleve = null;
				try
				{
					Liste_Eleve window = new Liste_Eleve();
					window.open();
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});

		Database db = new Database();
		Connection cnx = db.DbConnexion();

		String requete = "Select nom, prenom, id_classe from eleve where id ='"+Globideleve+"'";
		ResultSet resultat = db.Request(cnx, requete);
		while(resultat.next())
		{
			String nom = resultat.getString("nom");
			String prenom = resultat.getString("prenom");
			lblEleve.setText("Pour :"+nom+" "+prenom);

		}

		//Justification.setText(justification);

		Label lblTitre = new Label(shlAjouterVieScollaire, SWT.NONE);
		lblTitre.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblTitre.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblTitre.setBounds(180, 42, 245, 35);
		lblTitre.setText("Ajouter une Absence/Sanction/Retard");
		
		Label lblMenu = new Label(shlAjouterVieScollaire, SWT.NONE);
		lblMenu.setText("Ma Gestion");
		lblMenu.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		lblMenu.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.BOLD));
		lblMenu.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblMenu.setAlignment(SWT.CENTER);
		lblMenu.setBounds(217, 10, 151, 34);



		btnNewButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{

				Database db = new Database();
				Connection cnx = db.DbConnexion();
				String requete = "INSERT into vie_scolaire (type, date, justification) Values('"+Type.getText()+"','"+champDate.getDay()+'/'+champDate.getMonth()+'/'+champDate.getYear()+"','"+Justification.getText()+"')";
				ResultSet resultat = db.Request(cnx, requete);
				try
				{
					while(resultat.next())
					{

						type = resultat.getString("type");
						date = resultat.getString("date");
						justification = resultat.getString("justification");
					}
				}
				catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}
}
