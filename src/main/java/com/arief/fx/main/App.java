package com.arief.fx.main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Created by Arief on 8/23/2017.
 */

public class App extends Application{


    /*
        java.awt.Desktop merupakan API di java yang berfungsi membuka suatu file
        , kelas ini akan membuka file tersebut berdasarkan os kita masing2
         jika kita membuka file gambar , maka objek ini akan memanggil aplikasi default untuk membuka gambar
         di os kita seperti (aplikasi Photo di windows 10 )
    */
    private Desktop desktop = Desktop.getDesktop();

    public void start(Stage primaryStage) throws Exception {

        FileChooser fileChooser = new FileChooser();

        VBox box = new VBox(10.5);

        GridPane grid = new GridPane();

        grid.setHgap(200);
        grid.setVgap(80);

        Button openFchooser = new Button("Open File Chooser");
        Button openMovie  = new Button("Open Movie File");
        Button openMultiple = new Button("Open Multiple Dialog");
        Button saveFile = new Button("Save Text to File");
        Button buatDirectory = new Button("Buat Dir");

        TextField isiFile = new TextField();

        Label namaFile = new Label("Nama File");
        Label hasilNama = new Label("Disini Nama Filenya");


        hasilNama.setWrapText(true);

        Label pathFile = new Label("Path File");
        Label hasilPath = new Label("Disini Path filenya");


        hasilPath.setWrapText(true);


        grid.add(namaFile,0,0);
        grid.add(hasilNama,1,0);
        grid.add(pathFile,0,1);
        grid.add(hasilPath,1,1);

        box.setPadding(new Insets(8));

        box.getChildren().add(isiFile);
        box.getChildren().add(openFchooser);
        box.getChildren().add(openMovie);
        box.getChildren().add(openMultiple);
        box.getChildren().add(saveFile);
        box.getChildren().add(buatDirectory);

        box.getChildren().add(grid);


        saveFile.setPrefWidth(600);
        isiFile.setPrefWidth(600);
        openFchooser.setPrefWidth(600);
        openMovie.setPrefWidth(600);

        openFchooser.setAlignment(Pos.CENTER);
        openFchooser.setAlignment(Pos.CENTER);


        buatDirectory.setOnAction(e->{
            fileChooser.getExtensionFilters().clear();
            fileChooser.setSelectedExtensionFilter(null);
            fileChooser.setTitle("Directory Maker");


            File buatDir = fileChooser.showSaveDialog(primaryStage);

            boolean valid = buatDir.mkdir();

            if(valid){
               System.out.println("Directory dibuat");
            }

        });


        saveFile.setOnAction(e->{

            //fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("MyText",".txt"));

            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("MyText",".txt"));
            File file = fileChooser.showSaveDialog(primaryStage);

            if(file!=null && !isiFile.getText().equals("")){
                FileWriter writer = null;
                try {


                    writer = new FileWriter(file,true);


                    writer.write(isiFile.getText());
                    writer.flush();


                } catch (IOException e1) {
                    e1.printStackTrace();
                }finally {
                    if(writer!=null){
                        try {
                            writer.close();

                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }

        });

        openMultiple.setOnAction(e->{
            List<File> files = fileChooser.showOpenMultipleDialog(primaryStage);

            if (files!=null)
            files.forEach(f->{
                System.out.println(f.getName());
            });

        });

        openMovie.setOnAction(e->{
            fileChooser.setInitialDirectory(new File("A:\\Movie\\boruto"));
            File movie = fileChooser.showOpenDialog(primaryStage);

            if (movie!=null)
            bukaFile(movie);

        });


        openFchooser.setOnAction(e->{

            fileChooser.getExtensionFilters().clear();
            fileChooser.setSelectedExtensionFilter(null);

            File file = fileChooser.showOpenDialog(primaryStage);



            if(file!=null) {

                FileReader reader = null;
                try {

                    String ekstensi = file.getName();

                    String eksName = ekstensi.substring(ekstensi.lastIndexOf("."),ekstensi.length());

                    if(eksName.contains(".txt")){

                        reader = new FileReader(file);
                        String a = "";

                        int c;
                        while ((c=reader.read())!=-1){
                            a+=(char)c;
                        }


                        isiFile.setText(a);
                    }

                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }finally {
                    if(reader!=null){
                        try {
                            reader.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });





        Scene s =new Scene(box);
        primaryStage.setScene(s);

        primaryStage.setWidth(600);
        primaryStage.setHeight(600);
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    private static void bukaFile(File file){
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[]x){
        launch(x);
    }

}
