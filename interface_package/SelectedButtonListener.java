package interface_package;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SelectedButtonListener implements PropertyChangeListener {
	   @Override
	   public void propertyChange(PropertyChangeEvent evt) {
	      System.out.println("t");
	   }
	}
