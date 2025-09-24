import javax.swing.*;
import java.awt.*;

public class PanelInicioSesion extends JPanel {

    private JTextField campoUsuario;
    private JPasswordField campoPassword;
    private JButton botonLogin;
    private JComboBox<String> comboIdiomas;
    private JCheckBox checkTerminos;

    public PanelInicioSesion() {
        // Usar un JLayeredPane para el fondo
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(500, 600));
        this.setLayout(new BorderLayout());
        this.add(layeredPane, BorderLayout.CENTER);

        // ---------- FONDO ----------
        ImageIcon fondoIcon = new ImageIcon(getClass().getResource("/fondo web.jpg"));
        JLabel fondoLabel = new JLabel(fondoIcon);
        fondoLabel.setBounds(0, 0, 500, 600);
        layeredPane.add(fondoLabel, JLayeredPane.DEFAULT_LAYER);

        // ---------- PANEL DE CONTENIDO ----------
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setOpaque(false);
        panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        panelContenido.setBounds(0, 0, 500, 600);

        // ---------- LOGO ----------
        JLabel logo = new JLabel();
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setOpaque(false);
        ImageIcon icon = new ImageIcon(getClass().getResource("/Logo_Gonzalo.png"));
        Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(img));
        panelContenido.add(logo);
        panelContenido.add(Box.createRigidArea(new Dimension(0, 20)));

        // ... (Resto de componentes)
        JLabel etiquetaIdioma = new JLabel("Escoger Idioma:");
        etiquetaIdioma.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaIdioma.setForeground(Color.WHITE);
        panelContenido.add(etiquetaIdioma);
        panelContenido.add(Box.createRigidArea(new Dimension(0, 5)));

        String[] idiomas = {"Español", "English", "Français", "Deutsch"};
        comboIdiomas = new JComboBox<>(idiomas);
        comboIdiomas.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboIdiomas.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        comboIdiomas.setBackground(Color.WHITE);
        panelContenido.add(comboIdiomas);
        panelContenido.add(Box.createRigidArea(new Dimension(0, 15)));

        JLabel etiquetaUsuario = new JLabel("Usuario:");
        etiquetaUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaUsuario.setForeground(Color.WHITE);
        campoUsuario = new JTextField(20);
        campoUsuario.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panelContenido.add(etiquetaUsuario);
        panelContenido.add(campoUsuario);
        panelContenido.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel etiquetaPassword = new JLabel("Contraseña:");
        etiquetaPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaPassword.setForeground(Color.WHITE);
        campoPassword = new JPasswordField(20);
        campoPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panelContenido.add(etiquetaPassword);
        panelContenido.add(campoPassword);
        panelContenido.add(Box.createRigidArea(new Dimension(0, 20)));

        checkTerminos = new JCheckBox("Acepto los términos y condiciones");
        checkTerminos.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkTerminos.setOpaque(false);
        checkTerminos.setForeground(Color.WHITE);
        checkTerminos.addActionListener(e -> botonLogin.setEnabled(checkTerminos.isSelected()));
        panelContenido.add(checkTerminos);
        panelContenido.add(Box.createRigidArea(new Dimension(0, 20)));

        botonLogin = new JButton("Iniciar Sesión");
        botonLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonLogin.setBackground(Color.BLACK);
        botonLogin.setForeground(Color.WHITE);
        botonLogin.setFocusPainted(false);
        botonLogin.setFont(new Font("Arial", Font.BOLD, 14));
        botonLogin.setEnabled(false);
        botonLogin.addActionListener(e -> iniciarSesion());
        panelContenido.add(botonLogin);

        layeredPane.add(panelContenido, JLayeredPane.PALETTE_LAYER);
    }

    private void iniciarSesion() {
        String usuario = campoUsuario.getText();
        String password = new String(campoPassword.getPassword());
        if (usuario.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Usuario y contraseña no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso para el usuario: " + usuario, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
}
