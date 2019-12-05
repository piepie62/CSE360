package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

public class ErrorForm extends JDialog
{

	private static final long serialVersionUID = 6820540183772193945L;
	private JScrollPane errorScrollPane;
	private JTextArea errorTextArea;
	private JButton closeButton;

	public ErrorForm(MainFrame parent, boolean modal)
	{
		super(parent, modal);
		this.initComponents();
	}

	private void initComponents()
	{
		this.errorScrollPane = new JScrollPane();
		this.errorTextArea = new JTextArea();
		this.closeButton = new JButton();
	
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setTitle("Errors this session");
	
		this.errorTextArea.setColumns(20);
		this.errorTextArea.setLineWrap(true);
		this.errorTextArea.setRows(5);
		this.errorTextArea.setEnabled(false);
		this.errorScrollPane.setViewportView(this.errorTextArea);
	
		this.closeButton.setText("Close");
		this.closeButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				ErrorForm.this.closeButtonActionPerformed(evt);
			}
		});
	
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.errorScrollPane)
				.addGroup(layout.createSequentialGroup().addGap(221, 221, 221).addComponent(this.closeButton).addContainerGap(241, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addComponent(this.errorScrollPane, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.closeButton).addContainerGap()));
	
		this.pack();
	}
	/**
	 * Sets the text of the error form to the errors from the current session,
	 *
	 * @param e
	 *            A string containing the errors from the current session
	 */
	protected void setErrors(String e)
	{
		this.errorTextArea.setText(e);
	}
	/**
	 * Action for when the "Close" button is selected. Exits the current 
	 * window.
	 *
	 * @param evt
	 *            the event that caused this action(Ignore)
	 */
	private void closeButtonActionPerformed(ActionEvent evt)
	{
		this.dispose();
	}
}
