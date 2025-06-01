import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DoctorsPage extends JFrame{
        JPanel container = new JPanel();
        JPanel sidePanel = new JPanel();
        static String doctor;

        ArrayList<Patient> patients = new ArrayList<>();
        
        DoctorsPage(String currentDoc) {
                doctor = currentDoc;
                
                setIconImage(new ImageIcon("assets\\icon\\icon.png").getImage());
                setTitle("Appointments");
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(900, 600);
                setLayout(new BorderLayout());
                
                // set rows by using the length of the arrays of patients
                container.setLayout(new GridLayout(patients.size() + 10, 1, 10, 10));

                sidePanel.setLayout(new FlowLayout());
                sidePanel.setAlignmentX(CENTER_ALIGNMENT);
                sidePanel.setPreferredSize(new Dimension((int)(getWidth() * 0.2), sidePanel.getHeight()));
                add(sidePanel, BorderLayout.WEST);

                JPanel headerContainer = new JPanel();
                headerContainer.setLayout(new BoxLayout(headerContainer, BoxLayout.X_AXIS));
                JLabel header = new JLabel("Welcome, " + currentDoc);
                header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                header.setFont(new Font("Arial", Font.PLAIN, 24));
                headerContainer.add(header);
                container.add(headerContainer);
                
                JPanel infoHeader = new JPanel();
                infoHeader.setLayout(new GridLayout(1, 4, 10, 10));
                infoHeader.add(new JLabel("Name", JLabel.CENTER));
                infoHeader.add(new JLabel("Age", JLabel.CENTER));
                infoHeader.add(new JLabel("Contact", JLabel.CENTER));
                infoHeader.add(new JLabel("Date", JLabel.CENTER));
                container.add(infoHeader);
                
                getPatientsInfo();
                displayPatient();

                JScrollPane scrollPane = new JScrollPane(container);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                add(scrollPane, BorderLayout.CENTER);

                JPanel footerContainer = new JPanel();
                container.add(footerContainer);

                Dimension SIDEPANEL_BUTTONSIZE = new Dimension(160, 45);

                JButton logOutButton = new JButton("Log Out");
                logOutButton.setPreferredSize(SIDEPANEL_BUTTONSIZE);
                logOutButton.addActionListener(e -> {
                        new LandingPage();
                        dispose();
                });

                JButton bookButton = new JButton("New Booking");
                bookButton.setPreferredSize(SIDEPANEL_BUTTONSIZE);
                bookButton.addActionListener(e -> {
                        new BookingPage(currentDoc);
                        dispose();
                });

                JLabel sidePanelHeader = new JLabel("Doldam Hospital");
                sidePanelHeader.setFont(new Font("Arial", Font.PLAIN, 16));
                sidePanelHeader.setForeground(Color.GRAY);
                sidePanelHeader.setBorder(BorderFactory.createEmptyBorder(30, 5, 30, 30));

                Component[] sidePanelComponets = {sidePanelHeader, bookButton, logOutButton};
                for (Component sidePanelButton : sidePanelComponets) {
                        sidePanel.add(sidePanelButton);
                }

                setVisible(true);
        }

        private ArrayList<String> getData(String location) {
                // get the infos individually
                ArrayList<String> info = new ArrayList<>();
                try (Scanner scanner = new Scanner(new FileReader("patients\\" + location + ".txt"))) {
                        while (scanner.hasNextLine()) {
                                info.add(scanner.nextLine());
                        }
                } 
                catch (FileNotFoundException e) {
                        System.out.println(e);
                }
                return info;
        }

        private void getPatientsInfo() {
                // group those infos in an array of patients class
                ArrayList<String> names = getData("names");
                ArrayList<String> ages = getData("ages");
                ArrayList<String> contacts = getData("contacts");
                ArrayList<String> doctors = getData("appointed-doctor");
                ArrayList<String> dates = getData("appointment-dates");

                for (int i = 0; i < names.size(); i++) {
                        if (doctors.get(i).equals(doctor)) {
                                patients.add(new Patient(names.get(i), ages.get(i), contacts.get(i), dates.get(i), doctors.get(i)));
                        }
                }
        }

        private void displayPatient() {
                for (int i = 0; i < patients.size(); i++) {
                        container.add(new AppointmentPanel(patients.get(i)));
                }
        }

        public static class AppointmentPanel extends JPanel {
                AppointmentPanel(Patient patient) {
                        setPreferredSize(new Dimension(578, 35));
                        setBackground(new Color(217, 217, 217));
                        setLayout(new GridLayout(1, 4, 10, 10));

                        JLabel name = new JLabel(patient.name, JLabel.CENTER);
                        JLabel age = new JLabel(patient.age, JLabel.CENTER);
                        JLabel contact = new JLabel(patient.contact, JLabel.CENTER);
                        JLabel date = new JLabel(patient.appointmentDate, JLabel.CENTER);

                        Component[] components = {name, age, contact, date};
                        for (Component component : components) {
                                add(component);
                        }
                }
        }
}
