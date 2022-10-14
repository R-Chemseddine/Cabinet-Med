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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.border.EtchedBorder;

public class Doctor extends JFrame {

	private JPanel contentPane;
	private JTextField txtdname;
	private JTextField txtspeciality;
	private JTable table;
	private JLabel labelnumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Doctor frame = new Doctor();
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
	private JTextField txtqualification;
	private JTextField txtchannelfee;
	private JTextField txtdp;
	private JSpinner txtroom;

	public void doctor_table() {

		try {
			pst = con.prepareStatement("select * from doctor where logid = ?");

			pst.setInt(1, newid);

			rs = pst.executeQuery();

			ResultSetMetaData rsm = rs.getMetaData();

			int c;
			c = rsm.getColumnCount();

			DefaultTableModel df = (DefaultTableModel) table.getModel();
			df.setRowCount(0);

			while (rs.next()) {

				Vector v2 = new Vector();

				for (int i = 1; i <= c; i++) {
					v2.add(rs.getString("doctorno"));
					v2.add(rs.getString("name"));
					v2.add(rs.getString("special"));
					v2.add(rs.getString("qualification"));
					v2.add(rs.getString("channelfee"));
					v2.add(rs.getString("phone"));
					v2.add(rs.getString("roomno"));
					// v2.add(rs.getString("logid"));
				}

				df.addRow(v2);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
	public Doctor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 896, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(this);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(14, 46, 130));
		panel.setBounds(0, 0, 890, 384);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Doctor registration", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBackground(new Color(240, 248, 255));
		panel_1.setBounds(10, 52, 301, 280);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Docteur N\u00B0");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 21, 84, 14);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nom");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 46, 84, 14);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Sp\u00E9cialit\u00E9");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 71, 84, 14);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Frais RDV");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(10, 122, 84, 14);
		panel_1.add(lblNewLabel_3);

		labelnumber = new JLabel("#");
		labelnumber.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelnumber.setBounds(133, 21, 158, 14);
		panel_1.add(labelnumber);

		txtdname = new JTextField();
		txtdname.setBounds(133, 43, 158, 20);
		panel_1.add(txtdname);
		txtdname.setColumns(10);

		txtspeciality = new JTextField();
		txtspeciality.setBounds(133, 68, 158, 20);
		panel_1.add(txtspeciality);
		txtspeciality.setColumns(10);

		final JButton btnNewButton_2 = new JButton("Add");

		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String pname = txtdname.getText();
				String speciality = txtspeciality.getText();
				String qualification = txtqualification.getText();
				String phone = txtdp.getText();
				String channelfee = txtchannelfee.getText();
				String room = txtroom.getValue().toString();

				try {
					pst = con.prepareStatement(
							"insert into doctor(name,special,qualification,channelfee,phone,roomno,logid)values(?,?,?,?,?,?,?)");

					pst.setString(1, pname);
					pst.setString(2, speciality);
					pst.setString(3, qualification);
					pst.setString(4, channelfee);
					pst.setString(5, phone);
					pst.setString(6, room);
					pst.setInt(7, newid);

					pst.executeUpdate();

					JOptionPane.showMessageDialog(null, "Doctor inserted !", "grr", JOptionPane.INFORMATION_MESSAGE);

					txtdname.setText("");
					txtspeciality.setText("");
					txtqualification.setText("");
					txtdp.setText("");
					txtchannelfee.setText("");

					ln++;
					labelnumber.setText(ln + "");

					// patient_table();
					doctor_table();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
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
				String no = labelnumber.getText();
				String pname = txtdname.getText();
				String speciality = txtspeciality.getText();
				String qualification = txtqualification.getText();
				String phone = txtdp.getText();
				String channelfee = txtchannelfee.getText();
				String room = txtroom.getValue().toString();

				try {
					pst = con.prepareStatement(
							"update doctor set name = ?,special = ?,qualification = ?,channelfee = ?,phone = ?,roomno = ? where doctorno = ?");

					pst.setString(1, pname);
					pst.setString(2, speciality);
					pst.setString(3, qualification);
					pst.setString(4, channelfee);
					pst.setString(5, phone);
					pst.setString(6, room);
					pst.setString(7, no);
					pst.executeUpdate();

					JOptionPane.showMessageDialog(null, "Doctor updated !", "gg", JOptionPane.INFORMATION_MESSAGE);

					txtdname.setText("");
					txtspeciality.setText("");
					txtqualification.setText("");
					txtdp.setText("");
					txtchannelfee.setText("");
					// txtroom.setValue("0");

					ln++;
					labelnumber.setText(ln + "");

					// patient_table();
					doctor_table();
					btnNewButton_2.setEnabled(true);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
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

				String no = labelnumber.getText();

				try {
					pst = con.prepareStatement("delete from doctor where doctorno = ?");

					pst.setString(1, no);

					pst.executeUpdate();

					JOptionPane.showMessageDialog(null, "Doctor deleted !", "gg", JOptionPane.INFORMATION_MESSAGE);

					txtdname.setText("");
					txtspeciality.setText("");
					txtqualification.setText("");
					txtdp.setText("");
					txtchannelfee.setText("");

					ln++;
					labelnumber.setText(ln + "");

					// patient_table();
					doctor_table();
					btnNewButton_2.setEnabled(true);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
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

		txtqualification = new JTextField();
		txtqualification.setBounds(133, 94, 158, 20);
		panel_1.add(txtqualification);
		txtqualification.setColumns(10);

		JLabel lblNewLabel_3_1 = new JLabel("Qualification");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3_1.setBounds(10, 96, 84, 14);
		panel_1.add(lblNewLabel_3_1);

		txtchannelfee = new JTextField();
		txtchannelfee.setBounds(133, 120, 158, 20);
		panel_1.add(txtchannelfee);
		txtchannelfee.setColumns(10);

		JLabel lblNewLabel_3_2 = new JLabel("Phone");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3_2.setBounds(10, 147, 84, 14);
		panel_1.add(lblNewLabel_3_2);

		txtdp = new JTextField();
		txtdp.setBounds(133, 145, 158, 20);
		panel_1.add(txtdp);
		txtdp.setColumns(10);

		JLabel lblNewLabel_3_2_1 = new JLabel("Chambre N\u00B0");
		lblNewLabel_3_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3_2_1.setBounds(10, 172, 84, 14);
		panel_1.add(lblNewLabel_3_2_1);

		txtroom = new JSpinner();
		txtroom.setBounds(195, 170, 43, 20);
		panel_1.add(txtroom);

		JButton btnNewButton_3 = new JButton("Cancel");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_3.setBounds(113, 343, 89, 23);
		panel.add(btnNewButton_3);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				DefaultTableModel d1 = (DefaultTableModel) table.getModel();
				int SelectIndex = table.getSelectedRow();

				labelnumber.setText(d1.getValueAt(SelectIndex, 0).toString());
				txtdname.setText(d1.getValueAt(SelectIndex, 1).toString());
				txtspeciality.setText(d1.getValueAt(SelectIndex, 2).toString());
				txtqualification.setText(d1.getValueAt(SelectIndex, 3).toString());
				txtchannelfee.setText(d1.getValueAt(SelectIndex, 4).toString());
				txtdp.setText(d1.getValueAt(SelectIndex, 5).toString());
				// txtroom.setValue(Integer.parseInt(d1.getValueAt(SelectIndex, 6).toString()));

				btnNewButton_2.setEnabled(false);
			}
		});

		JScrollPane sp = new JScrollPane();
		sp.setBounds(323, 52, 546, 280);
		panel.add(sp);

		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Doctor N\u00B0", "Name", "Speciality",
				"Qualification", "Frais", "Phone", "Room N\u00B0" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { String.class, String.class, Integer.class, String.class, Integer.class,
					Integer.class, Integer.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.setBounds(323, 52, 546, 280);
		sp.setViewportView(table);

		JLabel lblNewLabel_4 = new JLabel("Doctor registration");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("SansSerif", Font.BOLD, 32));
		lblNewLabel_4.setBounds(298, 6, 291, 42);
		panel.add(lblNewLabel_4);
		Connexion.Connect(con, pst, rs);
		doctor_table();

	}

	String utype;
	int id;

	int newid;

	private JSpinner txtroom1;

	public Doctor(int idd, String utype) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 896, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(this);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(14, 46, 130));
		panel.setBounds(0, 0, 899, 384);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Doctor registration", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBackground(new Color(240, 248, 255));
		panel_1.setBounds(10, 52, 301, 280);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Docteur N\u00B0");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 21, 84, 14);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nom");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 46, 84, 14);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Sp\u00E9cialit\u00E9");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 71, 84, 14);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Frais RDV");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(10, 122, 84, 14);
		panel_1.add(lblNewLabel_3);

		labelnumber = new JLabel("#");
		labelnumber.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelnumber.setBounds(133, 21, 158, 14);
		panel_1.add(labelnumber);

		txtdname = new JTextField();
		txtdname.setBounds(133, 43, 158, 20);
		panel_1.add(txtdname);
		txtdname.setColumns(10);

		txtspeciality = new JTextField();
		txtspeciality.setBounds(133, 68, 158, 20);
		panel_1.add(txtspeciality);
		txtspeciality.setColumns(10);

		final JButton btnNewButton_2 = new JButton("Add");

		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String pname = txtdname.getText();
				String speciality = txtspeciality.getText();
				String qualification = txtqualification.getText();
				String phone = txtdp.getText();
				String channelfee = txtchannelfee.getText();
				String room = txtroom1.getValue().toString();

				try {
					pst = con.prepareStatement(
							"insert into doctor(name,special,qualification,channelfee,phone,roomno,logid)values(?,?,?,?,?,?,?)");

					pst.setString(1, pname);
					pst.setString(2, speciality);
					pst.setString(3, qualification);
					pst.setString(4, channelfee);
					pst.setString(5, phone);
					pst.setString(6, room);
					pst.setInt(7, newid);

					pst.executeUpdate();

					JOptionPane.showMessageDialog(null, "Doctor inserted !", "grr", JOptionPane.INFORMATION_MESSAGE);

					txtdname.setText("");
					txtspeciality.setText("");
					txtqualification.setText("");
					txtdp.setText("");
					txtchannelfee.setText("");
					// txtroom.setValue("0");

					ln++;
					labelnumber.setText(ln + "");

					// patient_table();
					doctor_table();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
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
				String no = labelnumber.getText();
				String pname = txtdname.getText();
				String speciality = txtspeciality.getText();
				String qualification = txtqualification.getText();
				String phone = txtdp.getText();
				String channelfee = txtchannelfee.getText();
				String room = txtroom1.getValue().toString();

				try {
					pst = con.prepareStatement(
							"update doctor set name = ?,special = ?,qualification = ?,channelfee = ?,phone = ?,roomno = ? where doctorno = ?");

					pst.setString(1, pname);
					pst.setString(2, speciality);
					pst.setString(3, qualification);
					pst.setString(4, channelfee);
					pst.setString(5, phone);
					pst.setString(6, room);
					pst.setString(7, no);

					pst.executeUpdate();

					JOptionPane.showMessageDialog(null, "Doctor updated !", "gg", JOptionPane.INFORMATION_MESSAGE);

					txtdname.setText("");
					txtspeciality.setText("");
					txtqualification.setText("");
					txtdp.setText("");
					txtchannelfee.setText("");

					ln++;
					labelnumber.setText(ln + "");

					// patient_table();
					doctor_table();
					btnNewButton_2.setEnabled(true);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
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
				String no = labelnumber.getText();

				try {
					pst = con.prepareStatement("delete from doctor where doctorno = ?");

					pst.setString(1, no);

					pst.executeUpdate();

					JOptionPane.showMessageDialog(null, "Doctor deleted !", "gg", JOptionPane.INFORMATION_MESSAGE);

					txtdname.setText("");
					txtspeciality.setText("");
					txtqualification.setText("");
					txtdp.setText("");
					txtchannelfee.setText("");

					ln++;
					labelnumber.setText(ln + "");

					// patient_table();
					doctor_table();
					btnNewButton_2.setEnabled(true);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
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

		txtqualification = new JTextField();
		txtqualification.setBounds(133, 94, 158, 20);
		panel_1.add(txtqualification);
		txtqualification.setColumns(10);

		JLabel lblNewLabel_3_1 = new JLabel("Qualification");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3_1.setBounds(10, 96, 84, 14);
		panel_1.add(lblNewLabel_3_1);

		txtchannelfee = new JTextField();
		txtchannelfee.setBounds(133, 120, 158, 20);
		panel_1.add(txtchannelfee);
		txtchannelfee.setColumns(10);

		JLabel lblNewLabel_3_2 = new JLabel("Phone");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3_2.setBounds(10, 147, 84, 14);
		panel_1.add(lblNewLabel_3_2);

		txtdp = new JTextField();
		txtdp.setBounds(133, 145, 158, 20);
		panel_1.add(txtdp);
		txtdp.setColumns(10);

		JLabel lblNewLabel_3_2_1 = new JLabel("Chambre N\u00B0");
		lblNewLabel_3_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3_2_1.setBounds(10, 172, 84, 14);
		panel_1.add(lblNewLabel_3_2_1);

		txtroom1 = new JSpinner();
		txtroom1.setBounds(195, 170, 43, 20);
		panel_1.add(txtroom1);

		JButton btnNewButton_3 = new JButton("close");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_3.setBounds(113, 343, 89, 23);
		panel.add(btnNewButton_3);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				DefaultTableModel d1 = (DefaultTableModel) table.getModel();
				int SelectIndex = table.getSelectedRow();

				labelnumber.setText(d1.getValueAt(SelectIndex, 0).toString());
				txtdname.setText(d1.getValueAt(SelectIndex, 1).toString());
				txtspeciality.setText(d1.getValueAt(SelectIndex, 2).toString());
				txtqualification.setText(d1.getValueAt(SelectIndex, 3).toString());
				txtchannelfee.setText(d1.getValueAt(SelectIndex, 4).toString());
				txtdp.setText(d1.getValueAt(SelectIndex, 5).toString());
				// txtroom.setValue(Integer.parseInt(d1.getValueAt(SelectIndex, 6).toString()));

				btnNewButton_2.setEnabled(false);
			}
		});

		JScrollPane sp = new JScrollPane();
		sp.setBounds(323, 52, 546, 280);
		panel.add(sp);

		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Doctor N\u00B0", "Name", "Speciality",
				"Qualification", "Frais", "Phone", "Room N\u00B0" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { String.class, String.class, Integer.class, String.class, Integer.class,
					Integer.class, Integer.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.setBounds(323, 52, 546, 280);
		sp.setViewportView(table);
		// patient_table();
		doctor_table();

		JLabel lblNewLabel_4 = new JLabel("Docteur Registration");
		lblNewLabel_4.setFont(new Font("SansSerif", Font.BOLD, 32));
		lblNewLabel_4.setBounds(185, 6, 309, 42);
		panel.add(lblNewLabel_4);

		this.id = idd;
		this.utype = utype;

		newid = id;
		doctor_table();
	}
}
