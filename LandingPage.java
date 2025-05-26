import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LandingPage extends JFrame{
        JLabel heading1 = new JLabel("Doldam");
        JLabel heading2 = new JLabel("Hospital");
        JButton bookButton = new JButton("Appointment");
        JButton logiButton = new JButton("Log In");

        LandingPage() {
                setIconImage(new ImageIcon("assets\\icon\\icon.png").getImage());
                setTitle("Doldam Hospital");
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(700, 500);
                setLayout(null);
                setResizable(false);
                setLocationRelativeTo(null);

                heading1.setBounds(240, 86, 279, 79);
                heading2.setBounds(290, 152, 157, 38);
                heading1.setFont(new Font("Arial", Font.PLAIN, 64));
                heading2.setFont(new Font("Arial", Font.PLAIN, 32));

                bookButton.setBounds(187, 310, 142, 55);
                bookButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                new BookingPage();
                                dispose();
                        }
                });

                logiButton.setBounds(370, 310, 142, 55);
                logiButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                new LogInPage();
                                dispose();
                        }
                });

                Component[] components = {
                                        heading1,
                                        heading2,
                                        bookButton,
                                        logiButton
                                };
                for (Component component : components) {
                    add(component);
                }

                setVisible(true);
        }
}