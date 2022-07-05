package login;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


import client.mainGUI;
import tags.Encode;
import tags.Tags;
import javax.swing.ImageIcon;

public class loginGUI extends JFrame implements ActionListener {
	private static String NAME_FAILED = "THIS NAME CONTAINS INVALID CHARACTER. PLEASE TRY AGAIN";
	private static String NAME_EXSIST = "THIS NAME IS ALREADY USED. PLEASE TRY AGAIN";
	private static String SERVER_NOT_START = "TURN ON SERVER BEFORE START";

	private Pattern checkName = Pattern.compile("[_a-zA-Z][_a-zA-Z0-9]*");
	private JPanel contentPane;
	private JTextField txtIP;
	private JTextField txtPort;
	private JTextField txtUserName;
	int port;
	String IP, userName;
	JButton btnConnectServer;
	String file = System.getProperty("user.dir") + "\\server.txt";
	List<String> listServer = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					loginGUI frame = new loginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	void updateServer(String IP, String port) {
		txtIP.setText(IP);
		txtPort.setText(port);
	}

	String[] readFileServer() throws FileNotFoundException {
		// Read file
		System.out.println(file);
		Scanner scanner = new Scanner(new File(file));
		while (scanner.hasNext()) {
			String server = scanner.nextLine();
			System.out.println(server + "-" + port);
			listServer.add(server);
		}
		scanner.close();
		String[] array = listServer.toArray(new String[0]);
		return array;
	}

	/**
	 * Create the frame.
	 */
	public loginGUI() {
		setTitle("Login Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 504);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Chat Connect Server");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel.setBounds(173, 0, 352, 49);
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new CompoundBorder(null, UIManager.getBorder("List.noFocusBorder")), "T\u00F9y Ch\u1ECDn ", TitledBorder.CENTER, TitledBorder.TOP, null, Color.RED));
		panel.setBackground(Color.WHITE);
		panel.setBounds(53, 91, 541, 211);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblipaddressserver = new JLabel("IP Address  Server");
		lblipaddressserver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblipaddressserver.setBounds(10, 28, 136, 37);
		panel.add(lblipaddressserver);

		JLabel lblport = new JLabel("Port");
		lblport.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblport.setBounds(10, 126, 45, 13);
		panel.add(lblport);

		txtIP = new JTextField();
		txtIP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtIP.setBounds(10, 66, 157, 37);
		panel.add(txtIP);
		txtIP.setColumns(10);
		try {
			txtIP.setText(Inet4Address.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		txtPort = new JTextField();
		txtPort.setText("8080");
		txtPort.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPort.setBounds(10, 150, 157, 37);
		panel.add(txtPort);
		txtPort.setColumns(10);

		JLabel lblname = new JLabel("Your Name");
		lblname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblname.setBounds(233, 116, 84, 37);
		panel.add(lblname);

		txtUserName = new JTextField();
		txtUserName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtUserName.setBounds(234, 150, 272, 37);
		panel.add(txtUserName);
		txtUserName.setColumns(10);

		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(Color.BLUE);
		// Read Data
		String[] data = null;
		try {
			data = readFileServer();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (data == null) {
			comboBox.setVisible(false);
		} else {
			comboBox.setModel(new DefaultComboBoxModel(data));
			// Handle event
			comboBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JComboBox cb = (JComboBox) e.getSource();
					String ss = (String) cb.getSelectedItem();
					String[] s = ss.split(" ");
					updateServer(s[0], s[1]);
				}
			});
		}
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setBounds(234, 66, 272, 37);
		panel.add(comboBox);

		btnConnectServer = new JButton("Connect ");
		btnConnectServer.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnConnectServer.setIcon(new ImageIcon("C:\\Users\\Administrator\\Downloads\\DoAnChat\\DoAnChat\\image\\people.png"));
		btnConnectServer.setBounds(257, 395, 164, 49);
		btnConnectServer.addActionListener(this);
		contentPane.add(btnConnectServer);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == btnConnectServer) {
			userName = txtUserName.getText();
			IP = txtIP.getText();

			// must edit here
			if (checkName.matcher(userName).matches() && !IP.equals("")) {
				try {
					// Login
					Random rd = new Random();
					int portPeer = 10000 + rd.nextInt() % 1000;
					InetAddress ipServer = InetAddress.getByName(IP);
					int portServer = Integer.parseInt(txtPort.getText());
					Socket socketClient = new Socket(ipServer, portServer);

					String msg = Encode.getCreateAccount(userName, Integer.toString(portPeer));
					ObjectOutputStream serverOutputStream = new ObjectOutputStream(socketClient.getOutputStream());
					serverOutputStream.writeObject(msg);
					serverOutputStream.flush();
					ObjectInputStream serverInputStream = new ObjectInputStream(socketClient.getInputStream());
					msg = (String) serverInputStream.readObject();

					socketClient.close();
					if (msg.equals(Tags.SESSION_DENY_TAG)) {
						JOptionPane.showMessageDialog(this, NAME_EXSIST, "Login Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					System.out.println("Port Server Login: " + portServer);
					new mainGUI(IP, portPeer, userName, msg, portServer);
					this.dispose();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this, SERVER_NOT_START, "Login Error", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(this, NAME_FAILED, "Login Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
