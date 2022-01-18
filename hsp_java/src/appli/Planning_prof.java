package appli;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import com.dbconnexion.Database;

import controller.Global;

public class Planning_prof extends Global
{

	protected Shell shell;
	private Button btnClasse;
	private Table tableMardi;
	private Table tableMercredi;
	private Table tableLundi;
	private Table tableJeudi;
	private Table tableVendredi;


	/**
	* @wbp.parser.entryPoint
	*/
	public void open() throws Exception
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


	protected void createContents() throws SQLException
	{
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		shell.setSize(1066, 551);
		shell.setText("Ma Gestion | Planning");
		shell.setLayout(new org.eclipse.swt.layout.FormLayout());

		btnClasse = new Button(shell, SWT.NONE);
		btnClasse.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		org.eclipse.swt.layout.FormData fd_btnClasse = new org.eclipse.swt.layout.FormData();
		fd_btnClasse.left = new FormAttachment(0, 10);
		btnClasse.setLayoutData(fd_btnClasse);
		btnClasse.setText("Liste des \u00E9l\u00E8ves");
		btnClasse.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				shell.close();
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

		Label lblBonjour = new Label(shell, SWT.NONE);
		lblBonjour.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblBonjour.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 12, SWT.NORMAL));
		org.eclipse.swt.layout.FormData fd_lblBonjour = new org.eclipse.swt.layout.FormData();
		fd_lblBonjour.right = new FormAttachment(100, -503);
		fd_lblBonjour.left = new FormAttachment(0, 494);
		lblBonjour.setLayoutData(fd_lblBonjour);
		lblBonjour.setText("Bonjour");

		tableMardi = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		FormData fd_tableMardi = new FormData();
		fd_tableMardi.bottom = new FormAttachment(100, -31);
		tableMardi.setLayoutData(fd_tableMardi);
		tableMardi.setHeaderVisible(true);
		tableMardi.setLinesVisible(true);

		tableMercredi = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		tableMercredi.setBackground(SWTResourceManager.getColor(255, 239, 213));
		fd_tableMardi.right = new FormAttachment(tableMercredi, -6);
		FormData fd_tableMercredi = new FormData();
		fd_tableMercredi.bottom = new FormAttachment(100, -31);
		fd_tableMercredi.left = new FormAttachment(0, 542);
		tableMercredi.setLayoutData(fd_tableMercredi);
		tableMercredi.setHeaderVisible(true);
		tableMercredi.setLinesVisible(true);

		tableLundi = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		tableLundi.setHeaderBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		tableLundi.setForeground(SWTResourceManager.getColor(0, 0, 0));
		tableLundi.setBackground(SWTResourceManager.getColor(255, 239, 213));
		fd_tableMardi.left = new FormAttachment(tableLundi, 6);
		FormData fd_tableLundi = new FormData();
		fd_tableLundi.bottom = new FormAttachment(100, -31);
		fd_tableLundi.right = new FormAttachment(100, -650);
		tableLundi.setLayoutData(fd_tableLundi);
		tableLundi.setHeaderVisible(true);
		tableLundi.setLinesVisible(true);

		tableJeudi = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		fd_tableMercredi.right = new FormAttachment(100, -366);
		FormData fd_tableJeudi = new FormData();
		fd_tableJeudi.bottom = new FormAttachment(100, -31);
		fd_tableJeudi.left = new FormAttachment(tableMercredi, 6);
		tableJeudi.setLayoutData(fd_tableJeudi);
		tableJeudi.setHeaderVisible(true);
		tableJeudi.setLinesVisible(true);

		tableVendredi = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		tableVendredi.setBackground(SWTResourceManager.getColor(255, 239, 213));
		fd_tableJeudi.right = new FormAttachment(tableVendredi, -6);
		FormData fd_tableVendredi = new FormData();
		fd_tableVendredi.bottom = new FormAttachment(100, -31);
		fd_tableVendredi.left = new FormAttachment(0, 826);
		fd_tableVendredi.right = new FormAttachment(100, -82);
		tableVendredi.setLayoutData(fd_tableVendredi);
		tableVendredi.setHeaderVisible(true);
		tableVendredi.setLinesVisible(true);

		Label lblh = new Label(shell, SWT.NONE);
		lblh.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblh.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		lblh.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		fd_btnClasse.right = new FormAttachment(lblh, -63);
		fd_tableLundi.left = new FormAttachment(0, 258);
		FormData fd_lblh = new FormData();
		fd_lblh.right = new FormAttachment(tableLundi, -6);
		lblh.setLayoutData(fd_lblh);
		lblh.setText("8h");

		Label lblh_1 = new Label(shell, SWT.NONE);
		lblh_1.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblh_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		lblh_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		fd_btnClasse.top = new FormAttachment(lblh_1, -5, SWT.TOP);
		fd_lblh.bottom = new FormAttachment(100, -342);
		FormData fd_lblh_1 = new FormData();
		fd_lblh_1.top = new FormAttachment(lblh);
		fd_lblh_1.right = new FormAttachment(tableLundi, -6);
		lblh_1.setLayoutData(fd_lblh_1);
		lblh_1.setText("9h");

		Label lblh_2 = new Label(shell, SWT.NONE);
		lblh_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		lblh_2.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblh_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		FormData fd_lblh_2 = new FormData();
		fd_lblh_2.top = new FormAttachment(lblh_1, 5);
		fd_lblh_2.right = new FormAttachment(tableLundi, -6);
		lblh_2.setLayoutData(fd_lblh_2);
		lblh_2.setText("10h");

		Label lblh_3 = new Label(shell, SWT.NONE);
		lblh_3.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblh_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		lblh_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		FormData fd_lblh_3 = new FormData();
		fd_lblh_3.top = new FormAttachment(lblh_2, 6);
		fd_lblh_3.right = new FormAttachment(tableLundi, -6);
		lblh_3.setLayoutData(fd_lblh_3);
		lblh_3.setText("11h");

		Label lblh_4 = new Label(shell, SWT.NONE);
		lblh_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		lblh_4.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblh_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		FormData fd_lblh_4 = new FormData();
		fd_lblh_4.top = new FormAttachment(lblh_2, 32);
		fd_lblh_4.right = new FormAttachment(tableLundi, -6);
		lblh_4.setLayoutData(fd_lblh_4);
		lblh_4.setText("12h");

		Label lblh_5 = new Label(shell, SWT.NONE);
		lblh_5.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblh_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		lblh_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		FormData fd_lblh_5 = new FormData();
		fd_lblh_5.right = new FormAttachment(tableLundi, -6);
		lblh_5.setLayoutData(fd_lblh_5);
		lblh_5.setText("14h");

		Label lblh_6 = new Label(shell, SWT.NONE);
		lblh_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		lblh_6.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblh_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		FormData fd_lblh_6 = new FormData();
		fd_lblh_6.top = new FormAttachment(lblh_5, 2);
		fd_lblh_6.right = new FormAttachment(tableLundi, -6);
		lblh_6.setLayoutData(fd_lblh_6);
		lblh_6.setText("15h");

		Label lblh_7 = new Label(shell, SWT.NONE);
		lblh_7.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		lblh_7.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblh_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		fd_lblh_6.bottom = new FormAttachment(lblh_7, -6);
		FormData fd_lblh_7 = new FormData();
		fd_lblh_7.right = new FormAttachment(tableLundi, -6);
		lblh_7.setLayoutData(fd_lblh_7);
		lblh_7.setText("16h");

		Label lblh_8 = new Label(shell, SWT.NONE);
		lblh_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		lblh_8.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblh_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		fd_lblh_7.bottom = new FormAttachment(lblh_8, -6);
		FormData fd_lblh_8 = new FormData();
		fd_lblh_8.top = new FormAttachment(0, 387);
		fd_lblh_8.right = new FormAttachment(tableLundi, -6);
		lblh_8.setLayoutData(fd_lblh_8);
		lblh_8.setText("17h");

		Label lblLundi = new Label(shell, SWT.NONE);
		lblLundi.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblLundi.setAlignment(SWT.CENTER);
		fd_tableLundi.top = new FormAttachment(0, 126);

		TableItem tableItem_1_1 = new TableItem(tableLundi, SWT.NONE);


		TableItem tableItem_1_2 = new TableItem(tableLundi, SWT.NONE);

		TableItem tableItem_1_3 = new TableItem(tableLundi, SWT.NONE);

		TableItem tableItem_1_4 = new TableItem(tableLundi, SWT.NONE);

		TableItem tableItem_1_5 = new TableItem(tableLundi, SWT.NONE);

		TableItem tableItem_1_pause = new TableItem(tableLundi, SWT.NONE);
		tableItem_1_pause.setText("Pause               ");

		TableItem tableItem_1_6 = new TableItem(tableLundi, SWT.NONE);

		TableItem tableItem_1_7 = new TableItem(tableLundi, SWT.NONE);

		TableItem tableItem_1_8 = new TableItem(tableLundi, SWT.NONE);

		TableItem tableItem_1_9 = new TableItem(tableLundi, SWT.NONE);
		lblLundi.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblLundi.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		FormData fd_lblLundi = new FormData();
		fd_lblLundi.left = new FormAttachment(0, 296);
		fd_lblLundi.bottom = new FormAttachment(tableLundi, -6);
		lblLundi.setLayoutData(fd_lblLundi);
		lblLundi.setText("Lundi");

		Label lblMardi = new Label(shell, SWT.NONE);
		lblMardi.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblMardi.setAlignment(SWT.CENTER);
		fd_lblLundi.right = new FormAttachment(100, -688);
		fd_tableMardi.top = new FormAttachment(0, 126);

		TableItem tableItem_2_1 = new TableItem(tableMardi, 0);

		TableItem tableItem_2_2 = new TableItem(tableMardi, 0);

		TableItem tableItem_2_3 = new TableItem(tableMardi, 0);

		TableItem tableItem_2_4 = new TableItem(tableMardi, 0);

		TableItem tableItem_2_5 = new TableItem(tableMardi, 0);

		TableItem tableItem_2_pause = new TableItem(tableMardi, 0);
		tableItem_2_pause.setText("Pause               ");

		TableItem tableItem_2_6 = new TableItem(tableMardi, 0);

		TableItem tableItem_2_7 = new TableItem(tableMardi, 0);

		TableItem tableItem_2_8 = new TableItem(tableMardi, 0);

		TableItem tableItem_2_9 = new TableItem(tableMardi, 0);
		lblMardi.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblMardi.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		FormData fd_lblMardi = new FormData();
		fd_lblMardi.left = new FormAttachment(0, 434);
		fd_lblMardi.bottom = new FormAttachment(tableMardi, -6);
		lblMardi.setLayoutData(fd_lblMardi);
		lblMardi.setText("Mardi");

		Label lblMercredi = new Label(shell, SWT.NONE);
		fd_lblMardi.right = new FormAttachment(100, -542);
		lblMercredi.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblMercredi.setAlignment(SWT.CENTER);
		fd_tableMercredi.top = new FormAttachment(0, 126);

		TableItem tableItem_3_1 = new TableItem(tableMercredi, 0);

		TableItem tableItem_3_2 = new TableItem(tableMercredi, 0);

		TableItem tableItem_3_3 = new TableItem(tableMercredi, 0);

		TableItem tableItem_3_4 = new TableItem(tableMercredi, 0);

		TableItem tableItem_3_5 = new TableItem(tableMercredi, 0);

		TableItem tableItem_3_pause = new TableItem(tableMercredi, 0);
		tableItem_3_pause.setText("Pause               ");

		TableItem tableItem_3_6 = new TableItem(tableMercredi, 0);

		TableItem tableItem_3_7 = new TableItem(tableMercredi, 0);

		TableItem tableItem_3_8 = new TableItem(tableMercredi, 0);

		TableItem tableItem_3_9 = new TableItem(tableMercredi, 0);
		lblMercredi.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblMercredi.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		FormData fd_lblMercredi = new FormData();
		fd_lblMercredi.right = new FormAttachment(lblMardi, 140, SWT.RIGHT);
		fd_lblMercredi.left = new FormAttachment(0, 574);
		fd_lblMercredi.bottom = new FormAttachment(tableMercredi, -6);
		lblMercredi.setLayoutData(fd_lblMercredi);
		lblMercredi.setText("Mercredi");

		Label lblJeudi = new Label(shell, SWT.NONE);
		lblJeudi.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		fd_tableJeudi.top = new FormAttachment(lblJeudi, 6);
		lblJeudi.setAlignment(SWT.CENTER);

		TableItem tableItem_4_1 = new TableItem(tableJeudi, 0);

		TableItem tableItem_4_2 = new TableItem(tableJeudi, 0);

		TableItem tableItem_4_3 = new TableItem(tableJeudi, 0);

		TableItem tableItem_4_4 = new TableItem(tableJeudi, 0);

		TableItem tableItem_4_5 = new TableItem(tableJeudi, 0);

		TableItem tableItem_4_pause = new TableItem(tableJeudi, 0);
		tableItem_4_pause.setText("Pause               ");

		TableItem tableItem_4_6 = new TableItem(tableJeudi, 0);

		TableItem tableItem_4_7 = new TableItem(tableJeudi, 0);

		TableItem tableItem_4_8 = new TableItem(tableJeudi, 0);

		TableItem tableItem_4_9 = new TableItem(tableJeudi, 0);
		lblJeudi.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblJeudi.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		FormData fd_lblJeudi = new FormData();
		fd_lblJeudi.bottom = new FormAttachment(100, -396);
		fd_lblJeudi.left = new FormAttachment(lblMercredi, 68);
		lblJeudi.setLayoutData(fd_lblJeudi);
		lblJeudi.setText("Jeudi");

		Label lblVendredi = new Label(shell, SWT.NONE);
		lblVendredi.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblVendredi.setAlignment(SWT.CENTER);
		fd_tableVendredi.top = new FormAttachment(lblVendredi, 6);
		fd_lblJeudi.right = new FormAttachment(100, -265);

		TableItem tableItem_5_1 = new TableItem(tableVendredi, 0);

		TableItem tableItem_5_2 = new TableItem(tableVendredi, 0);

		TableItem tableItem_5_3 = new TableItem(tableVendredi, 0);

		TableItem tableItem_5_4 = new TableItem(tableVendredi, 0);

		TableItem tableItem_5_5 = new TableItem(tableVendredi, 0);

		TableItem tableItem_5_pause = new TableItem(tableVendredi, 0);
		tableItem_5_pause.setText("Pause               ");

		TableItem tableItem_5_6 = new TableItem(tableVendredi, 0);

		TableItem tableItem_5_7 = new TableItem(tableVendredi, 0);

		TableItem tableItem_5_8 = new TableItem(tableVendredi, 0);

		TableItem tableItem_5_9 = new TableItem(tableVendredi, 0);
		lblVendredi.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		lblVendredi.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		FormData fd_lblVendredi = new FormData();
		fd_lblVendredi.bottom = new FormAttachment(100, -396);
		fd_lblVendredi.right = new FormAttachment(lblJeudi, 148, SWT.RIGHT);
		fd_lblVendredi.left = new FormAttachment(lblJeudi, 62);
		lblVendredi.setLayoutData(fd_lblVendredi);
		lblVendredi.setText("Vendredi");

		Label lblNom = new Label(shell, SWT.NONE);
		lblNom.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblNom.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 12, SWT.NORMAL));
		FormData fd_lblNom = new FormData();
		fd_lblNom.right = new FormAttachment(100, -366);
		fd_lblNom.left = new FormAttachment(lblBonjour, 19);
		lblNom.setLayoutData(fd_lblNom);
		lblNom.setText(Globnom);
		ArrayList<ArrayList<TableItem>> planning = new ArrayList<ArrayList<TableItem>>();
		ArrayList<TableItem> planning_lundi = new ArrayList<TableItem>();
		planning_lundi.add(tableItem_1_1);
		planning_lundi.add(tableItem_1_2);
		planning_lundi.add(tableItem_1_3);
		planning_lundi.add(tableItem_1_4);
		planning_lundi.add(tableItem_1_5);
		planning_lundi.add(tableItem_1_6);
		planning_lundi.add(tableItem_1_7);
		planning_lundi.add(tableItem_1_8);
		planning_lundi.add(tableItem_1_9);
		ArrayList<TableItem> planning_mardi = new ArrayList<TableItem>();
		planning_mardi.add(tableItem_2_1);
		planning_mardi.add(tableItem_2_2);
		planning_mardi.add(tableItem_2_3);
		planning_mardi.add(tableItem_2_4);
		planning_mardi.add(tableItem_2_5);
		planning_mardi.add(tableItem_2_6);
		planning_mardi.add(tableItem_2_7);
		planning_mardi.add(tableItem_2_8);
		planning_mardi.add(tableItem_2_9);
		ArrayList<TableItem> planning_mercredi = new ArrayList<TableItem>();
		planning_mercredi.add(tableItem_3_1);
		planning_mercredi.add(tableItem_3_2);
		planning_mercredi.add(tableItem_3_3);
		planning_mercredi.add(tableItem_3_4);
		planning_mercredi.add(tableItem_3_5);
		planning_mercredi.add(tableItem_3_6);
		planning_mercredi.add(tableItem_3_7);
		planning_mercredi.add(tableItem_3_8);
		planning_mercredi.add(tableItem_3_9);
		ArrayList<TableItem> planning_jeudi = new ArrayList<TableItem>();
		planning_jeudi.add(tableItem_4_1);
		planning_jeudi.add(tableItem_4_2);
		planning_jeudi.add(tableItem_4_3);
		planning_jeudi.add(tableItem_4_4);
		planning_jeudi.add(tableItem_4_5);
		planning_jeudi.add(tableItem_4_6);
		planning_jeudi.add(tableItem_4_7);
		planning_jeudi.add(tableItem_4_8);
		planning_jeudi.add(tableItem_4_9);
		ArrayList<TableItem> planning_vendredi = new ArrayList<TableItem>();
		planning_vendredi.add(tableItem_5_1);
		planning_vendredi.add(tableItem_5_2);
		planning_vendredi.add(tableItem_5_3);
		planning_vendredi.add(tableItem_5_4);
		planning_vendredi.add(tableItem_5_5);
		planning_vendredi.add(tableItem_5_6);
		planning_vendredi.add(tableItem_5_7);
		planning_vendredi.add(tableItem_5_8);
		planning_vendredi.add(tableItem_5_9);

		Label lblh_9 = new Label(shell, SWT.NONE);
		lblh_9.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		lblh_9.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblh_9.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		fd_lblh_5.top = new FormAttachment(lblh_9, 6);
		FormData fd_lblh_9 = new FormData();
		fd_lblh_9.top = new FormAttachment(lblh_4, 2);
		fd_lblh_9.right = new FormAttachment(tableLundi, -6);
		lblh_9.setLayoutData(fd_lblh_9);
		lblh_9.setText("13h");


		btnClasse.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				shell.close();
				try
				{
					Modifier_prof modif_prof = new Modifier_prof();
					modif_prof.open();
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});

		Button btnModifier = new Button(shell, SWT.NONE);
		btnModifier.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		FormData fd_btnModifier = new FormData();
		fd_btnModifier.right = new FormAttachment(btnClasse, 165);
		fd_btnModifier.top = new FormAttachment(lblh_3, -2, SWT.TOP);
		fd_btnModifier.left = new FormAttachment(btnClasse, 0, SWT.LEFT);
		btnModifier.setLayoutData(fd_btnModifier);
		btnModifier.setText("Modifier votre profil");

		Button btnDeconnexion = new Button(shell, SWT.NONE);
		btnDeconnexion.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				shell.close();
				try
				{
					Connexion window = new Connexion();
					window.open();
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		btnDeconnexion.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		btnDeconnexion.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		FormData fd_btnDeconnexion = new FormData();
		fd_btnDeconnexion.bottom = new FormAttachment(tableMardi, 0, SWT.BOTTOM);
		fd_btnDeconnexion.left = new FormAttachment(btnClasse, 0, SWT.LEFT);
		btnDeconnexion.setLayoutData(fd_btnDeconnexion);
		btnDeconnexion.setText("Deconnexion");

		Label lblF = new Label(shell, SWT.NONE);
		fd_lblNom.top = new FormAttachment(lblF, 20);
		fd_lblBonjour.top = new FormAttachment(lblF, 20);
		lblF.setText("Ma Gestion");
		lblF.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		lblF.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.BOLD));
		lblF.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblF.setAlignment(SWT.CENTER);
		FormData fd_lblF = new FormData();
		fd_lblF.top = new FormAttachment(0, 10);
		fd_lblF.right = new FormAttachment(100, -446);
		lblF.setLayoutData(fd_lblF);

		planning.add(planning_lundi);
		planning.add(planning_mardi);
		planning.add(planning_mercredi);
		planning.add(planning_jeudi);
		planning.add(planning_vendredi);

		Database db = new Database();
		Connection cnx = db.DbConnexion();
		String requete = "Select * from utilisateur inner join planning on utilisateur.id = id_professeur inner join classe on planning.id_classe = classe.id where identifiant = '"+Globidentifiant+"'";
		ResultSet resultat = db.Request(cnx, requete);
		while(resultat.next())
		{
			int jour = resultat.getInt("id_jour") - 1;
			int heure = resultat.getInt("id_heure") - 1;
			String classe = resultat.getString("libelle");
			planning.get(jour).get(heure).setText(classe);

		}

		btnModifier.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				shell.close();
				try
				{
					Modifier_prof modif_prof = new Modifier_prof();
					modif_prof.open();
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});

	}
}
