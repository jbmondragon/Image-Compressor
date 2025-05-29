import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HuffmanControlPanel extends JPanel {

    JButton view, train, compress, openFile;
    JPanel topPanel, bottomPanel, containerPanel, buttonPanel;
    GridBagConstraints gbc;

    public HuffmanControlPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        containerPanel = new JPanel(new GridBagLayout());
        containerPanel.setBackground(new Color(30,30,30));
        GridBagConstraints containerGbc = new GridBagConstraints();
        containerGbc.gridx = 0;
        containerGbc.gridy = 0;
        containerGbc.anchor = GridBagConstraints.CENTER;
        
        topPanel = new JPanel(new GridBagLayout());
        topPanel.setBackground(new Color(30, 30, 30));

        JLabel title = new JLabel("<html>IMAGE<br>COMPRESSOR<br>//////////////////////////</html>");
        title.setFont(new Font("Arial Black", Font.BOLD, 20));
        title.setForeground(new Color(255, 204, 0));

        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.gridx = 0;
        titleConstraints.gridy = 0;
        titleConstraints.insets = new Insets(10, 0, 10, 0);
        titleConstraints.anchor = GridBagConstraints.CENTER;
        topPanel.add(title, titleConstraints);

        bottomPanel = new JPanel(new GridBagLayout());
        bottomPanel.setBackground(new Color(30, 30, 30));

        buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(30, 30, 30));

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.insets = new Insets(5, 0, 5, 0);

        createButtons();

        gbc.gridy = 0;
        buttonPanel.add(view, gbc);
        gbc.gridy = 1;
        buttonPanel.add(train, gbc);
        gbc.gridy = 2;
        buttonPanel.add(compress, gbc);
        gbc.gridy = 3;
        buttonPanel.add(openFile, gbc);
        
        bottomPanel.add(buttonPanel, new GridBagConstraints());

        containerGbc.gridy = 0;
        containerPanel.add(topPanel, containerGbc);
        
        containerGbc.gridy = 1;
        containerPanel.add(bottomPanel, containerGbc);

        add(containerPanel, BorderLayout.CENTER);
    }

    public void createButtons() {
        view = createHoverButton("V I E W");
        train = createHoverButton("T R A I N");
        compress = createHoverButton("C O M P R E S S");
        openFile = createHoverButton("O P E N");
    }

    private JButton createHoverButton(String text) {
        JButton button = new JButton(text);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(150, 30));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setOpaque(true);
                button.setBackground(new Color(60, 60, 60));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setOpaque(false);
                button.setBackground(null);
            }
        });
        
        return button;
    }
}
