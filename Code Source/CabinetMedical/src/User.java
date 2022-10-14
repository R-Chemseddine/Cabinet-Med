import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

public class User extends JFrame {

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtusername;
	private JPasswordField txtpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User frame = new User();
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

	/**
	 * Create the frame.
	 */
	public User() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 627, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(this);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 139, 139));
		panel.setBounds(0, 0, 621, 399);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setForeground(new Color(240, 248, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(27, 101, 61, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setForeground(new Color(240, 248, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(27, 148, 61, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setForeground(new Color(240, 248, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(27, 194, 61, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("User Type");
		lblNewLabel_3.setForeground(new Color(240, 248, 255));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(27, 236, 75, 20);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("User creation");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel_4.setBounds(228, 28, 156, 21);
		panel.add(lblNewLabel_4);

		txtname = new JTextField();
		txtname.setBounds(100, 101, 156, 20);
		panel.add(txtname);
		txtname.setColumns(10);

		txtusername = new JTextField();
		txtusername.setBounds(100, 148, 156, 20);
		panel.add(txtusername);
		txtusername.setColumns(10);

		txtpassword = new JPasswordField();
		txtpassword.setBounds(100, 194, 156, 20);
		panel.add(txtpassword);

		final JComboBox txtusertype = new JComboBox();
		txtusertype.setModel(new DefaultComboBoxModel(new String[] { "pharmacist", "doctor", "receptionist" }));
		txtusertype.setBounds(100, 237, 156, 22);
		txtusertype.setSelectedIndex(-1);
		panel.add(txtusertype);

		JButton btnNewButton = new JButton("Create");

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtname.getText();
				String username = txtusername.getText();
				String password = txtpassword.getText();
				String usertype = txtusertype.getSelectedItem().toString();

				Connexion.Connect(con, pst, rs);
				try {
					pst = con.prepareStatement("insert into user(name,username,password,usertype)values(?,?,?,?)");
					pst.setString(1, name);
					pst.setString(2, username);
					pst.setString(3, password);
					pst.setString(4, usertype);

					pst.executeUpdate();

					JOptionPane.showMessageDialog(null, "user created successfuly !", "gg",
							JOptionPane.INFORMATION_MESSAGE);

					txtname.setText("");
					txtusername.setText("");
					txtpassword.setText("");
					txtusertype.setSelectedIndex(-1);
					txtname.requestFocus();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					try {

						pst.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
		});
		btnNewButton.setBounds(44, 313, 89, 23);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(149, 313, 89, 23);
		panel.add(btnNewButton_1);

		JLabel lblNewLabel_5 = new JLabel("New label");
		// Image img = new
		// ImageIcon(this.getClass().getResource("/medical-5.png")).getImage();
		ImageIcon img = new ImageIcon(this.getClass().getClassLoader().getResource("medical-5.png"));
		lblNewLabel_5.setIcon(img);
		lblNewLabel_5.setBounds(0, 0, 621, 399);
		panel.add(lblNewLabel_5);

	}
}
