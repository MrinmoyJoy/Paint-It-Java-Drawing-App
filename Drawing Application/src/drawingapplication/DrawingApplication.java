
package drawingapplication;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.event.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;


public class DrawingApplication extends Application {
    
    
    //Global Variable
    static Color color;
    static String mode = "";
    static double length;
    Line line = new Line();
    Rectangle rect = new Rectangle();
    Circle circle = new Circle();

    @Override
    public void start(Stage primaryStage) {

        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setStyle("-fx-font-weight: bold");

        Label label = new Label("Brush Width");
        label.setStyle("-fx-font-weight: bold");
        
        Label label2 = new Label("Color");
        label2.setStyle("-fx-font-weight: bold");
        
        Label labeltool = new Label("Tools");
        labeltool.setStyle("-fx-font-weight: bold");
        
        Label labelload = new Label("Save & Load");
        labelload.setStyle("-fx-font-weight: bold");

        ScrollBar scrollBar = new ScrollBar();
        scrollBar.setMin(5);
        scrollBar.setMax(150);
        scrollBar.setValue(20);

        colorPicker.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                color = colorPicker.getValue();
            }
        });

        Button paintBtn = new Button("Paint");
        paintBtn.setStyle("-fx-font-weight: bold");
        paintBtn.setMaxWidth(124);

        Button strokeBtn = new Button("Pen");
        strokeBtn.setStyle("-fx-font-weight: bold");
        strokeBtn.setMaxWidth(124);
        
        Button eraserBtn = new Button("Eraser");
        eraserBtn.setStyle("-fx-font-weight: bold");
        eraserBtn.setMaxWidth(124);
        
        Button fillStrokeBtn = new Button("Fill Stroke");
        fillStrokeBtn.setStyle("-fx-font-weight: bold");
        fillStrokeBtn.setMaxWidth(124);
        
        Button lineBtn = new Button("Line");
        lineBtn.setStyle("-fx-font-weight: bold");
        lineBtn.setMaxWidth(124);

        Button rectangleBtn = new Button("Rectangle");
        rectangleBtn.setStyle("-fx-font-weight: bold");
        rectangleBtn.setMaxWidth(124);
        
        Button fillRectBtn = new Button("Fill Rectangle");
        fillRectBtn.setStyle("-fx-font-weight: bold");
        fillRectBtn.setMaxWidth(124);

        Button circleBtn = new Button("Circle");
        circleBtn.setStyle("-fx-font-weight: bold");
        circleBtn.setMaxWidth(124);
        
        Button fillCircleBtn = new Button("Fill Circle");
        fillCircleBtn.setStyle("-fx-font-weight: bold");
        fillCircleBtn.setMaxWidth(124);

        Button saveBtn = new Button("Save");
        saveBtn.setStyle("-fx-font-weight: bold");
        saveBtn.setMaxWidth(124);
        
        Button openBtn = new Button("Open");
        openBtn.setStyle("-fx-font-weight: bold");
        openBtn.setMaxWidth(124);

        Button resetBtn = new Button("Reset");
        resetBtn.setStyle("-fx-font-weight: bold");
        resetBtn.setMaxWidth(124);

        
        paintBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                mode = "Paint";
            }
        });

        strokeBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                mode = "Stroke";
            }
        });

        fillStrokeBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                mode = "Fill Stroke";
            }
        });

        eraserBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                mode = "Eraser";
            }
        });
        
        lineBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                mode = "Line";
            }
        });

        rectangleBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                mode = "Rectangle";
            }
        });

        fillRectBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                mode = "Fill Rect";
            }
        });

        circleBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                mode = "Circle";
            }
        });

        fillCircleBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                mode = "Fill Circle";
            }
        });

        
        VBox btns = new VBox(17);
        btns.getChildren().addAll(label2,colorPicker, label, scrollBar, labeltool, paintBtn, strokeBtn,lineBtn,  fillStrokeBtn, rectangleBtn, fillRectBtn, circleBtn, fillCircleBtn,eraserBtn, resetBtn,labelload,saveBtn, openBtn);
        btns.setStyle("-fx-background-color: lightblue;-fx-border-color: #4b77be");
        //btns.setStyle("-fx-border-color: #4b77be");
        btns.setPrefWidth(125);

        Canvas canvas = new Canvas(1200, 800);
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.setLineWidth(2);

        canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                if (mode == "Paint") {
                    context.setFill(colorPicker.getValue());
                    length = scrollBar.getValue();
                }
                
                else if(mode=="Eraser"){
                    length = scrollBar.getValue();
                }
                else if (mode == "Stroke") {
                    context.setStroke(Color.BLACK);
                    context.beginPath();

                } else if (mode == "Fill Stroke") {

                    context.setStroke(colorPicker.getValue());
                    context.setFill(colorPicker.getValue());
                    context.beginPath();
                    line.setStartX(e.getX());
                    line.setStartY(e.getY());
                } else if (mode == "Rectangle") {

                    context.setStroke(colorPicker.getValue());

                    rect.setX(e.getX());
                    rect.setY(e.getY());

                }
                else if (mode == "Line") {

                    context.setStroke(colorPicker.getValue());

                    rect.setX(e.getX());
                    rect.setY(e.getY());

                }else if (mode == "Fill Rect") {
                    context.setFill(colorPicker.getValue());

                    rect.setX(e.getX());
                    rect.setY(e.getY());
                } else if (mode == "Circle") {
                    context.setStroke(colorPicker.getValue());

                    circle.setCenterX(e.getX());
                    circle.setCenterY(e.getY());
                } else if (mode == "Fill Circle") {
                    context.setFill(colorPicker.getValue());

                    circle.setCenterX(e.getX());
                    circle.setCenterY(e.getY());
                }
            }
        });

        canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                if (mode == "Paint") {
                    //context.fillRect(e.getX(), e.getY(), length, length);
                    context.fillRoundRect(e.getX(), e.getY(), length, length,100,100);
                }
                else if(mode=="Eraser"){
                    context.clearRect(e.getX(), e.getY(), length, length);
                }
                else if (mode == "Stroke" || mode == "Fill Stroke") {
                    context.lineTo(e.getX(), e.getY());
                    context.stroke();
                }

            }
        });

        canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                if (mode == "Stroke") {

                    context.closePath();
                } else if (mode == "Fill Stroke") {
                    line.setEndX(e.getX());
                    line.setEndY(e.getY());
                    context.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
                    context.fill();
                } else if (mode == "Rectangle") {

                    rect.setWidth(Math.abs(e.getX() - rect.getX()));
                    rect.setHeight(Math.abs(e.getY() - rect.getY()));

                    if (rect.getX() > e.getX()) {
                        rect.setX(e.getX());
                    }

                    if (rect.getY() > e.getY()) {
                        rect.setY(e.getY());
                    }

                    context.strokeRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
                }
                
                else if (mode == "Line") {

                    context.setStroke(colorPicker.getValue());

                    context.strokeLine(rect.getX(), rect.getY(), e.getX(), e.getY());

                }else if (mode == "Fill Rect") {

                    rect.setWidth(Math.abs(e.getX() - rect.getX()));
                    rect.setHeight(Math.abs(e.getY() - rect.getY()));

                    if (rect.getX() > e.getX()) {
                        rect.setX(e.getX());
                    }

                    if (rect.getY() > e.getY()) {
                        rect.setY(e.getY());
                    }

                    context.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
                } else if (mode == "Circle") {

                    circle.setRadius(Math.sqrt(Math.pow(circle.getCenterX() - e.getX(), 2) + Math.pow(circle.getCenterY() - e.getY(), 2)));

                    context.strokeOval(circle.getCenterX(), circle.getCenterY(), circle.getRadius(), circle.getRadius());

                } else if (mode == "Fill Circle") {

                    circle.setRadius(Math.sqrt(Math.pow(circle.getCenterX() - e.getX(), 2) + Math.pow(circle.getCenterY() - e.getY(), 2)));

                    context.fillOval(circle.getCenterX(), circle.getCenterY(), circle.getRadius(), circle.getRadius());

                }

            }
        });

        resetBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                context.clearRect(0, 0, 1200, 800);
            }
        });

        // Open
        openBtn.setOnAction((e) -> {
            FileChooser openFile = new FileChooser();
            openFile.setTitle("Open File");
            File file = openFile.showOpenDialog(primaryStage);
            if (file != null) {
                try {
                    InputStream io = new FileInputStream(file);
                    Image img = new Image(io);
                    context.drawImage(img, 0, 0);
                } catch (IOException ex) {
                    System.out.println("Error!");
                }
            }
        });

        // Save
        saveBtn.setOnAction((e) -> {
            FileChooser savefile = new FileChooser();
            savefile.setTitle("Save File");

            File file = savefile.showSaveDialog(primaryStage);
            if (file != null) {
                try {
                    WritableImage writableImage = new WritableImage(1200, 800);
                    canvas.snapshot(null, writableImage);
                    RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                    ImageIO.write(renderedImage, "png", file);
                } catch (IOException ex) {
                    System.out.println("Error!");
                }
            }

        });
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(btns);
        borderPane.setCenter(canvas);

        Scene scene = new Scene(borderPane);

        // show stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Paint It Out!!!");
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
