import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


class Menu{

    JFrame jFrame;


    private BufferedImage menuBackground;    // background screen of menu

    private String imagePath = "F:\\java stuff\\LaserShipGame\\images\\";    // path of the images

    private int mouseX, mouseY;    // x and y coordinates for the mouse

    private Rectangle buttonPlay, buttonSetting, buttonQuit;    // buttons on the menu

    Menu(){
        init();
    }

    /**
     * init and settings
     */
    void init(){
        buttonPlay = new Rectangle(610, 440 , 280, 110);    // play button hit box
        buttonSetting = new Rectangle(610, 610, 280, 110);    // setting button hit box
        buttonQuit = new Rectangle(610, 780, 280, 110);    // quit button hit box
        loadImage();    // load images
    }

    /**
     * loop for menu
     */
    void menuLoop() {

        //printMouseLocation();    // print mouse location

    }

    /**
     * if player clicks
     * @param e
     */
    void click(MouseEvent e){
        setMouseX(e);    // set the location of the mouse
        setMouseY(e);
        System.out.println(mouseX);
        System.out.println(mouseY);
        buttonClick(e);    // check if one of the buttons are clicked
    }

    /**
     * update the x coordinate of the mouse
     */
    void setMouseX(MouseEvent e){
        mouseX = e.getX();
    }

    /**
     * updates the y coordinate of the mouse
     */
    void setMouseY(MouseEvent e){
        mouseY = e.getY();
    }

    /**
     * prints the current location of the mouse
     */
    void printMouseLocation(){
        System.out.println(mouseX);
        System.out.println(mouseY);
    }

    /**
     * if player clicked on a button in menu
     * @param e
     */
    void buttonClick(MouseEvent e){

        if(buttonPlay.contains(mouseX, mouseY)){    // play button
            GameVariables.single = true;
            GameVariables.menu = false;
        }
        if(buttonSetting.contains(mouseX, mouseY)){    // setting button

        }
        if(buttonQuit.contains(mouseX, mouseY)){    // quit button
            GameVariables.inGame = false;

        }

    }


    /**
     * loads images
     */
    void loadImage(){
        try {
            menuBackground = ImageIO.read(new File( imagePath + "MenuBackground.jpg"));
        }catch(Exception e){
            System.out.println("can't load image");
        }
    }


    /**
     * menu background
     * @param g
     */
    void background(Graphics g){

        g.drawImage(menuBackground, 0, 0, null);

    }


    /**
     * all graphics in menu
     * @param g
     */
    void draw(Graphics g) {

        background(g);
    }


    void keys(KeyEvent e) {

        select(e);
    }


    void select(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_S) {
            GameVariables.single = true;
            GameVariables.menu = false;
            GameVariables.multi = false;
        }
    }
}
