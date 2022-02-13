import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewPrescription extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPrescription frame = new ViewPrescription();
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

	public void Connect() {
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			//con = DriverManager.getConnection("jdbc:mysql://localhost/cabinetmedical", "root", "");
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

	public void prescription_table() {

		try {
			pst = con.prepareStatement("select * from prescription");
			rs = pst.executeQuery();

			ResultSetMetaData rsm = rs.getMetaData();

			int c;
			c = rsm.getColumnCount();

			DefaultTableModel df = (DefaultTableModel) table.getModel();
			df.setRowCount(0);

			while (rs.next()) {

				Vector v2 = new Vector();

				for (int i = 1; i <= c; i++) {
					v2.add(rs.getString("pid"));
					v2.add(rs.getString("channelid"));
					v2.add(rs.getString("doctorname"));
					v2.add(rs.getString("desctype"));
					v2.add(rs.getString("description"));
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
	public ViewPrescription() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 633, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(this);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(14, 46, 130));
		panel.setBounds(0, 0, 617, 431);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane sp = new JScrollPane();
		sp.setBounds(6, 55, 605, 321);
		panel.add(sp);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Prescription ID", "Rendez-vous", "Docteur", "Type", "Description" }));
		table.setBounds(10, 11, 496, 279);
		sp.setViewportView(table);

		JLabel lblNewLabel = new JLabel("Prescriptions");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(196, 6, 221, 37);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 32));
		
				JButton btnNewButton_1 = new JButton("Cancel");
				btnNewButton_1.setBounds(315, 389, 102, 23);
				panel.add(btnNewButton_1);
				
						JButton btnNewButton = new JButton("Inventaire");
						btnNewButton.setBounds(201, 389, 102, 23);
						panel.add(btnNewButton);
						btnNewButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								DefaultTableModel d1 = (DefaultTableModel) table.getModel();
								int selectedindex = table.getSelectedRow();

								String pid = d1.getValueAt(selectedindex, 0).toString();

								//JOptionPane.showMessageDialog(null, pid, "gg", JOptionPane.INFORMATION_MESSAGE);
								
								new Inventory(pid).setVisible(true);
							}
						});
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});

		Connect();
		prescription_table();
	}
}
