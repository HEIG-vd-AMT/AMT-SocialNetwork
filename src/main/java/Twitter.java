import java.util.LinkedList;
import java.util.List;

public class Twitter implements IObservable {
    private List<IObserver> _observers = new LinkedList<>();
    private List<String> _twits = new LinkedList<>();

    public Twitter() {}

    public Twitter(List<IObserver> observers) {
        _observers = observers;
    }

    @Override
    public void subscribe(List<IObserver> observers) {
        for (IObserver observer : observers) {
            if (_observers.contains(observer)) {
                throw new SubscriberAlreadyExistsException();
            }
            _observers.add(observer);
        }
    }

    @Override
    public void unsubscribe(IObserver observer) {
        if (_observers.isEmpty()) {
            throw new EmptyListOfSubscribersException();
        }
        if (!_observers.remove(observer)) {
            throw new SubscriberNotFoundException();
        };
    }

    @Override
    public void notifyObservers() throws EmptyListOfSubscribersException {
        if (_observers.isEmpty()) {
            throw new EmptyListOfSubscribersException();
        }

    }

    public List<IObserver> getObservers() {
        return _observers;
    }

    public List<String> getTwits() {
        return _twits;
    }

    public void post(String twit) {
        throw new UnsupportedOperationException();
    }

    private boolean exists(IObserver followerToFind) {
        throw new UnsupportedOperationException();
    }
}

