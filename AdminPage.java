import Login.UsersAndPass;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class AdminPage extends JFrame{
        JPanel container = new JPanel();
        JPanel sidePanel = new JPanel();

        JPanel patientsContainer = new JPanel();
        JPanel doctorsContainer = new JPanel();

        ArrayList<Patient> patients = new ArrayList<>();
        ArrayList<Doctor> allDoctors = new ArrayList<>();

        JButton addNewDoctor = new JButton("New Doctor");

        AdminPage() {
                setIconImage(new ImageIcon("assets\\icon\\icon.png").getImage());
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(new Dimension(900, 600));
                setLocationRelativeTo(null);
                setTitle("Admin");
                setLayout(new BorderLayout());

                getPatientsInfo();
                getDoctorsInfo();
                
                container.setLayout(new FlowLayout());

                sidePanel.setLayout(new FlowLayout());
                sidePanel.setAlignmentX(CENTER_ALIGNMENT);
                sidePanel.setPreferredSize(new Dimension((int)(getWidth() * 0.2), sidePanel.getHeight()));
                add(sidePanel, BorderLayout.WEST);

                patientsContainer.setLayout(new GridLayout(patients.size() + 6, 1, 10, 10));
                container.add(patientsContainer);

                JPanel patientsHeader = new JPanel();
                patientsHeader.add(new JLabel("Patients"));
                patientsHeader.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
                patientsContainer.add(patientsHeader);
                
                JPanel patientsLabel = new JPanel();
                patientsLabel.setLayout(new GridLayout(1, 4, 10, 10));
                patientsLabel.add(new JLabel("Name", JLabel.CENTER));
                patientsLabel.add(new JLabel("Age", JLabel.CENTER));
                patientsLabel.add(new JLabel("Contact", JLabel.CENTER));
                patientsLabel.add(new JLabel("Date", JLabel.CENTER));
                patientsContainer.add(patientsLabel);
                
                displayPatients();

                doctorsContainer.setLayout(new GridLayout(allDoctors.size() + 6, 1, 10, 10));
                doctorsContainer.setVisible(false);
                container.add(doctorsContainer);
                
                JPanel doctorsHeader = new JPanel();
                doctorsHeader.add(new JLabel("Doctors"));
                doctorsHeader.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
                doctorsContainer.add(doctorsHeader);
                
                JPanel doctorsLabel = new JPanel();
                doctorsLabel.setLayout(new GridLayout(1, 2, 10, 10));
                doctorsLabel.add(new JLabel("Name", JLabel.CENTER));
                doctorsLabel.add(new JLabel("Username", JLabel.CENTER));
                doctorsContainer.add(doctorsLabel);
                
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
                        doctorsContainer.setVisible(false);
                        patientsContainer.setVisible(true);
                });

                JButton allDoctorsButton = new JButton("Doctors");
                allDoctorsButton.setPreferredSize(SIDEPANEL_BUTTONSIZE);
                allDoctorsButton.addActionListener(e -> {
                        patientsContainer.setVisible(false);
                        doctorsContainer.setVisible(true);
                });
                
                JButton eraseAllPatients = new JButton("Remove Patients");
                eraseAllPatients.setPreferredSize(SIDEPANEL_BUTTONSIZE);
                eraseAllPatients.addActionListener(e -> {
                        removeAllPatients();
                        new AdminPage();
                        dispose();
                });

                addNewDoctor.setPreferredSize(SIDEPANEL_BUTTONSIZE);
                addNewDoctor.addActionListener(e -> {
                        addNewDoctor();
                });

                JButton removeDoctor = new JButton("Remove Doctor");
                removeDoctor.setPreferredSize(SIDEPANEL_BUTTONSIZE);
                removeDoctor.addActionListener(e -> {
                        removeDoctor();
                });

                JLabel sidePanelHeader = new JLabel("Doldam Hospital");
                sidePanelHeader.setFont(new Font("Arial", Font.PLAIN, 16));
                sidePanelHeader.setForeground(Color.GRAY);
                sidePanelHeader.setBorder(BorderFactory.createEmptyBorder(30, 5, 30, 30));
                
                Component[] sidePanelComponents = {
                                        sidePanelHeader,
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

        private void removeDoctor() {
                String username = JOptionPane.showInputDialog("username");
                boolean isUsernameExist = false;
                String[] options = {"Remove", "Cancel"};
                
                for (Doctor doctor : allDoctors) {
                        if (doctor.username.equals(username)) {
                                isUsernameExist = true;
                        }
                }

                
                if (username != null) {
                        if (!isUsernameExist) {
                                JOptionPane.showMessageDialog(this, "username not found");
                        }
                        else {
                                int confirmation = JOptionPane.showOptionDialog(
                                        this, 
                                        "Confirm remove?", 
                                        "Confirm",
                                        JOptionPane.YES_NO_OPTION,
                                        JOptionPane.QUESTION_MESSAGE, 
                                        null, 
                                        options, 
                                        options[0]);
        
                                if (confirmation == JOptionPane.YES_OPTION) {
                                        JOptionPane.showMessageDialog(this, "removed: " + username);
                                        allDoctors.removeIf(doctor -> doctor.username.equals(username));

                                        resetDoctor("names");
                                        resetDoctor("usernames");
                                        resetDoctor("keys");
                                        
                                        for (Doctor doctor : allDoctors) {
                                                updateDoctor("names", doctor.name);
                                                updateDoctor("usernames", doctor.username);
                                                updateDoctor("keys", doctor.key);
                                        }
                                        dispose();
                                        new AdminPage();
                                }
                        }
                }


        }

        private void updateDoctor(String file, String updatedInfo) {
                try (FileWriter writer = new FileWriter("personel\\doctors\\" + file + ".txt", true)) {
                        writer.write(updatedInfo + "\n");
                }
                catch (IOException ex) {
                        System.out.println(ex);
                }
        }

        private void resetDoctor(String info) {
                try (FileWriter writer = new FileWriter("personel\\doctors\\" + info + ".txt"))  {
                        writer.write("");
                }
                catch (IOException ex) {
                        System.out.println(ex);
                }
        }

        private void addNewDoctor() {
                Dimension INPUT_FIELD_SIZE = new Dimension(100, 20);

                JFrame window = new JFrame("New Doctor");
                window.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
                window.setLocationRelativeTo(addNewDoctor);
                window.setSize(300, 200);

                JPanel localcontainer = new JPanel();
                localcontainer.setLayout(new BoxLayout(localcontainer, BoxLayout.Y_AXIS));
                window.add(localcontainer);

                JLabel nameLabel = new JLabel("Name: ");
                JLabel usernameLabel = new JLabel("Username: ");
                JLabel passLabel = new JLabel("Password: ");

                JTextField nameInput = new JTextField();
                JTextField usernameInput = new JTextField();
                JTextField passwordInput = new JTextField();

                nameInput.setPreferredSize(INPUT_FIELD_SIZE);
                usernameInput.setPreferredSize(INPUT_FIELD_SIZE);
                passwordInput.setPreferredSize(INPUT_FIELD_SIZE);

                JButton addButton = new JButton("Add");
                addButton.addActionListener(e -> {
                        boolean isInputEmpty = nameInput.getText().isEmpty() ||
                                                usernameInput.getText().isEmpty() ||
                                                passwordInput.getText().isEmpty();

                        if (!isInputEmpty) {
                                saveNewDoctor(nameInput.getText(), usernameInput.getText(), passwordInput.getText());
                                new AdminPage();
                                window.dispose();
                                dispose();
                        }
                });

                Component[] components = {nameLabel, nameInput, usernameLabel, usernameInput, passLabel, passwordInput, addButton};
                for (Component component : components) {
                        localcontainer.add(component);
                }

                window.setVisible(true);
        }

        private void newDoctor(String info, String folder) {
                try (FileWriter writer = new FileWriter("personel\\doctors\\" + folder + ".txt", true)) {
                        writer.write(info + "\n");
                }
                catch (IOException ex) {
                        System.out.println(ex);
                }
        }

        private void saveNewDoctor(String name, String username, String pass) {
                newDoctor(name, "names");
                newDoctor(username, "usernames");
                newDoctor(pass, "keys");
        }

        private void resetPatient(String info) {
                try (FileWriter writer = new FileWriter("patients\\" + info + ".txt")) {
                        writer.write("");
                } 
                catch (IOException e) {
                        System.out.println(e);
                }
        }

        private void removeAllPatients() {
                int confirmation = JOptionPane.showConfirmDialog(this, 
                                        "Erase all patient information? This action cannot be undone.",
                                        "Confirm",
                                        JOptionPane.YES_NO_OPTION);
                
                if (confirmation == JOptionPane.YES_OPTION) {
                        resetPatient("names");
                        resetPatient("ages");
                        resetPatient("contacts");
                        resetPatient("appointment-dates");
                        resetPatient("appointed-doctor");
                        patients.clear();

                        JOptionPane.showMessageDialog(this, "Erased all patient records.");
                }
                else {
                        JOptionPane.showMessageDialog(this, "Cancelled.");

                }


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
                ArrayList<String> keys = usersAndPass.getKeys();

                for (int i = 0;i < names.size(); i++) {
                        allDoctors.add(new Doctor(names.get(i), usernames.get(i), keys.get(i)));
                }
        }

        private void displayPatients() {
                for (int i = 0; i < patients.size(); i++) {
                        patientsContainer.add(new DoctorsPage.AppointmentPanel(patients.get(i)));
                }
        }
        
        private void displayDoctor() {
                for (int i = 0; i < allDoctors.size(); i++) {
                        doctorsContainer.add(new DoctorPanel(allDoctors.get(i)));
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