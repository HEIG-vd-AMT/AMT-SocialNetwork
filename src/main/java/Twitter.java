import java.util.ArrayList;
import java.util.List;

public class Twitter implements IObservable {
    private List<IObserver> _observers = new ArrayList<IObserver>();
    private final List<String> _twits = new ArrayList<String>();

    public Twitter() {}
    public Twitter(List<IObserver> observers) {
        subscribe(observers);
    }

    public List<IObserver> getObservers(){
        return _observers;
    }

    public List<String> getTwits(){
        return _twits;
    }

    public String lastTwit() {
        if(_twits.isEmpty())
            throw new TwitterException();
        return _twits.get(_twits.size() - 1);
    }

    public void post(String twit){
        _twits.add(twit);
    }

    @Override
    public void subscribe(List<IObserver> observer) {
        for (IObserver observerToAdd : observer) {
            if(_observers.contains(observerToAdd)) {
                throw new SubscriberAlreadyExistsException();
            } else {
                _observers.add(observerToAdd);
            }
        }
    }

    @Override
    public void unsubscribe(IObserver observer) {
        if(_observers.isEmpty())
            throw new EmptyListOfSubscribersException();
        if(_observers.contains(observer))
            _observers.remove(observer);
        else
            throw new SubscriberNotFoundException();
    }

    @Override
    public void notifyObservers() {
        if(_observers.isEmpty())
            throw new EmptyListOfSubscribersException();
        else
            _observers.notifyAll();
    }

    public class TwitterException extends RuntimeException { }
    public class EmptyListOfSubscribersException extends TwitterException { }
    public class SubscriberAlreadyExistsException extends TwitterException { }
    public class SubscriberNotFoundException extends TwitterException { }
}
