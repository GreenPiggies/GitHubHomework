import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

// The Application class of the package javafx.application is the entry point for a JavaFX application.
// To create a JavaFX application, inherit this class and implement its abstract method start.
// In the start() method, write all the code for the JavaFX graphics.
public class TreeViewExample extends Application {

	@Override
	public void start(Stage stage) {
		// In a hierarchial tree structure, the highest object in the hierarchy is called the "root". 
		// By the way, this doesn't mean that the variable name needs to be called "root". 
		// The root contains several child items, which can have children as well. 
		// An item without children is called a "leaf". 
		
		// In JavaFX, a tree structure is composed of TreeItem objects.
		// One of these TreeItem object should be the "root", which all other TreeItem objects would belong to.
		// The TreeItem class uses generic notation to be able to represent any object.
		// This example will just use String objects.
		TreeItem<String> rootItem = new TreeItem<>("I'm (G)root");
		rootItem.setExpanded(true);
		
		for (int count = 1; count <= 5; count++) {
			TreeItem<String> item = new TreeItem<>("Child: " + count);
			rootItem.getChildren().add(item);
		}
		
		TreeItem<String> fruits = new TreeItem<String>("Fruits");
		rootItem.getChildren().add(fruits);		
		
		ArrayList<TreeItem<String>> fruitList = new ArrayList<TreeItem<String>>();
		TreeItem<String> apple = new TreeItem<String>("Apple");
		fruitList.add(apple);
		TreeItem<String> orange = new TreeItem<String>("Orange");
		fruitList.add(orange);
		TreeItem<String> pear = new TreeItem<String>("Pear");
		fruitList.add(pear);
		fruits.getChildren().addAll(fruitList);
		fruits.getChildren().add(new TreeItem<String>("Banana"));

		
		// To view the tree structure in JavaFX, we use the TreeView class. 
		// We can specify the root of the tree when we create a new TreeView object.
		TreeView<String> tree = new TreeView<>(rootItem);
		
		// Create a scene object with the TreeView.
		Scene scene = new Scene(tree);
		
		// Add the scene to the stage.
		stage.setScene(scene);
		
		// Set the title of the stage.
		stage.setTitle("Tree View Example");
		
		// Set the size of the stage (window dimensions):
		stage.setWidth(600); // 300
		stage.setHeight(900); // 450
		
		// Display the contents of the stage.
		stage.show();
	}
	
	
	public static void main(String[] args) {
		// To launch the JavaFX application, call the launch method.
		// This method internally constructs an instance of the specified Application class and calls the start() method of the class.
		launch(args);
	}

}
