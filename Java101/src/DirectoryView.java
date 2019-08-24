import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class DirectoryView extends Application {
	
	@Override
	public void start(Stage stage) {
		String dir = ".";
		File fileDir = new File(dir);
		
		TreeItem<String> rootDir = list(fileDir);	

		TreeView<String> tree = new TreeView<>(rootDir);
		
		Scene scene = new Scene(tree);
		
		stage.setScene(scene);
		
		stage.setTitle("Directory View");
		
		stage.setWidth(600); // 300
		stage.setHeight(900); // 450
		
		stage.show();
	}
	
	public static TreeItem<String> list(File directory) {
		TreeItem<String> dirItem = new TreeItem<>(directory.getName());
		if (directory.isDirectory()) {
			for (File subFile : directory.listFiles()) {
				dirItem.getChildren().add(list(subFile));
			}
		}
		return dirItem;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
