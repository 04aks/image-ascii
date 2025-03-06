package aks;

import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        BufferedImage image = manager.getImage("/alcatraz.jpg");
        System.out.println(image);
    }

    
}