import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Inventory extends JFrame {

	private JPanel contentPane;
	private JTextField txtcode;
	private JTextField txtname;
	private JTable table;
	private JTextField txtbalance;
	private JTextField txtpaye;
	private JTextField txttotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventory frame = new Inventory();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void sales() {
		DateTimeFormatter daa = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();

		String date = daa.format(now);

		String subtot = txttotal.getText();
		String pay = txtpaye.getText();
		String balance = txtbalance.getText();

		int lastincertid = 0;

		try {
			String query = "insert into sales(date,subtotal,pay,balance)values(?,?,?,?)";
			pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, date);
			pst.setString(2, subtot);
			pst.setString(3, pay);
			pst.setString(4, balance);
			pst.executeUpdate();

			rs = pst.getGeneratedKeys();

			if (rs.next()) {
				lastincertid = rs.getInt(1);
			}

			int rows = table.getColumnCount();

			String query1 = "insert into salesProduct(salesid,productid,sellprice,qnt,total)values(?,?,?,?,?)";
			pst = con.prepareStatement(query1);

			String prescriptionid;
			String itemid;
			String itemname;
			String price;
			Integer qnt;
			Integer total;

			for (int i = 0; i < table.getRowCount(); i++) {
				prescriptionid = (String) table.getValueAt(i, 0);
				itemid = (String) table.getValueAt(i, 1);
				qnt = (Integer) table.getValueAt(i, 3);
				price = (String) table.getValueAt(i, 4);
				total = (Integer) table.getValueAt(i, 5);

				pst.setInt(1, lastincertid);
				pst.setString(2, itemid);
				pst.setInt(3, qnt.intValue());
				pst.setString(4, price);
				pst.setInt(5, total.intValue());

				pst.executeUpdate();

				JOptionPane.showMessageDialog(null, "record added !", "gg", JOptionPane.INFORMATION_MESSAGE);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	/**
	 * Create the frame.
	 */
	public Inventory() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(this);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(14, 46, 130));
		panel.setBounds(0, 0, 619, 459);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Prescription ID");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(23, 56, 131, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Medicament code");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(23, 96, 131, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Medicament nom");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(23, 136, 131, 14);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Quantit\u00E9");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(23, 176, 131, 14);
		panel.add(lblNewLabel_4);

		final JLabel labelpid = new JLabel("#");
		labelpid.setForeground(Color.WHITE);
		labelpid.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelpid.setBounds(164, 56, 131, 14);
		panel.add(labelpid);

		txtcode = new JTextField();
		txtcode.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					String code = txtcode.getText();

					try {
						pst = con.prepareStatement("select * from item where itemid = ?");

						pst.setString(1, code);
						rs = pst.executeQuery();

						if (rs.next() == false) {
							JOptionPane.showMessageDialog(null, "Medicament non trouv�", "gg",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							String name = rs.getString("itemname");
							txtname.setText(name.trim());
						}

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

			}
		});
		txtcode.setBounds(164, 95, 144, 20);
		panel.add(txtcode);
		txtcode.setColumns(10);

		txtname = new JTextField();
		txtname.setColumns(10);
		txtname.setBounds(164, 135, 144, 20);
		panel.add(txtname);

		final JSpinner txtqnt = new JSpinner();
		txtqnt.setBounds(164, 175, 43, 20);
		panel.add(txtqnt);

		JScrollPane sp = new JScrollPane();
		sp.setBounds(6, 240, 607, 184);
		panel.add(sp);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Item ID", "Item name", "Description", "Prix de vente", "Quantit�", "Prix d'achat" }));
		table.setBounds(10, 196, 611, 178);
		sp.setViewportView(table);

		JLabel lblNewLabel_1_2 = new JLabel("Total");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(371, 58, 59, 14);
		panel.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Pay\u00E9");
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(371, 96, 59, 14);
		panel.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Balance");
		lblNewLabel_1_4.setForeground(Color.WHITE);
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_4.setBounds(371, 138, 59, 14);
		panel.add(lblNewLabel_1_4);

		txtbalance = new JTextField();
		txtbalance.setColumns(10);
		txtbalance.setBounds(440, 135, 144, 20);
		panel.add(txtbalance);

		txtpaye = new JTextField();
		txtpaye.setColumns(10);
		txtpaye.setBounds(440, 95, 144, 20);
		panel.add(txtpaye);

		txttotal = new JTextField();
		txttotal.setColumns(10);
		txttotal.setBounds(440, 55, 144, 20);
		panel.add(txttotal);

		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String code = txtcode.getText();
				try {
					pst = con.prepareStatement("select * from item where itemid = ?");
					pst.setString(1, code);
					rs = pst.executeQuery();

					while (rs.next()) {
						int currentqnt;
						int sellprice;
						int qnt;

						currentqnt = rs.getInt("qnt");
						sellprice = rs.getInt("sellprice");

						qnt = Integer.parseInt(txtqnt.getValue().toString());

						int total = sellprice * qnt;

						Integer Total = new Integer(total);
						Integer Sellprice = new Integer(sellprice);

						if (qnt >= currentqnt) {
							JOptionPane.showMessageDialog(null, "Quantit� disponible" + currentqnt, "grr",
									JOptionPane.INFORMATION_MESSAGE);
							JOptionPane.showMessageDialog(null, "Quantit� insuffisante", "grr",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							DefaultTableModel df = (DefaultTableModel) table.getModel();
							df.addRow(new Object[] { labelpid.getText(), txtcode.getText(), txtname.getText(),
									Sellprice, txtqnt.getValue().toString(), Total, });

							int sum = 0;

							for (int i = 0; i < table.getRowCount(); i++) {

								sum = sum + Integer.parseInt(table.getValueAt(i, 5).toString());

							}

							txttotal.setText(Integer.toString(sum));
							txtcode.setText("");
							txtname.setText("");

							txtcode.requestFocus();

						}
					}

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
		btnNewButton.setBounds(191, 206, 103, 23);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int pay = (Integer.parseInt(txtpaye.getText()));
				int totalcost = (Integer.parseInt(txttotal.getText()));

				int bal = pay - totalcost;

				txtbalance.setText(String.valueOf(bal));

				sales();

			}
		});
		btnNewButton_1.setBounds(330, 206, 103, 23);
		panel.add(btnNewButton_1);

		JLabel lblNewLabel = new JLabel("Inventaire");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(221, 6, 168, 32);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 32));

		JButton btnNewButton_2 = new JButton("Cancel");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_2.setBounds(256, 430, 89, 23);
		panel.add(btnNewButton_2);
		Connexion.Connect(con, pst, rs);
	}

	String pnoo;

	String newpno;

	public Inventory(String pno) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(this);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(14, 46, 130));
		panel.setBounds(0, 0, 619, 459);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Prescription ID");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(23, 56, 131, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Medicament code");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(23, 96, 131, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Medicament nom");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(23, 136, 131, 14);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Quantit\u00E9");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(23, 176, 131, 14);
		panel.add(lblNewLabel_4);

		final JLabel labelpid = new JLabel("#");
		labelpid.setForeground(Color.WHITE);
		labelpid.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelpid.setBounds(164, 56, 131, 14);
		panel.add(labelpid);

		txtcode = new JTextField();
		txtcode.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					String code = txtcode.getText();

					try {
						pst = con.prepareStatement("select * from item where itemid = ?");

						pst.setString(1, code);
						rs = pst.executeQuery();

						if (rs.next() == false) {
							JOptionPane.showMessageDialog(null, "Medicament non trouv�", "gg",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							String name = rs.getString("itemname");
							txtname.setText(name.trim());
						}

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

			}
		});
		txtcode.setBounds(164, 95, 144, 20);
		panel.add(txtcode);
		txtcode.setColumns(10);

		txtname = new JTextField();
		txtname.setColumns(10);
		txtname.setBounds(164, 135, 144, 20);
		panel.add(txtname);

		final JSpinner txtqnt = new JSpinner();
		txtqnt.setBounds(164, 175, 43, 20);
		panel.add(txtqnt);

		JScrollPane sp = new JScrollPane();
		sp.setBounds(6, 240, 607, 184);
		panel.add(sp);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Item ID", "Item name", "Description", "Prix de vente", "Quantit�", "Prix d'achat" }));
		table.setBounds(10, 196, 611, 178);
		sp.setViewportView(table);

		JLabel lblNewLabel_1_2 = new JLabel("Total");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(371, 58, 59, 14);
		panel.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Pay\u00E9");
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(371, 96, 59, 14);
		panel.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Balance");
		lblNewLabel_1_4.setForeground(Color.WHITE);
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_4.setBounds(371, 138, 59, 14);
		panel.add(lblNewLabel_1_4);

		txtbalance = new JTextField();
		txtbalance.setColumns(10);
		txtbalance.setBounds(440, 135, 144, 20);
		panel.add(txtbalance);

		txtpaye = new JTextField();
		txtpaye.setColumns(10);
		txtpaye.setBounds(440, 95, 144, 20);
		panel.add(txtpaye);

		txttotal = new JTextField();
		txttotal.setColumns(10);
		txttotal.setBounds(440, 55, 144, 20);
		panel.add(txttotal);

		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String code = txtcode.getText();
				try {
					pst = con.prepareStatement("select * from item where itemid = ?");
					pst.setString(1, code);
					rs = pst.executeQuery();

					while (rs.next()) {
						int currentqnt;
						int sellprice;
						int qnt;

						currentqnt = rs.getInt("qnt");
						sellprice = rs.getInt("sellprice");

						qnt = Integer.parseInt(txtqnt.getValue().toString());

						int total = sellprice * qnt;

						Integer Total = new Integer(total);
						Integer Sellprice = new Integer(sellprice);

						if (qnt >= currentqnt) {
							JOptionPane.showMessageDialog(null, "Quantit� disponible" + currentqnt, "grr",
									JOptionPane.INFORMATION_MESSAGE);
							JOptionPane.showMessageDialog(null, "Quantit� insuffisante", "grr",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							DefaultTableModel df = (DefaultTableModel) table.getModel();
							df.addRow(new Object[] { labelpid.getText(), txtcode.getText(), txtname.getText(),
									Sellprice, txtqnt.getValue().toString(), Total, });

							int sum = 0;

							for (int i = 0; i < table.getRowCount(); i++) {

								sum = sum + Integer.parseInt(table.getValueAt(i, 5).toString());

							}

							txttotal.setText(Integer.toString(sum));
							txtcode.setText("");
							txtname.setText("");

							txtcode.requestFocus();

						}
					}

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
		btnNewButton.setBounds(191, 206, 103, 23);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int pay = (Integer.parseInt(txtpaye.getText()));
				int totalcost = (Integer.parseInt(txttotal.getText()));

				int bal = pay - totalcost;

				txtbalance.setText(String.valueOf(bal));

				sales();

			}
		});
		btnNewButton_1.setBounds(330, 206, 103, 23);
		panel.add(btnNewButton_1);

		JLabel lblNewLabel = new JLabel("Inventaire");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(221, 6, 168, 32);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 32));

		JButton btnNewButton_2 = new JButton("Cancel");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_2.setBounds(256, 430, 89, 23);
		panel.add(btnNewButton_2);
		Connexion.Connect(con, pst, rs);
		this.pnoo = pno;

		newpno = pnoo;

		labelpid.setText(newpno);
	}
}
