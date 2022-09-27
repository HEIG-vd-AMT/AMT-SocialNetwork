import java.util.ArrayList;
import java.util.List;

public class Twitter implements IObservable {

    //region private attributes
    private List<IObserver> _observers = new ArrayList<IObserver>();
    private List<String> _twits = new ArrayList<String>();
    //endregion private attributes

    public Twitter(){}

    public Twitter(List<IObserver> observers){
        _observers = observers;
    }

    public List<IObserver> getObservers(){
        return _observers;
    }

    public List<String> getTwits(){
        return _twits;
    }

    public String lastTwit(){
        return _twits.get(_twits.size() - 1);
    }

    public void post(String twit){
        _twits.add(twit);
    }

    @Override
    public void subscribe(List<IObserver> observer) {
        if(observer.isEmpty())
            throw new EmptyListOfSubscribersException();

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
        _observers.notifyAll();
    }

    public class TwitterException extends RuntimeException { }
    public class EmptyListOfSubscribersException extends TwitterException {

    }
    public class SubscriberAlreadyExistsException extends TwitterException { }
    public class SubscriberNotFoundException extends TwitterException {

    }
}
