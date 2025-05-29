# Huffman Coding Based Image Compressor

_This project is developed by **Jake Mondragon**, **Gian Tongzon**, and **Kenneth Raniel Montes** to apply Binary Trees for reducing image file sizes through Huffman Coding._

---

## 📚 Table of Contents

- [Introduction](#introduction)  
- [Features](#features)  
- [Installation & Setup](#installation--setup)  
- [Usage](#usage)  
- [Methodology](#methodology)  
- [Algorithm / Design](#algorithm--design)  
- [Data / Dataset](#data--dataset)  
- [Results / Analysis](#results--analysis)  
- [Contributing](#contributing)  
- [License](#license)  
- [Contact](#contact)  

---

## 🧩 Introduction

This project compresses image data using Huffman Coding. The application converts the RGB values of an image into hexadecimal, calculates their frequency, builds a Huffman tree, and encodes the data as binary. This reduces redundancy and overall file size.

---

## ✨ Features

- Select an image to compress.
- Extract RGB and convert to Hex values.
- Generate and store Huffman-encoded data.
- Decompress and view the original image.
- Compression and decompression using BitSet for efficiency.

---

## 🚀 Installation & Setup

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/jbmondragon/Image-Compressor.git
cd Image-Compressor
2️⃣ Open in VS Code
Make sure you have Java Extension Pack installed.

Navigate to the SourceCode/ folder.

Open Main.java.

3️⃣ Run the Application
Click the Run Java button in the top-right corner, or

Press F6 to run.

4️⃣ Build a JAR File (Optional)
Press Ctrl + Shift + P → Open Command Palette.

Search for: Java: Export Jar.

Choose Main as the main class.

Select all required files.

Output: Image-Compressor.jar

5️⃣ Run the JAR File
bash
Copy
Edit
java -jar Image-Compressor.jar
Or double-click the .jar file if Java is installed.

⚠️ Requires Java 21 or higher.

🛠️ Usage
Launch the application.

Choose an image to compress.

The app processes pixel RGB values, converts them to hex, then compresses via Huffman encoding.

You can later decompress and visualize the image from the encoded file.

🧠 Methodology
RGB values extracted from the image.

Converted to hexadecimal format.

Frequency of each hex value is calculated.

Huffman Tree is constructed based on frequencies.

Compression writes binary Huffman codes using BitSet.

Decompression maps binary codes back to original RGB values.

🧮 Algorithm / Design
Main Algorithms Used:
dataExtraction: Extract RGB & convert to hex.

HuffmanTree: Build Huffman coding tree.

compressImage(): Encode image and write using BitSet.

DecompressImage(): Reconstruct original pixels from binary Huffman codes.

📊 Data / Dataset
Accepts any image (bmp, PNG, tiff).

Extracts per-pixel color data (RGB → Hex).

Frequency map generated from hex values.
