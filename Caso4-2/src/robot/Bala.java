package robot;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bala {
	public static String rutaImagenes = "C:\\Users\\Erick Kauffmann\\eclipse-workspace\\Caso4-2\\src\\Imagen\\";
	public int balaType;
	public int distancia;
	public JLabel imagenBala;
	public String balaImage;
	public int posX;
	public int posY;
	public ORIENTATION orientacion;
	public boolean existeInterfaz;
	
	public Bala(int tipoBala, int x, int y, ORIENTATION pOrientacion) {
		this.distancia = 0;
		this.posX = x;
		this.posY = y;
		this.orientacion = pOrientacion;
		this.existeInterfaz = false;
		this.balaType = tipoBala;
		if(balaType == 1) {
			balaImage = "Disparo1.png";
		}
		else if(balaType == 2) {
			balaImage = "Disparo2.png";
		}
		//System.out.println("balaType: " + balaType);
		BufferedImage myPicture = null;
		try {			
			myPicture = ImageIO.read(new File(rutaImagenes + this.balaImage));
			
			imagenBala = new JLabel(new ImageIcon(myPicture));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
