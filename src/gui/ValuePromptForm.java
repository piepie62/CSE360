package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

public class ValuePromptForm extends JDialog
{
	private static final long serialVersionUID = 8425360453295415078L;

	private JLabel enterValueLabel;
	private JTextField valueTextField;
	private JButton enterButton;
	private JButton cancelButton;

	public ValuePromptForm(MainFrame parent, boolean modal, String title)
	{
		super(parent, modal);
		this.initComponents();
		this.setTitle(title);
	}

	private void initComponents()
	{
		this.enterValueLabel = new JLabel();
		this.valueTextField = new JTextField();
		this.enterButton = new JButton();
		this.cancelButton = new JButton();
	
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	
		this.enterValueLabel.setText("Please enter a value:");
	
		this.valueTextField.setToolTipText("");
	
		this.enterButton.setText("Enter");
		this.enterButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				ValuePromptForm.this.enterButtonActionPerformed(evt);
			}
		});
	
		this.cancelButton.setText("Cancel");
		this.cancelButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				ValuePromptForm.this.cancelButtonActionPerformed(evt);
			}
		});
	
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.enterValueLabel).addComponent(this.enterButton, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.cancelButton, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE).addComponent(this.valueTextField))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(14, 14, 14)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.enterValueLabel).addComponent(this.valueTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.enterButton).addComponent(this.cancelButton))
				.addContainerGap()));
	
		this.pack();
	}

	private void cancelButtonActionPerformed(ActionEvent evt)
	{
		this.dispose();
	}

	private void enterButtonActionPerformed(ActionEvent evt)
	{
		this.dispose();
	}
	
	public int getValue()
	{
		String value = valueTextField.getText();
		
		int returnValue = -1;
		
		try
		{
			returnValue = Integer.parseInt(value);
		}
		catch(Exception e)
		{
			returnValue = -1;
		}
		
		return returnValue;
	}
}
