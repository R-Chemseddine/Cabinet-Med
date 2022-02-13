import java.awt.EventQueue;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Patient extends JFrame {

	private JPanel contentPane;
	private JTextField txtpname;
	private JTextField txtpp;
	private JTable table;
	private JLabel labelnumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Patient frame = new Patient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	int ln = 7;

	public void Connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager
					.getConnection("jdbc:sqlite:sample.db");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pst.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

	}

	public void patient_table() {

		try {
			pst = con.prepareStatement("select * from 'patient'");
			rs = pst.executeQuery();

			ResultSetMetaData rsm = rs.getMetaData();

			int c;
			c = rsm.getColumnCount();

			DefaultTableModel df = (DefaultTableModel) table.getModel();
			df.setRowCount(0);

			while (rs.next()) {

				Vector v2 = new Vector();

				for (int i = 1; i <= c; i++) {
					v2.add(rs.getString("patientno"));
					v2.add(rs.getString("name"));
					v2.add(rs.getString("phone"));
					v2.add(rs.getString("adress"));
				}

				df.addRow(v2);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pst.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

	}

	/**
	 * Create the frame.
	 */
	public Patient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 726, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(this);
		contentPane.setLayout(null);

		Connect();

		JPanel panel = new JPanel();
		panel.setBackground(new Color(14, 46, 130));
		panel.setBounds(0, 0, 710, 372);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Patient registration", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_1.setBackground(new Color(240, 248, 255));
		panel_1.setBounds(10, 52, 301, 280);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Patient N\u00B0");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 21, 84, 14);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Patient name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 46, 84, 14);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Phone");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 71, 84, 14);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Adress");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(10, 96, 84, 14);
		panel_1.add(lblNewLabel_3);

		labelnumber = new JLabel("0");
		labelnumber.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelnumber.setBounds(133, 21, 158, 14);
		panel_1.add(labelnumber);

		txtpname = new JTextField();
		txtpname.setBounds(133, 43, 158, 20);
		panel_1.add(txtpname);
		txtpname.setColumns(10);

		txtpp = new JTextField();
		txtpp.setBounds(133, 68, 158, 20);
		panel_1.add(txtpp);
		txtpp.setColumns(10);

		final JTextArea txtadress = new JTextArea();
		txtadress.setBounds(133, 95, 158, 84);
		panel_1.add(txtadress);

		final JButton btnNewButton_2 = new JButton("Add");

		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String pname = txtpname.getText();
				String phone = txtpp.getText();
				String adress = txtadress.getText();

				try {
					pst = con.prepareStatement("insert into patient(name,phone,adress)values(?,?,?)");

					pst.setString(1, pname);
					pst.setString(2, phone);
					pst.setString(3, adress);

					pst.executeUpdate();

					JOptionPane.showMessageDialog(null, "Patient inserted !", "grr", JOptionPane.INFORMATION_MESSAGE);

					txtpname.setText("");
					txtpp.setText("");
					txtadress.setText("");
					txtpname.requestFocus();

					ln++;
					labelnumber.setText(ln + "");

					patient_table();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					try {
						rs.close();
						pst.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}

			}
		});
		btnNewButton_2.setBounds(10, 232, 89, 23);
		panel_1.add(btnNewButton_2);

		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pname = txtpname.getText();
				String phone = txtpp.getText();
				String adress = txtadress.getText();
				String n = labelnumber.getText();

				try {
					pst = con
							.prepareStatement("update patient set name = ?, phone = ?, adress = ? where patientno = ?");

					pst.setString(1, pname);
					pst.setString(2, phone);
					pst.setString(3, adress);
					pst.setString(4, n);
					pst.executeUpdate();

					JOptionPane.showMessageDialog(null, "Patient updated !", "grr", JOptionPane.INFORMATION_MESSAGE);

					txtpname.setText("");
					txtpp.setText("");
					txtadress.setText("");
					txtpname.requestFocus();
					btnNewButton_2.setEnabled(true);
					
					ln++;
					labelnumber.setText(ln + "");

					patient_table();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					try {
						rs.close();
						pst.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}

			}
		});
		btnNewButton_1.setBounds(104, 232, 89, 23);
		panel_1.add(btnNewButton_1);

		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String n = labelnumber.getText();

				try {
					pst = con.prepareStatement("delete from patient where patientno = ?");

					pst.setString(1, n);
					pst.executeUpdate();

					JOptionPane.showMessageDialog(null, "Patient deleted !", "grr", JOptionPane.INFORMATION_MESSAGE);

					txtpname.setText("");
					txtpp.setText("");
					txtadress.setText("");
					txtpname.requestFocus();

					ln++;
					labelnumber.setText(ln + "");

					patient_table();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					try {
						rs.close();
						pst.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}

			}
		});
		btnNewButton.setBounds(202, 232, 89, 23);
		panel_1.add(btnNewButton);

		JButton btnNewButton_3 = new JButton("Close");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_3.setBounds(302, 344, 89, 23);
		panel.add(btnNewButton_3);

		JScrollPane sp = new JScrollPane();
		sp.setBounds(323, 52, 381, 280);
		panel.add(sp);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				DefaultTableModel d1 = (DefaultTableModel) table.getModel();
				int SelectIndex = table.getSelectedRow();

				labelnumber.setText(d1.getValueAt(SelectIndex, 0).toString());
				txtpname.setText(d1.getValueAt(SelectIndex, 1).toString());
				txtpp.setText(d1.getValueAt(SelectIndex, 2).toString());
				txtadress.setText(d1.getValueAt(SelectIndex, 3).toString());

				btnNewButton_2.setEnabled(false);
			}
		});

		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Patient N\u00B0", "Name", "Phone", "Adress" }));
		table.setBounds(323, 52, 342, 280);
		sp.setViewportView(table);
		patient_table();

		JLabel lblNewLabel_4 = new JLabel("Patient Registration");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("SansSerif", Font.BOLD, 32));
		lblNewLabel_4.setBounds(196, 6, 309, 42);
		panel.add(lblNewLabel_4);

	}
}
