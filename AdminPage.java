import Login.UsersAndPass;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class AdminPage extends JFrame{
        JPanel container = new JPanel();
        JPanel sidePanel = new JPanel();

        // create an array
        // get data
        // group data per patient and doctor
        // display infos
        ArrayList<Patient> patients = new ArrayList<>();
        ArrayList<Doctor> allDoctors = new ArrayList<>();

        AdminPage() {
                setIconImage(new ImageIcon("assets\\icon\\icon.png").getImage());
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(new Dimension(900, 600));
                setLocationRelativeTo(null);
                setTitle("Admin");
                setLayout(new BorderLayout());

                getPatientsInfo();
                getDoctorsInfo();
                
                container.setLayout(new GridLayout(patients.size() + allDoctors.size() + 6, 1, 10, 10));

                sidePanel.setLayout(new FlowLayout());
                sidePanel.setAlignmentX(CENTER_ALIGNMENT);
                sidePanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
                sidePanel.setPreferredSize(new Dimension((int)(getWidth() * 0.2), sidePanel.getHeight()));
                add(sidePanel, BorderLayout.WEST);

                JPanel patientsHeader = new JPanel();
                patientsHeader.add(new JLabel("Patients"));
                patientsHeader.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
                container.add(patientsHeader);
                
                JPanel patientsLabel = new JPanel();
                patientsLabel.setLayout(new GridLayout(1, 4, 10, 10));
                patientsLabel.add(new JLabel("Name", JLabel.CENTER));
                patientsLabel.add(new JLabel("Age", JLabel.CENTER));
                patientsLabel.add(new JLabel("Contact", JLabel.CENTER));
                patientsLabel.add(new JLabel("Date", JLabel.CENTER));
                container.add(patientsLabel);
                
                displayPatient();
                
                JPanel doctorsHeader = new JPanel();
                doctorsHeader.add(new JLabel("Doctors"));
                doctorsHeader.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
                container.add(doctorsHeader);
                
                JPanel doctorsLabel = new JPanel();
                doctorsLabel.setLayout(new GridLayout(1, 2, 10, 10));
                doctorsLabel.add(new JLabel("Name", JLabel.CENTER));
                doctorsLabel.add(new JLabel("Username", JLabel.CENTER));
                container.add(doctorsLabel);
                
                displayDoctor();
                
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

                JButton allPatientsButton = new JButton("Patients");
                allPatientsButton.setPreferredSize(SIDEPANEL_BUTTONSIZE);
                allPatientsButton.addActionListener(e -> {
                        System.out.println(1);
                });

                JButton allDoctorsButton = new JButton("Doctors");
                allDoctorsButton.setPreferredSize(SIDEPANEL_BUTTONSIZE);
                allDoctorsButton.addActionListener(e -> {
                        System.out.println(2);
                        
                });
                
                JButton eraseAllPatients = new JButton("Remov Patients");
                eraseAllPatients.setPreferredSize(SIDEPANEL_BUTTONSIZE);
                eraseAllPatients.addActionListener(e -> {
                        System.out.println(3);
                        
                });

                JButton addNewDoctor = new JButton("New Doctor");
                addNewDoctor.setPreferredSize(SIDEPANEL_BUTTONSIZE);
                addNewDoctor.addActionListener(e -> {
                        System.out.println(4);
                        
                });

                JButton removeDoctor = new JButton("Remove Doctor");
                removeDoctor.setPreferredSize(SIDEPANEL_BUTTONSIZE);
                removeDoctor.addActionListener(e -> {
                        System.out.println(5);
                        
                });
                
                Component[] sidePanelComponents = {
                                        allPatientsButton,
                                        allDoctorsButton, 
                                        addNewDoctor, 
                                        eraseAllPatients, 
                                        removeDoctor, 
                                        logOutButton
                                };
                for (Component sidePanelButton : sidePanelComponents) {
                        sidePanel.add(sidePanelButton);
                }
                
                setVisible(true);
        }

        private ArrayList<String> getData(String folder, String location) {
                ArrayList<String> data = new ArrayList<>();

                try {
                        FileReader reader = new FileReader(folder + "\\" + location + ".txt");
                        Scanner scanner = new Scanner(reader);

                        while (scanner.hasNextLine()) {
                                data.add(scanner.nextLine());
                        }
                        scanner.close();
                } 
                catch (FileNotFoundException e) {
                        System.out.println(e);
                }

                return data;
        }

        private void getPatientsInfo() {
                ArrayList<String> names = getData("patients", "names");
                ArrayList<String> ages = getData("patients", "ages");
                ArrayList<String> contacts = getData("patients", "contacts");
                ArrayList<String> doctors = getData("patients","appointed-doctor");
                ArrayList<String> dates = getData("patients","appointment-dates");

                for (int i = 0; i < names.size(); i++) {
                        patients.add(new Patient(names.get(i), ages.get(i), contacts.get(i), dates.get(i), doctors.get(i)));
                }
        }
        
        private void getDoctorsInfo() {
                UsersAndPass usersAndPass = new UsersAndPass();

                ArrayList<String> names = usersAndPass.getNames();
                ArrayList<String> usernames = usersAndPass.getUsernames();

                for (int i = 0;i < names.size(); i++) {
                        allDoctors.add(new Doctor(names.get(i), usernames.get(i)));
                }
        }

        private void displayPatient() {
                for (int i = 0; i < patients.size(); i++) {
                        container.add(new DoctorsPage.AppointmentPanel(patients.get(i)));
                }
        }
        
        private void displayDoctor() {
                for (int i = 0; i < allDoctors.size(); i++) {
                        container.add(new DoctorPanel(allDoctors.get(i)));
                }
        }


        private class DoctorPanel extends JPanel{
                DoctorPanel(Doctor doctor) {
                        setPreferredSize(new Dimension(578, 35));
                        setBackground(new Color(217, 217, 217));
                        setLayout(new GridLayout(1, 2));

                        JLabel name = new JLabel(doctor.name, JLabel.CENTER);
                        JLabel username = new JLabel(doctor.username, JLabel.CENTER);

                        add(name);
                        add(username);
                }
        }
}
