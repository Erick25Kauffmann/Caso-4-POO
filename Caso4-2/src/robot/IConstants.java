package robot;

public interface IConstants {
	/* ARENA */
	public static final int ARENA_WIDTH = 1300;
	public static final int ARENA_HEIGTH = 800;
	
	/* ROBOTS */
	public static final int ROBOT_MOVEMENT_LENGTH = 1000;
	public static final int ENERGY_PER_MOVEMENT = 1;
	public static final int WEAPONS_PER_ROBOT = 1;
	public static final int STRIKES_PER_ROBOT = 2;
	public static final int INITIALPOSXI = 550;
	public static final int INITIALPOSYI = 300;
	public static final int INITIALPOSXD = 1750;
	public static final int INITIALPOSYD = 300;
	public static final int LADO_IZQUIERDO = 1;
	public static final int LADO_DERECHO = 2;
	
	public static final String COMANDO_CREAR_ROBOT = "crearRobot:";
	public static final String COMANDO_CAMBIAR_POS = "cambiarPos:";
	
	public static final String COMANDO_CREAR_ROBOT_PARAM_TYPE = "Tipo:";
	public static final String COMANDO_CREAR_ROBOT_PARAM_A1 = "Arma1:";
	public static final String COMANDO_CREAR_ROBOT_PARAM_A2 = "Arma2:";
	public static final String COMANDO_CREAR_ROBOT_PARAM_A3 = "Arma3:";
	public static final String COMANDO_CREAR_ROBOT_PARAM_POSX = "PosX:";
	public static final String COMANDO_CREAR_ROBOT_PARAM_POSY = "PosY:";
	
}