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
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Report extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Report frame = new Report();
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
	
	public void sales_table() {

		try {
			pst = con.prepareStatement("select * from sales");

			rs = pst.executeQuery();

			ResultSetMetaData rsm = rs.getMetaData();

			int c;
			c = rsm.getColumnCount();

			DefaultTableModel df = (DefaultTableModel) table.getModel();
			df.setRowCount(0);

			while (rs.next()) {

				Vector v2 = new Vector();

				for (int i = 1; i <= c; i++) {
					v2.add(rs.getString("id"));
					v2.add(rs.getString("date"));
					v2.add(rs.getString("subtotal"));
					v2.add(rs.getString("pay"));
					v2.add(rs.getString("balance"));

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
	public Report() {
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
		sp.setBounds(6, 50, 597, 347);
		panel.add(sp);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Date", "Totale", "Payé", "Balance"
			}
		));
		table.setBounds(10, 11, 569, 320);
		sp.setViewportView(table);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.setBounds(252, 409, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Ventes");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(245, 6, 114, 32);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 32));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		Connect();
		sales_table();
	}

}
