
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.net.Inet4Address;
import java.text.SimpleDateFormat;
import java.util.Date;


public class GUIUserController extends Application {

        private ShiftsModel shiftsModel = new ShiftsModel();
        private Date weekStart = shiftsModel.getCurrentWeekMonday();
        //private Date weekStart = shiftsModel.getCurrentWeekMondayTest(0);

        private static final String USERNAME = "Craig";


        // launch the application
        public void start(Stage s)
        {
            s.setTitle("Java Shift Controller");

            HBox mainHB = new HBox();

            VBox vb = new VBox(createMenuBar());

            VBox vbLeft = new VBox();


            // create a scene
            Scene sc = new Scene(vb, 800, 350);

            //Create Title
            VBox titleVB = new VBox();
            titleVB.setAlignment(Pos.CENTER);
            Label title = new Label("Java Shift Recorder");
            Label emp = new Label("");
            title.setFont(Font.font ("Verdana", 30));
            title.setTextFill(Color.BLACK);
            titleVB.getChildren().add(title);
            vb.getChildren().add(titleVB);
            vb.getChildren().add(emp);

            VBox upcomingShiftsVB = new VBox();

           upcomingShiftsVB.setAlignment(Pos.BASELINE_RIGHT);

            Text welcome = new Text("This Weeks Shifts. (w/c: " + shiftsModel.dateFormatedForGUI()+")");
            Text empty = new Text(" ");
            welcome.setTextAlignment(TextAlignment.LEFT );
            welcome.setFont(Font.font ("Verdana", 20));

            welcome.setFill(Color.GREEN);

            upcomingShiftsVB.setAlignment(Pos.BASELINE_LEFT);
            upcomingShiftsVB.getChildren().add(welcome);
            upcomingShiftsVB.getChildren().add(empty);
            upcomingShiftsVB.getChildren().add(createWeekdayText("Monday:  " + shiftsModel.splitIntoWeeks().get(weekStart).get(0)));
            upcomingShiftsVB.getChildren().add(createWeekdayText("Tuesday:  " + shiftsModel.splitIntoWeeks().get(weekStart).get(1)));
            upcomingShiftsVB.getChildren().add(createWeekdayText("Wednesday:  " + shiftsModel.splitIntoWeeks().get(weekStart).get(2)));
            upcomingShiftsVB.getChildren().add(createWeekdayText("Thursday:  " + shiftsModel.splitIntoWeeks().get(weekStart).get(3)));
            upcomingShiftsVB.getChildren().add(createWeekdayText("Friday:  " + shiftsModel.splitIntoWeeks().get(weekStart).get(4)));
            upcomingShiftsVB.getChildren().add(createWeekdayText("Saturday:  " + shiftsModel.splitIntoWeeks().get(weekStart).get(5)));
            upcomingShiftsVB.getChildren().add(createWeekdayText("Sunday:  " + shiftsModel.splitIntoWeeks().get(weekStart).get(6)));
            vb.getChildren().add(upcomingShiftsVB);

            vb.getChildren().add(mainHB);









            mainHB.setSpacing(200);
            mainHB.getChildren().addAll(vbLeft,upcomingShiftsVB );


            introText(vbLeft );
            EnterShift(vbLeft );


            // set the scene
            s.setScene(sc);

            s.setResizable(false);
            s.show();
        }

        public static void main(String args[]){
        launch(args);
    }

    public Text createWeekdayText(String nameWeekDay){
        Text weekday = new Text(nameWeekDay);
        weekday.setTextAlignment(TextAlignment.CENTER );
        weekday.setFont(Font.font ("Verdana", 20));
        weekday.setFill(Color.BLACK);

        return weekday;
    }



    public void introText(VBox vb){
        Text welcome = new Text("Welcome Back " + USERNAME + "...");
        welcome.setFont(Font.font ("Verdana", 20));
        welcome.setFill(Color.BLUE);

        Text moneyEarned = new Text("\nTotal Earned: £" + shiftsModel.getTotalAmountEarned());
        moneyEarned .setFont(Font.font ("Verdana", 15));
        moneyEarned .setFill(Color.BLACK);

        Text totalTime = new Text("Total Time: " + shiftsModel.getTotalTimeWorked() + " Hours\n");
        totalTime .setFont(Font.font ("Verdana", 15));
        totalTime .setFill(Color.BLACK);

        Text addShiftTxt = new Text("\nAdd a New Shift...");
        addShiftTxt.setTextAlignment(TextAlignment.CENTER );
        addShiftTxt.setFont(Font.font ("Verdana", 20));
        addShiftTxt.setFill(Color.BLUE);


        vb.getChildren().add(welcome);
        vb.getChildren().add(moneyEarned);
        vb.getChildren().add(totalTime);
        vb.getChildren().add(addShiftTxt);
    }

