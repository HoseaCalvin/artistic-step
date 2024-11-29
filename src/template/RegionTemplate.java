package template;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Region;

public class RegionTemplate {

	public static final double H_GAP = 15;
	public static final double V_GAP = 15;
	
	public static ColumnConstraints columnConstraints(double width) {
		ColumnConstraints column = new ColumnConstraints();
		column.setPercentWidth(width);
		
		return column;
	}
	
	public static Region separateHeight(double height) {
		Region separator = new Region();
		separator.setPrefHeight(height);
		
		return separator;
	}
	
	public static Region separateWidth(double height) {				
		Region separator = new Region();
		separator.setPrefWidth(height);
		
		return separator;
	}
}
