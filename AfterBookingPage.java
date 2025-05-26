import java.awt.Component;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AfterBookingPage extends JFrame{
                JLabel nameLabel = new JLabel("Name: ");
                JLabel ageLabel = new JLabel("Age: ");
                JLabel contactLabel = new JLabel("Contact: ");
                JLabel doctorLabel = new JLabel("Doctor: ");
                JLabel dateLabel = new JLabel("Date: ");

                JLabel name = new JLabel(BookingPage.name);
                JLabel age = new JLabel(BookingPage.age);
                JLabel contact = new JLabel(BookingPage.contact);
                JLabel doctor = new JLabel(BookingPage.doctor);
                JLabel date = new JLabel(BookingPage.date);

                JButton doneButton = new JButton("Done");

                AfterBookingPage() {
                        setIconImage(new ImageIcon("assets\\icon\\icon.png").getImage());
                        setTitle("Book Appointment");
                        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        setSize(700, 500);
                        setLayout(null);
                        setLocationRelativeTo(null);
                        setResizable(false);

                        nameLabel.setBounds(210, 106, 87, 20);
                        name.setBounds(325, 106, 164, 20);
                        nameLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                        name.setFont(new Font("Arial", Font.PLAIN, 12));

                        ageLabel.setBounds(210, 137, 87, 20);
                        age.setBounds(325, 137, 164, 20);
                        ageLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                        age.setFont(new Font("Arial", Font.PLAIN, 12));

                        contactLabel.setBounds(210, 168, 87, 20);
                        contact.setBounds(325, 168, 164, 20);
                        contactLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                        contact.setFont(new Font("Arial", Font.PLAIN, 12));

                        dateLabel.setBounds(210, 230, 87, 20);
                        date.setBounds(325, 230, 164, 20);
                        dateLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                        date.setFont(new Font("Arial", Font.PLAIN, 12));

                        doctorLabel.setBounds(210, 199, 87, 20);
                        doctorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                        doctor.setFont(new Font("Arial", Font.PLAIN, 12));
                        doctor.setBounds(325, 199, 164, 20);

                        doneButton.setBounds(279, 315, 142, 55);
                        doneButton.addActionListener(e -> {
                                new LandingPage();
                                dispose();
                        });

                        Component[] components = {
                                        nameLabel,
                                        name,
                                        ageLabel,
                                        age,
                                        contactLabel,
                                        contact,
                                        dateLabel,
                                        date,
                                        doctorLabel,
                                        doctor,
                                        doneButton
                                };
                        for (Component component : components) {
                                add(component);
                        }

                        setVisible(true);
                }

                AfterBookingPage(String currentDoc) {
                        setIconImage(new ImageIcon("assets\\icon\\icon.png").getImage());
                        setTitle("Book Appointment");
                        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        setSize(700, 500);
                        setLayout(null);
                        setLocationRelativeTo(null);
                        setResizable(false);

                        nameLabel.setBounds(210, 106, 87, 20);
                        name.setBounds(325, 106, 164, 20);
                        nameLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                        name.setFont(new Font("Arial", Font.PLAIN, 12));

                        ageLabel.setBounds(210, 137, 87, 20);
                        age.setBounds(325, 137, 164, 20);
                        ageLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                        age.setFont(new Font("Arial", Font.PLAIN, 12));

                        contactLabel.setBounds(210, 168, 87, 20);
                        contact.setBounds(325, 168, 164, 20);
                        contactLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                        contact.setFont(new Font("Arial", Font.PLAIN, 12));

                        dateLabel.setBounds(210, 230, 87, 20);
                        date.setBounds(325, 230, 164, 20);
                        dateLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                        date.setFont(new Font("Arial", Font.PLAIN, 12));

                        doctorLabel.setBounds(210, 199, 87, 20);
                        doctorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                        doctor.setFont(new Font("Arial", Font.PLAIN, 12));
                        doctor.setBounds(325, 199, 164, 20);

                        doneButton.setBounds(279, 315, 142, 55);
                        doneButton.addActionListener(e -> {
                                new DoctorsPage(currentDoc);
                                dispose();
                        });

                        Component[] components = {
                                        nameLabel,
                                        name,
                                        ageLabel,
                                        age,
                                        contactLabel,
                                        contact,
                                        dateLabel,
                                        date,
                                        doctorLabel,
                                        doctor,
                                        doneButton
                                };
                        for (Component component : components) {
                                add(component);
                        }

                        setVisible(true);
                }
}
