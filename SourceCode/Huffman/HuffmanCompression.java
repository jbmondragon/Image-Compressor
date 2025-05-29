import java.awt.image.BufferedImage;
import java.io.*;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class HuffmanCompression {

    Map<String, String> huffmanMap; 
    Map<String, String> reverseHuffmanMap ;
    JLabel imageLabel; 
    String outputFileName = "Data/compressed_output.MMT";
    

    //kanan pag track hit huffman tree, important ine for compressing and decompressing
    public void readFile() {
        String fileName = "Data/huffman_tree.HUFF"; 
        huffmanMap= new HashMap<>();
        reverseHuffmanMap= new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                try {
                    String hexCode = data[0].trim();
                    String code = data[1].trim();
                    huffmanMap.put(hexCode, code);
                    reverseHuffmanMap.put(code, hexCode);
                } catch (NumberFormatException e) {
                    System.err.println("Skipping invalid line: " + line + " (" + e.getMessage() + ")");
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    //kanan pag translate han pixel data from hexcode to .something na file
    public void compressImage() {
        String inputFileName = "Data/pixel_data.txt";
    

    try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
         FileOutputStream fos = new FileOutputStream(outputFileName, false)) {

        BitSet bitSet = new BitSet();
        int bitIndex = 0;

        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split("\n");
            String hexCode = data[0].trim();

            if (huffmanMap.containsKey(hexCode)) {
                String huffmanCode = huffmanMap.get(hexCode);
                for (char bit : huffmanCode.toCharArray()) {
                    if (bit == '1') {
                        bitSet.set(bitIndex);
                    }
                    bitIndex++;
                }
            }
        }

        //pag write han dot something na file
        byte[] compressedBytes = bitSet.toByteArray();
        fos.write(compressedBytes);
        System.out.println("\n******************************************************************");
        System.out.println("*   Compression complete. Output written");
        System.out.println("******************************************************************");

    } catch (IOException e) {
        System.err.println("An error occurred during file operations: " + e.getMessage());
    }
}

public void decompressImage(String inputCompressedFilePath, int width, int height, File file) {
    try (FileInputStream fis = new FileInputStream(inputCompressedFilePath)) {
    
        readFile();
        byte[] compressedBytes = fis.readAllBytes();
        BitSet bitSet = BitSet.valueOf(compressedBytes);
        File compressedFile = new File(inputCompressedFilePath);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int x = 0, y = 0;
        StringBuilder currentCode = new StringBuilder();


        for (int i = 0; i < bitSet.length(); i++) {
            currentCode.append(bitSet.get(i) ? '1' : '0');

            if (reverseHuffmanMap.containsKey(currentCode.toString())) {
                String hexCode = reverseHuffmanMap.get(currentCode.toString());

                //pagconvert from hexcode to rgb na values
                try {
                    if (hexCode.startsWith("#") && hexCode.length() == 7) {
                        int color = Integer.parseInt(hexCode.substring(1), 16);
                        image.setRGB(x, y, color);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing hex color code: " + hexCode);
                }
                x++;
                if (x == width) {
                    x = 0;
                    y++;
                    if (y == height) {
                        break;
                    }
                }
                currentCode.setLength(0);
            }
        }
        String outputPath = inputCompressedFilePath.replace(".MMT", ".png").replace(".mmt", ".png");
        File outputFile = new File(outputPath);
       
        imageLabel = new JLabel(new ImageIcon(image));


        // Print file sizes in KB
        System.out.println("\n******************************************************************");
        if(file!=null){
            //for visuals lang to for easy comparison and testing
            long compressedSize = compressedFile.length();
            long decompressedSize = outputFile.length();
            long decompressedFileSize = file.length();
            System.out.println("*   Selected Image (depends) file size: " + ( decompressedFileSize/ 1024.0) + " KB");
            System.out.println("*   Decompressed (.PNG) file size: " + (decompressedSize / 1024.0) + " KB");
            System.out.println("*   Compressed (.MMT) file size: " + (compressedSize / 1024.0) + " KB");
        }
        System.out.println("*   Image fully decompressed.");
        System.out.println("******************************************************************");
    } catch (IOException e) {
        System.err.println("Error during image decompression: " + e.getMessage());
    } catch (Exception e) {
        System.err.println("Unexpected error: " + e.getMessage());
    }
}
}



