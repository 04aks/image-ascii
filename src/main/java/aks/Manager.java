package aks;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class Manager {
    
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

    public void grayscaleImage(BufferedImage originalImage){
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
        } catch (IOException e) {
            System.out.println("Error while saving the image!");
        }
    }
}
