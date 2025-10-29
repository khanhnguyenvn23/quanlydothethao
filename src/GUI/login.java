package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class login {
    private JFrame frame;
    private JPanel mainPanel;
    private int width = 600;
    private int height = 580;

    public login() {
        frame = new JFrame("SportApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0, 0, 0));
        frame.setResizable(true);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(width, height));
        mainPanel.setBackground(Color.BLACK);

        ImageIcon icon = new ImageIcon("IMG\\background-dep-4k.jpg");
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        JLabel loginGUI = new JLabel(new ImageIcon(img));
        loginGUI.setLayout(null);

        JLabel loginLabel = new JLabel(common.login.toUpperCase());
        loginLabel.setFont(new Font(common.font_family, Font.BOLD, 25));
        loginLabel.setForeground(Color.WHITE);
        loginLabel.setBounds(200, 100, 200, 30);
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginGUI.add(loginLabel);

        JLabel usernameLabel = new JLabel(common.username.substring(0, 1).toUpperCase() + common.username.substring(1));
        usernameLabel.setFont(new Font(common.font_family, Font.BOLD, 20));
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setBounds(100, 170, 200, 30);
        usernameLabel.setHorizontalAlignment(SwingConstants.LEADING);
        loginGUI.add(usernameLabel);

        JLabel passwordLabel = new JLabel(common.password.substring(0, 1).toUpperCase() + common.password.substring(1));
        passwordLabel.setFont(new Font(common.font_family, Font.BOLD, 20));
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(100, 260, 200, 30);
        passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);
        loginGUI.add(passwordLabel);

        JLabel fogetPassword = new JLabel(
                common.forgetpassword.substring(0, 1).toUpperCase() + common.forgetpassword.substring(1));
        fogetPassword.setFont(new Font(common.font_family, Font.PLAIN, 15));
        fogetPassword.setForeground(Color.WHITE);
        fogetPassword.setBounds(300, 260, 200, 30);
        fogetPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        fogetPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        fogetPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                // hover
                fogetPassword.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                // hover
                fogetPassword.setForeground(Color.WHITE);
            }
        });
        loginGUI.add(fogetPassword);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(100, 300, 400, 30);
        JCheckBox showPass = new JCheckBox("Hiển thị mật khẩu");
        showPass.setForeground(Color.WHITE);
        showPass.setOpaque(false); // bo nen xam
        showPass.setFocusPainted(false); // bo vien khi duoc focus
        // showPass.setBorderPainted(false); // bo vien o
        showPass.setBounds(100, 340, 150, 30);
        showPass.addActionListener(e -> {
            if (showPass.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        });
        loginGUI.add(passwordField);
        loginGUI.add(showPass);

        JTextField userField = new JTextField("");
        userField.setBounds(100, 210, 400, 30);
        loginGUI.add(userField);

        JButton loginButton = new JButton(common.login.substring(0, 1).toUpperCase() + common.login.substring(1));
        loginButton.setForeground(Color.BLACK);
        loginButton.setBounds(200, 380, 200, 30);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setFocusPainted(false);
        loginGUI.add(loginButton);
        // JTextField passField = new JTextField("");
        // passField.setBounds(100, 300, 400, 30);
        // loginGUI.add(passField);

        JLabel question = new JLabel("Chưa có tài khoản? ");
        JLabel signUP = new JLabel("<html><u>Tạo tài khoản</u></html>");
        question.setForeground(Color.WHITE);
        question.setFont(new Font(common.font_family, Font.PLAIN, 15));
        question.setBounds(180, 430, 200, 30);
        signUP.setForeground(Color.WHITE);
        signUP.setFont(new Font(common.font_family, Font.BOLD, 15));
        signUP.setBounds(315, 430, 200, 30);
        signUP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signUP.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                // hover
                signUP.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                // hover
                signUP.setForeground(Color.WHITE);
            }
        });
        loginGUI.add(question);
        loginGUI.add(signUP);
        mainPanel.add(loginGUI, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(login::new);

    }
}