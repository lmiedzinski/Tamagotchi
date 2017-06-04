package pl.dbjllmjk.Model;
import javax.swing.ImageIcon;

/**
 * Class which allows to connect a {@link Pet} with an image (displayed in user interface).
 */
public class PetEntry {
	  private String title;
	  /**
	   * (Non)relative path to image.
	   */
	  private String imagePath;
	  private ImageIcon image;
	  /**
	   * Constructor with parameters.
	   * @param title
	   * @param imagePath
	   */
	  public PetEntry(String title, String imagePath) {
	    this.title = title;
	    this.imagePath = imagePath;
	  }
	  /**
	   * @return Title of {@link PetEntry}
	   */
	  public String getTitle() {
	    return title;
	  }
	  
	  /**
	   * @return Image representing {@link PetEntry} 
	   */
	  public ImageIcon getImage() {
	    if (image == null) {
	      image = new ImageIcon(imagePath);
	    }
	    return image;
	  }
	  @Override
	  public String toString() {
	    return title;
	  }
}
