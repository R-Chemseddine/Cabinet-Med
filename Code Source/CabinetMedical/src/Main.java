import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	JLabel labelusername;
	JLabel labelusertype;

	int idd;
	String utype;

	int newid;
	String username;
	String usertype;

	public Main() {

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 728, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(this);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 128));
		panel_1.setBounds(0, 0, 719, 392);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setForeground(new Color(230, 230, 250));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(256, 145, 69, 14);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("UserType");
		lblNewLabel_3.setForeground(new Color(230, 230, 250));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(256, 179, 69, 20);
		panel_1.add(lblNewLabel_3);

		labelusername = new JLabel("New label");
		labelusername.setForeground(Color.WHITE);
		labelusername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelusername.setBounds(352, 145, 91, 14);
		panel_1.add(labelusername);

		labelusertype = new JLabel("New label");
		labelusertype.setForeground(Color.WHITE);
		labelusertype.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelusertype.setBounds(352, 182, 91, 14);
		panel_1.add(labelusertype);

		JButton btnNewButton_4 = new JButton("Ventes");
		btnNewButton_4.setBounds(610, 345, 90, 24);
		panel_1.add(btnNewButton_4);

		JButton btnpatient = new JButton("Patient");
		btnpatient.setBounds(31, 74, 158, 23);
		panel_1.add(btnpatient);

		JButton btndoctor = new JButton("Medecin");
		btndoctor.setBounds(31, 108, 158, 23);
		panel_1.add(btndoctor);

		JButton btncreatchanel = new JButton("Cr\u00E9er rendez-vous");
		btncreatchanel.setBounds(31, 142, 158, 23);
		panel_1.add(btncreatchanel);

		JButton btnviewchanel = new JButton("Voir rendez-vous");
		btnviewchanel.setBounds(31, 176, 158, 23);
		panel_1.add(btnviewchanel);

		JButton btnviewprescriptions = new JButton("Voir prescriptions");
		btnviewprescriptions.setBounds(31, 210, 158, 23);
		panel_1.add(btnviewprescriptions);

		JButton btncreatitem = new JButton("Cr\u00E9er medicament");
		btncreatitem.setBounds(31, 244, 158, 23);
		panel_1.add(btncreatitem);

		JButton btncreatuser = new JButton("Cr\u00E9er utilisateur");
		btncreatuser.setBounds(31, 278, 158, 23);
		panel_1.add(btncreatuser);

		JButton btnviewdoctor = new JButton("Voir docteur");
		btnviewdoctor.setBounds(31, 312, 158, 23);
		panel_1.add(btnviewdoctor);

		JButton btnlogout = new JButton("Logout");
		btnlogout.setBounds(31, 346, 158, 23);
		panel_1.add(btnlogout);
		
		JLabel lblNewLabel_5 = new JLabel("Noname Cabinet");
		lblNewLabel_5.setFont(new Font("SansSerif", Font.BOLD, 32));
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(256, 11, 260, 66);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel = new JLabel("R.ChemsEddine");
		lblNewLabel.setBounds(621, 370, 92, 16);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_4 = new JLabel("");
		//Image img = new ImageIcon(this.getClass().getResource("/medical-54596.png")).getImage();
		ImageIcon img = new ImageIcon(this.getClass().getClassLoader().getResource("medical-54596.png"));
		lblNewLabel_4.setIcon(img);
		lblNewLabel_4.setBounds(0, 0, 727, 399);
		panel_1.add(lblNewLabel_4);
		
		
		
		
		btnlogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				StartLogin u = new StartLogin();
				u.setVisible(true);
			}
		});
		btnviewdoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewDoctor v = new ViewDoctor();
				v.setVisible(true);
			}
		});
		btncreatuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User u = new User();
				u.setVisible(true);
			}
		});
		btncreatitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item i = new Item();
				i.setVisible(true);
			}
		});
		btnviewprescriptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewPrescription vp = new ViewPrescription();
				vp.setVisible(true);
			}
		});
		btnviewchanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewChannel(idd).setVisible(true);
			}
		});
		btncreatchanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Channel c = new Channel();
				c.setVisible(true);
			}
		});
		btndoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Doctor d = new Doctor();
				d.setVisible(true);
			}
		});
		btnpatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Patient p = new Patient();
				p.setVisible(true);
			}
		});
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Report r = new Report();
				r.setVisible(true);
			}
		});

	}

	/**
	 * Create the frame.
	 */
	public Main(int id, String username, String usertype) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 728, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(this);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 128));
		panel_1.setBounds(0, 0, 719, 392);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setForeground(new Color(230, 230, 250));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(256, 145, 69, 14);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("UserType");
		lblNewLabel_3.setForeground(new Color(230, 230, 250));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(256, 179, 69, 20);
		panel_1.add(lblNewLabel_3);

		labelusername = new JLabel("New label");
		labelusername.setForeground(Color.WHITE);
		labelusername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelusername.setBounds(352, 145, 91, 14);
		panel_1.add(labelusername);

		labelusertype = new JLabel("New label");
		labelusertype.setForeground(Color.WHITE);
		labelusertype.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelusertype.setBounds(352, 182, 91, 14);
		panel_1.add(labelusertype);

		JButton btnNewButton_4 = new JButton("Ventes");
		btnNewButton_4.setBounds(610, 345, 90, 24);
		panel_1.add(btnNewButton_4);

		JButton btnpatient = new JButton("Patient");
		btnpatient.setBounds(31, 74, 158, 23);
		panel_1.add(btnpatient);

		JButton btndoctor = new JButton("Medecin");
		btndoctor.setBounds(31, 108, 158, 23);
		panel_1.add(btndoctor);

		JButton btncreatchanel = new JButton("Cr\u00E9er rendez-vous");
		btncreatchanel.setBounds(31, 142, 158, 23);
		panel_1.add(btncreatchanel);

		JButton btnviewchanel = new JButton("Voir rendez-vous");
		btnviewchanel.setBounds(31, 176, 158, 23);
		panel_1.add(btnviewchanel);

		JButton btnviewprescriptions = new JButton("Voir prescriptions");
		btnviewprescriptions.setBounds(31, 210, 158, 23);
		panel_1.add(btnviewprescriptions);

		JButton btncreatitem = new JButton("Cr\u00E9er medicament");
		btncreatitem.setBounds(31, 244, 158, 23);
		panel_1.add(btncreatitem);

		JButton btncreatuser = new JButton("Cr\u00E9er utilisateur");
		btncreatuser.setBounds(31, 278, 158, 23);
		panel_1.add(btncreatuser);

		JButton btnviewdoctor = new JButton("Voir docteur");
		btnviewdoctor.setBounds(31, 312, 158, 23);
		panel_1.add(btnviewdoctor);

		JButton btnlogout = new JButton("Logout");
		btnlogout.setBounds(31, 346, 158, 23);
		panel_1.add(btnlogout);
		
		JLabel lblNewLabel_5 = new JLabel("Noname Cabinet");
		lblNewLabel_5.setFont(new Font("SansSerif", Font.BOLD, 32));
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(256, 11, 260, 66);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel = new JLabel("R.ChemsEddine");
		lblNewLabel.setBounds(621, 370, 92, 16);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_4 = new JLabel("");
		//Image img = new ImageIcon(this.getClass().getResource("/medical-54596.png")).getImage();
		ImageIcon img = new ImageIcon(this.getClass().getClassLoader().getResource("medical-54596.png"));
		lblNewLabel_4.setIcon(img);
		lblNewLabel_4.setBounds(0, 0, 727, 399);
		panel_1.add(lblNewLabel_4);
		
		
		btnlogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				StartLogin u = new StartLogin();
				u.setVisible(true);
			}
		});
		btnviewdoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewDoctor v = new ViewDoctor();
				v.setVisible(true);
			}
		});
		btncreatuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User u = new User();
				u.setVisible(true);
			}
		});
		btncreatitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item i = new Item();
				i.setVisible(true);
			}
		});
		btnviewprescriptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewPrescription vp = new ViewPrescription();
				vp.setVisible(true);
			}
		});
		btnviewchanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewChannel(idd).setVisible(true);
			}
		});
		btncreatchanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Channel c = new Channel();
				c.setVisible(true);
			}
		});
		btndoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Doctor d = new Doctor();
				d.setVisible(true);
			}
		});
		btnpatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Patient p = new Patient();
				p.setVisible(true);
			}
		});
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Report r = new Report();
				r.setVisible(true);
			}
		});

		this.username = username;
		labelusername.setText(username);
		this.usertype = usertype;
		labelusertype.setText(usertype);
		this.newid = id;

		idd = newid;

		utype = labelusertype.getText();

		if (utype.equals("doctor")) {
			btncreatchanel.setVisible(false);
			btnpatient.setVisible(false);
			btncreatuser.setVisible(false);
			btnviewprescriptions.setVisible(false);
			btncreatitem.setVisible(false);
			btnNewButton_4.setVisible(false);
		} else if (utype.equals("receptionist")) {
			btndoctor.setVisible(false);
			btnNewButton_4.setVisible(false);
			btnviewprescriptions.setVisible(false);
			btncreatitem.setVisible(false);
			btnviewchanel.setVisible(false);
		} else if (utype.equals("pharmacist")) {
			btnpatient.setVisible(false);
			btndoctor.setVisible(false);
			btncreatchanel.setVisible(false);
			btnviewchanel.setVisible(false);
			btncreatuser.setVisible(false);
			btnviewdoctor.setVisible(false);
		}

	}
}
