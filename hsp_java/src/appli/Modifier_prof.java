package appli;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.dbconnexion.Database;

import controller.Global;
import org.eclipse.wb.swt.SWTResourceManager;

public class Modifier_prof extends Global
{

	protected Shell shell;
	private Text textNom;
	private Text textPrenom;
	private Text textEmail;
	private Text textMatiere;
	private String nom;
	private String prenom;
	private String email;
	private String matiere;



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
		shell.setText("Modifier son profil");

		Label lblNom = new Label(shell, SWT.NONE);
		lblNom.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblNom.setBounds(165, 121, 81, 25);
		lblNom.setText("Nom");

		Label lblPrnom = new Label(shell, SWT.NONE);
		lblPrnom.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblPrnom.setBounds(165, 173, 81, 25);
		lblPrnom.setText("Pr\u00E9nom");

		Label lblEmail = new Label(shell, SWT.NONE);
		lblEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblEmail.setBounds(165, 231, 81, 25);
		lblEmail.setText("Email");

		Label lblMatire = new Label(shell, SWT.NONE);
		lblMatire.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblMatire.setBounds(165, 284, 81, 25);
		lblMatire.setText("Mati\u00E8re");

		Label lblTitre = new Label(shell, SWT.NONE);
		lblTitre.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblTitre.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblTitre.setBounds(295, 65, 121, 25);
		lblTitre.setText("Modifier son profil");

		textNom = new Text(shell, SWT.BORDER);
		textNom.setBounds(277, 121, 147, 31);

		textPrenom = new Text(shell, SWT.BORDER);
		textPrenom.setBounds(277, 176, 147, 31);

		textEmail = new Text(shell, SWT.BORDER);
		textEmail.setBounds(277, 231, 147, 31);

		textMatiere = new Text(shell, SWT.BORDER);
		textMatiere.setBounds(277, 287, 147, 31);

		Button btnValider = new Button(shell, SWT.NONE);
		btnValider.setBounds(306, 340, 105, 35);
		btnValider.setText("Valider");

		Button btnRetour = new Button(shell, SWT.NONE);
		btnRetour.setBounds(10, 10, 105, 35);
		btnRetour.setText("Retour");

		Database db = new Database();
		Connection cnx = db.DbConnexion();
		String requete = "Select nom, prenom, email, matiere from utilisateur where identifiant = '"+Globidentifiant+"'";
		ResultSet resultat = db.Request(cnx, requete);
		while(resultat.next())
		{
			nom = resultat.getString("nom");
			prenom = resultat.getString("prenom");
			email = resultat.getString("email");
			matiere = resultat.getString("matiere");
		}
		textNom.setText(nom);
		textPrenom.setText(prenom);
		textEmail.setText(email);
		textMatiere.setText(matiere);

		Label lblErreur = new Label(shell, SWT.NONE);
		lblErreur.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblErreur.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblErreur.setBounds(277, 436, 253, 25);
		lblErreur.setText("Veuiller remplir tous les champs");
		lblErreur.setVisible(false);

		Label lblSucces = new Label(shell, SWT.NONE);
		lblSucces.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblSucces.setVisible(false);
		lblSucces.setText("Modifications enregistr\u00E9es");
		lblSucces.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		lblSucces.setBounds(277, 403, 253, 25);
		lblSucces.setVisible(false);
		
		Label lblMenu = new Label(shell, SWT.NONE);
		lblMenu.setText("Ma Gestion");
		lblMenu.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		lblMenu.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.BOLD));
		lblMenu.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblMenu.setAlignment(SWT.CENTER);
		lblMenu.setBounds(273, 25, 151, 34);

		btnValider.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String requete = "Update utilisateur set nom ='"+textNom.getText()+"', prenom ='"+textPrenom.getText()+"', email ='"+textEmail.getText()+"', matiere ='"+textMatiere.getText()+"' where identifiant = '"+Globidentifiant+"'";
				boolean message = db.Prepare(cnx, requete);
				lblErreur.setVisible(message);
				lblSucces.setVisible(!message);
				requete = "Select nom, prenom, email, matiere from utilisateur where identifiant = '"+Globidentifiant+"'";
				ResultSet resultat = db.Request(cnx, requete);
				try
				{
					while(resultat.next())
					{
						nom = resultat.getString("nom");
						prenom = resultat.getString("prenom");
						email = resultat.getString("email");
						matiere = resultat.getString("matiere");
					}
				}
				catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textNom.setText(nom);
				textPrenom.setText(prenom);
				textEmail.setText(email);
				textMatiere.setText(matiere);
			}
		});

		btnRetour.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				shell.close();
				try
				{
					Globnom = nom;
					Planning_prof window = new Planning_prof();
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
