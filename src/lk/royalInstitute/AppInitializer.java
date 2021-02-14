package lk.royalInstitute;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lk.royalInstitute.util.FactoryConfiguration;
import org.hibernate.Session;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
//        primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("view/UserAccountForm.fxml"))));
        primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("view/LoginForm.fxml"))));
        primaryStage.setTitle("Royal Institute Management System");
        primaryStage.getIcons().add(new Image("lk/royalInstitute/assets/icons8-add-user-male-48.png"));
        primaryStage.centerOnScreen();
        primaryStage.show();
        new Thread(() -> {
            Session session = FactoryConfiguration.getInstance().getSession();
            System.out.println(session.isOpen());
//            Query query = session.createQuery("select  from Student where student_ID=?1");
//            query.setParameter(1,"S001");
//            Student o = (Student) query.uniqueResult();
//            System.out.println(o);
            session.close();
        }).start();
    }
}
