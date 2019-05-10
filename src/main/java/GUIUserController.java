
import javafx.application.Application;
import javafx.application.HostServices;
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

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;


public class GUIUserController extends Application {

    private ShiftsModel shiftsModel = new ShiftsModel();
    private int selectedWeek = 0;
    private ShiftModel weekStart = shiftsModel.getCurrentWeekMondayAdaptable(selectedWeek);
    private Text welcome;
    private boolean furthestPoint = false;
    private boolean dateToggle = false;

    private GuiHelpers guiHelpers;

    Text mon;
    Text tue;
    Text wed;
    Text thu;
    Text fri;
    Text sat;
    Text sun;


    private static final String USERNAME = "Craig";


    // launch the application
    public void start(Stage s) {

        guiHelpers = new GuiHelpers();
        s.setTitle("Java Shift Controller");

        HBox mainHB = new HBox();

        VBox vb = new VBox(createMenuBar());

        VBox vbLeft = new VBox();


        // create a scene
        Scene sc = new Scene(vb, 800, 380);

        //Create Title
        VBox titleVB = new VBox();
        titleVB.setAlignment(Pos.CENTER);
        Label title = new Label("Morrisons Shift Recorder");
        Label emp = new Label("");
        title.setFont(Font.font("Verdana", 30));
        title.setTextFill(Color.BLACK);
        titleVB.getChildren().add(title);
        vb.getChildren().add(titleVB);
        vb.getChildren().add(emp);

        vb.getChildren().add(mainHB);
        mainHB.setSpacing(150);
        mainHB.getChildren().addAll(vbLeft, buildWorkingWeek());
        introText(vbLeft);
        EnterShift(vbLeft);


        // set the scene
        s.setScene(sc);

        s.setResizable(false);
        s.show();
    }

    public static void main(String args[]) {
        launch(args);
    }






    public VBox buildWorkingWeek() {
        VBox upcomingShiftsVB = new VBox();
        upcomingShiftsVB.setAlignment(Pos.BASELINE_RIGHT);

        welcome = new Text("This Weeks Shifts (w/c: " + shiftsModel.dateFormatedForGUI(selectedWeek) + ")");
        Text empty = new Text(" ");
        welcome.setTextAlignment(TextAlignment.LEFT);
        welcome.setFont(Font.font("Verdana", 20));

        welcome.setFill(Color.GREEN);

        //Defining the Update button
        HBox hb4 = new HBox();


        //Defining the Previous button
        Button previous = new Button("Previous Week");
        previous.setFont(Font.font("Verdana", 15));
        hb4.getChildren().add(previous);

        //Defining the next button
        Button next = new Button("Next Week");
        next.setFont(Font.font("Verdana", 15));
        hb4.getChildren().add(next);

        Button current = new Button("Current Week");
        current.setFont(Font.font("Verdana", 15));
        hb4.getChildren().add(current);


        //Defining a empty label object
        Label label = new Label();

        Label emp = new Label("");
        HashMap<Integer, Text> forStrikeThrough = new HashMap<>();
        mon = createWeekdayText("Monday ");
        tue = createWeekdayText("Tuesday:   ");
        wed = createWeekdayText("Wednesday: ");
        thu = createWeekdayText("Thursday:  ");
        fri = createWeekdayText("Friday:    ");
        sat = createWeekdayText("Saturday:  ");
        sun = createWeekdayText("Sunday:    ");




        mon.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> mon.setStrikethrough(true));


        try {
            guiHelpers.setValidWeekText(dateToggle,mon,tue,wed,thu,fri,sat,sun,shiftsModel,weekStart);
        } catch (NullPointerException ex) {
            guiHelpers.setOFFweekText(dateToggle,mon,tue,wed,thu,fri,sat,sun,shiftsModel,weekStart);
        }


        forStrikeThrough.put(weekStart.getDateImproved(weekStart.getDate()), mon);
        forStrikeThrough.put(weekStart.getDateImproved(weekStart.getDate() + 1), tue);
        forStrikeThrough.put(weekStart.getDateImproved(weekStart.getDate() + 2), wed);
        forStrikeThrough.put(weekStart.getDateImproved(weekStart.getDate() + 3), thu);
        forStrikeThrough.put(weekStart.getDateImproved(weekStart.getDate() + 4), fri);
        forStrikeThrough.put(weekStart.getDateImproved(weekStart.getDate() + 5), sat);
        forStrikeThrough.put(weekStart.getDateImproved(weekStart.getDate() + 6), sun);

        Date currentDate = new Date();

