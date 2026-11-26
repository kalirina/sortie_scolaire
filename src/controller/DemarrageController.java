package controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class DemarrageController {
	
	@FXML
	private Spinner<Integer> spinnerN;
	
	@FXML
	private Label lblError;
	
	@FXML
	private void initialize() {
		spinnerN.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 26,4));
	}
	
	@FXML
	private void handleCommencer() {
		int n = spinnerN.getValue();
		MainApp.config.initialiser(n);
		
		// temporary to check 
		System.out.println("n = " + n + ", studs = " + MainApp.config.get_s());
	}

}
