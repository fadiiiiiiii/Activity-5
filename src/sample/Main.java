package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    Text headingT;
    Stage stage;
    Label nameTl,phNoTl,addressTl,sizeTl,crustTl,toppingsTl;
    TextField nameTf,phNoTf,addressTf;
    RadioButton smallRb,mediumRb,largeRb,thinRb,thickRb;
    CheckBox pepperoniCb,sausageCb,linguicaCb,olivesCb,mushroomsCb,tomatoesCb,anchoviesCb;
    Button okBtn,cancleBtn;
    HBox headigHb,CustomerDetailsHb,pizzaDetailsHb;
    VBox detailslabelVb,detailsTfVb,sizeVb,crustVb;
    FlowPane toppingFp;
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Heading
        headingT=new Text("Order Your Pizza Now!");
        headingT.setFont(new Font(20));
        headigHb=new HBox(headingT);
        headigHb.setPadding(new Insets(20,10,20,10));
        //Customer Details
        nameTf=new TextField();
        nameTf.setPromptText("Enter the customer's name");
        phNoTf=new TextField();
        phNoTf.setPromptText("Enter the Customer's Phone Number ");
        addressTf=new TextField();
        addressTf.setPromptText("Enter customer Address");
        nameTl=new Label("Name: ");
        phNoTl=new Label("Phone Number: ");
        addressTl=new Label("Address");
        detailslabelVb=new VBox(13,nameTl,phNoTl,addressTl);
        detailsTfVb=new VBox(5,nameTf,phNoTf,addressTf);
        CustomerDetailsHb =new HBox(detailslabelVb,detailsTfVb);
        //size

        sizeTl=new Label("Size");
        smallRb=new RadioButton("Small");
        mediumRb=new RadioButton("Medium");
        largeRb=new RadioButton("Large");
        ToggleGroup sizeTG=new ToggleGroup();
        smallRb.setToggleGroup(sizeTG);
        mediumRb.setToggleGroup(sizeTG);
        largeRb.setToggleGroup(sizeTG);
        sizeVb=new VBox(sizeTl,smallRb,mediumRb,largeRb);
        sizeVb.setSpacing(10);
        //Crust Area
        crustTl=new Label("Crust");
        thinRb=new RadioButton("Thin");
        thickRb=new RadioButton("Thick");
        ToggleGroup crustTG=new ToggleGroup();
        thinRb.setToggleGroup(crustTG);
        thickRb.setToggleGroup(crustTG);
        crustVb=new VBox(crustTl,thinRb,thickRb);
        crustVb.setSpacing(10);
        crustVb.setPadding(new Insets(0,0,0,10));
        //Topping Area
        toppingsTl=new Label("Tooping");
        pepperoniCb=new CheckBox("Pepperoni");
        sausageCb=new CheckBox("Sausage");
        linguicaCb=new CheckBox("Lingoica");
        olivesCb=new CheckBox("Olives");
        mushroomsCb=new CheckBox("Mushrooms");
        tomatoesCb=new CheckBox("Tomatoes");
        anchoviesCb=new CheckBox("Anchovies");
        toppingFp=new FlowPane(Orientation.VERTICAL,pepperoniCb,sausageCb,linguicaCb,olivesCb,mushroomsCb,tomatoesCb,anchoviesCb);

        toppingFp.setPadding(new Insets(10,0,10,0));
        toppingFp.setHgap(20);
        toppingFp.setVgap(10);
        toppingFp.setPrefWrapLength(100);
        VBox ToopingVB=new VBox(toppingsTl,toppingFp);
        ToopingVB.setPadding(new Insets(0,0,0,15));

        pizzaDetailsHb=new HBox(sizeVb,crustVb,ToopingVB);

        VBox centerVB=new VBox(20,CustomerDetailsHb,pizzaDetailsHb);
        centerVB.setPadding(new Insets(0,10,0,10));

        //Button Area
        cancleBtn=new Button("Cancle");
        okBtn=new Button("OK");
        okBtn.setPrefWidth(80);
        okBtn.setOnAction(e-> btnOK_Click() );
        
        cancleBtn.setPrefWidth(80);
        cancleBtn.setOnAction(e-> btnCancle_Click());
        Region spacer=new Region();
        HBox BtnHB=new HBox(10,spacer,okBtn,cancleBtn);

        BtnHB.setPadding(new Insets(20,10,20,10));
        BorderPane mainBP=new BorderPane();
        mainBP.setTop(headigHb);
        mainBP.setCenter(centerVB);
        mainBP.setBottom(BtnHB);




        stage=primaryStage;


        Scene Screen =new Scene(mainBP);
        primaryStage.setScene(Screen);
        primaryStage.setTitle("Pizza Order");


        primaryStage.show();
    }

    private void btnOK_Click() {
        String msg = "Customer:\n\n";
        msg += "\t" + nameTf.getText() + "\n";
        msg += "\t" + addressTf.getText() + "\n";
        msg += "\t" + phNoTf.getText() + "\n\n";
        msg += "You have ordered a ";
        // Add the pizza size
        if (smallRb.isSelected())
            msg += "small ";
        if (mediumRb.isSelected())
            msg += "medium ";
        if (largeRb.isSelected())
            msg += "large ";
        // Add the crust style
        if (thinRb.isSelected())
            msg += "thin crust pizza with ";
        if (thickRb.isSelected())
            msg += "thick crust pizza with ";
        // Add the toppings
        String toppings = "";
        toppings = buildToppings(pepperoniCb, toppings);
        toppings = buildToppings(sausageCb, toppings);
        toppings = buildToppings(linguicaCb, toppings);
        toppings = buildToppings(olivesCb, toppings);
        toppings = buildToppings(tomatoesCb, toppings);
        toppings = buildToppings(mushroomsCb, toppings);
        toppings = buildToppings(anchoviesCb, toppings);
        if (toppings.equals(""))
            msg += "no toppings.";
        else
            msg += "the following toppings:\n"
                    + toppings;
        // Display the message
        //MessageBox.show(msg, "Order Details");
        Alert output=new Alert(Alert.AlertType.INFORMATION);
        output.setContentText(msg);

    }
    public String buildToppings(CheckBox chk, String msg)
    {
        // Helper method for displaying the list of toppings
        if (chk.isSelected())
        {
            if (!msg.equals(""))
            {
                msg += ", ";
            }
            msg += chk.getText();
        }
        return msg;
    }

    private void btnCancle_Click() {
    stage.close();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
