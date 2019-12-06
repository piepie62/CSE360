package gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = -6323073875493450450L;

	private JMenuBar menuBar;

	private JMenu dataMenu;
	private JMenuItem loadDataMenuItem;
	private JMenuItem appendDataMenuItem;
	private JMenuItem setBoundariesMenuItem;

	private JMenu analyticsMenu;
	private JMenuItem runAnalyticsMenuItem;
	private JMenuItem createGraphMenuItem;
	private JMenuItem createReportMenuItem;

	private JMenu helpMenu;
	private JMenuItem displayErrorsMenuItem;

	private JScrollPane dataTableScrollPane;
	private JTable dataTable;

	private JButton loadFileButton;
	private JButton appendFileButton;
	private JButton insertDataButton;
	private JButton deleteDataButton;

	private JPanel topAnalyticsPanel;

	private JPanel analyticsPanel;
	private JLabel analyticsLabel;
	private JLabel numEntriesLabel;
	private JLabel maxGradeLabel;
	private JLabel minGradeLabel;
	private JLabel medianLabel;
	private JLabel meanLabel;
	private JLabel modeLabel;

	private JPanel distributionPanel;
	private JLabel distributionLabel;
	private JLabel percentage0Label;
	private JLabel percentage10Label;
	private JLabel percentage20Label;
	private JLabel percentage30Label;
	private JLabel percentage40Label;
	private JLabel percentage50Label;
	private JLabel percentage60Label;
	private JLabel percentage70Label;
	private JLabel percentage80Label;
	private JLabel percentage90Label;
	private JPanel analyticsPanelSizePanel;

	private JSeparator graphSeparator;

	private JPanel graphPanel;
	private JTextArea graphTextArea;

	private List<Float> dataList = new ArrayList<Float>();
	String errors = "";
	String[] numbers;

	int numbers0Count = 0;
	int numbers10Count = 0;
	int numbers20Count = 0;
	int numbers30Count = 0;
	int numbers40Count = 0;
	int numbers50Count = 0;
	int numbers60Count = 0;
	int numbers70Count = 0;
	int numbers80Count = 0;
	int numbers90Count = 0;

	float numbers0Total = 0;
	float numbers10Total = 0;
	float numbers20Total = 0;
	float numbers30Total = 0;
	float numbers40Total = 0;
	float numbers50Total = 0;
	float numbers60Total = 0;
	float numbers70Total = 0;
	float numbers80Total = 0;
	float numbers90Total = 0;

	String numbers0Bar = "";
	String numbers10Bar = "";
	String numbers20Bar = "";
	String numbers30Bar = "";
	String numbers40Bar = "";
	String numbers50Bar = "";
	String numbers60Bar = "";
	String numbers70Bar = "";
	String numbers80Bar = "";
	String numbers90Bar = "";

	String allActionsPerformed = "";

	boolean firstData = true;
	boolean addedValue = false;
	float lower = 0;
	float upper = 100;
	float maxGrade = -1000000000;
	float minGrade = 1000000000;

	/**
	 * Constructor to create a MainFrame. Upon instantiation, the frame then needs
	 * to be set as visible to be shown to the user.
	 */
	public MainFrame() {
		this.initComponents();
	}

	/**
	 * Initializes the entire GUI frame. This method initializes all of the
	 * individual components that go into the frame as well as places them in
	 * position. This is a long and complex method that should be ignored unless
	 * major changes to the GUI are needed.
	 */
	private void initComponents() {
		this.dataTableScrollPane = new JScrollPane();
		this.dataTable = new JTable();
		this.loadFileButton = new JButton();
		this.appendFileButton = new JButton();
		this.insertDataButton = new JButton();
		this.deleteDataButton = new JButton();
		this.topAnalyticsPanel = new JPanel();
		this.analyticsPanel = new JPanel();
		this.analyticsLabel = new JLabel();
		this.modeLabel = new JLabel();
		this.medianLabel = new JLabel();
		this.meanLabel = new JLabel();
		this.minGradeLabel = new JLabel();
		this.maxGradeLabel = new JLabel();
		this.numEntriesLabel = new JLabel();
		this.distributionPanel = new JPanel();
		this.distributionLabel = new JLabel();
		this.percentage90Label = new JLabel();
		this.percentage80Label = new JLabel();
		this.percentage70Label = new JLabel();
		this.percentage60Label = new JLabel();
		this.percentage50Label = new JLabel();
		this.percentage40Label = new JLabel();
		this.percentage30Label = new JLabel();
		this.percentage20Label = new JLabel();
		this.percentage10Label = new JLabel();
		this.percentage0Label = new JLabel();
		this.analyticsPanelSizePanel = new JPanel();
		this.graphSeparator = new JSeparator();
		this.graphPanel = new JPanel();
		this.graphTextArea = new JTextArea();
		this.menuBar = new JMenuBar();
		this.dataMenu = new JMenu();
		this.loadDataMenuItem = new JMenuItem();
		this.appendDataMenuItem = new JMenuItem();
		this.setBoundariesMenuItem = new JMenuItem();
		this.analyticsMenu = new JMenu();
		this.runAnalyticsMenuItem = new JMenuItem();
		this.createGraphMenuItem = new JMenuItem();
		this.createReportMenuItem = new JMenuItem();
		this.helpMenu = new JMenu();
		this.displayErrorsMenuItem = new JMenuItem();

		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("Grade Analytics");
		this.setMaximumSize(new Dimension(840, 650));
		this.setMinimumSize(new Dimension(840, 650));
		this.setPreferredSize(new Dimension(840, 650));
		this.setResizable(false);
		this.setSize(new Dimension(840, 650));

		this.dataTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "A", "B", "C", "D" }) {

			private static final long serialVersionUID = 1L;
			boolean[] canEdit = new boolean[] { false, false, false, false };
			Class<?>[] types = new Class[] { Float.class, Float.class, Float.class, Float.class };

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return this.types[columnIndex];
			}

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return this.canEdit[columnIndex];
			}
		});

		this.dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.dataTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		this.dataTable.setRowSelectionAllowed(false);
		this.dataTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.dataTable.setShowGrid(true);
		this.dataTable.getTableHeader().setReorderingAllowed(false);
		this.dataTableScrollPane.setViewportView(this.dataTable);
		this.dataTable.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		if (this.dataTable.getColumnModel().getColumnCount() > 0) {
			this.dataTable.getColumnModel().getColumn(0).setResizable(false);
			this.dataTable.getColumnModel().getColumn(1).setResizable(false);
			this.dataTable.getColumnModel().getColumn(2).setResizable(false);
			this.dataTable.getColumnModel().getColumn(3).setResizable(false);
		}

		this.loadFileButton.setText("Load File");
		this.loadFileButton.setMaximumSize(new Dimension(77, 50));
		this.loadFileButton.setMinimumSize(new Dimension(77, 50));
		this.loadFileButton.setPreferredSize(new Dimension(77, 50));
		this.loadFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				MainFrame.this.loadFileButtonActionPerformed(evt);
			}
		});

		this.appendFileButton.setText("Append File");
		this.appendFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				MainFrame.this.appendFileButtonActionPerformed(evt);
			}
		});

		this.insertDataButton.setText("Insert Data");
		this.insertDataButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				MainFrame.this.insertDataButtonActionPerformed(evt);
			}
		});

		this.deleteDataButton.setText("Delete Data");
		this.deleteDataButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				MainFrame.this.deleteDataButtonActionPerformed(evt);
			}
		});

		this.topAnalyticsPanel.setMaximumSize(new Dimension(414, 186));
		this.topAnalyticsPanel.setMinimumSize(new Dimension(414, 186));
		this.topAnalyticsPanel.setRequestFocusEnabled(false);

		this.analyticsLabel.setFont(new Font("Tahoma", 0, 18)); // NOI18N
		this.analyticsLabel.setText("Analytics");

		this.modeLabel.setText("Mode:");

		this.medianLabel.setText("Median:");

		this.meanLabel.setText("Mean:");

		this.minGradeLabel.setText("Min Grade:");

		this.maxGradeLabel.setText("Max Grade:");

		this.numEntriesLabel.setText("Number of Entries:");

		GroupLayout analyticsPanelLayout = new GroupLayout(this.analyticsPanel);
		this.analyticsPanel.setLayout(analyticsPanelLayout);
		analyticsPanelLayout.setHorizontalGroup(analyticsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(analyticsPanelLayout.createSequentialGroup()
						.addGroup(analyticsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(analyticsPanelLayout.createSequentialGroup().addGap(40, 40, 40)
										.addComponent(this.analyticsLabel))
								.addComponent(this.maxGradeLabel).addComponent(this.minGradeLabel)
								.addComponent(this.meanLabel).addComponent(this.medianLabel)
								.addComponent(this.modeLabel).addComponent(this.numEntriesLabel))
						.addContainerGap(37, Short.MAX_VALUE)));
		analyticsPanelLayout.setVerticalGroup(analyticsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(analyticsPanelLayout.createSequentialGroup().addComponent(this.analyticsLabel)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(this.numEntriesLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(this.maxGradeLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(this.minGradeLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(this.meanLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.medianLabel)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.modeLabel)
						.addContainerGap()));

		this.analyticsPanel.setVisible(false);

		this.distributionLabel.setFont(new Font("Tahoma", 0, 18)); // NOI18N
		this.distributionLabel.setText("Distribution");

		GroupLayout distributionPanelLayout = new GroupLayout(this.distributionPanel);
		this.distributionPanel.setLayout(distributionPanelLayout);
		distributionPanelLayout
				.setHorizontalGroup(distributionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(GroupLayout.Alignment.TRAILING,
								distributionPanelLayout.createSequentialGroup().addContainerGap(72, Short.MAX_VALUE)
										.addComponent(this.distributionLabel).addGap(67, 67, 67))
						.addGroup(distributionPanelLayout.createSequentialGroup().addContainerGap()
								.addGroup(distributionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(this.percentage90Label).addComponent(this.percentage80Label)
										.addComponent(this.percentage70Label).addComponent(this.percentage60Label)
										.addComponent(this.percentage50Label))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addGroup(distributionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(distributionPanelLayout
												.createParallelGroup(GroupLayout.Alignment.TRAILING)
												.addComponent(this.percentage30Label)
												.addComponent(this.percentage40Label)
												.addComponent(this.percentage20Label)
												.addComponent(this.percentage10Label))
										.addComponent(this.percentage0Label))
								.addGap(35, 35, 35)));
		distributionPanelLayout
				.setVerticalGroup(distributionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(distributionPanelLayout.createSequentialGroup().addComponent(this.distributionLabel)
								.addGap(18, 18, 18)
								.addGroup(distributionPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(this.percentage90Label).addComponent(this.percentage40Label))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(distributionPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(this.percentage80Label).addComponent(this.percentage30Label))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(distributionPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(this.percentage70Label).addComponent(this.percentage20Label))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(distributionPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(this.percentage60Label, GroupLayout.PREFERRED_SIZE, 14,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(this.percentage10Label))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(distributionPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(this.percentage50Label).addComponent(this.percentage0Label))
								.addGap(0, 0, Short.MAX_VALUE)));

		this.distributionPanel.setVisible(false);

		this.analyticsPanelSizePanel.setMaximumSize(new Dimension(10, 164));
		this.analyticsPanelSizePanel.setMinimumSize(new Dimension(10, 164));
		this.analyticsPanelSizePanel.setPreferredSize(new Dimension(10, 164));

		GroupLayout analyticsPanelSizePanelLayout = new GroupLayout(this.analyticsPanelSizePanel);
		this.analyticsPanelSizePanel.setLayout(analyticsPanelSizePanelLayout);
		analyticsPanelSizePanelLayout.setHorizontalGroup(analyticsPanelSizePanelLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 10, Short.MAX_VALUE));
		analyticsPanelSizePanelLayout.setVerticalGroup(analyticsPanelSizePanelLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 164, Short.MAX_VALUE));

		GroupLayout topAnalyticsPanelLayout = new GroupLayout(this.topAnalyticsPanel);
		this.topAnalyticsPanel.setLayout(topAnalyticsPanelLayout);
		topAnalyticsPanelLayout
				.setHorizontalGroup(
						topAnalyticsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(
										topAnalyticsPanelLayout.createSequentialGroup()
												.addComponent(this.analyticsPanel, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(28, 28, 28)
												.addComponent(this.distributionPanel, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(this.analyticsPanelSizePanel, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		topAnalyticsPanelLayout.setVerticalGroup(topAnalyticsPanelLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(topAnalyticsPanelLayout.createSequentialGroup().addContainerGap()
						.addGroup(topAnalyticsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(this.analyticsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(this.distributionPanel, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(this.analyticsPanelSizePanel, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));

		this.graphTextArea.setColumns(20);
		this.graphTextArea.setFont(new Font("Monospaced", 0, 11)); // NOI18N
		this.graphTextArea.setRows(5);

		this.graphTextArea.setEnabled(false);

		GroupLayout graphPanelLayout = new GroupLayout(this.graphPanel);
		this.graphPanel.setLayout(graphPanelLayout);
		graphPanelLayout.setHorizontalGroup(graphPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(graphPanelLayout.createSequentialGroup().addGap(0, 10, Short.MAX_VALUE)
						.addComponent(this.graphTextArea, GroupLayout.PREFERRED_SIZE, 443, GroupLayout.PREFERRED_SIZE)
						.addGap(0, 11, Short.MAX_VALUE)));
		graphPanelLayout.setVerticalGroup(graphPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(graphPanelLayout.createSequentialGroup().addGap(0, 31, Short.MAX_VALUE)
						.addComponent(this.graphTextArea, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
						.addGap(0, 32, Short.MAX_VALUE)));

		this.graphTextArea.setVisible(false);

		this.dataMenu.setText("Data");

		this.loadDataMenuItem.setText("Load Data from File");
		this.loadDataMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				MainFrame.this.loadDataMenuItemActionPerformed(evt);
			}
		});
		this.dataMenu.add(this.loadDataMenuItem);

		this.appendDataMenuItem.setText("Append Data from File");
		this.appendDataMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				MainFrame.this.appendDataMenuItemActionPerformed(evt);
			}
		});
		this.dataMenu.add(this.appendDataMenuItem);

		this.setBoundariesMenuItem.setText("Set Boundaries");
		this.setBoundariesMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				MainFrame.this.setBoundariesMenuItemActionPerformed(evt);
			}
		});
		this.dataMenu.add(this.setBoundariesMenuItem);

		this.menuBar.add(this.dataMenu);

		this.analyticsMenu.setText("Analytics");

		this.runAnalyticsMenuItem.setText("Run Analytics");
		this.runAnalyticsMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				MainFrame.this.runAnalyticsMenuItemActionPerformed(evt);
			}
		});
		this.analyticsMenu.add(this.runAnalyticsMenuItem);

		this.createGraphMenuItem.setText("Create Graph");
		this.createGraphMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				MainFrame.this.createGraphMenuItemActionPerformed(evt);
			}
		});
		this.analyticsMenu.add(this.createGraphMenuItem);

		this.createReportMenuItem.setText("Create Report");
		this.createReportMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				MainFrame.this.createReportMenuItemActionPerformed(evt);
			}
		});
		this.analyticsMenu.add(this.createReportMenuItem);

		this.menuBar.add(this.analyticsMenu);

		this.helpMenu.setText("Help");

		this.displayErrorsMenuItem.setText("Display Errors");
		this.displayErrorsMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				MainFrame.this.displayErrorsMenuItemActionPerformed(evt);
			}
		});
		this.helpMenu.add(this.displayErrorsMenuItem);

		this.menuBar.add(this.helpMenu);

		this.setJMenuBar(this.menuBar);

		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(this.appendFileButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(this.deleteDataButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(this.insertDataButton, GroupLayout.PREFERRED_SIZE, 100,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(this.loadFileButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(
								this.dataTableScrollPane, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(this.graphPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(this.graphSeparator).addComponent(this.topAnalyticsPanel,
														GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
										.addContainerGap()))));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(this.topAnalyticsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(this.graphSeparator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.graphPanel,
								GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(this.loadFileButton, GroupLayout.PREFERRED_SIZE, 50,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(this.appendFileButton, GroupLayout.PREFERRED_SIZE, 50,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(this.insertDataButton, GroupLayout.PREFERRED_SIZE, 50,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(this.deleteDataButton, GroupLayout.PREFERRED_SIZE, 50,
												GroupLayout.PREFERRED_SIZE)
										.addGap(0, 399, Short.MAX_VALUE))
								.addComponent(this.dataTableScrollPane))
						.addContainerGap()));

		this.pack();
	}

	/**
	 * Action for when the "Append Data" menu item is selected. Prompts the user to
	 * type a number that will then be inserted into the dataset.
	 *
	 * @param evt the event that caused this action(Ignore)
	 */
	private void appendDataMenuItemActionPerformed(ActionEvent evt) {
		File file = this.showFileOpenDialog();
		if (file != null)
			parseFile(file);

		this.updateTable();

		setAnalytics();
		setGraph();
	}

	/**
	 * Action for when the "Append File" button is selected. Prompts the user to
	 * select a file that will then be inserted into the dataset.
	 *
	 * @param evt the event that caused this action(Ignore)
	 */
	private void appendFileButtonActionPerformed(ActionEvent evt) {
		File file = this.showFileOpenDialog();
		if (file != null)
			parseFile(file);

		this.updateTable();

		setAnalytics();
		setGraph();
	}

	/**
	 * Action for when the "Create Graph" menu item is selected. Will display and
	 * populate the graph based on the data supplied.
	 *
	 * @param evt the event that caused this action(Ignore)
	 */
	private void createGraphMenuItemActionPerformed(ActionEvent evt) {
		setGraph();
		this.graphTextArea.setVisible(!this.graphTextArea.isVisible());
	}

	/**
	 * Action for when the "Create Report" menu item is selected. It will generate a
	 * report based on all of the actions that have been performed then prompt the
	 * user to save it as a .txt file.
	 *
	 * @param evt the event that caused this action(Ignore)
	 */
	private void createReportMenuItemActionPerformed(ActionEvent evt) {
		// Generate report

		// Note: file here may or may not have the correct extension
		// Will have to add the .txt
		File saveFile = showSaveReportDialog();

		if (saveFile == null) {
			// Error handling
		} else {
			try (Writer summary = new BufferedWriter(new FileWriter(saveFile.getCanonicalPath()))) {
				summary.write(allActionsPerformed);
				summary.close();
			} catch (IOException e) {
				errors += "\n" + e.getMessage();
			}
			System.out.println(saveFile.getName());
		}
	}

	/**
	 * Action for when the "Delete Data" button is selected. It will prompt the user
	 * to enter a number to remove and remove it from the data set. TODO: Make
	 * dialog box to prompt the user for a number to delete.
	 *
	 * @param evt the event that caused this action(Ignore)
	 */
	private void deleteDataButtonActionPerformed(ActionEvent evt) {
		ValuePromptForm valueForm = new ValuePromptForm(this, true, "Enter a value to delete");

		Point dialogPosition = new Point();
		dialogPosition.setLocation(this.getLocation());
		dialogPosition.translate(this.getWidth() / 2, this.getHeight() / 2);

		valueForm.setLocation(dialogPosition);
		valueForm.setVisible(true);

		// Wait for the form to be closed
		while (valueForm.isDisplayable()) {

		}

		// Error handling: make sure the value entered is in the dataset
		float number = valueForm.getValue();

		if (!dataList.contains(number)) {
			allActionsPerformed += "Could not remove value. Table does not contain " + valueForm.getValue() + ".\n";
		} else {
			dataList.remove(number);

			numbers0Count = 0;
			numbers10Count = 0;
			numbers20Count = 0;
			numbers30Count = 0;
			numbers40Count = 0;
			numbers50Count = 0;
			numbers60Count = 0;
			numbers70Count = 0;
			numbers80Count = 0;
			numbers90Count = 0;

			numbers0Total = 0;
			numbers10Total = 0;
			numbers20Total = 0;
			numbers30Total = 0;
			numbers40Total = 0;
			numbers50Total = 0;
			numbers60Total = 0;
			numbers70Total = 0;
			numbers80Total = 0;
			numbers90Total = 0;

			numbers0Bar = "";
			numbers10Bar = "";
			numbers20Bar = "";
			numbers30Bar = "";
			numbers40Bar = "";
			numbers50Bar = "";
			numbers60Bar = "";
			numbers70Bar = "";
			numbers80Bar = "";
			numbers90Bar = "";

			for (float f : dataList)
				this.calculatePartitions(f);

			allActionsPerformed += "Value deleted: " + valueForm.getValue() + "\n";
		}

		setAnalytics();
		setGraph();

		this.updateTable();
	}

	/**
	 * Action for when the "Display Errors" menu item is selected. Will display a
	 * dialog that will list all of the errors that have occurred in the current
	 * session. TODO: Make dialog box to show the errors.
	 *
	 * @param evt the event that caused this action(Ignore)
	 */
	private void displayErrorsMenuItemActionPerformed(ActionEvent evt) {
		ErrorForm errorDisplay = new ErrorForm(this, true);

		Point dialogPosition = new Point();
		dialogPosition.setLocation(this.getLocation());
		dialogPosition.translate(this.getWidth() / 2, this.getHeight() / 2);
		errorDisplay.setErrors(errors);
		errorDisplay.setLocation(dialogPosition);
		errorDisplay.setVisible(true);
	}

	/**
	 * Action for when the "Insert Data" button is selected. Prompts the user to
	 * type a number that will then be added to the data set. If this is the first
	 * data being input, prompts the user to set the bounds of the data set.
	 *
	 * @param evt the event that caused this action(Ignore)
	 */
	private void insertDataButtonActionPerformed(ActionEvent evt) {
		if (firstData) {
			setBounds();
		}
		ValuePromptForm valueForm = new ValuePromptForm(this, true, "Enter a value to insert");

		Point dialogPosition = new Point();
		dialogPosition.setLocation(this.getLocation());
		dialogPosition.translate(this.getWidth() / 2, this.getHeight() / 2);

		valueForm.setLocation(dialogPosition);
		valueForm.setVisible(true);

		// Wait for the form to be closed
		while (valueForm.isDisplayable()) {

		}

		addValue(valueForm.getValue());
		setAnalytics();
		setGraph();

		this.updateTable();

		allActionsPerformed += "Value inserted: " + valueForm.getValue() + ".\n";
	}

	/**
	 * Action for when the "Load Data" menu item is selected. Prompts the user to
	 * open a supported file and then insert its contents into a new dataset. This
	 * will also prompt the user to set the bounds on the current dataset.
	 * 
	 * @param evt the event that caused this action(Ignore)
	 */
	private void loadDataMenuItemActionPerformed(ActionEvent evt) {
		setBounds();
		File file = this.showFileOpenDialog();
		if (file != null) {
			clearData();
			firstData = true;
			parseFile(file);
		}

		this.updateTable();

		setAnalytics();
		setGraph();
	}

	/**
	 * Action for when the "Load File" button is selected. Prompts the user to open
	 * a supported file and then insert its contents into a new dataset. This will
	 * also prompt the user to set the bounds on the current dataset.
	 * 
	 * @param evt the event that caused this action(Ignore)
	 */
	private void loadFileButtonActionPerformed(ActionEvent evt) {
		setBounds();
		File file = this.showFileOpenDialog();
		if (file != null) {
			clearData();
			firstData = true;
			parseFile(file);
		}

		this.updateTable();

		setAnalytics();
		setGraph();
	}

	/**
	 * Action for when the "Run Analytics" menu item is selected. Populates the
	 * analytics area with the information about the current dataset.
	 *
	 * @param evt the event that caused this action(Ignore)
	 */
	private void runAnalyticsMenuItemActionPerformed(ActionEvent evt) {
		setAnalytics();
		// see no point in re-hiding the panel after first showing it if the analytics
		// are run again,
		// since the values still update with them always showing
		if (!this.analyticsPanel.isVisible())
			this.analyticsPanel.setVisible(true);
		if (!this.distributionPanel.isVisible())
			this.distributionPanel.setVisible(true);
	}

	/**
	 * The action for the menu item "Set Boundaries" is selected. Will show the
	 * BoundarySetForm to the user.
	 *
	 * @param evt the event that caused this action(Ignore)
	 */
	private void setBoundariesMenuItemActionPerformed(ActionEvent evt) {
		setBounds();

		ArrayList<Float> toRemoveList = new ArrayList<>();

		for (float f : dataList) {
			if (!checkBounds(f)) {
				errors = errors + "\n" + f + " is not in range " + lower + "-" + upper;
				toRemoveList.add(f);
			}
		}

		dataList.removeAll(toRemoveList);

		this.updateTable();
	}

	/**
	 * Displays a file chooser dialog box to the user. The dialog will filter out
	 * all other files other than *.txt and *.csv. Returns null if the user cancels
	 * the dialog.
	 *
	 * @return the chosen file
	 */
	private File showFileOpenDialog() {
		JFileChooser fileChooser = new JFileChooser();

		fileChooser.setDialogTitle("Open file");
		fileChooser.setAcceptAllFileFilterUsed(false);

		// Adds the filter to ignore all other files but .txt and .csv
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.txt, *.csv", "txt", "csv"));

		int returnVal = fileChooser.showOpenDialog(this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			allActionsPerformed += "Loaded file: " + file.getName() + ".\n";
			return file;
		} else {
			System.out.println("File Open Canceled by User");
		}

		return null;
	}

	/**
	 * Displays a file chooser dialog box to the user. The dialog prompts the user
	 * to enter a file to save. The dialog will filter out all other files other
	 * than *.txt. Returns null if the user cancels the dialog.
	 *
	 * @return the chosen file
	 */
	private File showSaveReportDialog() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save file");

		fileChooser.setAcceptAllFileFilterUsed(false);

		// Adds the filter to ignore all other files but .txt
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.txt", "txt"));

		int returnVal = fileChooser.showSaveDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			System.out.println(file.getName());
			if (!file.getName().endsWith(".txt")) {
				try {
					file = new File(file.getCanonicalPath() + ".txt");
				} catch (IOException e) {
					errors += "\n" + e.getMessage();
					return null;
				}
			}
			return file;
		} else {
			System.out.println("File Open Canceled by User");
		}

		return null;
	}

	/**
	 * Adds the value to the data table and list.
	 * 
	 * @param value the number to be added
	 */
	private void addValue(float value) {
		if (checkBounds(value)) {
			this.dataList.add(value);
			if (value < this.minGrade) {
				this.minGrade = value;
			}
			if (value > this.maxGrade) {
				this.maxGrade = value;
			}
			addedValue = true;
			firstData = false;
		} else {
			addedValue = false;
			if (errors != "") {
				errors += "\n";
			}
			errors = errors + value + " is not in range " + lower + " - " + upper;
		}

		calculatePartitions(value);
	}

	/**
	 * Synchronize the table with the main data list. Sorts the list and puts the
	 * data into the the table in descending columns. Call this method after
	 * changing the data list.
	 */
	private void updateTable() {
		DefaultTableModel tableModel = (DefaultTableModel) this.dataTable.getModel();

		// Remove any null values
		while (dataList.contains(null))
			dataList.remove(null);

		// Sort the data list
		dataList.sort((Float f1, Float f2) -> {
			if (f1 > f2) {
				return -1;
			} else if (f1 < f2) {
				return 1;
			}
			return 0;
		});

		int rows = (int) Math.ceil((dataList.size() / 4.0));

		Float[][] dataVector = new Float[rows][4];

		for (int col = 0; col < 4; col++) {
			for (int row = 0; row < rows; row++) {
				// Calculate the array position
				int loc = row * 4 + col;
				if (loc < dataList.size()) {
					dataVector[row][col] = dataList.get(loc);
				}
			}
		}

		tableModel.setDataVector(dataVector, new String[] { "A", "B", "C", "D" });
	}

	/**
	 * Clears the data table and list of all values.
	 */
	private void clearData() {
		dataList.clear();
		updateTable();
		setAnalytics();
		setGraph();
	}

	/**
	 * Opens the file and adds the values to the data table.
	 * 
	 * @param file the requested file to be opened
	 */
	private void parseFile(File file) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line = in.readLine();
			if (line.length() >= 3 && line.charAt(0) == 0xEF && line.charAt(1) == 0xBB && line.charAt(2) == 0xBF) {
				line = line.substring(3);
			}
			int lineNum = 0;
			while (line != null) {
				lineNum++;
				numbers = line.split(",");
				for (String num : numbers) {
					try {
						addValue(Float.parseFloat(num));
					} catch (NumberFormatException e) {
						errors = errors + "\nInvalid number format in " + file.getName() + ": " + e.getMessage();
					}
					if (!addedValue) {
						errors += "\n\t Value located in " + file.getName() + " Line " + lineNum;
					}
				}
				line = in.readLine();
			}
			in.close();
		} catch (FileNotFoundException e) {
			errors = errors + "\n" + e.getMessage();
		} catch (IOException e) {
			errors = errors + "\n" + e.getMessage();
		}
	}

	/**
	 * Sets the upper and lower bounds.
	 */
	private void setBounds() {
		BoundarySetForm boundsDialog = new BoundarySetForm(this, true);
		Point dialogPosition = new Point();
		dialogPosition.setLocation(this.getLocation());
		dialogPosition.translate(this.getWidth() / 2, this.getHeight() / 2);

		boundsDialog.setLocation(dialogPosition);
		boundsDialog.setVisible(true);
	}

	/**
	 * Tests the value against the currently set bounds.
	 * 
	 * @param value the number that will be tested
	 * @return whether it is true or false if the value is within bounds
	 */
	private boolean checkBounds(float value) {
		return lower <= value && upper >= value;
	}

	/**
	 * Calculates the amount per percentage distribution.
	 * 
	 * @param value the data value that was taken from the source file
	 */
	private void calculatePartitions(float value) {
		if (value >= 0 && value < 10) {
			numbers0Count++;
			numbers0Total = numbers0Total + value;
		} else if (value >= 10 && value < 20) {
			numbers10Count++;
			numbers10Total = numbers10Total + value;
		} else if (value >= 20 && value < 30) {
			numbers20Count++;
			numbers20Total = numbers20Total + value;
		} else if (value >= 30 && value < 40) {
			numbers30Count++;
			numbers30Total = numbers30Total + value;
		} else if (value >= 40 && value < 50) {
			numbers40Count++;
			numbers40Total = numbers40Total + value;
		} else if (value >= 50 && value < 60) {
			numbers50Count++;
			numbers50Total = numbers50Total + value;
		} else if (value >= 60 && value < 70) {
			numbers60Count++;
			numbers60Total = numbers60Total + value;
		} else if (value >= 70 && value < 80) {
			numbers70Count++;
			numbers70Total = numbers70Total + value;
		} else if (value >= 80 && value < 90) {
			numbers80Count++;
			numbers80Total = numbers80Total + value;
		} else if (value >= 90 && value <= 100) {
			numbers90Count++;
			numbers90Total = numbers90Total + value;
		}
	}

	/**
	 * Calculates the number of hyphens needed to fill out each bar in the graph.
	 * 
	 * @param bar    the String of hyphens (empty when called)
	 * @param amount the amount of grades between a specific percentage
	 * @return the String of hyphens
	 */
	private String calculateGraphBars(String bar, int amount) {
		int hyphens = ((amount * 4) / 10) - 1;
		for (int index = 0; index < hyphens; index++) {
			bar = bar + "-";
		}

		return bar;
	}

	/**
	 * Finds the mean of the current data set.
	 *
	 * @return the mean of the data
	 */
	private float findMean() {
		float total = 0;
		for (int meanLoop = 0; meanLoop < this.dataList.size(); meanLoop++) {
			total += this.dataList.get(meanLoop);
		}
		return total / (float) this.dataList.size();
	}

	/**
	 * Finds the median of the current data set.
	 * 
	 * @return the median of the data
	 */
	private float findMedian() {
		if (dataList.isEmpty())
			return 0;

		List<Float> listCopy = new ArrayList<>(dataList);
		listCopy.sort(null);
		return listCopy.get(listCopy.size() / 2);
	}

	/**
	 * Finds the mode of the current data set.
	 * 
	 * @return the mode of the data
	 */
	private float findMode() {
		if (dataList.isEmpty())
			return 0;

		int currentCount = 1;
		int maxCount = 0;
		float mode = this.dataList.get(0);
		for (int modeLoop = 0; modeLoop < this.dataList.size(); modeLoop++) {
			for (int inModeLoop = 0; inModeLoop < this.dataList.size(); inModeLoop++) {
				// System.out.println(this.dataList.get(inModeLoop) + " == " +
				// this.dataList.get(modeLoop) + " ?");
				if ((float) this.dataList.get(inModeLoop) == (float) this.dataList.get(modeLoop)) {
					// System.out.println("yes, adding");
					currentCount++;
				}
			}
			if (currentCount > maxCount) {
				// System.out.println("found new mode");
				maxCount = currentCount;
				mode = dataList.get(modeLoop);
			}
			currentCount = 1;
		}
		return mode;
	}

	/**
	 * Sets the analytics and distribution values.
	 */
	private void setAnalytics() {
		this.numEntriesLabel.setText("Number of Entries: " + dataList.size());
		if (!firstData) {
			this.maxGradeLabel.setText("Max Grade: " + this.maxGrade);
			this.minGradeLabel.setText("Min Grade: " + this.minGrade);
		}
		this.meanLabel.setText("Mean: " + findMean());
		this.medianLabel.setText("Median: " + findMedian());
		this.modeLabel.setText("Mode: " + findMode());
		this.percentage90Label.setText("90%-100%: " + (numbers90Total / numbers90Count));
		this.percentage80Label.setText("80%-89%: " + (numbers80Total / numbers80Count));
		this.percentage70Label.setText("70%-79%: " + (numbers70Total / numbers70Count));
		this.percentage60Label.setText("60%-69%: " + (numbers60Total / numbers60Count));
		this.percentage50Label.setText("50%-59%: " + (numbers50Total / numbers50Count));
		this.percentage40Label.setText("40%-49%: " + (numbers40Total / numbers40Count));
		this.percentage30Label.setText("30%-39%: " + (numbers30Total / numbers30Count));
		this.percentage20Label.setText("20%-29%: " + (numbers20Total / numbers20Count));
		this.percentage10Label.setText("10%-19%: " + (numbers10Total / numbers10Count));
		this.percentage0Label.setText("0%-9%: " + (numbers0Total / numbers0Count));
	}

	/**
	 * Sets the graph strings.
	 */
	private void setGraph() {
		this.graphTextArea.setText("\t|\n" + "90%-100%|" + calculateGraphBars(numbers90Bar, numbers90Count) + "|\n"
				+ "\t|\n" + " 80%-89%|" + calculateGraphBars(numbers80Bar, numbers80Count) + "|\n" + "\t|\n"
				+ " 70%-79%|" + calculateGraphBars(numbers70Bar, numbers70Count) + "|\n" + "\t|\n" + " 60%-69%|"
				+ calculateGraphBars(numbers60Bar, numbers60Count) + "|\n" + "\t|\n" + " 50%-59%|"
				+ calculateGraphBars(numbers50Bar, numbers50Count) + "|\n" + "\t|\n" + " 40%-49%|"
				+ calculateGraphBars(numbers40Bar, numbers40Count) + "|\n" + "\t|\n" + " 30%-39%|"
				+ calculateGraphBars(numbers30Bar, numbers30Count) + "|\n" + "\t|\n" + " 20%-29%|"
				+ calculateGraphBars(numbers20Bar, numbers20Count) + "|\n" + "\t|\n" + " 10%-19%|"
				+ calculateGraphBars(numbers10Bar, numbers10Count) + "|\n" + "\t|\n" + "   0%-9%|"
				+ calculateGraphBars(numbers0Bar, numbers0Count) + "|\n"
				+ "\t|___|___|___|___|___|___|___|___|___|___|___|___|\n"
				+ "       \t0  10  20  30  40  50  60  70  80  90  100 110  MAX");
	}
}