package aks;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class Picaso {
    
    public static final String ASCII_CHARS = "@%#*+=-:. ";


    // ALL IN ONE METHOD
    public String imgToAscii(String path, int size){
        File originalImageFile = new File(path);
        BufferedImage image = getImage(originalImageFile);
        // turn it gray
        File grayImgFile = grayscaleImage(image);
        BufferedImage grayedImage = getImage(grayImgFile);
        BufferedImage resized = resizeImage(grayedImage, size);
        String art = asciiArt(resized);
        return art;
    }


    public BufferedImage getImage(String path){
        InputStream is = getClass().getResourceAsStream(path);
        try{
            BufferedImage image = ImageIO.read(is); 
            is.close();
            return image;
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    public BufferedImage getImage(File file){
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public File grayscaleImage(BufferedImage originalImage){
        BufferedImage image = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

        for(int y = 0; y < originalImage.getHeight(); y++){
            for(int x = 0; x < originalImage.getWidth(); x++){
                Color color = new Color(originalImage.getRGB(x, y));
                int gray = (int) (0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue());
                int grayRGB = new Color(gray,gray,gray).getRGB();
                image.setRGB(x, y, grayRGB);
            }
        }

        File file = new File("grayed.png");
        try {
            ImageIO.write(image, "png", file);
            return file;
        } catch (IOException e) {
            System.out.println("Error while saving the image!");
        }

        return null;
    }

    public BufferedImage resizeImage(BufferedImage originImage, int width){

        int height = (int) (originImage.getHeight() * ((double) width / originImage.getWidth()));
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        var g = resizedImage.createGraphics();
        g.drawImage(originImage, 0, 0,width,height, null);
        g.dispose();


        File file = new File("resized.png");
        try {
            ImageIO.write(resizedImage, "png", file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return resizedImage;
    }

    public String asciiArt(BufferedImage image){
        StringBuilder asciiArt = new StringBuilder();

        for(int y = 0; y < image.getHeight(); y ++){
            for(int x = 0; x < image.getWidth(); x++){  
                Color color = new Color(image.getRGB(x, y));
                int grade = color.getRed(); // don't matter which one I pick 'R,G or B' cuh they are equal
                char asciiChar = getChar(grade);
                asciiArt.append(asciiChar);
            }
            asciiArt.append("\n");
        }

        return asciiArt.toString();
    }

    public char getChar(int grade){
        int index = (int) ((grade / 255.0) * (ASCII_CHARS.length() - 1));
        return ASCII_CHARS.charAt(index);
    }
}
