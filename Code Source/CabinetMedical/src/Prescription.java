import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Prescription extends JFrame {

	private JPanel contentPane;
	private JTextField txtchannelno;
	private JTextField txtmal;
	private JLabel labelno;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prescription frame = new Prescription();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	int ln = 0;

	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	/**
	 * Create the frame.
	 */
	public Prescription() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 286);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(this);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Prescription", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panel.setBackground(new Color(14, 46, 130));
		panel.setBounds(0, 0, 496, 247);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Rendez-vous N\u00B0");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(67, 69, 113, 14);
		panel.add(lblNewLabel);

		JLabel lblMaladie = new JLabel("Maladie");
		lblMaladie.setForeground(Color.WHITE);
		lblMaladie.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMaladie.setBounds(67, 100, 78, 14);
		panel.add(lblMaladie);

		JLabel lblNewLabel_1_1 = new JLabel("Description");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(67, 128, 78, 14);
		panel.add(lblNewLabel_1_1);

		txtchannelno = new JTextField();
		txtchannelno.setBounds(190, 68, 242, 20);
		panel.add(txtchannelno);
		txtchannelno.setColumns(10);

		txtmal = new JTextField();
		txtmal.setColumns(10);
		txtmal.setBounds(190, 99, 242, 20);
		panel.add(txtmal);

		final JTextArea txtdesc = new JTextArea();
		txtdesc.setBounds(190, 128, 242, 59);
		panel.add(txtdesc);

		JLabel lblNewLabel_1 = new JLabel("Prescription N\u00B0");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(67, 36, 113, 14);
		panel.add(lblNewLabel_1);

		labelno = new JLabel("#");
		labelno.setForeground(Color.WHITE);
		labelno.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelno.setBounds(190, 36, 78, 14);
		panel.add(labelno);

		Connexion.Connect(con, pst, rs);

		JButton btnNewButton = new JButton("Cr�er");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// String pno = labelno.getText();
				String chno = txtchannelno.getText();
				String desctype = txtmal.getText();
				String desc = txtdesc.getText();

				try {
					pst = con.prepareStatement(
							"insert into prescription(channelid,doctorname,desctype,description)values(?,?,?,?)");

					// pst.setString(1, pno);
					pst.setString(1, chno);
					pst.setString(2, newdocname);
					pst.setString(3, desctype);
					pst.setString(4, desc);

					pst.executeUpdate();

					JOptionPane.showMessageDialog(null, "Prescription inserted !", "gg",
							JOptionPane.INFORMATION_MESSAGE);

					// labelno.setText("");
					txtchannelno.setText("");
					txtmal.setText("");
					txtdesc.setText("");
					txtchannelno.requestFocus();

					ln++;
					txtchannelno.setText(ln + "");

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
		btnNewButton.setBounds(135, 198, 89, 23);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(249, 198, 89, 23);
		panel.add(btnNewButton_1);

	}

	String id;
	String docname;

	String newid;
	String newdocname;

	public Prescription(String channelno, String doctorname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 286);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(this);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Prescription", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panel.setBackground(new Color(14, 46, 130));
		panel.setBounds(0, 0, 496, 247);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Rendez-vous N\u00B0");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(67, 69, 113, 14);
		panel.add(lblNewLabel);

		JLabel lblMaladie = new JLabel("Maladie");
		lblMaladie.setForeground(Color.WHITE);
		lblMaladie.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMaladie.setBounds(67, 100, 78, 14);
		panel.add(lblMaladie);

		JLabel lblNewLabel_1_1 = new JLabel("Description");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(67, 128, 78, 14);
		panel.add(lblNewLabel_1_1);

		txtchannelno = new JTextField();
		txtchannelno.setBounds(190, 68, 242, 20);
		panel.add(txtchannelno);
		txtchannelno.setColumns(10);

		txtmal = new JTextField();
		txtmal.setColumns(10);
		txtmal.setBounds(190, 99, 242, 20);
		panel.add(txtmal);

		final JTextArea txtdesc = new JTextArea();
		txtdesc.setBounds(190, 128, 242, 59);
		panel.add(txtdesc);

		JLabel lblNewLabel_1 = new JLabel("Prescription N\u00B0");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(67, 36, 113, 14);
		panel.add(lblNewLabel_1);

		labelno = new JLabel("#");
		labelno.setForeground(Color.WHITE);
		labelno.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelno.setBounds(190, 36, 78, 14);
		panel.add(labelno);

		Connexion.Connect(con, pst, rs);

		JButton btnNewButton = new JButton("Cr�er");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// String pno = labelno.getText();
				String chno = txtchannelno.getText();
				String desctype = txtmal.getText();
				String desc = txtdesc.getText();

				try {
					pst = con.prepareStatement(
							"insert into prescription(channelid,doctorname,desctype,description)values(?,?,?,?)");

					// pst.setString(1, pno);
					pst.setString(1, chno);
					pst.setString(2, newdocname);
					pst.setString(3, desctype);
					pst.setString(4, desc);

					pst.executeUpdate();

					JOptionPane.showMessageDialog(null, "Prescription inserted !", "gg",
							JOptionPane.INFORMATION_MESSAGE);

					// labelno.setText("");
					txtchannelno.setText("");
					txtmal.setText("");
					txtdesc.setText("");
					txtchannelno.requestFocus();

					ln++;
					txtchannelno.setText(ln + "");

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
		btnNewButton.setBounds(135, 198, 89, 23);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(249, 198, 89, 23);
		panel.add(btnNewButton_1);

		this.id = channelno;
		this.docname = doctorname;

		newid = id;
		newdocname = docname;

		txtchannelno.setText(newid);
		txtchannelno.setEditable(false);

	}
}
