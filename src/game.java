import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class game extends Application{
//IO
	public static String name="";
	public static String location="U:/GameSave.txt";
//CHARACTER
	public Node character;
	static double characterX=10;
	double characterY=418;
	public Node DEADcharacter;
//BACKGROUND
	boolean atEND=false;
	static public Node background1;
	static double background1X=0;
	static public Node background2;
	static double background2X=1195;
	static public Node background3;
	static double background3X=2390;
//CONTROLS
	boolean JumpUp=false;
	boolean JumpDown=false;
	boolean moveR=false;
	boolean moveL=false;
//MISCHELEANOUS
	static public Node hole;
	static double holeX=2840;
	double floor=412;
	boolean fall=false;
	static public Node tube;
	static double TubeX=923;
	boolean tubeHIT=false;
	static public Node BLOCK1;
	static double block1X=710;
	boolean blockHIT=false;
//LifeRoom
	boolean tubeDOWN=false;
	boolean tubeROOM=false;
	double tubeDOWNlevel=369;
	boolean tubePIC=false;
	boolean ER=false;
	
	boolean tubeLife=true;
//added
	boolean OnCRTube=false;
	boolean EnterCRTube=false;
	boolean STOPCRtube=false;
	boolean ResetCR=false;
//Lives
	int lives=1; //Don't add to CleanUp
	boolean live1STOP=false;
	boolean live2STOP=false;
	public Node live1;
	public Node live2;
	boolean lifee1=false;
	boolean life1RESET=false;
	static public Node life1;
	static double life1X=715;
	static double life1Y=305;
	boolean mDOWN=false;
	boolean RecievedfirstLife=false;
	public Node live3;
	boolean live3STOP=false;
//Coin Room
	boolean levelOneEND=false;
//perm lives
	public Node permLife1;
	public Node permLife2;
	public Node permLife3;
//Door Room
	public Node TubeRoomTube;
	public Node fakeCharacter;
	double fakeCharacterY=500;
	boolean pauseFakeControls=true;
	boolean UpOnDoor_fakeRoom=false;
	boolean TurtleUp=false;
//ENEMIES
	static public Node skel1;
	static public Node deadskel1;
	static public double deadSkel1X=0;
	static public Node skel2;
	static public Node deadskel2;
	static public double deadSkel2X=0;
		
	static double skel1X=1100;
	static double skel2X=900;
	static double skelY=424;
	static boolean deadSkel1=false;
	static boolean deadSkel2=false;
	boolean STOPSKELS=false;//DONT ADD TO CLEANUP
	static public boolean PauseControls=false;
	
//BOSS LEVEL
	public Node fallingBlock1;
	public Node fallingBlock2;
		
	public double fallingBlock1X=200;
	public double fallingBlock1Y=0;
	
	public double fallingBlock2X=660;
	public double fallingBlock2Y=274;//274
	
	boolean fbDOWN=false;
	boolean fbUP=true;
	boolean hitfallingBlock1=false;
	boolean fb2UP=true;
	public Node fallingWall;
	double fallingWallX=1200;
	double fallingWallY=-400;
	public boolean WUP=false;
	boolean hitFallingWall=false;	
	boolean Pause2=false;
	
	boolean bossLevelDIED=false;
	
	boolean toBOSS=false;
	public Node KEYS;
//BOSS
	
	boolean moveRight=false;
	boolean moveLeft=false;
	
	boolean characterR=false;
	boolean characterL=false;
	boolean jump=false;
	
	public Node background;
	double backgroundX=0;
	
	public Node bowser;
	public double bowserX=-200;
	public double bowserY=410;

	public Node bowser2;
	public double bowser2X=-350;
	public double bowser2Y=410;

	public Node bowser3;
	public double bowser3X=-500;
	public double bowser3Y=410;

	public Node bowser4;
	public double bowser4X=-650;
	public double bowser4Y=410;
	//LEFT
	public Node bowser5;
	public double bowser5X=1400;
	public double bowser5Y=410;

	public Node bowser6;
	public double bowser6X=1550;
	public double bowser6Y=410;

	public Node bowser7;
	public double bowser7X=1700;
	public double bowser7Y=410;

	public Node bowser8;
	public double bowser8X=1850;
	public double bowser8Y=410;
	public boolean hitBoss=false;
	
	int kills=0;
	
	
	
/*                                                                                                                                                                                                                                                                                           
                                                                                                                                                                                                                                                                                          dddddddd
   SSSSSSSSSSSSSSS                                                                                           CCCCCCCCCCCCC                                                                WWWWWWWW                           WWWWWWWW                                 lllllll             d::::::d
 SS:::::::::::::::S                                                                                       CCC::::::::::::C                                                                W::::::W                           W::::::W                                 l:::::l             d::::::d
S:::::SSSSSS::::::S                                                                                     CC:::::::::::::::C                                                                W::::::W                           W::::::W                                 l:::::l             d::::::d
S:::::S     SSSSSSS                                                                                    C:::::CCCCCCCC::::C                                                                W::::::W                           W::::::W                                 l:::::l             d:::::d 
S:::::S            uuuuuu    uuuuuu ppppp   ppppppppp       eeeeeeeeeeee    rrrrr   rrrrrrrrr         C:::::C       CCCCCC   ooooooooooo   rrrrr   rrrrrrrrryyyyyyy           yyyyyyy      W:::::W           WWWWW           W:::::W ooooooooooo   rrrrr   rrrrrrrrr   l::::l     ddddddddd:::::d 
S:::::S            u::::u    u::::u p::::ppp:::::::::p    ee::::::::::::ee  r::::rrr:::::::::r       C:::::C               oo:::::::::::oo r::::rrr:::::::::ry:::::y         y:::::y        W:::::W         W:::::W         W:::::Woo:::::::::::oo r::::rrr:::::::::r  l::::l   dd::::::::::::::d 
 S::::SSSS         u::::u    u::::u p:::::::::::::::::p  e::::::eeeee:::::eer:::::::::::::::::r      C:::::C              o:::::::::::::::or:::::::::::::::::ry:::::y       y:::::y          W:::::W       W:::::::W       W:::::Wo:::::::::::::::or:::::::::::::::::r l::::l  d::::::::::::::::d 
  SS::::::SSSSS    u::::u    u::::u pp::::::ppppp::::::pe::::::e     e:::::err::::::rrrrr::::::r     C:::::C              o:::::ooooo:::::orr::::::rrrrr::::::ry:::::y     y:::::y            W:::::W     W:::::::::W     W:::::W o:::::ooooo:::::orr::::::rrrrr::::::rl::::l d:::::::ddddd:::::d 
    SSS::::::::SS  u::::u    u::::u  p:::::p     p:::::pe:::::::eeeee::::::e r:::::r     r:::::r     C:::::C              o::::o     o::::o r:::::r     r:::::r y:::::y   y:::::y              W:::::W   W:::::W:::::W   W:::::W  o::::o     o::::o r:::::r     r:::::rl::::l d::::::d    d:::::d 
       SSSSSS::::S u::::u    u::::u  p:::::p     p:::::pe:::::::::::::::::e  r:::::r     rrrrrrr     C:::::C              o::::o     o::::o r:::::r     rrrrrrr  y:::::y y:::::y                W:::::W W:::::W W:::::W W:::::W   o::::o     o::::o r:::::r     rrrrrrrl::::l d:::::d     d:::::d 
            S:::::Su::::u    u::::u  p:::::p     p:::::pe::::::eeeeeeeeeee   r:::::r                 C:::::C              o::::o     o::::o r:::::r               y:::::y:::::y                  W:::::W:::::W   W:::::W:::::W    o::::o     o::::o r:::::r            l::::l d:::::d     d:::::d 
            S:::::Su:::::uuuu:::::u  p:::::p    p::::::pe:::::::e            r:::::r                  C:::::C       CCCCCCo::::o     o::::o r:::::r                y:::::::::y                    W:::::::::W     W:::::::::W     o::::o     o::::o r:::::r            l::::l d:::::d     d:::::d 
SSSSSSS     S:::::Su:::::::::::::::uup:::::ppppp:::::::pe::::::::e           r:::::r                   C:::::CCCCCCCC::::Co:::::ooooo:::::o r:::::r                 y:::::::y                      W:::::::W       W:::::::W      o:::::ooooo:::::o r:::::r           l::::::ld::::::ddddd::::::dd
S::::::SSSSSS:::::S u:::::::::::::::up::::::::::::::::p  e::::::::eeeeeeee   r:::::r                    CC:::::::::::::::Co:::::::::::::::o r:::::r                  y:::::y                        W:::::W         W:::::W       o:::::::::::::::o r:::::r           l::::::l d:::::::::::::::::d
S:::::::::::::::SS   uu::::::::uu:::up::::::::::::::pp    ee:::::::::::::e   r:::::r                      CCC::::::::::::C oo:::::::::::oo  r:::::r                 y:::::y                          W:::W           W:::W         oo:::::::::::oo  r:::::r           l::::::l  d:::::::::ddd::::d
 SSSSSSSSSSSSSSS       uuuuuuuu  uuuup::::::pppppppp        eeeeeeeeeeeeee   rrrrrrr                         CCCCCCCCCCCCC   ooooooooooo    rrrrrrr                y:::::y                            WWW             WWW            ooooooooooo    rrrrrrr           llllllll   ddddddddd   ddddd
                                     p:::::p                                                                                                                      y:::::y                                                                                                                         
                                     p:::::p                                                                                                                     y:::::y                                                                                                                          
                                    p:::::::p                                                                                                                   y:::::y                                                                                                                           
                                    p:::::::p                                                                                                                  y:::::y                                                                                                                            
                                    p:::::::p                                                                                                                 yyyyyyy                                                                                                                             
                                    ppppppppp                                                                                                                                                                                                                                                     
 */
//START
	public void start(Stage primaryStage){
		new Pane();
		Scene scene=new Scene(menuScreen(),1200,600);
		primaryStage.setTitle("Super Cory World");
		//primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
/*	___  ___                   _____                          
	|  \/  |                  /  ___|                         
	| .  . | ___ _ __  _   _  \ `--.  ___ _ __ ___  ___ _ __  
	| |\/| |/ _ \ '_ \| | | |  `--. \/ __| '__/ _ \/ _ \ '_ \ 
	| |  | |  __/ | | | |_| | /\__/ / (__| | |  __/  __/ | | |
	|_|  |_|\___|_| |_|\__,_| \____/ \___|_|  \___|\___|_| |_|
*/
	public Pane menuScreen(){
		Pane pane=new Pane();
	//SONG
		String menuPath=game.class.getResource("marioMenuSong.mp3").toString();
        Media songg=new Media(menuPath);
        MediaPlayer menuSong=new MediaPlayer(songg);
        menuSong.play();
		
		Image home_screen=new Image(getClass().getResourceAsStream("backgroundd.png"));
		ImageView homepic=new ImageView(home_screen);
		homepic.setFitWidth(1200);
		homepic.setFitHeight(600);
		homepic.setSmooth(true);
		homepic.setCache(true);
		
		DropShadow ds = new DropShadow();
		ds.setOffsetY(9.0f);
		ds.setColor(Color.BLACK);
		
		Rectangle welcome=new Rectangle(270,80,680,200);
		welcome.setFill(Color.CHOCOLATE);
		welcome.setStroke(Color.BLACK);
		welcome.setStyle("-fx-stroke-width: 4;");
		
		Text text1=new Text("SUPER");
		text1.setFill(Color.WHITE);
		text1.setFont(Font.font("Tahoma",FontWeight.BOLD,55));
		text1.setStroke(Color.BLACK);
		text1.setStyle("-fx-stroke-width:2;");
		text1.setEffect(ds);
		text1.setCache(true);
		text1.setX(310);
		text1.setY(145);
		
		Text text2=new Text("CORY WORLD.");
		text2.setFill(Color.WHITE);
		text2.setFont(Font.font("Tahoma",FontWeight.BOLD,75));
		text2.setStroke(Color.BLACK);
		text2.setStyle("-fx-stroke-width:2;");
		text2.setEffect(ds);
		text2.setCache(true);
		text2.setX(305);
		text2.setY(245);
	//ANIMATION
		
		//CLOUD
		Image image_c=new Image(getClass().getResourceAsStream("flyingg.png"));
		ImageView ch1=new ImageView(image_c);
		ch1.setFitHeight(200);
		ch1.setFitWidth(195);
		ch1.setX(100);
		ch1.setY(90);
		Circle cir=new Circle(80,80,280);
		cir.setFill(Color.TRANSPARENT);
		PathTransition pt = new PathTransition();
	    pt.setDuration(Duration.millis(17000));
	    pt.setPath(cir);
	    pt.setNode(ch1);
	    pt.setCycleCount(8);
	    pt.setAutoReverse(true);
	    pt.setCycleCount(3);
	    
	    pt.play(); 
	    //SMILEY
		Image image_d=new Image(getClass().getResourceAsStream("smiley.png"));
		ImageView smiley=new ImageView(image_d);
		smiley.setSmooth(true);
		smiley.setCache(true);
		smiley.setFitHeight(35);
		smiley.setFitWidth(35);
		smiley.setX(430);
		smiley.setY(312);
		//BUTTONS
		Button START=new Button("1 PLAYER GAME");
		START.setFont(Font.font("Monospace",FontWeight.BOLD,40));
		START.setStyle("-fx-background-color:transparent");
		START.setTextFill(Color.WHITE);
		START.setLayoutX(450);
		START.setLayoutY(290);
		START.setOnMouseEntered(e2->{
			START.setTextFill(Color.GOLD);
			smiley.setY(312);
		});
		START.setOnMouseExited(e2->{
			START.setTextFill(Color.WHITE);
		});
		START.setOnAction(e->{
			menuSong.stop();
			START.getScene().setRoot(LevelOne());
		});
	//DUMMY
		Button START2=new Button("2 PLAYER GAME");
		START2.setFont(Font.font("Monospace",FontWeight.BOLD,40));
		START2.setStyle("-fx-background-color:transparent");
		START2.setTextFill(Color.WHITE);
		START2.setLayoutX(450);
		START2.setLayoutY(350);
		START2.setOnMouseEntered(e2->{
			START2.setTextFill(Color.GOLD);
			smiley.setY(368);
		});
		START2.setOnMouseExited(e2->{
			START2.setTextFill(Color.WHITE);
		});	
	//RUNNER
		pane.getChildren().addAll(homepic,ch1,welcome,cir);
		Image image_b=new Image(getClass().getResourceAsStream("character.png"));
		ImageView ch2=new ImageView(image_b);
		ch2.setFitHeight(90);
		ch2.setFitWidth(90);
		ch2.setX(0);
		ch2.setY(377);
		
		Path jumpPath=new Path();
		jumpPath.getElements().add(new MoveTo(-400,464));
		jumpPath.getElements().add(new LineTo(400,464));
		jumpPath.getElements().add(new LineTo(440,384));
		jumpPath.getElements().add(new LineTo(480,464));
		jumpPath.getElements().add(new LineTo(530,464));		
		jumpPath.getElements().add(new LineTo(2700,464));
		PathTransition jumpTransition=new PathTransition();
		jumpTransition.setDuration(Duration.millis(11800));
		jumpTransition.setNode(ch2);
		jumpTransition.setPath(jumpPath);
		jumpTransition.setCycleCount(10);
		jumpTransition.playFromStart();
		
		pane.getChildren().addAll(text1,text2,START,START2,smiley,ch2);
		

		Button Loadd=new Button("Controls/Load");
		Loadd.setFont(Font.font("Monospace",FontWeight.BOLD,20));
		Loadd.setStyle("-fx-background-color:transparent");
		Loadd.setTextFill(Color.WHITE);
		Loadd.setLayoutX(780);
		Loadd.setLayoutY(275);
		pane.getChildren().add(Loadd);
		Loadd.setOnMouseEntered(e2->{
			Loadd.setTextFill(Color.BLUE);
		});
		Loadd.setOnMouseExited(e2->{
			Loadd.setTextFill(Color.WHITE);
		});
		Loadd.setOnAction(e->{
			menuSong.stop();
			Loadd.getScene().setRoot(CL());
		});
		
		
		return pane;
	}
	public Pane CL(){
		Pane pane=new Pane();
		Rectangle rr=new Rectangle(0,0,1200,600);
		rr.setFill(Color.BLACK);
		pane.getChildren().add(rr);
		Button Returnbtn=new Button("Return");
		Returnbtn.setFont(Font.font("Monospace",FontWeight.BOLD,20));
		Returnbtn.setStyle("-fx-background-color:transparent");
		Returnbtn.setTextFill(Color.WHITE);
		Returnbtn.setLayoutX(20);
		Returnbtn.setLayoutY(25);
		pane.getChildren().add(Returnbtn);
		Returnbtn.setOnMouseEntered(e2->{
			Returnbtn.setTextFill(Color.BLUE);
		});
		Returnbtn.setOnMouseExited(e2->{
			Returnbtn.setTextFill(Color.WHITE);
		});
		Returnbtn.setOnAction(e->{
			Returnbtn.getScene().setRoot(menuScreen());
		});
		Image image_controls=new Image(getClass().getResourceAsStream("KEYS.png"));
		KEYS=new ImageView(image_controls);
		((ImageView)KEYS).setFitHeight(200);
		((ImageView)KEYS).setFitWidth(300);
		((ImageView)KEYS).setSmooth(true);
		((ImageView)KEYS).setCache(true);
		((ImageView)KEYS).setX(450);
		((ImageView)KEYS).setY(300);
		pane.getChildren().add(KEYS);
		//Keys
		Text r=new Text("Right");
		r.setFill(Color.WHITE);
		r.setX(780);
		r.setY(455);
		Text l=new Text("Left");
		l.setFill(Color.WHITE);
		l.setX(400);
		l.setY(455);
		Text j=new Text("Release Up Arrow To Jump");
		j.setFill(Color.WHITE);
		j.setX(535);
		j.setY(270);
		Text d=new Text("Down");
		d.setFill(Color.WHITE);
		d.setX(585);
		d.setY(515);
		pane.getChildren().addAll(r,l,j,d);
		
		Text loadText=new Text("LOAD GAME");
		loadText.setFont(Font.font("Monospace",FontWeight.BOLD,20));
		loadText.setFill(Color.ORANGE);
		loadText.setX(550);
		loadText.setY(80);
		
		Button loadGame=new Button("Load Save");
		loadGame.setTranslateX(690);
		loadGame.setTranslateY(100);
		
		
		TextField texty=new TextField();
		texty.setTranslateX(530);
		texty.setTranslateY(100);
		
		pane.getChildren().addAll(texty,loadText,loadGame);
		
		Text ERROR=new Text("File Not Found");
		ERROR.setFont(Font.font("Monospace",FontWeight.BOLD,20));
		ERROR.setFill(Color.RED);
		ERROR.setX(-650);
		ERROR.setY(80);
		pane.getChildren().add(ERROR);
		
		loadGame.setOnAction(e->{
			location=texty.getText();
			File file=new File(texty.getText());
			
			
			try {
				Scanner input = new Scanner(file);
				ERROR.setTranslateX(-650);
				name=input.nextLine();
				String l2=input.nextLine();
				lives=Integer.parseInt(l2);
				
				System.out.println(name+"\n"+lives);
				
				
			} catch (FileNotFoundException e3) {
				System.out.println("FILE NOT FOUND");
				ERROR.setTranslateX(1180);
				ERROR.setTranslateY(80);
				
			}
			
		});
		TextField textyname=new TextField();
		textyname.setTranslateX(530);
		textyname.setTranslateY(170);
		
		Button SvName=new Button("Save Name");
		SvName.setTranslateX(690);
		SvName.setTranslateY(170);
		
		Text loadNm=new Text("SAVE CHARACTER NAME");
		loadNm.setFont(Font.font("Monospace",FontWeight.BOLD,20));
		loadNm.setFill(Color.ORANGE);
		loadNm.setX(500);
		loadNm.setY(155);
		
		pane.getChildren().addAll(textyname,SvName,loadNm);
		
		SvName.setOnAction(e->{
			name=textyname.getText();
		});
		//Save Picture
		
		Image image_ESC=new Image(getClass().getResourceAsStream("esc.png"));
		ImageView ESCpic=new ImageView(image_ESC);
		ESCpic.setFitHeight(100);
		ESCpic.setFitWidth(100);
		ESCpic.setSmooth(true);
		ESCpic.setCache(true);
		ESCpic.setX(50);
		ESCpic.setY(300);
		
		Text escc=new Text("= Save Game");
		escc.setTranslateX(165);
		escc.setTranslateY(350);
		escc.setFill(Color.WHITE);
		
		pane.getChildren().addAll(ESCpic,escc);
		
		
		
		return pane;
	}
/*
	=========================================================
	   _                        _    ____               
	  | |                      | |  / __ \              
	  | |      ___ __   __ ___ | | | |  | | _ __    ___ 
	  | |     / _ \\ \ / // _ \| | | |  | || '_ \  / _ \
	  | |____|  __/ \ V /|  __/| | | |__| || | | ||  __/
	  |______|\___|  \_/  \___||_|  \____/ |_| |_| \___|
	                                                    
	=========================================================
*/       
	public Pane LevelOne(){
		Pane pane=new Pane();
		String sPath=game.class.getResource("gameSong.mp3").toString();
        Media songg=new Media(sPath);
        MediaPlayer gSong=new MediaPlayer(songg);
        gSong.play();
		Jump();
		Timeline jmp = new Timeline(new KeyFrame(
		        Duration.millis(9),
		        ae -> Jump()));
		jmp.setCycleCount(Animation.INDEFINITE);
		jmp.play();
		//skelz
		Timeline moveSkelz = new Timeline(new KeyFrame(
		        Duration.millis(50),
		        ae -> moveSkels()));
		moveSkelz.setCycleCount(Animation.INDEFINITE);
		moveSkelz.play();
		
	//Sep classes ============================================
		gameControls ctrl=new gameControls();
		moveBackgrounds bg=new moveBackgrounds();
	//========================================================
		Button btn=new Button();
		pane.getChildren().add(btn);
	//----------------------------------------------------------------------------------------
	//BACKGROUND 3
		Image image_background3=new Image(getClass().getResourceAsStream("backgroundEND.png"));
		background3=new ImageView(image_background3);
		((ImageView)background3).setFitWidth(1200);
		((ImageView)background3).setFitHeight(600);
		((ImageView)background3).setSmooth(true);
		((ImageView)background3).setCache(true);
		pane.getChildren().add(background3);
	//BACKGROUND 2
		Image image_background2=new Image(getClass().getResourceAsStream("backgroundd.png"));
		background2=new ImageView(image_background2);
		((ImageView)background2).setFitWidth(1200);
		((ImageView)background2).setFitHeight(600);
		((ImageView)background2).setSmooth(true);
		((ImageView)background2).setCache(true);
		pane.getChildren().add(background2);
	//BACKGROUND 1
		Image image_background1=new Image(getClass().getResourceAsStream("backgroundd.png"));
		background1=new ImageView(image_background1);
		((ImageView)background1).setFitWidth(1200);
		((ImageView)background1).setFitHeight(600);
		((ImageView)background1).setSmooth(true);
		((ImageView)background1).setCache(true);
		pane.getChildren().add(background1);
	//Character
		Image image=new Image(getClass().getResourceAsStream("character.png"));
		character=new ImageView(image);
		((ImageView)character).setFitHeight(90);
		((ImageView)character).setFitWidth(90);
		((ImageView)character).setX(characterX);
		((ImageView)character).setY(418);
		pane.getChildren().add(character);
	//Enemy 1
		Image skel1_pic=new Image(getClass().getResourceAsStream("skel.png"));
		skel1=new ImageView(skel1_pic);
		((ImageView)skel1).setFitHeight(100);
		((ImageView)skel1).setFitWidth(100);
		((ImageView)skel1).setSmooth(true);
		((ImageView)skel1).setCache(true);
		((ImageView)skel1).setY(skelY);
		pane.getChildren().add(skel1);
	//Enemy 2
		Image skel1_pic2=new Image(getClass().getResourceAsStream("skel_backwards.png"));
		skel2=new ImageView(skel1_pic2);
		((ImageView)skel2).setFitHeight(100);
		((ImageView)skel2).setFitWidth(100);
		((ImageView)skel2).setSmooth(true);
		((ImageView)skel2).setCache(true);
		((ImageView)skel2).setY(skelY);
		pane.getChildren().add(skel2);
	//Hole
		Rectangle hole=new Rectangle(holeX,500,50,50);
		hole.setFill(Color.TRANSPARENT);
		pane.getChildren().add(hole);
	//Tube
		Image tube_image=new Image(getClass().getResourceAsStream("tube.png"));
		ImageView tube =new ImageView(tube_image);
		((ImageView)tube).setFitHeight(260);
		((ImageView)tube).setFitWidth(260);
		//((ImageView)tube).setX(796);
		((ImageView)tube).setY(360);
		pane.getChildren().add(tube);
		tube.setLayoutX(TubeX);
	//1UP
		Image image_1up=new Image(getClass().getResourceAsStream("1up.png"));
		life1=new ImageView(image_1up);
		((ImageView)life1).setFitWidth(40);
		((ImageView)life1).setFitHeight(43);
		((ImageView)life1).setSmooth(true);
		((ImageView)life1).setCache(true);
		((ImageView)life1).setX(life1X);
		((ImageView)life1).setY(life1Y);
		pane.getChildren().add(life1);
	//Block 1
		Image normalBlock=new Image(getClass().getResourceAsStream("BLOCK.png"));
		BLOCK1=new ImageView(normalBlock);
		((ImageView)BLOCK1).setFitWidth(50);
		((ImageView)BLOCK1).setFitHeight(50);
		((ImageView)BLOCK1).setSmooth(true);
		((ImageView)BLOCK1).setCache(true);
		((ImageView)BLOCK1).setY(300);
		pane.getChildren().add(BLOCK1);
		BLOCK1.setLayoutX(block1X);
		
		if(lives>=0){
			Image image_l1=new Image(getClass().getResourceAsStream("1up.png"));
			permLife1=new ImageView(image_l1);
			((ImageView)permLife1).setFitWidth(30);
			((ImageView)permLife1).setFitHeight(32);
			((ImageView)permLife1).setSmooth(true);
			((ImageView)permLife1).setCache(true);
			((ImageView)permLife1).setX(1150);
			((ImageView)permLife1).setY(20);
			pane.getChildren().add(permLife1);
		}
		if(lives>1){
			Image image_l2=new Image(getClass().getResourceAsStream("1up.png"));
			permLife2=new ImageView(image_l2);
			((ImageView)permLife2).setFitWidth(30);
			((ImageView)permLife2).setFitHeight(32);
			((ImageView)permLife2).setSmooth(true);
			((ImageView)permLife2).setCache(true);
			((ImageView)permLife2).setX(1108);
			((ImageView)permLife2).setY(20);
			pane.getChildren().add(permLife2);
		}
		if(lives>2){
			Image image_l3=new Image(getClass().getResourceAsStream("1up.png"));
			permLife3=new ImageView(image_l3);
			((ImageView)permLife3).setFitWidth(30);
			((ImageView)permLife3).setFitHeight(32);
			((ImageView)permLife3).setSmooth(true);
			((ImageView)permLife3).setCache(true);
			((ImageView)permLife3).setX(1066);
			((ImageView)permLife3).setY(20);
			pane.getChildren().add(permLife3);
		}
//END OF IMPLEMENTS-------------------------------------------------------------------------------------------
		live1STOP=false;
		live2STOP=false;
		live3STOP=false;
		
		Text characterName=new Text(name);
		characterName.setX(960);
		characterName.setY(35);
		characterName.setFont(Font.font("Monospace",FontWeight.BOLD,20));
		characterName.setStyle("-fx-background-color:transparent");
		characterName.setFill(Color.BLUEVIOLET);
		pane.getChildren().add(characterName);
		
	//CONTROLS
		
		//Press
		btn.setOnKeyPressed(e->{
			if(e.getCode()==KeyCode.UP){
				toBOSS=true;
			}
			if(e.getCode()==KeyCode.RIGHT && PauseControls==false){
				//forward character-----------------------------------------------------------
				pane.getChildren().remove(character);
				Image image2=new Image(getClass().getResourceAsStream("character.png"));
				character=new ImageView(image2);
				((ImageView) character).setFitHeight(90);
				((ImageView) character).setFitWidth(90);
				pane.getChildren().add(character);
				character.relocate(characterX, characterY);
				//-----------------------------------------------------------------------------
				moveR=true;
			}
			if(e.getCode()==KeyCode.LEFT && tubeROOM==false && PauseControls==false){
				//backwards character----------------------------------------------------------
				pane.getChildren().remove(character);
				Image image2=new Image(getClass().getResourceAsStream("characterbackwards.png"));
				character=new ImageView(image2);
				((ImageView) character).setFitHeight(90);
				((ImageView) character).setFitWidth(90);
				pane.getChildren().add(character);
				character.relocate(characterX, characterY);
				//------------------------------------------------------------------------------
				moveL=true;
			}
			if(e.getCode()==KeyCode.DOWN && tubeDOWN && characterY>366 && characterY<370 && PauseControls==false){
				System.out.println(characterY);
				tubeROOM=true;
			}
			if(e.getCode()==KeyCode.ESCAPE){
				System.out.println("Character is Trying to Escape D: \n Game Saved");
				
				try {
					PrintWriter out=new PrintWriter(new File(location));
					out.println(name);
					out.print(lives);
					out.close();
				} catch (FileNotFoundException e4) {
					System.out.println("Did not specify Location \n"+e4);
				}
			}
			
			
			
		//Release
		});
		btn.setOnKeyReleased(e->{
			if(e.getCode()==KeyCode.UP && characterY==418.0  && PauseControls==false|| e.getCode()==KeyCode.UP && tubeHIT && tubeROOM==false && PauseControls==false){
				JumpUp=true;
			}
			if(e.getCode()==KeyCode.RIGHT && PauseControls==false){
				moveR=false;
			}
			if(e.getCode()==KeyCode.LEFT && PauseControls==false){
				moveL=false;
			}
			if(e.getCode()==KeyCode.UP){
				toBOSS=false;
			}
		});
		
		
		
		
	//JUMP*************************************************
		AnimationTimer characterJump=new AnimationTimer(){
			public void handle(long arg0) {
				if(JumpUp){
					characterY-=9;
					character.relocate(characterX,characterY);
				}
				if(characterY<230 && tubeHIT==false && blockHIT==false){
					JumpUp=false;
				}
				if(characterY<230 && tubeHIT && characterY>430 && blockHIT==false){
					JumpUp=false;
				}
				if(characterY<180 && tubeHIT && characterY<431 && blockHIT==false){
					JumpUp=false;
				}
				/*
				  __  __           _                               
				  |  \/  |         | |                              
				  | \  / |_   _ ___| |__  _ __ ___   ___  _ __ ___  
				  | |\/| | | | / __| '_ \| '__/ _ \ / _ \| '_ ` _ \ 
				  | |  | | |_| \__ \ | | | | | (_) | (_) | | | | | |
				  |_|  |_|\__,_|___/_| |_|_|  \___/ \___/|_| |_| |_|
				                                                    
				*/  
				if(blockHIT && characterY<310){
					JumpUp=false;
					if(ER==false){
						lifee1=true;
					}
				}
				if(lifee1 && life1RESET==false){
					life1RESET=true;
				}
				if(life1RESET && life1Y>257){
					life1Y-=2;
					life1.relocate(life1X, life1Y);
				}
				if(life1RESET && life1Y<258 && life1X<770){
					life1X+=2;
					life1.relocate(life1X, life1Y);
				}
				if(life1RESET && life1X>765.0 && life1Y<465){
					life1Y+=2;
					life1.relocate(life1X, life1Y);
					mDOWN=true;
				}
				if(mDOWN){
					life1Y+=2;
					life1.relocate(life1X, life1Y);
				}
				if(character.getLayoutX()>life1X-15 && character.getLayoutX()<life1X+30 && character.getLayoutY()>life1Y-25 && character.getLayoutY()<life1Y+20 && RecievedfirstLife==false && ER==false){
					RecievedfirstLife=true;
					int a=lives+1;
					System.out.println("Lives "+a);
					pane.getChildren().remove(life1);
					lives+=1;
					if(lives>=0 && live1STOP==false){
						Image image_live1=new Image(getClass().getResourceAsStream("1up.png"));
						live1=new ImageView(image_live1);
						((ImageView)live1).setFitWidth(30);
						((ImageView)live1).setFitHeight(32);
						((ImageView)live1).setSmooth(true);
						((ImageView)live1).setCache(true);
						((ImageView)live1).setX(1150);
						((ImageView)live1).setY(20);
						pane.getChildren().add(live1);
						live1STOP=true;
					}
					if(lives>1 && live2STOP==false){
						Image image_live2=new Image(getClass().getResourceAsStream("1up.png"));
						live2=new ImageView(image_live2);
						((ImageView)live2).setFitWidth(30);
						((ImageView)live2).setFitHeight(32);
						((ImageView)live2).setSmooth(true);
						((ImageView)live2).setCache(true);
						((ImageView)live2).setX(1108);
						((ImageView)live2).setY(20);
						pane.getChildren().add(live2);
						live2STOP=true;
					}
					if(lives>2 && live3STOP==false){
						Image image_live3=new Image(getClass().getResourceAsStream("1up.png"));
						live3=new ImageView(image_live3);
						((ImageView)live3).setFitWidth(30);
						((ImageView)live3).setFitHeight(32);
						((ImageView)live3).setSmooth(true);
						((ImageView)live3).setCache(true);
						((ImageView)live3).setX(1066);
						((ImageView)live3).setY(20);
						pane.getChildren().add(live3);
						live3STOP=true;
					}
				}
				else{
					mDOWN=false;
				}
			}
		};
		characterJump.start();
	//MOVE CHARACTER ++++++++++++++++++++++++++++++++++++++
		AnimationTimer moveCharacter=new AnimationTimer(){
			public void handle(long arg0) {
				if(moveR && characterX<700 && blockHIT==false || blockHIT==true && moveR && characterY>310 && characterX<700){
					ctrl.CONTROLright(characterX);
					character.setLayoutX(characterX);
				}
				if(moveL && characterX>-0 && blockHIT==false || blockHIT && moveL && characterY>310 && characterX<700){
					ctrl.CONTROLleft(characterX);
					character.setLayoutX(characterX);
				}
			}
		};
		moveCharacter.start();
		//Move Background+++++++++++++++++++++++++++++++++++
		AnimationTimer moveBackground=new AnimationTimer(){
			public void handle(long arg0) {
			//FOWARD
				if(moveR && characterX>690 && background3X>0){
					bg.CONTROLright();
					
					hole.setX(holeX);
					tube.setLayoutX(TubeX);
				}
			//BACKWARDS
				if(moveL && characterX<1 && background1X<0){
					bg.CONTROLleft();
					
					hole.setX(holeX);
					tube.setLayoutX(TubeX);
				}
			//END OF BORDER
				if(moveR && background3X<0 && characterX<1115){
					characterX+=3;
					character.setLayoutX(characterX);
				}
				
			/*    _   _       _      
				 | | | |     | |     
				 | |_| | ___ | | ___ 
				 |  _  |/ _ \| |/ _ \
				 | | | | (_) | |  __/
				 |_| |_|\___/|_|\___|
			*/ 
		    	if(character.getLayoutX()>holeX-30 && character.getLayoutX()<holeX-5){
		    		floor=700;
		    	}
		    	if(!(character.getBoundsInParent().intersects(hole.getBoundsInParent()))){
		    		floor=412;
		    	}
		    //Tube-under
		    	if(!(TubeX>characterX-100 && TubeX<characterX-74)){
		    		tubeDOWN=false;
		    	}
		    	if(TubeX>characterX-100 && TubeX<characterX-74){
		    		tubeDOWN=true;
		    	}
		    	if(TubeX>characterX-150 && TubeX<characterX-20){
		    		tubeHIT=true;
		    	}
		    	else{
		    		tubeHIT=false;
		    		blockHIT=false;
		    	}
			}
		};
		moveBackground.start();
		
	/*	  _____       _____     _           ______                      
		 |_   _|     |_   _|   | |          | ___ \                     
		   | | ___     | |_   _| |__   ___  | |_/ /___   ___  _ __ ___  
		   | |/ _ \    | | | | | '_ \ / _ \ |    // _ \ / _ \| '_ ` _ \ 
		   | | (_) |   | | |_| | |_) |  __/ | |\ \ (_) | (_) | | | | | |
		   \_/\___/    \_/\__,_|_.__/ \___| \_| \_\___/ \___/|_| |_| |_|
	*/                        
		
		AnimationTimer Death=new AnimationTimer(){
			public void handle(long now) {
				//Tube DOwn
		    	 if(tubeROOM){
		    		 if(tubePIC==false){
		    			pane.getChildren().remove(character);
		    			pane.getChildren().add(character);
		    			pane.getChildren().remove(tube);
		    			pane.getChildren().add(tube);
		    			tube.setLayoutX(TubeX);
		    		 }
		    		 characterY+=3;
		    		 character.relocate(characterX, characterY);
		    		 if(characterY>490){
		    			 gSong.stop();
		    			tubeROOM=false;
		    			levelOneEND=true;
		    			int a=lives;
		    			moveCharacter.stop();
		    			moveBackground.stop();
		    			(this).stop();
		    			characterJump.stop();
		    			CleanUp g=new CleanUp();
		    			g.CleanUp();
		    			lives=a;
		    			tubeROOM=true;
		    			moveSkelz.stop();
		    			jmp.stop();
		    			ER=true;
		    			pane.getChildren().remove(permLife1);
		    			pane.getChildren().remove(permLife2);
		    			pane.getChildren().remove(permLife3);
		    			pane.getChildren().remove(live1);
		    			pane.getChildren().remove(live2);
		    			pane.getChildren().remove(live3);
		    			
		    			pane.getChildren().remove(skel1);
		    			pane.getChildren().remove(skel2);
		    			//STOPSKELS=true;
		    			pane.getScene().setRoot(TubeRooom());
//GOTO COIN ROOM-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		    		 }
		    	 }
		    //Block-under
		    	if(block1X>characterX-25 && block1X<characterX+62){
		    		blockHIT=true;
		    		tubeDOWN=false;
		    	}
		    //Boss Door
		    	if(background3X==-1 && characterX>709 && characterX<787 && toBOSS){
		    		gSong.stop();
	    			moveCharacter.stop();
	    			moveBackground.stop();
	    			(this).stop();
	    			characterJump.stop();
	    			moveSkelz.stop();
	    			jmp.stop();
	    			CleanUp g=new CleanUp();
	    			g.CleanUp();
	    			ER=false;
	    			tubeLife=true;
					pane.getScene().setRoot(BossLevel());
//GOTO COIN BOSS ROOM----------------------------------------------------------------------------------------------------------------------------------------------------------------------
		    	}
		    	
/*
 	===============================================================================
 	
		oooooooooo.   oooooooooooo       .o.       ooooooooooooo ooooo   ooooo 
		`888'   `Y8b  `888'     `8      .888.      8'   888   `8 `888'   `888' 
		 888      888  888             .8"888.          888       888     888  
		 888      888  888oooo8       .8' `888.         888       888ooooo888  
		 888      888  888    "      .88ooo8888.        888       888     888  
		 888     d88'  888       o  .8'     `888.       888       888     888  
		o888bood8P'   o888ooooood8 o88o     o8888o     o888o     o888o   o888o 
		                                                                       
		                                                                       
	==============================================================================	     
*/
			//Skel1
				if(skel2X>characterX-65 && skel2X<characterX+50 && characterY<350 && characterY>340 && deadSkel2==false){
					System.out.println("Enemy 1: KILLED");
					
					pane.getChildren().remove(skel2);
					Image deadskel1_pic=new Image(getClass().getResourceAsStream("deadskel.png"));
					deadskel2=new ImageView(deadskel1_pic);
					((ImageView)deadskel2).setFitHeight(100);
					((ImageView)deadskel2).setFitWidth(100);
					((ImageView)deadskel2).setSmooth(true);
					((ImageView)deadskel2).setCache(true);
					pane.getChildren().add(deadskel2);
					deadskel2.setTranslateX(skel2X);
					deadskel2.setTranslateY(skelY+20);
					deadSkel2=true;
					deadSkel2X=skel2X;
				}
			//Skel2
				if(skel1X>characterX-65 && skel1X<characterX+50 && characterY<350 && characterY>340 && deadSkel1==false){
					System.out.println("Enemy 2: KILLED");
					
					pane.getChildren().remove(skel1);
					Image deadskel2_pic=new Image(getClass().getResourceAsStream("deadskel.png"));
					deadskel1=new ImageView(deadskel2_pic);
					((ImageView)deadskel1).setFitHeight(100);
					((ImageView)deadskel1).setFitWidth(100);
					((ImageView)deadskel1).setSmooth(true);
					((ImageView)deadskel1).setCache(true);
					pane.getChildren().add(deadskel1);
					deadskel1.setTranslateX(skel1X);
					deadskel1.setTranslateY(skelY+20);
					deadSkel1=true;
					deadSkel1X=skel1X;
				}
				
				
				if(characterY>650 && lives>0 && STOPSKELS==false || skel1X>characterX-65 && skel1X<characterX+50 && deadSkel1==false && characterY>339 && lives>0 && STOPSKELS==false
						|| skel2X>characterX-65 && skel2X<characterX+50 && deadSkel2==false && characterY>339 && lives>0 && STOPSKELS==false){
					
					characterY=-200;
					characterX=100;
					character.relocate(100, -200);
					if(lives==1){
						pane.getChildren().remove(live1);
						pane.getChildren().remove(permLife1);
					}
					if(lives==2){
						pane.getChildren().remove(live2);
						pane.getChildren().remove(permLife2);
					}
					if(lives==3){
						pane.getChildren().remove(live3);
						pane.getChildren().remove(permLife3);
					}
					lives-=1;
					int a=lives;
					System.out.println(a);
				}
				if(characterY>650 && lives==0 && STOPSKELS==false || skel1X>characterX-65 && skel1X<characterX+50 && deadSkel1==false && characterY>339  && lives==0 && STOPSKELS==false
						|| skel2X>characterX-65 && skel2X<characterX+50 && deadSkel2==false && characterY>339 && lives==0&& STOPSKELS==false){
					STOPSKELS=false;
					PauseControls=true;
					
					if(!(characterY>650 && lives==0 && STOPSKELS==false)){
						pane.getChildren().remove(character);
						Image image=new Image(getClass().getResourceAsStream("dead.png"));
						DEADcharacter=new ImageView(image);
						((ImageView) DEADcharacter).setFitHeight(90);
						((ImageView) DEADcharacter).setFitWidth(90);
						((ImageView) DEADcharacter).setX(characterX);
						((ImageView) DEADcharacter).setY(418+40);
						pane.getChildren().add(DEADcharacter);
					}
					
					moveSkelz.stop();
					jmp.stop();
					Button retry=new Button("RETRY");
					retry.setFont(Font.font("Monospace",FontWeight.BOLD,40));
					retry.setStyle("-fx-background-color:transparent");
					retry.setLayoutX(570);
					retry.setLayoutY(280);
					retry.setOnMouseEntered(e->{
						retry.setTextFill(Color.GOLD);
					});
					retry.setOnMouseExited(e->{
						retry.setTextFill(Color.DIMGRAY);
					});
					retry.setOnAction(e->{
		    			moveCharacter.stop();
		    			moveBackground.stop();
		    			characterJump.stop();
		    			CleanUp g=new CleanUp();
		    			g.CleanUp();
		    			ER=false;
		    			lives=1;
		    			tubeLife=true;
						pane.getScene().setRoot(LevelOne());
					});
					Rectangle blackScreen=new Rectangle(250,100,650,320);
					blackScreen.setFill(Color.BLACK);
					Text lose=new Text("YOU LOSE");
					lose.setFill(Color.WHITE);
					lose.setFont(Font.font("Monospace",FontWeight.BOLD,55));
					lose.setX(430);
					lose.setY(210);
					Button quit=new Button("QUIT");
					quit.setFont(Font.font("Monospace",FontWeight.BOLD,40));
					quit.setStyle("-fx-background-color:transparent");
					quit.setLayoutX(370);
					quit.setLayoutY(280);
					quit.setOnMouseEntered(e2->{
						quit.setTextFill(Color.RED);
					});
					quit.setOnMouseExited(e2->{
						quit.setTextFill(Color.DIMGRAY);
					});
					quit.setOnAction(e->{
		    			moveCharacter.stop();
		    			moveBackground.stop();
		    			characterJump.stop();
		    			CleanUp g=new CleanUp();
		    			g.CleanUp();
		    			ER=false;
		    			lives=1;
		    			tubeLife=true;
						pane.getScene().setRoot(menuScreen());
					});
					pane.getChildren().addAll(blackScreen,lose,quit,retry);
					(this).stop();
		    	}
			}
		};
		Death.start();

		
		
		return pane;
	}
/*
	============================================================= 
	   _______    _            _____                       
	  |__   __|  | |          |  __ \                      
	     | |_   _| |__   ___  | |__) |___   ___  _ __ ___  
	     | | | | | '_ \ / _ \ |  _  // _ \ / _ \| '_ ` _ \ 
	     | | |_| | |_) |  __/ | | \ \ (_) | (_) | | | | | |
	     |_|\__,_|_.__/ \___| |_|  \_\___/ \___/|_| |_| |_|
	=============================================================                                                   
*/
	public Pane TubeRooom(){
			Pane pane=new Pane();

			Timeline jmpCR = new Timeline(new KeyFrame(
			        Duration.millis(9),
			        ae -> JumpCoinRoom()));
			jmpCR.setCycleCount(Animation.INDEFINITE);
			jmpCR.play();
			
			characterY=-280;
			characterX=60;
		new gameControls();
		//========================================================
			Button btn=new Button();
			pane.getChildren().add(btn);
			
		//BACKGROUND 1
			Image image_background1=new Image(getClass().getResourceAsStream("coinRoom.png"));
			background1=new ImageView(image_background1);
			((ImageView)background1).setFitWidth(1200);
			((ImageView)background1).setFitHeight(600);
			((ImageView)background1).setSmooth(true);
			((ImageView)background1).setCache(true);
			pane.getChildren().add(background1);
		//Character
			Image image=new Image(getClass().getResourceAsStream("character.png"));
			character=new ImageView(image);
			((ImageView)character).setFitHeight(90);
			((ImageView)character).setFitWidth(90);
			((ImageView)character).setX(characterX);
			((ImageView)character).setY(418);
			pane.getChildren().add(character);
		//Tube Down
			Image tube_image=new Image(getClass().getResourceAsStream("tubeDown.png"));
			ImageView tube =new ImageView(tube_image);
			tube.setFitHeight(260);
			tube.setFitWidth(260);
			tube.setX(-20);
			tube.setY(-130);
			pane.getChildren().add(tube);
		//Tube Left
			Image tube_Left=new Image(getClass().getResourceAsStream("tubeLeft.png"));
			ImageView tubeLeft =new ImageView(tube_Left);
			tubeLeft.setFitHeight(310);
			tubeLeft.setFitWidth(300);
			tubeLeft.setX(980);
			tubeLeft.setY(252);
			pane.getChildren().add(tubeLeft);
	//END OF IMPLEMENTS--------------------------------------------------------------------
			Text text2=new Text("CORY WORLD.");
			text2.setFill(Color.WHITE);
			text2.setFont(Font.font("Tahoma",FontWeight.BOLD,75));
			text2.setStroke(Color.BLACK);
			text2.setStyle("-fx-stroke-width:2;");
			text2.setCache(true);
			text2.setX(305);
			text2.setY(120);
			
			Text text1=new Text("Developed By Cory");
			text1.setFill(Color.GRAY);
			text1.setFont(Font.font("Tahoma",FontWeight.BOLD,35));
			text1.setStroke(Color.BLACK);
			text1.setStyle("-fx-stroke-width:2;");
			text1.setCache(true);
			text1.setX(370);
			text1.setY(170);
			
			pane.getChildren().addAll(text2,text1);
			
			if(tubeLife){
				Image image_1up=new Image(getClass().getResourceAsStream("1up.png"));
				life1=new ImageView(image_1up);
				((ImageView)life1).setFitWidth(40);
				((ImageView)life1).setFitHeight(43);
				((ImageView)life1).setSmooth(true);
				((ImageView)life1).setCache(true);
				((ImageView)life1).setX(800);
				((ImageView)life1).setY(463);
				pane.getChildren().add(life1);
			}
			
		//CONTROLS
			btn.setOnKeyPressed(e->{
				if(e.getCode()==KeyCode.UP){
					
				}
				if(e.getCode()==KeyCode.RIGHT && EnterCRTube==false){
					//forward character-----------------------------------------------------------
					pane.getChildren().remove(character);
					Image image2=new Image(getClass().getResourceAsStream("character.png"));
					character=new ImageView(image2);
					((ImageView) character).setFitHeight(90);
					((ImageView) character).setFitWidth(90);
					pane.getChildren().add(character);
					character.relocate(characterX, characterY);
					//-----------------------------------------------------------------------------
					moveR=true;
				}
				if(e.getCode()==KeyCode.LEFT && EnterCRTube==false){
					//backwards character----------------------------------------------------------
					pane.getChildren().remove(character);
					Image image2=new Image(getClass().getResourceAsStream("characterbackwards.png"));
					character=new ImageView(image2);
					((ImageView) character).setFitHeight(90);
					((ImageView) character).setFitWidth(90);
					pane.getChildren().add(character);
					character.relocate(characterX, characterY);
					//------------------------------------------------------------------------------
					moveL=true;
				}
				if(e.getCode()==KeyCode.ESCAPE){
					System.out.println("Character is Trying to Escape D: \nGame Saved");
					
					try {
						PrintWriter out=new PrintWriter(new File(location));
						out.println(name);
						out.print(lives);
						out.close();
					} catch (FileNotFoundException e4) {
						System.out.println("Did not specify Location \n"+e4);
					}
				}
			//Release
			});
			btn.setOnKeyReleased(e->{
				if(e.getCode()==KeyCode.UP && characterY==418 || e.getCode()==KeyCode.UP && characterY<=338 && characterY>=334){
					JumpUp=true;
				}
				if(e.getCode()==KeyCode.RIGHT){
					moveR=false;
				}
				if(e.getCode()==KeyCode.LEFT){
					moveL=false;
				}
			});
		//JUMP*************************************************
			AnimationTimer characterJump=new AnimationTimer(){
				public void handle(long arg0) {
					if(JumpUp){
						characterY-=9;
						character.relocate(characterX,characterY);
					}
					if(characterY<230 && OnCRTube==false){
						JumpUp=false;
					}
					if(characterY<151 && OnCRTube){
						JumpUp=false;
					}
				}
			};
			characterJump.start();
		//MOVE CHARACTER ++++++++++++++++++++++++++++++++++++++
			AnimationTimer moveCharacter=new AnimationTimer(){
				public void handle(long arg0) {
					if(moveR && characterX<967){
						characterX+=3;
						character.setLayoutX(characterX);
					}
					if(moveL && characterX>22){
						characterX-=3;
						character.setLayoutX(characterX);
					}
				//If on Tube
					if(moveR && characterX>=967 && characterX<1100){
						characterX+=3;
						character.setLayoutX(characterX);
					}
					if(characterX>967){
						OnCRTube=true;
					}
					else{
						OnCRTube=false;
					}
		//Get Life
					if(tubeLife && characterX>738 && characterX<819 && characterY>350){
						tubeLife=false;
						pane.getChildren().remove(life1);
						lives+=1;
					}
		//Enter Tube
					if(characterY==418 && characterX>967 && STOPCRtube==false){
						EnterCRTube=true;
						pane.getChildren().remove(tubeLeft);
						pane.getChildren().add(tubeLeft);
						if(characterX<1200){
							characterX+=1;
							character.relocate(characterX, characterY);
							if(characterX>1150){
								STOPCRtube=true;
								OnCRTube=false;
								EnterCRTube=false;
								
								tubeROOM=true;
								characterJump.stop();
								(this).stop();
								ResetCR=true;
								int a=lives;
				    			CleanUp g=new CleanUp();
				    			g.CleanUp();
				    			lives=a;
								jmpCR.stop();
								characterY=-200;
								pane.getScene().setRoot(DoorRooom());
							}
						}
					}
				}
			};
			moveCharacter.start();
			if(ResetCR){
				ResetCR=false;
			}
			
			return pane;
		}
	/*
		============================================================= 
		  _____                     _____                       
		 |  __ \                   |  __ \                      
		 | |  | | ___   ___  _ __  | |__) |___   ___  _ __ ___  
		 | |  | |/ _ \ / _ \| '__| |  _  // _ \ / _ \| '_ ` _ \ 
		 | |__| | (_) | (_) | |    | | \ \ (_) | (_) | | | | | |
		 |_____/ \___/ \___/|_|    |_|  \_\___/ \___/|_| |_| |_|
		=============================================================                                                   
	*/
		public Pane DoorRooom(){
			Pane pane=new Pane();
			
			Timeline jmpCR2 = new Timeline(new KeyFrame(
			        Duration.millis(9),
			        ae -> JumpDoorRoom()));
			jmpCR2.setCycleCount(Animation.INDEFINITE);
			jmpCR2.play();
			
			characterY=200;
			characterX=60;
		new gameControls();
		//========================================================
			Button btn=new Button();
			pane.getChildren().add(btn);
			
		//BACKGROUND 1
			Image image_background1=new Image(getClass().getResourceAsStream("backgroundENDD.png"));
			background1=new ImageView(image_background1);
			((ImageView)background1).setFitWidth(1200);
			((ImageView)background1).setFitHeight(600);
			((ImageView)background1).setSmooth(true);
			((ImageView)background1).setCache(true);
			pane.getChildren().add(background1);
		//Character
			Image image=new Image(getClass().getResourceAsStream("character.png"));
			character=new ImageView(image);
			((ImageView)character).setFitHeight(90);
			((ImageView)character).setFitWidth(90);
			((ImageView)character).setX(-200);
			((ImageView)character).setY(418);
		//fake character
			Image image_fakeCharacter=new Image(getClass().getResourceAsStream("character.png"));
			fakeCharacter=new ImageView(image_fakeCharacter);
			((ImageView)fakeCharacter).setFitHeight(90);
			((ImageView)fakeCharacter).setFitWidth(90);
			((ImageView)fakeCharacter).setX(1073);
			//418
			((ImageView)fakeCharacter).setY(418);
			pane.getChildren().add(fakeCharacter);
		//Tube
			Image tube_image=new Image(getClass().getResourceAsStream("tube.png"));
			ImageView TubeRoomTube =new ImageView(tube_image);
			((ImageView)TubeRoomTube).setFitHeight(260);
			((ImageView)TubeRoomTube).setFitWidth(260);
			((ImageView)TubeRoomTube).setX(988);
			((ImageView)TubeRoomTube).setY(408);
			pane.getChildren().add(TubeRoomTube);
			
			if(lives>=0){
				Image image_l1=new Image(getClass().getResourceAsStream("1up.png"));
				permLife1=new ImageView(image_l1);
				((ImageView)permLife1).setFitWidth(30);
				((ImageView)permLife1).setFitHeight(32);
				((ImageView)permLife1).setSmooth(true);
				((ImageView)permLife1).setCache(true);
				((ImageView)permLife1).setX(1150);
				((ImageView)permLife1).setY(20);
				pane.getChildren().add(permLife1);
			}
			if(lives>1){
				Image image_l2=new Image(getClass().getResourceAsStream("1up.png"));
				permLife2=new ImageView(image_l2);
				((ImageView)permLife2).setFitWidth(30);
				((ImageView)permLife2).setFitHeight(32);
				((ImageView)permLife2).setSmooth(true);
				((ImageView)permLife2).setCache(true);
				((ImageView)permLife2).setX(1108);
				((ImageView)permLife2).setY(20);
				pane.getChildren().add(permLife2);
			}
			if(lives>2){
				Image image_l3=new Image(getClass().getResourceAsStream("1up.png"));
				permLife3=new ImageView(image_l3);
				((ImageView)permLife3).setFitWidth(30);
				((ImageView)permLife3).setFitHeight(32);
				((ImageView)permLife3).setSmooth(true);
				((ImageView)permLife3).setCache(true);
				((ImageView)permLife3).setX(1066);
				((ImageView)permLife3).setY(20);
				pane.getChildren().add(permLife3);
			}
			//Lives
			if(lives>=0){
				Image image_l1=new Image(getClass().getResourceAsStream("1up.png"));
				permLife1=new ImageView(image_l1);
				((ImageView)permLife1).setFitWidth(30);
				((ImageView)permLife1).setFitHeight(32);
				((ImageView)permLife1).setSmooth(true);
				((ImageView)permLife1).setCache(true);
				((ImageView)permLife1).setX(1150);
				((ImageView)permLife1).setY(20);
				pane.getChildren().add(permLife1);
			}
			if(lives>1){
				Image image_l2=new Image(getClass().getResourceAsStream("1up.png"));
				permLife2=new ImageView(image_l2);
				((ImageView)permLife2).setFitWidth(30);
				((ImageView)permLife2).setFitHeight(32);
				((ImageView)permLife2).setSmooth(true);
				((ImageView)permLife2).setCache(true);
				((ImageView)permLife2).setX(1108);
				((ImageView)permLife2).setY(20);
				pane.getChildren().add(permLife2);
			}
			if(lives>2){
				Image image_l3=new Image(getClass().getResourceAsStream("1up.png"));
				permLife3=new ImageView(image_l3);
				((ImageView)permLife3).setFitWidth(30);
				((ImageView)permLife3).setFitHeight(32);
				((ImageView)permLife3).setSmooth(true);
				((ImageView)permLife3).setCache(true);
				((ImageView)permLife3).setX(1066);
				((ImageView)permLife3).setY(20);
				pane.getChildren().add(permLife3);
			}
			Text characterName=new Text(name);
			characterName.setX(960);
			characterName.setY(35);
			characterName.setFont(Font.font("Monospace",FontWeight.BOLD,20));
			characterName.setStyle("-fx-background-color:transparent");
			characterName.setFill(Color.BLUEVIOLET);
			pane.getChildren().add(characterName);
	//END OF IMPLEMENTS-------------------------------------------------------------------------------------------
			
		//Fake Character
			AnimationTimer fakeMove=new AnimationTimer(){
				public void handle(long arg0) {
					if(!(fakeCharacterY==418)){
						fakeCharacterY-=1;
						fakeCharacter.relocate(1073, fakeCharacterY);
						pane.getChildren().remove(TubeRoomTube);
						pane.getChildren().add(TubeRoomTube);
					}
					if(fakeCharacterY==418){
						System.out.println("Hit Floor Level:");
						 pauseFakeControls=false;
						 pane.getChildren().remove(fakeCharacter);
						 characterX=1073;
						 characterY=418;
						 pane.getChildren().add(character);
						 character.relocate(1073, 418);
						 (this).stop();
					}
				}
			};
			fakeMove.start();
		//CONTROLS
			btn.setOnKeyPressed(e->{
				if(e.getCode()==KeyCode.UP){
					TurtleUp=true;
				}
				if(e.getCode()==KeyCode.RIGHT && pauseFakeControls==false){
					//forward character------------------------------------------------------------
					pane.getChildren().remove(character);
					Image image2=new Image(getClass().getResourceAsStream("character.png"));
					character=new ImageView(image2);
					((ImageView) character).setFitHeight(90);
					((ImageView) character).setFitWidth(90);
					pane.getChildren().add(character);
					character.relocate(characterX, characterY);
					//-----------------------------------------------------------------------------
					moveR=true;
				}
				if(e.getCode()==KeyCode.LEFT && pauseFakeControls==false){
					//backwards character----------------------------------------------------------
					pane.getChildren().remove(character);
					Image image2=new Image(getClass().getResourceAsStream("characterbackwards.png"));
					character=new ImageView(image2);
					((ImageView) character).setFitHeight(90);
					((ImageView) character).setFitWidth(90);
					pane.getChildren().add(character);
					character.relocate(characterX, characterY);
					//------------------------------------------------------------------------------
					moveL=true;
				}
				if(e.getCode()==KeyCode.ESCAPE){
					System.out.println("Character is Trying to Escape D: \nGame Saved");
					
					try {
						PrintWriter out=new PrintWriter(new File(location));
						out.println(name);
						out.print(lives);
						out.close();
					} catch (FileNotFoundException e4) {
						System.out.println("Did not specify Location \n"+e4);
					}
				}
			//Release
			});
			btn.setOnKeyReleased(e->{
				if(e.getCode()==KeyCode.UP){
					if(characterY<=418 && characterY>=414 && pauseFakeControls==false){
						JumpUp=true;
					}
					System.out.println(characterY);
					TurtleUp=false;
				}
				if(e.getCode()==KeyCode.RIGHT){
					moveR=false;
				}
				if(e.getCode()==KeyCode.LEFT){
					moveL=false;
				}
			});
		//JUMP*************************************************
			AnimationTimer characterJump=new AnimationTimer(){
				public void handle(long arg0) {
					if(JumpUp){
						characterY-=9;
						character.relocate(characterX,characterY);
					}
					if(characterY<230){
						JumpUp=false;
					}
					if(characterY<151){
						JumpUp=false;
					}
				}
			};
			characterJump.start();
		//MOVE CHARACTER ++++++++++++++++++++++++++++++++++++++
			AnimationTimer moveCharacter=new AnimationTimer(){
				public void handle(long arg0) {
					if(moveR && characterX<1130){
						characterX+=3;
						character.setLayoutX(characterX);
					}
					if(moveL && characterX>-5){
						characterX-=3;
						character.setLayoutX(characterX);
					}
				}
			};
			moveCharacter.start();
	//To Next Room
			AnimationTimer toNextRoom=new AnimationTimer(){
				public void handle(long now) {
					if(TurtleUp && characterX>701 && characterX<797){
						System.out.println("To Boss Room");
						
						moveCharacter.stop();
						characterJump.stop();
						(this).stop();
						//Reset
						fakeCharacterY=500;
						pauseFakeControls=true;
						UpOnDoor_fakeRoom=false;
						TurtleUp=false;
						jmpCR2.stop();
						
						CleanUp a=new CleanUp();
						a.CleanUp();
						
						STOPSKELS=false;
						pane.getScene().setRoot(BossLevel());
					}
				}
			};
			toNextRoom.start();
			
			return pane;
		}			
/*
===========================================================================================================
		/$$$$$$$                                     /$$                                     /$$
		| $$__  $$                                   | $$                                    | $$
		| $$  \ $$  /$$$$$$   /$$$$$$$ /$$$$$$$      | $$        /$$$$$$  /$$    /$$ /$$$$$$ | $$
		| $$$$$$$  /$$__  $$ /$$_____//$$_____/      | $$       /$$__  $$|  $$  /$$//$$__  $$| $$
		| $$__  $$| $$  \ $$|  $$$$$$|  $$$$$$       | $$      | $$$$$$$$ \  $$/$$/| $$$$$$$$| $$
		| $$  \ $$| $$  | $$ \____  $$\____  $$      | $$      | $$_____/  \  $$$/ | $$_____/| $$
		| $$$$$$$/|  $$$$$$/ /$$$$$$$//$$$$$$$/      | $$$$$$$$|  $$$$$$$   \  $/  |  $$$$$$$| $$
		|_______/  \______/ |_______/|_______/       |________/ \_______/    \_/    \_______/|__/
		
============================================================================================================
*/
			public Pane BossLevel(){
				Pane pane=new Pane();
				
				background1X=0;
				background2X=1200;
				background3X=2400;
			//added
				fallingBlock1X=200;
				fallingBlock1Y=0;
				fallingBlock2X=660;
				fallingBlock2Y=274;//274
				fbDOWN=false;
				fbUP=true;
				hitfallingBlock1=false;
				fb2UP=true;
				fallingWallX=1200;
				fallingWallY=-400;
				WUP=false;
				hitFallingWall=false;
				Pause2=false;
				bossLevelDIED=false;
				
				
				
			new gameControls();
			//========================================================
				Button btn=new Button();
				pane.getChildren().add(btn);
			//BACKGROUND 1
				Image image_background1=new Image(getClass().getResourceAsStream("BOSSbackground.png"));
				background1=new ImageView(image_background1);
				((ImageView)background1).setFitWidth(1200);
				((ImageView)background1).setFitHeight(600);
				((ImageView)background1).setSmooth(true);
				((ImageView)background1).setCache(true);
				pane.getChildren().add(background1);
			//BACKGROUND 2
				Image image_background2=new Image(getClass().getResourceAsStream("BOSSbackground.png"));
				background2=new ImageView(image_background2);
				((ImageView)background2).setFitWidth(1200);
				((ImageView)background2).setFitHeight(600);
				((ImageView)background2).setSmooth(true);
				((ImageView)background2).setCache(true);
				pane.getChildren().add(background2);
			//BACKGROUND 3
				Image image_background3=new Image(getClass().getResourceAsStream("BOSSbackground.png"));
				background3=new ImageView(image_background3);
				((ImageView)background3).setFitWidth(1200);
				((ImageView)background3).setFitHeight(600);
				((ImageView)background3).setSmooth(true);
				((ImageView)background3).setCache(true);
				pane.getChildren().add(background3);
			//Falling Wall :)
				Image image_fwall=new Image(getClass().getResourceAsStream("fallingWall.png"));
				fallingWall=new ImageView(image_fwall);
				((ImageView)fallingWall).setFitWidth(1200);
				((ImageView)fallingWall).setFitHeight(600);
				((ImageView)fallingWall).setSmooth(true);
				((ImageView)fallingWall).setCache(true);
				pane.getChildren().add(fallingWall);
				fallingWall.setTranslateX(fallingWallX);
				fallingWall.setTranslateY(-200);
			//Character
				Image image=new Image(getClass().getResourceAsStream("character.png"));
				character=new ImageView(image);
				((ImageView)character).setFitHeight(90);
				((ImageView)character).setFitWidth(90);
				((ImageView)character).setY(characterY);
				pane.getChildren().add(character);
				character.relocate(characterX, characterY);
			//Falling Block 1
				Image image_FB1=new Image(getClass().getResourceAsStream("fallingBlock.png"));
				fallingBlock1=new ImageView(image_FB1);
				((ImageView)fallingBlock1).setFitHeight(150);
				((ImageView)fallingBlock1).setFitWidth(190);
				((ImageView)fallingBlock1).setY(fallingBlock1Y);
				pane.getChildren().add(fallingBlock1);
				fallingBlock1.relocate(fallingBlock1X, fallingBlock1Y);
				fallingBlock1X-=205;
			//Falling Block 2
				Image image_FB2=new Image(getClass().getResourceAsStream("fallingBlock2.png"));
				fallingBlock2=new ImageView(image_FB2);
				((ImageView)fallingBlock2).setFitHeight(230);
				((ImageView)fallingBlock2).setFitWidth(330);
				pane.getChildren().add(fallingBlock2);
				fallingBlock2.relocate(fallingBlock2X, fallingBlock2Y);
				fallingBlock2X-=665;
				
				Text characterName=new Text(name);
				characterName.setX(960);
				characterName.setY(35);
				characterName.setFont(Font.font("Monospace",FontWeight.BOLD,20));
				characterName.setStyle("-fx-background-color:transparent");
				characterName.setFill(Color.BLUEVIOLET);
				pane.getChildren().add(characterName);
		//END OF IMPLEMENTS-------------------------------------------------------------------------------------------
				
				//Lives ------------------------------------------------------------------------
				if(lives>=1){
					Image image_l1=new Image(getClass().getResourceAsStream("1up.png"));
					permLife1=new ImageView(image_l1);
					((ImageView)permLife1).setFitWidth(30);
					((ImageView)permLife1).setFitHeight(32);
					((ImageView)permLife1).setSmooth(true);
					((ImageView)permLife1).setCache(true);
					((ImageView)permLife1).setX(1150);
					((ImageView)permLife1).setY(20);
					pane.getChildren().add(permLife1);
				}
				if(lives>1){
					Image image_l2=new Image(getClass().getResourceAsStream("1up.png"));
					permLife2=new ImageView(image_l2);
					((ImageView)permLife2).setFitWidth(30);
					((ImageView)permLife2).setFitHeight(32);
					((ImageView)permLife2).setSmooth(true);
					((ImageView)permLife2).setCache(true);
					((ImageView)permLife2).setX(1108);
					((ImageView)permLife2).setY(20);
					pane.getChildren().add(permLife2);
				}
				if(lives>2){
					Image image_l3=new Image(getClass().getResourceAsStream("1up.png"));
					permLife3=new ImageView(image_l3);
					((ImageView)permLife3).setFitWidth(30);
					((ImageView)permLife3).setFitHeight(32);
					((ImageView)permLife3).setSmooth(true);
					((ImageView)permLife3).setCache(true);
					((ImageView)permLife3).setX(1066);
					((ImageView)permLife3).setY(20);
					pane.getChildren().add(permLife3);
				}
			//----------------------------------------------------------------------------------
				
			//CONTROLS
				btn.setOnKeyPressed(e->{
					if(e.getCode()==KeyCode.UP){
						
					}
					if(e.getCode()==KeyCode.RIGHT && bossLevelDIED==false){
						//forward character-----------------------------------------------------------
						pane.getChildren().remove(character);
						Image image2=new Image(getClass().getResourceAsStream("character.png"));
						character=new ImageView(image2);
						((ImageView) character).setFitHeight(90);
						((ImageView) character).setFitWidth(90);
						pane.getChildren().add(character);
						character.relocate(characterX, characterY);
						//-----------------------------------------------------------------------------
						moveR=true;
					}
					if(e.getCode()==KeyCode.LEFT && bossLevelDIED==false){
						//backwards character----------------------------------------------------------
						pane.getChildren().remove(character);
						Image image2=new Image(getClass().getResourceAsStream("characterbackwards.png"));
						character=new ImageView(image2);
						((ImageView) character).setFitHeight(90);
						((ImageView) character).setFitWidth(90);
						pane.getChildren().add(character);
						character.relocate(characterX, characterY);
						//------------------------------------------------------------------------------
						moveL=true;
					}
					if(e.getCode()==KeyCode.ESCAPE){
						System.out.println("Character is Trying to Escape D: \nGame Saved");
						
						try {
							PrintWriter out=new PrintWriter(new File(location));
							out.println(name);
							out.print(lives);
							out.close();
						} catch (FileNotFoundException e4) {
							System.out.println("Did not specify Location \n"+e4);
						}
					}
				//Release
				});
				btn.setOnKeyReleased(e->{
					if(e.getCode()==KeyCode.UP){
						if(characterY<=418 && characterY>=414 && bossLevelDIED==false){
							JumpUp=true;
						}
					}
					if(e.getCode()==KeyCode.RIGHT){
						moveR=false;
					}
					if(e.getCode()==KeyCode.LEFT){
						moveL=false;
					}
				});
				
			//JUMP*************************************************
				AnimationTimer characterJump=new AnimationTimer(){
					public void handle(long arg0) {
						if(JumpUp){
							characterY-=9;
							character.relocate(characterX,characterY);
						}
						if(characterY<230){
							JumpUp=false;
						}
						if(characterY<151){
							JumpUp=false;
						}
						if(hitfallingBlock1){
							JumpUp=false;
						}
						if(hitFallingWall){
							JumpUp=false;
						}
					}
				};
				characterJump.start();
			//MOVE CHARACTER ++++++++++++++++++++++++++++++++++++++
				AnimationTimer moveCharacter=new AnimationTimer(){
					public void handle(long arg0) {
					//Hit Things -------------------------------------------------------------------------------------------------------------------------------------------------------
					//block 1
						if(characterX>fallingBlock1X+145 && characterX<fallingBlock1X+380 && characterY>fallingBlock1Y-200 && characterY<fallingBlock1Y+120){
							hitfallingBlock1=true;
						}
						if(!(characterX>fallingBlock1X+145 && characterX<fallingBlock1X+380 && characterY>fallingBlock1Y-200 && characterY<fallingBlock1Y+120)){
							hitfallingBlock1=false;
						}
					//Block 2
						//Side
						if(characterX>fallingBlock2X+625 && characterX<fallingBlock2X+950 && characterY<fallingBlock2Y+450){
							System.out.println("HIT");
						}
					//Wall
						if(characterX>fallingWallX-71 && characterX<fallingWallX+1180 && characterY<fallingWallY+588){
							hitFallingWall=true;
						}
						if(!(characterX>fallingWallX-71 && characterX<fallingWallX+1180 && characterY<fallingWallY+588)){
							hitFallingWall=false;
						}
					//------------------------------------------------------------------------------------------------------------------------------------------------------------------
						
						
					//Move
						if(moveR && characterX<700 && hitfallingBlock1==false  && hitFallingWall==false|| moveR && background3X==0 && characterX<1130 && hitfallingBlock1==false && hitFallingWall==false){
							characterX+=3;
							character.setLayoutX(characterX);
						}
						if(moveL && characterX>-5 && hitfallingBlock1==false && hitFallingWall==false){
							characterX-=3;
							character.setLayoutX(characterX);
						}
					}
				};
				moveCharacter.start();
				
			//Move Backgrounds
				AnimationTimer moveBackground=new AnimationTimer(){
					public void handle(long arg0) {
					//FOWARD
						if(moveR && characterX>690 && background3X>0){
							background1X-=3;
							background1.setTranslateX(background1X);
							background2X-=3;
							background2.setTranslateX(background2X);
							background3X-=3;
							background3.setTranslateX(background3X);
							
							fallingBlock1X-=3;
							fallingBlock1.setTranslateX(fallingBlock1X);
							fallingBlock2X-=3;
							fallingBlock2.setTranslateX(fallingBlock2X);
							
							fallingWallX-=3;
							fallingWall.setTranslateX(fallingWallX);
						}
					//BACKWARDS
						if(moveL && characterX<1 && background1X<0){
							background1X+=3;
							background1.setTranslateX(background1X);
							background2X+=3;
							background2.setTranslateX(background2X);
							background3X+=3;
							background3.setTranslateX(background3X);
							
							fallingBlock1X+=3;
							fallingBlock1.setTranslateX(fallingBlock1X);
							fallingBlock2X+=3;
							fallingBlock2.setTranslateX(fallingBlock2X);

							fallingWallX+=3;
							fallingWall.setTranslateX(fallingWallX);
						}
					}
				};
				moveBackground.start();	
				
				/* 	  _            _   _     
				     | |          | | | |    
				   __| | ___  __ _| |_| |__  
				  / _` |/ _ \/ _` | __| '_ \ 
				 | (_| |  __/ (_| | |_| | | |
				  \__,_|\___|\__,_|\__|_| |_|
				                             
				*/
			//Death
				Timeline jmpCR2 = new Timeline(new KeyFrame(
				        Duration.millis(9),
				        ae -> JumpDoorRoom()));
				jmpCR2.setCycleCount(Animation.INDEFINITE);
				jmpCR2.play();
			//Falling Block 1
				Timeline timeBlock = new Timeline(new KeyFrame(
				        Duration.millis(2),
				        ae -> TimeBlocks()));
				timeBlock.setCycleCount(Animation.INDEFINITE);
				timeBlock.play();
				
				Timeline timeBlockUP = new Timeline(new KeyFrame(
				        Duration.millis(20),
				        ae -> TimeBlocksUP()));
				timeBlockUP.setCycleCount(Animation.INDEFINITE);
				timeBlockUP.play();
				
			//Falling Block 2
				Timeline timeBlock2 = new Timeline(new KeyFrame(
				        Duration.millis(1),
				        ae -> TimeBlocks2()));
				timeBlock2.setCycleCount(Animation.INDEFINITE);
				timeBlock2.play();
				
				Timeline timeBlockUP2 = new Timeline(new KeyFrame(
				        Duration.millis(10),
				        ae -> TimeBlocksUP2()));
				timeBlockUP2.setCycleCount(Animation.INDEFINITE);
				timeBlockUP2.play();
				
			//falling Wall
				Timeline timeWall = new Timeline(new KeyFrame(
				        Duration.millis(90),
				        ae -> TimeWall()));
				timeWall.setCycleCount(Animation.INDEFINITE);
				timeWall.play();
				Timeline timeWallUP = new Timeline(new KeyFrame(
				        Duration.millis(90),
				        ae -> TimeWallUP()));
				timeWallUP.setCycleCount(Animation.INDEFINITE);
				timeWallUP.play();
				AnimationTimer DeathTimer=new AnimationTimer(){
					public void handle(long arg0) {
						if(fallingBlock1X+205>characterX-150 && fallingBlock1X+205<characterX+54 && fallingBlock1Y>330 || characterX>fallingWallX-55 && characterX<fallingWallX+1175 && characterY<fallingWallY+564
								|| characterX>fallingBlock2X+625 && characterX<fallingBlock2X+950 && characterY<fallingBlock2Y+450){
							lives-=1;
							if(lives>=0){
								//lives-=1;
								if(lives==0){
									pane.getChildren().remove(permLife1);
								}
								if(lives==1){
									pane.getChildren().remove(permLife2);
								}
								if(lives==2){
									pane.getChildren().remove(permLife3);
								}
								System.out.println(lives);
								background1X=0;
								background2X=1200;
								background3X=2400;
								characterX=70;
								characterY=418;
								characterX=50;
								characterY=414;
								
								fallingBlock1X=200;
								fallingBlock1Y=0;
								fallingBlock2X=660;
								fallingBlock2Y=274;
								
								
								fbDOWN=false;
								fbUP=true;
								hitfallingBlock1=false;
								
								fallingWallX=1200;
								fallingWallY=-400;
								WUP=false;
								hitFallingWall=false;
								fb2UP=true;
								
								
								moveCharacter.stop();
								moveBackground.stop();
								characterJump.stop();
								timeBlock.stop();
								timeBlockUP.stop();
								//2
								timeBlock2.stop();
								timeBlockUP2.stop();
								timeWall.stop();
								timeWallUP.stop();
								jmpCR2.stop();
								(this).stop();
								moveCharacter.stop();
								moveBackground.stop();
								characterJump.stop();
								timeBlock.stop();
								timeBlockUP.stop();
								//2
								timeBlock2.stop();
								timeBlockUP2.stop();
								timeWall.stop();
								timeWallUP.stop();
								jmpCR2.stop();
								(this).stop();
								
				    			CleanUp g=new CleanUp();
				    			g.CleanUp();
				    			
				    			characterX=10;
				    			character.relocate(characterX,characterY);
				    			
				    			ER=false;
				    			tubeLife=true;
								pane.getScene().setRoot(BossLevel());
							}
							if(lives==-1){
								bossLevelDIED=true;
									
								pane.getChildren().remove(character);
								Image image=new Image(getClass().getResourceAsStream("dead.png"));
								DEADcharacter=new ImageView(image);
								((ImageView) DEADcharacter).setFitHeight(90);
								((ImageView) DEADcharacter).setFitWidth(90);
								((ImageView) DEADcharacter).setX(characterX);
								((ImageView) DEADcharacter).setY(418+40);
								pane.getChildren().add(DEADcharacter);
								
								Button retry=new Button("RETRY");
								retry.setFont(Font.font("Monospace",FontWeight.BOLD,40));
								retry.setStyle("-fx-background-color:transparent");
								retry.setLayoutX(570);
								retry.setLayoutY(280);
								retry.setOnMouseEntered(e->{
									retry.setTextFill(Color.GOLD);
								});
								retry.setOnMouseExited(e->{
									retry.setTextFill(Color.DIMGRAY);
								});
								Rectangle blackScreen=new Rectangle(250,100,650,320);
								blackScreen.setFill(Color.BLACK);
								Text lose=new Text("YOU LOSE");
								lose.setFill(Color.WHITE);
								lose.setFont(Font.font("Monospace",FontWeight.BOLD,55));
								lose.setX(430);
								lose.setY(210);
								Button quit=new Button("QUIT");
								quit.setFont(Font.font("Monospace",FontWeight.BOLD,40));
								quit.setStyle("-fx-background-color:transparent");
								quit.setLayoutX(370);
								quit.setLayoutY(280);
								quit.setOnMouseEntered(e2->{
									quit.setTextFill(Color.RED);
								});
								quit.setOnMouseExited(e2->{
									quit.setTextFill(Color.DIMGRAY);
								});
								quit.setOnAction(e->{
									moveCharacter.stop();
									moveBackground.stop();
									characterJump.stop();
									timeBlock.stop();
									timeBlockUP.stop();
									//2
									timeBlock2.stop();
									timeBlockUP2.stop();
									timeWall.stop();
									timeWallUP.stop();
									jmpCR2.stop();
									(this).stop();
									
					    			CleanUp g=new CleanUp();
					    			g.CleanUp();
					    			lives=1;
					    			ER=false;
					    			lives=1;
					    			tubeLife=true;
					    			pane.getChildren().removeAll(blackScreen,lose,quit,retry,DEADcharacter);
									pane.getScene().setRoot(menuScreen());
								});
								retry.setOnAction(e->{
									moveCharacter.stop();
									moveBackground.stop();
									characterJump.stop();
									timeBlock.stop();
									timeBlockUP.stop();
									//2
									timeBlock2.stop();
									timeBlockUP2.stop();
									timeWall.stop();
									timeWallUP.stop();
									jmpCR2.stop();
									(this).stop();
									
					    			CleanUp g=new CleanUp();
					    			g.CleanUp();
					    			lives=1;
					    			
					    			characterX=10;
					    			character.relocate(characterX,characterY);
					    			pane.getChildren().removeAll(blackScreen,lose,quit,retry,DEADcharacter);
					    			
					    			ER=false;
					    			tubeLife=true;
									pane.getScene().setRoot(LevelOne());
								});
			//========================================================================================================================================================    DIED
							pane.getChildren().addAll(blackScreen,lose,quit,retry);
							}
						}
						if(background3X==0){
							moveCharacter.stop();
							moveBackground.stop();
							characterJump.stop();
							timeBlock.stop();
							timeBlockUP.stop();
							//2
							timeBlock2.stop();
							timeBlockUP2.stop();
							timeWall.stop();
							timeWallUP.stop();
							jmpCR2.stop();
							(this).stop();
							pane.getScene().setRoot(finalBattle());
						}
					}
				};
				DeathTimer.start();
				
				
				
				return pane;
			}
//BOSS FIGHTS+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			public Pane finalBattle(){
				Pane pane=new Pane();
				
				characterX=50;
				characterY=458;
				moveRight=false;
				moveLeft=false;
				
				characterR=false;
				characterL=false;
				jump=false;
				fall=false;
				backgroundX=0;
				bowserX=-200;
				bowserY=410;
				bowser2X=-350;
				bowser2Y=410;
				bowser3X=-500;
				bowser3Y=410;
				bowser4X=-650;
				bowser4Y=410;
				bowser5X=1400;
				bowser5Y=410;
				bowser6X=1550;
				bowser6Y=410;
				bowser7X=1700;
				bowser7Y=410;
				bowser8X=1850;
				bowser8Y=410;
				String bossPath=game.class.getResource("doomsday.mp3").toString();
		        Media songg=new Media(bossPath);
		        MediaPlayer bossSong=new MediaPlayer(songg);
		        bossSong.play();
				
				hitBoss=false;
				
				kills=0;
				
				Timeline moveBowser = new Timeline(new KeyFrame(
				        Duration.millis(10),
				        ae -> moveB()));
				moveBowser.setCycleCount(Animation.INDEFINITE);
				moveBowser.play();
				
				Button btn=new Button();
				pane.getChildren().add(btn);
			//BACKGROUND
				Image image_background=new Image(getClass().getResourceAsStream("backgrounddd.png"));
				background=new ImageView(image_background);
				((ImageView)background).setFitWidth(3600);
				((ImageView)background).setFitHeight(600);
				((ImageView)background).setSmooth(true);
				((ImageView)background).setCache(true);
				pane.getChildren().add(background);
			//Character
				Image image_character=new Image(getClass().getResourceAsStream("characterrr.png"));
				character=new ImageView(image_character);
				((ImageView)character).setFitWidth(45);
				((ImageView)character).setFitHeight(50);
				((ImageView)character).setSmooth(true);
				((ImageView)character).setCache(true);
				pane.getChildren().add(character);
				character.setTranslateX(characterX);
				character.setTranslateY(characterY);	
			//Bowser 1
				Image image_bowser=new Image(getClass().getResourceAsStream("bowser.png"));
				bowser=new ImageView(image_bowser);
				((ImageView)bowser).setFitWidth(100);
				((ImageView)bowser).setFitHeight(100);
				((ImageView)bowser).setSmooth(true);
				((ImageView)bowser).setCache(true);
				pane.getChildren().add(bowser);
				bowser.setTranslateX(bowserX);
				bowser.setTranslateY(bowserY);
			//Bowser 2
				Image image_bowser2=new Image(getClass().getResourceAsStream("bowser.png"));
				bowser2=new ImageView(image_bowser2);
				((ImageView)bowser2).setFitWidth(100);
				((ImageView)bowser2).setFitHeight(100);
				((ImageView)bowser2).setSmooth(true);
				((ImageView)bowser2).setCache(true);
				pane.getChildren().add(bowser2);
				bowser2.setTranslateX(bowser2X);
				bowser2.setTranslateY(bowser2Y);
			//Bowser 3
				Image image_bowser3=new Image(getClass().getResourceAsStream("bowser.png"));
				bowser3=new ImageView(image_bowser3);
				((ImageView)bowser3).setFitWidth(100);
				((ImageView)bowser3).setFitHeight(100);
				((ImageView)bowser3).setSmooth(true);
				((ImageView)bowser3).setCache(true);
				pane.getChildren().add(bowser3);
				bowser3.setTranslateX(bowser3X);
				bowser3.setTranslateY(bowser3Y);
			//Bowser 4
				Image image_bowser4=new Image(getClass().getResourceAsStream("bowser.png"));
				bowser4=new ImageView(image_bowser4);
				((ImageView)bowser4).setFitWidth(100);
				((ImageView)bowser4).setFitHeight(100);
				((ImageView)bowser4).setSmooth(true);
				((ImageView)bowser4).setCache(true);
				pane.getChildren().add(bowser4);
				bowser4.setTranslateX(bowser4X);
				bowser4.setTranslateY(bowser4Y);
		//LEFT BOWSER
			//Bowser 5
				Image image_bowser5=new Image(getClass().getResourceAsStream("bowserL.png"));
				bowser5=new ImageView(image_bowser5);
				((ImageView)bowser5).setFitWidth(100);
				((ImageView)bowser5).setFitHeight(100);
				((ImageView)bowser5).setSmooth(true);
				((ImageView)bowser5).setCache(true);
				pane.getChildren().add(bowser5);
				bowser5.setTranslateX(bowser5X);
				bowser5.setTranslateY(bowser5Y);
			//Bowser 6
				Image image_bowser6=new Image(getClass().getResourceAsStream("bowserL.png"));
				bowser6=new ImageView(image_bowser6);
				((ImageView)bowser6).setFitWidth(100);
				((ImageView)bowser6).setFitHeight(100);
				((ImageView)bowser6).setSmooth(true);
				((ImageView)bowser6).setCache(true);
				pane.getChildren().add(bowser6);
				bowser6.setTranslateX(bowser6X);
				bowser6.setTranslateY(bowser6Y);
			//Bowser 7
				Image image_bowser7=new Image(getClass().getResourceAsStream("bowserL.png"));
				bowser7=new ImageView(image_bowser7);
				((ImageView)bowser7).setFitWidth(100);
				((ImageView)bowser7).setFitHeight(100);
				((ImageView)bowser7).setSmooth(true);
				((ImageView)bowser7).setCache(true);
				pane.getChildren().add(bowser7);
				bowser7.setTranslateX(bowser7X);
				bowser7.setTranslateY(bowser7Y);
			//Bowser 8
				Image image_bowser8=new Image(getClass().getResourceAsStream("bowserL.png"));
				bowser8=new ImageView(image_bowser8);
				((ImageView)bowser8).setFitWidth(100);
				((ImageView)bowser8).setFitHeight(100);
				((ImageView)bowser8).setSmooth(true);
				((ImageView)bowser8).setCache(true);
				pane.getChildren().add(bowser8);
				bowser8.setTranslateX(bowser8X);
				bowser8.setTranslateY(bowser8Y);
				
				Text characterName=new Text(name);
				characterName.setX(960);
				characterName.setY(35);
				characterName.setFont(Font.font("Monospace",FontWeight.BOLD,20));
				characterName.setStyle("-fx-background-color:transparent");
				characterName.setFill(Color.BLUEVIOLET);
				pane.getChildren().add(characterName);
				
				if(lives>=0){
					Image image_l1=new Image(getClass().getResourceAsStream("1up.png"));
					permLife1=new ImageView(image_l1);
					((ImageView)permLife1).setFitWidth(30);
					((ImageView)permLife1).setFitHeight(32);
					((ImageView)permLife1).setSmooth(true);
					((ImageView)permLife1).setCache(true);
					((ImageView)permLife1).setX(1150);
					((ImageView)permLife1).setY(20);
					pane.getChildren().add(permLife1);
				}
				if(lives>1){
					Image image_l2=new Image(getClass().getResourceAsStream("1up.png"));
					permLife2=new ImageView(image_l2);
					((ImageView)permLife2).setFitWidth(30);
					((ImageView)permLife2).setFitHeight(32);
					((ImageView)permLife2).setSmooth(true);
					((ImageView)permLife2).setCache(true);
					((ImageView)permLife2).setX(1108);
					((ImageView)permLife2).setY(20);
					pane.getChildren().add(permLife2);
				}
				if(lives>2){
					Image image_l3=new Image(getClass().getResourceAsStream("1up.png"));
					permLife3=new ImageView(image_l3);
					((ImageView)permLife3).setFitWidth(30);
					((ImageView)permLife3).setFitHeight(32);
					((ImageView)permLife3).setSmooth(true);
					((ImageView)permLife3).setCache(true);
					((ImageView)permLife3).setX(1066);
					((ImageView)permLife3).setY(20);
					pane.getChildren().add(permLife3);
				}
				
				//Press
				btn.setOnKeyPressed(e->{
					if(e.getCode()==KeyCode.RIGHT){
						moveRight=true;
						if(characterR==false){
							pane.getChildren().remove(character);
							Image image_characterR=new Image(getClass().getResourceAsStream("characterrr.png"));
							character=new ImageView(image_characterR);
							((ImageView)character).setFitWidth(45);
							((ImageView)character).setFitHeight(50);
							((ImageView)character).setSmooth(true);
							((ImageView)character).setCache(true);
							pane.getChildren().add(character);
							character.setTranslateX(characterX);
							character.setTranslateY(characterY);
							characterR=true;
							characterL=false;
						}
					}
					if(e.getCode()==KeyCode.LEFT){
						moveLeft=true;
						if(characterL==false){
							pane.getChildren().remove(character);
							Image image_characterL=new Image(getClass().getResourceAsStream("characterLL.png"));
							character=new ImageView(image_characterL);
							((ImageView)character).setFitWidth(45);
							((ImageView)character).setFitHeight(50);
							((ImageView)character).setSmooth(true);
							((ImageView)character).setCache(true);
							pane.getChildren().add(character);
							character.setTranslateX(characterX);
							character.setTranslateY(characterY);
							characterL=true;
							characterR=false;
						}
					}
					if(e.getCode()==KeyCode.UP && characterY==458){
						jump=true;
					}
					if(e.getCode()==KeyCode.ESCAPE){
						System.out.println("Character is Trying to Escape D: \n Game Saved");
						try {
							PrintWriter out=new PrintWriter(new File(location));
							out.println(name);
							out.print(lives);
							out.close();
						} catch (FileNotFoundException e4) {
							System.out.println("Did not specify Location \n"+e4);
						}
					}
				});
				//Release
				btn.setOnKeyReleased(e->{
					if(e.getCode()==KeyCode.RIGHT){
						moveRight=false;
					}
					if(e.getCode()==KeyCode.LEFT){
						moveLeft=false;
					}
				});
				AnimationTimer animation_move=new AnimationTimer(){
					public void handle(long arg0) {
						//Right move
						if(moveRight && characterX<1150){
							characterX+=3;
							character.setTranslateX(characterX);
						}
						//Left move
						if(moveLeft && characterX>2){
							characterX-=3;
							character.setTranslateX(characterX);
						}
						//Jump move
						if(jump && characterY>280){
							characterY-=8;
							character.setTranslateY(characterY);
							if(characterY==274){
								jump=false;
							}
						}
						if(jump==false && characterY<450){
							characterY+=8;
							character.setTranslateY(characterY);
						}
						if(jump==false && characterY<458){
							characterY+=1;
							character.setTranslateY(characterY);
						}
					//HIT
						if(character.getBoundsInParent().intersects(bowser.getBoundsInParent()) && characterY>400 || character.getBoundsInParent().intersects(bowser2.getBoundsInParent()) && characterY>400
								|| character.getBoundsInParent().intersects(bowser3.getBoundsInParent()) && characterY>400 || character.getBoundsInParent().intersects(bowser4.getBoundsInParent()) && characterY>400
								|| character.getBoundsInParent().intersects(bowser5.getBoundsInParent()) && characterY>400 || character.getBoundsInParent().intersects(bowser6.getBoundsInParent()) && characterY>400
								|| character.getBoundsInParent().intersects(bowser7.getBoundsInParent()) && characterY>400 || character.getBoundsInParent().intersects(bowser8.getBoundsInParent()) && characterY>400){
							lives-=1;
							System.out.println("HIT");
							if(lives==0){
								pane.getChildren().remove(permLife1);
								characterX=600;
								character.setTranslateX(characterX);
								characterY=0;
								character.setTranslateY(characterY);
							}
							if(lives==1){
								pane.getChildren().remove(permLife2);
								characterX=600;
								character.setTranslateX(characterX);
								characterY=0;
								character.setTranslateY(characterY);
							}
							if(lives==2){
								pane.getChildren().remove(permLife3);
								characterX=600;
								character.setTranslateX(characterX);
								characterY=0;
								character.setTranslateY(characterY);
							}
							if(lives==-1){
								(this).stop();
								moveBowser.stop();
								System.out.println("YOU LOSE");
								pane.getChildren().remove(character);
								Image image=new Image(getClass().getResourceAsStream("dead.png"));
								DEADcharacter=new ImageView(image);
								((ImageView) DEADcharacter).setFitHeight(90);
								((ImageView) DEADcharacter).setFitWidth(90);
								((ImageView) DEADcharacter).setX(characterX);
								((ImageView) DEADcharacter).setY(418+40);
								pane.getChildren().add(DEADcharacter);
								
								Button retry=new Button("RETRY");
								retry.setFont(Font.font("Monospace",FontWeight.BOLD,40));
								retry.setStyle("-fx-background-color:transparent");
								retry.setLayoutX(570);
								retry.setLayoutY(280);
								retry.setOnMouseEntered(e->{
									retry.setTextFill(Color.GOLD);
								});
								retry.setOnMouseExited(e->{
									retry.setTextFill(Color.DIMGRAY);
								});
								Rectangle blackScreen=new Rectangle(250,100,650,320);
								blackScreen.setFill(Color.BLACK);
								Text lose=new Text("YOU LOSE");
								lose.setFill(Color.WHITE);
								lose.setFont(Font.font("Monospace",FontWeight.BOLD,55));
								lose.setX(430);
								lose.setY(210);
								Button quit=new Button("QUIT");
								quit.setFont(Font.font("Monospace",FontWeight.BOLD,40));
								quit.setStyle("-fx-background-color:transparent");
								quit.setLayoutX(370);
								quit.setLayoutY(280);
								quit.setOnMouseEntered(e2->{
									quit.setTextFill(Color.RED);
								});
								quit.setOnMouseExited(e2->{
									quit.setTextFill(Color.DIMGRAY);
								});
								quit.setOnAction(e->{
							        bossSong.stop();
									(this).stop();
					    			CleanUp g=new CleanUp();
					    			g.CleanUp();
					    			lives=1;
					    			ER=false;
					    			lives=1;
					    			tubeLife=true;
					    			pane.getChildren().removeAll(blackScreen,lose,quit,retry,DEADcharacter);
									pane.getScene().setRoot(menuScreen());
								});
								retry.setOnAction(e->{
							        bossSong.stop();
									(this).stop();
					    			CleanUp g=new CleanUp();
					    			g.CleanUp();
					    			lives=1;
					    			characterX=10;
					    			character.relocate(characterX,characterY);
					    			pane.getChildren().removeAll(blackScreen,lose,quit,retry,DEADcharacter);
					    			ER=false;
					    			tubeLife=true;
									pane.getScene().setRoot(LevelOne());
								});
			//========================================================================================================================================================    DIED
							pane.getChildren().addAll(blackScreen,lose,quit,retry);
							}
						}
						if(character.getBoundsInParent().intersects(bowser.getBoundsInParent()) && characterY<400 || kills>50){
							double deadBossX=bowserX;
						//Dead Bowser
							Image image_Dbowser=new Image(getClass().getResourceAsStream("deadBowser.png"));
							ImageView deadBowser=new ImageView(image_Dbowser);
							deadBowser.setFitWidth(100);
							deadBowser.setFitHeight(100);
							deadBowser.setSmooth(true);
							deadBowser.setCache(true);
							pane.getChildren().add(deadBowser);
							deadBowser.setTranslateX(deadBossX);
							deadBowser.setTranslateY(bowserY+30);
							bowserX=-800;
							bowser.setTranslateX(bowserX);
							kills++;
						}
						if(character.getBoundsInParent().intersects(bowser2.getBoundsInParent()) && characterY<400 || kills>50){
							double deadBossX=bowser2X;
						//Dead Bowser
							Image image_Dbowser=new Image(getClass().getResourceAsStream("deadBowser.png"));
							ImageView deadBowser=new ImageView(image_Dbowser);
							deadBowser.setFitWidth(100);
							deadBowser.setFitHeight(100);
							deadBowser.setSmooth(true);
							deadBowser.setCache(true);
							pane.getChildren().add(deadBowser);
							deadBowser.setTranslateX(deadBossX);
							deadBowser.setTranslateY(bowserY+30);
							bowser2X=-600;
							bowser2.setTranslateX(bowser2X);
							kills++;
						}
						if(character.getBoundsInParent().intersects(bowser3.getBoundsInParent()) && characterY<400 || kills>50){
							double deadBossX=bowser3X;
						//Dead Bowser
							Image image_Dbowser=new Image(getClass().getResourceAsStream("deadBowser.png"));
							ImageView deadBowser=new ImageView(image_Dbowser);
							deadBowser.setFitWidth(100);
							deadBowser.setFitHeight(100);
							deadBowser.setSmooth(true);
							deadBowser.setCache(true);
							pane.getChildren().add(deadBowser);
							deadBowser.setTranslateX(deadBossX);
							deadBowser.setTranslateY(bowserY+30);
							bowser3X=-400;
							bowser3.setTranslateX(bowser3X);
							kills++;
						}
						if(character.getBoundsInParent().intersects(bowser4.getBoundsInParent()) && characterY<400 || kills>50){
							double deadBossX=bowser4X;
						//Dead Bowser
							Image image_Dbowser=new Image(getClass().getResourceAsStream("deadBowser.png"));
							ImageView deadBowser=new ImageView(image_Dbowser);
							deadBowser.setFitWidth(100);
							deadBowser.setFitHeight(100);
							deadBowser.setSmooth(true);
							deadBowser.setCache(true);
							pane.getChildren().add(deadBowser);
							deadBowser.setTranslateX(deadBossX);
							deadBowser.setTranslateY(bowserY+30);
							bowser4X=-200;
							bowser4.setTranslateX(bowser4X);
							kills++;
						}
					//Left
						if(character.getBoundsInParent().intersects(bowser5.getBoundsInParent()) && characterY<400 || kills>50){
							double deadBossX=bowser5X;
						//Dead Bowser
							Image image_Dbowser=new Image(getClass().getResourceAsStream("deadBowserL.png"));
							ImageView deadBowser=new ImageView(image_Dbowser);
							deadBowser.setFitWidth(100);
							deadBowser.setFitHeight(100);
							deadBowser.setSmooth(true);
							deadBowser.setCache(true);
							pane.getChildren().add(deadBowser);
							deadBowser.setTranslateX(deadBossX);
							deadBowser.setTranslateY(bowserY+30);
							bowser5X=2000;
							bowser5.setTranslateX(bowser5X);
							kills++;
						}
						if(character.getBoundsInParent().intersects(bowser6.getBoundsInParent()) && characterY<400 || kills>50){
							double deadBossX=bowser6X;
						//Dead Bowser
							Image image_Dbowser=new Image(getClass().getResourceAsStream("deadBowserL.png"));
							ImageView deadBowser=new ImageView(image_Dbowser);
							deadBowser.setFitWidth(100);
							deadBowser.setFitHeight(100);
							deadBowser.setSmooth(true);
							deadBowser.setCache(true);
							pane.getChildren().add(deadBowser);
							deadBowser.setTranslateX(deadBossX);
							deadBowser.setTranslateY(bowserY+30);
							bowser6X=1800;
							bowser6.setTranslateX(bowser6X);
							kills++;
						}
						if(character.getBoundsInParent().intersects(bowser7.getBoundsInParent()) && characterY<400 || kills>50){
							double deadBossX=bowser7X;
						//Dead Bowser
							Image image_Dbowser=new Image(getClass().getResourceAsStream("deadBowserL.png"));
							ImageView deadBowser=new ImageView(image_Dbowser);
							deadBowser.setFitWidth(100);
							deadBowser.setFitHeight(100);
							deadBowser.setSmooth(true);
							deadBowser.setCache(true);
							pane.getChildren().add(deadBowser);
							deadBowser.setTranslateX(deadBossX);
							deadBowser.setTranslateY(bowserY+30);
							bowser7X=1600;
							bowser7.setTranslateX(bowser7X);
							kills++;
						}
						if(character.getBoundsInParent().intersects(bowser8.getBoundsInParent()) && characterY<400 || kills>50){
							double deadBossX=bowser8X;
						//Dead Bowser
							Image image_Dbowser=new Image(getClass().getResourceAsStream("deadBowserL.png"));
							ImageView deadBowser=new ImageView(image_Dbowser);
							deadBowser.setFitWidth(100);
							deadBowser.setFitHeight(100);
							deadBowser.setSmooth(true);
							deadBowser.setCache(true);
							pane.getChildren().add(deadBowser);
							deadBowser.setTranslateX(deadBossX);
							deadBowser.setTranslateY(bowserY+30);
							bowser8X=1400;
							bowser8.setTranslateX(bowser8X);
							kills++;
						}
						if(bowserX>1900 && kills<50){
							bowserX=-200;
							bowser.setTranslateX(bowserX);
						}
						if(bowser2X>1900 && kills<50){
							bowser2X=-200;
							bowser2.setTranslateX(bowser2X);
						}
						if(bowser3X>1900 && kills<50){
							bowser3X=-200;
							bowser3.setTranslateX(bowser3X);
						}
						if(bowser4X>1900 && kills<50){
							bowser4X=-200;
							bowser4.setTranslateX(bowser4X);
						}
						//LEFT
						if(bowser5X<-400 && kills<50){
							bowser5X=1400;
							bowser5.setTranslateX(bowser5X);
							System.out.println("TEST");
						}
						if(bowser6X<-400 && kills<50){
							bowser6X=1400;
							bowser6.setTranslateX(bowser6X);
						}
						if(bowser7X<-400 && kills<50){
							bowser7X=1400;
							bowser7.setTranslateX(bowser7X);
						}
						if(bowser8X<-400 && kills<50){
							bowser8X=1400;
							bowser8.setTranslateX(bowser8X);
						}
						//WIN
						if(kills>50){
							(this).stop();
							moveBowser.stop();
							
							System.out.println("YOU WIN");
							
							Button retry=new Button("RETRY");
							retry.setFont(Font.font("Monospace",FontWeight.BOLD,40));
							retry.setStyle("-fx-background-color:transparent");
							retry.setLayoutX(570);
							retry.setLayoutY(280);
							retry.setOnMouseEntered(e->{
								retry.setTextFill(Color.GOLD);
							});
							retry.setOnMouseExited(e->{
								retry.setTextFill(Color.DIMGRAY);
							});
							Rectangle blackScreen=new Rectangle(250,100,650,320);
							blackScreen.setFill(Color.BLACK);
							Text lose=new Text("YOU WIN");
							lose.setFill(Color.ALICEBLUE);
							lose.setFont(Font.font("Monospace",FontWeight.BOLD,55));
							lose.setX(430);
							lose.setY(210);
							Button quit=new Button("QUIT");
							quit.setFont(Font.font("Monospace",FontWeight.BOLD,40));
							quit.setStyle("-fx-background-color:transparent");
							quit.setLayoutX(370);
							quit.setLayoutY(280);
							quit.setOnMouseEntered(e2->{
								quit.setTextFill(Color.RED);
							});
							quit.setOnMouseExited(e2->{
								quit.setTextFill(Color.DIMGRAY);
							});
							quit.setOnAction(e->{
						        bossSong.stop();
				    			CleanUp g=new CleanUp();
				    			g.CleanUp();
				    			lives=1;
				    			ER=false;
				    			lives=1;
				    			tubeLife=true;
				    			pane.getChildren().removeAll(blackScreen,lose,quit,retry);
								pane.getScene().setRoot(menuScreen());
							});
							retry.setOnAction(e->{
						        bossSong.stop();
				    			CleanUp g=new CleanUp();
				    			g.CleanUp();
				    			lives=1;
				    			
				    			characterX=10;
				    			character.relocate(characterX,characterY);
				    			pane.getChildren().removeAll(blackScreen,lose,quit,retry,DEADcharacter);
				    			
				    			ER=false;
				    			tubeLife=true;
								pane.getScene().setRoot(LevelOne());
							});
		//========================================================================================================================================================    DIED
						pane.getChildren().addAll(blackScreen,lose,quit,retry);
						}
					}
				};
				animation_move.start();
				
				
				
				return pane;
			}
			
			
			
			
			
	/*
		============================================================= 
		  _____      _            _        __      __   _     _     
		 |  __ \    (_)          | |       \ \    / /  (_)   | |    
		 | |__) | __ ___   ____ _| |_ ___   \ \  / /__  _  __| |___ 
		 |  ___/ '__| \ \ / / _` | __/ _ \   \ \/ / _ \| |/ _` / __|
		 | |   | |  | |\ V / (_| | ||  __/    \  / (_) | | (_| \__ \
		 |_|   |_|  |_| \_/ \__,_|\__\___|     \/ \___/|_|\__,_|___/
		                                                            
		=============================================================                                                   
	*/
	public void Jump(){
		if(characterY<floor && JumpUp==false && tubeHIT==false){
			characterY+=4;
		    character.relocate(characterX,characterY);
		}
		if(characterY>=412 && characterY<418 && JumpUp==false && tubeHIT==false){
			characterY+=1;
		    character.relocate(characterX,characterY);
		}
		if(fall && characterY<1000 && tubeHIT==false){
			characterY+=1;
		    character.relocate(characterX,characterY);
		}
		if(characterY>419 && characterY<700 && tubeHIT==false){
			characterY+=4;
		    character.relocate(characterX, characterY);
		}
		if(tubeHIT && characterY<366 && JumpUp==false){
			characterY+=4;
		    character.relocate(characterX,characterY);
		}
	}
	public void JumpCoinRoom(){
		if(characterY<414 && JumpUp==false && tubeROOM && characterX<967){
	  		characterY+=4;
	   		character.relocate(characterX,characterY);
    	}
		if(characterY>=414 && characterY<418 && JumpUp==false && tubeROOM && characterX<967){
			characterY+=1;
			character.relocate(characterX,characterY);
		}
	//If on Tube
		if(characterY<414 && JumpUp==false && tubeROOM && OnCRTube && characterY<335){
			characterY+=4;
			character.relocate(characterX,characterY);
		}
		if(characterY>=414 && characterY<418 && JumpUp==false && tubeROOM && OnCRTube && characterY<335){
			characterY+=1;
			character.relocate(characterX,characterY);
		}		 
	}
	public void JumpDoorRoom(){
		if(characterY<414 && JumpUp==false){
			characterY+=4;
		    character.relocate(characterX,characterY);
		}
		if(characterY>=414 && characterY<418 && JumpUp==false && characterX<967){
			characterY+=1;
		    character.relocate(characterX,characterY);
		}
	}
	public void moveSkels(){
		if(deadSkel2==false){
			skel2X-=1;
			skel2.setTranslateX(skel2X);
		}
		if(deadSkel1==false){
			skel1X+=1;
			skel1.setTranslateX(skel1X);
		}
	}
//B1
	public void TimeBlocks(){
		if(fallingBlock1Y<360 && fbUP){
			fallingBlock1Y+=1;
			fallingBlock1.setTranslateY(fallingBlock1Y);
			if(fallingBlock1Y==360){
				fbUP=false;
			}
		}
	}
	public void TimeBlocksUP(){
		if(fbUP==false && fallingBlock1Y>=0){
			fallingBlock1Y-=1;
			fallingBlock1.setTranslateY(fallingBlock1Y);
			if(fallingBlock1Y==0){
				fbUP=true;
			}
		}
	}
//B2
	public void TimeBlocks2(){
		if(fallingBlock2Y<0 && fb2UP==false){
			fallingBlock2Y+=1;
			fallingBlock2.setTranslateY(fallingBlock2Y);
			if(fallingBlock2Y==0){
				fb2UP=true;
			}
		}
	}
	public void TimeBlocksUP2(){
		if(fb2UP==true && fallingBlock2Y>=-274){
			fallingBlock2Y-=1;
			fallingBlock2.setTranslateY(fallingBlock2Y);
			if(fallingBlock2Y==-274){
				fb2UP=false;
			}
		}
	}
//F WALL
	public void TimeWall(){
		if(WUP==false && fallingWallY<-100){
			fallingWallY+=1;
			fallingWall.setTranslateY(fallingWallY);
			if(fallingWallY==-100){
				WUP=true;
			}
		}
	}
	public void TimeWallUP(){
		if(WUP==true && fallingWallY>=-250){
			fallingWallY-=1;
			fallingWall.setTranslateY(fallingWallY);
			if(fallingWallY==-250){
				WUP=false;
			}
		}
	}
	public void moveB(){
		if(kills<50){
			bowserX+=.5;
			bowser.setTranslateX(bowserX);
			bowser2X+=.4;
			bowser2.setTranslateX(bowser2X);
			bowser3X+=.3;
			bowser3.setTranslateX(bowser3X);
			bowser4X+=.5;
			bowser4.setTranslateX(bowser4X);
			
			//LEFT
			bowser5X-=.7;
			bowser5.setTranslateX(bowser5X);
			bowser6X-=.7;
			bowser6.setTranslateX(bowser6X);
			bowser7X-=.6;
			bowser7.setTranslateX(bowser7X);
			bowser8X-=.8;
			bowser8.setTranslateX(bowser8X);
		}
	}
	/*
	========================================================================
	  ______ _   _ _____      ____  ______    _____          __  __ ______ 
	 |  ____| \ | |  __ \    / __ \|  ____|  / ____|   /\   |  \/  |  ____|
	 | |__  |  \| | |  | |  | |  | | |__    | |  __   /  \  | \  / | |__   
	 |  __| | . ` | |  | |  | |  | |  __|   | | |_ | / /\ \ | |\/| |  __|  
	 | |____| |\  | |__| |  | |__| | |      | |__| |/ ____ \| |  | | |____ 
	 |______|_| \_|_____/    \____/|_|       \_____/_/    \_\_|  |_|______|
	                                                                       
	=========================================================================                                                
*/
	
	public static void main(String[] args){
		launch(args);
		
		//CleanUp clean=new CleanUp();
		//clean.CleanUp();
	}
	public class CleanUp{
		public void CleanUp(){
		//CHARACTER
			characterX=10;
			characterY=418;
		//BACKGROUND
			atEND=false;
			background1X=0;
			background2X=1195;
			background3X=2390;
		//CONTROLS
			JumpUp=false;
			JumpDown=false;
			moveR=false;
			moveL=false;
			PauseControls=false;
		//MISCHELEANOUS
			holeX=2840;
			floor=412;
			fall=false;
			TubeX=923;
			tubeHIT=false;
			block1X=710;
			blockHIT=false;
		//LifeRoom
			tubeDOWN=false;
			tubeROOM=false;
			tubeDOWNlevel=369;
			tubePIC=false;
		//Lives
			live1STOP=false;
			live2STOP=false;
			lifee1=false;
			life1RESET=false;
			life1X=715;
			life1Y=305;
			mDOWN=false;
			RecievedfirstLife=false;
		//Coin Room
			levelOneEND=false;
			OnCRTube=false;
			EnterCRTube=false;
			STOPCRtube=false;
			ResetCR=false;
		//Skellys
			deadSkel1X=0;
			deadSkel2X=0;
			skel1X=1100;
			skel2X=900;
			skelY=424;
			deadSkel1=false;
			deadSkel2=false;
		//Boss
			toBOSS=false;
			
			characterR=false;
			characterL=false;
			jump=false;
			fall=false;
			backgroundX=0;
			bowserX=-200;
			bowserY=410;
			bowser2X=-350;
			bowser2Y=410;
			bowser3X=-500;
			bowser3Y=410;
			bowser4X=-650;
			bowser4Y=410;
			bowser5X=1400;
			bowser5Y=410;
			bowser6X=1550;
			bowser6Y=410;
			bowser7X=1700;
			bowser7Y=410;
			bowser8X=1850;
			bowser8Y=410;
		}
	}

public class gameControls{
//Right
	public void CONTROLright(double a) {
		if(PauseControls==false){
			characterX=a+3;
		}
	}
//Left
	public void CONTROLleft(double a){
		if(PauseControls==false){
			characterX=a-3;
		}
	}
	
}

public class moveBackgrounds extends game{
//Right
	public void CONTROLright(){
		if(PauseControls==false){
		//B1
			background1X-=3;
			background1.setLayoutX(background1X);
		//B2
			background2X-=3;
			background2.setLayoutX(background2X);
		//B3
			background3X-=3;
			background3.setLayoutX(background3X);
		//HOLE
			holeX-=3;
		//TUBE
			TubeX-=3;
			
		//BLOCK 1
			block1X-=3;
			BLOCK1.setLayoutX(block1X);
		//1UP
			life1X-=3;
			life1.relocate(life1X, life1Y);
		//SKEL 1
			skel1X-=3;
			skel1.setTranslateX(skel1X);
			if(deadSkel1){
				deadSkel1X-=3;
				deadskel1.setTranslateX(deadSkel1X);
			}
		//SKEL 2
			skel2X-=3;
			skel2.setTranslateX(skel2X);
			if(deadSkel2){
				deadSkel2X-=3;
				deadskel2.setTranslateX(deadSkel2X);
			}
		}
	}
	
	
//Left
	public void CONTROLleft(){
		if(PauseControls==false){
		//B1
			background1X+=3;
			background1.setLayoutX(background1X);
		//B2
			background2X+=3;
			background2.setLayoutX(background2X);
		//B3
			background3X+=3;
			background3.setLayoutX(background3X);
		//HOLE
			holeX+=3;
		//TUBE
			TubeX+=3;
		//BLOCK 1
			block1X+=3;
			BLOCK1.setLayoutX(block1X);
		//1UP
			life1X+=3;
			life1.relocate(life1X, life1Y);
			//SKEL 1
			skel1X+=3;
			skel1.setTranslateX(skel1X);
			if(deadSkel1){
				deadSkel1X+=3;
				deadskel1.setTranslateX(deadSkel1X);
			}
		//SKEL 2
			skel2X+=3;
			skel2.setTranslateX(skel2X);
			if(deadSkel2){
				deadSkel2X+=3;
				deadskel2.setTranslateX(deadSkel2X);
			}
		}
		
	}
	
}


	
}