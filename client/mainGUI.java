package client;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


import login.loginGUI;
import tags.Tags;
import javax.swing.ImageIcon;

@SuppressWarnings("unused")
public class mainGUI extends JFrame implements WindowListener {

	private JPanel contentPane;
	private Client clientNode;
	private static String IPClient = "", nameUser = "", dataUser = "";
	private static int portClient = 0;
	private static JList<String> listActive;
	private static int portServer;
	private String name;
	static DefaultListModel<String> model = new DefaultListModel<>();
	String file = System.getProperty("user.dir") + "\\Server.txt";
	private JButton btnSaveServer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					mainGUI frame = new mainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public mainGUI(String arg, int arg1, String name, String msg, int port_Server) throws Exception {
		IPClient = arg;
		portClient = arg1;
		nameUser = name;
		dataUser = msg;
		portServer = port_Server;
		System.out.println("Port Server Main UI: " + portServer);

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					mainGUI frame = new mainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void updateFriendMainFrame(String msg) {
		model.addElement(msg);
	}

	public static void resetList() {
		model.clear();
	}

	void SaveServer() {
		try {
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(IPClient + " " + portServer);
			bw.newLine();
			bw.close();

			JOptionPane.showMessageDialog(this, "Server đã được lưu lại.");
			btnSaveServer.setVisible(false);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/**
	 * Create the frame.
	 *
	 * @throws Exception
	 */
	public mainGUI() throws Exception {
		this.addWindowListener(this);
		setResizable(false);

		System.out.println("Port Server Main UI: " + portServer);
		updateFriendMainFrame("12");
		clientNode = new Client(IPClient, portClient, nameUser, dataUser, portServer);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setForeground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblchatclient = new JLabel("Chat Client");
		lblchatclient.setForeground(Color.RED);
		lblchatclient.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblchatclient.setBounds(166, 11, 171, 64);
		contentPane.add(lblchatclient);

		JLabel lblwelcome = new JLabel("Welcome " + nameUser);
		lblwelcome.setBackground(Color.BLACK);
		lblwelcome.setForeground(Color.WHITE);
		lblwelcome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblwelcome.setBounds(27, 80, 222, 47);
		contentPane.add(lblwelcome);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new CompoundBorder(null, UIManager.getBorder("CheckBoxMenuItem.border")), "Danh s\u00E1ch ng\u01B0\u1EDDi d\u00F9ng online", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(27, 183, 613, 325);

		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 1));

		listActive = new JList<>(model);
		listActive.setBorder(new EmptyBorder(5, 5, 5, 5));
		listActive.setBackground(Color.WHITE);
		listActive.setForeground(Color.RED);
		listActive.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		listActive.setBounds(10, 20, 577, 332);
		JScrollPane listPane = new JScrollPane(listActive);

		panel.add(listPane);

		JPanel thongtinserver = new JPanel();
		thongtinserver.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin server", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		thongtinserver.setForeground(Color.WHITE);
		thongtinserver.setBackground(Color.GRAY);
		thongtinserver.setBounds(361, 11, 290, 107);
		contentPane.add(thongtinserver);
		thongtinserver.setLayout(null);

		JLabel lblIPaddress = new JLabel("IP Address :");
		lblIPaddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIPaddress.setForeground(Color.WHITE);
		lblIPaddress.setBounds(10, 10, 85, 24);
		thongtinserver.add(lblIPaddress);

		JLabel lblportserver = new JLabel("Port Server :");
		lblportserver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblportserver.setForeground(Color.WHITE);
		lblportserver.setBounds(10, 45, 85, 15);
		thongtinserver.add(lblportserver);

		JLabel lblNewLabel_4 = new JLabel("127.0.0.1");
		try {
			lblNewLabel_4.setText(Inet4Address.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setForeground(Color.GREEN);
		lblNewLabel_4.setBounds(88, 9, 115, 24);
		thongtinserver.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel(String.valueOf(portServer));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_5.setForeground(Color.RED);
		lblNewLabel_5.setBounds(88, 44, 74, 17);
		thongtinserver.add(lblNewLabel_5);

		JLabel lblportclient = new JLabel("Port Client :");
		lblportclient.setForeground(Color.WHITE);
		lblportclient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblportclient.setBounds(10, 81, 74, 13);
		thongtinserver.add(lblportclient);

		JLabel lblNewLabel_7 = new JLabel(String.valueOf(portClient));
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_7.setForeground(new Color(255, 20, 147));
		lblNewLabel_7.setBounds(88, 80, 89, 13);
		thongtinserver.add(lblNewLabel_7);

		btnSaveServer = new JButton(" Lưu server");
		btnSaveServer.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSaveServer.setIcon(new ImageIcon("C:\\Users\\Administrator\\Downloads\\DoAnChat\\DoAnChat\\image\\diskette.png"));
		btnSaveServer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Save Server
				SaveServer();
			}
		});
		btnSaveServer.setFocusable(false);
		btnSaveServer.setBounds(456, 123, 184, 49);
		contentPane.add(btnSaveServer);
		
//		JButton btntieptuc = new JButton("Tiếp tục");
//		btntieptuc.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				loginGUI lf = new loginGUI();
//				lf.setVisible(true);
//				
//			}
//		});
//		btntieptuc.setFont(new Font("Tahoma", Font.BOLD, 20));
//		btntieptuc.setIcon(new ImageIcon("C:\\Users\\Administrator\\Downloads\\DoAnChat\\DoAnChat\\image\\fast-forward-button.png"));
//		btntieptuc.setBounds(452, 125, 162, 47);
//		contentPane.add(btntieptuc);
		listActive.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				name = listActive.getModel().getElementAt(listActive.locationToIndex(arg0.getPoint()));
				connectChat();
			}
		});
	}

	private void connectChat() {
		// TODO Auto-generated method stub
		int n = JOptionPane.showConfirmDialog(this, "Bạn có muốn kết nối với người này không?", "Kết nối",
				JOptionPane.YES_NO_OPTION);
		if (n == 0) {
			System.out.println(name);
			if (name.equals("") || Client.clientarray == null) {
				Tags.show(this, "Invaild username", false);
				return;
			}
			if (name.equals(nameUser)) {
				Tags.show(this, "Chương trình này không hổ trợ chức năng chat chính mình", false);
				return;
			}
			int size = Client.clientarray.size();
			for (int i = 0; i < size; i++) {
				if (name.equals(Client.clientarray.get(i).getName())) {
					try {
						clientNode.intialNewChat(Client.clientarray.get(i).getHost(),
								Client.clientarray.get(i).getPort(), name);
						return;
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
			Tags.show(this, "Bạn bè của bạn không tìm thấy. Vui lòng cập nhật danh sách bạn bè", false);
		}
	}

	public static int request(String msg, boolean type) {
		JFrame frameMessage = new JFrame();
		return Tags.show(frameMessage, msg, type);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		// Only debug
//		Tags.show(this, "Are you sure to leave", true);
		try {
			clientNode.exit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}
}
