package com.example.memorization_mastery;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class WelcomeScreen implements Screen {
	// Welcome Screen variables
	static WelcomeScreen instance;
	Stage primaryStage;
	
	// Load App logo
    Image backgroundImage = new Image("MemorizationMasteryLogo.png");
	
	/**
	 * Welcome Screen constructor. If more than one screen is created, an error will be output to the console.
	 */
	public WelcomeScreen(Stage primaryStage) {
		if(instance == null) {
			this.primaryStage = primaryStage;
			instance = this;
		}
		else {
			System.out.println("ERROR: Instance of Welcome Screen already exists.");
		}
	}
	
	/**
	 * Create Welcome Screen UI elements and controlling logic
	 */
	@Override
	public void InitializeScreen() {
		System.out.print("\nHello WelcomeScreen");
		try {
			// Create Start Button
			Button startButton = new Button("START");
			startButton.setStyle("-fx-border-color: #FF69B4; -fx-border-width: 2px; "
                    + "-fx-font-family: 'Comic Sans MS'; -fx-font-size: 16px; -fx-text-fill: lightgray; -fx-background-color: black");
			// Add a CSS style for the hover effect
			startButton.setOnMouseEntered(e -> {
			    startButton.setStyle("-fx-border-color: #FF69B4; -fx-border-width: 2px; "
			        + "-fx-font-family: 'Comic Sans MS'; -fx-font-size: 16px; -fx-text-fill: black; -fx-background-color: #FF69B4;");
			});
			startButton.setOnMouseExited(e -> {
			    startButton.setStyle("-fx-border-color: #FF69B4; -fx-border-width: 2px; "
			        + "-fx-font-family: 'Comic Sans MS'; -fx-font-size: 16px; -fx-text-fill: lightgray; -fx-background-color: black;");
			});
			startButton.setOnAction(e ->handleStartButtonClick());

			// Grid Pane to hold Start button
			GridPane buttonPane = new GridPane();
			buttonPane.add(startButton, 0, 2);
			buttonPane.setAlignment(Pos.BOTTOM_CENTER);
			buttonPane.setPadding(new Insets(0, 0, 120, 0));
			
			// Put Image into welcome screen background
			ImageView imageView = new ImageView(backgroundImage);
			imageView.fitWidthProperty().bind(primaryStage.widthProperty());
	        imageView.fitHeightProperty().bind(primaryStage.heightProperty());
	        
	        // Set the background of the layout container to the ImageView and format
	        BackgroundImage background = new BackgroundImage(
	                backgroundImage,
	                BackgroundRepeat.NO_REPEAT,
	                BackgroundRepeat.NO_REPEAT,
	                BackgroundPosition.DEFAULT,
	                new BackgroundSize(
	                        primaryStage.getWidth(),
	                        primaryStage.getHeight(),
	                        false,
	                        false,
	                        true,
	                        true
	                )
	        );

			// Border pane as root to hold all page elements
			BorderPane root = new BorderPane();
			root.setCenter(buttonPane);
			root.setBackground(new Background(background));
			
			Scene scene = new Scene(root,800,800);
			primaryStage.setTitle("Welcome");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Event handler for Start Button. When button is clicked, navigate to Login Screen
	 */
    private void handleStartButtonClick() {
        // Implement your back button logic here
        System.out.println("Start Button Clicked...");
        Controller.activateScreen(Controller.loginScreen);
    }
	@Override
	public void DestroyScreen() {
	}
}
