import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.io.File;

public class PlagiarismApp extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Plagiarism Checker");

        TextField file1Field = new TextField();
        TextField file2Field = new TextField();
        file1Field.setEditable(false);
        file2Field.setEditable(false);

        Button browse1 = new Button("Choose File 1");
        Button browse2 = new Button("Choose File 2");
        Button analyze = new Button("Analyze");
        TextArea log = new TextArea();
        log.setEditable(false);
        log.setPrefRowCount(8);

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Text File");

        browse1.setOnAction(e -> {
            File f = chooser.showOpenDialog(stage);
            if (f != null) file1Field.setText(f.getAbsolutePath());
        });

        browse2.setOnAction(e -> {
            File f = chooser.showOpenDialog(stage);
            if (f != null) file2Field.setText(f.getAbsolutePath());
        });

        analyze.setOnAction(e -> {
            String p1 = file1Field.getText();
            String p2 = file2Field.getText();
            if (p1.isBlank() || p2.isBlank()) {
                log.appendText("Please select both files.\n");
                return;
            }
            try {
                AnalysisResult result = PlagiarismAnalyzer.analyze(p1, p2);
                log.appendText(String.format(
                        "Analysis complete.\nCosine: %.2f%%  Jaccard: %.2f%%\n",
                        result.cosineScore, result.jaccardScore));

                // open report.html automatically
                File report = new File("report.html");
                if (report.exists() && Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().browse(report.toURI());
                    log.appendText("Opened report.html in browser.\n");
                } else {
                    log.appendText("Report generated at: " + report.getAbsolutePath() + "\n");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                log.appendText("Error: " + ex.getMessage() + "\n");
            }
        });

        GridPane grid = new GridPane();
        grid.setHgap(8);
        grid.setVgap(8);
        grid.add(new Label("File 1:"), 0, 0);
        grid.add(file1Field, 1, 0);
        grid.add(browse1, 2, 0);
        grid.add(new Label("File 2:"), 0, 1);
        grid.add(file2Field, 1, 1);
        grid.add(browse2, 2, 1);
        grid.add(analyze, 1, 2);

        VBox root = new VBox(10, grid, log);
        root.setPadding(new Insets(15));

        stage.setScene(new Scene(root, 700, 350));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
