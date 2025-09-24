import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registro extends JFrame {

    // ---------- ATRIBUTOS ----------
    private JTextField campoUsuario;
    private JPasswordField campoPassword;
    private JCheckBox checkTerminos;
    private JButton botonRegistro;
    private JPanel panel1;

    // ---------- CONSTRUCTOR ----------
    public Registro() {
        setTitle("Registro de Sesión");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana

        // Panel principal con diseño vertical
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBackground(new Color(102, 0, 153)); // Morado oscuro
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // ---------- LOGO ----------
        JLabel logo = new JLabel();
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setOpaque(true);
        logo.setBackground(new Color(102, 0, 153)); // Morado oscuro

        // Cargar la imagen (asegúrate de tener logo.png en src)
        ImageIcon icon = new ImageIcon(getClass().getResource("/Logo_Gonzalo.png"));
        Image img;
        img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(img));

        panelPrincipal.add(logo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio debajo del logo

        // ---------- CAMPO USUARIO ----------
        JLabel etiquetaUsuario = new JLabel("Usuario:");
        etiquetaUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaUsuario.setForeground(Color.WHITE);
        campoUsuario = new JTextField(20);
        campoUsuario.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        campoUsuario.setBackground(Color.WHITE);

        panelPrincipal.add(etiquetaUsuario);
        panelPrincipal.add(campoUsuario);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));

        // ---------- CAMPO CONTRASEÑA ----------
        JLabel etiquetaPassword = new JLabel("Contraseña:");
        etiquetaPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaPassword.setForeground(Color.WHITE);
        campoPassword = new JPasswordField(20);
        campoPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        campoPassword.setBackground(Color.WHITE);

        panelPrincipal.add(etiquetaPassword);
        panelPrincipal.add(campoPassword);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));

        // ---------- CHECKBOX TÉRMINOS ----------
        checkTerminos = new JCheckBox("Acepto los términos y condiciones");
        checkTerminos.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkTerminos.setBackground(new Color(102, 0, 153)); // Morado oscuro
        checkTerminos.setForeground(Color.WHITE);

        checkTerminos.addActionListener(e -> {
            // Activar o desactivar el botón según el checkbox
            botonRegistro.setEnabled(checkTerminos.isSelected());
        });

        panelPrincipal.add(checkTerminos);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));

        // ---------- BOTÓN REGISTRO ----------
        botonRegistro = new JButton("Registrarse");
        botonRegistro.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonRegistro.setBackground(Color.BLACK);
        botonRegistro.setForeground(Color.WHITE);
        botonRegistro.setFocusPainted(false);
        botonRegistro.setFont(new Font("Arial", Font.BOLD, 14));
        botonRegistro.setEnabled(false); // Deshabilitado por defecto

        botonRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });

        panelPrincipal.add(botonRegistro);

        // Agregar el panel a la ventana
        add(panelPrincipal);
    }

    // ---------- MÉTODO DE LÓGICA ----------
    private void registrarUsuario() {
        String usuario = campoUsuario.getText();
        String password = new String(campoPassword.getPassword());

        if (usuario.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Usuario y contraseña no pueden estar vacíos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!checkTerminos.isSelected()) {
            JOptionPane.showMessageDialog(this,
                    "Debe aceptar los términos y condiciones.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this,
                "Registro exitoso para el usuario: " + usuario,
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // ---------- MÉTODO PRINCIPAL ----------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Registro ventana = new Registro();
            ventana.setVisible(true); // Muestra la ventana
        });
    }
}
