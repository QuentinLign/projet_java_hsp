package appli;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.dbconnexion.Database;

import controller.Global;

public class Admin_Classe extends Global
{

	protected Shell shlClasse;
	Database db = new Database();
	Connection cnx = db.DbConnexion();
	private Text textModClasse;
	private Text textAddClasse;




	/**
	* Ouverture de la fenetre
	 * @throws SQLException
	*/
	public void open() throws SQLException
	{
		Display display = Display.getDefault();
		createContents();
		shlClasse.open();
		shlClasse.layout();
		while (!shlClasse.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
		}
	}


	/**
	* @throws SQLException
	 * @wbp.parser.entryPoint
	*/
	protected void createContents() throws SQLException
	{

		shlClasse = new Shell();
		shlClasse.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		shlClasse.setSize(489, 547);
		shlClasse.setText("Classe");

		Label lblErreur = new Label(shlClasse, SWT.NONE);
		lblErreur.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblErreur.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblErreur.setBounds(16, 243, 253, 25);
		lblErreur.setText("Veuiller selectionner une classe");
		lblErreur.setVisible(false);

		Label lblSucces = new Label(shlClasse, SWT.NONE);
		lblSucces.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblSucces.setText("Modification r\u00E9ussi");
		lblSucces.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		lblSucces.setBounds(16, 271, 253, 25);
		lblSucces.setVisible(false);

		Label lblAjoutErreur = new Label(shlClasse, SWT.NONE);
		lblAjoutErreur.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblAjoutErreur.setVisible(false);
		lblAjoutErreur.setText("Veuiller remplir le champs");
		lblAjoutErreur.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblAjoutErreur.setBounds(210, 421, 217, 25);

		Label lblAjoutSucces = new Label(shlClasse, SWT.NONE);
		lblAjoutSucces.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		lblAjoutSucces.setBounds(210, 421, 102, 25);
		lblAjoutSucces.setText("Ajout r\u00E9ussi");
		lblAjoutSucces.setVisible(false);

		Label lblClasse = new Label(shlClasse, SWT.NONE);
		lblClasse.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblClasse.setBounds(22, 60, 81, 25);
		lblClasse.setText("Classe :");

		Combo comboClasse = new Combo(shlClasse, SWT.READ_ONLY);
		comboClasse.setBackground(SWTResourceManager.getColor(255, 255, 255));
		comboClasse.setBounds(22, 91, 211, 33);
		comboClasse.select(0);

		String requete = "Select * from classe where undeletable = 0";
		ResultSet resultat = db.Request(cnx, requete);
		ArrayList<Integer> classeList = new  ArrayList<Integer>();
		while(resultat.next())
		{

			comboClasse.add(resultat.getString("libelle"));
			classeList.add(resultat.getInt("id"));
		}

		Label lblModLibelle = new Label(shlClasse, SWT.NONE);
		lblModLibelle.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblModLibelle.setBounds(22, 158, 81, 25);
		lblModLibelle.setText("Libell\u00E9 :");

		textModClasse = new Text(shlClasse, SWT.BORDER);
		textModClasse.setBounds(109, 155, 186, 31);


		Button btnModValider = new Button(shlClasse, SWT.NONE);
		btnModValider.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(comboClasse.getSelectionIndex() > -1) {
					if(textModClasse.getText() != null && !textModClasse.getText().trim().isEmpty()) {
						String requete = "Update classe set libelle ='"+textModClasse.getText()+"' where id ='"+classeList.get(comboClasse.getSelectionIndex())+"'";
						boolean message = db.Prepare(cnx, requete);
						comboClasse.removeAll();
						classeList.clear();
						requete = "Select * from classe where undeletable = 0";
						ResultSet resultat = db.Request(cnx, requete);
						try {
							while(resultat.next()) {
								comboClasse.add(resultat.getString("libelle"));
								classeList.add(resultat.getInt("id"));
							}
							lblSucces.setVisible(true);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else {
						lblErreur.setText("Veuiller remplir le champs");
						lblErreur.setVisible(true);
						lblSucces.setVisible(false);
					}
				}
				else {
					lblErreur.setText("Veuiller selectionner une classe");
					lblErreur.setVisible(true);
					lblSucces.setVisible(false);
				}
			}
		});

