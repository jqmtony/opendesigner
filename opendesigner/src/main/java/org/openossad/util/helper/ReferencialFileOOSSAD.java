package org.openossad.util.helper;

import com.mxgraph.util.mxResources;
import org.openossad.ui.component.JXErrorDialog;
import org.openossad.util.DefaultFileFilter;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

public class ReferencialFileOOSSAD {
    Cipher ecipher;
    Cipher dcipher;

    public ReferencialFileOOSSAD(SecretKey key) {
        // Create an 8-byte initialization vector
        byte[] iv = new byte[]{(byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07,0x72, 0x6F, 0x5A};
        AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
        try {
            ecipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            dcipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

            // CBC requires an initialization vector
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        } catch (java.security.InvalidAlgorithmParameterException e) {
        } catch (javax.crypto.NoSuchPaddingException e) {
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch (java.security.InvalidKeyException e) {
        }
    }

    // Buffer used to transport the bytes from one stream to another
    byte[] buf = new byte[1024];

    public void encrypt(InputStream in, OutputStream out) {
        try {
            // Bytes written to out will be encrypted
            out = new CipherOutputStream(out, ecipher);

            // Read in the cleartext bytes and write to out to encrypt
            int numRead = 0;
            while ((numRead = in.read(buf)) >= 0) {
                out.write(buf, 0, numRead);
            }
            out.close();
        } catch (IOException e) {
        }
    }

    public void decrypt(InputStream in, OutputStream out) {
        try {
            // Bytes read from in will be decrypted
            in = new CipherInputStream(in, dcipher);

            // Read in the decrypted bytes and write the cleartext to out
            int numRead = 0;
            while ((numRead = in.read(buf)) >= 0) {
                out.write(buf, 0, numRead);
            }
            out.close();
        } catch (IOException e) {
        }
    }

    public void SecureDelete(String args) throws IOException {

        File file = new File(args);
        if (file.exists()) {
            SecureRandom random = new SecureRandom();
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            FileChannel channel = raf.getChannel();
            MappedByteBuffer buffer= channel.map(FileChannel.MapMode.READ_WRITE, 0, raf.length());
            // overwrite with zeros
            while (buffer.hasRemaining()) {
                buffer.put((byte) 0);
            }
            buffer.force();
            buffer.rewind();
            // overwrite with ones
            while (buffer.hasRemaining()) {
                buffer.put((byte) 0xFF);
            }
            buffer.force();
            buffer.rewind();
            // overwrite with random data; one byte at a time
            byte[] data = new byte[1];
            while (buffer.hasRemaining()) {
                random.nextBytes(data);
                buffer.put(data[0]);
            }
            buffer.force();
            file.delete();
        }
    }


    public static void OpenDocument(String fileName){


        if (fileName.lastIndexOf("/")>0) {

        }
        else {
            fileName=ReferencialOOSSAD.getParsedModelDoc(fileName);
        }
        try {
            // para windows.
            //Runtime.getRuntime().exec("cmd /c \""+fileName+"\"");
            //Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL \""+fileName);
            Desktop.getDesktop().open( new File(fileName) );


        } catch (IOException e) {
            JXErrorDialog.showDialog(null, "Failure opening file", e);

        }
    }

    /*
      * try { // Generate a temporary key. In practice, you would save this key.
      * // See also e464 Encrypting with DES Using a Pass Phrase. SecretKey key =
      * KeyGenerator.getInstance("DES").generateKey();
      *
      * // Create encrypter/decrypter class DesEncrypter encrypter = new
      * DesEncrypter(key);
      *
      * // Encrypt encrypter.encrypt(new FileInputStream("cleartext1"), new
      * FileOutputStream("ciphertext"));
      *
      * // Decrypt encrypter.decrypt(new FileInputStream("ciphertext"), new
      * FileOutputStream("cleartext2")); } catch (Exception e) { }
      */

    public static String getFileToSavePDF() {
        String wd = System.getProperty("user.dir");
        JFileChooser fc = new JFileChooser(wd);
        javax.swing.filechooser.FileFilter defaultFilter = new DefaultFileFilter(".pdf", "Archivo Pdf");
        fc.addChoosableFileFilter(defaultFilter);

        fc.setFileFilter(defaultFilter);
        int rc = fc.showDialog(null, mxResources.get("save"));

        if (rc != JFileChooser.APPROVE_OPTION) { return null;
        } else {
            String lastDir = fc.getSelectedFile().getParent();
        }

        String filename = fc.getSelectedFile().getAbsolutePath();
        javax.swing.filechooser.FileFilter selectedFilter = fc.getFileFilter();

        if (selectedFilter instanceof DefaultFileFilter) {
            String ext = ((DefaultFileFilter) selectedFilter).getExtension();
            if (!filename.toLowerCase().endsWith(ext)) {
                filename += ext;
            }
        }
        return filename;
    }



    public static File createTempDirectory() throws IOException
    {
        final File temp;
        temp = File.createTempFile("temp", "_pdf");

        if(!(temp.delete()))
        {
            throw new IOException("Could not delete temp file: " + temp.getAbsolutePath());
        }

        if(!(temp.mkdir()))
        {
            throw new IOException("Could not create temp directory: " + temp.getAbsolutePath());
        }

        return (temp);
    }


        public static void copyFilesFromResources(String modelFile, String dest) throws IOException
    {
        InputStream in = ReferencialFileOOSSAD.class.getResourceAsStream(modelFile);
        OutputStream out = new FileOutputStream(dest);
        byte[] buffer = new byte[1024];
        int length;
        //copy the file content in bytes
        while ((length = in.read(buffer)) > 0){
            out.write(buffer, 0, length);
        }
        in.close();
        out.close();
    }

       public static boolean deleteDirectory(File path) {
        if( path.exists() ) {
            File[] files = path.listFiles();
            for(int i=0; i<files.length; i++) {
                if(files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                }
                else {
                    files[i].delete();
                }
            }
        }
        return( path.delete() );
    }




}
