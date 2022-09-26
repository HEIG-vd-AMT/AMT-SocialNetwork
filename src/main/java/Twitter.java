import java.util.ArrayList;
import java.util.List;

public class Twitter implements IObservable {

    //region private attributes
    private List<IObserver> _observers = new ArrayList<IObserver>();
    private List<String> _twits = new ArrayList<String>();
    //endregion private attributes

    public Twitter(){}

    public Twitter(List<IObserver> observers){
        throw new UnsupportedOperationException();
    }

    public List<IObserver> getObservers(){
        throw new UnsupportedOperationException();
    }

    public List<String> getTwits(){
        throw new UnsupportedOperationException();
    }

    public String lastTwit(){
        throw new UnsupportedOperationException();
    }

    public void post(String twit){
        throw new UnsupportedOperationException();
    }

    @Override
    public void subscribe(List<IObserver> observer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unsubscribe(IObserver observer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void notifyObservers() {
        throw new UnsupportedOperationException();
    }

    public class TwitterException extends Exception { }
    public class EmptyListOfSubscribersException extends TwitterException { }
    public class SubscriberAlreadyExistsException extends TwitterException { }
    public class SubscriberNotFoundException extends TwitterException { }
}