		btnModValider.setBounds(22, 202, 105, 35);
		btnModValider.setText("Valider");


		Button btnSupprimer = new Button(shlClasse, SWT.NONE);
		btnSupprimer.setBounds(163, 202, 105, 35);
		btnSupprimer.setText("Supprimer");
		btnSupprimer.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(comboClasse.getSelectionIndex() > -1) {
					if(textModClasse.getText() != null && !textModClasse.getText().trim().isEmpty()) {
						String requete = "Delete from classe where id ='"+classeList.get(comboClasse.getSelectionIndex())+"'";
						boolean message = db.Prepare(cnx, requete);
						requete = "Update eleve set id_classe = 1 where id_classe ='"+classeList.get(comboClasse.getSelectionIndex())+"'";
						message = db.Prepare(cnx, requete);
						comboClasse.removeAll();
						classeList.clear();
						requete = "Select * from classe where undeletable = 0";
						ResultSet resultat = db.Request(cnx, requete);
						try {
							while(resultat.next()) {

								comboClasse.add(resultat.getString("libelle"));
								classeList.add(resultat.getInt("id"));
							}
							lblSucces.setVisible(true);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else {
						lblErreur.setText("Veuiller remplir le champs");
						lblErreur.setVisible(true);
						lblSucces.setVisible(false);
					}
				}
				else {
					lblErreur.setText("Veuiller selectionner une classe");
					lblErreur.setVisible(true);
					lblSucces.setVisible(false);
				}
			}
		});

		Label lblLAddlibelle = new Label(shlClasse, SWT.NONE);
		lblLAddlibelle.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblLAddlibelle.setText("Libell\u00E9 :");
		lblLAddlibelle.setBounds(16, 369, 81, 25);

		textAddClasse = new Text(shlClasse, SWT.BORDER);
		textAddClasse.setBounds(103, 366, 186, 31);

		Button AjouterClasse = new Button(shlClasse, SWT.NONE);
		AjouterClasse.setBounds(10, 416, 162, 35);
		AjouterClasse.setText("Ajouter une classe");

		Button btnOk = new Button(shlClasse, SWT.NONE);
		btnOk.setBounds(255, 91, 150, 35);
		btnOk.setText("Selectionner");
		
		Label lblAjouterUneClasse = new Label(shlClasse, SWT.NONE);
		lblAjouterUneClasse.setText("Ajouter une classe");
		lblAjouterUneClasse.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblAjouterUneClasse.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblAjouterUneClasse.setBounds(166, 50, 121, 25);
		
		Label lblMenu = new Label(shlClasse, SWT.NONE);
		lblMenu.setText("Ma Gestion");
		lblMenu.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		lblMenu.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.BOLD));
		lblMenu.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblMenu.setAlignment(SWT.CENTER);
		lblMenu.setBounds(144, 10, 151, 34);
		btnOk.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				if(comboClasse.getSelectionIndex() > -1) {
					textModClasse.setText(comboClasse.getItem(comboClasse.getSelectionIndex()));
					lblErreur.setVisible(false);
					lblSucces.setVisible(false);
					lblAjoutSucces.setVisible(false);
				}
				else {
					lblErreur.setVisible(true);
					lblSucces.setVisible(false);
				}

			}
		});

		AjouterClasse.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				if(textAddClasse.getText() != null && !textAddClasse.getText().trim().isEmpty()) {
					comboClasse.removeAll();
					classeList.clear();
					String requete = "INSERT into classe (libelle) Values('"+textAddClasse.getText()+"')";
					boolean message = db.Prepare(cnx, requete);

					requete = "Select * from classe where undeletable = 0";
					ResultSet resultat = db.Request(cnx, requete);
					try {
						while(resultat.next()) {
							comboClasse.add(resultat.getString("libelle"));
							classeList.add(resultat.getInt("id"));
						}
						lblAjoutSucces.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					lblAjoutErreur.setVisible(true);
					lblAjoutSucces.setVisible(false);
				}

			}
		});


	}
}
