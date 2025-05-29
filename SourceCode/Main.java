import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Main implements ActionListener {

    /*
     *  Note: To make this work dapat ig change an mga absolute path
     *        this includes the absolute path for the pixel data where in
     *        ig sstore an pixel value ngan an huffmanTree na file and pati and dot something
     */

    //ine nga class kay amo ine an main class
    HuffmanControlPanel controlPanel = new HuffmanControlPanel();
    HuffmanImagePanel imagePanel = new HuffmanImagePanel();
    Huffman huffmanTree = new Huffman();
    HuffmanCompression h = new HuffmanCompression();

    // pag restrict na mag show hin duha ka sabay na image
    private boolean isImageLoaded = false;
    private boolean isEmpty = false;
    private boolean isTrained = false;
    private boolean isCompressed = false;
    int width, height;

    public Main() {
        new HuffmanGUI(imagePanel, controlPanel);

        //mga action listener para ha mga buttons
        controlPanel.view.addActionListener(this);
        controlPanel.train.addActionListener(this);
        controlPanel.compress.addActionListener(this);
        controlPanel.openFile.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        switch (clickedButton.getText()) {

            //pag show hit image na ig c-compress
            case "V I E W":
                huffmanTree.extraxtHexCode.selectImage();
                
                if (huffmanTree.extraxtHexCode.imagePath!=null){
                    isImageLoaded = true;
                    imagePanel.removeAll();
                    
                    //pag render ngan pag extract hit pixel value
                    huffmanTree.extraxtHexCode.ViewImage();
                    imagePanel.add(huffmanTree.extraxtHexCode.imageLabel, BorderLayout.CENTER);
                    imagePanel.revalidate();
                    imagePanel.repaint(); 

                    huffmanTree.extraxtHexCode.trainImage();
                    huffmanTree.extraxtHexCode.imagePath=null;
                    isTrained=false;
                    isCompressed=false;
                }
                break;

            case "T R A I N":

                if (!isImageLoaded){
                    JOptionPane.showMessageDialog(null, "Please load an image first", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (isTrained) {
                    JOptionPane.showMessageDialog(null, "Image already trained", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                //pag create hit huffman tree
                if(huffmanTree.extraxtHexCode != null) {
                    huffmanTree.createHuffanTree();
                    isTrained = true; // Mark as trained
                }
                break;

            case "C O M P R E S S":
                
                if (!isImageLoaded && !isTrained) {
                    JOptionPane.showMessageDialog(null, "Please load and train an image first", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (isCompressed&&isEmpty) {
                    JOptionPane.showMessageDialog(null, "Image already compressed", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                //pag create han .something na file nga huffman coded
                if(huffmanTree.extraxtHexCode.image != null) {
                    h.readFile();
                    h.compressImage();
                    imagePanel.removeAll();
                    imagePanel.revalidate();
                    imagePanel.repaint();
                    isCompressed = true;
                    isEmpty = true;  
                }
                break;

            case "O P E N":
                File selectedFile;
                JFileChooser fileChooser = new JFileChooser("C:\\Users\\jbmon\\OneDrive\\Desktop\\Kinit-main\\Data");
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    
                    if (!selectedFile.getAbsolutePath().toLowerCase().endsWith(".mmt")) {
                        JOptionPane.showMessageDialog(null, "Invalid file type! Please select a MMT file.", "Error", JOptionPane.ERROR_MESSAGE);
                    }else if(selectedFile.getAbsolutePath().toLowerCase().endsWith(".mmt")&&selectedFile!=null){
                        try {
                            String fileName = "C:\\Users\\jbmon\\OneDrive\\Desktop\\Kinit-main\\Data\\pixel_data.txt";
                            
                            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                                width = Integer.parseInt(reader.readLine().trim());
                                height = Integer.parseInt(reader.readLine().trim());
                                reader.close();
                            } catch (Exception ine) {
                                ine.printStackTrace();
                            }

                            if(selectedFile!=null){
                                imagePanel.removeAll();
                                imagePanel.revalidate(); 
                                imagePanel.repaint(); 
                            }
                            
                            //pag decompress han .something na file
                            h.decompressImage(selectedFile.getAbsolutePath(), width, height, huffmanTree.extraxtHexCode.selectedFile);                        
                            imagePanel.add(h.imageLabel, BorderLayout.CENTER);
                            imagePanel.revalidate(); 
                            imagePanel.repaint();  
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error loading image: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No file selected", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                break;

            default:
                break;
        }
    }

    public static void main(String[] args) {
        Main huffman = new Main();
    }
}
