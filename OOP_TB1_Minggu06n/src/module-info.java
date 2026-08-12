module OOP_TB1_Minggu06n {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	
	opens SisforCDGame to javafx.graphics, javafx.fxml, javafx.base;
	exports SisforCDGame;
}
