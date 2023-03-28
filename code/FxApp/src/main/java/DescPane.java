import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;


public class DescPane extends BorderPane {
        private Label lblImageTitle = new Label();
        private TextArea taDesc = new TextArea();

        public DescPane() {
                lblImageTitle.setContentDisplay(ContentDisplay.TOP);
                lblImageTitle.setPrefSize(200, 100);
                lblImageTitle.setFont(new Font("SansSerif", 16));

                taDesc.setWrapText(true);
                taDesc.setEditable(false);

                ScrollPane scrollPane = new ScrollPane(taDesc);

                this.setLeft(lblImageTitle);
                this.setCenter(scrollPane);
                this.setPadding(new Insets(5, 5, 5, 5));
        }

        public void setTitle(String Title)
        {
                lblImageTitle.setText(Title);
        }

        public void setImageView(ImageView icon)
        {
                lblImageTitle.setGraphic(icon);
        }

        public void setDesc(String desc)
        {
                taDesc.setText(desc);

        }





        }
