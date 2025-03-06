package aks;

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
}
