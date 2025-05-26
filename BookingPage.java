import Login.UsersAndPass;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class BookingPage extends JFrame{
        JLabel errorMsg = new JLabel("Please check your information form", JLabel.CENTER);

        JButton returnButton = new JButton("<--");
        JButton bookButton = new JButton("Book appointment");

        JLabel nameLabel = new JLabel("Name: ");
        JLabel ageLabel = new JLabel("Age: ");
        JLabel contactLabel = new JLabel("Contact: ");
        JLabel doctorLabel = new JLabel("Select doctor: ");
        JLabel dateLabel = new JLabel("Date: ");

        JTextField nameInput = new JTextField();
        JTextField ageInput = new JTextField();
        JTextField contactInput = new JTextField();
        JComboBox<String> selectDate;

        static String name;
        static String age;
        static String contact;
        static String date;
        static String doctor;

        JComboBox<String> selectDoctor;

        BookingPage(){
                setIconImage(new ImageIcon("assets\\icon\\icon.png").getImage());
                setTitle("Book Appointment");
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(700, 500);
                setLayout(null);
                setLocationRelativeTo(null);
                setResizable(false);

                returnButton.setBounds(9, 9, 76, 30);
                returnButton.addActionListener(e -> {
                        new LandingPage(); 
                        dispose();
                });

                errorMsg.setBounds(158, 44, 396, 29);
                errorMsg.setFont(new Font("Arial", Font.PLAIN, 24));
                errorMsg.setForeground(Color.RED);
                errorMsg.setVisible(false);

                nameLabel.setBounds(210, 106, 87, 20);
                nameInput.setBounds(325, 106, 164, 20);
                nameLabel.setFont(new Font("Arial", Font.PLAIN, 12));

                ageLabel.setBounds(210, 137, 87, 20);
                ageInput.setBounds(325, 137, 164, 20);
                ageLabel.setFont(new Font("Arial", Font.PLAIN, 12));

                contactLabel.setBounds(210, 168, 87, 20);
                contactInput.setBounds(325, 168, 164, 20);
                contactLabel.setFont(new Font("Arial", Font.PLAIN, 12));

                
                doctorLabel.setBounds(210, 199, 87, 20);
                doctorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                
                bookButton.setBounds(279, 315, 142, 55);
                bookButton.addActionListener(e -> handleinput());                
                
                dateLabel.setBounds(210, 230, 87, 20);

                selectDate = new JComboBox<>(getDates());
                selectDate.setBounds(325, 230, 164, 20);

                UsersAndPass usersAndPass = new UsersAndPass();
                String[] doctors = (String[])usersAndPass.getNames().toArray(String[]::new);
                selectDoctor = new JComboBox<>(doctors);
                selectDoctor.setBounds(325, 199, 164, 20);

                displayUI();

                setVisible(true);
        }

        BookingPage(String currentDoctor) {
                setIconImage(new ImageIcon("assets\\icon\\icon.png").getImage());
                setTitle("Book Appointment");
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(700, 500);
                setLayout(null);
                setLocationRelativeTo(null);
                setResizable(false);

                returnButton.setBounds(9, 9, 76, 30);
                returnButton.addActionListener(e -> {
                        new DoctorsPage(currentDoctor); 
                        dispose();
                });

                errorMsg.setBounds(158, 44, 396, 29);
                errorMsg.setFont(new Font("Arial", Font.PLAIN, 24));
                errorMsg.setForeground(Color.RED);
                errorMsg.setVisible(false);

                nameLabel.setBounds(210, 106, 87, 20);
                nameInput.setBounds(325, 106, 164, 20);
                nameLabel.setFont(new Font("Arial", Font.PLAIN, 12));

                ageLabel.setBounds(210, 137, 87, 20);
                ageInput.setBounds(325, 137, 164, 20);
                ageLabel.setFont(new Font("Arial", Font.PLAIN, 12));

                contactLabel.setBounds(210, 168, 87, 20);
                contactInput.setBounds(325, 168, 164, 20);
                contactLabel.setFont(new Font("Arial", Font.PLAIN, 12));

                
                doctorLabel.setBounds(210, 199, 87, 20);
                doctorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                
                bookButton.setBounds(279, 315, 142, 55);
                bookButton.addActionListener(e -> handleinput(currentDoctor));                
                
                dateLabel.setBounds(210, 230, 87, 20);

                selectDate = new JComboBox<>(getDates());
                selectDate.setBounds(325, 230, 164, 20);

                String[] doctors = {currentDoctor};
                selectDoctor = new JComboBox<>(doctors);
                selectDoctor.setBounds(325, 199, 164, 20);

                displayUI();

                setVisible(true);
        }

        private void displayUI() {
                Component[] components = {
                                        errorMsg,
                                        returnButton, 
                                        nameLabel, 
                                        nameInput, 
                                        ageLabel, 
                                        ageInput, 
                                        contactLabel, 
                                        contactInput, 
                                        selectDate,
                                        dateLabel, 
                                        doctorLabel, 
                                        bookButton, 
                                        selectDoctor
                };
                for (Component component : components) {
                    add(component);
                }
        }

        private String[] getDates() {
                LocalDate localDate = LocalDate.now();
                String month = localDate.getMonth().toString();
                int dateTomorrow = localDate.plusDays(1).getDayOfMonth();
                int dateAfterTomorrow = localDate.plusDays(2).getDayOfMonth();
                int thirdDateAfterTom = localDate.plusDays(3).getDayOfMonth();

                String[] dates = {
                                month + " " + dateTomorrow,
                                month + " " + dateAfterTomorrow,
                                month + " " + thirdDateAfterTom,
                };
                return  dates;
        }

        private void handleinput() {
                boolean isInputEmpty = nameInput.getText().isEmpty() ||
                                        ageInput.getText().isEmpty() ||
                                        contactInput.getText().isEmpty();

                if (isInputEmpty) {
                        errorMsg.setVisible(true);
                }
                else {
                        saveInput();
                        new AfterBookingPage();
                        dispose();
                }
        }

        private void handleinput(String currentDoc) {
                boolean isInputEmpty = nameInput.getText().isEmpty() ||
                                        ageInput.getText().isEmpty() ||
                                        contactInput.getText().isEmpty();

                if (isInputEmpty) {
                        errorMsg.setVisible(true);
                }
                else {
                        saveInput();
                        new AfterBookingPage(currentDoc);
                        dispose();
                }
        }

        private void saveInput() {
                name = nameInput.getText();
                age = ageInput.getText();
                contact = contactInput.getText();
                date = (String) selectDate.getSelectedItem();
                doctor = (String) selectDoctor.getSelectedItem();
                savePatientInfo(name, "names");
                savePatientInfo(age, "ages");
                savePatientInfo(contact, "contacts");
                savePatientInfo(date, "appointment-dates");
                savePatientInfo(doctor, "appointed-doctor");
        }

        private void savePatientInfo(String info, String location) {
                try (FileWriter writer = new FileWriter("patients\\" + location + ".txt", true)){
                        writer.write(info + "\n");
                } 
                catch (IOException ex) {
                        System.out.println(ex);
                }
        }   
}