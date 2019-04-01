
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;




public class GUIUserController extends Application {

        private ShiftsModel shiftsModel = new ShiftsModel();

        private static final String USERNAME = "Craig";


        // launch the application
        public void start(Stage s)
        {
            s.setTitle("Java Shift Controller");
            VBox vb = new VBox(createMenuBar());

            // create a scene
            Scene sc = new Scene(vb, 800, 500);


            Text welcome = new Text("Welcome Back " + USERNAME);
            welcome.setTextAlignment(TextAlignment.CENTER );
            welcome.setFont(Font.font ("Verdana", 20));
            welcome.setFill(Color.RED);

            Text moneyEarned = new Text("\nTotal Earned: £" + shiftsModel.getTotalAmountEarned());
            moneyEarned .setFont(Font.font ("Verdana", 15));
            moneyEarned .setFill(Color.BLACK);

            Text totalTime = new Text("Total Time: " + shiftsModel.getTotalTimeWorked() + " Hours");
            totalTime .setFont(Font.font ("Verdana", 15));
            totalTime .setFill(Color.BLACK);


            vb.getChildren().add(welcome);
            vb.getChildren().add(moneyEarned);
            vb.getChildren().add(totalTime);


            // set the scene
            s.setScene(sc);


            s.show();
        }

        public static void main(String args[]){
        launch(args);
    }

    public MenuBar createMenuBar(){
        MenuBar mb = new MenuBar();
        mb.getMenus().add(createFileMenu());
        mb.getMenus().add(createEditMenu());
        mb.getMenus().add(createViewMenu());
        mb.getMenus().add(createAboutMenu());
        return mb;

    }

    public Menu createFileMenu(){
        Menu menuFile = new Menu("File");
        MenuItem exit = new MenuItem("Exit");
        menuFile.getItems().add(exit);


        EventHandler<ActionEvent> eventExit = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                System.exit(0);
            }
        };

        exit.setOnAction(eventExit);
        return menuFile;
    }

    public Menu createEditMenu(){
        Menu menuEdit = new Menu("Edit");
        MenuItem add = new MenuItem("Add A Shift");
        MenuItem remove = new MenuItem("Remove a Shift");
        menuEdit.getItems().add(add);
        menuEdit.getItems().add(remove);
        return menuEdit;
    }

    public Menu createViewMenu(){
        Menu menuView = new Menu("View");
        MenuItem allShifts = new MenuItem("All Shifts");
        MenuItem totalEarned = new MenuItem("Total Money Earned");
        MenuItem totalTime = new MenuItem("Total Time");
        menuView.getItems().add(allShifts);
        menuView.getItems().add(totalEarned);
        menuView.getItems().add(totalTime);

        EventHandler<ActionEvent> eventTotalEarned= new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

                Alert alert = new Alert(Alert.AlertType.NONE, "£"+shiftsModel.getTotalAmountEarned(), ButtonType.OK);
                alert.setHeaderText("Total Amount of money made in Morrisons");
                alert.setTitle("Total Pay");
                alert.showAndWait();
            }
        };

        EventHandler<ActionEvent> eventTotalHours= new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

                Alert alert = new Alert(Alert.AlertType.NONE, shiftsModel.getTotalTimeWorked() + " Hours", ButtonType.OK);
               alert.setHeaderText("Total Hours spent in Morrisons");
               alert.setTitle("Total Hours");
               alert.showAndWait();

            }
        };


        totalEarned.setOnAction(eventTotalEarned);
        totalTime.setOnAction(eventTotalHours);
        return menuView;

    }

    public Menu createAboutMenu(){
        Menu menuAbout = new Menu("About");
        MenuItem about = new MenuItem("About this app");
        menuAbout.getItems().add(about);

        EventHandler<ActionEvent> eventAbout = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Hello and Welcome to my App!");
                alert.setTitle("Information");
                alert.setContentText("A basic application with the intention of better\n managing the hours I am putting in at work!\n I want to improve my knowledge of java");
                alert.showAndWait();
            }
        };

        about.setOnAction(eventAbout);

        return menuAbout;

    }


}

















