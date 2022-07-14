package farmacie.gestiune;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
public class Farmacie extends JFrame {

	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField numeFarmacie;
    private JTextField adresa;
    private JTextField oras;
    private JTextField telefon;
    private JButton btnNewButton;
    public Farmacie() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Roxi\\Desktop\\logo.jpg"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblPharmacy = new JLabel("Adaugare farmacie");
        lblPharmacy.setFont(new Font("Times New Roman", Font.PLAIN, 42));
        lblPharmacy.setBounds(362, 52, 325, 50);
        contentPane.add(lblPharmacy);

        JLabel lblName = new JLabel("Nume");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblName.setBounds(58, 152, 99, 43);
        contentPane.add(lblName);

        JLabel lblAdresa = new JLabel("Adresa");
        lblAdresa.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblAdresa.setBounds(58, 243, 110, 29);
        contentPane.add(lblAdresa);

        JLabel lblOras = new JLabel("Oras");
        lblOras.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblOras.setBounds(542, 159, 99, 29);
        contentPane.add(lblOras);
        
        JLabel lblTelefon = new JLabel("Telefon");
        lblTelefon.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblTelefon.setBounds(542, 250, 110, 29);
        contentPane.add(lblTelefon);

        numeFarmacie = new JTextField();
        numeFarmacie.setFont(new Font("Tahoma", Font.PLAIN, 32));
        numeFarmacie.setBounds(214, 151, 228, 50);
        contentPane.add(numeFarmacie);
        numeFarmacie.setColumns(10);

        adresa = new JTextField();
        adresa.setFont(new Font("Tahoma", Font.PLAIN, 32));
        adresa.setBounds(214, 235, 228, 50);
        contentPane.add(adresa);
        adresa.setColumns(10);

        oras = new JTextField();
        oras.setFont(new Font("Tahoma", Font.PLAIN, 31));
        oras.setBounds(707, 151, 228, 50);
        contentPane.add(oras);
        oras.setColumns(10);

        telefon = new JTextField();
        telefon.setFont(new Font("Tahoma", Font.PLAIN, 32));
        telefon.setBounds(707, 242, 228, 50);
        contentPane.add(telefon);
        telefon.setColumns(10);

       
        
        

        btnNewButton = new JButton("Register");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nume = numeFarmacie.getText();
                String adresaFarmacie = adresa.getText();
                String city = oras.getText();
                String mob = telefon.getText();
                String jdbcURL="jdbc:mysql://localhost:3306/gestiune_farmacii";
        		String username="root";
        		String password="";
                int len = mob.length();
                String msg = "" + nume;
                msg += " \n";
                if (len != 10) {
                    JOptionPane.showMessageDialog(btnNewButton, "Introdu un numar de telefon valid");
                }
                

                try {
                    Connection con = DriverManager.getConnection(jdbcURL, username, password);
                    if (con!=null) {
        				System.out.println("Conectat la baza de date");
        			}
                    String sql = "insert into farmacie(nume, adresa, oras, nr_telefon) values('"+nume+"','"+adresaFarmacie+"','"+city+"','"+mob+"')";
                    PreparedStatement s=con.prepareStatement(sql);
        			int x=s.executeUpdate();
                    if (x == 0) {
                        JOptionPane.showMessageDialog(btnNewButton, "Deja exista");
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton,
                            "Bine ai venit, " + msg + "! Contul e creat cu succes!");
                    }
                    con.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnNewButton.setBounds(399, 447, 259, 74);
        contentPane.add(btnNewButton);
    }

}