        for (int x : forStrikeThrough.keySet()) {

            if (x  <= currentDate.getDate())
            {
                forStrikeThrough.get(x).setStrikethrough(true);
                System.out.println(x + "  " + currentDate.getDate());
            }

        }


        upcomingShiftsVB.setAlignment(Pos.BASELINE_LEFT);
        upcomingShiftsVB.getChildren().add(welcome);
        upcomingShiftsVB.getChildren().add(empty);
        upcomingShiftsVB.getChildren().add(mon);
        upcomingShiftsVB.getChildren().add(tue);
        upcomingShiftsVB.getChildren().add(wed);
        upcomingShiftsVB.getChildren().add(thu);
        upcomingShiftsVB.getChildren().add(fri);
        upcomingShiftsVB.getChildren().add(sat);
        upcomingShiftsVB.getChildren().add(sun);
        upcomingShiftsVB.getChildren().add(emp);
        upcomingShiftsVB.getChildren().add(hb4);
        upcomingShiftsVB.getChildren().add(label);


        EventHandler<ActionEvent> eventPrev = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                removeStikeThrough();
                furthestPoint = false;
                selectedWeek++;
                weekStart = shiftsModel.getCurrentWeekMondayAdaptable(selectedWeek);
                welcome.setText("Shifts for w/c: " + shiftsModel.dateFormatedForGUI(selectedWeek));

                guiHelpers.setValidWeekText(dateToggle,mon,tue,wed,thu,fri,sat,sun,shiftsModel,weekStart);

