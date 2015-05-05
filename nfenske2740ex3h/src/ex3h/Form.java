package ex3h;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

public class Form extends JFrame {

	private JPanel contentPane;
	private JLabel totalLbl;
	private JLabel averageLabel;
	private JLabel maxLbl;
	private JLabel MinLbl;
	private JTextField updateTxt;
	private JList list;
	private String[] strRainfall = {"1.2", "2.7", "2.2", "3.1", "2.9", "5.1", "3.2", "2.7", "3.6", "1.8", "2.2", "1.7"};
	private JButton btnUpdate;
	private JButton btnCalculate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form frame = new Form();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Form() {
		setTitle("NFenske 2740 Ex3H Rainfall");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 333, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMonthlyRainfall = new JLabel("Monthly Rainfall");
		lblMonthlyRainfall.setBounds(10, 11, 107, 14);
		contentPane.add(lblMonthlyRainfall);
		
		JList monthList = new JList();
		monthList.setBackground(UIManager.getColor("Label.background"));
		monthList.setEnabled(false);
		monthList.setModel(new AbstractListModel() {
			String[] values = new String[] {"01 Jan ", "02 Feb", "03 Mar", "04 Apr", "05 May", "06 Jun", "07 Jul", "08 Aug", "09 Sept", "10 Oct", "11 Nov", "12 Dec"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		monthList.setBounds(10, 36, 48, 223);
		contentPane.add(monthList);
		
		list = new JList(strRainfall);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				do_list_valueChanged(arg0);
			}
		});
		list.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		list.setBounds(68, 36, 57, 223);
		contentPane.add(list);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(154, 82, 82, 14);
		contentPane.add(lblTotal);
		
		JLabel lblAverage = new JLabel("Average:");
		lblAverage.setBounds(154, 104, 82, 14);
		contentPane.add(lblAverage);
		
		JLabel lblMaximum = new JLabel("Maximum:");
		lblMaximum.setBounds(154, 129, 82, 14);
		contentPane.add(lblMaximum);
		
		JLabel lblMinimum = new JLabel("Minimum:");
		lblMinimum.setBounds(154, 159, 82, 14);
		contentPane.add(lblMinimum);
		
		totalLbl = new JLabel("0.0");
		totalLbl.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		totalLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		totalLbl.setBounds(246, 78, 57, 18);
		contentPane.add(totalLbl);
		
		averageLabel = new JLabel("0.0");
		averageLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		averageLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		averageLabel.setBounds(246, 104, 57, 18);
		contentPane.add(averageLabel);
		
		maxLbl = new JLabel("0.0");
		maxLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		maxLbl.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		maxLbl.setBounds(246, 129, 57, 18);
		contentPane.add(maxLbl);
		
		MinLbl = new JLabel("0.0");
		MinLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		MinLbl.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		MinLbl.setBounds(246, 159, 57, 18);
		contentPane.add(MinLbl);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnUpdate_actionPerformed(e);
			}
		});
		btnUpdate.setBounds(36, 313, 89, 23);
		btnUpdate.setEnabled(false);
		contentPane.add(btnUpdate);
		
		btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnCalculate_actionPerformed(e);
			}
		});
		btnCalculate.setBounds(214, 188, 89, 23);
		contentPane.add(btnCalculate);
		
		updateTxt = new JTextField();
		updateTxt.setBounds(68, 285, 57, 20);
		updateTxt.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(updateTxt);
		updateTxt.setColumns(10);
	}
	
	protected void do_list_valueChanged(ListSelectionEvent arg0) 
	{
		btnUpdate.setEnabled(true);
		updateTxt.setText((String)list.getSelectedValue());
		updateTxt.requestFocus();
		updateTxt.selectAll();
	}
	
	protected void do_btnCalculate_actionPerformed(ActionEvent e) 
	{
		Rainfall rainfallData = new Rainfall(strRainfall);
		
		DecimalFormat fmt = new DecimalFormat("0.0");
		totalLbl.setText(fmt.format(rainfallData.getTotal()));
		averageLabel.setText(fmt.format(rainfallData.getAverage()));
		maxLbl.setText(fmt.format(rainfallData.getHighest()));
		MinLbl.setText(fmt.format(rainfallData.getLowest()));
	}
	
	protected void do_btnUpdate_actionPerformed(ActionEvent e) 
	{
		int selectedIndex = list.getSelectedIndex();
		double r = Double.parseDouble(updateTxt.getText());
		strRainfall[selectedIndex] = Double.toString(r);
		list.repaint();
		
		updateTxt.setText("0.0");
		btnUpdate.setEnabled(false);
		totalLbl.setText("");
		averageLabel.setText("");
		MinLbl.setText("");
		maxLbl.setText("");
	}
}
