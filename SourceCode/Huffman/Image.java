import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Image {

    ImageIcon image;
    JLabel imageLabel;
    File selectedFile;
    int count=0;

    //Absolute path hit extracted pixel
    File outputFile = new File("Data/pixel_data.txt");
    String convertedFile = new String("Data/convertedImage.png");
    Map<String, Integer> hexFrequencyMap = new HashMap<>();
    int width, height;
    String imagePath;

    public Image() {
    }
//kanan pag view
public void ViewImage() {
    try {
        BufferedImage img = ImageIO.read(new File(imagePath));
        if (img == null) {
            System.err.println("Error: Unsupported image format!");
            return;
        }
        
        image = new ImageIcon(img);  // Use BufferedImage for better support
        imageLabel = new JLabel(image);
    } catch (IOException e) {
        System.err.println("Error loading image: " + e.getMessage());
    }
}



    //kanan pag show han .something na file
    public void ViewCompressedImage(BufferedImage decompressImage) {
        ImageIcon imageIcon = new ImageIcon(decompressImage);
        imageLabel = new JLabel(imageIcon);
    }

    //kanan pag select han image na ig c-compress
    public void selectImage() {
        JFileChooser fileChooser = new JFileChooser("TrialImage/Pixel Redundancy Approach");
        fileChooser.setDialogTitle("Select an Image");

        
        //limitations na png la it ma show
        FileNameExtensionFilter filter = new FileNameExtensionFilter("All Files", "png", "tiff", "bmp");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            imagePath = selectedFile.getAbsolutePath();  
        } else {
            System.out.println("No file selected.");
        }


        
    }

    // Extraction hit RGB
    public void trainImage() {
        try {
            
            if (imagePath == null || imagePath.isEmpty()) {
                return;
            }

            BufferedImage image = ImageIO.read(new File(imagePath));
            width = image.getWidth();
            height = image.getHeight();
            
            //orig method
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                writer.write(width + System.lineSeparator());
                writer.write(height + System.lineSeparator());
    
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        // pag extract na hit RGB
                        int pixel = image.getRGB(x, y);
                        int alpha = (pixel >> 24) & 0xFF;
                        int red = (pixel >> 16) & 0xFF;  
                        int green = (pixel >> 8) & 0xFF; 
                        int blue = pixel & 0xFF;         

                        // Convert to hexadecimal
                        String hexValue = String.format("#%02X%02X%02X", red, green, blue);
                        hexFrequencyMap.put(hexValue, hexFrequencyMap.getOrDefault(hexValue, 0) + 1);

                        // Write hex value to the file
                        writer.write(hexValue + System.lineSeparator());
                        count++;    
                    }
                }
                System.out.println("\n\n******************************************************************");
                System.out.println("*   pixel_data.txt successfully created.");
                System.out.println("*   Total no. of pixels: "+count);
                System.out.println("*   Total no. of unique pixels: "+hexFrequencyMap.size());
                System.out.println("******************************************************************");
                count=0;
            }
        } catch (IOException e) {
            System.err.println("Error processing the PNG file: " + e.getMessage());
        }
    }
}
