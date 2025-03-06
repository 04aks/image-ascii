# Image to ASCII - java

##Maven dependency:
```xml
  <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
  </repositories>


  <dependency>
    <groupId>com.github.04aks</groupId>
    <artifactId>image-ascii</artifactId>
    <version>0.1</version>
  </dependency>
```

##How to use
```java
  int scale = 50;

  Picaso picaso = new Picaso();
  String art = picaso.imgToAscii("C:\\path\\to\\image.png", scale);
  System.out.println(art);
```
