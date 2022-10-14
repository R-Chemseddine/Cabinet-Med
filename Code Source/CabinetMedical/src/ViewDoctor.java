import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ViewDoctor extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewDoctor frame = new ViewDoctor();
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

	public void Doctor_table() {

		try {
			pst = con.prepareStatement("select * from doctor");

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
	public ViewDoctor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 625, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(this);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(14, 46, 130));
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(0, 0, 609, 438);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane sp = new JScrollPane();
		sp.setBounds(6, 58, 597, 339);
		panel.add(sp);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Doctor N�", "Nom", "Sp�cialit�",
				"Qualification", "Rendez-vous", "N� telephone", "Chambre N�" }));
		table.getTableHeader();
		table.setBounds(10, 11, 569, 320);
		sp.setViewportView(table);

		JButton btnNewButton = new JButton("Close");
		btnNewButton.setBounds(254, 409, 89, 23);
		panel.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Doctors List");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(206, 14, 202, 32);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 32));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		Connexion.Connect(con, pst, rs);
		Doctor_table();
	}

}
