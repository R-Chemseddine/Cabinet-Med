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
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;

public class Item extends JFrame {

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtdesc;
	private JTable table;
	private JLabel labelnumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Item frame = new Item();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	int ln = 0;
	private JTextField txtsell;
	private JTextField txtbuy;
	private JTextField txtqnt;

	Connection con;
	PreparedStatement pst;
	ResultSet rs;

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

	public void item_table() {

		try {
			pst = con.prepareStatement("select * from item");
			rs = pst.executeQuery();

			ResultSetMetaData rsm = rs.getMetaData();

			int c;
			c = rsm.getColumnCount();

			DefaultTableModel df = (DefaultTableModel) table.getModel();
			df.setRowCount(0);

			while (rs.next()) {

				Vector v2 = new Vector();

				for (int i = 1; i <= c; i++) {
					v2.add(rs.getString("itemid"));
					v2.add(rs.getString("itemname"));
					v2.add(rs.getString("description"));
					v2.add(rs.getString("sellprice"));
					v2.add(rs.getString("buyprice"));
					v2.add(rs.getString("qnt"));
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
	public Item() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(this);
		contentPane.setLayout(null);

		Connect();

		JPanel panel = new JPanel();
		panel.setBackground(new Color(14, 46, 130));
		panel.setBounds(0, 0, 884, 373);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Create Item", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBackground(new Color(240, 248, 255));
		panel_1.setBounds(6, 52, 305, 280);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Medicament ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 21, 100, 14);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nom");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 46, 84, 14);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Description");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 71, 84, 14);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Prix de vente");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(10, 96, 84, 14);
		panel_1.add(lblNewLabel_3);

		labelnumber = new JLabel("0");
		labelnumber.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelnumber.setBounds(133, 21, 158, 14);
		panel_1.add(labelnumber);

		txtname = new JTextField();
		txtname.setBounds(133, 43, 158, 20);
		panel_1.add(txtname);
		txtname.setColumns(10);

		txtdesc = new JTextField();
		txtdesc.setBounds(133, 68, 158, 20);
		panel_1.add(txtdesc);
		txtdesc.setColumns(10);

		final JButton btnNewButton_2 = new JButton("Add");

		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String itemname = txtname.getText();
				String desc = txtdesc.getText();
				String sellprice = txtsell.getText();
				String buyprice = txtbuy.getText();
				String qnt = txtqnt.getText();

				try {
					pst = con.prepareStatement(
							"insert into item(itemname,description,sellprice,buyprice,qnt)values(?,?,?,?,?)");

					pst.setString(1, itemname);
					pst.setString(2, desc);
					pst.setString(3, sellprice);
					pst.setString(4, buyprice);
					pst.setString(5, qnt);

					pst.executeUpdate();

					JOptionPane.showMessageDialog(null, "Medicament inserted !", "gg", JOptionPane.INFORMATION_MESSAGE);

					txtname.setText("");
					txtdesc.setText("");
					txtsell.setText("");
					txtbuy.setText("");
					txtqnt.setText("");
					txtname.requestFocus();

					ln++;
					labelnumber.setText(ln + "");

					item_table();

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
				String itemname = txtname.getText();
				String desc = txtdesc.getText();
				String sellprice = txtsell.getText();
				String buyprice = txtbuy.getText();
				String qnt = txtqnt.getText();
				String id = labelnumber.getText();

				try {
					pst = con.prepareStatement(
							"update item set itemname = ?, description = ?, sellprice = ?, buyprice = ?, qnt = ? where itemid = ?");

					pst.setString(1, itemname);
					pst.setString(2, desc);
					pst.setString(3, sellprice);
					pst.setString(4, buyprice);
					pst.setString(5, qnt);
					pst.setString(6, id);
					pst.executeUpdate();

					JOptionPane.showMessageDialog(null, "Medicament updated !", "grr", JOptionPane.INFORMATION_MESSAGE);

					txtname.setText("");
					txtdesc.setText("");
					txtsell.setText("");
					txtbuy.setText("");
					txtqnt.setText("");
					txtname.requestFocus();
					btnNewButton_2.setEnabled(true);

					ln++;
					labelnumber.setText(ln + "");

					item_table();

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
					pst = con.prepareStatement("delete from item where itemid = ?");

					pst.setString(1, n);
					pst.executeUpdate();

					JOptionPane.showMessageDialog(null, "Medicamant deleted !", "grr", JOptionPane.INFORMATION_MESSAGE);

					txtname.setText("");
					txtdesc.setText("");
					txtsell.setText("");
					txtbuy.setText("");
					txtqnt.setText("");
					txtname.requestFocus();
					btnNewButton_2.setEnabled(true);

					ln++;
					labelnumber.setText(ln + "");

					item_table();

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

		txtsell = new JTextField();
		txtsell.setColumns(10);
		txtsell.setBounds(133, 94, 158, 20);
		panel_1.add(txtsell);

		txtbuy = new JTextField();
		txtbuy.setColumns(10);
		txtbuy.setBounds(133, 119, 158, 20);
		panel_1.add(txtbuy);

		txtqnt = new JTextField();
		txtqnt.setColumns(10);
		txtqnt.setBounds(133, 144, 158, 20);
		panel_1.add(txtqnt);

		JLabel lblNewLabel_3_1 = new JLabel("Prix d'achat");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3_1.setBounds(10, 121, 84, 14);
		panel_1.add(lblNewLabel_3_1);

		JLabel lblNewLabel_3_2 = new JLabel("Quantit\u00E9");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3_2.setBounds(10, 146, 84, 14);
		panel_1.add(lblNewLabel_3_2);

		JButton btnNewButton_3 = new JButton("Cancel");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_3.setBounds(113, 343, 89, 23);
		panel.add(btnNewButton_3);
		
		JScrollPane sp = new JScrollPane();
		sp.setBounds(323, 52, 555, 280);
		panel.add(sp);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				DefaultTableModel d1 = (DefaultTableModel) table.getModel();
				int SelectIndex = table.getSelectedRow();

				labelnumber.setText(d1.getValueAt(SelectIndex, 0).toString());
				txtname.setText(d1.getValueAt(SelectIndex, 1).toString());
				txtdesc.setText(d1.getValueAt(SelectIndex, 2).toString());
				txtsell.setText(d1.getValueAt(SelectIndex, 3).toString());
				txtbuy.setText(d1.getValueAt(SelectIndex, 4).toString());
				txtqnt.setText(d1.getValueAt(SelectIndex, 5).toString());

				btnNewButton_2.setEnabled(false);
			}
		});

		table.setModel(
				new DefaultTableModel(new Object[][] {},
						new String[] { "Item ID", "Item name", "Description", "Prix de vente", "Prix d'achat", "Quantité" }));
		table.setBounds(323, 52, 531, 280);
		sp.setViewportView(table);
		item_table();

		JLabel lblNewLabel_4 = new JLabel("Cr\u00E9er medicament");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("SansSerif", Font.BOLD, 32));
		lblNewLabel_4.setBounds(310, 6, 286, 42);
		panel.add(lblNewLabel_4);

	}
}
