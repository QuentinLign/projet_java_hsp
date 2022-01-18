package appli;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.dbconnexion.Database;

import controller.Global;

import org.eclipse.swt.widgets.Combo;

public class Admin_GererCompte extends Global
{

	protected Shell shell;
	private Text textNom;
	private Text textPrenom;
	private String nom;
	private String prenom;
	private String identifiant;
	private String email;
	private String mdp;
	private String matiere;
	private String role;
	private Text textIdentifiant;
	private Text textEmail;
	private Text textMdp;
	private Text textMatiere;



	public void open() throws SQLException
	{
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
		}
	}

	/**
	* Fondation du contenu de la fenetre
	* @throws SQLException
	* @wbp.parser.entryPoint
	*/
	protected void createContents() throws SQLException
	{
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		shell.setSize(765, 559);
		shell.setText("Ajouter un Professeur");

		Label lblNom = new Label(shell, SWT.NONE);
		lblNom.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblNom.setBounds(165, 138, 81, 25);
		lblNom.setText("Nom");

		Label lblPrnom = new Label(shell, SWT.NONE);
		lblPrnom.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblPrnom.setBounds(165, 188, 81, 25);
		lblPrnom.setText("Pr\u00E9nom");

		textNom = new Text(shell, SWT.BORDER);
		textNom.setBounds(270, 135, 147, 31);

		textPrenom = new Text(shell, SWT.BORDER);
		textPrenom.setBounds(270, 185, 147, 31);

		Button btnValider = new Button(shell, SWT.NONE);
		btnValider.setBounds(471, 405, 105, 35);
		btnValider.setText("Valider");


		Label lblErreur = new Label(shell, SWT.NONE);
		lblErreur.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblErreur.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblErreur.setBounds(270, 489, 253, 25);
		lblErreur.setText("Veuiller remplir tous les champs");
		lblErreur.setVisible(false);

		Label lblSucces = new Label(shell, SWT.NONE);
		lblSucces.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblSucces.setText("Ajout r\u00E9ussi");
		lblSucces.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		lblSucces.setBounds(270, 458, 253, 25);
		lblSucces.setVisible(false);

		Label lblClasse = new Label(shell, SWT.NONE);
		lblClasse.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblClasse.setBounds(159, 234, 87, 25);
		lblClasse.setText("Identifiant");

		textIdentifiant = new Text(shell, SWT.BORDER);
		textIdentifiant.setBounds(270, 231, 147, 31);

		Label lblEmail = new Label(shell, SWT.NONE);
		lblEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblEmail.setText("Email");
		lblEmail.setBounds(165, 284, 87, 25);

		Label lblMotDePasse = new Label(shell, SWT.NONE);
		lblMotDePasse.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblMotDePasse.setText("MDP");
		lblMotDePasse.setBounds(165, 328, 81, 25);

		Label lblMatire = new Label(shell, SWT.NONE);
		lblMatire.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblMatire.setText("Mati\u00E8re");
		lblMatire.setBounds(165, 372, 81, 25);

		textEmail = new Text(shell, SWT.BORDER);
		textEmail.setBounds(270, 284, 147, 31);

		textMdp = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		textMdp.setBounds(270, 333, 147, 31);

		textMatiere = new Text(shell, SWT.BORDER);
		textMatiere.setBounds(270, 372, 147, 31);
		
		Label lblRle = new Label(shell, SWT.NONE);
		lblRle.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblRle.setText("R\u00F4le");
		lblRle.setBounds(165, 414, 81, 25);
		
		Combo comboRole = new Combo(shell, SWT.NONE);
		comboRole.setItems(new String[] {"professeur", "admin"});
		comboRole.setBounds(270, 414, 147, 20);

		Database db = new Database();
		Connection cnx = db.DbConnexion();

		btnValider.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String requete = "INSERT into utilisateur (nom, prenom, identifiant, email, mdp, matiere, role) Values ('"+textNom.getText()+"','"+textPrenom.getText()+"','"+textIdentifiant.getText()+"','"+textEmail.getText()+"','"+textMdp.getText()+"','"+textMatiere.getText()+"','"+comboRole.getItem(comboRole.getSelectionIndex())+"')";
				boolean message = db.Prepare(cnx, requete);
				lblErreur.setVisible(message);
				lblSucces.setVisible(!message);
			}
		});
		Button btnRetour = new Button(shell, SWT.NONE);
		btnRetour.setBounds(10, 10, 105, 35);
		btnRetour.setText("Retour");
		
		Label lblAjouterUnProfesseur = new Label(shell, SWT.NONE);
		lblAjouterUnProfesseur.setText("Ajouter un professeur");
		lblAjouterUnProfesseur.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblAjouterUnProfesseur.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblAjouterUnProfesseur.setBounds(292, 50, 151, 25);
		
		Label lblMenu = new Label(shell, SWT.NONE);
		lblMenu.setText("Ma Gestion");
		lblMenu.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		lblMenu.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.BOLD));
		lblMenu.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblMenu.setAlignment(SWT.CENTER);
		lblMenu.setBounds(270, 10, 151, 34);
		btnRetour.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				shell.close();
				try
				{
					Liste_Utilisateurs window = new Liste_Utilisateurs();
					window.open();
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});

	}
}
