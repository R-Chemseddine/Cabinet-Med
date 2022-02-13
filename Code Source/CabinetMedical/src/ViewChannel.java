import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
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

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewChannel extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewChannel frame = new ViewChannel();
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

	public void channel_table() {

		try {
			pst = con.prepareStatement(
					"select channel.channelno,doctor.name,patient.name,channel.roomno,channel.date from doctor INNER JOIN channel on channel.doctorname = doctor.name INNER JOIN patient on channel.patientname = patient.name where doctor.logid = ?");

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
					v2.add(rs.getString(1));
					v2.add(rs.getString(2));
					v2.add(rs.getString(3));
					v2.add(rs.getString(4));
					v2.add(rs.getString(5));

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
	public ViewChannel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(this);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(14, 46, 130));
		panel.setBounds(0, 0, 689, 398);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Rendez-vous");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblNewLabel.setBounds(224, 16, 222, 39);
		panel.add(lblNewLabel);

		JScrollPane sp = new JScrollPane();
		sp.setBounds(84, 67, 512, 276);
		panel.add(sp);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Rendez-vous N°", "Docteur", "Patient", "Chambre N°", "Date" }));
		table.setBounds(84, 73, 512, 270);
		sp.setViewportView(table);

		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton.setBounds(476, 355, 120, 23);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Prescription");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel d1 = (DefaultTableModel) table.getModel();
				int selectedIndex = table.getSelectedRow();
				String channelno = d1.getValueAt(selectedIndex, 0).toString();
				String docname = d1.getValueAt(selectedIndex, 0).toString();

				new Prescription(channelno, docname).setVisible(true);
				;

			}
		});
		btnNewButton_1.setBounds(84, 354, 120, 23);
		panel.add(btnNewButton_1);

		newid = 6;

		Connect();
		channel_table();
	}

	int id;
	int newid;

	public ViewChannel(int id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(this);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(14, 46, 130));
		panel.setBounds(0, 0, 689, 398);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Rendez-vous");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblNewLabel.setBounds(224, 16, 222, 39);
		panel.add(lblNewLabel);

		JScrollPane sp = new JScrollPane();
		sp.setBounds(84, 67, 512, 276);
		panel.add(sp);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Rendez-vous N°", "Docteur", "Patient", "Chambre N°", "Date" }));
		table.setBounds(84, 73, 512, 270);
		sp.setViewportView(table);

		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton.setBounds(476, 355, 120, 23);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Prescription");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel d1 = (DefaultTableModel) table.getModel();
				int selectedIndex = table.getSelectedRow();
				String channelno = d1.getValueAt(selectedIndex, 0).toString();
				String docname = d1.getValueAt(selectedIndex, 1).toString();

				new Prescription(channelno, docname).setVisible(true);
				;

			}
		});
		btnNewButton_1.setBounds(84, 354, 120, 23);
		panel.add(btnNewButton_1);

		this.id = id;
		newid = id;

		Connect();
		channel_table();
	}
}
