import java.awt.*;
import javax.swing.*;

public class HuffmanImagePanel extends JPanel {

    private ImageIcon image;
    private JLabel imageLabel;

    public HuffmanImagePanel() {
        setLayout(new BorderLayout()); 
        image = new ImageIcon("SourceCode/placeholder.png");

        if (image.getIconWidth() == -1) {
            System.out.println("Image not found or invalid file path.");
        }

        imageLabel = new JLabel(image);
        add(imageLabel, BorderLayout.CENTER);
    }
}
