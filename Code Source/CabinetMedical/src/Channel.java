import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Channel extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Channel frame = new Channel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JComboBox txtdoctorname;
	private JComboBox txtpatientname;

	int ln = 0;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	String chno;

	public void Connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:sample.db");
			
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

	public class Doctor {
		String id;
		String name;

		public Doctor(String id, String name) {
			this.id = id;
			this.name = name;
		}

		public String toString() {
			return name;
		}
	}

	public void LoadDoctor() {
		try {
			pst = con.prepareStatement("select * from doctor");

			rs = pst.executeQuery();
			txtdoctorname.removeAll();

			while (rs.next()) {
				txtdoctorname.addItem(new Doctor(rs.getString(1), rs.getString(2)));

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

	public class Patient {
		String id;
		String name;

		public Patient(String id, String name) {
			this.id = id;
			this.name = name;
		}

		public String toString() {
			return name;
		}
	}

	public void LoadPatient() {
		try {
			pst = con.prepareStatement("select * from patient");

			rs = pst.executeQuery();
			txtpatientname.removeAll();

			while (rs.next()) {
				txtpatientname.addItem(new Patient(rs.getString(1), rs.getString(2)));

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

	public void channel_table() {

		try {
			pst = con.prepareStatement("select * from channel");

			rs = pst.executeQuery();

			ResultSetMetaData rsm = rs.getMetaData();

			int c;
			c = rsm.getColumnCount();

			DefaultTableModel df = (DefaultTableModel) table.getModel();
			df.setRowCount(0);

			while (rs.next()) {

				Vector v2 = new Vector();

				for (int i = 1; i <= c; i++) {
					v2.add(rs.getString("channelno"));
					v2.add(rs.getString("doctorname"));
					v2.add(rs.getString("patientname"));
					v2.add(rs.getString("roomno"));
					v2.add(rs.getString("date"));

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
	public Channel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 734, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(this);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(14, 46, 130));
		panel.setBounds(0, 0, 718, 357);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Reservation", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));
		panel_1.setBounds(6, 61, 280, 233);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Rendez-vous N\u00B0");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 28, 112, 14);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Docteur");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 53, 101, 14);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Patient");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 80, 101, 14);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Chambre N\u00B0");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(10, 106, 101, 14);
		panel_1.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Date du rendez-vous");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(10, 128, 144, 28);
		panel_1.add(lblNewLabel_4);

		final JLabel labelchnumber = new JLabel("#");
		labelchnumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelchnumber.setBounds(158, 28, 79, 14);
		panel_1.add(labelchnumber);

		txtdoctorname = new JComboBox();
		txtdoctorname.setBounds(158, 50, 116, 22);
		panel_1.add(txtdoctorname);

		txtpatientname = new JComboBox();
		txtpatientname.setBounds(158, 77, 116, 22);
		panel_1.add(txtpatientname);

		final JSpinner txtroomno = new JSpinner();
		txtroomno.setBounds(198, 103, 31, 20);
		panel_1.add(txtroomno);

		final JDateChooser txtdate = new JDateChooser();
		txtdate.setBounds(158, 128, 116, 28);
		panel_1.add(txtdate);

		JButton btnNewButton = new JButton("Cr\u00E9er");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// String chnumber = labelchnumber.getText();
				Doctor d = (Doctor) txtdoctorname.getSelectedItem();
				Patient p = (Patient) txtpatientname.getSelectedItem();
				String roomno = txtroomno.getValue().toString();

				SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
				String date = dateformat.format(txtdate.getDate());

				try {
					pst = con
							.prepareStatement("insert into channel(doctorname,patientname,roomno,date)values(?,?,?,?)");

					// pst.setString(1, chnumber);
					pst.setString(1, d.name);
					pst.setString(2, p.name);
					pst.setString(3, roomno);
					pst.setString(4, date);

					pst.executeUpdate();

					JOptionPane.showMessageDialog(null, "Rendez-vous Crée !", "grr", JOptionPane.INFORMATION_MESSAGE);

					txtdoctorname.setSelectedIndex(-1);
					txtpatientname.setSelectedIndex(-1);

					ln++;
					labelchnumber.setText(ln + "");

					// patient_table();
					// doctor_table();
					channel_table();

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
		btnNewButton.setBounds(32, 188, 90, 28);
		panel_1.add(btnNewButton);

		JButton btnCancel = new JButton("Annuler");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					pst = con.prepareStatement("delete from channel where channelno = ?");

					pst.setString(1, chno);

					pst.executeUpdate();

					JOptionPane.showMessageDialog(null, "Channel canceled !", "grr", JOptionPane.INFORMATION_MESSAGE);

					txtdoctorname.setSelectedIndex(-1);
					txtpatientname.setSelectedIndex(-1);

					ln++;
					labelchnumber.setText(ln + "");

					// patient_table();
					// doctor_table();
					channel_table();

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
		btnCancel.setBounds(147, 188, 90, 28);
		panel_1.add(btnCancel);
		
		JScrollPane sp = new JScrollPane();
		sp.setBounds(296, 61, 416, 233);
		panel.add(sp);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				int selectindex = table.getSelectedRow();

				chno = table.getValueAt(selectindex, 0).toString();
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Channel N\u00B0", "Doctor name", "Patient name", "Room N\u00B0", "Channel date" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { Integer.class, String.class, String.class, Integer.class,
					String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.setBounds(296, 66, 392, 270);
		sp.setViewportView(table);
		

		JLabel lblNewLabel_6 = new JLabel("Rendez-vous");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setFont(new Font("SansSerif", Font.PLAIN, 32));
		lblNewLabel_6.setBounds(255, 6, 193, 48);
		panel.add(lblNewLabel_6);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(306, 306, 90, 28);
		panel.add(btnNewButton_1);

		Connect();
		LoadDoctor();
		LoadPatient();
		channel_table();
	}
}
