package com.example.memorization_mastery;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class MemorizeScreen implements Screen {
	// MemorizationScreen class-accessible variables
	static MemorizeScreen instance;
	Stage primaryStage;
	ToggleGroup toggleGroup = new ToggleGroup();
	Label textToBeMemorized = new Label();
	
	/**
	 * MemorizeScreen constructor. If more than one screen is created, an error will be output to the console.
	 */
	public MemorizeScreen(Stage primaryStage) {
		if(instance == null) {
			this.primaryStage = primaryStage;
			instance = this;
		}
		else {
			System.out.println("ERROR: Instance of Memorize Screen already exists.");
		}
	}

	/**
	 *  Create Memorization Screen UI and associated logic
	 */
	@Override
	public void InitializeScreen() {
		System.out.print("\nHello MemorizeScreen");
		try {
			// Query Database to get the text to be memorized
			String memorize = Controller.selectedWriting.getText();
	        
			// Border pane to hold text to be memorized
			BorderPane memorizePane = new BorderPane();
			memorizePane.setStyle("-fx-border-color: rgb(70, 191, 238); -fx-border-width: 2px;");

			// Set the minimum and preferred size for the Border Pane
			memorizePane.setMinWidth(400);
			memorizePane.setMaxWidth(400);
			memorizePane.setMinHeight(400);
			memorizePane.setMaxHeight(600);

			// Create a Label to display text to be memorized
			textToBeMemorized.setText(memorize);
	        textToBeMemorized.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-size: 14px; -fx-text-fill: lightgray; -fx-padding: 10px; -fx-alignment: center-left;");
			textToBeMemorized.setWrapText(true);

			// Add text to border pane
			memorizePane.setCenter(textToBeMemorized);

	        // Create a Back Button and style
	        Button backButton = new Button("Back");
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
	        
	        // Create a Login Button and style
	        Button regenerateButton = new Button("Regenerate Text");
	        regenerateButton.setOnMouseEntered(e -> {regenerateButton.setStyle("-fx-border-color: #FF69B4; -fx-border-width: 2px; "
	     			+ "-fx-font-family: 'Comic Sans MS'; -fx-font-size: 16px; -fx-text-fill: black; -fx-background-color: #FF69B4;");
	     	});
	        regenerateButton.setOnMouseExited(e -> {regenerateButton.setStyle("-fx-border-color: #FF69B4; -fx-border-width: 2px; "
	     			+ "-fx-font-family: 'Comic Sans MS'; -fx-font-size: 16px; -fx-text-fill: lightgray; -fx-background-color: black;");
	     	});
	        regenerateButton.setStyle("-fx-border-color: #FF69B4; -fx-border-width: 2px; "
                    + "-fx-font-family: 'Comic Sans MS'; -fx-font-size: 16px; -fx-text-fill: lightgray; -fx-background-color: black");
	        // Handle loginButton clicked
	        regenerateButton.setOnAction(e -> handleRegenerateButtonClick());

	        // Create Mastered Button and style - clicked when user fully memorizes text
	        Button MasteredButton = new Button("MASTERED!");
	        MasteredButton.setOnMouseEntered(e -> {MasteredButton.setStyle("-fx-border-color: #FF69B4; -fx-border-width: 2px; "
	     			+ "-fx-font-family: 'Comic Sans MS'; -fx-font-size: 16px; -fx-text-fill: black; -fx-background-color: #FF69B4;");
	     	});
	        MasteredButton.setOnMouseExited(e -> {MasteredButton.setStyle("-fx-border-color: #FF69B4; -fx-border-width: 2px; "
	     			+ "-fx-font-family: 'Comic Sans MS'; -fx-font-size: 16px; -fx-text-fill: lightgray; -fx-background-color: black;");
	     	});
	        MasteredButton.setStyle("-fx-border-color: #FF69B4; -fx-border-width: 2px; "
                    + "-fx-font-family: 'Comic Sans MS'; -fx-font-size: 16px; -fx-text-fill: lightgray; -fx-background-color: black");
	        // Handle loginButton clicked
	        MasteredButton.setOnAction(e -> handleMasteredButtonClick());

	        // HBox to hold buttons side by side
	        HBox buttonBox = new HBox(10);
	        buttonBox.getChildren().addAll(backButton, MasteredButton);
	        buttonBox.setAlignment(Pos.CENTER);
	        
	        // HBox for radio Buttons
	        HBox radioGroup = new HBox(20);
	        radioGroup.setAlignment(Pos.CENTER);

	        // Create radio buttons and labels
	        for (int i = 1; i <= 5; i++) {
	            RadioButton radioButton = new RadioButton();
				radioButton.setText(String.valueOf(i));
				radioButton.setStyle("-fx-text-fill: rgba(0, 0, 0, 0);");
	            radioButton.setToggleGroup(toggleGroup);

	            // Add radio buttons to a VBox for each row
	            VBox radioBox = new VBox(15);
	            radioBox.setAlignment(Pos.CENTER);
	            radioBox.getChildren().add(radioButton);

	            // Add labels to radioBox
	            Label label = new Label("lvl " + i);
	            label.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-size: 14px; -fx-text-fill: lightgray;");
	            radioBox.getChildren().add(label);

	            radioGroup.getChildren().add(radioBox);
	        }

	        //VBox to hold all page elements
	        VBox root = new VBox(25);
	        root.getChildren().addAll(memorizePane, radioGroup, regenerateButton, buttonBox);
	        VBox.setMargin(memorizePane, new Insets(0,0,0,0));
	        VBox.setMargin(MasteredButton, new Insets(25,0,0,0));
	        root.setAlignment(Pos.CENTER);

	        BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, null, null);
	        Background background = new Background(backgroundFill);
	        root.setBackground(background);
	        
			Scene scene = new Scene(root,800,800);
			primaryStage.setTitle("Memorize");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * If back button is clicked, go back to Menu Screen
	 */
	private void handleBackButtonClick() {
		Controller.activateScreen(Controller.menuScreen);
	}

	/**
	 * If regenerate button is clicked, get which radio button is selected and regenerate text with
	 * appropriate percentage of text redacted.
	 */
	private void handleRegenerateButtonClick() {
		System.out.println("Text Regenerated");
		RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
		if (selectedRadioButton != null) {
			String selectedLevel = selectedRadioButton.getText();
			System.out.println("Selected Level: " + selectedLevel);

			// Get the text from the label
			String originalText = Controller.selectedWriting.getText();

			// Create a copy of the original text
			String textCopy = originalText;

			// Redact percentage based on radio button selected
			double redactionPercentage = 0.0;  // default to 0%
			switch (selectedLevel){
				case "1":
					redactionPercentage = 0.20;  // 20%
					break;
				case "2":
					redactionPercentage = 0.40;  // 40%
					break;
				case "3":
					redactionPercentage = 0.60;  // 60%
					break;
				case "4":
					redactionPercentage = 0.80;  // 80%
					break;
				case "5":
					redactionPercentage = 1.0;  // 100%
					break;
				default:
					break;
			}

			// Redact % of the text in the copy
			String redactedText = redact(textCopy, (redactionPercentage));

			// Set the newly redacted text to the label
			textToBeMemorized.setText(redactedText);
		}
		else {
			System.out.println("No level selected");
		}
	}

	/**
	 * If Mastered Button is clicked, award current user a badge and navigate to Good Job screen
	 */
	private void handleMasteredButtonClick() {
		System.out.println("You're a Master!");
		// Connect to db to get current user number of badges and +1 badge
		Connection conn = DatabaseConnection.getDatabaseConnection();
		try {
			PreparedStatement statement = conn.prepareStatement("update user_profiles set badges = badges+1 where username = ?");
			statement.setString(1, Controller.currentUser);
			statement.execute();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		Controller.activateScreen(Controller.goodJobScreen);
	}

	@Override
	public void DestroyScreen() {
	}

	/**
	 *  Redact a provided percentage of words from text based on user radio button selection
	 */
	public static String redact(String input, double percent) {
		int numWordsToRedact = (int) Math.ceil(countWords(input) * percent);

		// Create a Random object to generate random indices
		Random random = new Random();  //RANDOM SEED

		// Use a StringBuilder for string manipulation
		StringBuilder result = new StringBuilder();

		// Keep track of number of words redacted
		int wordsRedacted = 0;

		// Split the input into words and process each word
		String[] words = input.split("\\s+");

		for (String word: words) {
			if (wordsRedacted < numWordsToRedact  &&  random.nextDouble() <= percent) {
				wordsRedacted++;
				if (result.length() > 0) {
					result.append(" ");
				}
				result.append(word.replaceAll("[A-Za-z]",  "_"));
			}
			else {
				if (result.length() > 0) {
					result.append(" ");
				}
				result.append(word);
			}
		}
		return result.toString();
	}

	/**
	 * Return the number of words in a string
	 */
	public static int countWords (String input) {
		String [] words = input.trim().split("\\s+");
		return words.length;
	}
}