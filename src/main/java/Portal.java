import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Portal extends JFrame {

    private Image backgroundImage;

    public Portal() {
        setTitle("Portal de Acceso");
        setSize(450, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        backgroundImage = new ImageIcon(getClass().getResource("/fondo web.jpg")).getImage();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Iniciar Sesión", createLoginPanel());
        tabbedPane.addTab("Registro", createRegisterPanel());
        tabbedPane.setBackground(new Color(102, 0, 153));
        tabbedPane.setForeground(Color.WHITE);

        add(tabbedPane);
    }

    class ImagePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private void showProgressBarAndExecute(String successMessage) {
        JDialog progressDialog = new JDialog(this, "Procesando...", true);
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressDialog.add(BorderLayout.CENTER, progressBar);
        progressDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        progressDialog.setSize(300, 75);
        progressDialog.setLocationRelativeTo(this);

        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(20); // Simula trabajo
                    setProgress(i);
                }
                return null;
            }

            @Override
            protected void done() {
                progressDialog.dispose();
                JOptionPane.showMessageDialog(Portal.this, successMessage, "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        };

        worker.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("progress".equals(evt.getPropertyName())) {
                    progressBar.setValue((Integer) evt.getNewValue());
                }
            }
        });

        worker.execute();
        progressDialog.setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel panelPrincipal = new ImagePanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setOpaque(false);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 150)));

        JLabel etiquetaIdioma = new JLabel("Escoger Idioma:");
        etiquetaIdioma.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaIdioma.setForeground(Color.WHITE);
        etiquetaIdioma.setOpaque(false);
        panelPrincipal.add(etiquetaIdioma);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 5)));

        String[] idiomas = {"Español", "English", "Français", "Deutsch"};
        JComboBox<String> comboIdiomas = new JComboBox<>(idiomas);
        comboIdiomas.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboIdiomas.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        comboIdiomas.setBackground(Color.WHITE);
        panelPrincipal.add(comboIdiomas);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));

        JLabel etiquetaUsuario = new JLabel("Usuario:");
        etiquetaUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaUsuario.setForeground(Color.WHITE);
        etiquetaUsuario.setOpaque(false);
        JTextField campoUsuario = new JTextField(20);
        campoUsuario.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panelPrincipal.add(etiquetaUsuario);
        panelPrincipal.add(campoUsuario);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel etiquetaPassword = new JLabel("Contraseña:");
        etiquetaPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaPassword.setForeground(Color.WHITE);
        etiquetaPassword.setOpaque(false);
        JPasswordField campoPassword = new JPasswordField(20);
        campoPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panelPrincipal.add(etiquetaPassword);
        panelPrincipal.add(campoPassword);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));

        JCheckBox checkTerminosLogin = new JCheckBox("Acepto los términos y condiciones");
        checkTerminosLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkTerminosLogin.setOpaque(false);
        checkTerminosLogin.setForeground(Color.WHITE);
        panelPrincipal.add(checkTerminosLogin);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton botonLogin = new JButton("Iniciar Sesión");
        botonLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonLogin.setBackground(Color.BLACK);
        botonLogin.setForeground(Color.WHITE);
        botonLogin.setEnabled(false);

        checkTerminosLogin.addActionListener(e -> botonLogin.setEnabled(checkTerminosLogin.isSelected()));
        botonLogin.addActionListener(e -> {
            if (campoUsuario.getText().isEmpty() || new String(campoPassword.getPassword()).isEmpty()) {
                JOptionPane.showMessageDialog(this, "Usuario y contraseña no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            showProgressBarAndExecute("Inicio de sesión para '" + campoUsuario.getText() + "' exitoso.");
        });

        panelPrincipal.add(botonLogin);
        return panelPrincipal;
    }

    private JPanel createRegisterPanel() {
        JPanel panelPrincipal = new ImagePanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setOpaque(false);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 150)));

        JLabel etiquetaUsuario = new JLabel("Usuario:");
        etiquetaUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaUsuario.setForeground(Color.WHITE);
        etiquetaUsuario.setOpaque(false);
        JTextField campoUsuario = new JTextField(20);
        campoUsuario.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panelPrincipal.add(etiquetaUsuario);
        panelPrincipal.add(campoUsuario);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel etiquetaPassword = new JLabel("Contraseña:");
        etiquetaPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaPassword.setForeground(Color.WHITE);
        etiquetaPassword.setOpaque(false);
        JPasswordField campoPassword = new JPasswordField(20);
        campoPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panelPrincipal.add(etiquetaPassword);
        panelPrincipal.add(campoPassword);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel etiquetaEdad = new JLabel("Edad:");
        etiquetaEdad.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaEdad.setForeground(Color.WHITE);
        etiquetaEdad.setOpaque(false);
        JSpinner spinnerEdad = new JSpinner(new SpinnerNumberModel(18, 1, 120, 1));
        spinnerEdad.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panelPrincipal.add(etiquetaEdad);
        panelPrincipal.add(spinnerEdad);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));

        JCheckBox checkTerminos = new JCheckBox("Acepto los términos y condiciones");
        checkTerminos.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkTerminos.setOpaque(false);
        checkTerminos.setForeground(Color.WHITE);
        panelPrincipal.add(checkTerminos);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton botonRegistro = new JButton("Registrarse");
        botonRegistro.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonRegistro.setBackground(Color.BLACK);
        botonRegistro.setForeground(Color.WHITE);
        botonRegistro.setEnabled(false);

        checkTerminos.addActionListener(e -> botonRegistro.setEnabled(checkTerminos.isSelected()));
        botonRegistro.addActionListener(e -> {
            if (campoUsuario.getText().isEmpty() || new String(campoPassword.getPassword()).isEmpty()) {
                JOptionPane.showMessageDialog(this, "Usuario y contraseña no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            showProgressBarAndExecute("Registro para '" + campoUsuario.getText() + "' exitoso.");
        });

        panelPrincipal.add(botonRegistro);
        return panelPrincipal;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Portal portal = new Portal();
            portal.setVisible(true);
        });
    }
}
