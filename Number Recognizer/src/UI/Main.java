package UI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Process.Work;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main extends Application {
    Path path;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Image image = new Image(new FileInputStream("C:\\Users\\scara\\IdeaProjects\\FinalProject\\Pictures\\NumberIconNew.jpg"));
        // create a image View
        ImageView imageView = new ImageView(image);
        //Setting the position of the image
        imageView.setX(100);
        imageView.setY(150);
        //setting the fit height and width of the image view
        imageView.setFitHeight(455);
        imageView.setFitWidth(500);
        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);
        //Drawing a text
        Text text = new Text("Let's Recognize the Number");
        //Setting the font of the text
        text.setFont(Font.font("Comic Sans MS", FontWeight.BLACK, 50));
        //Setting the position of the text
        text.setX(500);
        text.setY(100);
        //Setting color to the text
        text.setFill(Color.BEIGE);
        text.setStrokeWidth(2);
        text.setStroke(Color.DARKSLATEBLUE);
        Button button2 =new Button("Exit");
        button2.relocate(750, 425);
        button2.setMinSize(100, 75);
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Exiting the App");
                System.exit(0);
            }
        });
        //Creating a Group object
        Group root = new Group(imageView, text);
        root.getChildren().add(button2);
        //Creating a scene object
        Scene scene = new Scene(root, 1550, 800);
        scene.setFill(Color.BEIGE);
        //Setting title to the Stage
        primaryStage.setTitle("Number Recognizer");
        //Adding scene to the stage
        primaryStage.setScene(scene);
        //Displaying the contents of the stage
        primaryStage.show();
        //Recognition Page
        EventHandler myEventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Text text = new Text("Draw the Number");
                //Setting the font of the text
                text.setFont(Font.font("Edwardian Script ITC", 50));
                //Setting the position of the text
                text.setX(650);
                text.setY(100);
                text.setFill(Color.BEIGE);
                text.setStrokeWidth(2);
                text.setStroke(Color.DARKSLATEBLUE);
                Button button21 = new Button("Draw Another Number");
                button21.setOnAction(this);
                button21.relocate(1200, 650);
                button21.setMinSize(100, 75);
                Button button22 = new Button("Exit");
                button22.relocate(1400, 650);
                button22.setMinSize(100, 75);
                button22.setOnAction(new EventHandler<>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        System.out.println("Exiting the App");
                        System.exit(0);
                    }
                });
                Canvas canvas = new Canvas(750, 750);
                canvas.relocate(50,0);
                GraphicsContext graphics_context =
                        canvas.getGraphicsContext2D();
                graphics_context.setFill(Color.WHITE);
                graphics_context.fillRect(250, 250, 350, 350);
                Group root2 = new Group(text);
                root2.getChildren().add(button22);
                root2.getChildren().add(button21);
                root2.getChildren().add(canvas);
                Scene secondScene = new Scene(root2, 1550, 800);
                secondScene.setFill(Color.BEIGE);
                path = new Path();
                path.relocate(50,0);
                path.setStrokeWidth(30);
                path.setStroke(Color.GRAY);
                canvas.setOnMouseClicked(mouseHandler);
                canvas.setOnMouseDragged(mouseHandler);
                canvas.setOnMouseEntered(mouseHandler);
                canvas.setOnMouseExited(mouseHandler);
                canvas.setOnMouseMoved(mouseHandler);
                canvas.setOnMousePressed(mouseHandler);
                canvas.setOnMouseReleased(mouseHandler);
                root2.getChildren().add(path);
                Stage secondStage = new Stage();
                secondStage.setScene(secondScene);
                secondStage.setTitle("Recognition Page");
                secondStage.show();
                Button btn = new Button();
                btn.setText("Recognize");btn.relocate(800,450);btn.setMinSize(100, 75);
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            takeSnapShot();
                            Work work=new Work();
                            String img=work.Work();
                            Image images = new Image(new FileInputStream(img));
                            ImageView imageView = new ImageView(images);
                            //Setting the position of the result image
                            imageView.setX(1000);
                            imageView.setY(250);
                            //setting the fit height and width of the result image view
                            imageView.setFitHeight(350);
                            imageView.setFitWidth(350);
                            root2.getChildren().add(imageView);
                        } catch (AWTException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                root2.getChildren().add(btn);
                primaryStage.close();
            }
        };
        Button button1 = new Button("Click To draw");
        button1.relocate(750, 250);
        button1.setMinSize(100, 75);
        button1.setOnAction(myEventHandler);
        root.getChildren().add(button1);
    }

    //Capturing Mouse Activity
    EventHandler<MouseEvent> mouseHandler = new EventHandler<>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
                path.getElements()
                        .add(new MoveTo(mouseEvent.getX(), mouseEvent.getY()));
            } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                path.getElements()
                        .add(new LineTo(mouseEvent.getX(), mouseEvent.getY()));
            }
        }
    };

    //Capturing Canvas
    private void takeSnapShot() throws AWTException, IOException {
        int X = 280;
        int Y = 270;
        int width = 350;
        int height = 350;
        Rectangle screenRect = new Rectangle(X, Y, width, height);
        BufferedImage capture = new Robot().createScreenCapture(screenRect);
        ImageIO.write(capture, "png", new File("C:\\Users\\scara\\IdeaProjects\\FinalProject\\Pictures\\capture.png"));
        BufferedImage scale=getScaledImage(capture,28,28);
        ImageIO.write(scale, "png", new File("C:\\Users\\scara\\IdeaProjects\\FinalProject\\Pictures\\pythonInput.png"));
    }

    //Converting the image into 28x28
    private BufferedImage getScaledImage(BufferedImage srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, Transparency.TRANSLUCENT);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
