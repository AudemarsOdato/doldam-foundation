import Login.UsersAndPass;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogInPage extends  JFrame{
        JLabel errorMsg = new JLabel("", JLabel.CENTER);
        JButton returnButton = new JButton("<--");
        JLabel usernameLabel = new JLabel("username: ");
        JLabel passwordLabel = new JLabel("password: ");
        JTextField usernameInput = new JTextField();
        JPasswordField passwordInput = new JPasswordField();
        JButton logInButton = new JButton("Log in");

        UsersAndPass usersAndPass = new UsersAndPass();

        LogInPage() {
                setIconImage(new ImageIcon("assets\\icon\\icon.png").getImage());
                setTitle("Log in");
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(700, 500);
                setLayout(null);
                setResizable(false);
                setLocationRelativeTo(null);

                returnButton.setBounds(9, 9, 76, 30);
                returnButton.addActionListener(e -> {
                        new LandingPage();
                        dispose();
                });

                errorMsg.setBounds(158, 44, 396, 29);
                errorMsg.setFont(new Font("Arial", Font.PLAIN, 24));
                errorMsg.setForeground(Color.RED);
                errorMsg.setVisible(false);

                usernameLabel.setBounds(218, 122, 87, 20);
                usernameLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                usernameInput.setBounds(318, 122, 164, 20);

                passwordLabel.setBounds(218, 170, 87, 20);
                passwordLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                passwordInput.setBounds(318, 170, 164, 20);

                logInButton.setBounds(279, 315, 142, 55);
                logInButton.addActionListener(e -> {
                        HashMap loginInfo = usersAndPass.getLoginInfo();
                        String userInput = usernameInput.getText();
                        String passInput = String.valueOf(passwordInput.getPassword());
                        
                        
                        if (userInput.equals("admin")) {
                                if (loginInfo.get(userInput).equals(passInput)) {
                                        new AdminPage();
                                        dispose();
                                }
                                else {
                                        handleInputError("incorrect password");
                                }
                        }
                        else if (loginInfo.containsKey(userInput)) {
                                if (loginInfo.get(userInput).equals(passInput)) {
                                        new DoctorsPage(usersAndPass.getName(userInput));
                                        dispose();
                                }
                                else {
                                        handleInputError("incorrect password");
                                }
                        }
                        else {
                                handleInputError("incorrect username");
                        }
                        
                });

                Component[] components = {
                                        errorMsg,
                                        returnButton, 
                                        usernameLabel, 
                                        usernameInput, 
                                        passwordLabel,
                                        passwordInput, 
                                        logInButton
                };
                for (Component component : components) {
                        add(component);
                }
                setVisible(true);
        }

        private void handleInputError(String msg) {
                errorMsg.setText(msg);
                errorMsg.setVisible(true);
        }
}
