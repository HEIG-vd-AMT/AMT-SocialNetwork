import java.util.List;

public interface IObservable {
    void subscribe(List<IObserver> observers) throws SubscriberAlreadyExistsException;
    void unsubscribe(IObserver observer);
    void notifyObservers() throws EmptyListOfSubscribersException;
}
