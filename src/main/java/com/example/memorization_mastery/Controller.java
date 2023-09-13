package com.example.memorization_mastery;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *<h1>Memorization Mastery</h1>
 *
 * <p>This is a gamified memorization app that helps users memorize famous works of literature including poems, scriptures
 * historical text, quotes, etc. Users can select a mastery level and the text is displayed with a percentage of words redacted
 * based on the mastery level selected. Users can continue to generate a new random set of words to be redacted and increase
 * their mastery level. Users earn a badge for each work they have memorized.
 * All text works are stored in a PostgreSQL database being hosted via localhost.
 * </p>
 *
 * <p>Competed: 9/12/2023</p>
 *
 * @author Jason Beach
 * @Version 1.0.0
 */

/**
 * Controller Class is the controller for the application. All other classes communicate directly with the Controller
 * and the Controller communicates with each class.
 */
public class Controller extends Application{
	// Variables the Controller will maintain control of and share with each class as needed
	public static WelcomeScreen welcomeScreen;
	public static LoginScreen loginScreen;
	public static MenuScreen menuScreen;
	public static MemorizeScreen memorizeScreen;
	public static GoodJobScreen goodJobScreen;
	public static Screen activeScreen;
	public static String currentUser;
	public static int currentUserBadges;
	public static Writing selectedWriting;

	/**
     * start method for the application.
     */
	@Override
	public void start(Stage mainStage) throws Exception {
		welcomeScreen = new WelcomeScreen(mainStage);
		loginScreen = new LoginScreen(mainStage);
		menuScreen = new MenuScreen(mainStage);
		memorizeScreen = new MemorizeScreen(mainStage);
		goodJobScreen = new GoodJobScreen(mainStage);
		
		// Set active screen to Welcome Screen and activate
		activeScreen = welcomeScreen;
		activateScreen(activeScreen);
	}

	/**
	 * Activates the screen sent to the method
	 */
	public static void activateScreen(Screen to) {
		activeScreen.DestroyScreen();
		to.InitializeScreen();
	}

	/**
     * This method is only needed for IDEs to launch the application
     */
	public static void main(String[] args) {
		launch(args);
	}
}