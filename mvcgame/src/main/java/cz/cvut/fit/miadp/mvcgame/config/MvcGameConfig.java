package cz.cvut.fit.miadp.mvcgame.config;

public class MvcGameConfig 
{
    public static final int MAX_X = 1280;
    public static final int MAX_Y = 720;
    public static final int MOVE_STEP = 10;
    public static final int CANNON_POS_X = 50;
    public static final int CANNON_POS_Y = MAX_Y / 2;
    public static final int INIT_POWER = 10;
    public static final int INIT_ANGLE = 0;
    public static final double ANGLE_STEP = Math.PI/18;
    public static final int POWER_STEP = 1;

    public static final double GRAVITY = 9.8;
    public static final long TIME_TICK_PERIOD = 10; // TODO change to reasonable
    public static final int MISSILE_HIT_RADIUS = 20;
    private static final int TOP_BORDER_HEIGHT = 10;
    private static final int INFO_MARGIN = 5;
    public static final int INFO_POS_X = INFO_MARGIN; // TODO change to reasonable
    public static final int INFO_POS_Y = TOP_BORDER_HEIGHT + INFO_MARGIN; // TODO change to reasonable
    public static final int ENEMIES_CNT = 20;
}