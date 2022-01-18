package appli;

import java.sql.SQLException;

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

public class Connexion
{

	protected Shell shell;
	private Text textIdentifiant;
	private Text textMotdePasse;

	/**
	* Launch the application.
	* @param args
	*/
	public static void main(String[] args)
	{
		try
		{
			Connexion window = new Connexion();
			window.open();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	* Ouverture de la fenetre.
	*/
	public void open()
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
	* fondation du contenu de la fenetre
	*/
	protected void createContents()
	{
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		shell.setSize(494, 331);
		shell.setText("Ma Gestion | Connexion");
		//Identifiant
		textIdentifiant = new Text(shell, SWT.BORDER);
		textIdentifiant.setBounds(145, 103, 215, 35);

		textMotdePasse = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		textMotdePasse.setBounds(145, 144, 215, 34);

		Button btnJeMinscris = new Button(shell, SWT.NONE);
		btnJeMinscris.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnJeMinscris.setFont(SWTResourceManager.getFont("Rockwell", 9, SWT.BOLD));

		btnJeMinscris.setBounds(174, 196, 151, 35);
		btnJeMinscris.setText("Je me connecte");

		Label lblF = new Label(shell, SWT.NONE);
		lblF.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.BOLD));
		lblF.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblF.setAlignment(SWT.CENTER);
		lblF.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		lblF.setBounds(169, 35, 135, 34);
		lblF.setText("Ma Gestion");

		Label Identifiant = new Label(shell, SWT.NONE);
		Identifiant.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		Identifiant.setBounds(37, 113, 91, 25);
		Identifiant.setText("Identifiant");

		Label MotdePasse = new Label(shell, SWT.NONE);
		MotdePasse.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		MotdePasse.setBounds(37, 153, 106, 25);
		MotdePasse.setText("Mot de passe");

		Label lblErreur = new Label(shell, SWT.NONE);
		lblErreur.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblErreur.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblErreur.setBounds(133, 243, 317, 25);
		lblErreur.setText("Erreur dans l'identifiant ou le mot de passe");
		lblErreur.setVisible(false);

		btnJeMinscris.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{

				try
				{
					Controller_connexion connexion = new Controller_connexion();
					boolean message = connexion.Connexion(textIdentifiant.getText(), textMotdePasse.getText(), shell);

					lblErreur.setVisible(message);

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
