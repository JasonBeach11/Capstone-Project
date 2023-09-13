package com.example.memorization_mastery;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *  MenuScreen Class contains all UI elements and associated logic for Menu Screen
 */
public class MenuScreen implements Screen{
	// Menu Screen Class-accessible variables
	static MenuScreen instance;
	Stage primaryStage;
	Label menuLabel = new Label();
	Label currentUserLabel = new Label();
	Label currentUserBadgesLabel = new Label();

	// Load image for badge icon
	Image badgeIcon = new Image("blackBadge.png");
	private TabPane tabPane;
			
	/**
	 * MenuScreen constructor. If more than one Menu Screen is created, an error will be output to the console.
	 */
	public MenuScreen(Stage primaryStage) {
		if(instance == null) {
			this.primaryStage = primaryStage;
			instance = this;
		}
		else {
			System.out.println("ERROR: Instance of Menu Screen already exists.");
		}
	}

	/**
	 * Create Menu Screen UI elements and associated logic
	 */
	@Override
	public void InitializeScreen() {
		System.out.print("\nHello MenuScreen");

		// Import badge icon and format
		ImageView badgeImageView = new ImageView(badgeIcon);
		badgeImageView.setFitWidth(20);
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

			// Menu label/title
			menuLabel.setText("Let's Memorize!");
			menuLabel.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-size: 36px; -fx-text-fill: rgb(70, 191, 238);");
	        
	        // Create ListView controls for each tab
	        ListView<Writing> listViewTab1 = new ListView<>();
	        listViewTab1.setItems(DatabaseConnection.getPoemTitles());
	        ListView<Writing> listViewTab2 = new ListView<>();
	        listViewTab2.setItems(DatabaseConnection.getScriptureReferences());
	        ListView<Writing> listViewTab3 = new ListView<>();
	        listViewTab3.setItems(DatabaseConnection.getHistoricalWritingsTitles());
	        ListView<Writing> listViewTab4 = new ListView<>();
	        listViewTab4.setItems(DatabaseConnection.getFamousQuoteAuthors());
	        
	        // Create tabs for each ListView
	        Tab tab1 = new Tab("Poems", listViewTab1);
	        Tab tab2 = new Tab("Scriptures", listViewTab2);
	        Tab tab3 = new Tab("Historical Writings", listViewTab3);
	        Tab tab4 = new Tab("Famous Quotes", listViewTab4);
	        
	        //Stylize tabs
	        stylizeTab(tab1);
	        stylizeTab(tab2);
	        stylizeTab(tab3);
	        stylizeTab(tab4);
	        
	        // Create a TabPane to hold tabs
	        tabPane = new TabPane(tab1, tab2, tab3, tab4);
	        tabPane.setFocusTraversable(false);
	        
	        // Apply styling to the tabs and ListView
	        tabPane.setStyle("-fx-border-color: rgb(70, 191, 238); -fx-border-width: 2px;");
	        for (Tab tab : tabPane.getTabs()) {
	            tab.setStyle("-fx-background-color: lightgray; -fx-text-fill: black;");
	            ListView<String> listView = (ListView<String>) tab.getContent();
	            listView.setStyle("-fx-border-color: rgb(70, 191, 238); -fx-border-width: 2px;");
	        }
	        
	        // Create a Back Button and add styling
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
	        
	        // Create Memorize Button and add styling
	        Button memorizeButton = new Button("Memorize!");
	     	memorizeButton.setOnMouseEntered(e -> {memorizeButton.setStyle("-fx-border-color: #FF69B4; -fx-border-width: 2px; "
	     			+ "-fx-font-family: 'Comic Sans MS'; -fx-font-size: 16px; -fx-text-fill: black; -fx-background-color: #FF69B4;");
	     	});
	     	memorizeButton.setOnMouseExited(e -> {memorizeButton.setStyle("-fx-border-color: #FF69B4; -fx-border-width: 2px; "
	     			+ "-fx-font-family: 'Comic Sans MS'; -fx-font-size: 16px; -fx-text-fill: lightgray; -fx-background-color: black;");
	     	});
	        memorizeButton.setStyle("-fx-border-color: #FF69B4; -fx-border-width: 2px; "
                    + "-fx-font-family: 'Comic Sans MS'; -fx-font-size: 16px; -fx-text-fill: lightgray; -fx-background-color: black");
	        // Handle memorize button clicked
	        memorizeButton.setOnAction(e -> handleMemorizeButton());
	        
	        //Create an HBox to hold current user and badges side by side
	        HBox userBox = new HBox(10);
	        userBox.setAlignment(Pos.CENTER_RIGHT);
	        userBox.getChildren().addAll(currentUserLabel, badgeImageView, currentUserBadgesLabel);
			
	        // Create an HBox to hold both buttons side by side
	        HBox buttonBox = new HBox(10);
	        buttonBox.setAlignment(Pos.CENTER);
	        buttonBox.getChildren().addAll(backButton, memorizeButton);

	        // Create a VBox to hold the TabPane
	        VBox vBox = new VBox();
	        vBox.getChildren().addAll(userBox, menuLabel, tabPane, buttonBox);
	        vBox.setMaxWidth(400);
	        
	        // Adjust VBox spacing and margins
	        VBox.setMargin(userBox, new Insets(10,0,0,200));
	        VBox.setMargin(buttonBox, new Insets(20, 0, 0, 0));
	        VBox.setMargin(menuLabel, new Insets(100, 0, 0, 60));
	        VBox.setMargin(tabPane, new Insets(20, 0, 0, 0));

			// Border pane as root to hold all UI elements
			BorderPane root = new BorderPane();
			BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, null, null);
	        Background background = new Background(backgroundFill);
	        root.setBackground(background);
	        root.setCenter(vBox);
			
			Scene scene = new Scene(root,800,800);
			primaryStage.setTitle("Menu");
			primaryStage.setScene(scene);
			primaryStage.show();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Add inline CSS Styling to tabs 
	 */
	private void stylizeTab(Tab tab) {
		tab.setClosable(false);
        tab.setStyle("-fx-background-color: pink; -fx-border-color: pink; -fx-border-width: 2px; -fx-text-fill: black;");
        // Set the selected tab style
        tab.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            if (isSelected) {
            	tab.setStyle("-fx-background-color: pink; -fx-border-color: pink; -fx-border-width: 2px; -fx-text-fill: black;");
            } else {
            	tab.setStyle("-fx-border-color: pink; -fx-border-width: 2px; -fx-text-fill: black;");
            }
        });
	}

	/**
	 * If back button is clicked, navigate back to Login Screen
	 */
	private void handleBackButtonClick() {
		Controller.activateScreen(Controller.loginScreen);
	}

	/**
	 * Memorize button selects highlighted text in text view and sends it to Controller. Then navigates to
	 * memorize screen.
	 */
	private void handleMemorizeButton() {
	    Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
	    if (selectedTab != null) {
	        ListView<Writing> selectedListView = (ListView<Writing>) selectedTab.getContent();
	        Writing selectedItem = selectedListView.getSelectionModel().getSelectedItem();
	        
	        if (selectedItem != null) {
	        	Controller.selectedWriting = selectedItem;
	            System.out.println("Memorize Button clicked! Selected item: " + Controller.selectedWriting);
	            Controller.activateScreen(Controller.memorizeScreen);
	        } else {
	            System.out.println("No item selected.");
	        }
	    }
		else {
	        System.out.println("No tab selected.");
	    }
	}

	@Override
	public void DestroyScreen() {
	}
}
