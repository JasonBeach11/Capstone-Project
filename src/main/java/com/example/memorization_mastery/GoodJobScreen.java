package com.example.memorization_mastery;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.*;

/**
 * Good Job Class contains all UI and associated logic for Good Job screen
 */
public class GoodJobScreen implements Screen {
	// Good Job Screen class-accessible variables
	static GoodJobScreen instance;
	Stage primaryStage;
	Label currentUserLabel = new Label();
	Label currentUserBadgesLabel = new Label();

	// Load badge icon asset
	Image badgeIcon = new Image("blackBadge.png");
				
	/**
	 * MemorizeScreen constructor. If more than one screen is created, an error will be output to the console.
	 **/
	public GoodJobScreen(Stage primaryStage) {
		if(instance == null) {
			this.primaryStage = primaryStage;
			instance = this;
		}
		else {
			System.out.println("ERROR: Instance of GoodJobScreen already exists.");
		}
	}

	/**
	 * Create Good Job Screen UI and associated logic
	 */
	@Override
	public void InitializeScreen() {
		System.out.print("\nHello GoodJobScreen");

		// Import badge icon
		ImageView badgeImageView = new ImageView(badgeIcon);
		badgeImageView.setFitWidth(20); // Set the desired width
		badgeImageView.setPreserveRatio(true);
		badgeImageView.setSmooth(true);
		badgeImageView.setCache(true);

		try {
			// Query Database to get current number of badges for current user
			int badges = DatabaseConnection.getNumberOfBadgesForUser(Controller.currentUser);
			System.out.println("Number of Badges: " + badges);

			// Set Label for number of badges
			currentUserBadgesLabel.setText(String.valueOf(badges));
			currentUserBadgesLabel.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-size: 18px; -fx-text-fill: #FF69B4;");

			// Set current user label
			currentUserLabel.setText(Controller.currentUser + ":");
			currentUserLabel.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-size: 18px; -fx-text-fill: #FF69B4;");

			//Create an HBox to hold current user and badges side by side
			HBox userBox = new HBox(10);
			userBox.setAlignment(Pos.CENTER_RIGHT);
			userBox.getChildren().addAll(currentUserLabel, badgeImageView, currentUserBadgesLabel);

			// "Good Job!" label
			Label goodJob = new Label();
			goodJob.setText("Good Job!");
			goodJob.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-size: 34px; -fx-text-fill: rgb(70, 191, 238);");

			// "You're a Master!" label
			Label youreAMaster = new Label();
			youreAMaster.setText("You're a Memorization Master!");
			youreAMaster.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-size: 40px; -fx-text-fill: rgb(70, 191, 238);");

			// "You've earned a badge!" label
			Label reward = new Label();
			reward.setText("You earned a badge!");
			reward.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-size: 24px; -fx-text-fill: #FF69B4;");

			// Create a Close Button and stylize
			Button closeButton = new Button("Close Application");
			closeButton.setOnMouseEntered(e -> {closeButton.setStyle("-fx-border-color: #FF69B4; -fx-border-width: 2px; "
					+ "-fx-font-family: 'Comic Sans MS'; -fx-font-size: 16px; -fx-text-fill: black; -fx-background-color: #FF69B4;");
			});
			closeButton.setOnMouseExited(e -> {closeButton.setStyle("-fx-border-color: #FF69B4; -fx-border-width: 2px; "
					+ "-fx-font-family: 'Comic Sans MS'; -fx-font-size: 16px; -fx-text-fill: lightgray; -fx-background-color: black;");
			});
			closeButton.setStyle("-fx-border-color: #FF69B4; -fx-border-width: 2px; "
					+ "-fx-font-family: 'Comic Sans MS'; -fx-font-size: 16px; -fx-text-fill: lightgray; -fx-background-color: black");
			// Handle Close button clicked
			closeButton.setOnAction(e -> handleCloseButtonClick());

			// Create Lets Go Again Button and stylize (Navigates back to menu screen)
			Button letsGoAgain = new Button("Let's Go Again");
			letsGoAgain.setOnMouseEntered(e -> {letsGoAgain.setStyle("-fx-border-color: #FF69B4; -fx-border-width: 2px; "
					+ "-fx-font-family: 'Comic Sans MS'; -fx-font-size: 16px; -fx-text-fill: black; -fx-background-color: #FF69B4;");
			});
			letsGoAgain.setOnMouseExited(e -> {letsGoAgain.setStyle("-fx-border-color: #FF69B4; -fx-border-width: 2px; "
					+ "-fx-font-family: 'Comic Sans MS'; -fx-font-size: 16px; -fx-text-fill: lightgray; -fx-background-color: black;");
			});
			letsGoAgain.setStyle("-fx-border-color: #FF69B4; -fx-border-width: 2px; "
					+ "-fx-font-family: 'Comic Sans MS'; -fx-font-size: 16px; -fx-text-fill: lightgray; -fx-background-color: black");
			// Handle memorize button clicked
			letsGoAgain.setOnAction(e -> handleLetsGoAgainButtonClick());

			// Create HBox to hold the buttons side by side
			HBox buttonBox = new HBox(20);
			buttonBox.getChildren().addAll(closeButton, letsGoAgain);
			buttonBox.setAlignment(Pos.CENTER);

			// VBox to hold all page elements
			VBox pageElementsBox = new VBox();
			pageElementsBox.getChildren().addAll(userBox, goodJob, youreAMaster, reward, buttonBox);
			pageElementsBox.setAlignment(Pos.TOP_CENTER);
			pageElementsBox.setMaxWidth(600);
			VBox.setMargin(userBox, new Insets(10,0,0,200));
			VBox.setMargin(goodJob, new Insets(300,0,0,0));
			VBox.setMargin(buttonBox, new Insets(75,0,0,0));

			// Border Pane as root to hold all
			BorderPane root = new BorderPane();
			BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, null, null);
			Background background = new Background(backgroundFill);
			root.setBackground(background);
			root.setCenter(pageElementsBox);

			Scene scene = new Scene(root,800,800);
			primaryStage.setTitle("Good Job!");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * On Close Button click, close the app
	 */
	private void handleCloseButtonClick(){
		Platform.exit();
	}

	/**
	 * On Let's Go Again Button click, navigate back to menu screen
	 */
	private void handleLetsGoAgainButtonClick(){
		Controller.activateScreen(Controller.menuScreen);
	}

	@Override
	public void DestroyScreen() {
	}
}
