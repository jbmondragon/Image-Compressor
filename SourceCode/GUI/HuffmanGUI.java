import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HuffmanGUI extends JFrame {

    private GridBagConstraints gbc;

    // GUI = Frame + Panel
    public HuffmanGUI(JPanel imagePanel, JPanel controlPanel) {
        setTitle("Image Compressor");
        setLayout(new GridBagLayout());
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(30,30,30));
        setResizable(false);

        gbc = new GridBagConstraints();

        imagePanel.setPreferredSize(new Dimension(800, 800));
        imagePanel.setMinimumSize(new Dimension(800, 800));
        imagePanel.setMaximumSize(new Dimension(800, 800));
        imagePanel.setBackground(new Color(42, 42, 42));

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.8; 
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(imagePanel, gbc);

        controlPanel.setPreferredSize(new Dimension(200, 800));
        controlPanel.setMinimumSize(new Dimension(200, 800));
        controlPanel.setMaximumSize(new Dimension(200, 800));
        controlPanel.setBackground(new Color(30, 30, 30));

        gbc.gridx = 0;
        gbc.weightx = 0.2; 
        gbc.fill = GridBagConstraints.BOTH;
        add(controlPanel, gbc);

        setVisible(true);
    }
}