    public void EnterShift(VBox vb){
        Text lblDate = new Text("Shift Date:  ");
        lblDate.setFont(Font.font ("Verdana", 15));
        lblDate .setFill(Color.BLACK);
        TextField txtFieldDate = new TextField ("DD-MMM-YYYY");
        //textField.setPromptText();
        HBox hb = new HBox();
        hb.getChildren().addAll(lblDate, txtFieldDate);
        hb.setSpacing(10);
        vb.getChildren().add(hb);

        Text lblSrtTme = new Text("Start Time: ");
        lblSrtTme.setFont(Font.font ("Verdana", 15));
        lblSrtTme .setFill(Color.BLACK);
        TextField txtFieldStartTime = new TextField ("HH");
        //textField.setPromptText("HH");
        HBox hb2 = new HBox();
        hb2.getChildren().addAll(lblSrtTme, txtFieldStartTime);
        hb2.setSpacing(10);
        vb.getChildren().add(hb2);


        Text lblFinishTme = new Text("Finish Time:  ");
        lblFinishTme.setFont(Font.font ("Verdana", 15));
        lblFinishTme .setFill(Color.BLACK);
        TextField txtFieldFinishTime = new TextField ("HH");
        HBox hb3 = new HBox();
        hb3.getChildren().addAll(lblFinishTme, txtFieldFinishTime);
        hb.setSpacing(10);
        vb.getChildren().add(hb3);

        //Defining the Submit button
        HBox hb4 = new HBox();
        Button submit = new Button("Submit");
        submit.setFont(Font.font ("Verdana", 15));

        hb4.getChildren().add(submit);

        //Defining the Clear button
        Button clear = new Button("Clear");
      clear.setFont(Font.font ("Verdana", 15));
        hb4.getChildren().add(clear);
        hb.setSpacing(10);
        vb.getChildren().add(hb4);

        //Defining a empty label object
        Label label = new Label();
        vb.getChildren().add(label);

        EventHandler<ActionEvent> eventClear = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                txtFieldDate.clear();
                txtFieldStartTime.clear();
                txtFieldFinishTime.clear();
                label.setText("");
            }
        };

        clear.setOnAction(eventClear);

        EventHandler<ActionEvent> eventSubmit = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if ((txtFieldDate.getText() != null && !txtFieldStartTime.getText().isEmpty()) && !txtFieldFinishTime.getText().isEmpty()) {

                    SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yyyy");
                    SimpleDateFormat formatTime = new SimpleDateFormat("HH");
                    String date = txtFieldDate.getText();
                    String startTime = txtFieldStartTime.getText();
                    String finishTime = txtFieldFinishTime.getText();

                    boolean dateError = false;
                    boolean timeError = false;


                    try {
                        formatDate.parse(date);

                    } catch (Exception ex) {
                        dateError = true;
                        label.setText("Error When Parsing Date");
                        label.setFont(Font.font("Verdana", 15));
                        label.setTextFill(Color.RED);
                    }

                    try {
                        formatTime.parse(startTime);
                        formatTime.parse(finishTime);

                        Integer intStartTime = Integer.parseInt(startTime);
                        Integer intFinishTime = Integer.parseInt(finishTime);


                        if ((intStartTime < 0 || intStartTime > 24) || (intFinishTime < 0 || intFinishTime > 24)) {
                            throw new Exception();

                        }
                    } catch (Exception ex) {
                        timeError = true;
                        label.setText("Error When Parsing Time");
                        label.setFont(Font.font("Verdana", 15));
                        label.setTextFill(Color.RED);
                    }


                    if (!dateError && !timeError) {
                        label.setText("Shift added Successfully!");
                        label.setFont(Font.font("Verdana", 15));
                        label.setTextFill(Color.GREEN);


                        shiftsModel.addShift(new ShiftModel((date + " " + startTime + ":00:00"), (date + " " + finishTime + ":00:00")));

                        txtFieldDate.clear();
                        txtFieldStartTime.clear();
                        txtFieldFinishTime.clear();

                    }
                    dateError = false;
                    timeError = false;


                } else {
                    label.setText("One or more fields is empty??");
                    label.setFont(Font.font("Verdana", 15));
                    label.setTextFill(Color.RED);
                }

            }

        };

        submit.setOnAction(eventSubmit);

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