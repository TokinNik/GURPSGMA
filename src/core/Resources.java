package core;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

class Resources
{
    static final int width = 1275;
    static final  int height = 850;

    static final Font font11 = new Font("Verdana", Font.PLAIN, 11);
    static final Font font15 = new Font("Verdana", Font.PLAIN, 15);
    static final Font font15B = new Font("Verdana", Font.BOLD, 15);
    static final Font font21 = new Font("Verdana", Font.PLAIN, 21);
    static final Font font31 = new Font("Verdana", Font.PLAIN, 31);

    static final File BG_GRAY =  new File("bg_gray.png");
    static final File BG_GREEN =  new File("bg_green.png");
    static final File BG_RED =  new File("bg_red.png");
    static final File BG_YELLOW =  new File("bg_yellow.png");
    static final File BG_BLUE =  new File("bg_blue.png");
    static final File BACKGROUND =  BG_GREEN;


    static final Color LIGHT_GREEN = Color.getHSBColor(0.3f, 0.6f, 1);
    static final Color GLASS_GREEN = Color.getHSBColor(0.3f, 0.3f, 1);
    static final Color LIGHT_RED = Color.getHSBColor(0.0f, 0.6f, 1);
}
