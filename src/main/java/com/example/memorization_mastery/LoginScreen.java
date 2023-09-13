package com.example.memorization_mastery;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Login Screen Class contains all UI elements and associated logic for the Login Screen
 */
public class LoginScreen implements Screen {
	// Login Screen class accessible variables
	static LoginScreen instance;
	Stage primaryStage;
	Label loginLabel = new Label();
	Label loginStatus = new Label();
		
	/**
	 * Login Screen constructor. If more than one Login Screen is created, an error will be output to the console.
	 */
	public LoginScreen(Stage primaryStage) {
		if(instance == null) {
			this.primaryStage = primaryStage;
			instance = this;
		}
		else {
			System.out.println("ERROR: Instance of Login Screen already exists.");
		}
	}
	
	/**
	 * Create Login Screen UI and associated logic
	 */
	@Override
	public void InitializeScreen() {
		System.out.print("\nHello LoginScreen");
		loginStatus.setText(null);

		try {

			
	        loginLabel.setText("Enter Your Username");
	        loginLabel.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-size: 36px; -fx-text-fill: rgb(70, 191, 238);");
	        loginStatus.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-size: 12px; -fx-text-fill: lightgray;");
	        
			// Create TextField for entering the username
	        TextField usernameField = new TextField();
	        usernameField.setPromptText("Username");
	        usernameField.setMaxWidth(200);
	        usernameField.setStyle("-fx-border-color: #FF69B4; -fx-border-width: 2px; "
                    + "-fx-font-family: 'Comic Sans MS'; -fx-font-size: 16px; -fx-text-fill: black;");

	        // Create a Back Button
	        Button backButton = new Button("Back");
	        // Add a CSS style for the hover effect
	     	backButton.setOnMouseEntered(e -> {backButton.setStyle("-fx-border-color: #FF69B4; -fx-border-width: 2px; "
	     			+ "-fx-font-family: 'Comic Sans MS'; -fx-font-size: 16px; -fx-text-fill: black; -fx-background-color: #FF69B4;");
	     	});
	     	backButton.setOnMouseExited(e -> {backButton.setStyle("-fx-border-color: #FF69B4; -fx-border-width: 2px; "
	     			+ "-fx-font-family: 'Comic Sans MS'; -fx-font-size: 16px; -fx-text-fill: lightgray; -fx-background-color: black;");
	     	});
	        backButton.setStyle("-fx-border-color: #FF69B4; -fx-border-width: 2px; "
                    + "-fx-font-family: 'Comic Sans MS'; -fx-font-size: 16px; -fx-text-fill: lightgray; -fx-background-color: black");
	        // Handle back button clicked
	        backButton.setOnAction(e -> handleBackButtonClick());
	        
	        // Create a Login Button
	        Button loginButton = new Button("Login");
	        // Add a CSS style for the hover effect
	     	loginButton.setOnMouseEntered(e -> {loginButton.setStyle("-fx-border-color: #FF69B4; -fx-border-width: 2px; "
	     			+ "-fx-font-family: 'Comic Sans MS'; -fx-font-size: 16px; -fx-text-fill: black; -fx-background-color: #FF69B4;");
	     	});
	     	loginButton.setOnMouseExited(e -> {loginButton.setStyle("-fx-border-color: #FF69B4; -fx-border-width: 2px; "
	     			+ "-fx-font-family: 'Comic Sans MS'; -fx-font-size: 16px; -fx-text-fill: lightgray; -fx-background-color: black;");
	     	});
	        loginButton.setStyle("-fx-border-color: #FF69B4; -fx-border-width: 2px; "
                    + "-fx-font-family: 'Comic Sans MS'; -fx-font-size: 16px; -fx-text-fill: lightgray; -fx-background-color: black");
	        // Handle loginButton clicked
	        loginButton.setOnAction(e -> handleLogin(usernameField.getText()));
			
	        // Create an HBox to hold both buttons side by side
	        HBox buttonBox = new HBox(10);
	        buttonBox.setAlignment(Pos.CENTER);
	        buttonBox.getChildren().addAll(backButton, loginButton);
	        
	        // Create a VBox to hold the login components vertically
	        VBox loginBox = new VBox(10);
	        loginBox.setAlignment(Pos.CENTER);
	        loginBox.getChildren().addAll(loginLabel, usernameField, buttonBox, loginStatus);

			// Root pane to hold all screen elements
			BorderPane root = new BorderPane();
			BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, null, null);
			Background background = new Background(backgroundFill);
			root.setBackground(background);

	        root.setCenter(loginBox);
			
			Scene scene = new Scene(root,800,800);
			primaryStage.setTitle("Login");
			primaryStage.setScene(scene);
			primaryStage.show();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * If back button is clicked, go back to Welcome Screen
	 */
	private void handleBackButtonClick() {
		Controller.activateScreen(Controller.welcomeScreen);
	}

	/**
     * If Login button is clicked, check for matching username and update login status.
     * If login is successful progress to MenuScreen
     */
    private void handleLogin(String username) {
        System.out.println("Login clicked with username: " + username);

        // Check if the username exists in the database using your DatabaseConnection class
        boolean userExists = DatabaseConnection.checkUsernameExists(username);
        if (userExists) {
        	System.out.println("Username pulled from database: " + username);
        	Controller.currentUser = username;
            loginStatus.setText("Login Success!");
            loginStatus.setVisible(true);
            Controller.activateScreen(Controller.menuScreen);
        }
		else {
            System.out.println("Username: " + username + " not found.");
            loginStatus.setText("Username Invalid");
            loginStatus.setVisible(true);
        }
    }
	@Override
	public void DestroyScreen() {
	}
}
