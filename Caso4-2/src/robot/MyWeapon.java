package robot;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MyWeapon extends Weapon{
	public static String rutaImagenes = "C:\\Users\\Erick Kauffmann\\eclipse-workspace\\Caso4-2\\src\\Imagen\\";
	public int WeaponType;
	public boolean weaponEnable = true;
	public float weaponHealth = 100;
	public String WeaponImage;
	public JLabel ImagenWeapon;
	public ArrayList<Bala> disparos;
	
	
	public MyWeapon(int pWeaponType, int pSpeed, int pLevel, int pAttackDistance) {
		super(pSpeed, pLevel, pAttackDistance);
		this.disparos = new ArrayList<Bala>();
		this.WeaponType = pWeaponType;
		if(WeaponType == 1) {
			WeaponImage = "Disparo1.png";
		}
		else if(WeaponType == 2) {
			WeaponImage = "Disparo2.png";
		}
		else if(WeaponType == 3) {
			WeaponImage = "Golpe1.png";
		}
		else if(WeaponType == 4) {
			WeaponImage = "Golpe2.png";
		}
		else if(WeaponType == 5) {
			WeaponImage = "Golpe3.png";
		}
		
		BufferedImage myPicture = null;
		try {
			
			myPicture = ImageIO.read(new File(rutaImagenes + this.WeaponImage));
			
			ImagenWeapon = new JLabel(new ImageIcon(myPicture));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void moverDisparos() {	
		for(int i = 0; i<this.disparos.size(); i++) {
			Bala disparoAct = this.disparos.get(i);
			disparoAct.distancia += this.speed;			
			this.disparos.set(i, disparoAct);
			if(disparoAct.orientacion == ORIENTATION.EAST) {
				disparoAct.posX += this.speed;
			}
			else if(disparoAct.orientacion == ORIENTATION.WEST) {
				disparoAct.posX -= this.speed;
			}
			else if(disparoAct.orientacion == ORIENTATION.NORTH) {
				disparoAct.posY -= this.speed;
			}
			else if(disparoAct.orientacion == ORIENTATION.SOUTH) {
				disparoAct.posY += this.speed;
			}
		}
	}
	
	public void removeDisparos() {
		for(int i = this.disparos.size()-1; i>=0; i--) {
			Bala disparoAct = this.disparos.get(i);
			if(disparoAct.distancia >= this.attackDistance) {
				this.disparos.remove(i);
			}
			else if(disparoAct.posX <= -50) {
				this.disparos.remove(i);
			}
			else if(disparoAct.posX >= 1200) {
				this.disparos.remove(i);
			}
			else if(disparoAct.posY <= -50) {
				this.disparos.remove(i);
			}
			else if(disparoAct.posY >= 700) {
				this.disparos.remove(i);
			}
		}
	}
	
	public int getAttackDistance() {
		return this.attackDistance;
	}
	

	@Override
	protected void triggerWeapon(int pPosX, int pPosY, ORIENTATION pDirection) {
		// TODO Auto-generated method stub
		
	}

}
