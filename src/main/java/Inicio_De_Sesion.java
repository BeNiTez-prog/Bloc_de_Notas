import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inicio_De_Sesion extends JFrame {

    // ---------- ATRIBUTOS ----------
    private JTextField campoUsuario;
    private JPasswordField campoPassword;
    private JButton botonLogin;
    private JComboBox<String> comboIdiomas;
    private JCheckBox checkTerminos;

    // ---------- CONSTRUCTOR ----------
    public Inicio_De_Sesion() {
        setTitle("Inicio de Sesión");
        setSize(400, 550); // Adjusted size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana

        // Usar un JLayeredPane para poner un fondo
        JLayeredPane layeredPane = new JLayeredPane();
        setContentPane(layeredPane);

        // ---------- FONDO ----------
        // Asegúrate de que la imagen 'fondo web.jpg' esté en 'src/main/resources'
        ImageIcon fondoIcon = new ImageIcon(getClass().getResource("/fondo web.jpg"));
        JLabel fondoLabel = new JLabel(fondoIcon);
        fondoLabel.setBounds(0, 0, 400, 550); // Mismo tamaño que la ventana
        layeredPane.add(fondoLabel, JLayeredPane.DEFAULT_LAYER);

        // ---------- PANEL PRINCIPAL ----------
        // Este panel contendrá todos los demás componentes
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setOpaque(false); // Hacerlo transparente
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        panelPrincipal.setBounds(0, 0, 400, 550);

        // ---------- LOGO ----------
        JLabel logo = new JLabel();
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setOpaque(false); // Hacerlo transparente

        ImageIcon icon = new ImageIcon(getClass().getResource("/Logo_Gonzalo.png"));
        Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(img));

        panelPrincipal.add(logo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));

        // ---------- ETIQUETA IDIOMAS ----------
        JLabel etiquetaIdioma = new JLabel("Escoger Idioma:");
        etiquetaIdioma.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaIdioma.setForeground(Color.WHITE); // Texto en blanco para que se lea bien
        panelPrincipal.add(etiquetaIdioma);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 5))); // Pequeño espacio

        // ---------- DESPLEGABLE IDIOMAS ----------
        String[] idiomas = {"Español", "English", "Français", "Deutsch"};
        comboIdiomas = new JComboBox<>(idiomas);
        comboIdiomas.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboIdiomas.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        comboIdiomas.setBackground(Color.WHITE);

        panelPrincipal.add(comboIdiomas);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));

        // ---------- CAMPO USUARIO ----------
        JLabel etiquetaUsuario = new JLabel("Usuario:");
        etiquetaUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaUsuario.setForeground(Color.WHITE); // Texto en blanco para que se lea bien
        campoUsuario = new JTextField(20);
        campoUsuario.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        panelPrincipal.add(etiquetaUsuario);
        panelPrincipal.add(campoUsuario);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));

        // ---------- CAMPO CONTRASEÑA ----------
        JLabel etiquetaPassword = new JLabel("Contraseña:");
        etiquetaPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaPassword.setForeground(Color.WHITE); // Texto en blanco
        campoPassword = new JPasswordField(20);
        campoPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        panelPrincipal.add(etiquetaPassword);
        panelPrincipal.add(campoPassword);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));

        // ---------- CHECKBOX TÉRMINOS ----------
        checkTerminos = new JCheckBox("Acepto los términos y condiciones");
        checkTerminos.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkTerminos.setOpaque(false);
        checkTerminos.setForeground(Color.WHITE);

        checkTerminos.addActionListener(e -> {
            // Activar o desactivar el botón según el checkbox
            botonLogin.setEnabled(checkTerminos.isSelected());
        });

        panelPrincipal.add(checkTerminos);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));

        // ---------- BOTÓN LOGIN ----------
        botonLogin = new JButton("Iniciar Sesión");
        botonLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonLogin.setBackground(Color.BLACK);
        botonLogin.setForeground(Color.WHITE);
        botonLogin.setFocusPainted(false);
        botonLogin.setFont(new Font("Arial", Font.BOLD, 14));
        botonLogin.setEnabled(false); // Deshabilitado por defecto

        botonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });

        panelPrincipal.add(botonLogin);

        // Añadir el panel principal encima del fondo
        layeredPane.add(panelPrincipal, JLayeredPane.PALETTE_LAYER);
    }

    // ---------- MÉTODO DE LÓGICA ----------
    private void iniciarSesion() {
        String usuario = campoUsuario.getText();
        String password = new String(campoPassword.getPassword());

        if (usuario.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Usuario y contraseña no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso para el usuario: " + usuario, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    // ---------- MÉTODO PRINCIPAL ----------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Inicio_De_Sesion ventana = new Inicio_De_Sesion();
            ventana.setResizable(false);
            ventana.setVisible(true);
        });
    }
}