                label.setText("Previous week loaded");
                label.setFont(Font.font("Verdana", 15));
                label.setTextFill(Color.GREEN);


            }
        };

        EventHandler<ActionEvent> eventNext = new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent e) {

                removeStikeThrough();
                try {
                    if (!furthestPoint) {
                        selectedWeek--;
                    }
                    weekStart = shiftsModel.getCurrentWeekMondayAdaptable(selectedWeek);
                    welcome.setText("Shifts for w/c: " + shiftsModel.dateFormatedForGUI(selectedWeek));


                    guiHelpers.setValidWeekText(dateToggle,mon,tue,wed,thu,fri,sat,sun,shiftsModel,weekStart);

                    label.setText("Previous week loaded");
                    label.setFont(Font.font("Verdana", 15));
                    label.setTextFill(Color.GREEN);
                } catch (NullPointerException ex) {
                    furthestPoint = true;
                    label.setText("No more shifts to load");
                    label.setFont(Font.font("Verdana", 15));
                    label.setTextFill(Color.RED);

                    guiHelpers.setOFFweekText(dateToggle,mon,tue,wed,thu,fri,sat,sun,shiftsModel,weekStart);

                }

            }
        };

        EventHandler<ActionEvent> eventCurrent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                furthestPoint = false;
                selectedWeek = 0;
                weekStart = shiftsModel.getCurrentWeekMondayAdaptable(selectedWeek);
                welcome.setText("This Weeks Shifts (w/c: " + shiftsModel.dateFormatedForGUI(selectedWeek) + ")");


                HashMap<Integer, Text> forStrikeThrough = new HashMap<>();

                try {
                    guiHelpers.setValidWeekText(dateToggle,mon,tue,wed,thu,fri,sat,sun,shiftsModel,weekStart);

                } catch (NullPointerException ex) {
                    guiHelpers.setOFFweekText(dateToggle,mon,tue,wed,thu,fri,sat,sun,shiftsModel,weekStart);

                }


                forStrikeThrough.put(weekStart.getDateImproved(weekStart.getDate()), mon);
                forStrikeThrough.put(weekStart.getDateImproved(weekStart.getDate() + 1), tue);
                forStrikeThrough.put(weekStart.getDateImproved(weekStart.getDate() + 2), wed);
                forStrikeThrough.put(weekStart.getDateImproved(weekStart.getDate() + 3), thu);
                forStrikeThrough.put(weekStart.getDateImproved(weekStart.getDate() + 4), fri);
                forStrikeThrough.put(weekStart.getDateImproved(weekStart.getDate() + 5), sat);
                forStrikeThrough.put(weekStart.getDateImproved(weekStart.getDate() + 6), sun);

                Date currentDate = new Date();

                for (int x : forStrikeThrough.keySet()) {
                    if (x + 1 < currentDate.getDate()) {
                        forStrikeThrough.get(x).setStrikethrough(true);
                    }
                }


                label.setText("Current");
                label.setFont(Font.font("Verdana", 15));
                label.setTextFill(Color.GREEN);
            }
        };


        previous.setOnAction(eventPrev);

        next.setOnAction(eventNext);

        current.setOnAction(eventCurrent);


        return upcomingShiftsVB;
    }


    public void removeStikeThrough() {
        mon.setStrikethrough(false);
        tue.setStrikethrough(false);
        wed.setStrikethrough(false);
        thu.setStrikethrough(false);
        fri.setStrikethrough(false);
        sat.setStrikethrough(false);
        sun.setStrikethrough(false);

    }



    public Text createWeekdayText(String nameWeekDay) {
        Text weekday = new Text(nameWeekDay);
        weekday.setTextAlignment(TextAlignment.CENTER);
        weekday.setFont(Font.font("Verdana", 20));
        weekday.setFill(Color.BLACK);

        return weekday;
    }


    public void introText(VBox vb) {
        Text welcome = new Text("Welcome Back " + USERNAME + "...");
        welcome.setFont(Font.font("Verdana", 20));
        welcome.setFill(Color.BLUE);

        Text moneyEarned = new Text("\nTotal Earned: £" + shiftsModel.getTotalAmountEarned());
        moneyEarned.setFont(Font.font("Verdana", 15));
        moneyEarned.setFill(Color.BLACK);

        Text totalTime = new Text("Total Time: " + shiftsModel.getTotalTimeWorked() + " Hours");
        totalTime.setFont(Font.font("Verdana", 15));
        totalTime.setFill(Color.BLACK);

        Text expectedPay = new Text("This Months Expected Pay: £" + shiftsModel.calculateExpectedPay("08-Apr-2019 16:00:00","05-May-2019 16:00:00"));
        expectedPay.setFont(Font.font("Verdana", 15));
        expectedPay.setFill(Color.BLACK);

        Text expectedHours = new Text("This Months Expected Hours: " + shiftsModel.calculateExpectedHours("08-Apr-2019 16:00:00","05-May-2019 16:00:00")+ " Hours");
        expectedHours.setFont(Font.font("Verdana", 15));
        expectedHours.setFill(Color.BLACK);

        Text addShiftTxt = new Text("\nAdd a New Shift...");
        addShiftTxt.setTextAlignment(TextAlignment.CENTER);
        addShiftTxt.setFont(Font.font("Verdana", 20));
        addShiftTxt.setFill(Color.BLUE);


        vb.getChildren().add(welcome);
        vb.getChildren().add(moneyEarned);
        vb.getChildren().add(totalTime);
        vb.getChildren().add(expectedPay);
        vb.getChildren().add(expectedHours);
        vb.getChildren().add(addShiftTxt);
    }

    public void EnterShift(VBox vb) {
        Text lblDate = new Text("Shift Date:  ");
        lblDate.setFont(Font.font("Verdana", 15));
        lblDate.setFill(Color.BLACK);
        TextField txtFieldDate = new TextField("DD-MMM-YYYY");
        //textField.setPromptText();
        HBox hb = new HBox();
        hb.getChildren().addAll(lblDate, txtFieldDate);
        hb.setSpacing(10);
        vb.getChildren().add(hb);

        Text lblSrtTme = new Text("Start Time: ");
        lblSrtTme.setFont(Font.font("Verdana", 15));
        lblSrtTme.setFill(Color.BLACK);
        TextField txtFieldStartTime = new TextField("HH");
        //textField.setPromptText("HH");
        HBox hb2 = new HBox();
        hb2.getChildren().addAll(lblSrtTme, txtFieldStartTime);
        hb2.setSpacing(10);
        vb.getChildren().add(hb2);


        Text lblFinishTme = new Text("Finish Time:  ");
        lblFinishTme.setFont(Font.font("Verdana", 15));
        lblFinishTme.setFill(Color.BLACK);
        TextField txtFieldFinishTime = new TextField("HH");
        HBox hb3 = new HBox();
        hb3.getChildren().addAll(lblFinishTme, txtFieldFinishTime);
        hb.setSpacing(10);
        vb.getChildren().add(hb3);

        Label emp = new Label("");


        vb.getChildren().add(emp);

        //Defining the Submit button
        HBox hb4 = new HBox();
        Button submit = new Button("Submit");
        submit.setFont(Font.font("Verdana", 15));

        hb4.getChildren().add(submit);

        //Defining the Clear button
        Button clear = new Button("Clear");
        clear.setFont(Font.font("Verdana", 15));
        hb4.getChildren().add(clear);
        hb.setSpacing(10);

        Button update = new Button("Update");
        update.setFont(Font.font("Verdana", 15));

        hb4.getChildren().add(update);
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
                        label.setText("Error when reading date!");
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
                        label.setText("Error when reading time");
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

        EventHandler<ActionEvent> eventUpdate = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                guiHelpers.setValidWeekText(dateToggle,mon,tue,wed,thu,fri,sat,sun,shiftsModel,weekStart);

                label.setText("Successfully Updated");
                label.setFont(Font.font("Verdana", 15));
                label.setTextFill(Color.GREEN);
            }
        };

        update.setOnAction(eventUpdate);


    }


    public MenuBar createMenuBar() {
        MenuBar mb = new MenuBar();
        mb.getMenus().add(createFileMenu());
        mb.getMenus().add(createEditMenu());
        mb.getMenus().add(createViewMenu());
        mb.getMenus().add(createAboutMenu());
        return mb;

    }

    public Menu createFileMenu() {
        Menu menuFile = new Menu("File");
        MenuItem exit = new MenuItem("Exit");
        CheckMenuItem checkMenuItem = new CheckMenuItem("Toggle date");


        menuFile.getItems().add(exit);
        menuFile.getItems().add(checkMenuItem);

        EventHandler<ActionEvent> eventExit = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                System.exit(0);
            }
        };

        EventHandler<ActionEvent> eventToggle = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (checkMenuItem.isSelected()) {
                    dateToggle = true;
                    try {
                        guiHelpers.setValidWeekText(dateToggle,mon,tue,wed,thu,fri,sat,sun,shiftsModel,weekStart);
                    } catch (NullPointerException ex) {
                        guiHelpers.setOFFweekText(dateToggle,mon,tue,wed,thu,fri,sat,sun,shiftsModel,weekStart);
                    }

                } else {
                    dateToggle = false;
                    try {
                        guiHelpers.setValidWeekText(dateToggle,mon,tue,wed,thu,fri,sat,sun,shiftsModel,weekStart);
                    } catch (NullPointerException ex) {
                        guiHelpers.setOFFweekText(dateToggle,mon,tue,wed,thu,fri,sat,sun,shiftsModel,weekStart);
                    }
                }
            }
        };

        exit.setOnAction(eventExit);
        checkMenuItem.setOnAction(eventToggle);
        return menuFile;
    }

    public Menu createEditMenu() {
        Menu menuEdit = new Menu("Edit");
        MenuItem add = new MenuItem("Add A Shift");
        MenuItem remove = new MenuItem("Remove a Shift");
        menuEdit.getItems().add(add);
        menuEdit.getItems().add(remove);


        EventHandler<ActionEvent> eventRemove = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

                TextInputDialog dialog = new TextInputDialog("DD-MMM-YYYY");
                dialog.setTitle("Remove a shift");
                dialog.setHeaderText("Remove a Shift");
                dialog.setContentText("Please enter shift date:");

                // Traditional way to get the response value.
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    System.out.println("Your name: " + result.get());
                }


            }
        };


        remove.setOnAction(eventRemove);


        return menuEdit;
    }

    public Menu createViewMenu() {
        Menu menuView = new Menu("View");
        MenuItem allShifts = new MenuItem("Open All Shifts file");
        MenuItem totalEarned = new MenuItem("Total Money Earned");
        MenuItem totalTime = new MenuItem("Total Time");
        menuView.getItems().add(allShifts);
        menuView.getItems().add(totalEarned);
        menuView.getItems().add(totalTime);


        EventHandler<ActionEvent> eventAllShifts = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

                File file = new File(shiftsModel.getShiftFile());
                HostServices hostServices = getHostServices();
                hostServices.showDocument(file.getAbsolutePath());
            }
        };

        EventHandler<ActionEvent> eventTotalEarned = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

                Alert alert = new Alert(Alert.AlertType.NONE, "£" + shiftsModel.getTotalAmountEarned(), ButtonType.OK);
                alert.setHeaderText("Total Amount of money made in Morrisons");
                alert.setTitle("Total Pay");
                alert.showAndWait();
            }
        };


        EventHandler<ActionEvent> eventTotalHours = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

                Alert alert = new Alert(Alert.AlertType.NONE, shiftsModel.getTotalTimeWorked() + " Hours", ButtonType.OK);
                alert.setHeaderText("Total Hours spent in Morrisons");
                alert.setTitle("Total Hours");
                alert.showAndWait();

            }
        };


        allShifts.setOnAction(eventAllShifts);
        totalEarned.setOnAction(eventTotalEarned);
        totalTime.setOnAction(eventTotalHours);
        return menuView;

    }

    public Menu createAboutMenu() {
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