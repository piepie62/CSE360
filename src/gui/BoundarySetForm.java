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

public class BoundarySetForm extends JDialog
{
	private static final long serialVersionUID = 5263485919488463314L;
	private MainFrame parentFrame;
	
	private JTextField highBoundaryField;
	private JLabel highGradeLabel;
	private JTextField lowBoundaryField;
	private JLabel lowGradeLabel;
	private JButton setBoundaryButton;

	public BoundarySetForm(MainFrame parent, boolean modal)
	{
		super(parent, modal);
		this.parentFrame = parent;
		this.initComponents();
	}

	private void initComponents()
	{

		this.lowBoundaryField = new JTextField();
		this.lowGradeLabel = new JLabel();
		this.highGradeLabel = new JLabel();
		this.highBoundaryField = new JTextField();
		this.setBoundaryButton = new JButton();

		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setTitle("Set Bounds");

		this.lowBoundaryField.setText("0");

		this.lowGradeLabel.setText("Lowest Grade");

		this.highGradeLabel.setText("Heighest Grade");

		this.highBoundaryField.setText("100");

		this.setBoundaryButton.setText("Set");
		this.setBoundaryButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				BoundarySetForm.this.setBoundaryButtonActionPerformed(evt);
			}
		});

		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addComponent(this.lowGradeLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(this.lowBoundaryField,
										GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup().addComponent(this.highGradeLabel).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(this.highBoundaryField, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE).addGap(0, 0, Short.MAX_VALUE)))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup().addGap(51, 51, 51).addComponent(this.setBoundaryButton).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(16, 16, 16)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.lowGradeLabel).addComponent(this.lowBoundaryField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.highGradeLabel).addComponent(this.highBoundaryField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE).addComponent(this.setBoundaryButton).addContainerGap()));

		this.pack();
	}
	
	private void setBoundaryButtonActionPerformed(ActionEvent evt)
	{
		//TODO: send the bounds information to the MainFrame or somewhere into the backend.
		this.dispose();
	}
}
