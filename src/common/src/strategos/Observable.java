package strategos;

import java.util.Observer;

public interface Observable {

	public void addObserver(Observer o);

	public void setChanged();

	public boolean hasChanged();

	public void notifyObservers(Object o);

}
