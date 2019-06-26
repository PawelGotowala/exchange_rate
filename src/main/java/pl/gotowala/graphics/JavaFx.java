package pl.gotowala.graphics;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.gotowala._model.CurrencyType;
import pl.gotowala.functionality.ExchangeRatesProcessing;


public class JavaFx extends Application {

    public static void showAlertMessage(String textForMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Result");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText(textForMessage);

        alert.showAndWait();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        Stage window = primaryStage;
        window.setTitle("Program");


        Label currencyL = new Label("Select currency:  ");
        Label startDateL = new Label("Start date (format YYYY-MM-DD) :  ");
        Label endDateL = new Label("End date (format YYYY-MM-DD) :  ");


        ObservableList<CurrencyType> currencyTypes = FXCollections.observableArrayList(CurrencyType.values());
        ChoiceBox<CurrencyType> choiceBox = new ChoiceBox<>(currencyTypes);

        TextField textFieldStartDate = new TextField();
        textFieldStartDate.setMaxSize(100, 100);
        TextField textFieldEndDate = new TextField();
        textFieldEndDate.setMaxSize(100, 100);

        Button start = new Button("start");

        start.setOnAction(e -> {
                    String a = textFieldStartDate.getText();
                    String b = textFieldEndDate.getText();
                    CurrencyType c = choiceBox.getValue();
                    ExchangeRatesProcessing.putExchangeRatesToFieldRates(a, b, c);
                    double avarage = ExchangeRatesProcessing.getAverageOfRates(ExchangeRatesProcessing.getAllAskRates());
                    double std = ExchangeRatesProcessing.getStandardDeviationOfRates(ExchangeRatesProcessing.getAllBidRates());
                    showAlertMessage("Average of Ask Rates: " + avarage + "\nStandard Deviation of Bid Rates: " + std);
                }
        );

        VBox root = new VBox();
        root.getChildren().addAll(currencyL, choiceBox, startDateL, textFieldStartDate, endDateL, textFieldEndDate, start);
        Scene firtsScene = new Scene(root, 250, 250);

        window.setScene(firtsScene);
        window.setMinWidth(250);
        window.setMinHeight(250);
        window.setMaxHeight(250);
        window.setMaxWidth(250);
        window.show();
    }

}


