package com.example.memorization_mastery;

/**
 * Screen Interface. All screens (scenes) implement Screen Interface.
 * */
public interface Screen {
	
	/**
	 * Initialize new Screen
	 * */
	void InitializeScreen();
	
	
	/**
	 * Destroy/Close Screen
	 * */
	void DestroyScreen();
}
