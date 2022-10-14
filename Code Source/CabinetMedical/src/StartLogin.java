import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class StartLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtusername;
	private JPasswordField txtpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartLogin frame = new StartLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection con = null;
	PreparedStatement pst;
	ResultSet rs;

	/**
	 * Create the frame.
	 */
	public StartLogin() {
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
		panel.setBorder(null);
		panel.setBounds(0, 0, 615, 393);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(37, 82, 77, 14);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(37, 155, 77, 14);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("User Type");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(37, 230, 77, 14);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblNewLabel_2);

		txtusername = new JTextField();
		txtusername.setBounds(124, 80, 123, 20);
		panel.add(txtusername);
		txtusername.setColumns(10);

		txtpassword = new JPasswordField();
		txtpassword.setBounds(124, 153, 123, 20);
		panel.add(txtpassword);

		final JComboBox txtusertype = new JComboBox();
		txtusertype.setBounds(124, 227, 123, 22);
		txtusertype.setModel(new DefaultComboBoxModel(new String[] { "pharmacist", "doctor", "receptionist" }));
		panel.add(txtusertype);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(49, 307, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String username = txtusername.getText();
				String password = txtpassword.getText();
				String usertype = txtusertype.getSelectedItem().toString();

				Connexion.Connect(con, pst, rs);
				try {
					pst = con.prepareStatement(
							"select * from 'user' where username = ? and password = ? and usertype = ?");

					pst.setString(1, username);
					pst.setString(2, password);
					pst.setString(3, usertype);

					rs = pst.executeQuery();

					if (rs.next()) {
						int userid = rs.getInt("id");
						setVisible(false);

						new Main(userid, username, usertype).setVisible(true);

					} else {
						JOptionPane.showMessageDialog(null, "Username or password do not match !", "grr",
								JOptionPane.INFORMATION_MESSAGE);
						txtusername.setText("");
						txtpassword.setText("");
						txtusertype.setSelectedIndex(-1);
						txtusername.requestFocus();
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
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.setBounds(148, 307, 89, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel.add(btnNewButton_1);

		JLabel lblNewLabel_4 = new JLabel("Login");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel_4.setBounds(280, 21, 77, 28);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("R.ChemsEddine");
		lblNewLabel_5.setBounds(516, 371, 99, 16);
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_3 = new JLabel("");
		ImageIcon img = new ImageIcon(this.getClass().getClassLoader().getResource("medical-5.png"));
		lblNewLabel_3.setIcon(img);
		lblNewLabel_3.setBounds(-11, 0, 626, 393);
		panel.add(lblNewLabel_3);

	}
}
